package vehiclentpartner.com.vehiclent.login;

import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;

public interface IPLogin {

        void doLogin(String email, String password,String device_token,String latitude,String longitude);
        void onLoginResponseFromModel(int statusValue);
        void onLoginSucess(LoginResponseModel loginResponseModel);
        void onLoginFailed(String message);
}
