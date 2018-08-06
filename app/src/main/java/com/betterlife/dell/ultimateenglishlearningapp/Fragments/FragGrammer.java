package com.betterlife.dell.ultimateenglishlearningapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.activities.Grammer;
import com.betterlife.dell.ultimateenglishlearningapp.activities.MainActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FragGrammer extends Fragment {


    public FragGrammer() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frag_grammer, container, false);
        TextView tv = rootView.findViewById(R.id.basictermtv);
        TextView tv1 = rootView.findViewById(R.id.presenttensetv);
        TextView tv2 = rootView.findViewById(R.id.pasttensetv);
        TextView tv3 = rootView.findViewById(R.id.futuretensetv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Grammer.class);
                in.putExtra("filename", "basicterms.html");
                startActivity(in);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Grammer.class);
                in.putExtra("filename", "tensepage.html");
                startActivity(in);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Grammer.class);
                in.putExtra("filename", "tensepage1.html");
                startActivity(in);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Grammer.class);
                in.putExtra("filename", "tensepage2.html");
                startActivity(in);
            }
        });
        AdView mAdView = rootView.findViewById(R.id.adViewgrammer);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        return rootView;
    }


}
