package vehiclentpartner.com.vehiclent.optActivity;

import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingDetailsResponseModel;

public interface IOTPActivity {

    void  onOTPResponseFromPresenter(int statusValue);
    void  onOTPSucessResponseFromPresenter(MatchOTPResponse matchOTPResponse);
    void  onOTPFaildResponseFromPresenter(String message);


    void  onUpCommingJobDetailsResponseFromPresenter(int statusValue);
    void  onUpCommingJobDetailsFromPresenter(UpCommingDetailsResponseModel upCommingDetailsResponseModel);
    void  onUpCommingJobDetailsFromPresenter(String message);
}
