package vehiclentpartner.com.vehiclent.home.fragment;

import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;

public interface ISettingFragment {

    void onLogoutFromPresenter(int statusValue);
    void onLogoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel);
    void onLogoutFailedFromPresenter(String message);
}
