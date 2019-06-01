package vehiclentpartner.com.vehiclent.home.presenter;

import vehiclentpartner.com.vehiclent.home.fragment.IProfileFragment;
import vehiclentpartner.com.vehiclent.home.fragment.ProfileFragment;
import vehiclentpartner.com.vehiclent.home.models.IMProfileFragment;
import vehiclentpartner.com.vehiclent.home.models.ModelProfileFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;

public class PProfileFragment implements IPProfileFragment {

    IProfileFragment ipProfileFragment;
    IMProfileFragment imProfileFragment;

    public PProfileFragment(ProfileFragment profileFragment) {

        this.ipProfileFragment = profileFragment;
    }


    @Override
    public void doGetUserProfile(String id) {

        imProfileFragment = new ModelProfileFragment( this);
        imProfileFragment.getUserProfileRestCall(id);

    }

    @Override
    public void doUpdateUserProfile(String id, String first_name, String last_name, String gender, String address, String phone_number) {
        imProfileFragment=new ModelProfileFragment(this);
        imProfileFragment.updateUserProfileRestCall(id,first_name,last_name,gender,address,phone_number);
    }

    @Override
    public void onGetUserProfileResponseFromModel(int statusValue) {
        ipProfileFragment.onGetProfileFromPresenter(statusValue);
    }

    @Override
    public void onUpdateUserProfileResponseFromModel(int statusValue) {

        ipProfileFragment.onUpdateProfileFromPresenter(statusValue);

    }

    @Override
    public void onGetUserProfileSuccess(GetUserProfileResponseModel getUserProfileResponseModel) {
        ipProfileFragment.onGetProfileSuccessFromPresenter(getUserProfileResponseModel);

    }

    @Override
    public void onUpdateUserProfileSuccess(UpdateUserProfileResponseModel updateUserProfileResponseModel) {
        ipProfileFragment.onUpdateUserProfileFromPresenter(updateUserProfileResponseModel);

    }

    @Override
    public void onGetUserProfileFailed(String message) {

        ipProfileFragment.onGetProfileSFailedFromPresenter(message);


    }

    @Override
    public void onUpdateUserProfileFailed(String message) {

        ipProfileFragment.onUpdateProfileSFailedFromPresenter(message);


    }
}
