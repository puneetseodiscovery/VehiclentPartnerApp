package vehiclentpartner.com.vehiclent.home.presenter;

import vehiclentpartner.com.vehiclent.home.fragment.EarningFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IMEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IPEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.models.MEarningFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;

public class PEarningFragmnet implements IPEarningListingFragment {

        IEarningListingFragment iEarningListingFragment;
    IMEarningListingFragment imEarningListingFragment;

    public PEarningFragmnet(EarningFragment earningFragment) {
         this.iEarningListingFragment=earningFragment;
    }

    @Override
    public void doearningListing(String partnerid) {
        imEarningListingFragment=new MEarningFragment(this);
        imEarningListingFragment.getEarningListingRestCall(partnerid);
    }

    @Override
    public void onearningListingResponseFromModel(int statusValue) {
        iEarningListingFragment.onearningListingFromPresenter(statusValue);
    }

    @Override
    public void onearningListingSuccessResponseFromModel(EarningListResponseModel earningListResponseModel) {
        iEarningListingFragment.onearningListingSuccessFromPresenter(earningListResponseModel);
    }

    @Override
    public void onearningListingFaildResponseFromModel(String message) {
        iEarningListingFragment.onearningListingFailedFromPresenter(message);
    }

    @Override
    public void onearningListingEmptyFromPresenter(String messge) {
        iEarningListingFragment.onearningListingEmptyFromPresenter(messge);
    }
}
