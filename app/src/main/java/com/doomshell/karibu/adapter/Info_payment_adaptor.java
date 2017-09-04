package com.doomshell.karibu.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doomshell.karibu.R;

import java.util.ArrayList;

/**
 * Created by Neha on 29-08-2017.
 */

public class Info_payment_adaptor extends RecyclerView.Adapter<Info_payment_adaptor.MyViewHolder> {

    Context context;
    Activity activity;

    ArrayList<String> pay_id=new ArrayList<>();
    ArrayList<String> pay_name=new ArrayList<>();
    ArrayList<String> pay_image=new ArrayList<>();
    public Info_payment_adaptor(Context context, ArrayList<String> pay_id, ArrayList<String>
            pay_name, ArrayList<String> pay_image, FragmentActivity activity) {

        this.context=context;
        this.pay_id=pay_id;
        this.pay_name=pay_name;
        this.pay_image=pay_image;
        this.activity=activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_pay;
        ImageView image_pay;

        public MyViewHolder(View itemView) {
            super(itemView);

            name_pay=(TextView)itemView.findViewById(R.id.pay_name);
            image_pay=(ImageView)itemView.findViewById(R.id.pay_img);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_info_payment_adaptor,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.name_pay.setText(""+pay_name.get(position));

        Glide.with(context).load(pay_image.get(position)).asBitmap().into(new SimpleTarget<Bitmap>(100,100) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.image_pay.setImageBitmap(resource);
            }
        });

    }


    @Override
    public int getItemCount() {
        return pay_id.size();
    }
}
