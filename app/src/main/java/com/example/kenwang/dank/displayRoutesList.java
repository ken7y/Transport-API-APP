package com.example.kenwang.dank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class displayRoutesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_routes_list);



        TextView textBox =(TextView) findViewById(R.id.startText);
        String startText = getIntent().getExtras().getString("startLocation");
        textBox.setText(startText);


        TextView endText =(TextView) findViewById(R.id.goalText);
        String goalText = getIntent().getExtras().getString("destinationLocation");
        endText.setText(goalText);


    }
}
