package vehiclentpartner.com.vehiclent.pastdetails;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;
import vehiclentpartner.com.vehiclent.util.Utility;

public class PastDetailsActivity extends BaseClass implements View.OnClickListener, IPastJobDetailsActivity {

    @BindView(R.id.tv_earning_details)
    TextView tv_earning_details;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_email)
    TextView tv_user_email;

    @BindView(R.id.tv_user_mobile)
    TextView tv_user_mobile;

    @BindView(R.id.tv_carmodel)
    TextView tv_carmodel;

    @BindView(R.id.tv_services)
    TextView tv_services;

    @BindView(R.id.tv_user_address)
    TextView tv_user_address;

    @BindView(R.id.tv_payable_amount)
    TextView tv_payable_amount;

    @BindView(R.id.tv_jobdate)
    TextView tv_jobdate;

    @BindView(R.id.img_back_earning_details)
    ImageView img_back_earning_details;

    @BindView(R.id.img_userprofile)
    ImageView img_userprofile;

    PastDetailsActivity context;
    String job_id = "",job_images="";
    ProgressDialog progressDialog;
    IPPastJobDetails ipPastJobDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_details);


        ButterKnife.bind(this);
        context = PastDetailsActivity.this;
        progressDialog = new ProgressDialog(this);
        ipPastJobDetails = new PPastJobDetails(this);

        Initialization();

    }


    private void Initialization() {
        job_id = getIntent().getStringExtra("job_id");
        job_images = getIntent().getStringExtra("job_images");
        tv_earning_details.setTypeface(Utility.typeFaceForBoldText(this));

        if (job_images.isEmpty()||job_images.equals("")){

            Glide.with(context).load(job_images).placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(img_userprofile);

        }else {
            Glide.with(context).load(job_images).placeholder(R.drawable.placeholder).into(img_userprofile);

        }


        if (Utility.isNetworkConnected(context)){
            progressDialog = Utility.showLoader(context);
            ipPastJobDetails.doPastJobDetails(job_id);

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

        EventListner();

    }

    private void EventListner() {
        img_back_earning_details.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_back_earning_details:
                finish();
                break;
        }

    }

    @Override
    public void onPastJobDetailsFromPresenter(int status) {
        progressDialog.dismiss();
    }

    @Override
    public void onPastJobDetailsSucessFromPresenter(PastJobDetailsResponseModel pastJobDetailsResponseModel) {
        progressDialog.dismiss();

        if (pastJobDetailsResponseModel.getData() != null || pastJobDetailsResponseModel.getData().size() > 0) {

           // Glide.with(context).load(pastJobDetailsResponseModel.getData().get(0).getProfilePic()).into(img_userprofile);

            tv_user_name.setText(pastJobDetailsResponseModel.getData().get(0).getFirstName() + " " + pastJobDetailsResponseModel.getData().get(0).getLastName());
            tv_user_email.setText(pastJobDetailsResponseModel.getData().get(0).getEmail());
            tv_user_mobile.setText(pastJobDetailsResponseModel.getData().get(0).getPhoneNumber());
            tv_user_address.setText(pastJobDetailsResponseModel.getData().get(0).getAddress());
            tv_payable_amount.setText("\u20B9" + " " + pastJobDetailsResponseModel.getData().get(0).getPrice());
            tv_jobdate.setText(pastJobDetailsResponseModel.getData().get(0).getJobdate());
            tv_carmodel.setText(pastJobDetailsResponseModel.getData().get(0).getServiceName());
            tv_services.setText(pastJobDetailsResponseModel.getData().get(0).getServiceName());


        } else {

        }

    }

    @Override
    public void onPastJobDetailsFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
