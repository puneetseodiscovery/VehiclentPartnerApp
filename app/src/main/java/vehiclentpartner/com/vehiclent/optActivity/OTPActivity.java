package vehiclentpartner.com.vehiclent.optActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.Jobcompleted.JobCompletedActivity;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.location.LocationActivity;
import vehiclentpartner.com.vehiclent.services.TrackerServices;
import vehiclentpartner.com.vehiclent.upcommingDetails.UpCommingDetailsActivity;
import vehiclentpartner.com.vehiclent.util.Utility;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    OTPActivity context;

    @BindView(R.id.tvotp)
    TextView tvotp;

    @BindView(R.id.cardetails)
    TextView cardetails;

    @BindView(R.id.servicesdetails)
    TextView servicesdetails;

    @BindView(R.id.tv_otp)
    TextView tv_otp;

    @BindView(R.id.tv_getotp)
    TextView tv_getotp;

    @BindView(R.id.btn_track)
    Button btn_track;

    @BindView(R.id.btn_startJob)
    Button btn_startJob;

    @BindView(R.id.img_back_otp)
    ImageView img_back_otp;

    private static final int PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ButterKnife.bind(this);
        Initialization();
    }

    private void Initialization() {

        context = this;

        tvotp.setTypeface(Utility.typeFaceForBoldText(this));
        cardetails.setTypeface(Utility.typeFaceForBoldText(this));
        servicesdetails.setTypeface(Utility.typeFaceForBoldText(this));
        tv_otp.setTypeface(Utility.typeFaceForBoldText(this));
        tv_getotp.setTypeface(Utility.typeFaceForBoldText(this));
        EventListner();
    }

    private void EventListner() {

        btn_track.setOnClickListener(this);
        btn_startJob.setOnClickListener(this);
        img_back_otp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_otp:
                finish();
                break;
            case R.id.btn_track:
                if (Utility.isNetworkConnected(context))
                    enableGPS();
                else
                    Toast.makeText(context, "No Network Connection", Toast.LENGTH_SHORT).show();

                /*Uri gmmIntentUri = Uri.parse("geo:30.7154,76.6913");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                    startActivity(mapIntent);
                }*/
                break;
            case R.id.btn_startJob:
                Intent intentstartJob = new Intent(OTPActivity.this, JobCompletedActivity.class);
                startActivity(intentstartJob);
                break;
        }
    }

    private void enableGPS() {
        Log.e("enableGPS", "working");
        // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable location services", Toast.LENGTH_SHORT).show();
        } else {
            checkPermissions();
        }
    }

    private void checkPermissions() {
        Log.e("checkPermissions", "working");
        // Check location permission is granted - if it is, start the service, otherwise request the permission
        /*int permission1 = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);

        int permission2 = ContextCompat.checkSelfPermission(context,
                Manifest.permission.FOREGROUND_SERVICE);

        if (permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED) {
            Log.e("granted", "working");
            startTrackerService();
        } else {
            Log.e("Not granted", "working");
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.FOREGROUND_SERVICE},
                    PERMISSIONS_REQUEST);
        }*/

        int permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService();
        } else {
            ActivityCompat.requestPermissions((OTPActivity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean p1 = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        boolean p2 = grantResults[1] == PackageManager.PERMISSION_GRANTED;
        Log.e("requestCode", "" + requestCode);
        Log.e("grantResults len", "" + grantResults.length);
        Log.e("p1", "" + p1);
        Log.e("p2", "" + p2);
        if (requestCode == PERMISSIONS_REQUEST && grantResults.length == 2
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            // Start the service when the permission is granted

            startTrackerService();
        }
    }

    private void startTrackerService() {
        Log.e("startTrackerService", "working");
        startService(new Intent(this, TrackerServices.class));
        // finish();
    }
}
