package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.betterlife.dell.ultimateenglishlearningapp.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.95);
        setContentView(R.layout.activity_about);
        getWindow().setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void closeabout(View view) {
        finish();
    }
}
