package vehiclentpartner.com.vehiclent.Jobcompleted;

import vehiclentpartner.com.vehiclent.optActivity.MOTPActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.CompleteJobResponse;

public class PJobComplete implements IPJobComplete {

    IJobComplete iJobComplete;
    IMJobComplete imJobComplete;


    public PJobComplete(JobCompletedActivity jobCompletedActivity) {
        this.iJobComplete = jobCompletedActivity;
    }

    @Override
    public void doJobComplete(String jobid) {
        imJobComplete = new MJobComplete(this);
        imJobComplete.JobCompleteRestCalls(jobid);
    }

    @Override
    public void onJobCompleteResponseFromModel(int satusValue) {
        iJobComplete.onJobCompleteResponseFromPresenter(satusValue);
    }

    @Override
    public void onJobCompleteSuccessResponseFromModel(CompleteJobResponse completeJobResponse) {
        iJobComplete.onJobCompleteSucessResponseFromPresenter(completeJobResponse);
    }

    @Override
    public void onJobCompleteFailedResponseFromModel(String message) {
        iJobComplete.onJobCompleteFaildResponseFromPresenter(message);
    }
}
