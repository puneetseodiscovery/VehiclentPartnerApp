package vehiclentpartner.com.vehiclent.home.models;
import android.os.Handler;
import android.os.Message;
import vehiclentpartner.com.vehiclent.home.presenter.IPLogout;
import vehiclentpartner.com.vehiclent.home.presenter.PLogOut;
import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class ModelLogOut implements IModelLogout {

    IPLogout ipLogout;

    public ModelLogOut(PLogOut pLogOut) {

        this.ipLogout = pLogOut;
    }

    @Override
    public void logoutRestCall(String id) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.user_Logout(id, mHandler);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGOUT_SUCCESS:
                    LogoutResponseModel logoutResponseModel = ((LogoutResponseModel) msg.obj);
                    ipLogout.onLogoutSuccess(logoutResponseModel);
                    break;

                case APIInterface.LOGOUT_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipLogout.onLogoutFailed(mesFailed);
                    break;
            }
        }
    };
}
