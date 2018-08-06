package com.betterlife.dell.ultimateenglishlearningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import com.betterlife.dell.ultimateenglishlearningapp.Adapters.LevelDetailAdapter;
import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.modals.LevelData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LevelDetail extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    RecyclerView leveldetailrcv;
    ArrayList<LevelData> al;
    LevelDetailAdapter myad;

    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detail);
        try {
            filename = getIntent().getStringExtra("filename");
            String levelname = getIntent().getStringExtra("levelname");
            al = new ArrayList<>();
            toolbar = (Toolbar) (findViewById(R.id.toolbar));
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(levelname + "");
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>"+levelname+" </font>"));


            leveldetailrcv = (RecyclerView) findViewById(R.id.leveldetailrcv);
            leveldetailrcv.setNestedScrollingEnabled(false);
            myad = new LevelDetailAdapter(al, getApplicationContext());
            leveldetailrcv.setAdapter(myad);


        } catch (Exception ae) {
            ae.printStackTrace();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        leveldetailrcv.setLayoutManager(linearLayoutManager);

        new Thread(new loadLevelContent()).start();

    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    class loadLevelContent implements Runnable {

        @Override
        public void run() {

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open(filename)));
                // do reading, usually loop until end of file reading
                String mLine;
                StringTokenizer st;
                al.clear();
                while ((mLine = reader.readLine()) != null) {
                    st = new StringTokenizer(mLine, ";");
                    String id = st.nextToken();
                    String title = st.nextToken();
                    String answer = st.nextToken();
                    String additional = st.nextToken();
                    String photo = st.nextToken();
                    al.add(new LevelData(id, title, answer, additional, photo));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myad.notifyDataSetChanged();

                        }
                    });
                }
            } catch (IOException e) {
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
