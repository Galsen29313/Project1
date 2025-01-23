package com.gal.project.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gal.project.R;
import com.gal.project.services.AuthenticationService;
import com.gal.project.services.DatabaseService;

public class AddNewEvent extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etDate,etTime,etAddress,etMaxMembers,etDescription;
    String name,date,time, address,members,descroption;
    Spinner spType,spCity;
    Button createEvent;
    Boolean isVaild = true;

    private AuthenticationService authenticationService;
    private DatabaseService databaseService;
    private com.gal.project.models.User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_event);
/// get the instance of the authentication service
        authenticationService = AuthenticationService.getInstance();
        /// get the instance of the database service
        databaseService = DatabaseService.getInstance();



        initViews();

    }
    private void initViews() {
        etName = findViewById(R.id.etEventName);
        etDate = findViewById(R.id.etEventDate);
        etTime = findViewById(R.id.etEventTime);
        etAddress = findViewById(R.id.etEventAddress);
        etMaxMembers = findViewById(R.id.etEventMaxMembers);
        etDescription = findViewById(R.id.etDesc);
        createEvent = findViewById(R.id.btnAddNewEvent);
        createEvent.setOnClickListener(this);

        spType = findViewById(R.id.spEventType);
        spType.setAdapter(spType.getAdapter());

        spCity = findViewById(R.id.spEventCity);
        spCity.setAdapter(spCity.getAdapter());


    }
    @Override
    public void onClick(View v) {
        if (v == createEvent) {
            name = etName.getText().toString();
            date = etDate.getText().toString();
            time = etTime.getText().toString();
            address = etAddress.getText().toString();
            members = etMaxMembers.getText().toString();
            descroption = etDescription.getText().toString();



            if (!name.isEmpty() && !date.isEmpty() && !time.isEmpty() && !address.isEmpty() && !members.isEmpty()) {
                etName.setText("");
                etDate.setText("");
                etTime.setText("");
                etAddress.setText("");
                etMaxMembers.setText("");

                if (name.length() < 2) {
                    Toast.makeText(AddNewEvent.this, "שם אירוע קצר מדי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (date.length() < 2) {
                    Toast.makeText(AddNewEvent.this, "תאריך לא תקין", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (!time.contains(":")) {
                    Toast.makeText(AddNewEvent.this, "מספר הטלפון לא תקין", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (address.contains("@")) {
                    Toast.makeText(AddNewEvent.this, "כתובת לא תקינה", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (etMaxMembers.length() < 1) {
                    Toast.makeText(AddNewEvent.this, "מספר משתתפים קטן מידי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }
                if (etMaxMembers.length() > 100) {
                    Toast.makeText(AddNewEvent.this, "מספר המשתתפים גודל מידי", Toast.LENGTH_LONG).show();
                    isVaild = false;
                }





            }
        }
    }

}