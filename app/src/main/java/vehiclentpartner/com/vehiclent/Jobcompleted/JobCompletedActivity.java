package vehiclentpartner.com.vehiclent.Jobcompleted;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.congratulations.CongratulationActivity;
import vehiclentpartner.com.vehiclent.feedback.FeedbackActivity;
import vehiclentpartner.com.vehiclent.location.LocationActivity;
import vehiclentpartner.com.vehiclent.optActivity.OTPActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.CompleteJobResponse;
import vehiclentpartner.com.vehiclent.util.Utility;

public class JobCompletedActivity extends BaseClass implements View.OnClickListener, IJobComplete {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_jobcompleted)
    TextView tv_jobcompleted;

    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.relative_Swipe)
    RelativeLayout relative_Swipe;

    @BindView(R.id.relative_layout)
    RelativeLayout relative_layout;

    IPJobComplete ipJobComplete;
    ProgressDialog progressDialog;
    JobCompletedActivity context;
    String job_id = "";

    int charGaps = 3;
    int startPosition = 0;
    int endPosition = charGaps;
    int lengthOfString;
    int intervalMiliSeconds = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_completed);

        ButterKnife.bind(this);
        context = JobCompletedActivity.this;
        ipJobComplete = new PJobComplete(this);
        progressDialog = new ProgressDialog(this);
        Initialization();
    }

    private void Initialization() {


        job_id = getIntent().getStringExtra("job_id");
        tv_jobcompleted.setTypeface(Utility.typeFaceForBoldText(this));

        lengthOfString = tv_submit.getText().length();

        MyThread thread = new MyThread();

        thread.start();
        EventListner();

    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        relative_Swipe.setOnClickListener(this);
    }

    class MyThread extends Thread {
        //used for stopping thread
        boolean flag;

        //init flag to true so that method run continuously
        public MyThread() {
            flag = true;
        }

        //set flag false, if want to stop this thread
        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            super.run();
            while (flag) {
                try {
                    Thread.sleep(intervalMiliSeconds);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Spannable spn = new SpannableString(tv_submit
                                    .getText().toString());
                            spn.setSpan(new ForegroundColorSpan(Color.BLACK),
                                    startPosition, endPosition,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv_submit.setText(spn);

                            startPosition++;
                            endPosition++;
                            endPosition %= (lengthOfString + charGaps);
                            startPosition %= lengthOfString;

                            if (startPosition == 0) {
                                endPosition = charGaps;
                                startPosition = 0;
                            }

                            if (endPosition > lengthOfString) {
                                endPosition = lengthOfString;
                            }

                            Log.d("Home", "Start : " + startPosition + " End : " + endPosition);

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.relative_Swipe:
                if (Utility.isNetworkConnected(context)) {
                    progressDialog = Utility.showLoader(JobCompletedActivity.this);
                    ipJobComplete.doJobComplete(job_id);
                } else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    @Override
    public void onJobCompleteResponseFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onJobCompleteSucessResponseFromPresenter(CompleteJobResponse completeJobResponse) {
        progressDialog.dismiss();
       /* if (completeJobResponse.getStatus().equals("0")) {

            Snackbar snackbar = Snackbar
                    .make(relative_layout, "Payment is not recevied yet.", Snackbar.LENGTH_LONG)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(relative_layout, "", Snackbar.LENGTH_SHORT);
                            snackbar1.dismiss();
                        }
                    });
            snackbar.show();

        }*/
            Intent intent = new Intent(JobCompletedActivity.this, CongratulationActivity.class);
            startActivity(intent);
    }

    @Override
    public void onJobCompleteFaildResponseFromPresenter(String message) {
        progressDialog.dismiss();
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();

    }
}
