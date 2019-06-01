package vehiclentpartner.com.vehiclent.login;

import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;

public interface ILoginActivity {

        void onLoginResponseFromPresenter(int statusValue);
        void onLoginSuccessFromPresenter(LoginResponseModel loginResponseModel);
        void onLoginFailedFromPresenter(String message);
}
