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


    Button region;
    private GoogleMap mMap;
    double myLat;
    double myLon;
    ListView list;

    String[] titles = new String[]{};
    Integer[] images = new Integer[]{};
    String[] rinks = new String[]{};
    MyItem item = new MyItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        region = (Button) findViewById(R.id.region);

        makeNewAdapter(0,30);

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
                                region.setText("연남/홍대");
                                makeNewAdapter(0,10);
                                break;
                             case R.id.신사:
                                region.setText("신사/압구정");
                                makeNewAdapter(10,6);
                                break;
                            case R.id.강남:
                                region.setText("강남/논현/잠실");
                                makeNewAdapter(16,6);
                                break;
                             case R.id.익선:
                                region.setText("익선/을지로");
                                 makeNewAdapter(22,8);
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

    public void makeNewAdapter(int startIndex, int num){

        titles = new String[num];
        for(int i = startIndex; i <= startIndex + num - 1;i++) titles[i - startIndex] = item.titles[i];
        images = new Integer[num];
        for(int i = startIndex; i <= startIndex + num - 1;i++) images[i - startIndex] = item.images[i];
        rinks = new String[num];
        for(int i = startIndex; i <= startIndex + num - 1;i++) rinks[i - startIndex] = item.rinks[i];

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

}