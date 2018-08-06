package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class datadetail extends AppCompatActivity {


    ImageView detialPhoto;
    TextView detailTitle, detailAnswer, detailAdditional;
    ImageView speakerBtn;
    TextToSpeech textToSpeech;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.98);
        setContentView(R.layout.activity_datadetail);
        getWindow().setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        btn = findViewById(R.id.readmorebtn);
        SharedPreferences sp = getSharedPreferences("UEL", MODE_PRIVATE);
        int aflag = sp.getInt("aflag", 1);
        aflag++;

        if (aflag % 5 == 0) {
            AdView mAdView = findViewById(R.id.adView);
            mAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        if (aflag == 100) {
            aflag = 1;
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("aflag", aflag);
        editor.commit();

        detailTitle = (TextView) (findViewById(R.id.detailtitle));
        detailAnswer = (TextView) (findViewById(R.id.detailanswer));
        detailAdditional = (TextView) (findViewById(R.id.detailadditional));
        detialPhoto = (ImageView) (findViewById(R.id.detailphoto));
        speakerBtn = (ImageButton) (findViewById(R.id.speakerbtn));

        Intent in = getIntent();
        final String title = in.getStringExtra("title");
        String answer = in.getStringExtra("answer");
        String photo = in.getStringExtra("photo");
        String additional = in.getStringExtra("additional");
        String fromnotification = in.getStringExtra("fromnotification");

        if (!(fromnotification == null || fromnotification.equals(""))) {
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(getApplicationContext(),MainActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();
                }
            });
        }
        speakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        speakerBtn.setImageResource(R.drawable.speaker);

                    }
                });
                textToSpeech.speak(title, TextToSpeech.QUEUE_FLUSH, null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(1000);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    speakerBtn.setImageResource(R.drawable.speaker1);
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });

        if (!photo.equals("na")) {
            Log.d("MYMSG", "path--" + photo + "--");

            Picasso.with(getApplicationContext()).load(photo).resize(400, 300).into(detialPhoto);
        } else {

            detialPhoto.setVisibility(View.GONE);
        }
        detailTitle.setText(title);
        detailAnswer.setText(answer);
        detailAdditional.setText(additional);


    }
}
