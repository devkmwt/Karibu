package com.doomshell.karibu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.doomshell.karibu.adapter.Custom_Spinner_Adapter;
import com.doomshell.karibu.adapter.Home_inner_Items_Test_Adaptor;
import com.doomshell.karibu.helper.CustomProgressDialog;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.SharedPrefManager;
import com.doomshell.karibu.model.retrofit_model.FetchKitchenTypes;
import com.doomshell.karibu.model.retrofit_model.FetchSearch;
import com.doomshell.karibu.model.retrofit_model.KitchenType;
import com.doomshell.karibu.model.retrofit_model.SearchProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Search extends AppCompatActivity implements View.OnClickListener{

    RadioButton radio_first;
    //LinearLayout search1,search2,search3;
    RadioGroup search_rg;

    String type=""+1;
    RatingBar search_ratingBar;
    float myrating=0.0f;
    Button search_find;
    SearchView search_sb;
    String query="";
    Spinner search_ktype_spinner;
    LinearLayout kitchentype_linearLayout;

    Dialog contactdialog;
    ProgressBar bar;

    ServerApi serverApi;

    RecyclerView search_recycler;

    ArrayList<String> kitchen_id;
    ArrayList<String> kitchen_name;

    ImageView no_found_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        search_find=(Button) findViewById(R.id.search_find);
        search_rg=(RadioGroup) findViewById(R.id.search_rg);
        kitchentype_linearLayout=(LinearLayout) findViewById(R.id.kitchentype_linearLayout);
        radio_first=(RadioButton)findViewById(R.id.radio_first);
        search_ratingBar=(RatingBar) findViewById(R.id.search_ratingBar);
        search_sb=(SearchView) findViewById(R.id.search_sb);
        search_ktype_spinner=(Spinner) findViewById(R.id.search_ktype_spinner);
        search_recycler=(RecyclerView) findViewById(R.id.search_recycler);
        no_found_img=(ImageView) findViewById(R.id.no_found_img);

        search_recycler.setLayoutManager(new GridLayoutManager(Search.this,2));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.Base_Url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);
        load_Kitchens();


        LayerDrawable stars = (LayerDrawable)search_ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        search_ratingBar.setRating(myrating);

        radio_first.setChecked(true);
        radio_first.setSelected(true);

        search_find.setOnClickListener(this);

        search_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton=(RadioButton)findViewById(checkedId);

                switch (radioButton.getText().toString())
                {
                    case "Restaurant" :
                        type=""+1;
                        kitchentype_linearLayout.setVisibility(View.VISIBLE);
                        break;

                    case "Grocery" :
                        type=""+2;
                        kitchentype_linearLayout.setVisibility(View.GONE);
                        break;

                    case "Annonces" :
                        type=""+3;
                        kitchentype_linearLayout.setVisibility(View.VISIBLE);
                        break;

                    default:
                        type=""+0;
                        kitchentype_linearLayout.setVisibility(View.VISIBLE);
                }

                String kid=""+0;
                int pos=search_ktype_spinner.getSelectedItemPosition();
                if(pos<0)
                {
                    kid=""+0;
                }else
                {
                    kid=kitchen_id.get(pos);
                }
                query=""+search_sb.getQuery();
                if(query.equals(""))
                {
                    query="0";
                    load_search(type,myrating,kitchen_id.get(pos),"302012",query);
                }else {

                    load_search(type, myrating, kid, "0", query);
                }

           //     Toast.makeText(Search.this, ""+type, Toast.LENGTH_SHORT).show();
            }
        });

        search_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myrating=rating;

              /*  if(myrating<=0.0f)
                {
                    myrating=-1;
                }*/

                int pos=search_ktype_spinner.getSelectedItemPosition();
                query=""+search_sb.getQuery();
                if(query.equals(""))
                {
                    query="0";
                    load_search(type,myrating,kitchen_id.get(pos),"302012",query);
                }else {

                    load_search(type, myrating, kitchen_id.get(pos), "0", query);

                }
           //     Toast.makeText(Search.this, ""+myrating, Toast.LENGTH_SHORT).show();
            }
        });

        search_ktype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(Search.this, ""+kitchen_id.get(position), Toast.LENGTH_SHORT).show();
                query=""+search_sb.getQuery();
                if(query.equals(""))
                {
                    query="0";
                    load_search(type,myrating,kitchen_id.get(position),"302012",query);
                }
                else {
                    load_search(type, myrating, kitchen_id.get(position), "0", query);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      //  Toast.makeText(this, ""+SharedPrefManager.getInstance(Search.this).getUser_details("userid"), Toast.LENGTH_SHORT).show();

    }

    void load_Kitchens()
    {
       final CustomProgressDialog customProgressDialog=new CustomProgressDialog(Search.this);

        serverApi.kitchen_types().enqueue(new Callback<FetchKitchenTypes>() {
            @Override
            public void onResponse(Call<FetchKitchenTypes> call, Response<FetchKitchenTypes> response) {
                if (response!=null)
                {
                    if(response.message().equalsIgnoreCase("ok"))
                    {
                        ArrayList<KitchenType> kitchenTypes= (ArrayList<KitchenType>) response.body().getKitchenTypes();

                        if(kitchenTypes!=null) {
                            kitchen_id = new ArrayList<String>();
                            kitchen_name = new ArrayList<String>();

                            kitchen_id.add("" + 0);
                            kitchen_name.add("Select all");

                            for (KitchenType kt : kitchenTypes) {
                                kitchen_id.add(kt.getId());
                                kitchen_name.add(kt.getCatName());
                            }

                        }else {
                            //adding default item in spiner

                            kitchen_id = new ArrayList<String>();
                            kitchen_name = new ArrayList<String>();

                            kitchen_id.add("" + 0);
                            kitchen_name.add("Select all");

               //             Toast.makeText(Search.this, "null kitchen", Toast.LENGTH_SHORT).show();
                        }
                        search_ktype_spinner.setAdapter(new Custom_Spinner_Adapter(kitchen_id,kitchen_name,R.layout.kitchen_spiner,R.id.kitchen_type_text,Search.this));
                        customProgressDialog.hide();
//                        int pos=search_ktype_spinner.getSelectedItemPosition();
                        query=""+search_sb.getQuery();
                        if(query.equals(""))
                        {
                            query="0";
                            load_search(type,myrating,"0","302012",query);
                        }else {
                            load_search(type, myrating, "" + 0, "0", query);
                        }
                      //  Toast.makeText(Search.this, ""+kitchen_name, Toast.LENGTH_SHORT).show();
                    }else {
                        //adding default item in spiner

                        kitchen_id = new ArrayList<String>();
                        kitchen_name = new ArrayList<String>();

                        kitchen_id.add("" + 0);
                        kitchen_name.add("Select all");

                        customProgressDialog.hide();
               //         Toast.makeText(Search.this, ""+response.errorBody(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FetchKitchenTypes> call, Throwable t) {
                customProgressDialog.hide();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v==search_find)
        {

            query=""+search_sb.getQuery();
            int pos=search_ktype_spinner.getSelectedItemPosition();
            if(query.equals(""))
            {
                query="0";
                load_search(type,myrating,kitchen_id.get(pos),"302012",query);
            }else {
                load_search(type, myrating, kitchen_id.get(pos), "0", query);
            }
      //      Toast.makeText(this, ""+query, Toast.LENGTH_SHORT).show();
        }

    }

    void load_search(String type,float myrating,String kitchen_id, String zipcode,String query)
    {
       final CustomProgressDialog customProgressDialog=new CustomProgressDialog(Search.this);
        Log.e("post values : ","type : "+type+"\n"+"rate : "+myrating+"\n"+"cate : "+kitchen_id+"\n"+"zipcode : "+zipcode+"\n"+"title : "+query);

        serverApi.search(type,myrating,kitchen_id,zipcode,query).enqueue(new Callback<FetchSearch>() {
            @Override
            public void onResponse(Call<FetchSearch> call, Response<FetchSearch> response) {
                if(response!=null)
                {
                    if (response.message().equalsIgnoreCase("ok"))
                    {
                        if(response.body().getSuccess().equals("1"))
                        {
                            ArrayList<String> stitle=new ArrayList<>();
                            ArrayList<String> simage=new ArrayList<>();
                            ArrayList<Integer> scat_id=new ArrayList<>();
                            ArrayList<String> stype=new ArrayList<>();
                            ArrayList<String> sdeliver=new ArrayList<>();
                            ArrayList<Float> srating=new ArrayList<>();

                            ArrayList<SearchProduct> searchProduct= (ArrayList<SearchProduct>) response.body().getSearchProduct();

                            for(SearchProduct sp:searchProduct)
                            {
                                stitle.add(sp.getName());
                                simage.add(sp.getImage());
                                scat_id.add(Integer.valueOf(sp.getRestorentid()));
                                stype.add(sp.getType());
                                sdeliver.add(sp.getDeliver());
                                srating.add(sp.getRating());
                            }
                            no_found_img.setVisibility(View.GONE);
                            search_recycler.setAdapter(new Home_inner_Items_Test_Adaptor(Search.this,Search.this,stitle,simage,scat_id,stype,sdeliver,srating));
                            customProgressDialog.hide();
                        }else {
//                            Snackbar.make(getWindow().getDecorView().getRootView(),"",Snackbar.LENGTH_SHORT).show();

                            no_found_img.setVisibility(View.VISIBLE);
                       //     Toast.makeText(Search.this, "No Related item found", Toast.LENGTH_SHORT).show();
                            customProgressDialog.hide();
                        }
                    }else {

                 //       Toast.makeText(Search.this, ""+response.errorBody(), Toast.LENGTH_SHORT).show();
                        customProgressDialog.hide();
                    }
                }
            }

            @Override
            public void onFailure(Call<FetchSearch> call, Throwable t) {
                customProgressDialog.hide();
                Snackbar.make(getWindow().getDecorView().getRootView(),"Something went wrong on server",Snackbar.LENGTH_SHORT).show();
           //     Toast.makeText(Search.this, "search error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* public void initilize_Dialogue(){
        contactdialog = new Dialog(Search.this);
        contactdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        contactdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ff1919")));
        bar = new ProgressBar(Search.this, null, android.R.attr.progressBarStyleLarge);
//bar.setProgress()
        bar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
        contactdialog.setContentView(bar);
        contactdialog.setCancelable(false);
    }

    public void show_dialogue()
    {

        contactdialog.show();
    }

    public void dismiss_dialogue()
    {
        contactdialog.dismiss();
    }*/

}
