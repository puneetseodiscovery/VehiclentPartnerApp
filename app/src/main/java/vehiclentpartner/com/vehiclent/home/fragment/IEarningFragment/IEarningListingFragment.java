package vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment;

import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;

public interface IEarningListingFragment {

    void onearningListingFromPresenter(int statusValue);
    void onearningListingSuccessFromPresenter(EarningListResponseModel earningListResponseModel);
    void onearningListingFailedFromPresenter(String messge);
    void onearningListingEmptyFromPresenter(String messge);
}
