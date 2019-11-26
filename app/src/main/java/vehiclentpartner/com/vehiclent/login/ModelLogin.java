package vehiclentpartner.com.vehiclent.login;

import android.os.Handler;
import android.os.Message;

import java.util.logging.LogRecord;

import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class ModelLogin implements IModelLogin {

    IPLogin ipLogin;
    //PLogin pLogin;

    public ModelLogin(PLogin pLogin) {
        this.ipLogin = pLogin;
    }

    @Override
    public void loginRestCall(String email, String password,String device_token,String latitude,String longitude) {

        RetrofitCalls retrofitCalls=new RetrofitCalls();
        retrofitCalls.partnerLoginApi(email,password,device_token,latitude,longitude,mHandler);

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APIInterface.LOGIN_SUCCESS:
                    LoginResponseModel loginResponseModel = (LoginResponseModel) msg.obj;
                    ipLogin.onLoginSucess(loginResponseModel);
                    break;

                case APIInterface.LOGIN_FAILED:
                    ipLogin.onLoginFailed(String.valueOf(msg.obj));
                    break;
            }
        }
    };

}
