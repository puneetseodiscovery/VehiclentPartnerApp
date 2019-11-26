package vehiclentpartner.com.vehiclent.home.fragment.IUpcomming;

import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;

public interface IPUpcoomingJobListingFragment {

    void doUpcomingJobListing(String partnerid);
    void onUpcomingJobListingResponseFromModel(int statusValue);
    void onUpcomingJobListingSuccessResponseFromModel(UpCommingJobListingResponseModel upCommingJobListingResponseModel);
    void onUpcomingJobListingFaildResponseFromModel(String message);
    void onUpcomingJobListingEmptyResponseFromModel(String message);
}
