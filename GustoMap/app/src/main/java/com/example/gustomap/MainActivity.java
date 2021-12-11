package com.example.gustomap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button region; //맛집 지역 설정
    ListView list; // 어뎁터 뷰

    RestaurantList restaurantList = new RestaurantList(); // 식당 정보가 저장되어 있는 객체를 생성

    //adapter view 에 전달할 정보를 해당 변수들에 저장
    String[] titles = new String[]{}; //식당 이름
    Integer[] images = new Integer[]{};// 식당 이미지
    String[] rinks = new String[]{};// 식당 링크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        region = (Button) findViewById(R.id.region);

        makeNewListView(0,30); // 리스트 뷰를 생성해주는 사용자 정의 함수
    }

    // 팝업 메뉴중 클릭된 지역에 맞는 리스트 뷰를 생성. (region 버튼의 콜백함수)
    public void selectRegion(View button) {
        PopupMenu popup = new PopupMenu(this, button);// 팝업 메뉴 생성
        popup.getMenuInflater().inflate(R.menu.mymenu, popup.getMenu());

        popup.setOnMenuItemClickListener( // 팝업 메뉴 클릭시 작동 설정
                new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId())
                        {
                             case R.id.연남:
                                region.setText("연남/홍대");
                                makeNewListView(0,10); // 새로운 리스트뷰 생성
                                break;
                             case R.id.신사:
                                region.setText("신사/압구정");
                                makeNewListView(10,6);
                                break;
                            case R.id.강남:
                                region.setText("강남/논현/잠실");
                                makeNewListView(16,7);
                                break;
                             case R.id.익선:
                                region.setText("익선/을지로");
                                 makeNewListView(23,8);
                                break;
                        }
                        return false;
                    }
                }
        );
        popup.show();
    }

    // 지도가 나오는 액티비티로 전환해주는 함수. (지도 아이콘의 콜백함수)
    public void changeToMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    // 커스텀 어뎁터 뷰를 위해 생성한 클래스
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
            Intent intent = new Intent(Intent.ACTION_VIEW);

            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rink = (TextView) rowView.findViewById(R.id.rink);
            rink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//어뎁터 뷰의 링크가 눌리면 해당 링크로 이동
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

    // 새로운 ListView 생성해주는 메서드
    public void makeNewListView(int startIndex, int num){
        // 각 지역마다 정해진 시작 index 와 개수가 존재. RestaurantList 객체에서 원하는 정보만 받아와서 변수에 전달 후 ListView 재생성
        titles = new String[num];
        for(int i = startIndex; i <= startIndex + num - 1;i++) titles[i - startIndex] = restaurantList.titles[i];
        images = new Integer[num];
        for(int i = startIndex; i <= startIndex + num - 1;i++) images[i - startIndex] = restaurantList.images[i];
        rinks = new String[num];
        for(int i = startIndex; i <= startIndex + num - 1;i++) rinks[i - startIndex] = restaurantList.rinks[i];

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    // 안드로이드 back 버튼 막기
    @Override
    public void onBackPressed() {
        // 가장 먼저 시작되는 activity 는 별다른 기능이 없이 그저 일정 시간이후에 main activity 를 호출한다
        // 따라서 뒤로가기를 눌러 해당 페이지로 돌아가게 되면 더이상 진행 할 수 없으므로, back 버튼을 막아서 비정상 작동을 방지.
        return;
    }

}