package com.example.kenwang.dank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.transit.realtime.GtfsRealtime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class displayTransportList extends AppCompatActivity {
    String destinationLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transport_list);



        TextView textBox = (TextView) findViewById(R.id.LabelTest);
        String startTxt = "";
        startTxt = getIntent().getExtras().getString("startLocation");
        textBox.setText(startTxt);

        Button destinationHome = (Button) findViewById(R.id.destinationHome);
        Button destinationUni = (Button) findViewById(R.id.destinationUni);
        Button destinationMacq = (Button) findViewById(R.id.destinationMacq);
        Button destinationTownHall = (Button) findViewById(R.id.destinationTownHall);
        final Button destinationCentral = (Button) findViewById(R.id.destinationCentral);


        final String startLocation = startTxt;
        destinationHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLocation = "Home";
                Intent startIntent = new Intent(getApplicationContext(), displayRoutesList.class);
                startIntent.putExtra("startLocation", startLocation);
                startIntent.putExtra("destinationLocation", destinationLocation);
                startActivity(startIntent);
            }
        });

        destinationCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLocation = "Central";
                Intent startIntent = new Intent(getApplicationContext(), displayRoutesList.class);
                startIntent.putExtra("startLocation", startLocation);
                startIntent.putExtra("destinationLocation", destinationLocation);
                startActivity(startIntent);
            }
        });

        destinationMacq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLocation = "Macq";
                Intent startIntent = new Intent(getApplicationContext(), displayRoutesList.class);
                startIntent.putExtra("startLocation", startLocation);
                startIntent.putExtra("destinationLocation", destinationLocation);
                startActivity(startIntent);
            }
        });

        destinationTownHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLocation = "TownHall";
                Intent startIntent = new Intent(getApplicationContext(), displayRoutesList.class);
                startIntent.putExtra("startLocation", startLocation);
                startIntent.putExtra("destinationLocation", destinationLocation);
                startActivity(startIntent);
            }
        });

        destinationUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationLocation = "Uni";
                Intent startIntent = new Intent(getApplicationContext(), displayRoutesList.class);
                startIntent.putExtra("startLocation", startLocation);
                startIntent.putExtra("destinationLocation", destinationLocation);
                startActivity(startIntent);
            }
        });


        if (startTxt.equals("Home")) {
            destinationHome.setVisibility(View.INVISIBLE);
        } else if (startTxt.equals("Macq")) {
            destinationCentral.setVisibility(View.INVISIBLE);
            destinationMacq.setVisibility(View.INVISIBLE);
            destinationTownHall.setVisibility(View.INVISIBLE);
            destinationUni.setVisibility(View.INVISIBLE);
        } else if (startTxt.equals("TownHall")) {
            destinationCentral.setVisibility(View.INVISIBLE);
            destinationMacq.setVisibility(View.INVISIBLE);
            destinationTownHall.setVisibility(View.INVISIBLE);

        }


    }


}


