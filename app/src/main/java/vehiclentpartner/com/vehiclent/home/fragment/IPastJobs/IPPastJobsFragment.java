package vehiclentpartner.com.vehiclent.home.fragment.IPastJobs;

import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;

public interface IPPastJobsFragment {

    void doPastJobsList(String partnerid);
    void onPastJobsListResponseFromModel(int statusValue);
    void onPastJobsListSuccessResponseFromModel(PastJobListingResponseModel pastJobListingResponseModel);
    void onPastJobsListFaildResponseFromModel(String message);
    void onPastJobsListEmptyResponseFromModel(String message);
}
