package vehiclentpartner.com.vehiclent.retrofitClasses;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import vehiclentpartner.com.vehiclent.login.BeanLogin;
import vehiclentpartner.com.vehiclent.responseModelClasses.AboutUsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.AcceptRejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.CompleteJobResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.ContactsUsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningDetailsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.ForgotPasswordResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.MatchOTPResponse;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobDetailsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.RejectResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.SendMessageResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.TermsConditionsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingDetailsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;
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

    public static final int CONTACTUS_SUCCESS = 13;
    public static final int CONTACTUS_FAILED = 14;

    public static final int ACCEPT_QUERY_SUCCESS = 15;
    public static final int ACCEPT_QUERY_FAILED = 16;

    public static final int REJECT_QUERY_SUCCESS = 17;
    public static final int REJECT_QUERY_FAILED = 18;

    public static final int MATCH_OTP_SUCCESS = 19;
    public static final int MATCH_OTP_FAILED = 20;

    public static final int COMPLETE_JOB_SUCCESS = 21;
    public static final int COMPLETE_JOB_FAILED = 22;

    public static final int PAST_JOB_SUCCESS = 23;
    public static final int PAST_JOB_FAILED = 24;
    public static final int PAST_JOB_EMPTY = 241;

    public static final int PASTJOBDETAILS_SUCCESS = 25;
    public static final int PASTJOBDETAILS_FAILED = 26;

    public static final int UPCOMMING_JOBLIST_SUCCESS = 27;
    public static final int UPCOMMING_JOBLIST_FAILED = 28;
    public static final int UPCOMMING_LISTING_EMPTY = 208;

    public static final int EARNING_LISTING_SUCCESS = 29;
    public static final int EARNING_LISTING_FAILED = 30;
    public static final int EARNING_LISTING_EMPTY = 301;

    public static final int EARNING_DETAILS_SUCCESS = 31;
    public static final int EARNING_DETAILS_FAILED = 32;

    public static final int UPCOMMING_DETAILS_SUCCESS = 33;
    public static final int UPCOMMING_DETAILS_FAILED = 34;

    public static final int MAIL_SUCCESS = 35;
    public static final int MAIL_FAILED = 36;

    public static final int FORGOT_SUCCESS = 38;
    public static final int FORGOT_FAILED = 39;



    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/login_partner.php")
    Call<LoginResponseModel> user_Loging(@Field("email") String email, @Field("password") String password, @Field("device_token") String device_token,
                                         @Field("latitude") String latitude,
                                         @Field("longitude") String longitude);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/about_us.php")
    Call<AboutUsResponseModel> about_us(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/user_forgotpassword.php")
    Call<ForgotPasswordResponse> forgot_Password(@Field("email") String email);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/private_policy.php")
    Call<TermsConditionsResponseModel> term_conditions(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/logout_partner.php")
    Call<LogoutResponseModel> logout_user(@Field("id") String id);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/partner_profile.php")
    Call<GetUserProfileResponseModel> get_userProfile(@Field("id") String id);


    @Multipart
    @POST("vehiclentsss/vehiclents/apis/editpartner_profile.php")
    Call<UpdateUserProfileResponseModel> update_userProfile(@Part("id") RequestBody user_id,
                                                            @Part("first_name") RequestBody user_firstname,
                                                            @Part("last_name") RequestBody user_last_name,
                                                            @Part("gender") RequestBody user_gender,
                                                            @Part("address") RequestBody user_address,
                                                            @Part("phone_number") RequestBody user_phone_number,
                                                            @Part("latitude") RequestBody latitude,
                                                            @Part("longitude") RequestBody longitude,
                                                            @Part("image") RequestBody imgReq,
                                                            @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/contact_us.php")
    Call<ContactsUsResponseModel> contactus(@Field("id") String id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/accept_query.php")
    Call<AcceptRejectResponseModel> accept_Query(@Field("ids_array") String ids_array,
                                                 @Field("user_id") String user_id,
                                                 @Field("service_id") String service_id,
                                                 @Field("Message") String Message,
                                                 @Field("Latitude") String Latitude,
                                                 @Field("Longitude") String Longitude,
                                                 @Field("Partner_Id") String Partner_Id);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/reject_query.php")
    Call<RejectResponseModel> reject_Query(@Field("id") String id,
                                           @Field("service_id") String service_id,
                                           @Field("user_query") String user_query,
                                           @Field("latitude") String latitude,
                                           @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/match_otp.php")
    Call<MatchOTPResponse> match_Otp(@Field("jobid") String jobid,
                                     @Field("otp") String otp);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/complete_job.php")
    Call<CompleteJobResponse> complete_Job(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/past_jobs_list_users.php")
    Call<PastJobListingResponseModel> pastjobListings(@Field("partnerid") String partnerid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/past_jobs_details_users.php")
    Call<PastJobDetailsResponseModel> pastJobDetails(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/earning_details.php")
    Call<EarningDetailsResponseModel> earningDetails(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/upcoming_jobs_detail.php")
    Call<UpCommingDetailsResponseModel> upcommingJobDetails(@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/upcoming_jobs_list.php")
    Call<UpCommingJobListingResponseModel> upcommingJobListing(@Field("partnerid") String partnerid);


    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/earnings_partner.php")
    Call<EarningListResponseModel> earningListing(@Field("partnerid") String partnerid);

    @FormUrlEncoded
    @POST("vehiclentsss/vehiclents/apis/partner_contactusmail.php")
    Call<SendMessageResponseModel> sendMail(@Field("id") String id, @Field("message") String message);


}
