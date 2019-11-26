package vehiclentpartner.com.vehiclent.optActivity;

import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingDetailsResponseModel;

public interface IPOTPAcitivty {

    void doOTPMatch(String jobid,String otp);
    void onOTPMatchResponseFromModel(int satusValue);
    void onOTPMatchSuccessResponseFromModel(MatchOTPResponse matchOTPResponse);
    void onOTPMatchFailedResponseFromModel(String message);


    void doUpCommingDetails(String jobid);
    void onUpCommingDetailsResponseFromModel(int satusValue);
    void onUpCommingDetailsSuccessResponseFromModel(UpCommingDetailsResponseModel upCommingDetailsResponseModel);
    void onUpCommingDetailsFailedResponseFromModel(String message);
}
