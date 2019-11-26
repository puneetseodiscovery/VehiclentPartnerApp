package vehiclentpartner.com.vehiclent.forgotPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.responseModelClasses.ForgotPasswordResponse;
import vehiclentpartner.com.vehiclent.util.Utility;

public class ForgotPassword extends BaseClass implements View.OnClickListener, IForgotPassword {

    ImageView img_back;
    EditText edit_usereamil;
    TextView tv_about_forgotpassword;
    Button btn_resetpassword;
    ForgotPassword context;
    ProgressDialog progressDialog;
    IPForgotPassword ipForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Initialization();
        EventListner();
    }

    private void Initialization() {
        context = ForgotPassword.this;
        progressDialog = new ProgressDialog(this);
        ipForgotPassword=new PForgotPassword(this);
        img_back = (ImageView) findViewById(R.id.img_back);
        btn_resetpassword = (Button) findViewById(R.id.btn_resetpassword);
        btn_resetpassword.setTypeface(Utility.typeFaceForBoldText(this));
        edit_usereamil = (EditText) findViewById(R.id.edit_usereamil);
        tv_about_forgotpassword = (TextView) findViewById(R.id.tv_about_forgotpassword);
        tv_about_forgotpassword.setTypeface(Utility.typeFaceForBoldText(this));
    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        btn_resetpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_back:

                finish();
                break;
            case R.id.btn_resetpassword:
                if (Utility.isNetworkConnected(context)){

                    if (edit_usereamil.getText().toString().trim().isEmpty()||edit_usereamil.getText().toString().trim().equals(""))
                    {
                        edit_usereamil.setError("Please enter your email");
                        edit_usereamil.requestFocus();
                    }else {
                        progressDialog = Utility.showLoader(context);
                        ipForgotPassword.doForgotPassword(edit_usereamil.getText().toString().trim());

                    }


                }else {
                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onForgotPasswordResponseFromPresenter(int statusValue) {

    }

    @Override
    public void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponse forgotPasswordResponse) {

    }

    @Override
    public void onForgotPasswordFailedResponseFromPresenter(String message) {

    }
}
