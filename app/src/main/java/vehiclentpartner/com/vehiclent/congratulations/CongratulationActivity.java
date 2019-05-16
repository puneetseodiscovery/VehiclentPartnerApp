package vehiclentpartner.com.vehiclent.congratulations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.Home;
import vehiclentpartner.com.vehiclent.util.Utility;

public class CongratulationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_back_congratulations)
    ImageView img_back_congratulations;

    @BindView(R.id.tv_congos)
    TextView tv_congos;

    @BindView(R.id.tv_goodjob)
    TextView tv_goodjob;

    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        ButterKnife.bind(this);

        Initialization();
    }

    private void Initialization() {

        tv_congos.setTypeface(Utility.typeFaceForBoldText(this));
        tv_goodjob.setTypeface(Utility.typeFaceForBoldText(this));
        EvenListner();
    }

    private void EvenListner() {

        img_back_congratulations.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_congratulations:
                finish();
                break;

            case R.id.tv_submit:
                Intent intent = new Intent(CongratulationActivity.this, Home.class);
                startActivity(intent);
                break;
        }
    }
}
