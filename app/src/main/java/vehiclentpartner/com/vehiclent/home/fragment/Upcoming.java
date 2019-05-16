package vehiclentpartner.com.vehiclent.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.adapter.UpcomingAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Upcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Upcoming extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.upcomingRecyclerView)
    RecyclerView upcomingRecyclerView;

    ArrayList upcomingItemList = new ArrayList();


    public Upcoming() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Upcoming.
     */
    // TODO: Rename and change types and number of parameters
    public static Upcoming newInstance(String param1, String param2) {
        Upcoming fragment = new Upcoming();
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
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this,view);
        init();
        // Inflate the layout for this fragment
        return view;
    }

    private void init()
    {

        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");
        upcomingItemList.add("First");

        UpcomingAdapter upcomingAdapter = new UpcomingAdapter(getActivity(),upcomingItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        upcomingRecyclerView.setLayoutManager(layoutManager);
        upcomingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(upcomingRecyclerView.getContext(),1);
        upcomingRecyclerView.addItemDecoration(dividerItemDecoration);
        upcomingRecyclerView.setAdapter(upcomingAdapter);


    }

}
