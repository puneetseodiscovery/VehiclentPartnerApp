package vehiclentpartner.com.vehiclent.earningDetails;

import vehiclentpartner.com.vehiclent.responseModelClasses.EarningDetailsResponseModel;


public interface IPEarningDetailsActivity {

    void  doEarningDetailsDetails(String jobid);
    void  onEarningDetailsResponseFromModel(int statusValue);
    void  onEarningDetailsSucessResponseFromModel(EarningDetailsResponseModel earningDetailsResponseModel);
    void  onEarningDetailsFailedResponseFromModel(String message);

}
