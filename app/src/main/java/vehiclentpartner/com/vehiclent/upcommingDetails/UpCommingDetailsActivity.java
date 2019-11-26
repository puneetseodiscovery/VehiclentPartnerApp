package vehiclentpartner.com.vehiclent.upcommingDetails;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.chatAcitvity.ChatActivity;
import vehiclentpartner.com.vehiclent.home.homeActivity.Home;
import vehiclentpartner.com.vehiclent.optActivity.OTPActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.AcceptRejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.RejectResponseModel;
import vehiclentpartner.com.vehiclent.trackerServices.TrackerService;
import vehiclentpartner.com.vehiclent.util.Constant;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;
public class UpCommingDetailsActivity extends BaseClass implements View.OnClickListener, IUpCommingDetailsActivity {

    @BindView(R.id.tv_upcomming)
    TextView tv_upcomming;

    @BindView(R.id.img_user)
    CircleImageView img_user;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_useremail)
    TextView tv_useremail;

    @BindView(R.id.tv_usercontact)
    TextView tv_usercontact;

    @BindView(R.id.tv_useraddress)
    TextView tv_useraddress;

    @BindView(R.id.tv_abousservices)
    TextView tv_abousservices;

    @BindView(R.id.cardetails)
    TextView cardetails;

    @BindView(R.id.tv_cardetails)
    TextView tv_cardetails;

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

    @BindView(R.id.img_chat)
    ImageView img_chat;

    UpCommingDetailsActivity context;
    SavePref savePref;

    String user_id = "", service_id = "", Latitude = "", Longitude = "";
    String title = "", partner_id = "", text = "", currentAddress = "";
    String first_name = "", last_name = "", email = "", service_name = "";
    String phone_number = "", profile_pic = "", address = "", job_id = "";

    IPUpCommingDetailsActivity ipUpCommingDetailsActivity;
    ProgressDialog progressDialog;
    private static final int PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_comming_details);

        ButterKnife.bind(this);
        context = UpCommingDetailsActivity.this;
        savePref = new SavePref(this);
        progressDialog = new ProgressDialog(this);
        ipUpCommingDetailsActivity = new PUpCommingDetailsActivity(this);

        Initialization();

        if (getIntent().getExtras() != null) {
            BeanUpcomingDetails beanUpcomingDetails = (BeanUpcomingDetails) getIntent().getSerializableExtra("user_date_send");

            user_id = beanUpcomingDetails.getUser_id();
            service_id = beanUpcomingDetails.getService_id();
            Latitude = beanUpcomingDetails.getLatitude();
            Longitude = beanUpcomingDetails.getLongitude();
            title = beanUpcomingDetails.getTitle();
            partner_id = beanUpcomingDetails.getPartner_Id();
            text = beanUpcomingDetails.getText();
            first_name = beanUpcomingDetails.getFirst_name();
            last_name = beanUpcomingDetails.getLast_name();
            email = beanUpcomingDetails.getEmail();
            service_name = beanUpcomingDetails.getService_name();
            phone_number = beanUpcomingDetails.getPhone_number();
            profile_pic = beanUpcomingDetails.getProfile_pic();
            address = beanUpcomingDetails.getAddress();

            getCompleteAddressString(Double.parseDouble(Latitude), Double.parseDouble(Longitude));

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

            if (profile_pic.isEmpty() || profile_pic.equals("")) {
                Glide.with(context).load(R.drawable.no_image)
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image).into(img_user);
            } else {
                Glide.with(context).load(profile_pic)
                        .error(R.drawable.no_image).into(img_user);
            }

        } else {

        }

        // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
      /*  super.onNewIntent(intent);
        setIntent(intent);*/
        Bundle extras = intent.getExtras();

        if (extras != null) {

            if (extras.containsKey("user_date_send")) {

                // extract the extra-data in the Notification
                String msg = extras.getString("user_date_send");
                Log.d("+++++++", "++ msg ++ " + msg);

                String user_id = extras.getString("user_id");
                Log.d("user_id++++", "" + user_id);

                String service_id = extras.getString("service_id");
                Log.d("service_id++++", "" + service_id);

                String Latitude = extras.getString("Latitude");
                Log.d("Latitude++++", "" + Latitude);

                String Longitude = extras.getString("Longitude");
                Log.d("Longitude++++", "" + Longitude);

            }
        }
    }

    private void Initialization() {

        // getUserID = savePref.getid();
        partner_id = savePref.getid();
        tv_upcomming.setTypeface(Utility.typeFaceForBoldText(this));
        tv_username.setTypeface(Utility.typeFaceForBoldText(this));
        cardetails.setTypeface(Utility.typeFaceForBoldText(this));
        servicesdetails.setTypeface(Utility.typeFaceForBoldText(this));

        enableGPS();

        EventListners();

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

        int permission = ContextCompat.checkSelfPermission(context,

                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission == PackageManager.PERMISSION_GRANTED) {

            startTrackerService();

        } else {

            ActivityCompat.requestPermissions((UpCommingDetailsActivity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);

        }
    }

    private void startTrackerService() {

        startService(new Intent(context, TrackerService.class));
    }

    private void EventListners() {

        img_back_upcomming.setOnClickListener(this);
        btn_accept.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
        img_chat.setOnClickListener(this);
        tv_usercontact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back_upcomming:

                finish();

                break;

            case R.id.tv_usercontact:

                callPhoneNumber();

                break;

            case R.id.img_chat:

                firebaseChatWith = user_id;
                Constant.user_name = first_name + " " + last_name;

                Intent intent = new Intent(UpCommingDetailsActivity.this, ChatActivity.class);
                intent.putExtra("firebaseChatWith", firebaseChatWith);
                intent.putExtra("partner_id", partner_id);
                intent.putExtra("user_name", first_name + " " + last_name);
                intent.putExtra("service_name", service_name);
                startActivity(intent);

                break;
            case R.id.btn_accept:
                if (Utility.isNetworkConnected(context)) {
                    progressDialog = Utility.showLoader(UpCommingDetailsActivity.this);
                    ipUpCommingDetailsActivity.doAccept("", user_id, service_id, text, Latitude, Longitude, partner_id);

                } else {
                    Toast.makeText(context, "Please check your internet connection !", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_reject:

                if (Utility.isNetworkConnected(context)) {

                    progressDialog = Utility.showLoader(UpCommingDetailsActivity.this);
                    ipUpCommingDetailsActivity.doReject(user_id, service_id, text, Latitude, Longitude);

                } else {

                    Toast.makeText(context, "Please check your internet connection !", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(UpCommingDetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            } else {
            }
        }
    }

    private String getCompleteAddressString(double Latitude, double Longitude) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Latitude, Longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);

                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }

                currentAddress = strReturnedAddress.toString().trim();

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return currentAddress;
    }

    @Override
    public void onAcceptResponseFromPresenter(int status) {
        progressDialog.dismiss();
    }

    @Override
    public void onAcceptSucessResponseFromPresenter(AcceptRejectResponseModel acceptRejectResponseModel) {

        job_id = String.valueOf(acceptRejectResponseModel.getData().get(0).getJobid());
        progressDialog.dismiss();

        Intent intent = new Intent(UpCommingDetailsActivity.this, OTPActivity.class);
        intent.putExtra("user_id", user_id);
        intent.putExtra("service_id", service_id);
        intent.putExtra("text", text);
        intent.putExtra("Latitude", Latitude);
        intent.putExtra("Longitude", Longitude);
        intent.putExtra("service_name", service_name);
        intent.putExtra("job_id", job_id);
        intent.putExtra("title", title);
        intent.putExtra("partner_id", partner_id);
        intent.putExtra("first_name", first_name);
        intent.putExtra("last_name", last_name);
        intent.putExtra("email", email);
        intent.putExtra("phone_number", phone_number);
        intent.putExtra("profile_pic", profile_pic);
        intent.putExtra("address", address);

        startActivity(intent);

    }

    @Override
    public void onAcceptFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();

    }

    @Override
    public void onRejectResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onRejectSucessResponseFromPresenter(RejectResponseModel rejectResponseModel) {
        progressDialog.dismiss();
        OpenRejectDialogBox();
    }


    @Override
    public void onRejectFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

    private void OpenRejectDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Hi " + savePref.getuser_name());
        builder.setMessage("Are you sure want to reject " + first_name + " " + last_name + " Request?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(UpCommingDetailsActivity.this, Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                /*.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })*/;
        AlertDialog alert = builder.create();
        alert.show();
    }
}
