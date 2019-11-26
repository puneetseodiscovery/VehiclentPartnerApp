package vehiclentpartner.com.vehiclent.Jobcompleted;

import vehiclentpartner.com.vehiclent.responseModelClasses.CompleteJobResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;

public interface IJobComplete {

    void  onJobCompleteResponseFromPresenter(int statusValue);
    void  onJobCompleteSucessResponseFromPresenter(CompleteJobResponse completeJobResponse);
    void  onJobCompleteFaildResponseFromPresenter(String message);
}
