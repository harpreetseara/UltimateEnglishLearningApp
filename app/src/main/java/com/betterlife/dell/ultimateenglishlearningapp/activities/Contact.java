package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterlife.dell.ultimateenglishlearningapp.R;

public class Contact extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.95);
        setContentView(R.layout.activity_contact);
        getWindow().setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        tv=(TextView)(findViewById(R.id.contactustv));
        tv.setText(Html.fromHtml("You can email us at <a href=\"mailto:ultimatelearningapp@gmail.it\">ultimatelearningapp@gmail.com</a> for any problems and Queries"));
    }

    public void closecontact(View view) {
        finish();
    }
}
