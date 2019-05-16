package vehiclentpartner.com.vehiclent.home.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        view= inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);

        tv_categories.setTypeface(Utility.typeFaceForBoldText(getActivity()));
        tv_aboutus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_notivation.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_contactus.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_howwork.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_payment.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_termcondintion.setTypeface(Utility.typeFaceForRegulerText(getActivity()));
        tv_logout.setTypeface(Utility.typeFaceForRegulerText(getActivity()));

        return view;
    }
}
