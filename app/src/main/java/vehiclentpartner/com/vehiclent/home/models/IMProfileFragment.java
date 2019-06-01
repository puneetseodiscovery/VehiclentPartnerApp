package vehiclentpartner.com.vehiclent.home.models;

public interface IMProfileFragment {

    void getUserProfileRestCall(String id);

    void updateUserProfileRestCall(String id, String first_name, String last_name, String gender, String address, String phone_number);

}
