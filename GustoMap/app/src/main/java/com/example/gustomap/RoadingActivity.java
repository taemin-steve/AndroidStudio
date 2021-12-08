package com.example.gustomap;

import android.app.Activity;
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

        new Handler().postDelayed(new Runnable() { @Override public void run() {
            Intent intent = new Intent(RoadingActivity.this, MainActivity.class); startActivity(intent); }
            }, 3000);
    }
}
