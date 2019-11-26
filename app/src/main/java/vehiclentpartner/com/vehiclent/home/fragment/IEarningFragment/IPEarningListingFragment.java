package vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment;

import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;
public interface IPEarningListingFragment {

    void doearningListing(String partnerid);
    void onearningListingResponseFromModel(int statusValue);
    void onearningListingSuccessResponseFromModel(EarningListResponseModel earningListResponseModel);
    void onearningListingFaildResponseFromModel(String message);
    void onearningListingEmptyFromPresenter(String messge);

}
