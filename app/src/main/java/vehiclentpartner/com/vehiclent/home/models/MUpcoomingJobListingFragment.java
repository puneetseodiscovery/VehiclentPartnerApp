package vehiclentpartner.com.vehiclent.home.models;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IMUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IPUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class MUpcoomingJobListingFragment implements IMUpcoomingJobListingFragment {

    IPUpcoomingJobListingFragment ipUpcoomingJobListingFragment;

    public MUpcoomingJobListingFragment(PUpcoomingJobListingFragment pUpcoomingJobListingFragment) {
        this.ipUpcoomingJobListingFragment = pUpcoomingJobListingFragment;
    }

    @Override
    public void getUpcoomingJobListingRestCall(String partnerid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.upcommingJobListing_Api(partnerid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.UPCOMMING_JOBLIST_SUCCESS:
                    UpCommingJobListingResponseModel upCommingJobListingResponseModel = ((UpCommingJobListingResponseModel) msg.obj);
                    ipUpcoomingJobListingFragment.onUpcomingJobListingSuccessResponseFromModel(upCommingJobListingResponseModel);
                    break;

                case APIInterface.UPCOMMING_JOBLIST_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipUpcoomingJobListingFragment.onUpcomingJobListingFaildResponseFromModel(mesFailed);
                    break;
                case APIInterface.UPCOMMING_LISTING_EMPTY:
                    String messsageEmpty = String.valueOf(msg.obj);
                    ipUpcoomingJobListingFragment.onUpcomingJobListingEmptyResponseFromModel(messsageEmpty);
                    break;
            }
        }
    };
}
