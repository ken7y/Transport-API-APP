package com.example.kenwang.dank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayTransportList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transport_list);



        TextView textBox =(TextView) findViewById(R.id.LabelTest);
        String startTxt = getIntent().getExtras().getString("startLocation");
        textBox.setText(startTxt);

        Button destinationHome = (Button) findViewById(R.id.destinationHome);
        Button destinationUni = (Button) findViewById(R.id.destinationUni);
        Button destinationMacq = (Button) findViewById(R.id.destinationMacq);
        Button destinationTownHall = (Button) findViewById(R.id.destinationTownHall);
        Button destinationCentral = (Button) findViewById(R.id.destinationCentral);



        if(startTxt.equals("Home")){
            destinationHome.setVisibility(View.INVISIBLE);
        }else if(startTxt.equals("Macq")){
            destinationCentral.setVisibility(View.INVISIBLE);
            destinationMacq.setVisibility(View.INVISIBLE);
            destinationTownHall.setVisibility(View.INVISIBLE);
            destinationUni.setVisibility(View.INVISIBLE);
        }else if(startTxt.equals("TownHall")){
            destinationCentral.setVisibility(View.INVISIBLE);
            destinationMacq.setVisibility(View.INVISIBLE);
            destinationTownHall.setVisibility(View.INVISIBLE);

        }


    }


}
