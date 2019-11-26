package vehiclentpartner.com.vehiclent.earningDetails;

import vehiclentpartner.com.vehiclent.responseModelClasses.EarningDetailsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;

public class PEarningDetailsActivity implements IPEarningDetailsActivity {

    IEarningDetailsActivity iEarningDetailsActivity;
    IMEarningDetailsActivity imEarningDetailsActivity;

    public PEarningDetailsActivity(EarningDetailsActivity earningDetailsActivity) {
        this.iEarningDetailsActivity = earningDetailsActivity;
    }

    @Override
    public void doEarningDetailsDetails(String jobid) {
        imEarningDetailsActivity = new MEarningDetailsActivity(this);
        imEarningDetailsActivity.getEarningDetailsRestCall(jobid);
    }

    @Override
    public void onEarningDetailsResponseFromModel(int statusValue) {
        iEarningDetailsActivity.onEarningDetailsFromPresenter(statusValue);
    }

    @Override
    public void onEarningDetailsSucessResponseFromModel(EarningDetailsResponseModel earningDetailsResponseModel) {
        iEarningDetailsActivity.onEarningDetailsSucessFromPresenter(earningDetailsResponseModel);
    }

    @Override
    public void onEarningDetailsFailedResponseFromModel(String message) {
        iEarningDetailsActivity.onEarningDetailsFailedFromPresenter(message);
    }
}
