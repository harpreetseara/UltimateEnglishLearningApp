package com.betterlife.dell.ultimateenglishlearningapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.activities.ChatActivity;
import com.betterlife.dell.ultimateenglishlearningapp.modals.Chat;

import java.util.ArrayList;

/**
 * Created by Dell on 6/19/2018.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    ArrayList<Chat> al;
    ChatActivity context;
    LinearLayout.LayoutParams params;


    public ChatAdapter(ArrayList<Chat> al, ChatActivity context) {
        this.al = al;
        this.context = context;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.85);
        params = new LinearLayout.LayoutParams(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    // Define ur own View Holder (Refers to Single Row)
    class MyViewHolder extends RecyclerView.ViewHolder {
        View singlecardview;

        // We have Changed View (which represent single row) to CardView in whole code
        public MyViewHolder(View itemView) {
            super(itemView);
            singlecardview = (itemView);
        }
    }


    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewthatcontainscardview = inflater.inflate(R.layout.layoutforchat, parent, false);
        Log.d("MYMESSAGE", "On CreateView Holder Done");
        return new MyViewHolder(viewthatcontainscardview);
    }


    @Override
    public void onBindViewHolder(ChatAdapter.MyViewHolder holder, final int position) {

        View localcardview = holder.singlecardview;
        TextView tv1;
        LinearLayout chatll, chatllin;

        tv1 = (TextView) (localcardview.findViewById(R.id.chattv));
        chatll = (localcardview.findViewById(R.id.chatll));
        chatllin = (localcardview.findViewById(R.id.chatllin));
        chatllin.setLayoutParams(params);

        Chat obj = al.get(position);

        tv1.setText(obj.getText());

        Log.d("MYMSG", obj.getSide() + "---");


        if (obj.getSide().equalsIgnoreCase("left")) {
            chatll.setGravity(Gravity.LEFT);
            chatllin.setBackgroundResource(R.drawable.roundedcorners);
            tv1.setTextColor(Color.parseColor("#000000"));

        } else {
            chatll.setGravity(Gravity.RIGHT);
            chatllin.setBackgroundResource(R.drawable.roundedcorners1);
            tv1.setTextColor(Color.parseColor("#ffffff"));
        }


        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.sayIt(al.get(position).getText());
            }
        });

    }

    @Override
    public int getItemCount() {

        Log.d("MYMESSAGE", "get Item Count Called");
        return al.size();
    }
}
