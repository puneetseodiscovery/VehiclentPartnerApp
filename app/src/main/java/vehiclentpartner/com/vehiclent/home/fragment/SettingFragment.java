package vehiclentpartner.com.vehiclent.home.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.about_us.AboutUsActivity;
import vehiclentpartner.com.vehiclent.chatAcitvity.ChatActivity;
import vehiclentpartner.com.vehiclent.contactsUsActivity.ContactsUsActivity;
import vehiclentpartner.com.vehiclent.home.homeActivity.Home;
import vehiclentpartner.com.vehiclent.home.presenter.IPLogout;
import vehiclentpartner.com.vehiclent.home.presenter.PLogOut;
import vehiclentpartner.com.vehiclent.login.LoginActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.LogoutResponseModel;
import vehiclentpartner.com.vehiclent.termandConditionActivity.TermConditionsActivity;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;


public class SettingFragment extends Fragment implements View.OnClickListener, ISettingFragment {

    View view;
    @BindView((R.id.tv_categories))
    TextView tv_categories;

    @BindView(R.id.tv_aboutus)
    TextView tv_aboutus;

    @BindView(R.id.tv_notivation)
    TextView tv_notivation;

    @BindView(R.id.tv_contactus)
    TextView tv_contactus;

    @BindView(R.id.tv_howwork)
    TextView tv_howwork;

    @BindView(R.id.tv_payment)
    TextView tv_payment;

    @BindView(R.id.tv_termcondintion)
    TextView tv_termcondintion;

    @BindView(R.id.tv_logout)
    TextView tv_logout;

    @BindView(R.id.relative_logout)
    RelativeLayout relative_logout;

    @BindView(R.id.relative_aboutus)
    RelativeLayout relative_aboutus;

    @BindView(R.id.relative_termcondition)
    RelativeLayout relative_termcondition;

    @BindView(R.id.relative_contactus)
    RelativeLayout relative_contactus;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SavePref savePref;
    IPLogout ipLogout;
    String get_uerID;
    ProgressDialog progressDialog;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        ButterKnife.bind(this, view);
        context = this.getContext();
        ipLogout = new PLogOut(this);
        savePref = new SavePref(getActivity());
        Initialization();


        return view;
    }

    private void Initialization() {

        progressDialog = new ProgressDialog(context);
        get_uerID = savePref.getid();

        tv_categories.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_aboutus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_notivation.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_contactus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_howwork.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_payment.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_termcondintion.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_logout.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        EventListner();
    }

    private void EventListner() {

        relative_logout.setOnClickListener(this);
        relative_aboutus.setOnClickListener(this);
        relative_termcondition.setOnClickListener(this);
        relative_contactus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()) {
            case R.id.relative_aboutus:
                intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;

            case R.id.relative_termcondition:
                intent = new Intent(getActivity(), TermConditionsActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_contactus:
                intent = new Intent(getActivity(), ContactsUsActivity.class);
                startActivity(intent);
                break;

            case R.id.relative_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                savePref.clearPreferences();

                                if (Utility.isNetworkConnected(context)){

                                    progressDialog = Utility.showLoader(getActivity());
                                    ipLogout.doLogout(get_uerID);

                                }else {
                                    Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
                                }




                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
    }

    @Override
    public void onLogoutFromPresenter(int statusValue) {
        progressDialog.dismiss();

    }

    @Override
    public void onLogoutSuccessFromPresenter(LogoutResponseModel logoutResponseModel) {

        progressDialog.dismiss();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onLogoutFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
