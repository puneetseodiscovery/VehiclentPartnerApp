package vehiclentpartner.com.vehiclent.home.fragment.IPastJobs;

import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;

public interface IPastJobsFragment {

    void onPastJobFromPresenter(int statusValue);
    void onPastJobSuccessFromPresenter(PastJobListingResponseModel pastJobListingResponseModel);
    void onPastJobFailedFromPresenter(String messge);
    void onPastJobEmptyFromPresenter(String messge);
}
