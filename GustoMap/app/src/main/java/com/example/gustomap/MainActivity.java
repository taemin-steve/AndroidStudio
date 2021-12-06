package com.example.gustomap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    ListView list;
    String[] titles = {
            "리리코",
            "Citizen Kane (1941)",
    } ;
    Integer[] images = {
            R.drawable.ririco,
            R.drawable.movie2,
    };
    String[] rinks = {
            "https://www.instagram.com/p/CI5lInDFK69/",
            "Citizen Kane (1941)",
    } ;

    Button region;
    private GoogleMap mMap;
    double myLat;
    double myLon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        region = (Button) findViewById(R.id.region);

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), titles[+position],Toast.LENGTH_SHORT).show();
            }
        });

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
                                YN();
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


    public void changeToMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("lat", myLat);
        intent.putExtra("lon", myLon);
        //Toast.makeText(MainActivity.this, "현재위치 \n위도 " + myLat + "\n경도 " + myLon, Toast.LENGTH_LONG).show();

        startActivity(intent);
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context){
            super(context,R.layout.listitem,titles);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem,null,true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rink = (TextView) rowView.findViewById(R.id.rink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            rink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.setData(Uri.parse(rinks[position]));
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "해당 페이지로 이동합니다",Toast.LENGTH_SHORT).show();
                }
            });

            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            rink.setText(rinks[position]);
            return rowView;
        }

    }
    
    public void YN(){
        titles = new String[]{
                "리리코오",
                "Citizen Kane (1941)",
        };

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

    }


}