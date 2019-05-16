package vehiclentpartner.com.vehiclent.Jobcompleted;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.feedback.FeedbackActivity;
import vehiclentpartner.com.vehiclent.location.LocationActivity;
import vehiclentpartner.com.vehiclent.optActivity.OTPActivity;
import vehiclentpartner.com.vehiclent.util.Utility;

public class JobCompletedActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_jobcompleted)
    TextView tv_jobcompleted;

    @BindView(R.id.relative_Swipe)
    RelativeLayout relative_Swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_completed);

        ButterKnife.bind(this);
        Initialization();
    }

    private void Initialization() {

        tv_jobcompleted.setTypeface(Utility.typeFaceForBoldText(this));
        EventListner();

    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        relative_Swipe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.relative_Swipe:

                Intent intent = new Intent(JobCompletedActivity.this, FeedbackActivity.class);
                startActivity(intent);

                break;
        }
    }

}
