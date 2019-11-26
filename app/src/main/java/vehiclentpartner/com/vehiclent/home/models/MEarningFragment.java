package vehiclentpartner.com.vehiclent.home.models;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IMEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IPEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PEarningFragmnet;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;


public class MEarningFragment implements IMEarningListingFragment {

    IPEarningListingFragment ipEarningListingFragment;


    public MEarningFragment(PEarningFragmnet pEarningFragmnet) {
        this.ipEarningListingFragment = pEarningFragmnet;
    }

    @Override
    public void getEarningListingRestCall(String partnerid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.earningListing_Api(partnerid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.EARNING_LISTING_SUCCESS:
                    EarningListResponseModel earningListResponseModel = (EarningListResponseModel) msg.obj;
                    ipEarningListingFragment.onearningListingSuccessResponseFromModel(earningListResponseModel);
                    break;

                case APIInterface.EARNING_LISTING_FAILED:
                    ipEarningListingFragment.onearningListingFaildResponseFromModel(String.valueOf(msg.obj));
                    break;

                case APIInterface.EARNING_LISTING_EMPTY:
                    ipEarningListingFragment.onearningListingEmptyFromPresenter(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
