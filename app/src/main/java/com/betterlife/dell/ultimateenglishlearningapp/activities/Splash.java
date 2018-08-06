package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.betterlife.dell.ultimateenglishlearningapp.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openingSplash = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(openingSplash);
                    finish();
                }
            }
        };

        timer.start();

    }
}
