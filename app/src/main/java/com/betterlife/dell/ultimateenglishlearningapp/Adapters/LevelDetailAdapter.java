package com.betterlife.dell.ultimateenglishlearningapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.activities.LevelDetail;
import com.betterlife.dell.ultimateenglishlearningapp.activities.datadetail;
import com.betterlife.dell.ultimateenglishlearningapp.modals.LevelData;

import java.util.ArrayList;

/**
 * Created by Dell on 5/29/2018.
 */

public class LevelDetailAdapter extends RecyclerView.Adapter<LevelDetailAdapter.MyViewHolder> {

    ArrayList<LevelData> al;
    Context context;


    public LevelDetailAdapter(ArrayList<LevelData> al, Context context) {
        this.al = al;
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View customview;

        public MyViewHolder(View itemView) {
            super(itemView);
            customview = itemView;
        }
    }
    @Override
    public LevelDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View singleview = inflater.inflate(R.layout.layoutfordata, parent, false);
        // This will call Constructor of MyViewHolder, which will further copy its reference
        // to customview (instance variable name) to make its usable in all other methods of class
        Log.d("MYMESSAGE", "On CreateView Holder Done");
        return new MyViewHolder(singleview);
    }

    @Override
    public void onBindViewHolder(LevelDetailAdapter.MyViewHolder holder, final int position) {

        View localcardview = holder.itemView;
        TextView tv1, tv2;
        tv1 = (TextView) (localcardview.findViewById(R.id.datatitle));
        tv2 = (TextView) (localcardview.findViewById(R.id.dataans));
        final LevelData obj = al.get(position);
        tv1.setText(obj.title);
        tv2.setText(obj.answer);

//            Picasso.with(getApplicationContext()).load(obj.photo).resize(400, 300).into(imv1);

        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, datadetail.class);
                in.putExtra("title", obj.title);
                in.putExtra("id", obj.id);
                in.putExtra("answer", obj.answer);
                in.putExtra("additional", obj.additional);
                in.putExtra("photo", obj.photo);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });
        Log.d("MYMESSAGE", "On Bind Of View Holder Called");

    }

    @Override
    public int getItemCount() {

        Log.d("MYMESSAGE", "get Item Count Called");
        return al.size();
    }
}
