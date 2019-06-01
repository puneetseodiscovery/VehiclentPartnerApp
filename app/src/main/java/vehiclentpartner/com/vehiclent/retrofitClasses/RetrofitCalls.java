package vehiclentpartner.com.vehiclent.retrofitClasses;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vehiclentpartner.com.vehiclent.login.BeanLogin;
import vehiclentpartner.com.vehiclent.responseModelClasses.AboutUsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.GetUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.TermsConditionsResponseModel;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpdateUserProfileResponseModel;
import vehiclentpartner.com.vehiclent.util.AppController;

public class RetrofitCalls {

    public APIInterface apiInterface;

    public RetrofitCalls() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    /* user Login api*/
    public void partnerLoginApi(String email, String password, final Handler mHandler) {
        final Message message = new Message();
        Call<LoginResponseModel> call = apiInterface.user_Loging(email, password);
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
                        message.obj = response.body();
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
    public void update_userProfile(String id, String first_name, String last_name, String gender, String address, String phone_number, final Handler mHandler) {
        final Message message = new Message();
        Call<UpdateUserProfileResponseModel> call = apiInterface.update_userProfile(id,first_name,last_name,gender,address,phone_number);
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
}
