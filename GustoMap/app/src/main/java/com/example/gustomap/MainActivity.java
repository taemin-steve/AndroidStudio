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
                                region.setText("연남/홍대");
                                YN();
                                break;
                             case R.id.신사:
                                region.setText("신사/압구정");
                                SS();
                                break;
                            case R.id.강남:
                                region.setText("강남/논현/잠실");
                                GN();
                                break;
                             case R.id.익선:
                                region.setText("익선/을지로");
                                YS();
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
                "리리코",
                "유니의 우아한식탁 ",
                "버바나",
                "감칠",
                "와이탑",
                "올더플레이트",
                "심원",
                "미담진족",
                "더 피자 보이즈",
                "홍정기 참치"
        };
        images = new Integer[]{
                R.drawable.ririco,
                R.drawable.uni,
                R.drawable.burbana,
                R.drawable.gamchil,
                R.drawable.whytop,
                R.drawable.alltheplate,
                R.drawable.simone,
                R.drawable.midam,
                R.drawable.thepizzaboys,
                R.drawable.hong,
        };
        rinks = new String[]{
                "https://www.instagram.com/p/CI5lInDFK69/",
                "https://www.instagram.com/p/CLy4jF1FT4g/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/COW-ST7lnuH/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CLvV0iTlkXt/?utm_medium=copy_link",
                "https://www.instagram.com/p/CVcoR9HFg6H/?utm_medium=copy_link",
                "https://www.instagram.com/p/CNt_c7Pligy/?utm_medium=copy_link",
                "https://www.instagram.com/p/CMjMffpFLj4/?utm_medium=copy_link",
                "https://www.instagram.com/p/CWabS3YFjy8/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CKtXURPFYdd/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CRgJJqgliGT/?utm_medium=copy_link"
        } ;

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    public void SS(){
        titles = new String[]{
                "골뱅이신사",
                "우니도",
                "신사 치킨 클럽",
                "신사의 바램",
                "콘피에르",
                "무탄",

        };
        images = new Integer[]{
                R.drawable.goal,
                R.drawable.unido,
                R.drawable.sinsaclub,
                R.drawable.barlamb,
                R.drawable.confere,
                R.drawable.jokbal,
                R.drawable.mutan,

        };
        rinks = new String[]{
                "https://www.instagram.com/p/CVzKI0QFrrG/?utm_medium=copy_link",
                "https://www.instagram.com/p/CQsNY-EFZa7/?utm_medium=copy_link",
                "https://www.instagram.com/p/CSD8aqNFYdR/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CSgAYyhFDsX/?utm_medium=copy_link",
                "https://www.instagram.com/p/CW2MiAAlYnU/?utm_medium=copy_link",
                "https://www.instagram.com/p/CQkk6F4FLIA/?utm_medium=copy_link",
                "https://www.instagram.com/p/CTEi769F_g1/?utm_medium=copy_link",

        } ;

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    public void GN(){
        titles = new String[]{
                "스시롭다",
                "포크댄스",
                "베트남이랑",
                "조양관",
                "스카이가든",
                "르띠"

        };
        images = new Integer[]{
                R.drawable.susi,
                R.drawable.porkdance,
                R.drawable.batnam,
                R.drawable.joyang,
                R.drawable.skygarden,
                R.drawable.letti,

        };
        rinks = new String[]{
                "https://www.instagram.com/p/CQfOEHoFOem/?utm_medium=copy_link",
                "https://www.instagram.com/p/CU9W9JBlgLe/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CL-3t79lpWZ/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/COeqlzAlrhO/?utm_medium=copy_link",
                "https://www.instagram.com/p/CPVAZoMlyvJ/?utm_medium=copy_link",
                "https://www.instagram.com/p/CPuYqv_loVQ/?utm_medium=copy_link"

        } ;

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    public void YS(){
        titles = new String[]{
                "경양카츠",
                "워터밀",
                "저스트텐동",
                "실낙원",
                "기러기 둥지",
                "송암여관",
                "을지깐깐",
                "을지다락",



        };
        images = new Integer[]{
                R.drawable.kungyang,
                R.drawable.watermeal,
                R.drawable.justtd,
                R.drawable.narkone,
                R.drawable.kirukinest,
                R.drawable.songarm,
                R.drawable.kankan,
                R.drawable.darrock,

        };
        rinks = new String[]{
                "https://www.instagram.com/bbang_byeol/p/CUHsGJRlmF7/?utm_medium=copy_link",
                "https://www.instagram.com/p/CO7fEpjFGRG/?utm_medium=copy_link",
                "https://www.instagram.com/p/CNmv_3sF135/?utm_medium=copy_link",
                "https://www.instagram.com/p/CM3hQUYlixR/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CJQi1MZFDs1/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CO2IwsgFTur/?utm_medium=copy_link",
                "https://www.instagram.com/p/CSL-aywljWz/?utm_medium=copy_link",
                "https://www.instagram.com/bbang_byeol/p/CTMaOkwFiaj/?utm_medium=copy_link",


        } ;

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    ListView list;
    String[] titles = {
            "리리코",
            "골뱅이신사",
            "경양카츠",
            "스시롭다",
            "유니의 우아한식탁 ",
            "버바나",
            "감칠",
            "와이탑",
            "올더플레이트",
            "심원",
            "미담진족",
            "더 피자 보이즈",
            "홍정기 참치"
    } ;
    Integer[] images = {
            R.drawable.ririco,
            R.drawable.goal,
            R.drawable.kungyang,
            R.drawable.susi,
            R.drawable.uni,
            R.drawable.burbana,
            R.drawable.gamchil,
            R.drawable.whytop,
            R.drawable.alltheplate,
            R.drawable.simone,
            R.drawable.midam,
            R.drawable.thepizzaboys,
            R.drawable.hong,
    };
    String[] rinks = {
            "https://www.instagram.com/p/CI5lInDFK69/",
            "https://www.instagram.com/p/CVzKI0QFrrG/?utm_medium=copy_link",
            "https://www.instagram.com/bbang_byeol/p/CUHsGJRlmF7/?utm_medium=copy_link",
            "https://www.instagram.com/p/CQfOEHoFOem/?utm_medium=copy_link",
            "https://www.instagram.com/p/CI5lInDFK69/",
            "https://www.instagram.com/p/CLy4jF1FT4g/?utm_medium=copy_link",
            "https://www.instagram.com/bbang_byeol/p/COW-ST7lnuH/?utm_medium=copy_link",
            "https://www.instagram.com/bbang_byeol/p/CLvV0iTlkXt/?utm_medium=copy_link",
            "https://www.instagram.com/p/CVcoR9HFg6H/?utm_medium=copy_link",
            "https://www.instagram.com/p/CNt_c7Pligy/?utm_medium=copy_link",
            "https://www.instagram.com/p/CMjMffpFLj4/?utm_medium=copy_link",
            "https://www.instagram.com/p/CWabS3YFjy8/?utm_medium=copy_link",
            "https://www.instagram.com/bbang_byeol/p/CKtXURPFYdd/?utm_medium=copy_link",
            "https://www.instagram.com/bbang_byeol/p/CRgJJqgliGT/?utm_medium=copy_link"
    } ;

}