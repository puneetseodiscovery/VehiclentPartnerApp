package vehiclentpartner.com.vehiclent.home.models;

import android.os.Message;
import vehiclentpartner.com.vehiclent.home.presenter.IPProfileFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PProfileFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.retrofitClasses.APIInterface;
import vehiclentpartner.com.vehiclent.retrofitClasses.RetrofitCalls;

public class ModelProfileFragment implements IMProfileFragment {

    IPProfileFragment ipProfileFragment;

    public ModelProfileFragment(PProfileFragment pProfileFragment) {
        this.ipProfileFragment = pProfileFragment;
    }

    @Override
    public void getUserProfileRestCall(String id) {
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.get_userProfile(id, mHandler);
    }

    @Override
    public void updateUserProfileRestCall(String id, String first_name, String last_name, String gender, String address, String phone_number) {

        RetrofitCalls retrofitCalls = new RetrofitCalls();
        retrofitCalls.update_userProfile(id,first_name,last_name,gender,address,phone_number, mHandler);
    }

    android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case APIInterface.PROFILE_SUCCESS:
                    GetUserProfileResponseModel getUserProfileResponseModel = ((GetUserProfileResponseModel) msg.obj);
                    ipProfileFragment.onGetUserProfileSuccess(getUserProfileResponseModel);
                    break;

                case APIInterface.PROFILE_FAILED:
                    String mesFailed = String.valueOf(msg.obj);
                    ipProfileFragment.onGetUserProfileFailed(mesFailed);
                    break;


                case APIInterface.UPDATE_PROFILE_SUCCESS:
                    UpdateUserProfileResponseModel updateUserProfileResponseModel = ((UpdateUserProfileResponseModel) msg.obj);
                    ipProfileFragment.onUpdateUserProfileSuccess(updateUserProfileResponseModel);
                    break;

                case APIInterface.UPDATE_PROFILE_FAILED:
                    String messageFailed = String.valueOf(msg.obj);
                    ipProfileFragment.onUpdateUserProfileFailed(messageFailed);
                    break;


            }
        }
    };
}
