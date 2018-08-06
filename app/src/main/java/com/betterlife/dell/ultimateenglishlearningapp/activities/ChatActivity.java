package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.betterlife.dell.ultimateenglishlearningapp.Adapters.ChatAdapter;
import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.modals.Chat;
import com.betterlife.dell.ultimateenglishlearningapp.modals.LevelData;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class ChatActivity extends AppCompatActivity {

    ArrayList<Chat> al;
    ArrayList<String> aldata;
    RecyclerView rv;
    String filename;
    boolean b = true;
    int count = 0;
    ChatAdapter myad;
    public TextToSpeech textToSpeech;

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6292652514957205/9408397993");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        filename = getIntent().getStringExtra("filename");

        new Thread(new loadChat()).start();
        rv = findViewById(R.id.rcchat);
        al = new ArrayList<>();
        aldata = new ArrayList<>();
        myad = new ChatAdapter(al, this);
        rv.setAdapter(myad);
        myad.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

        Toast.makeText(getApplicationContext(),"Turn On volume to hear the conversation",Toast.LENGTH_SHORT).show();

    }

    public void showchat(View view) {

        if (!(count == aldata.size() - 1)) {
            if (b == true) {
                al.add(new Chat(aldata.get(count), "left"));
                b = false;
            } else {
                al.add(new Chat(aldata.get(count), "right"));
                b = true;
            }
            sayIt(aldata.get(count));
            count++;

            myad.notifyDataSetChanged();
            rv.scrollToPosition(al.size() - 1);

        } else {
            Toast.makeText(getApplicationContext(), "Conversation Ended", Toast.LENGTH_SHORT).show();
        }

    }


    public void sayIt(String s) {
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        } else {
        }
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }

        if (al.size() > 5) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

    }

    class loadChat implements Runnable {

        @Override
        public void run() {

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open(filename), "UTF-8"));
                // do reading, usually loop until end of file reading
                String mLine;
                StringTokenizer st;

                al.clear();


                while ((mLine = reader.readLine()) != null) {


                    aldata.add(mLine);

                }
            } catch (IOException e) {
                e.printStackTrace();
                //log the exception
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        //log the exception
                    }
                }
            }
        }
    }
}
