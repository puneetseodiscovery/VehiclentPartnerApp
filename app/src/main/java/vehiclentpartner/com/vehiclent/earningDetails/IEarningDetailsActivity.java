package vehiclentpartner.com.vehiclent.earningDetails;

import vehiclentpartner.com.vehiclent.responseModelClasses.EarningDetailsResponseModel;


public interface IEarningDetailsActivity {

    void onEarningDetailsFromPresenter(int status);
    void onEarningDetailsSucessFromPresenter(EarningDetailsResponseModel earningDetailsResponseModel);
    void onEarningDetailsFailedFromPresenter(String message);
}
