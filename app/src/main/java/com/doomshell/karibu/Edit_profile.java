package com.doomshell.karibu;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.SharedPrefManager;
import com.doomshell.karibu.model.retrofit_model.RetroEdiproPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Edit_profile extends Fragment implements View.OnClickListener{

    AlertDialog optionalDialogue;
    int gallery_code=101;
    int cameracode=102;

    EditText fname,lname,mobile;
    ImageView profileimgage;
    Button save;
    String semail,sname,smobile;
    ServerApi serverApi;
    Context context;
    View convertview;

    boolean isfname=false,islname=false,ismob=false,isfile=false,uploaded=false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Retrofit retrofit;
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.fragment_edit_profile, container, false);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);

        fname=(EditText)convertview.findViewById(R.id.edit_fname);
        mobile=(EditText)convertview.findViewById(R.id.edit_mobno);
        save=(Button)convertview.findViewById(R.id.save_details);
        savedInstanceState=getArguments();
       // semail=""+savedInstanceState.getString("emailid");
        sname=""+savedInstanceState.getString("firsname");
        smobile=""+savedInstanceState.getString("mobile");

        save.setOnClickListener(this);
        uploaded=true;

      //if(semail.equalsIgnoreCase("null") || semail.equals("")){semail="Not Updated";}
        if(sname.equalsIgnoreCase("null") || sname.equals("")){sname="Not Updated";}
        if(smobile.equalsIgnoreCase("null") || smobile.equals("")){smobile="Not Updated";}

        fname.setText(sname);
        mobile.setText(smobile);

        return convertview;
    }

    @Override
    public void onClick(View v)
    {
       /* if (v==profileimgage)
        {
            show_optional_dialogue();
            uploaded=false;
        }*/
        if(v==save){

            edit_profile(sname,smobile);
        }
    }
    private void edit_profile(String sfname, String smobile) {

        serverApi.editprofile(SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUser_details("userid"),sfname, smobile).enqueue(new Callback<RetroEdiproPojo>() {
            @Override
            public void onResponse(Call<RetroEdiproPojo> call, Response<RetroEdiproPojo> response) {

                int sucess=response.body().getSuccess();

                if(sucess==1){

                SharedPrefManager.getInstance(getActivity().getApplicationContext()).saveUser_details("userid",response.body().getUserId().toString());
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).saveUser_details("emailid",response.body().getEmailId().toString());
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).saveUser_details("fname",response.body().getFirstName().toString());
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).saveUser_details("mobile",response.body().getMobile().toString());

                Intent intent = new Intent(context, Home.class);
                getActivity().finish();
                startActivity(intent);
            }
            else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetroEdiproPojo> call, Throwable t) {

                Toast.makeText(getActivity(), "not update", Toast.LENGTH_SHORT).show();
            }
        });
    }
/*

    public void show_optional_dialogue()  {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(),R.style.myDialog));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.image_option_dialogue, null);
        dialogBuilder.setView(dialogView);

        LinearLayout camera,other;
        ImageButton cancel;
        camera=(LinearLayout)dialogView.findViewById(R.id.post_camera_layout);
        other=(LinearLayout)dialogView.findViewById(R.id.post_other_layout);
        cancel=(ImageButton) dialogView.findViewById(R.id.remove);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionalDialogue.dismiss();
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryintent, gallery_code);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, cameracode);
                optionalDialogue.dismiss();
            }
        });

        optionalDialogue = dialogBuilder.create();
        optionalDialogue.show();
    }

    */
}
