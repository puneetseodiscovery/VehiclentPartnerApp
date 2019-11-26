package vehiclentpartner.com.vehiclent.home.presenter;

import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IMUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IPUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.Upcoming;
import vehiclentpartner.com.vehiclent.home.models.MUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;

public class PUpcoomingJobListingFragment implements IPUpcoomingJobListingFragment {

    IUpcoomingJobListingFragment iUpcoomingJobListingFragment;
    IMUpcoomingJobListingFragment imUpcoomingJobListingFragment;


    public PUpcoomingJobListingFragment(Upcoming upcoming) {
        this.iUpcoomingJobListingFragment=upcoming;
    }

    @Override
    public void doUpcomingJobListing(String partnerid) {

        imUpcoomingJobListingFragment=new MUpcoomingJobListingFragment(this);
        imUpcoomingJobListingFragment.getUpcoomingJobListingRestCall(partnerid);

    }

    @Override
    public void onUpcomingJobListingResponseFromModel(int statusValue) {
        iUpcoomingJobListingFragment.onUpcomingJobListingFromPresenter(statusValue);
    }

    @Override
    public void onUpcomingJobListingSuccessResponseFromModel(UpCommingJobListingResponseModel upCommingJobListingResponseModel) {
        iUpcoomingJobListingFragment.onUpcomingJobListingSuccessFromPresenter(upCommingJobListingResponseModel);
    }

    @Override
    public void onUpcomingJobListingFaildResponseFromModel(String message) {
        iUpcoomingJobListingFragment.onUpcomingJobListingFailedFromPresenter(message);
    }

    @Override
    public void onUpcomingJobListingEmptyResponseFromModel(String message) {
        iUpcoomingJobListingFragment.onUpcomingJobListingEmptyResponseFromPresenter(message);
    }
}
