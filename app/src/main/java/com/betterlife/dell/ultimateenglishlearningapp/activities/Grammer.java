package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.betterlife.dell.ultimateenglishlearningapp.Fragments.FragGrammer;
import com.betterlife.dell.ultimateenglishlearningapp.Fragments.FragVocab;
import com.betterlife.dell.ultimateenglishlearningapp.R;

public class Grammer extends AppCompatActivity {

    WebView webview1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammer);
        String filename=getIntent().getStringExtra("filename");
        webview1 = (WebView) (findViewById(R.id.webview));
        webview1.loadUrl("file:///android_asset/"+filename);

    }


}
