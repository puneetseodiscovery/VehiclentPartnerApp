package vehiclentpartner.com.vehiclent.home.presenter;

import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;

public interface IPProfileFragment {

    void doGetUserProfile(String id);
    void doUpdateUserProfile(String id, String first_name, String last_name, String gender, String address, String phone_number);

    void onGetUserProfileResponseFromModel(int statusValue);
    void onUpdateUserProfileResponseFromModel(int statusValue);

    void onGetUserProfileSuccess(GetUserProfileResponseModel getUserProfileResponseModel);
    void onUpdateUserProfileSuccess(UpdateUserProfileResponseModel updateUserProfileResponseModel);

    void onGetUserProfileFailed(String message);
    void onUpdateUserProfileFailed(String message);
}
