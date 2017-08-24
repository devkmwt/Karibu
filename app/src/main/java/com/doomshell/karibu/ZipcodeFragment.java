package com.doomshell.karibu;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doomshell.karibu.helper.Const_Permision_Codes;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.retrofit_model.for_zip.AddressComponent;
import com.doomshell.karibu.model.retrofit_model.for_zip.Result;
import com.doomshell.karibu.model.retrofit_model.for_zip.RetroZipcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZipcodeFragment extends Fragment implements View.OnClickListener,LocationListener {

    Location location;
    LocationManager locationManager;
    String provider;

    EditText edit_postalcode;
    Button postalcode_submit_btn;
    TextView zip_login_text;
    View convertview;

    Retrofit restAdapter_google;
    ServerApi serverApi_google;
    String BaseUrl_forgoogle = "";
    boolean in_postalScreen;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context=getActivity().getApplicationContext();
        convertview = inflater.inflate(R.layout.fragment_zipcode, container, false);
        postalcode_submit_btn = (Button) convertview.findViewById(R.id.postalcode_submit_btn);
        zip_login_text = (TextView) convertview.findViewById(R.id.zip_skip_text);
        edit_postalcode = (EditText) convertview.findViewById(R.id.edit_postalcode);
       // postalcode_submit_btn.setOnClickListener(this);
        zip_login_text.setOnClickListener(this);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);


        return convertview;
       // return inflater.inflate(R.layout.fragment_zipcode, container, false);
    }

    @Override
    public void onClick(View v) {

        /*if (v == postalcode_submit_btn) {

            if (!edit_postalcode.getText().toString().equals("")) {
               // verify_postal(edit_postalcode.getText().toString());

            } else {
                Toast.makeText(getActivity(), "You need to fill postal code", Toast.LENGTH_SHORT).show();
            }
        }*/
        if (v == zip_login_text) {

            Intent intent=new Intent(getActivity(),Home.class);
            startActivity(intent);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        float lat = (float) (location.getLatitude());
        float longi = (float) (location.getLongitude());

    //    Toast.makeText(context, ""+lat+"\n"+longi, Toast.LENGTH_SHORT).show();
        get_Zip_from_Google(""+lat+","+longi);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(provider!=null) {
            location = locationManager.getLastKnownLocation(provider);
        }
        int myAPI = Build.VERSION.SDK_INT;

        if (myAPI >= 23) {
            int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION);
            int result1 = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION);

            if (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED) {
                //  AppRegistration fc = new AppRegistration();
                //   fc.execute(101);
                //  location = locationManager.getLastKnownLocation(provider);

                if (location != null) {
                    onLocationChanged(location);
                    locationManager.requestLocationUpdates(provider, 4000, 1, ZipcodeFragment.this);

                } else {

                    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    provider = locationManager.getBestProvider(criteria, false);

                    locationManager.requestLocationUpdates(provider, 4000, 1, ZipcodeFragment.this);

                    //       Toast.makeText(getActivity().getApplicationContext(), "ok resume", Toast.LENGTH_SHORT).show();

                    //       Toast.makeText(getActivity().getApplicationContext(), "Please turn on GPS to locate", Toast.LENGTH_LONG).show();
                  /*  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);*/
                }


            } else {

           /*     ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                        Const_Permision_Codes.zipcode_Multi);
*/
                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        && !ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) )
                {
               /*     ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                            Const_Permision_Codes.zipcode_Multi);*/
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                            Const_Permision_Codes.zipcode_Multi);

                    //     Toast.makeText(activity, "Microphone permission needed for recording. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please give permission from settings for getting automatically enter postal code", Toast.LENGTH_SHORT).show();
                }

            }
        } else {

            if(provider!=null) {
                location = locationManager.getLastKnownLocation(provider);
              //  locationManager.requestLocationUpdates(provider, 4000, 1, ZipcodeFragment.this);
            }else {
                Toast.makeText(getActivity().getApplicationContext(), "Please turn on GPS to locate", Toast.LENGTH_LONG).show();
            }

            if (location != null) {
                onLocationChanged(location);
                locationManager.requestLocationUpdates(provider, 4000, 1, ZipcodeFragment.this);

            } /*else {

                Toast.makeText(getActivity().getApplicationContext(), "Please turn on GPS to locate", Toast.LENGTH_LONG).show();
                  *//*  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);*//*
            }*/


        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void get_Zip_from_Google(String term)
    {
     //   in_postalScreen=true;
        Auto_async auto_async=new Auto_async();
        auto_async.execute(term);

    }


    class Auto_async extends AsyncTask<String, Integer, Integer>
    {
        String postal;
        final ArrayList<String> arr=new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            BaseUrl_forgoogle= "https://maps.googleapis.com";
            //  BaseUrl="https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+ URLEncoder.encode(args[0].toString(),"UTF-8");

            restAdapter_google = new Retrofit.Builder()
                    .baseUrl(BaseUrl_forgoogle)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            serverApi_google =restAdapter_google.create(ServerApi.class);
            // arr.clear();
        }

        @Override
        protected Integer doInBackground(String... params) {

            String term=params[0];

           // String key="AIzaSyDCp18wScM3CmolZKvW_HAIs0bCLYqkrp0";

            serverApi_google.get_zip_google(term,"en").enqueue(new Callback<RetroZipcode>() {
                @Override
                public void onResponse(Call<RetroZipcode> call, Response<RetroZipcode> response) {
                   String msg=response.body().getStatus();
                //    Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
                    if(msg.equalsIgnoreCase("ok"))
                    {
                        RetroZipcode retroZipcode=response.body();
                                           String status= retroZipcode.getStatus();
                  //               Toast.makeText(context, ""+status, Toast.LENGTH_SHORT).show();
                    ArrayList<Result> res_List= (ArrayList<Result>) retroZipcode.getResults();
                    for(Result res: res_List)
                    {

                        for(int i=0;i<res.getTypes().size();i++) {
                            if(res.getTypes().get(i).equalsIgnoreCase("postal_code")) {
                                AddressComponent addressComponent=res.getAddressComponents().get(i);

                                ArrayList<String> type= (ArrayList<String>) addressComponent.getTypes();

                                for(int t=0;t<type.size();t++)
                                {
                                    if(type.get(i).equalsIgnoreCase("postal_code"))
                                    {
                                        postal=addressComponent.getLongName();
                                        edit_postalcode.setText(postal);
                                  //      Toast.makeText(context, ""+postal, Toast.LENGTH_SHORT).show();

                                    }
                                }


                            }
                        }
                    }
                    }

                }

                @Override
                public void onFailure(Call<RetroZipcode> call, Throwable t) {
                    Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            // final Place_AutocompleteAdapter adapter = new Place_AutocompleteAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line);


            //    Toast.makeText(Product_detail_Activity.this, "added ok ", Toast.LENGTH_SHORT).show();

        }
    }

}
