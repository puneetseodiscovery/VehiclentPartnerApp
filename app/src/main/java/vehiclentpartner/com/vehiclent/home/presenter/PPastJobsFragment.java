package vehiclentpartner.com.vehiclent.home.presenter;


import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IMPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IPPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.fragment.Past;
import vehiclentpartner.com.vehiclent.home.models.MPastJobsFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;

public class PPastJobsFragment implements IPPastJobsFragment {

    IPastJobsFragment ipPastJobsFragment;
    IMPastJobsFragment imPastJobsFragment;

    public PPastJobsFragment(Past past) {
        this.ipPastJobsFragment = past;
    }

    @Override
    public void doPastJobsList(String userid) {
        imPastJobsFragment = new MPastJobsFragment(this);
        imPastJobsFragment.getPastJobRestCall(userid);
    }

    @Override
    public void onPastJobsListResponseFromModel(int statusValue) {
        ipPastJobsFragment.onPastJobFromPresenter(statusValue);
    }

    @Override
    public void onPastJobsListSuccessResponseFromModel(PastJobListingResponseModel pastJobListingResponseModel) {
        ipPastJobsFragment.onPastJobSuccessFromPresenter(pastJobListingResponseModel);
    }

    @Override
    public void onPastJobsListFaildResponseFromModel(String message) {
        ipPastJobsFragment.onPastJobFailedFromPresenter(message);
    }

    @Override
    public void onPastJobsListEmptyResponseFromModel(String message) {
        ipPastJobsFragment.onPastJobEmptyFromPresenter(message);
    }
}
