package vehiclentpartner.com.vehiclent.home.presenter;

import android.support.v4.app.FragmentActivity;

import vehiclentpartner.com.vehiclent.about_us.ModelAboutUs;
import vehiclentpartner.com.vehiclent.home.fragment.ISettingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.SettingFragment;
import vehiclentpartner.com.vehiclent.home.models.IModelLogout;
import vehiclentpartner.com.vehiclent.home.models.ModelLogOut;
import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;

public class PLogOut implements IPLogout {

    ISettingFragment iSettingFragment;
    IModelLogout iModelLogoutl;


    public PLogOut(SettingFragment settingFragment) {

        this.iSettingFragment= settingFragment;
    }

    @Override
    public void doLogout(String id) {

        iModelLogoutl=new ModelLogOut(this);
        iModelLogoutl.logoutRestCall(id);
    }

    @Override
    public void onLogoutResponseFromModel(int statusValue) {
        iSettingFragment.onLogoutFromPresenter(statusValue);
    }

    @Override
    public void onLogoutSuccess(LogoutResponseModel logoutResponseModel) {
        iSettingFragment.onLogoutSuccessFromPresenter(logoutResponseModel);

    }

    @Override
    public void onLogoutFailed(String message) {
        iSettingFragment.onLogoutFailedFromPresenter(message);

    }
}
