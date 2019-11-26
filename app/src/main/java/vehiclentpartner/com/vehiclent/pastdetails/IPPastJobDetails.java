package vehiclentpartner.com.vehiclent.pastdetails;


import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;

public interface IPPastJobDetails {

    void  doPastJobDetails(String jobid);
    void  onPastJobDetailsResponseFromModel(int statusValue);
    void  onPastJobDetailsSucessResponseFromModel(PastJobDetailsResponseModel pastJobDetailsResponseModel);
    void  onPastJobDetailsFailedResponseFromModel(String message);

}
