package com.cscorner.leicestercollege;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Animation topanin, bottonanim;
    ImageView imageView;
    TextView  textView;

    public static int SPLACH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        topanin= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottonanim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        imageView=findViewById(R.id.walkImage);
        textView= findViewById(R.id.text1);

        imageView.setAnimation(topanin);
        textView.setAnimation(bottonanim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MainActivity.this==null){
                    return;
                }
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLACH_SCREEN_TIME_OUT);
    }
}