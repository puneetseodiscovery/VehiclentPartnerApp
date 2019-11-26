package vehiclentpartner.com.vehiclent.upcommingDetails;

import vehiclentpartner.com.vehiclent.responseModelClasses.AcceptRejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.RejectResponseModel;

public interface IUpCommingDetailsActivity {

    void onAcceptResponseFromPresenter(int status);
    void onAcceptSucessResponseFromPresenter(AcceptRejectResponseModel acceptRejectResponseModel);
    void onAcceptFailedResponseFromPresenter(String message);

    void  onRejectResponseFromPresenter(int statusValue);
    void  onRejectSucessResponseFromPresenter(RejectResponseModel  rejectResponseModel);
    void  onRejectFailedResponseFromPresenter(String message);
}
