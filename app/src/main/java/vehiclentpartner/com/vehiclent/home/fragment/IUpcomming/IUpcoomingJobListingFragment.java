package vehiclentpartner.com.vehiclent.home.fragment.IUpcomming;

import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;

public interface IUpcoomingJobListingFragment {

    void onUpcomingJobListingFromPresenter(int statusValue);
    void onUpcomingJobListingSuccessFromPresenter(UpCommingJobListingResponseModel upCommingJobListingResponseModel);
    void onUpcomingJobListingFailedFromPresenter(String messge);
    void onUpcomingJobListingEmptyResponseFromPresenter(String message);
}
