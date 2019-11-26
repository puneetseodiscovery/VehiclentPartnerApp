package vehiclentpartner.com.vehiclent.optActivity;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingDetailsResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class MOTPActivity implements IMOTPActivitiy {

    IPOTPAcitivty ipotpAcitivty;

    public MOTPActivity(POTPActivity potpActivity) {

        this.ipotpAcitivty = potpActivity;

    }

    @Override
    public void OTPRestCalls(String jobid, String otp) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.match_Otp(jobid, otp, mHandler);

    }

    @Override
    public void upCommingDetailRestCall(String jobid) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.upcommingDetails_Api(jobid, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.MATCH_OTP_SUCCESS:

                    MatchOTPResponse matchOTPResponse = (MatchOTPResponse) msg.obj;
                    ipotpAcitivty.onOTPMatchSuccessResponseFromModel(matchOTPResponse);

                    break;

                case APIInterface.MATCH_OTP_FAILED:

                    ipotpAcitivty.onOTPMatchFailedResponseFromModel(String.valueOf(msg.obj));

                    break;

                case APIInterface.UPCOMMING_DETAILS_SUCCESS:

                    UpCommingDetailsResponseModel upCommingDetailsResponseModel = (UpCommingDetailsResponseModel) msg.obj;
                    ipotpAcitivty.onUpCommingDetailsSuccessResponseFromModel(upCommingDetailsResponseModel);

                    break;

                case APIInterface.UPCOMMING_DETAILS_FAILED:

                    ipotpAcitivty.onUpCommingDetailsFailedResponseFromModel(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
