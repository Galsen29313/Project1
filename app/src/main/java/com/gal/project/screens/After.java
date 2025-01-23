package com.gal.project.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gal.project.R;

public class After extends AppCompatActivity implements View.OnClickListener {

    Button addEvent,searchEvent,information,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_after);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addEvent=findViewById(R.id.addE);
        addEvent.setOnClickListener(this);
        searchEvent=findViewById(R.id.searchE);
        searchEvent.setOnClickListener(this);
        information=findViewById(R.id.info);
        information.setOnClickListener(this);
        profile=findViewById(R.id.account);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==addEvent) {
            Intent go = new Intent(getApplicationContext(), AddNewEvent.class);
            startActivity(go);
        }
        if(v==searchEvent) {
            Intent go = new Intent(getApplicationContext(), SearchEvent.class);
            startActivity(go);
        }
        if(v==information) {
            Intent go = new Intent(getApplicationContext(), Info.class);
            startActivity(go);
        }
        if(v==profile) {


            Intent go = new Intent(getApplicationContext(), Profile.class);
            startActivity(go);
        }
    }
}