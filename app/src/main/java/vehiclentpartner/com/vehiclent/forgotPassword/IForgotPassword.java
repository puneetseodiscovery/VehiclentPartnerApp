package vehiclentpartner.com.vehiclent.forgotPassword;


import vehiclentpartner.com.vehiclent.responseModelClasses.ForgotPasswordResponse;

public interface IForgotPassword {

    void onForgotPasswordResponseFromPresenter(int statusValue);
    void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponse forgotPasswordResponse);
    void onForgotPasswordFailedResponseFromPresenter(String message);
}
