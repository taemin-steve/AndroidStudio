package com.example.lecturepractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    MainHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        handler = new MainHandler();
    }

    class BackgroundThread extends Thread {
        int value = 0;
        boolean running = true;
        Thread w = new Thread(new Runnable(){
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {}

                    value += 1;
                    Log.d("Thread", "value : " + value);
/*
                Message message = handler.obtainMessage(); // 메시지 객체 반환
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);// 키-값 쌍으로 정보 넣기
                message.setData(bundle); //메시지 객체에 데이터 넣기

                handler.sendMessage(message); // 메시지 큐로 전송
*/

                }
            }
        })
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) { // 메시지 큐에 있는 메시지 핸들 메소드
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value"); //전달받은 메시지 객체 중에 키가 "value" 인 데이터 처리
            textView.setText("value 값 : " + value);
        }
    }

}