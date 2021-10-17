package com.example.custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText = (EditText) findViewById(R.id.eText);

        eText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
                if (arg2.getAction() == KeyEvent.ACTION_UP) {
                    Toast.makeText(getApplicationContext(),
                            eText.getText().toString(),Toast.LENGTH_SHORT).show();
                }
                return false;
            }

        });
    }



}