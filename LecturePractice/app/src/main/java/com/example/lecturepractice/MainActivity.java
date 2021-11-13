package com.example.lecturepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("액티비티 테스트 예제");
        Log.i("액티비티 테스트", "onCreate()");

        Button btnDial = (Button) findViewById(R.id.btnDial);
        btnDial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:010-1234-5678");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        Button btnFinish = (Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("액티비티 테스트", "onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("액티비티 테스트", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("액티비티 테스트", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("액티비티 테스트", "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("액티비티 테스트", "onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("액티비티 테스트", "onDestroy()");
    }

}
