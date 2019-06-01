package vehiclentpartner.com.vehiclent.login;

import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;

public interface IPLogin {

        void doLogin(String email,String password);
        void onLoginResponseFromModel(int statusValue);
        void onLoginSucess(LoginResponseModel loginResponseModel);
        void onLoginFailed(String message);
}
