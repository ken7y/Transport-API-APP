package com.example.kenwang.dank;

import android.content.Entity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.transit.realtime.GtfsRealtime;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click = (Button) findViewById(R.id.button);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                HttpURLConnection urlConnection = null;
//                try {
//                    URL url = new URL("https://api.transport.nsw.gov.au/v1/gtfs/vehiclepos/buses");
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setRequestProperty ("Authorization", "apikey Qc9idalrWCIhYSKgNA0AVDFYXFOuaStWG66W");
//
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//
//                    GtfsRealtime.FeedMessage newMessage = GtfsRealtime.FeedMessage.parseFrom(in);
//                    Log.d("blub", newMessage.toString());
//
//
//                    //readStream(in);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    urlConnection.disconnect();
//                }

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
                            Log.d("dank", newMessage.toString());

                            //.d("dank", response.body().bytes().length+"");
                        }
                    }
                });
            }
        });
    }


}
