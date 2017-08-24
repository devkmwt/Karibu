package com.doomshell.karibu;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doomshell.karibu.model.SharedPrefManager;

public class Profile extends Fragment implements View.OnClickListener {

    Context context;
    View convertview;
    TextView profile_change;

    TextView profile_email,profile_firstname,profile_mobile;
    String userid,email,fname,mobl;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.activity_profile, container, false);

        profile_email=(TextView)convertview.findViewById(R.id.editp_id);
        profile_firstname=(TextView)convertview.findViewById(R.id.editp_name);
        profile_mobile=(TextView)convertview.findViewById(R.id.editp_mob);

        profile_change=(TextView)convertview.findViewById(R.id.profile_edit);


        if(SharedPrefManager.getInstance(context).getLogin_status("islogin")) {
            userid = SharedPrefManager.getInstance(context).getUser_details("userid");
            email = SharedPrefManager.getInstance(context).getUser_details("emailid");
            fname = SharedPrefManager.getInstance(context).getUser_details("fname");
            mobl = SharedPrefManager.getInstance(context).getUser_details("mobile");

            if (email.equalsIgnoreCase("null") || email == null) {
                email = "";
            }
            if (fname.equalsIgnoreCase("null") || fname == null) {
                fname = "";
            }
            if (mobl.equalsIgnoreCase("null") || mobl == null) {
                mobl = "";
            }

            profile_email.setText(email);
            profile_firstname.setText(fname);
            profile_mobile.setText(mobl);

            profile_change.setOnClickListener(this);
        }else {
            profile_email.setText("Please Login First");
            profile_firstname.setText("Please Login First");
            profile_mobile.setText("Please Login First");

            profile_change.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
            profile_change.setOnClickListener(null);
        }
        return convertview;
    }

    @Override
    public void onClick(View v) {

        if(v==profile_change)
        {
            Edit_profile editProfile=new Edit_profile();
            Bundle bundle=new Bundle();
           // bundle.putString("emailid",profile_email.getText().toString());
            bundle.putString("firsname",profile_firstname.getText().toString());
            bundle.putString("mobile",profile_mobile.getText().toString());
            editProfile.setArguments(bundle);
            FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.home_container,editProfile);
            transaction.addToBackStack(editProfile.getClass().getName().toString());
            transaction.commit();
        }
    }
}
