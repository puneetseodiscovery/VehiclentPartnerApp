package vehiclentpartner.com.vehiclent.upcommingDetails;

import vehiclentpartner.com.vehiclent.responseModelClasses.AcceptRejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.RejectResponseModel;

public interface IPUpCommingDetailsActivity {

    void doAccept(String ids_array, String user_id, String service_id, String Message, String Latitude, String Longitude, String Partner_Id);
    void onAcceptSucessResponseFromModel(AcceptRejectResponseModel acceptRejectResponseModel);
    void onAcceptFailedResponseFromModel(String message);

    void doReject(String id,String service_id,String user_query,String latitude,String longitude);
    void onRejectSucessResponseFromModel(RejectResponseModel rejectResponseModel);
    void onRejectFailedResponseFromModel(String message);

}
