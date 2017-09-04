package com.doomshell.karibu;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doomshell.karibu.adapter.Info_payment_adaptor;
import com.doomshell.karibu.adapter.Info_time_adaptor;
import com.doomshell.karibu.adapter.Review_Adaptor;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.retrofit_model.Info_Paymentmethod;
import com.doomshell.karibu.model.retrofit_model.Info_Time;
import com.doomshell.karibu.model.retrofit_model.Output_Review_Retorant;
import com.doomshell.karibu.model.retrofit_model.RetrorInfopojo;
import com.doomshell.karibu.model.retrofit_model.Retrorreviewpojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabInfo extends Fragment {

    RecyclerView time_recycler,payment_recycler;

    ServerApi serverApi;
    Context context;
    int Restro_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {

        context=getActivity().getApplicationContext();
        View convertview= inflater.inflate(R.layout.activity_tab_info, container, false);
        savedInstanceState=getArguments();
        Restro_ID=savedInstanceState.getInt("Restro_ID");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);

        time_recycler=(RecyclerView)convertview.findViewById(R.id.recycle_time);
        time_recycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        payment_recycler=(RecyclerView)convertview.findViewById(R.id.recycle_payment);
        payment_recycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        load_info();
        return  convertview;

    }

    private void load_info() {

        serverApi.info(""+Restro_ID).enqueue(new Callback<RetrorInfopojo>() {
            @Override
            public void onResponse(Call<RetrorInfopojo> call, Response<RetrorInfopojo> response) {

                ArrayList<String> del_day=new ArrayList<>();
                ArrayList<String> open_time=new ArrayList<>();
                ArrayList<String> close_time=new ArrayList<>();
                ArrayList<String> pay_id=new ArrayList<>();
                ArrayList<String> pay_name=new ArrayList<>();
                ArrayList<String> pay_image=new ArrayList<>();

                if(response.message().equalsIgnoreCase("ok")) {
                    if (response.body().getSuccess() == 1) {
                        RetrorInfopojo retrorInfopojo = response.body();

                        ArrayList<Info_Time> info_times = (ArrayList<Info_Time>) retrorInfopojo.getTime();
                        ArrayList<Info_Paymentmethod> info_paymentmethods = (ArrayList<Info_Paymentmethod>) retrorInfopojo.getPaymentmethods();

                        for (Info_Time it : info_times) {

                            del_day.add(it.getDay());
                            open_time.add(it.getOpentime());
                            close_time.add(it.getClosetime());
                        }
                        if(info_paymentmethods!=null) {
                            for (Info_Paymentmethod ip : info_paymentmethods) {

                                pay_id.add(ip.getId());
                                pay_name.add(ip.getName());
                                pay_image.add(ip.getImage());
                            }
                            payment_recycler.setAdapter(new Info_payment_adaptor(context, pay_id, pay_name, pay_image, getActivity()));
                        }
                        time_recycler.setAdapter(new Info_time_adaptor(context, del_day, open_time, close_time, getActivity()));


                    }
                }else {
                    Toast.makeText(context, "no info found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RetrorInfopojo> call, Throwable t) {

            }
        });
    }
}
