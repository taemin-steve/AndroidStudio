package com.example.gustomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;


public class MainActivity extends AppCompatActivity {

    Button region;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        region = (Button) findViewById(R.id.region);
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
                                region.setText("연남");
                                break;
                             case R.id.이태원:
                                region.setText("이태원");
                                break;
                             case R.id.익선:
                                region.setText("익선");
                                break;
                        }
                        return false;
                    }
                }
        );
        popup.show();
    }
}