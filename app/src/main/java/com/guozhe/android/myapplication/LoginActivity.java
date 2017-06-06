package com.guozhe.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    EditText id,password;
    Button login;
    FrameLayout ground;
    int deviceWeith,deviceHeight;
    public static final String TAG = "LOGIN";
    Stage stage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id= (EditText)findViewById(R.id.id);
        password = (EditText)findViewById(R.id.passWord);
        ground = (FrameLayout)findViewById(R.id.ground);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        DisplayMetrics displayMetrics =getResources().getDisplayMetrics();
        deviceWeith = displayMetrics.widthPixels;
        deviceHeight = displayMetrics.heightPixels;
        stage = new Stage(getBaseContext());
        ground.addView(stage);
        runTask();

    }

    private void runTask(){
        Rain rain =new Rain();
        rain.start();
        DrawCanvas drawCanvas = new DrawCanvas();
        drawCanvas.start();
    }
    class DrawCanvas extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                stage.postInvalidate();
            }
        }
    }

    class Rain extends Thread{
        @Override
        public void run() {
            Random random = new Random();
            for(int j = 0 ;j <100 ;j++){
                RatioDraw ratioDraw = new RatioDraw();
                ratioDraw.radius = random.nextInt(20)+5;
                ratioDraw.x = random.nextInt(deviceWeith);
                ratioDraw.speed = random.nextInt(10)+5;
                Paint paint =new Paint();
                paint.setColor(Color.WHITE);
                ratioDraw.paint = paint;
                stage.addRatio(ratioDraw);
                ratioDraw.start();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                   Log.e(TAG,e.toString());
                }
            }

        }
    }
    class Stage extends View {
        List<RatioDraw> datas = new ArrayList<>();
        public Stage(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for(RatioDraw draw:datas){
                canvas.drawCircle(draw.x,draw.y,draw.radius,draw.paint);
            }
        }
        public void addRatio(RatioDraw ratioDraw){
            this.datas.add(ratioDraw);
        }
    }

    class RatioDraw extends Thread{
        float x,y;
        int speed;
        float radius;
        Paint paint;

        @Override
        public void run() {
            int count = 0;
            while(y<deviceHeight){
                count++;
                y = speed*count;
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    Log.e(TAG,e.toString());
                }
            }
        }
    }

}
