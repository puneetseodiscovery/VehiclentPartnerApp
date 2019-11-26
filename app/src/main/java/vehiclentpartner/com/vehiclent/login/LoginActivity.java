package vehiclentpartner.com.vehiclent.login;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.home.homeActivity.Home;
import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;

public class LoginActivity extends BaseClass implements ILoginActivity,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_pass)
    TextView tv_pass;

    @BindView(R.id.tv_forgot)
    TextView tv_forgot;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.et_username)
    EditText et_username;

    @BindView(R.id.et_pass)
    EditText et_pass;

    @BindView(R.id.img_eyes)
    ImageView img_eyes;

    LoginActivity context;

    IPLogin interfacePresenterLogin;
    ProgressDialog progressDialog;
    SavePref savePref;
    boolean passVisible = false;
    String get_DeviceToken = "", get_userID = "", password = "";


    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    String placeCurrentLatitude = "";
    String placeCurrentLongitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        init();

    }

    public void init() {
        savePref = new SavePref(this);
        get_DeviceToken = savePref.getDeviceToken(context, "device_token");


        get_userID = savePref.getid();
        interfacePresenterLogin = new PLogin(this);
        tv_forgot.setTypeface(Utility.typeFaceForBoldText(this));
        img_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pass.getText().toString().trim().isEmpty()) {

                    Toast.makeText(context, "Noting to show", Toast.LENGTH_SHORT).show();
                } else {

                    if (!passVisible) {
                        et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        et_pass.setSelection(et_pass.length());
                        img_eyes.setImageResource(R.drawable.ic_visibility_off);
                        passVisible = true;

                    } else {
                        et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        et_pass.setSelection(et_pass.length());
                        img_eyes.setImageResource(R.drawable.ic_visibility_black);
                        passVisible = false;

                    }

                }
            }
        });
    }

    private boolean isGpsOn() {

        boolean isGpsOn = false;

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isGpsOn = false;

        } else {

            isGpsOn = true;

        }

        return isGpsOn;
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(getResources().getString(R.string.gps_disable))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.turn_on),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                                dialog.cancel();
                            }
                        });
        /*alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    @OnClick(R.id.login)
    public void onViewClicked() {

        if (Utility.isNetworkConnected(context)) {

            if (et_username.getText().toString().length() > 0 && et_pass.getText().toString().length() > 0) {


                if (Utility.validEmail(et_username.getText().toString().trim())) {

                    progressDialog = Utility.showLoader(LoginActivity.this);
                    interfacePresenterLogin.doLogin(et_username.getText().toString().trim(),
                            et_pass.getText().toString().trim(), get_DeviceToken, placeCurrentLatitude, placeCurrentLongitude);

                } else {
                    et_username.setError("Enter a valid email.");
                    et_username.requestFocus();
                }
            } else {
                if (et_username.getText().toString().length() == 0 && et_pass.getText().toString().length() == 0) {
                    et_username.setError("Enter a valid email.");
                    et_pass.setError("Enter password");
                    et_username.requestFocus();
                } else if (et_pass.getText().toString().length() == 0) {
                    et_pass.setError("Enter password");
                    et_pass.requestFocus();
                } else if (et_username.getText().toString().length() == 0) {
                    et_username.setError("Enter a valid email.");
                    et_username.requestFocus();
                }
                //Toast.makeText(this, "Enter correct login details.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /* Methods implement form login interface*/
    @Override
    public void onLoginResponseFromPresenter(int statusValue) {

        // Toast.makeText(this, "" + statusValue, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginSuccessFromPresenter(LoginResponseModel loginResponseModel) {

        progressDialog.dismiss();
        savePref.setStatus(loginResponseModel.getData().get(0).getStatus());
        savePref.setid(loginResponseModel.getData().get(0).getId());
        savePref.setuser_name(loginResponseModel.getData().get(0).getUserName());
        savePref.setuser_email(loginResponseModel.getData().get(0).getEmail());
        savePref.setuser_email(loginResponseModel.getData().get(0).getEmail());

        firebaseRegister();
        firebaseLogin();

        Intent intent = new Intent(LoginActivity.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    private void firebaseRegister() {

        password = et_pass.getText().toString();
        String url = "https://vehiclent.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase("https://vehiclent.firebaseio.com/users");

                if (s.equals("null")) {
                    reference.child(savePref.getid()).child("password").setValue(password);
                    //  Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(savePref.getid())) {
                            reference.child(savePref.getid()).child("password").setValue(password);
                            //      Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            //       Toast.makeText(LoginActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);

    }

    private void firebaseLogin() {

        get_userID = savePref.getid();
        password = et_pass.getText().toString();
        String url = "https://vehiclent.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.equals("null")) {
                    //    Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(get_userID)) {
                            //    Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                        } else if (obj.getJSONObject(get_userID).getString("password").equals(password)) {
                            firebaseUsername = get_userID;
                            firebasePassword = password;

                        } else {
                            //     Toast.makeText(LoginActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);
    }

    @Override
    public void onLoginFailedFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            //If everything went fine lets get latitude and longitude


            if (String.valueOf(location.getLatitude()).isEmpty()) {

            } else {

                placeCurrentLatitude = String.valueOf(location.getLatitude());
                placeCurrentLongitude = String.valueOf(location.getLongitude());

                if (placeCurrentLatitude.isEmpty()) {

                } else {


                }

            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    protected void onResume() {
        super.onResume();


        mGoogleApiClient.connect();
        if (!isGpsOn())
            showGPSDisabledAlertToUser();
    }
}
