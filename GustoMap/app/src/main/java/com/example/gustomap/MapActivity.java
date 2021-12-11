package com.example.gustomap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GpsTracker gpsTracker; // GPS 현재 위치 수신을 위한 서비스 클래스

    // GPS 수신을 위한 변수들
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    //지도, 지도위의 마커를 위한 변수들
    private GoogleMap mMap; // 지도 객체
    MarkerOptions positionMarker = new MarkerOptions(); // 사용자의 현재 위치를 나타내기 위한 MarkerOptions 객체
    Marker currentMarker; // 사용자의 현재 위치를 알려주는 marker 객체

    // 식당 정보를 담고 있는 객체
    RestaurantList restaurantList = new RestaurantList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        // 지도 생성
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // GPS 권한확인
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }else {
            checkRunTimePermission();
        }

        // point 버튼이 눌리면 사용자의 현재위치 정보를 받아오도록 설정
        ImageButton ShowLocationButton = (ImageButton) findViewById(R.id.point);
        ShowLocationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                currentMarker.remove(); // 기존에 사용자의 위치를 나타내는 마커 제거

                gpsTracker = new GpsTracker(MapActivity.this); // GPS 서비스 객체 생성
                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();
                LatLng myLatLng = new LatLng(latitude, longitude); // 현재 위치 정보 저장

                positionMarker.position(myLatLng);
                currentMarker = mMap.addMarker(positionMarker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 15));// 해당 지점을 map 중앙에 배치

                Toast.makeText(MapActivity.this, "현재위치 \n위도 " + latitude + "\n경도 " + longitude, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        MarkerOptions markerOptions = new MarkerOptions(); // 지도에 모든 식당에 해당하는 마커 생성
        Bitmap RestaurantCursor = BitmapFactory.decodeResource(getResources(), R.drawable.restaurant);// 이미지를 bitmap 으로 변환
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(RestaurantCursor));
        for( int i = 0; i <= 30; i++ ){
            markerOptions.position(restaurantList.postion[i]);// 마커 위도 경도 설정
            markerOptions.title(restaurantList.titles[i]);// 마커의 이름 설정
            markerOptions.snippet(restaurantList.rinks[i]);// 마커에 해당하는 음식점의 링크를 제공
            mMap.addMarker(markerOptions);
            // 해당 부분에서 설정한 것은 지도에서는 나타나지 않는다. (클릭하면 나타나는 것인데, onMarkerClick 을 오버라이딩 하였음)
            // 단 클릭 하였을때 해당 정보들을 이용하여 다른 액티비티에 정보 전달.
        }

        //현재위치를 이용해서 마커 생성
        gpsTracker = new GpsTracker(MapActivity.this);
        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();
        LatLng myPosition = new LatLng(latitude,longitude);
        positionMarker.title("현재위치");
        positionMarker.position(myPosition);

        //마커를 다른 모양의 마커로 변경
        Bitmap cursor = BitmapFactory.decodeResource(getResources(), R.drawable.point);// 이미지를 bitmap 으로 변환
        positionMarker.icon(BitmapDescriptorFactory.fromBitmap(cursor));

        currentMarker = mMap.addMarker(positionMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
        mMap.setOnMarkerClickListener(this);
    }

    // 뒤로가기 버튼의 콜백 함수. 해당 액티비티를 종료 시킨다.
    public void returnToMain(View view) {
        finish();
    }

    // 마커가 클릭되었을 때 해당 음식점의 세부 정보를 팝업창 형태의 액티비티를 실행시키는 함수.
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        if(marker.getTitle().equals("현재위치")) return true; // 사용자의 현재 위치를 나타내는 커서 클릭에 대한 예외 처리

        Intent intent = new Intent(this, PopUpActivity.class);
        intent.putExtra("title", marker.getTitle());// 이름 정보를 새로운 액티비티에 제공
        intent.putExtra("rink", marker.getSnippet());// rink 정보를 새로운 액티비티에 제공

        startActivity(intent);
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 아래부터는 GPS를 사용하기 위한 코드

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;
            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if (check_result) {
                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(MapActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(MapActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)
            // 3.  위치 값을 가져올 수 있음

        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MapActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MapActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);

            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MapActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
