package com.betterlife.dell.ultimateenglishlearningapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.activities.LevelDetail;
import com.betterlife.dell.ultimateenglishlearningapp.modals.VocalLevels;
import java.util.ArrayList;


public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.MyViewHolder> {

    ArrayList<VocalLevels> al;
    Context context;
    public VocabAdapter(ArrayList<VocalLevels> al, Context context) {
        this.al = al;
        this.context=context;
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
    public VocabAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewthatcontainscardview = inflater.inflate(R.layout.layoutforvocablevels, parent, false);


        Log.d("MYMESSAGE", "On CreateView Holder Done");
        return new MyViewHolder(viewthatcontainscardview);

    }

    @Override
    public void onBindViewHolder(VocabAdapter.MyViewHolder holder, final int position) {

        View localcardview = holder.singlecardview;
        TextView tv1, tv2, tv3;
        ImageView imv1;

        tv1 = (TextView) (localcardview.findViewById(R.id.vocableveltv));
        imv1 = (ImageView) (localcardview.findViewById(R.id.vocablevelimv));
        VocalLevels obj = al.get(position);

        tv1.setText(obj.getLevelName());
//            Picasso.with(getContext()).load(obj.photo).resize(400, 300).into(imv1);

        Log.d("MYMESSAGE", "On Bind Of View Holder Called");

        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, LevelDetail.class);
                in.putExtra("filename",al.get(position).getLevelIcon());
                in.putExtra("levelname",al.get(position).getLevelName());


                context.startActivity(in);

            }
        });

    }

    @Override
    public int getItemCount() {

        Log.d("MYMESSAGE", "get Item Count Called");
        return al.size();
    }
}
