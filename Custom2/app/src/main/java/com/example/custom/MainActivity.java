package com.example.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

class MyView extends View{ //커스텀 뷰를 만들겠다! 이 커스텀 뷰는 각각의 컴포넌트 요소가 아닌 앱 전반에 걸쳐서 영향을 줄 수 있음
    // MainActivity에서 연결해서 사용 할 예정이다
    int key;
    String str;
    int x, y;

    public MyView(Context context){
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    public boolean onTouchEvent(MotionEvent event){ //onTouchEvent는 View 하위의 메서드 이다.
        //onTouchEventListener 라는 인터페이스도 View 클래스에 존재하며, 이는 반드시 onTouch() 메서드를 구현하여야 쓸 수있다.
        x = (int) event.getX(0);
        y = (int) event.getY(0);
        invalidate(); // 화면을 무효화 시킴 > onDraw()가 호출된다.
        return super.onTouchEvent(event);
    }

    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawCircle(x,y,30,paint);
        canvas.drawText("(" + x + "," + y + ") 에서 터치 이벤트 발생",x,y,paint);
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyView mView = new MyView(this); //여기서 넣어주는 this 는 MainActivity 의 각종 정보를 담고있는 context 이다.
//        setContentView(mView);
        setContentView(R.layout.activity_main);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        VolumeControlView volumeControlView = (VolumeControlView) findViewById(R.id.volume);
        volumeControlView.setKnobListener(new VolumeControlView.KnobListener() {
            @Override
            public void onChanged(double angle) {
                float rating = ratingBar.getRating();
                if(angle > 0 && rating<7.0)
                    ratingBar.setRating(rating +1.0f);
                else if(rating >0.0)
                    ratingBar.setRating(rating-1.0f);
            }
        });
    }
}

