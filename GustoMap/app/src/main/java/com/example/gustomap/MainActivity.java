package com.example.gustomap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.FragmentManager;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    Button region;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.map);
        region = (Button) findViewById(R.id.region);

//        fragmentManager = getFragmentManager();
//        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googleMap);
//        mapFragment.getMapAsync(this);

    }


    public void selectRegion(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.mymenu, popup.getMenu());

        popup.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId())
                        {
                             case R.id.연남:
                                region.setText("연남");
                                break;
                             case R.id.이태원:
                                region.setText("이태원");
                                break;
                             case R.id.익선:
                                region.setText("익선");
                                break;
                        }
                        return false;
                    }
                }
        );
        popup.show();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) { //마커를 꼽는 것.
//        LatLng location = new LatLng(37.49128301811198, 126.9188174758624);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.title("보라매 공원");
//        markerOptions.snippet("공원");
//        markerOptions.position(location);
//        googleMap.addMarker(markerOptions);
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}