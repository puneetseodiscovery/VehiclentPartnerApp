package vehiclentpartner.com.vehiclent.forgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.baseClass.BaseClass;
import vehiclentpartner.com.vehiclent.util.Utility;

public class ForgotPassword extends BaseClass implements View.OnClickListener {

    ImageView img_back;
    TextView tv_about_forgotpassword;
    Button btn_resetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Initialization();
        EventListner();
    }

    private void Initialization() {

        img_back = (ImageView) findViewById(R.id.img_back);
        btn_resetpassword = (Button) findViewById(R.id.btn_resetpassword);
        btn_resetpassword.setTypeface(Utility.typeFaceForBoldText(this));
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

                Intent intent = new Intent(ForgotPassword.this, Long.class);
                startActivity(intent);

                break;
        }

    }

}
