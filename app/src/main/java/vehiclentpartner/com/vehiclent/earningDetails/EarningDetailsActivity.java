package vehiclentpartner.com.vehiclent.earningDetails;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningDetailsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;
import vehiclentpartner.com.vehiclent.util.Utility;

public class EarningDetailsActivity extends BaseClass implements View.OnClickListener,IEarningDetailsActivity {

    @BindView(R.id.tv_earning_details)
    TextView tv_earning_details;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_email)
    TextView tv_user_email;

    @BindView(R.id.tv_user_mobile)
    TextView tv_user_mobile;

    @BindView(R.id.tv_user_address)
    TextView tv_user_address;

    @BindView(R.id.tv_payable_amount)
    TextView tv_payable_amount;

    @BindView(R.id.tv_jobdate)
    TextView tv_jobdate;

    @BindView(R.id.tv_services)
    TextView tv_services;

    @BindView(R.id.img_back_earning_details)
    ImageView img_back_earning_details;

    @BindView(R.id.img_userprofile)
    CircleImageView img_userprofile;

    EarningDetailsActivity context;
    String job_id = "";
    ProgressDialog progressDialog;
    IPEarningDetailsActivity ipEarningDetailsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_details);

        ButterKnife.bind(this);

        context =EarningDetailsActivity.this;
        progressDialog=new ProgressDialog(this);
        ipEarningDetailsActivity=new PEarningDetailsActivity(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {
        job_id=getIntent().getStringExtra("job_id");
        tv_earning_details.setTypeface(Utility.typeFaceForBoldText(this));

        if (Utility.isNetworkConnected(context)){
            progressDialog=Utility.showLoader(context);
            ipEarningDetailsActivity.doEarningDetailsDetails(job_id);

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

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
    public void onEarningDetailsFromPresenter(int status) {
        progressDialog.dismiss();
    }

    @Override
    public void onEarningDetailsSucessFromPresenter(EarningDetailsResponseModel earningDetailsResponseModel) {
        progressDialog.dismiss();

        if (earningDetailsResponseModel.getData() != null || earningDetailsResponseModel.getData().size() > 0) {

            tv_user_name.setText(earningDetailsResponseModel.getData().get(0).getFirstName() + " " + earningDetailsResponseModel.getData().get(0).getLastName());
            tv_user_email.setText(earningDetailsResponseModel.getData().get(0).getEmail());
            tv_user_mobile.setText(earningDetailsResponseModel.getData().get(0).getPhoneNumber());
            tv_user_address.setText(earningDetailsResponseModel.getData().get(0).getAddress());
            tv_payable_amount.setText("\u20B9" + " " + earningDetailsResponseModel.getData().get(0).getPrice());
            tv_jobdate.setText(earningDetailsResponseModel.getData().get(0).getJobdate());
            tv_services.setText(earningDetailsResponseModel.getData().get(0).getServiceName());

            if (earningDetailsResponseModel.getData().get(0).getProfilePic() == null ||
                    earningDetailsResponseModel.getData().get(0).getProfilePic().isEmpty()) {

                Glide.with(context).load(earningDetailsResponseModel.getData().get(0).getProfilePic())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(img_userprofile);
            } else {

                Glide.with(context).load(earningDetailsResponseModel.getData().get(0).getProfilePic())
                        .error(R.drawable.placeholder)
                        .into(img_userprofile);
            }


        } else {

        }
    }

    @Override
    public void onEarningDetailsFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
