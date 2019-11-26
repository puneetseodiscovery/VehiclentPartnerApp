package vehiclentpartner.com.vehiclent.Jobcompleted;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.responseModelClasses.CompleteJobResponse;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class MJobComplete implements IMJobComplete {

    IPJobComplete ipJobComplete;

    public MJobComplete(PJobComplete pJobComplete) {

        this.ipJobComplete = pJobComplete;

    }

    @Override
    public void JobCompleteRestCalls(String jobid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.complete_Job(jobid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.COMPLETE_JOB_SUCCESS:
                    CompleteJobResponse completeJobResponse = (CompleteJobResponse) msg.obj;
                    ipJobComplete.onJobCompleteSuccessResponseFromModel(completeJobResponse);
                    break;

                case APIInterface.COMPLETE_JOB_FAILED:
                    ipJobComplete.onJobCompleteFailedResponseFromModel(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
