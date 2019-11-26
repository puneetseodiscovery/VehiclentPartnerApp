package vehiclentpartner.com.vehiclent.upcommingDetails;

import vehiclentpartner.com.vehiclent.responseModelClasses.AcceptRejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.RejectResponseModel;

public class PUpCommingDetailsActivity implements IPUpCommingDetailsActivity {

    IUpCommingDetailsActivity iUpCommingDetailsActivity;
    IMUpCommingDetailsActivity imUpCommingDetailsActivity;

    public PUpCommingDetailsActivity(IUpCommingDetailsActivity iUpCommingDetailsActivity) {
        this.iUpCommingDetailsActivity = iUpCommingDetailsActivity;
    }

    @Override
    public void doAccept(String ids_array, String user_id, String service_id, String Message, String Latitude, String Longitude, String Partner_Id) {

        imUpCommingDetailsActivity = new MUpCommingDetailsActivity(this);
        imUpCommingDetailsActivity.onAcceptRestCall(ids_array, user_id, service_id, Message, Latitude, Longitude, Partner_Id);

    }

    @Override
    public void onAcceptSucessResponseFromModel(AcceptRejectResponseModel acceptRejectResponseModel) {
        iUpCommingDetailsActivity.onAcceptSucessResponseFromPresenter(acceptRejectResponseModel);
    }

    @Override
    public void onAcceptFailedResponseFromModel(String message) {
        iUpCommingDetailsActivity.onAcceptFailedResponseFromPresenter(message);
    }

    @Override
    public void doReject(String id, String service_id, String user_query, String latitude, String longitude) {

        imUpCommingDetailsActivity = new MUpCommingDetailsActivity(this);
        imUpCommingDetailsActivity.onRejectRestCall(id, service_id, user_query, latitude, longitude);

    }

    @Override
    public void onRejectSucessResponseFromModel(RejectResponseModel rejectResponseModel) {
        iUpCommingDetailsActivity.onRejectSucessResponseFromPresenter(rejectResponseModel);
    }

    @Override
    public void onRejectFailedResponseFromModel(String message) {
        iUpCommingDetailsActivity.onRejectFailedResponseFromPresenter(message);

    }
}
