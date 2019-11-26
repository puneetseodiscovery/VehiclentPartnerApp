package vehiclentpartner.com.vehiclent.upcommingDetails;

import android.os.Handler;
import android.os.Message;

import vehiclentpartner.com.vehiclent.optActivity.POTPActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.AcceptRejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.RejectResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class MUpCommingDetailsActivity implements IMUpCommingDetailsActivity {

    IPUpCommingDetailsActivity ipUpCommingDetailsActivity;

    public MUpCommingDetailsActivity(PUpCommingDetailsActivity pUpCommingDetailsActivity) {

        this.ipUpCommingDetailsActivity = pUpCommingDetailsActivity;
    }



    @Override
    public void onAcceptRestCall(String ids_array, String user_id, String service_id, String Message, String Latitude, String Longitude, String Partner_Id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.acceptApi(ids_array, user_id, service_id, Message, Latitude, Longitude, Partner_Id, mHandler);
    }

    @Override
    public void onRejectRestCall(String id, String service_id, String user_query, String latitude, String longitude) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.rejectApi(id, service_id, user_query, latitude, longitude, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.ACCEPT_QUERY_SUCCESS:

                    AcceptRejectResponseModel acceptRejectResponseModel = (AcceptRejectResponseModel) msg.obj;
                    ipUpCommingDetailsActivity.onAcceptSucessResponseFromModel(acceptRejectResponseModel);

                    break;
                case APIInterface.ACCEPT_QUERY_FAILED:
                    ipUpCommingDetailsActivity.onAcceptFailedResponseFromModel(String.valueOf(msg.obj));
                    break;

                case APIInterface.REJECT_QUERY_SUCCESS:

                    RejectResponseModel rejectResponseModel = (RejectResponseModel) msg.obj;
                    ipUpCommingDetailsActivity.onRejectSucessResponseFromModel(rejectResponseModel);

                    break;
                case APIInterface.REJECT_QUERY_FAILED:
                    ipUpCommingDetailsActivity.onRejectFailedResponseFromModel(String.valueOf(msg.obj));
                    break;
            }
        }
    };
}
