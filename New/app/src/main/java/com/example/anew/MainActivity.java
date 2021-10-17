package com.example.anew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int result = 0; // 정답을 저장. 익명구현개체에서도 사용할 수 있게 정적 변수 생성
    static double correct = 0; // 정답인 횟수 저장
    static double wrong = 0; // 오답인 횟수 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 변수 선언. 익명구현 객체 내에서 쓰이기 위해서 final 로 선언.
        final TextView eText1;
        final EditText eText2;
        final TextView eText3;
        Button btn1;
        Button btn2;
        Button btn3;

        eText1 = (TextView) findViewById(R.id.eText1);
        eText2 = (EditText) findViewById(R.id.eText2);
        eText3 = (TextView) findViewById(R.id.eText3);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        //시작하자 마자 수식이 제공되게끔 한번 지정해준다. 이후 이벤트발생시 매번 초기화.
        Random ran = new Random();
        boolean op = ran.nextBoolean();
        int num1 = ran.nextInt(99)+1;
        int num2 = ran.nextInt(99)+1;
        String operator = (op) ? "+" : "-";
        result = (op) ? (num1 + num2) :(num1 - num2); // True 면 +, False -로 지정
        eText1.setText(num1 + operator + num2); // 수식란에 띄움.


        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = eText2.getText().toString(); // 답란에 적힌 문자를 받아옴
                if(str.isEmpty()){ // 아무것도 입력하지 않은 상태라면 답을 입력하라는 토스트 메세지 전송
                    Toast.makeText(getApplicationContext(),"답을 입력하세요",Toast.LENGTH_SHORT).show();
                    return ;
                }
                int input = Integer.parseInt(str); // 문자를 숫자로 변환
                if(input == result) { // 정답이라면 맞았다는 문구와 함게 correct++
                    Toast.makeText(getApplicationContext(),"맞았습니다.",Toast.LENGTH_SHORT).show();
                    correct++;
                }
                else { //틀렸다면, 틀렸다는 문구와, 정답을 적고 wrong++
                    Toast.makeText(getApplicationContext(),"틀렸습니다. 정답은" + result,Toast.LENGTH_SHORT).show();
                    wrong++;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //수식 초기화, 위에서와 동일
                Random ran = new Random();
                boolean op = ran.nextBoolean();
                int num1 = ran.nextInt(99)+1;
                int num2 = ran.nextInt(99)+1;
                String operator = (op) ? "+" : "-";

                eText1.setText(num1 + operator + num2);
                result = (op) ? (num1 + num2) :(num1 - num2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double percent = Math.round((correct / (wrong + correct))*10000)/100.0; //Math.round 를 이용하여 소수점 아래 2까지 표현.
                eText3.setText((int) (wrong + correct) + "중 " + (int) correct + "번 정답, 정답률:" + percent + "%");
            }
        });

    }

}