package com.doomshell.karibu.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doomshell.karibu.R;

import java.util.ArrayList;

/**
 * Created by vikas on 3/18/2017.
 */

public class Review_Adaptor extends RecyclerView.Adapter<Review_Adaptor.MyViewHolder> {

    Context context;
    Activity activity;

    ArrayList<String> userid=new ArrayList<>();
    ArrayList<String> username=new ArrayList<>();
    ArrayList<String> commentdate=new ArrayList<>();
    ArrayList<String> commenttime=new ArrayList<>();
    ArrayList<String> qualityrating=new ArrayList<>();
    ArrayList<String> deliveryrating=new ArrayList<>();
    ArrayList<String> feedbck=new ArrayList<>();
  //  ArrayList<String> avgrate=new ArrayList<>();

    public Review_Adaptor(Context context,  ArrayList<String> userid, ArrayList<String> username,
                          ArrayList<String> commentdate, ArrayList<String> commenttime,
                          ArrayList<String> qualityrating, ArrayList<String> deliveryrating,ArrayList<String> feedbck,
                          Activity activity) {

        this.context=context;
        this.userid=userid;
        this.username=username;
        this.commentdate=commentdate;
        this.commenttime=commenttime;
        this.qualityrating=qualityrating;
        this.deliveryrating=deliveryrating;
        this.feedbck=feedbck;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView reviewr_name;
        TextView coment_date;
        TextView coment_time;
        TextView coment_feed;
        RatingBar quality_rating;
        RatingBar delivery_rating;
        RatingBar avg_rating;

        public MyViewHolder(View itemView) {
            super(itemView);

            reviewr_name=(TextView)itemView.findViewById(R.id.r_name);
            coment_date=(TextView)itemView.findViewById(R.id.r_date);
            coment_time=(TextView)itemView.findViewById(R.id.r_time);
            coment_feed=(TextView)itemView.findViewById(R.id.textView_comment);
            quality_rating=(RatingBar)itemView.findViewById(R.id.qua_rating);
            delivery_rating=(RatingBar)itemView.findViewById(R.id.delvrt_rating);

            LayerDrawable stars = (LayerDrawable)quality_rating.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(activity.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            quality_rating.setRating(3.5f);

            LayerDrawable stars1 = (LayerDrawable)delivery_rating.getProgressDrawable();
            stars1.getDrawable(2).setColorFilter(activity.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            delivery_rating.setRating(3.5f);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_review_adaptor,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.reviewr_name.setText(""+username.get(position));
        holder.coment_date.setText(""+commentdate.get(position));
        holder.coment_time.setText(""+commenttime.get(position));
        holder.coment_feed.setText(""+feedbck.get(position));
        holder.quality_rating.setRating(Float.parseFloat(qualityrating.get(position).toString()));
        holder.delivery_rating.setRating(Float.parseFloat(deliveryrating.get(position).toString()));
       // holder.avg_rating.setRating(Float.parseFloat(avgrate.get(position).toString()));

    }

    @Override
    public int getItemCount() {
        return userid.size();
    }

}

