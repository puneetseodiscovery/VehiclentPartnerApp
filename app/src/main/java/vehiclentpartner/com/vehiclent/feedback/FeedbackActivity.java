package vehiclentpartner.com.vehiclent.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.congratulations.CongratulationActivity;
import vehiclentpartner.com.vehiclent.util.Utility;

public class FeedbackActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back_feedback)
    ImageView img_back_feedback;

    @BindView(R.id.tv_feedback)
    TextView tv_feedback;

    @BindView(R.id.rating_to_customer)
    TextView rating_to_customer;

    @BindView(R.id.tv_submit)
    TextView tv_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ButterKnife.bind(this);

        Initialization();
    }

    private void Initialization() {

        tv_feedback.setTypeface(Utility.typeFaceForBoldText(this));
        rating_to_customer.setTypeface(Utility.typeFaceForBoldText(this));

        EventListner();
    }

    private void EventListner() {
        img_back_feedback.setOnClickListener(this);
        tv_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_feedback:
                finish();
                break;

            case R.id.tv_submit:
                Intent intent = new Intent(FeedbackActivity.this, CongratulationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
