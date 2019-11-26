package vehiclentpartner.com.vehiclent.earningDetails;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.responseModelClasses.EarningDetailsResponseModel;

import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class MEarningDetailsActivity implements IMEarningDetailsActivity {

    IPEarningDetailsActivity ipEarningDetailsActivity;

    public MEarningDetailsActivity(PEarningDetailsActivity pEarningDetailsActivity) {
        this.ipEarningDetailsActivity=pEarningDetailsActivity;
    }

    @Override
    public void getEarningDetailsRestCall(String jobid) {
        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.earningDetails_Api(jobid,mHandler);

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.EARNING_DETAILS_SUCCESS:

                    EarningDetailsResponseModel earningDetailsResponseModel = (EarningDetailsResponseModel) msg.obj;
                    ipEarningDetailsActivity.onEarningDetailsSucessResponseFromModel(earningDetailsResponseModel);

                    break;
                case APIInterface.EARNING_DETAILS_FAILED:
                    ipEarningDetailsActivity.onEarningDetailsFailedResponseFromModel(String.valueOf(msg.obj));
                    break;


            }
        }
    };
}
