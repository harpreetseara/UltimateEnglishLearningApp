package com.betterlife.dell.ultimateenglishlearningapp.helperclasses;

import android.content.Context;

import com.betterlife.dell.ultimateenglishlearningapp.modals.LevelData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Dell on 7/19/2018.
 */

public class FetchMeData {


    ArrayList<LevelData> al;

    public FetchMeData() {
        al = new ArrayList<>();
    }


    public LevelData fetchLevel(Context context) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("allwords.txt")));
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
            }
            int r = new Random().nextInt((199 - 0) + 1) + 0;
            LevelData data = al.get(r);
            al = null;
            return data;
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        return null;
    }


}
