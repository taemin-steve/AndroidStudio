package com.example.event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener checkListener = new View.OnClickListener(){
        public void onClick(View view){
            Boolean checked = ((CheckBox) view).isChecked();
            switch (view.getId()) {
                case R.id.check1:{
                    if (checked){
                    Toast.makeText(getApplicationContext(),"고기선택",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"고기 선택 해제",Toast.LENGTH_SHORT).show();
                }
                }
                case R.id.check2:{
                    if(checked){
                    Toast.makeText(getApplicationContext(),"치즈 선택",Toast.LENGTH_SHORT).show();
                }else{
                 Toast.makeText(getApplicationContext(),"치즈 선택 해제",Toast.LENGTH_SHORT).show();
                }
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
            Toast.makeText(getApplicationContext(),"버튼이 눌러졌습니다",Toast.LENGTH_LONG).show();
            }
        });

        final EditText editText = (EditText) findViewById(R.id.etext1);
        Button btn1 = (Button) findViewById(R.id.send);
        btn1.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                String str = editText.getText().toString(); // getText 로 해당 컴포넌트에 적힌 것을 받아 올 수 있다.
                //또한 toString을 이용하여 문자열로 전환 가능.
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
            }
        });

        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.check1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.check2);
        checkBox1.setOnClickListener(checkListener);
        checkBox2.setOnClickListener(checkListener);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                Toast.makeText(getApplicationContext(),"Rating" + rating, Toast.LENGTH_LONG).show();
            }
        });


    }

    public void myClick(View view){
        Toast.makeText(getApplicationContext(),"버튼이 눌러졌습니다.",Toast.LENGTH_LONG).show();
    }

    public void onRadioClick(@NonNull View view){
        Boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.radio1:{
                Toast.makeText(getApplicationContext(),((RadioButton)view).getText().toString(),Toast.LENGTH_LONG).show();
            }
            case R.id.radio2:{
                Toast.makeText(getApplicationContext(),((RadioButton)view).getText().toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onToggleClick(View view){
        boolean on = ((ToggleButton) view).isChecked();

        if(on){
            Toast.makeText(getApplicationContext(),"Checked",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_LONG).show();
        }
    }
//    public void checkClick(View view){
//        boolean checked = ((CheckBox) view).isChecked(); // view를 체크박스 형변환
//
//        switch(view.getId()){
//            case R.id.check1:
//                if (checked){
//                    Toast.makeText(getApplicationContext(),"고기선택",Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(getApplicationContext(),"고기 선택 해제",Toast.LENGTH_SHORT).show();
//                }
//            case R.id.check2:
//                if(checked){
//                    Toast.makeText(getApplicationContext(),"치즈 선택",Toast.LENGTH_SHORT).show();
//                }else{
//                 Toast.makeText(getApplicationContext(),"치즈 선택 해제",Toast.LENGTH_SHORT).show();
//                }
//        }
//    }
}