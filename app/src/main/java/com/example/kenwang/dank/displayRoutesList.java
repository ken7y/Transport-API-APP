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
import java.util.Date;
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
        listViewId = (ListView) findViewById(R.id.listOfTimes);

        adapter = new ArrayAdapter(displayRoutesList.this, android.R.layout.simple_list_item_1, lis5t);
        listViewId.setAdapter(adapter);
        Log.d("super dnak memes", "count");

        TextView textBox = (TextView) findViewById(R.id.startText);
        String startText = getIntent().getExtras().getString("startLocation");
        textBox.setText(startText);


        TextView endText = (TextView) findViewById(R.id.goalText);
        String goalText = getIntent().getExtras().getString("destinationLocation");
        endText.setText(goalText);

        final List<GtfsRealtime.FeedEntity> citylist = new ArrayList<>();
        final List<GtfsRealtime.FeedEntity> macqlist = new ArrayList<>();
        final List<GtfsRealtime.FeedEntity> fakelist = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                //.url("https://api.transport.nsw.gov.au/v1/gtfs/vehiclepos/buses")
                .url("https://api.transport.nsw.gov.au/v1/gtfs/realtime/buses")

                .header("Authorization", "apikey Qc9idalrWCIhYSKgNA0AVDFYXFOuaStWG66W")
                .build();

        Response response = null;

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // do something wih the result
                    GtfsRealtime.FeedMessage newMessage = GtfsRealtime.FeedMessage.parseFrom(response.body().byteStream());
                    int counting = 0;
                    int total = 0;

                    for (final GtfsRealtime.FeedEntity testers : newMessage.getEntityList()) {
                        String mainid = testers.getId();
                        total++;
                        String[] idafter = mainid.split("_");

                        int indexOfLastStop = testers.getTripUpdate().getStopTimeUpdateList().size();

                        counting++;

                        if (testers.getTripUpdate().getStopTimeUpdate(indexOfLastStop - 1).getStopId().equals("200059")) {
                            for (GtfsRealtime.TripUpdate.StopTimeUpdate temp : testers.getTripUpdate().getStopTimeUpdateList()) {
                                if (temp.getStopId().equals("211220")) {
                                    citylist.add(testers);
                                    Log.d("koolkids", "count");

                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            long datee = testers.getTripUpdate().getStopTimeUpdate(41).getDeparture().getTime();
                                            Date d = new Date(testers.getTripUpdate().getStopTimeUpdate(41).getDeparture().getTime()*1000);
                                            lis5t.add(d);

                                            adapter.notifyDataSetChanged();                                        }
                                    });

                                }
                            }

                        } else if (testers.getTripUpdate().getStopTimeUpdate(indexOfLastStop - 1).getStopId().equals("211316")) {
                            macqlist.add(testers);
                        } else {
                            fakelist.add(testers);

                        }



                        /**
                         * if the last id is 200059 then its going city direction
                         * if the last id is 211316 or 2113232 then its going macq uni direction
                         */

                    }
/**
 * can sometimes get two identical responses but one with delay and one without.
 * Delay is in seconds
 *
 */

                    //Log.d("dank", response.body().bytes().length+"");
                }
            }

        });


        lis5t.add("testing");
        lis5t.add("testing");
        lis5t.add("testing");
        lis5t.add("testing");
        Log.d("testing", "testing");


    }
}
