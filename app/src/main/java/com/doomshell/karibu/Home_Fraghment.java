package com.doomshell.karibu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doomshell.karibu.adapter.HomeAdaptor_Mine;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.retrofit_model.FetchHomeProduct;
import com.doomshell.karibu.model.retrofit_model.Output_Fetch_HomeProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Home_Fraghment extends Fragment implements View.OnClickListener {

View convertview;
    Context context;
    ServerApi serverApi;

    RecyclerView home_types_recyleView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.fragment_home__fraghment, container, false);

        home_types_recyleView=(RecyclerView)convertview.findViewById(R.id.home_types_recyleView);
        home_types_recyleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        home_types_recyleView.setNestedScrollingEnabled(false);
        home_types_recyleView.setHasFixedSize(false);

        home_types_recyleView.setItemAnimator(new DefaultItemAnimator());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getActivity().getResources().getString(R.string.Base_Url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);

        fetch_home_products();

        return convertview;
    }

    @Override
    public void onClick(View v) {
         }


         void fetch_home_products()
         {
             serverApi.fatch_home_product().enqueue(new Callback<FetchHomeProduct>() {
                 @Override
                 public void onResponse(Call<FetchHomeProduct> call, Response<FetchHomeProduct> response) {
                  //   Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();

                     if(response!=null ) {
                         if (response.message().equalsIgnoreCase("ok")) {

                             if(response.body().getSuccess()==1) {
                                 ArrayList<String> name = new ArrayList<>();
                                 ArrayList<Integer> id = new ArrayList<>();

                                 ArrayList<Output_Fetch_HomeProduct> output_fetch_homeProducts_List= (ArrayList<Output_Fetch_HomeProduct>) response.body().getOutputFatchhomeproduct();

                                 for(Output_Fetch_HomeProduct ofh:output_fetch_homeProducts_List)
                                 {
                                     name.add(ofh.getName());
                                     id.add(ofh.getId());
                                 }

                                 HomeAdaptor_Mine homeAdaptor_mine=new HomeAdaptor_Mine(id,name,getActivity(),context);
                                 home_types_recyleView.setAdapter(homeAdaptor_mine);

                             }else {
                                 Toast.makeText(context, "Something went wrong on server", Toast.LENGTH_SHORT).show();
                             }

                         }else
                         {
                             Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
                         }
                     }

                 }

                 @Override
                 public void onFailure(Call<FetchHomeProduct> call, Throwable t) {
                     Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             });

         }
}
