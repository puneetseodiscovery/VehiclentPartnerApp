package vehiclentpartner.com.vehiclent.login;

import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;

public class PLogin implements IPLogin {

    ILoginActivity iLoginActivity;
    IModelLogin iModelLogin;

    public PLogin(LoginActivity loginActivity) {

        this.iLoginActivity = loginActivity;

    }

    @Override
    public void doLogin(String email, String password,String device_token,String latitude,String longitude) {

        iModelLogin = new ModelLogin(this);
        iModelLogin.loginRestCall(email, password,device_token,latitude,longitude);

    }

    @Override
    public void onLoginResponseFromModel(int statusValue) {

        iLoginActivity.onLoginResponseFromPresenter(statusValue);

    }

    @Override
    public void onLoginSucess(LoginResponseModel loginResponseModel) {
        iLoginActivity.onLoginSuccessFromPresenter(loginResponseModel);


    }

    @Override
    public void onLoginFailed(String message) {
        iLoginActivity.onLoginFailedFromPresenter(message);

    }
}
