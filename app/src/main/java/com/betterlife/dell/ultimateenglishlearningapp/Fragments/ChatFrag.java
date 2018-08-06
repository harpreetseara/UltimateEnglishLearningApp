package com.betterlife.dell.ultimateenglishlearningapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.activities.ChatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ChatFrag extends Fragment {

    Spinner optionsp;
    String options[] = {"Select Option", "Chat between two friends","Interview Conversation","Shopping in store","Discussion about a book","Asking for directions","Chat about weather","Booking a Taxi"};
    Button startchatbtn;

    public ChatFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        AdView mAdView = rootView.findViewById(R.id.adViewchat);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        optionsp = rootView.findViewById(R.id.optionsp);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, options);
        optionsp.setAdapter(ad);
        startchatbtn = rootView.findViewById(R.id.startchatbtn);
        startchatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = optionsp.getSelectedItem().toString();
                if (value.equalsIgnoreCase("Select Option")) {

                    Toast.makeText(getContext(),"Please select a choice",Toast.LENGTH_SHORT).show();
                } else if (value.equalsIgnoreCase("Chat between two friends")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat1.txt");
                    startActivity(in);
                } else if (value.equalsIgnoreCase("Interview Conversation")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat2.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Shopping in store")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat3.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Discussion about a book")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat4.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Asking for directions")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat5.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Chat about weather")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat6.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Booking a Taxi")) {
                    Intent in = new Intent(getActivity(), ChatActivity.class);
                    in.putExtra("filename","chat7.txt");
                    startActivity(in);
                }
            }
        });


        return rootView;

    }
}
