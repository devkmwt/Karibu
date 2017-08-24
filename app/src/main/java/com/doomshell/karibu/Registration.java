package com.doomshell.karibu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.SharedPrefManager;
import com.doomshell.karibu.model.retrofit_model.RetroRegistrationPojo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    View convertview;
    Context context;
    Button facebooksignup, googlesingup, signup;
    TextView login;
    EditText edit_name,edit_email,edit_password,edit_mob,edit_cpassword;
    String appemail,appname,appmobile,apppassword, appcpassword;
    boolean isemail, isname,ismob, ispass, iscpass;

    ArrayList<String> list_name = new ArrayList<String>();
    ArrayList<String> list_phone = new ArrayList<String>();

    private CallbackManager callbackManager;
    LoginManager fbLoginManager;

    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN=100;
    ServerApi serverApi;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)  {
        context=getActivity().getApplicationContext();
        FacebookSdk.sdkInitialize(context);

        super.onCreate(savedInstanceState);
        convertview = inflater.inflate(R.layout.activity_registration, container, false);

       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         serverApi =retrofit.create(ServerApi.class);

        facebooksignup = (Button)convertview.findViewById(R.id.facbook_btn_rgstr);
        googlesingup = (Button)convertview. findViewById(R.id.google_btn_rgstr);
        login=(TextView)convertview.findViewById(R.id.login);

        edit_email = (EditText)convertview.findViewById(R.id.edit_email);
        edit_name = (EditText) convertview.findViewById(R.id.edit_name);
        edit_mob = (EditText) convertview.findViewById(R.id.edit_mobile);
        edit_password = (EditText) convertview.findViewById(R.id.edit_password);
        edit_cpassword = (EditText) convertview.findViewById(R.id.edit_con_pass);
        signup =(Button) convertview.findViewById(R.id.btn_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean ispass = false;

                appemail = edit_email.getText().toString().trim();
                appname = edit_name.getText().toString().trim();
                appmobile = edit_mob.getText().toString().trim();
                apppassword = edit_password.getText().toString().trim();
                appcpassword = edit_cpassword.getText().toString().trim();

                boolean b = validate(appemail);
                if (appemail.equals(null) || appemail.equals("")) {
                    edit_email.setError("Please fill email address");
                    isemail = false;
                    edit_email.setFocusable(true);
                } else if (!b) {

                    edit_email.setError("Please fill valid email address");
                    isemail = false;
                } else {
                    isemail = true;
                }
                if (appname.equals(null) || appname.equals("")) {
                    edit_name.setError("Please fill your name");
                    isname = false;
                    edit_name.setFocusable(true);

                } else {
                    isname = true;
                }
                if(appmobile.equals(null) || appmobile.equals("")){
                        edit_mob.setError("Please fill mobile number");
                        ismob = false;
                        edit_mob.setFocusable(true);
                    }else
                        {
                    ismob = true;
                }
                if (apppassword.equals("")) {
                    edit_password.setError("Please fill password field");
                    edit_password.setFocusable(true);

                } else if (apppassword.length() <= 3) {
                    edit_password.setError("Password to short");
                    edit_password.setFocusable(true);
                } else {
                    ispass = true;
                }
                if (appcpassword.equals("")) {
                    edit_cpassword.setError("Please confirm password ");
                    edit_cpassword.setFocusable(true);
                    iscpass=false;
                } else if (appcpassword.length() <= 3) {
                    edit_cpassword.setError("Password to short");
                    edit_cpassword.setFocusable(true);
                    iscpass=false;
                } else if(!appcpassword.equals(apppassword)){
                    edit_cpassword.setError("Password not match");
                    edit_cpassword.setFocusable(true);
                    iscpass=false;
                }else {
                    if(ispass) {
                        iscpass = true;
                    }else {
                        iscpass = false;
                        edit_password.setError("Password to short");
                        edit_password.setFocusable(true);
                    }
                }
                if (isemail && isname && ismob && iscpass) {

                    app_retro_registration(appemail,appname,appmobile,appcpassword,getActivity());
                }
            }
        });

        callbackManager = CallbackManager.Factory.create();
        fbLoginManager = LoginManager.getInstance();
        fbLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                // handleFacebookAccessToken(loginResult.getAccessToken());
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {
                                    // Insert your code here
                                    Toast.makeText(context, "" + object.getString("email"), Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e) {
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "cancle Error", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context,error.toString() , Toast.LENGTH_SHORT).show();
                Log.d("fb error", error.toString());
            }
        });

        facebooksignup.setOnClickListener(this);
        googlesingup.setOnClickListener(this);
        login.setOnClickListener(this);


      /*  GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() , .this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .build();*/

        return convertview;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
       /* if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        } else {

        }*/
    }

    private void handleSignInResult(GoogleSignInResult googleSignInResult) {
        Log.d( "handleSignInResult:", ""+ googleSignInResult.isSuccess());

        if(googleSignInResult.isSuccess())
        {
            GoogleSignInAccount account=googleSignInResult.getSignInAccount();
            Toast.makeText(context,  account.getEmail().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, account.getDisplayName().toString(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Login Failed with google",Toast.LENGTH_SHORT).show();
            Log.i("Error" , googleSignInResult.toString());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == facebooksignup)
        {
            fbLoginManager.logInWithReadPermissions(Registration.this, Arrays.asList("email"));
        }

        if(v == googlesingup)
        {
            signIn();
        }

        if(v == login ){

            Log_in log_in = new Log_in();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.first_container, log_in);
            fragmentTransaction.addToBackStack(log_in.getClass().getName());
            fragmentTransaction.commit();

        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(context, "Connection failed", Toast.LENGTH_SHORT).show();
    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    void app_retro_registration(String semail, String sname, String smobl, String spass, final Activity activity)
    {
        final Dialog contactdialog = new Dialog(activity);
        contactdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        contactdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ff1919")));
        ProgressBar bar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);
        bar.getIndeterminateDrawable().setColorFilter(activity.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        contactdialog.setContentView(bar);
        contactdialog.setCancelable(false);
        contactdialog.show();

        serverApi.user_registration(semail,sname,smobl,spass).enqueue(new Callback<RetroRegistrationPojo>() {
            @Override
            public void onResponse(Call<RetroRegistrationPojo> call, Response<RetroRegistrationPojo> response) {

                int success=response.body().getSuccess();
                if(success==1)
                {
                    contactdialog.dismiss();
                    Toast.makeText(activity, "Thanks for Registration", Toast.LENGTH_SHORT).show();
                   /* Toast.makeText(activity, ""+response.body().getUserid(), Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(activity.getApplicationContext()).saveUser_details("userid",response.body().getUserid());*/
                    Intent intent=new Intent(getActivity(),Home.class);
                    startActivity(intent);

                }
                else {
                    contactdialog.dismiss();
                    Toast.makeText(activity, "user alerady register", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetroRegistrationPojo> call, Throwable t) {
                contactdialog.dismiss();
                Log.e("retro reg",t.getMessage());
                Toast.makeText(activity, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
