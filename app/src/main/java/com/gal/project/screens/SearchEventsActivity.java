package com.gal.project.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gal.project.R;
import com.gal.project.adapters.EventAdapter;
import com.gal.project.models.Event;
import com.gal.project.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class SearchEventsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;
    private DatabaseService databaseService;
    private ProgressBar progressBar;

    // Filter criteria
    private Spinner spinnerSportType, spinnerCity;
    private EditText editTextDate;
    private Button btnSearch;
    private String selectedSportType="", selectedCity="", enteredDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_events);



        recyclerView = findViewById(R.id.rcEvents);

        spinnerSportType = findViewById(R.id.spinnerSportType);
        spinnerCity = findViewById(R.id.spinnerCity);
        editTextDate = findViewById(R.id.editTextDate);
        btnSearch = findViewById(R.id.btnSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventList);
        recyclerView.setAdapter(eventAdapter);

        databaseService = DatabaseService.getInstance();

        // Get the entered date from the EditText
        // Setup Spinners (Dropdowns)
        setupSpinners();

        databaseService.getEvents(new DatabaseService.DatabaseCallback<List<Event>>() {
            @Override
            public void onCompleted(List<Event> events) {

                eventList.addAll(events);

                eventAdapter = new EventAdapter(eventList);
                recyclerView.setAdapter(eventAdapter);


                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        enteredDate = editTextDate.getText().toString();
                        List<Event> filteredEvents = new ArrayList<>();
                        for (Event event : eventList) {
                            //     Apply your filters here
                                   if(( event.getType().contains(selectedSportType))&& ( event.getCity().contains(selectedCity))&&(event.getDate().contains(enteredDate)))
                                       filteredEvents.add(event);
                               }

//


                                  EventAdapter adaptFilter=new EventAdapter(filteredEvents);
                                recyclerView.setAdapter(adaptFilter);
//




                    }
                });



            }

            @Override
            public void onFailed(Exception e) {

                Toast.makeText(SearchEventsActivity.this, "שגיאה בטעינת האירועים", Toast.LENGTH_SHORT).show();
                Log.e("SearchEventsActivity", "Error fetching events", e);
            }
        });



    }

    private void setupSpinners() {
        // Sport Type Spinner
        ArrayAdapter<CharSequence> sportAdapter = ArrayAdapter.createFromResource(this,
                R.array.arrTYpe, android.R.layout.simple_spinner_item);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSportType.setAdapter(sportAdapter);
        spinnerSportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(position>0) {
                    selectedSportType = parentView.getItemAtPosition(position).toString();
                }
                else   selectedSportType="";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedSportType="";

            }
        });

        // City Spinner
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.arrCity, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position>0) {
                    selectedCity = parentView.getItemAtPosition(position).toString();
                }
                else selectedCity="";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedCity="";
            }
        });






    }


    private void searchEvents() {



        // Example: Search events based on the selected filters
        databaseService.getEvents(new DatabaseService.DatabaseCallback<List<Event>>() {
            @Override
            public void onCompleted(List<Event> events) {
                progressBar.setVisibility(ProgressBar.GONE);


                List<Event> filteredEvents = new ArrayList<>();
                for (Event event : events) {
                //     Apply your filters here
                   if(event.getType().equals(selectedSportType)&& event.getCity().equals(selectedCity))
                        filteredEvents.add(event);
                    }

//


               // if(filteredEvents.size()>0)
             //   {
                    EventAdapter adaptFilter=new EventAdapter(filteredEvents);
                    recyclerView.setAdapter(adaptFilter);

             //   }
              //  else {

              //      eventList.clear();
              //      eventList.addAll(events);
              //      eventAdapter.notifyDataSetChanged();
              //  }
            }

            @Override
            public void onFailed(Exception e) {
                progressBar.setVisibility(ProgressBar.GONE);
                Toast.makeText(SearchEventsActivity.this, "שגיאה בטעינת האירועים", Toast.LENGTH_SHORT).show();
                Log.e("SearchEventsActivity", "Error fetching events", e);
            }
        });
    }
}
