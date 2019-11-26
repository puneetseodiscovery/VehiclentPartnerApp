package vehiclentpartner.com.vehiclent.optActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vehiclentpartner.com.vehiclent.Jobcompleted.JobCompletedActivity;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.chatAcitvity.ChatActivity;
import vehiclentpartner.com.vehiclent.congratulations.CongratulationActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingDetailsResponseModel;
import vehiclentpartner.com.vehiclent.trackerServices.TrackerService;
import vehiclentpartner.com.vehiclent.util.Constant;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;

public class OTPActivity extends BaseClass implements View.OnClickListener, IOTPActivity, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    OTPActivity context;

    @BindView(R.id.tvotp)
    TextView tvotp;

    @BindView(R.id.cardetails)
    TextView cardetails;

    @BindView(R.id.tv_cardetails)
    TextView tv_cardetails;

    @BindView(R.id.servicesdetails)
    TextView servicesdetails;

    @BindView(R.id.tv_abousservices)
    TextView tv_abousservices;

    @BindView(R.id.tv_otp)
    TextView tv_otp;

    @BindView(R.id.tv_service_price)
    TextView tv_service_price;

    @BindView(R.id.tv_getotp)
    TextView tv_getotp;

    @BindView(R.id.btn_track)
    Button btn_track;

    @BindView(R.id.btn_startJob)
    Button btn_startJob;

    @BindView(R.id.img_back_otp)
    ImageView img_back_otp;

    @BindView(R.id.edit01)
    EditText edit01;

    @BindView(R.id.edit02)
    EditText edit02;

    @BindView(R.id.edit03)
    EditText edit03;

    @BindView(R.id.edit04)
    EditText edit04;

    @BindView(R.id.relative_layout)
    RelativeLayout relative_layout;

    private static final int PERMISSIONS_REQUEST = 1;
    SavePref savePref;
    String user_id = "", service_id = "", Latitude = "", text = "", service_name = "", partner_id = "", job_id = "";
    String otp = "",title = "", Partner_Id = "", Longitude = "", currentAddress = "",first_name = "", last_name = "", email = "";
    String phone_number = "", profile_pic = "", address = "";

    ProgressDialog progressDialog;
    IPOTPAcitivty ipotpAcitivty;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_useraddress)
    TextView tv_useraddress;

    @BindView(R.id.service_price)
    TextView service_price;

    @BindView(R.id.tv_usercontact)
    TextView tv_usercontact;

    @BindView(R.id.img_user)
    CircleImageView img_user;

    @BindView(R.id.tv_useremail)
    TextView tv_useremail;

    @BindView(R.id.img_chat)
    ImageView img_chat;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    String placeCurrentLatitude = "";
    String placeCurrentLongitude = "";

    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ButterKnife.bind(this);
        context = OTPActivity.this;
        savePref = new SavePref(this);
        ipotpAcitivty = new POTPActivity(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        Initialization();

    }

    private void Initialization() {

        partner_id = savePref.getid();

        progressDialog = new ProgressDialog(this);
        user_id = getIntent().getStringExtra("user_id");
        text = getIntent().getStringExtra("text");
        service_id = getIntent().getStringExtra("service_id");
        Latitude = getIntent().getStringExtra("Latitude");
        Longitude = getIntent().getStringExtra("Longitude");
        service_name = getIntent().getStringExtra("service_name");
        job_id = getIntent().getStringExtra("job_id");
        title = getIntent().getStringExtra("title");
        partner_id = getIntent().getStringExtra("partner_id");
        currentAddress = getIntent().getStringExtra("currentAddress");
        first_name = getIntent().getStringExtra("first_name");
        last_name = getIntent().getStringExtra("last_name");
        email = getIntent().getStringExtra("email");
        phone_number = getIntent().getStringExtra("phone_number");
        profile_pic = getIntent().getStringExtra("profile_pic");
        address = getIntent().getStringExtra("address");

        tvotp.setTypeface(Utility.typeFaceForBoldText(this));
        cardetails.setTypeface(Utility.typeFaceForBoldText(this));
        servicesdetails.setTypeface(Utility.typeFaceForBoldText(this));
        service_price.setTypeface(Utility.typeFaceForBoldText(this));
        tv_otp.setTypeface(Utility.typeFaceForBoldText(this));
        tv_getotp.setTypeface(Utility.typeFaceForBoldText(this));
        tv_abousservices.setText(text);
        tv_cardetails.setText(service_name);
        tv_username.setText(first_name + " " + last_name);
        tv_useremail.setText(email);

        if (currentAddress == null || currentAddress.equals("")) {
            tv_useraddress.setText(address);
        } else {
            tv_useraddress.setText(currentAddress);
        }

        tv_abousservices.setText(text);
        tv_usercontact.setText(phone_number);
        tv_cardetails.setText(service_name);

       /* if (profile_pic.isEmpty() || profile_pic==null) {
            Glide.with(context).load(R.drawable.no_image)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image).into(img_user);
        } else {
            Glide.with(context).load(profile_pic)
                    .error(R.drawable.no_image).into(img_user);
        }*/

        //enableGPS();
        if (Utility.isNetworkConnected(context)) {

            progressDialog = Utility.showLoader(context);
            ipotpAcitivty.doUpCommingDetails(job_id);


        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

        EventListner();
    }

    private void EventListner() {

        btn_track.setOnClickListener(this);
        btn_startJob.setOnClickListener(this);
        img_back_otp.setOnClickListener(this);
        img_chat.setOnClickListener(this);
        tv_usercontact.setOnClickListener(this);

        edit01.setOnClickListener(this);
        edit02.setOnClickListener(this);
        edit03.setOnClickListener(this);
        edit04.setOnClickListener(this);


        sb=new StringBuilder();

        edit01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edit01.getText().toString().trim().length() == 1) {

                    edit02.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null && s.length()>1){
                    edit01.setText(s.subSequence(1, s.length()));
                    edit01.setSelection(1);
                }


            }
        });

        edit02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edit02.getText().toString().trim().length() == 1) {
                    edit03.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null && s.length()>1){
                    edit02.setText(s.subSequence(1, s.length()));
                    edit02.setSelection(1);
                }

            }
        });

        edit03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edit03.getText().toString().trim().length() == 1) {
                    edit04.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null && s.length()>1){
                    edit03.setText(s.subSequence(1, s.length()));
                    edit03.setSelection(1);
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


    @Override
    protected void onResume() {
        super.onResume();


        mGoogleApiClient.connect();
        if (!isGpsOn())
            showGPSDisabledAlertToUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_otp:
                finish();
                break;

            case R.id.tv_usercontact:
                callPhoneNumber();
                break;

            case R.id.img_chat:

                firebaseChatWith = user_id;
                Constant.user_name = first_name + " " + last_name;

                Intent intent = new Intent(OTPActivity.this, ChatActivity.class);
                intent.putExtra("firebaseChatWith", firebaseChatWith);
                intent.putExtra("partner_id", partner_id);
                intent.putExtra("user_name", first_name + " " + last_name);
                intent.putExtra("service_name", service_name);
                startActivity(intent);

                break;

            case R.id.btn_track:


                String uri = "http://maps.google.com/maps?saddr=" + placeCurrentLatitude + "," + placeCurrentLongitude + "&daddr=" + Latitude + "," + Longitude;
                Intent intentmap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intentmap.setPackage("com.google.android.apps.maps");
                startActivity(intentmap);

                break;
            case R.id.btn_startJob:
                if (Utility.isNetworkConnected(context))
                    validationsOnOtp();
                else
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions(OTPActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_number));
                startActivity(callIntent);

            } else {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone_number));
                startActivity(callIntent);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void validationsOnOtp() {

        if (edit01.getText().toString().trim().isEmpty() || edit01.getText().toString().trim().equals("") &&
                edit02.getText().toString().trim().isEmpty() || edit02.getText().toString().trim().equals("") &&
                edit03.getText().toString().trim().isEmpty() || edit03.getText().toString().trim().equals("") &&
                edit04.getText().toString().trim().isEmpty() || edit04.getText().toString().trim().equals("")) {

            Snackbar snackbar = Snackbar
                    .make(relative_layout, "Please enter valid OTP", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(relative_layout, "", Snackbar.LENGTH_SHORT);
                            snackbar1.dismiss();
                        }
                    });

            snackbar.show();

        } else {
            otp = edit01.getText().toString() + edit02.getText().toString() + edit03.getText().toString() + edit04.getText().toString();
            //  Toast.makeText(context, "OTP" + otp, Toast.LENGTH_SHORT).show();
            progressDialog = Utility.showLoader(OTPActivity.this);
            ipotpAcitivty.doOTPMatch(job_id, otp);
        }
    }

    private void enableGPS() {
        Log.e("enableGPS", "working");
        // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable location services", Toast.LENGTH_SHORT).show();
        } else {
            //  checkPermissions();
        }
    }
    private void checkPermissions() {

        int permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            // startTrackerService();
        } else {

            ActivityCompat.requestPermissions((OTPActivity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }
    }

    private void startTrackerService() {

        startService(new Intent(context, TrackerService.class));
        //finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_REQUEST && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            //startTrackerService();
        } else if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            } else {
            }
        } else {

            finish();
        }
    }

    @Override
    public void onOTPResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onOTPSucessResponseFromPresenter(MatchOTPResponse matchOTPResponse) {

        progressDialog.dismiss();
        Intent intentstartJob = new Intent(OTPActivity.this, JobCompletedActivity.class);
        intentstartJob.putExtra("job_id", job_id);
        startActivity(intentstartJob);

    }

    @Override
    public void onOTPFaildResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpCommingJobDetailsResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpCommingJobDetailsFromPresenter(UpCommingDetailsResponseModel upCommingDetailsResponseModel) {
        progressDialog.dismiss();
        if (upCommingDetailsResponseModel.getData().get(0).getYourProfile() == null) {
            Glide.with(context).load(R.drawable.no_image).error(R.drawable.no_image)
                    .into(img_user);
        } else {
            Glide.with(context).load(upCommingDetailsResponseModel.getData().get(0).getYourProfile())
                    .error(R.drawable.no_image)
                    .into(img_user);
        }

        tv_username.setText(upCommingDetailsResponseModel.getData().get(0).getFirstName()+" "+upCommingDetailsResponseModel.getData().get(0).getLastName());
        tv_abousservices.setText(upCommingDetailsResponseModel.getData().get(0).getMessage());
        tv_cardetails.setText(upCommingDetailsResponseModel.getData().get(0).getServiceName());
        tv_useraddress.setText(upCommingDetailsResponseModel.getData().get(0).getAddress());
        tv_useremail.setText(upCommingDetailsResponseModel.getData().get(0).getEmail());
        tv_usercontact.setText(upCommingDetailsResponseModel.getData().get(0).getPhoneNumber());
        tv_service_price.setText("\u20B9" + " " + upCommingDetailsResponseModel.getData().get(0).getPrice());

        if (upCommingDetailsResponseModel.getData().get(0).getOtpMatch().equals("0") || upCommingDetailsResponseModel.getData().get(0).getOtpMatch().contains("0")) {
            Snackbar snackbar = Snackbar
                    .make(relative_layout, "Please Enter OTP Here", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(relative_layout, "", Snackbar.LENGTH_SHORT);
                            snackbar1.dismiss();
                        }
                    });
            snackbar.show();
        } else {
            Intent intent = new Intent(context, CongratulationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onUpCommingJobDetailsFromPresenter(String message) {
        progressDialog.dismiss();
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
}
