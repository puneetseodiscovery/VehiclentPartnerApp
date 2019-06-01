package vehiclentpartner.com.vehiclent.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.home.homeActivity.Home;
import vehiclentpartner.com.vehiclent.responseModelClasses.LoginResponseModel;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;

public class LoginActivity extends BaseClass implements ILoginActivity {

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_pass)
    TextView tv_pass;

    @BindView(R.id.tv_forgot)
    TextView tv_forgot;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.et_username)
    EditText et_username;

    @BindView(R.id.et_pass)
    EditText et_pass;

    LoginActivity context;

    IPLogin interfacePresenterLogin;
    ProgressDialog progressDialog;
    int treatmentActivity;

    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        savePref=new SavePref(this);
        context = LoginActivity.this;
        ButterKnife.bind(this);
        init();

    }

    public void init() {

        interfacePresenterLogin = new PLogin(this);

        tv_forgot.setTypeface(Utility.typeFaceForBoldText(this));
    }

    @OnClick(R.id.login)
    public void onViewClicked() {

        if (Utility.isNetworkConnected(context)) {

            if (et_username.getText().toString().length() > 0 && et_pass.getText().toString().length() > 0) {


                if (Utility.validEmail(et_username.getText().toString().trim())) {

                    progressDialog = Utility.showLoader(LoginActivity.this);
                    interfacePresenterLogin.doLogin(et_username.getText().toString().trim(),
                            et_pass.getText().toString().trim());

                } else {
                    et_username.setError("Enter a valid email.");
                    et_username.requestFocus();
                }
            } else {
                if (et_username.getText().toString().length() == 0 && et_pass.getText().toString().length() == 0) {
                    et_username.setError("Enter a valid email.");
                    et_pass.setError("Enter password");
                    et_username.requestFocus();
                } else if (et_pass.getText().toString().length() == 0) {
                    et_pass.setError("Enter password");
                    et_pass.requestFocus();
                } else if (et_username.getText().toString().length() == 0) {
                    et_username.setError("Enter a valid email.");
                    et_username.requestFocus();
                }
                //Toast.makeText(this, "Enter correct login details.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /* Methods implement form login interface*/
    @Override
    public void onLoginResponseFromPresenter(int statusValue) {

        Toast.makeText(this, "" + statusValue, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, Home.class);
        startActivity(intent);
    }

    @Override
    public void onLoginSuccessFromPresenter(LoginResponseModel loginResponseModel) {

        progressDialog.dismiss();
        savePref.setStatus(loginResponseModel.getData().get(0).getStatus());
        savePref.setid(loginResponseModel.getData().get(0).getId());
        savePref.setuser_name(loginResponseModel.getData().get(0).getUserName());
        savePref.setuser_email(loginResponseModel.getData().get(0).getEmail());

        //if (treatmentActivity == 0) {
            Intent intent = new Intent(LoginActivity.this, Home.class);
            startActivity(intent);
            finish();

        /*} else {

            onBackPressed();
            finish();
        }*/
    }

    @Override
    public void onLoginFailedFromPresenter(String message) {

        Toast.makeText(this, "login Failed" + message, Toast.LENGTH_SHORT).show();
        // progressDialog.dismiss();


    }
}
