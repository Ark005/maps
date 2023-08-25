package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<WeightedLatLng> data = null;
        try {
            data = generateHeatMapData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        HeatmapTileProvider heatMapProvider = new HeatmapTileProvider.Builder()
                .weightedData(data) // load our weighted data
                .radius(50) // optional, in pixels, can be anything between 20 and 50
                .maxIntensity(1000.0) // set the maximum intensity
                .build();

        googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(heatMapProvider));

        LatLng indiaLatLng = new LatLng(20.5937, 78.9629);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(indiaLatLng, 5f));
    }

    private ArrayList<WeightedLatLng> generateHeatMapData() throws JSONException {
        ArrayList<WeightedLatLng> data = new ArrayList<>();

        JSONArray jsonData = getJsonDataFromAsset();
        if (jsonData != null) {
            try {
                for (int i = 0; i < jsonData.length(); i++) {
                    JSONObject entry = jsonData.getJSONObject(i);
                    double lat = entry.getDouble("lat");
                    double lon = entry.getDouble("lon");
                    double density = entry.getDouble("density");

                    if (density != 0.0) {
                        WeightedLatLng weightedLatLng = new WeightedLatLng(new LatLng(lat, lon), density);
                        data.add(weightedLatLng);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("Data", data.get(0).toString());
        return data;
    }

    private JSONArray getJsonDataFromAsset() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.coordinates);
        String json = new Scanner(inputStream).useDelimiter("\\A").next();
        JSONArray array = new JSONArray(json);
        return array;
    }
}


