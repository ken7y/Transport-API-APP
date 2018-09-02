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
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class displayRoutesList extends AppCompatActivity {
    ListView listViewId;
    List lis5t = new ArrayList();
    List listTemp = new ArrayList();
    Hashtable<String,String> hash4dest =new Hashtable<String,String>();


    ArrayAdapter adapter;

    String startStop;
    String destinationStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_routes_list);
        listViewId = (ListView) findViewById(R.id.listOfTimes);


        hash4dest.put("HomeTownHall","211220:200059");
        hash4dest.put("HomeMacq","211253:211333");
        hash4dest.put("HomeCentral","211220:2000428");
        hash4dest.put("TownHallUni","200073:200071");






        adapter = new ArrayAdapter(displayRoutesList.this, android.R.layout.simple_list_item_1, lis5t);
        listViewId.setAdapter(adapter);
        Log.d("super dnak memes", "count");

        TextView textBox = (TextView) findViewById(R.id.startText);
        final String startText = getIntent().getExtras().getString("startLocation");
        textBox.setText(startText);


        TextView endText = (TextView) findViewById(R.id.goalText);
        final String goalText = getIntent().getExtras().getString("destinationLocation");
        endText.setText(goalText);

        final List<GtfsRealtime.FeedEntity> citylist = new ArrayList<>();


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
                        final String[] idafter = mainid.split("_");

                        int indexOfLastStop = testers.getTripUpdate().getStopTimeUpdateList().size();

                        counting++;

                        String startVAR = "";
                        String endVAR = "";

                        switch (startText) {
                            case "Home":
                                String newString = "Home".concat(goalText);
                                String newRegex = hash4dest.get(newString);
                                String[] stopIDS = newRegex.split(":");
                                startVAR = stopIDS[0];
                                endVAR = stopIDS[1];
                                break;

                            case "TownHall":
                                newString = "TownHall".concat(goalText);
                                newRegex = hash4dest.get(newString);
                                stopIDS = newRegex.split(":");
                                startVAR = stopIDS[0];
                                endVAR = stopIDS[1];
                                break;

                            case "Macq":
                                newString = "Macq".concat(goalText);
                                newRegex = hash4dest.get(newString);
                                stopIDS = newRegex.split(":");
                                startVAR = stopIDS[0];
                                endVAR = stopIDS[1];
                                break;

                            case "Central":
                                newString = "Central".concat(goalText);
                                newRegex = hash4dest.get(newString);
                                stopIDS = newRegex.split(":");
                                startVAR = stopIDS[0];
                                endVAR = stopIDS[1];
                                break;
                        }
/**
 * read the code but basically I only do a check on last stop and this is bad because buses to uni have multiple last stops
 * So basically the issue is
 * 1) Need to be able to filter routes without checking last stop and hopefully
 * without the need to loop through every route and every stop. So either
 * filter out by route name or something.
 * 2) code is kind of messy
 * 3) Going to add in extra feature of showing bus live location so I don't get cucked by memes.
 */
                    if(indexOfLastStop >0){
                        if (testers.getTripUpdate().getStopTimeUpdate(indexOfLastStop - 1).getStopId().equals(endVAR)) {
                            int i = 0;
                            for (GtfsRealtime.TripUpdate.StopTimeUpdate temp : testers.getTripUpdate().getStopTimeUpdateList()) {
                                if (temp.getStopId().equals(startVAR)) {
                                    citylist.add(testers);
                                    Log.d("koolkids", "count");

                                    final int finalI = i;
                                    runOnUiThread(new Runnable() {
                                        public void run() {


                                            long datee = testers.getTripUpdate().getStopTimeUpdate(finalI).getArrival().getTime() ;

                                            Date d = new Date(datee * 1000);
                                            /**
                                             lis5t.add(d + idafter[3]);
                                             **/


                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    lis5t.clear();
                                                }
                                            });

                                            listTemp.add(d + idafter[0]+idafter[1]+idafter[2]+idafter[3]);
                                            /**
                                             *                                            Collections.sort(listTemp);

                                             */
                                            Collections.sort(listTemp);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    for (Object item : listTemp) {
                                                        lis5t.add(item);
                                                    }
                                                    adapter.notifyDataSetChanged();
                                                }
                                            });


                                        }
                                    });

                                }
                                i++;
                            }

                        }
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
        Log.d("testing", "testing");
        Log.d("test2ing", "testing");


    }


}
