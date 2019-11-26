package vehiclentpartner.com.vehiclent.home.models;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IMPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IPPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PPastJobsFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class MPastJobsFragment implements IMPastJobsFragment {

    IPPastJobsFragment ipPastJobsFragment;

    public MPastJobsFragment(PPastJobsFragment pPastJobsFragment) {

        this.ipPastJobsFragment = pPastJobsFragment;

    }

    @Override
    public void getPastJobRestCall(String partnerid) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.pastjobListing_Api(partnerid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case APIInterface.PAST_JOB_SUCCESS:

                    PastJobListingResponseModel pastJobListingResponseModel = ((PastJobListingResponseModel) msg.obj);
                    ipPastJobsFragment.onPastJobsListSuccessResponseFromModel(pastJobListingResponseModel);

                    break;

                case APIInterface.PAST_JOB_FAILED:

                    String mesFailed = String.valueOf(msg.obj);
                    ipPastJobsFragment.onPastJobsListFaildResponseFromModel(mesFailed);

                    break;
                case APIInterface.PAST_JOB_EMPTY:

                    String mesEmpty = String.valueOf(msg.obj);
                    ipPastJobsFragment.onPastJobsListEmptyResponseFromModel(mesEmpty);

                    break;
            }
        }
    };
}
