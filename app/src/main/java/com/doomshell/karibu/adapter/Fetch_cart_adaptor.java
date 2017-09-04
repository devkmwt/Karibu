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

import com.doomshell.karibu.Milano;
import com.doomshell.karibu.R;

import java.util.ArrayList;

/**
 * Created by LENOVO on 30-08-2017.
 */

public class Fetch_cart_adaptor extends RecyclerView.Adapter<Fetch_cart_adaptor.MyViewHolder> {
    Context context;
    Activity activity;
    String rest_id;

    ArrayList<String> itemId=new ArrayList<>();
    ArrayList<String> imageUrl=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> description=new ArrayList<>();
    ArrayList<String> qty=new ArrayList<>();
    ArrayList<String> price=new ArrayList<>();

    public Fetch_cart_adaptor(Context context, ArrayList<String> itemId, ArrayList<String> imageUrl,
                              ArrayList<String> title, ArrayList<String> description, ArrayList<String> qty,
                              ArrayList<String> price, Activity activity) {

        this.context=context;
        this.itemId=itemId;
        this.imageUrl=imageUrl;
        this.title=title;
        this.description=description;
        this.qty=qty;
        this.price=price;
        this.activity=activity;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itam_title;
        TextView item_price;

        /*TextView coment_time;
        TextView coment_feed;
        RatingBar quality_rating;
        RatingBar delivery_rating;*/

        public MyViewHolder(View itemView) {
            super(itemView);

            itam_title=(TextView)itemView.findViewById(R.id.itam_title);
            item_price=(TextView)itemView.findViewById(R.id.item_price);
//            rest_id=activity.getIntent().getStringExtra("cat_id");
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_adaptor,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itam_title.setText(""+title.get(position));
        holder.item_price.setText(""+price.get(position));
    }

    @Override
    public int getItemCount() {
        return itemId.size();
    }
}
