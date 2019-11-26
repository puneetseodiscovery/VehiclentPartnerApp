package vehiclentpartner.com.vehiclent.home.models;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface IMProfileFragment {

    void getUserProfileRestCall(String id);

    void updateUserProfileRestCall(String id, String first_name, String last_name, String gender, String address, String phone_number, String longitude, String latitude, MultipartBody.Part image, RequestBody imgReq);

}
