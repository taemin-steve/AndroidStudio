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
    MyItem myItem = new MyItem();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String rink = intent.getStringExtra("rink");
        for(int i=0; i <= 29; i++){
            if(title.equals(myItem.titles[i])){
                index = i;
                break;
            }
        }

        ImageView imageView = (ImageView) findViewById(R.id.popUpImage);
        imageView.setImageResource(myItem.images[index]);

        TextView titleView = (TextView) findViewById(R.id.popUpTitle);
        titleView.setText(title);

        TextView rinkView = (TextView) findViewById(R.id.popUpRink);
        rinkView.setText(rink);
        rinkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(rink));
                startActivity(intent);
                Toast.makeText(getBaseContext(), "해당 페이지로 이동합니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
