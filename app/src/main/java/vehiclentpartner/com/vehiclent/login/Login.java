package vehiclentpartner.com.vehiclent.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.Home;
import vehiclentpartner.com.vehiclent.util.Utility;

public class Login extends AppCompatActivity {

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_pass)
    TextView tv_pass;

    @BindView(R.id.tv_forgot)
    TextView tv_forgot;

    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }

    public void init() {
        tv_forgot.setTypeface(Utility.typeFaceForBoldText(this));
    }

    @OnClick(R.id.login)
    public void onViewClicked() {

        startActivity(new Intent(Login.this, Home.class));

    }
}
