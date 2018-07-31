package com.example.kenwang.dank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.transit.realtime.GtfsRealtime;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class displayRoutesList extends AppCompatActivity {
    ListView listViewId;
    List lis5t = new ArrayList();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_routes_list);


        TextView textBox = (TextView) findViewById(R.id.startText);
        String startText = getIntent().getExtras().getString("startLocation");
        textBox.setText(startText);


        TextView endText = (TextView) findViewById(R.id.goalText);
        String goalText = getIntent().getExtras().getString("destinationLocation");
        endText.setText(goalText);



        listViewId = (ListView) findViewById(R.id.listOfTimes);

        lis5t.add("testing");
        lis5t.add("testing");
        lis5t.add("testing");
        lis5t.add("testing");

        adapter = new ArrayAdapter(displayRoutesList.this, android.R.layout.simple_list_item_1, lis5t);
        listViewId.setAdapter(adapter);
        Log.d("super dnak memes", "count");

    }
}
