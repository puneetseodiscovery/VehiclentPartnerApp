package vehiclentpartner.com.vehiclent.responseModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AcceptRejectResponseModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("Latitude")
        @Expose
        private String latitude;
        @SerializedName("Longitude")
        @Expose
        private String longitude;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("service_price")
        @Expose
        private String servicePrice;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("jobid")
        @Expose
        private Integer jobid;
        @SerializedName("otp")
        @Expose
        private Integer otp;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("your_profile")
        @Expose
        private String yourProfile;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("click_action")
        @Expose
        private String clickAction;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(String servicePrice) {
            this.servicePrice = servicePrice;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Integer getJobid() {
            return jobid;
        }

        public void setJobid(Integer jobid) {
            this.jobid = jobid;
        }

        public Integer getOtp() {
            return otp;
        }

        public void setOtp(Integer otp) {
            this.otp = otp;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getYourProfile() {
            return yourProfile;
        }

        public void setYourProfile(String yourProfile) {
            this.yourProfile = yourProfile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }

    }
}
