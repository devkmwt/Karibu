package com.doomshell.karibu.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doomshell.karibu.R;
import com.doomshell.karibu.model.retrofit_model.Info_Time;

import java.util.ArrayList;

/**
 * Created by Neha on 29-08-2017.
 */

public class Info_time_adaptor extends RecyclerView.Adapter<Info_time_adaptor.MyViewHolder>{

    Context context;
    Activity activity;

    ArrayList<String> del_day=new ArrayList<>();
    ArrayList<String> open_time=new ArrayList<>();
    ArrayList<String> close_time=new ArrayList<>();

    public Info_time_adaptor(Context context, ArrayList<String> del_day, ArrayList<String>
            open_time, ArrayList<String> close_time, FragmentActivity activity) {

        this.context = context;
        this.del_day = del_day;
        this.open_time = open_time;
        this.close_time = close_time;
        this.activity = activity;
    }

        public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView day;
        TextView opntime;
        TextView clostime;

        public MyViewHolder(View itemView) {
            super(itemView);

            day=(TextView)itemView.findViewById(R.id.info_day);
            opntime=(TextView)itemView.findViewById(R.id.info_opentime);
            clostime=(TextView)itemView.findViewById(R.id.info_clostime);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_info_time_adaptor,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.day.setText(""+del_day.get(position));
        holder.opntime.setText(""+open_time.get(position));
        holder.clostime.setText(""+close_time.get(position));

    }

    @Override
    public int getItemCount() {
        return del_day.size();
    }
}
