package com.doomshell.karibu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.SharedPrefManager;
import com.doomshell.karibu.model.retrofit_model.Login;
import com.doomshell.karibu.model.retrofit_model.RetroLoginPojo;
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Log_in extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    EditText edit_login, edit_pass;
    TextView frgt, rgstr;
    Button singin;
    Button facebooklogin, googlelogin;
    String applogin,apppassword;
    String email, fname;
  //  View convertview;
    Context context;

    ServerApi serverApi;
    private CallbackManager callbackManager;
    LoginManager fbLoginManager;

    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN=100;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

    context=Log_in.this;
        Retrofit retrofit;
        FacebookSdk.sdkInitialize(context);
        // printHashKey();
     //   convertview = inflater.inflate(R.layout.activity_log_in, container, false);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);

        edit_login = (EditText)findViewById(R.id.edit_id);
        edit_pass = (EditText) findViewById(R.id.edit_pass);
        frgt = (TextView) findViewById(R.id.text_frgt);
        rgstr = (TextView)findViewById(R.id.text_rgstr);
        singin = (Button) findViewById(R.id.btn_signin);

        facebooklogin = (Button) findViewById(R.id.facbook_btn);
        googlelogin = (Button) findViewById(R.id.google_btn);

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
                                    email=object.getString("email");
                                    fname=object.getString("name");

                                    send_facebook_login();
                                    //Toast.makeText(context, "Welcome "+object.getString("name"), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(context, "" + object.getString("email"), Toast.LENGTH_SHORT).show();
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

        facebooklogin.setOnClickListener(this);
        googlelogin.setOnClickListener(this);
        rgstr.setOnClickListener(this);
        singin.setOnClickListener(this);

      /*googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() , Log_in.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .build();*/

      //  return convertview;
    }



    private void send_facebook_login() {








    }

    private void send_login(String applogin, String apppassword, final Activity activity) {

        final Dialog contactdialog = new Dialog(activity);
        contactdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        contactdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ff1919")));
        ProgressBar bar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);
        bar.getIndeterminateDrawable().setColorFilter(activity.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        contactdialog.setContentView(bar);
        contactdialog.setCancelable(false);

        contactdialog.show();

        serverApi.savePost(applogin, apppassword).enqueue(new Callback<RetroLoginPojo>() {
            @Override
            public void onResponse(Call<RetroLoginPojo> call, Response<RetroLoginPojo> response) {

                int sucess=response.body().getSuccess();
                if(sucess==1)
                {
                    String mob="";
                    if(response.body().getMobile()!=null)
                    {
                        mob= (String) response.body().getMobile();
                    }


                    SharedPrefManager.getInstance(context).saveUser_details("userid",response.body().getUserId().toString());
                    SharedPrefManager.getInstance(context).saveUser_details("emailid",response.body().getEmailId().toString());
                    SharedPrefManager.getInstance(context).saveUser_details("fname",response.body().getFirstName().toString());
                    SharedPrefManager.getInstance(context).saveUser_details("mobile",mob);
                    SharedPrefManager.getInstance(context).save_Login_status("islogin",true);

                    contactdialog.dismiss();
                    Toast.makeText(context, "Welcome "+response.body().getFirstName(), Toast.LENGTH_SHORT).show();
                   /* Intent intent=new Intent(context,Home.class);
                    startActivity(intent);*/

                    activity.finish();
                    /*activity.startActivity(getIntent());
                    activity.overridePendingTransition( 0, 0);*/
                }
                else
                {
                    contactdialog.dismiss();
                    Toast.makeText(activity, "No registered user", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RetroLoginPojo> call, Throwable t) {

                contactdialog.dismiss();
             //   Log.e("retro reg",t.getMessage());
                Toast.makeText(activity, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        /*if (requestCode == RC_SIGN_IN) {
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

    /*
    public void printHashKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.doomshell.karibu",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
            Log.d("print hash error", e.toString());
        }
    }
*/
    @Override
    public void onClick(View v) {

        if(v == singin){

            boolean islogin=false;
            boolean ispassword=false;

            applogin=edit_login.getText().toString();
            apppassword=edit_pass.getText().toString();

            boolean b = validate(applogin);
            if(applogin.equals(null) || applogin.equals(""))
            {
                edit_login.setError("Please fill email address");
                islogin=false;
                edit_login.setFocusable(true);
            }
            else if(!b){

                edit_login.setError("Please fill valid email address");
                islogin=false;
            }
            else{
                islogin=true;
            }
            if(apppassword.equals(""))
            {
                edit_pass.setError("Please fill password field");
                edit_pass.setFocusable(true);

            }else if(apppassword.length()<=3)
            {
                edit_pass.setError("Password to short");
                edit_pass.setFocusable(true);
            }
            else
            {
                ispassword=true;
            }
            if(islogin && ispassword)
            {
                send_login(applogin,apppassword, Log_in.this);
            }
        }
        if (v == facebooklogin)
        {
            fbLoginManager.logInWithReadPermissions(Log_in.this, Arrays.asList("email"));
        }

        if(v == googlelogin)
        {
            signIn();
        }

        if(v == rgstr)
        {
            Intent intent=new Intent(Log_in.this,Registration.class);
            startActivity(intent);
           /* Registration registration = new Registration();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.first_container, registration);
            fragmentTransaction.addToBackStack(registration.getClass().getName());
            fragmentTransaction.commit();*/
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


}
