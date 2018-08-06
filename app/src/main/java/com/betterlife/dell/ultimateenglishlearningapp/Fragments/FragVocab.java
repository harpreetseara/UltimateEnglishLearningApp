package com.betterlife.dell.ultimateenglishlearningapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterlife.dell.ultimateenglishlearningapp.Adapters.VocabAdapter;
import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.modals.VocalLevels;

import java.util.ArrayList;


public class FragVocab extends Fragment {

    ArrayList<VocalLevels> al = new ArrayList<>();
    RecyclerView rcv;


    public FragVocab() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_frag_vocab, container, false);
        rcv = (RecyclerView) (rootView.findViewById(R.id.rcv1));


        al.clear();
        al.add(new VocalLevels("Level 1", "level1.txt"));
        al.add(new VocalLevels("Level 2", "level2.txt"));
        al.add(new VocalLevels("Level 3", "level3.txt"));
        al.add(new VocalLevels("Level 4", "level4.txt"));
        al.add(new VocalLevels("Level 5", "level5.txt"));
        al.add(new VocalLevels("Level 6", "level6.txt"));
        al.add(new VocalLevels("Level 7", "level7.txt"));
        al.add(new VocalLevels("Level 8", "level8.txt"));


        VocabAdapter myad = new VocabAdapter(al, getContext());
        rcv.setAdapter(myad);
        myad.notifyDataSetChanged();
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 3);
        rcv.setLayoutManager(linearLayoutManager);
        Log.d("MYMESSAGE", "On Create of RecyclerView Demo Called");
        return rootView;
    }


}
