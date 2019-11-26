package vehiclentpartner.com.vehiclent.Jobcompleted;

import vehiclentpartner.com.vehiclent.responseModelClasses.CompleteJobResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;

public interface IPJobComplete {

    void doJobComplete(String jobid);
    void onJobCompleteResponseFromModel(int satusValue);
    void onJobCompleteSuccessResponseFromModel(CompleteJobResponse completeJobResponse);
    void onJobCompleteFailedResponseFromModel(String message);
}
