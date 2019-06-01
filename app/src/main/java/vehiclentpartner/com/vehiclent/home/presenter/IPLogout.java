package vehiclentpartner.com.vehiclent.home.presenter;

import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;

public interface IPLogout {

    void doLogout(String id);
    void onLogoutResponseFromModel(int statusValue);
    void onLogoutSuccess(LogoutResponseModel logoutResponseModel);
    void onLogoutFailed(String message);
}
