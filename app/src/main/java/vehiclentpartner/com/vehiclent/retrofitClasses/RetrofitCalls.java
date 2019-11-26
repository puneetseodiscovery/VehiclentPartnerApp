package vehiclentpartner.com.vehiclent.retrofitClasses;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
import vehiclentpartner.com.vehiclent.util.AppController;

public class RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    /* user Login api*/
    public void partnerLoginApi(String email, String password, String device_token, String latitude, String longitude, final Handler mHandler) {
        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.user_Loging(email, password, device_token, latitude, longitude);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {


                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGIN_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.LOGIN_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                message.what = apiInterface.LOGIN_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });

    }

    /*about us api*/
    public void about_us_Api(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<AboutUsResponseModel> call = apiInterface.about_us(id);
        call.enqueue(new Callback<AboutUsResponseModel>() {
            @Override
            public void onResponse(Call<AboutUsResponseModel> call, Response<AboutUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.ABOUTUS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ABOUTUS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AboutUsResponseModel> call, Throwable t) {
                message.what = apiInterface.ABOUTUS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


    /*about us api*/
    public void contactus(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<ContactsUsResponseModel> call = apiInterface.contactus(id);
        call.enqueue(new Callback<ContactsUsResponseModel>() {
            @Override
            public void onResponse(Call<ContactsUsResponseModel> call, Response<ContactsUsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.CONTACTUS_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.CONTACTUS_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ContactsUsResponseModel> call, Throwable t) {
                message.what = apiInterface.CONTACTUS_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*term condition api*/
    public void term_conditions(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<TermsConditionsResponseModel> call = apiInterface.term_conditions(id);
        call.enqueue(new Callback<TermsConditionsResponseModel>() {
            @Override
            public void onResponse(Call<TermsConditionsResponseModel> call, Response<TermsConditionsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.TERMCONDITION_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.TERMCONDITION_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<TermsConditionsResponseModel> call, Throwable t) {
                message.what = apiInterface.TERMCONDITION_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*user logout api*/
    public void user_Logout(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<LogoutResponseModel> call = apiInterface.logout_user(id);
        call.enqueue(new Callback<LogoutResponseModel>() {
            @Override
            public void onResponse(Call<LogoutResponseModel> call, Response<LogoutResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.LOGOUT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.LOGOUT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponseModel> call, Throwable t) {
                message.what = apiInterface.LOGOUT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*get userprofile api*/
    public void get_userProfile(String id, final Handler mHandler) {
        final Message message = new Message();
        Call<GetUserProfileResponseModel> call = apiInterface.get_userProfile(id);
        call.enqueue(new Callback<GetUserProfileResponseModel>() {
            @Override
            public void onResponse(Call<GetUserProfileResponseModel> call, Response<GetUserProfileResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.PROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*updateuserprofile api*/

    public void update_userProfile(String id, String first_name, String last_name, String gender, String address, String phone_number, String latitude, String longitude, MultipartBody.Part image, RequestBody imgReq, final Handler mHandler) {

        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody user_firstname = RequestBody.create(MediaType.parse("text/plain"), first_name);
        RequestBody user_last_name = RequestBody.create(MediaType.parse("text/plain"), last_name);
        RequestBody user_gender = RequestBody.create(MediaType.parse("text/plain"), gender);
        RequestBody user_phone_number = RequestBody.create(MediaType.parse("text/plain"), phone_number);
        RequestBody user_address = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody user_latitude = RequestBody.create(MediaType.parse("text/plain"), latitude);
        RequestBody user_longitude = RequestBody.create(MediaType.parse("text/plain"), longitude);

        final Message message = new Message();
        Call<UpdateUserProfileResponseModel> call = apiInterface.update_userProfile(user_id, user_firstname, user_last_name, user_gender, user_address, user_phone_number, user_latitude, user_longitude, imgReq, image);
        call.enqueue(new Callback<UpdateUserProfileResponseModel>() {
            @Override
            public void onResponse(Call<UpdateUserProfileResponseModel> call, Response<UpdateUserProfileResponseModel> response) {
                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {

                        message.what = apiInterface.UPDATE_PROFILE_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {

                        message.what = apiInterface.UPDATE_PROFILE_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);

                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateUserProfileResponseModel> call, Throwable t) {
                message.what = apiInterface.UPDATE_PROFILE_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


    /*forgotpassword */

    public void forgot_password_Api(String email, final Handler mHandler) {
        final Message message = new Message();
        Call<ForgotPasswordResponse> call = apiInterface.forgot_Password(email);
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (response.body() != null) {

                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.FORGOT_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);

                    } else {
                        message.what = apiInterface.FORGOT_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                message.what = apiInterface.FORGOT_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);

            }
        });
    }


    /*request accept  api*/
    public void acceptApi(String ids_array, String user_id, String service_id, String Message, String Latitude, String Longitude, String Partner_Id, final Handler mHandler) {
        final Message message = new Message();
        Call<AcceptRejectResponseModel> call = apiInterface.accept_Query(ids_array, user_id, service_id, Message, Latitude, Longitude, Partner_Id);
        call.enqueue(new Callback<AcceptRejectResponseModel>() {
            @Override
            public void onResponse(Call<AcceptRejectResponseModel> call, Response<AcceptRejectResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.ACCEPT_QUERY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.ACCEPT_QUERY_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AcceptRejectResponseModel> call, Throwable t) {
                message.what = apiInterface.ACCEPT_QUERY_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*request reject  api*/
    public void rejectApi(String id, String service_id, String user_query, String latitude, String longitude, final Handler mHandler) {
        final Message message = new Message();

        Call<RejectResponseModel> call = apiInterface.reject_Query(id, service_id, user_query, latitude, longitude);
        call.enqueue(new Callback<RejectResponseModel>() {
            @Override
            public void onResponse(Call<RejectResponseModel> call, Response<RejectResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.REJECT_QUERY_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.REJECT_QUERY_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<RejectResponseModel> call, Throwable t) {
                message.what = apiInterface.REJECT_QUERY_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*request match otp   api*/
    public void match_Otp(String jobid, String otp, final Handler mHandler) {
        final Message message = new Message();

        Call<MatchOTPResponse> call = apiInterface.match_Otp(jobid, otp);
        call.enqueue(new Callback<MatchOTPResponse>() {
            @Override
            public void onResponse(Call<MatchOTPResponse> call, Response<MatchOTPResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.MATCH_OTP_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.MATCH_OTP_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<MatchOTPResponse> call, Throwable t) {
                message.what = apiInterface.MATCH_OTP_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }

    /*complete   api*/
    public void complete_Job(String jobid, final Handler mHandler) {
        final Message message = new Message();

        Call<CompleteJobResponse> call = apiInterface.complete_Job(jobid);
        call.enqueue(new Callback<CompleteJobResponse>() {
            @Override
            public void onResponse(Call<CompleteJobResponse> call, Response<CompleteJobResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        message.what = apiInterface.COMPLETE_JOB_SUCCESS;
                        message.obj = response.body();
                        mHandler.sendMessage(message);
                    } else {
                        message.what = apiInterface.COMPLETE_JOB_FAILED;
                        message.obj = response.body().getMessage();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<CompleteJobResponse> call, Throwable t) {
                message.what = apiInterface.COMPLETE_JOB_FAILED;
                message.obj = t.getMessage();
                mHandler.sendMessage(message);
            }
        });
    }


    /*past job listing */
    public void pastjobListing_Api(String partnerid, final Handler mHandler) {
        final Message messages = new Message();
        Call<PastJobListingResponseModel> call = apiInterface.pastjobListings(partnerid);
        call.enqueue(new Callback<PastJobListingResponseModel>() {
            @Override
            public void onResponse(Call<PastJobListingResponseModel> call, Response<PastJobListingResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.PAST_JOB_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else if (response.body().getStatus() == 400){
                        messages.what = apiInterface.PAST_JOB_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }else {
                        messages.what = apiInterface.PAST_JOB_EMPTY;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<PastJobListingResponseModel> call, Throwable t) {
                messages.what = apiInterface.PAST_JOB_SUCCESS;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }


    /*past job listing */
    public void upcommingJobListing_Api(String partnerid, final Handler mHandler) {
        final Message messages = new Message();
        Call<UpCommingJobListingResponseModel> call = apiInterface.upcommingJobListing(partnerid);
        call.enqueue(new Callback<UpCommingJobListingResponseModel>() {
            @Override
            public void onResponse(Call<UpCommingJobListingResponseModel> call, Response<UpCommingJobListingResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.UPCOMMING_JOBLIST_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else if (response.body().getStatus() == 400){
                        messages.what = apiInterface.UPCOMMING_JOBLIST_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }else {
                        messages.what = apiInterface.UPCOMMING_LISTING_EMPTY;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpCommingJobListingResponseModel> call, Throwable t) {
                messages.what = apiInterface.UPCOMMING_JOBLIST_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*past job listing */
    public void earningListing_Api(String partnerid, final Handler mHandler) {
        final Message messages = new Message();
        Call<EarningListResponseModel> call = apiInterface.earningListing(partnerid);
        call.enqueue(new Callback<EarningListResponseModel>() {
            @Override
            public void onResponse(Call<EarningListResponseModel> call, Response<EarningListResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.EARNING_LISTING_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else if (response.body().getStatus() == 400) {
                        messages.what = apiInterface.EARNING_LISTING_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.EARNING_LISTING_EMPTY;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<EarningListResponseModel> call, Throwable t) {
                messages.what = apiInterface.EARNING_LISTING_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }


    /*past job detiald */
    public void pastjobDetails_Api(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<PastJobDetailsResponseModel> call = apiInterface.pastJobDetails(jobid);
        call.enqueue(new Callback<PastJobDetailsResponseModel>() {
            @Override
            public void onResponse(Call<PastJobDetailsResponseModel> call, Response<PastJobDetailsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.PASTJOBDETAILS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.PASTJOBDETAILS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<PastJobDetailsResponseModel> call, Throwable t) {
                messages.what = apiInterface.PASTJOBDETAILS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*past job detiald */
    public void upcommingDetails_Api(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<UpCommingDetailsResponseModel> call = apiInterface.upcommingJobDetails(jobid);
        call.enqueue(new Callback<UpCommingDetailsResponseModel>() {
            @Override
            public void onResponse(Call<UpCommingDetailsResponseModel> call, Response<UpCommingDetailsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.UPCOMMING_DETAILS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.UPCOMMING_DETAILS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpCommingDetailsResponseModel> call, Throwable t) {
                messages.what = apiInterface.UPCOMMING_DETAILS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }

    /*past job detiald */
    public void earningDetails_Api(String jobid, final Handler mHandler) {
        final Message messages = new Message();
        Call<EarningDetailsResponseModel> call = apiInterface.earningDetails(jobid);
        call.enqueue(new Callback<EarningDetailsResponseModel>() {
            @Override
            public void onResponse(Call<EarningDetailsResponseModel> call, Response<EarningDetailsResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.EARNING_DETAILS_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.EARNING_DETAILS_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<EarningDetailsResponseModel> call, Throwable t) {
                messages.what = apiInterface.EARNING_DETAILS_FAILED;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }


    public void sendMessage_Api(String id, String message, final Handler mHandler) {
        final Message messages = new Message();
        Call<SendMessageResponseModel> call = apiInterface.sendMail(id, message);
        call.enqueue(new Callback<SendMessageResponseModel>() {
            @Override
            public void onResponse(Call<SendMessageResponseModel> call, Response<SendMessageResponseModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus() == 200) {
                        messages.what = apiInterface.MAIL_SUCCESS;
                        messages.obj = response.body();
                        mHandler.sendMessage(messages);
                    } else {
                        messages.what = apiInterface.MATCH_OTP_FAILED;
                        messages.obj = response.body().getMessage();
                        mHandler.sendMessage(messages);
                    }
                }
            }

            @Override
            public void onFailure(Call<SendMessageResponseModel> call, Throwable t) {
                messages.what = apiInterface.MAIL_SUCCESS;
                messages.obj = t.getMessage();
                mHandler.sendMessage(messages);
            }
        });
    }
}
