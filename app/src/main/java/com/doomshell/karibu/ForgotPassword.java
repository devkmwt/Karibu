package com.doomshell.karibu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by vikas on 3/10/2017.
 */

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    Button reset;
    EditText edit_email;
    String appemail;

    TextView signup_textview,login_textview;

    AlertDialog alertDialog;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forgot_password);
        //FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
     //   View view=inflater.inflate(R.layout.fragment_forgot_password,container,false);
        edit_email=(EditText)findViewById(R.id.forgotemail);
        signup_textview=(TextView) findViewById(R.id.signup_textview);
        login_textview=(TextView) findViewById(R.id.login_textview);


        reset=(Button)findViewById(R.id.reset);

        reset.setOnClickListener(this);
        signup_textview.setOnClickListener(this);
        login_textview.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==signup_textview)
        {
            Intent intent=new Intent(ForgotPassword.this,Registration.class);
            startActivity(intent);
        }

        if(view==login_textview)
        {
          /*  Log_in firstpage_login=new Log_in();
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.first_container,firstpage_login);
            fragmentTransaction.addToBackStack(firstpage_login.getClass().getName());
            fragmentTransaction.commit();*/
            Intent intent=new Intent(ForgotPassword.this,Log_in.class);
            startActivity(intent);
        }

        if(view==reset)
        {
            boolean isemail=false;


            appemail=edit_email.getText().toString().trim();


            boolean b = validate(appemail);
            if(appemail.equalsIgnoreCase(null) || appemail.equals(""))
            {
                edit_email.setError("Please fill email address");
                isemail=false;
                edit_email.setFocusable(true);
            }else if(!b)
            {
                edit_email.setError("Please fill valid email address");
                isemail=false;
            }
            else
            {
                isemail=true;
            }

            if(isemail)
            {
                //App_pass_reset(appemail);
                // sendPost(email,password);

                //    AppLogin al=new AppLogin();
                //     al.execute();
            }
        }



    }
/*
    void App_pass_reset(String email)
    {
        serverApi.forgot_password(email, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                String s=new String(((TypedByteArray) response.getBody()).getBytes());
                try {
                    JSONObject jsonObject=new JSONObject(s);

                    int success=jsonObject.getInt("success");
                    if(success==1)
                    {

                      //  Toast.makeText(getActivity(),"Password Reset Link Sent successfully to Email.",Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater= getActivity().getLayoutInflater();
                        View alerview=inflater.inflate(R.layout.tnc_layout,null);
                        TextView dt=(TextView)alerview.findViewById(R.id._tnc);
                        dt.setText("Password Reset Link Sent successfully to Email  ");
                        builder.setView(alerview);


                        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog=builder.create();
                        alertDialog.show();

                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Sorry, Email Id Not Registered!" ,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Something went wrong on server", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
