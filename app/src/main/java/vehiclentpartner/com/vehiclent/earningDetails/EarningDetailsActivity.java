package vehiclentpartner.com.vehiclent.earningDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.util.Utility;

public class EarningDetailsActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.tv_earning_details)
    TextView tv_earning_details;

    @BindView(R.id.img_back_earning_details)
    ImageView img_back_earning_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_details);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        tv_earning_details.setTypeface(Utility.typeFaceForBoldText(this));

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

}
