package vehiclentpartner.com.vehiclent.home.fragment;

import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;

public interface IProfileFragment {

    void onGetProfileFromPresenter(int statusValue);
    void onUpdateProfileFromPresenter(int statusValue);

    void onGetProfileSuccessFromPresenter(GetUserProfileResponseModel getUserProfileResponseModel);
    void onUpdateUserProfileFromPresenter(UpdateUserProfileResponseModel updateUserProfileResponseModel);

    void onGetProfileSFailedFromPresenter(String message);
    void onUpdateProfileSFailedFromPresenter(String message);

}
