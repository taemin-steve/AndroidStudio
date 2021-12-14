package com.example.gustomap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roding_page);


        // 1.75초가 지난후 다이얼로그 생성, ok 버튼을 누르면 Main Activity로 진행
        new Handler().postDelayed(new Runnable() { @Override public void run() {

            AlertDialog.Builder builder = new AlertDialog.Builder(RoadingActivity.this);
            builder.setTitle("GPS를 키고 사용해 주세요!").setMessage("");
            builder.setCancelable(false);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                    Intent intent = new Intent(RoadingActivity.this, MainActivity.class); startActivity(intent);
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        }, 1750);
    }
}
