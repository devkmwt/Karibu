package com.doomshell.karibu;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.doomshell.karibu.adapter.Menu_adaptor;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.retrofit_model.Output_menu;
import com.doomshell.karibu.model.retrofit_model.RetromenuPojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tab_Menu extends Fragment  {

    ServerApi serverApi;
    Context context;
    View convertview;
    RecyclerView menu_recycler;
    int Restro_ID;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.activity_tab__menu, container, false);
        savedInstanceState=getArguments();
        Restro_ID=savedInstanceState.getInt("Restro_ID");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi =retrofit.create(ServerApi.class);

        menu_recycler=(RecyclerView)convertview.findViewById(R.id.menu_recycle);
        menu_recycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.restro_cart);

        load_menu();
        return convertview;
    }

    private void load_menu() {

        serverApi.menu(""+Restro_ID,0).enqueue(new Callback<RetromenuPojo>() {
            @Override
            public void onResponse(Call<RetromenuPojo> call, Response<RetromenuPojo> response) {
        //        Toast.makeText(context, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();

                ArrayList<String> restro_id=new ArrayList<>();
                ArrayList<String> product_name=new ArrayList<>();
                ArrayList<String> product_image=new ArrayList<>();
                ArrayList<String> description=new ArrayList<>();
                ArrayList<String> store_price=new ArrayList<>();
                ArrayList<String> p_id=new ArrayList<>();


                if(response.body().getSuccess() ==1)
                {
                    RetromenuPojo retromenuPojo =response.body();
                    ArrayList<Output_menu> output_menus= (ArrayList<Output_menu>)
                            retromenuPojo.getOutput();

                    for(Output_menu ot:output_menus)
                    {
                        restro_id.add(ot.getRestorentid());
                        product_name.add(ot.getPname());
                        product_image.add(ot.getImage());
                        description.add(ot.getDescription());
                        store_price.add(ot.getSPrice());
                        p_id.add(ot.getPid());
                    }
                    menu_recycler.setAdapter(new Menu_adaptor(context,restro_id,product_name,product_image,description,store_price,p_id, getActivity(),floatingActionButton));
                }
            }

            @Override
            public void onFailure(Call<RetromenuPojo> call, Throwable t) {

                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
