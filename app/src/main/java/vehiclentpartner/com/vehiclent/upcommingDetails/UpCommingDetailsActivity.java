package vehiclentpartner.com.vehiclent.upcommingDetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
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
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.optActivity.OTPActivity;
import vehiclentpartner.com.vehiclent.services.TrackerServices;
import vehiclentpartner.com.vehiclent.util.Utility;

public class UpCommingDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_upcomming)
    TextView tv_upcomming;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.cardetails)
    TextView cardetails;

    @BindView(R.id.servicesdetails)
    TextView servicesdetails;

    @BindView(R.id.tv_accept)
    TextView tv_accept;

    @BindView(R.id.tv_reject)
    TextView tv_reject;

    @BindView(R.id.btn_accept)
    Button btn_accept;

    @BindView(R.id.btn_reject)
    Button btn_reject;

    @BindView(R.id.img_back_upcomming)
    ImageView img_back_upcomming;

    UpCommingDetailsActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_comming_details);

        ButterKnife.bind(this);
        Initialization();


      /*  // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Check location permission is granted - if it is, start
        // the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }
*/

    }

    private void Initialization() {

        tv_upcomming.setTypeface(Utility.typeFaceForBoldText(this));
        tv_username.setTypeface(Utility.typeFaceForBoldText(this));
        cardetails.setTypeface(Utility.typeFaceForBoldText(this));
        servicesdetails.setTypeface(Utility.typeFaceForBoldText(this));

        EventListner();

    }



    private void EventListner() {
        img_back_upcomming.setOnClickListener(this);
        btn_accept.setOnClickListener(this);
        btn_reject.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_upcomming:
                finish();
                break;
            case R.id.btn_accept:
                Intent intent = new Intent(UpCommingDetailsActivity.this, OTPActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_reject:
                finish();
                break;
        }
    }


}
