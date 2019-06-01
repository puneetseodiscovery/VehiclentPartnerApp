package vehiclentpartner.com.vehiclent.retrofitClasses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vehiclentpartner.com.vehiclent.login.BeanLogin;
import vehiclentpartner.com.vehiclent.responseModelClasses.AboutUsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.TermsConditionsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;

public interface APIInterface {

    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;
    public static final int ABOUTUS_SUCCESS = 3;
    public static final int ABOUTUS_FAILED = 4;
    public static final int TERMCONDITION_SUCCESS = 5;
    public static final int TERMCONDITION_FAILED = 6;
    public static final int LOGOUT_SUCCESS = 7;
    public static final int LOGOUT_FAILED = 8;
    public static final int PROFILE_SUCCESS = 9;
    public static final int PROFILE_FAILED = 10;
    public static final int UPDATE_PROFILE_SUCCESS = 11;
    public static final int UPDATE_PROFILE_FAILED = 12;

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/login_partner.php")
    Call<LoginResponseModel> user_Loging(@Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/about_us.php")
    Call<AboutUsResponseModel> about_us(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/private_policy.php")
    Call<TermsConditionsResponseModel> term_conditions(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/logout_partner.php")
    Call<LogoutResponseModel> logout_user(@Field("id") String id);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/partner_profile.php")
    Call<GetUserProfileResponseModel> get_userProfile(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/editpartner_profile.php")
    Call<UpdateUserProfileResponseModel> update_userProfile(@Field("id") String id,
                                                            @Field("first_name") String first_name,
                                                            @Field("last_name") String last_name,
                                                            @Field("gender") String gender,
                                                            @Field("address") String address,
                                                            @Field("phone_number") String phone_number);


}
