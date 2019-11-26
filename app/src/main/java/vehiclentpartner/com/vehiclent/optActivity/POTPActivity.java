package vehiclentpartner.com.vehiclent.optActivity;

import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingDetailsResponseModel;
import vehiclentpartner.com.vehiclent.upcommingDetails.MUpCommingDetailsActivity;

public class POTPActivity implements IPOTPAcitivty {

    IOTPActivity iotpActivity;
    IMOTPActivitiy imotpActivitiy;

    public POTPActivity(OTPActivity otpActivity) {
        this.iotpActivity = otpActivity;
    }


    @Override
    public void doOTPMatch(String jobid, String otp) {
        imotpActivitiy = new MOTPActivity(this);
        imotpActivitiy.OTPRestCalls(jobid, otp);
    }

    @Override
    public void onOTPMatchResponseFromModel(int satusValue) {
        iotpActivity.onOTPResponseFromPresenter(satusValue);
    }

    @Override
    public void onOTPMatchSuccessResponseFromModel(MatchOTPResponse matchOTPResponse) {
        iotpActivity.onOTPSucessResponseFromPresenter(matchOTPResponse);
    }

    @Override
    public void onOTPMatchFailedResponseFromModel(String message) {
        iotpActivity.onOTPFaildResponseFromPresenter(message);
    }

    @Override
    public void doUpCommingDetails(String jobid) {
        imotpActivitiy = new MOTPActivity(this);
        imotpActivitiy.upCommingDetailRestCall(jobid);
    }

    @Override
    public void onUpCommingDetailsResponseFromModel(int satusValue) {
        iotpActivity.onUpCommingJobDetailsResponseFromPresenter(satusValue);
    }

    @Override
    public void onUpCommingDetailsSuccessResponseFromModel(UpCommingDetailsResponseModel upCommingDetailsResponseModel) {
        iotpActivity.onUpCommingJobDetailsFromPresenter(upCommingDetailsResponseModel);
    }

    @Override
    public void onUpCommingDetailsFailedResponseFromModel(String message) {
        iotpActivity.onUpCommingJobDetailsFromPresenter(message);
    }
}
