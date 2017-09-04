package com.doomshell.karibu;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doomshell.karibu.adapter.Review_Adaptor;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.retrofit_model.Output_Review_Retorant;
import com.doomshell.karibu.model.retrofit_model.Retrorreviewpojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabReview extends Fragment {

    RecyclerView review_recycler;



    ServerApi serverApi;
    Context context;
    int Restro_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {

            context=getActivity().getApplicationContext();
            View convertview= inflater.inflate(R.layout.activity_tab_review, container, false);
        savedInstanceState=getArguments();
        Restro_ID=savedInstanceState.getInt("Restro_ID");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);

        review_recycler=(RecyclerView)convertview.findViewById(R.id.review_recycle);
        review_recycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        load_review();
        return  convertview;
    }

    private void load_review()
    {
        serverApi.review(""+Restro_ID,0).enqueue(new Callback<Retrorreviewpojo>() {
            @Override
            public void onResponse(Call<Retrorreviewpojo> call, Response<Retrorreviewpojo> response) {
                Toast.makeText(context, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();

                ArrayList<String> userid=new ArrayList<>();
                ArrayList<String> username=new ArrayList<>();
                ArrayList<String> commentdate=new ArrayList<>();
                ArrayList<String> commenttime=new ArrayList<>();
                ArrayList<String> qualityrating=new ArrayList<>();
                ArrayList<String> deliveryrating=new ArrayList<>();
                ArrayList<String> avgrate=new ArrayList<>();
                ArrayList<String> feedbck=new ArrayList<>();

                if(response.body().getSuccess() ==1)
                {

                    Retrorreviewpojo retrorreviewpojo=response.body();
                    ArrayList<Output_Review_Retorant> output_review_retorants= (ArrayList<Output_Review_Retorant>)
                            retrorreviewpojo.getOutputReviewRetorant();

                    for(Output_Review_Retorant ot:output_review_retorants)
                    {
                        userid.add(ot.getUserId());
                        username.add(ot.getUserName());
                        commentdate.add(ot.getCommentDate());
                        commenttime.add(ot.getCommentTime());
                        qualityrating.add(ot.getQualityRating());
                        deliveryrating.add(ot.getDeliveryRating());
                        avgrate.add(ot.getAvgrate());
                        feedbck.add(ot.getFeedback());
                    }

                    review_recycler.setAdapter(new Review_Adaptor(context,userid,username,commentdate,commenttime,qualityrating,deliveryrating,feedbck,getActivity()));
                }
            }

            @Override
            public void onFailure(Call<Retrorreviewpojo> call, Throwable t) {

                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
