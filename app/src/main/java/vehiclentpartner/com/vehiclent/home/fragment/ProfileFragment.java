package vehiclentpartner.com.vehiclent.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    View view;

    @BindView(R.id.tv_userprofile)
    TextView tv_userprofile;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.mobile)
    TextView mobile;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.edit_user_name)
    EditText edit_user_name;

    @BindView(R.id.edit_user_email)
    EditText edit_user_email;

    @BindView(R.id.edit_user_mobile)
    EditText edit_user_mobile;

    @BindView(R.id.edit_user_address)
    EditText edit_user_address;

    @BindView(R.id.img_edit)
    ImageView img_edit;

    @BindView(R.id.img_done)
    ImageView img_done;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        Initialization();
        return view;
    }

    private void Initialization() {

        tv_userprofile.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_username.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_name.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_email.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_mobile.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        edit_user_address.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        edit_user_name.setEnabled(false);
        edit_user_email.setEnabled(false);
        edit_user_mobile.setEnabled(false);
        edit_user_address.setEnabled(false);


        EventListner();

    }

    private void EventListner() {

        img_edit.setOnClickListener(this);
        img_done.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_edit:

                img_edit.setVisibility(View.GONE);
                img_done.setVisibility(View.VISIBLE);


                edit_user_name.setEnabled(true);
                edit_user_email.setEnabled(true);
                edit_user_mobile.setEnabled(true);
                edit_user_address.setEnabled(true);


                break;
            case R.id.img_done:

                img_done.setVisibility(View.GONE);
                img_edit.setVisibility(View.VISIBLE);

                edit_user_name.setEnabled(false);
                edit_user_email.setEnabled(false);
                edit_user_mobile.setEnabled(false);
                edit_user_address.setEnabled(false);

                break;
        }
    }
}
