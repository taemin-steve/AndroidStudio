package com.example.gustomap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PopUpActivity extends Activity {
    RestaurantList restaurantList = new RestaurantList();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        //이전에 받아온 정보들을 저장.
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String rink = intent.getStringExtra("rink");
        for(int i=0; i <= 29; i++){ //이미지에 대한 정보를 for 문을 이용하여 검색하고, 해당 index 를 변수에 전달.
            if(title.equals(restaurantList.titles[i])){
                index = i;
                break;
            }
        }

        TextView titleView = (TextView) findViewById(R.id.popUpTitle);
        titleView.setText(title); // popUpTitle 을 클릭된 마커 음식점의 이름으로 설정

        ImageView imageView = (ImageView) findViewById(R.id.popUpImage);
        imageView.setImageResource(restaurantList.images[index]); // popUpImage 를 해당 음식점의 사진으로 설정

        TextView rinkView = (TextView) findViewById(R.id.popUpRink);
        rinkView.setText(rink); // popUpRink 를 해당 음식점의 링크로 설정
        rinkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 링크 클릭시 해당 페이지로 이동.
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(rink));
                startActivity(intent);
                Toast.makeText(getBaseContext(), "해당 페이지로 이동합니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
