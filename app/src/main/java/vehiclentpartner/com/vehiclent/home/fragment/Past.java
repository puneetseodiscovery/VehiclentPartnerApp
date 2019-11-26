package vehiclentpartner.com.vehiclent.home.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.adapter.PastAdapter;
import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IPPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PPastJobsFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;


public class Past extends Fragment implements IPastJobsFragment {

    @BindView(R.id.pastRecyclerView)
    RecyclerView pastRecyclerView;

    @BindView(R.id.img_nodata)
    ImageView img_nodata;

    PastAdapter pastAdapter;
    Context context;
    ProgressDialog progressDialog;
    SavePref savePref;
    IPPastJobsFragment ipPastJobsFragment;

    String partner_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_past, container, false);

        ButterKnife.bind(this,view);
        context = this.getContext();
        progressDialog = new ProgressDialog(context);
        savePref = new SavePref(context);
        partner_id = savePref.getid();
        ipPastJobsFragment = new PPastJobsFragment(this);


        if (Utility.isNetworkConnected(context)){
            progressDialog = Utility.showLoader(context);
            ipPastJobsFragment.doPastJobsList(partner_id);

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onPastJobFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onPastJobSuccessFromPresenter(PastJobListingResponseModel pastJobListingResponseModel) {
        progressDialog.dismiss();
        if (pastJobListingResponseModel != null && pastJobListingResponseModel.getData().size() > 0) {
            img_nodata.setVisibility(View.GONE);
            pastAdapter = new PastAdapter(context,pastJobListingResponseModel.getData());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            pastRecyclerView.setLayoutManager(layoutManager);
            pastRecyclerView.setItemAnimator(new DefaultItemAnimator());
            pastRecyclerView.setAdapter(pastAdapter);

        } else {

        }
    }

    @Override
    public void onPastJobFailedFromPresenter(String messge) {
        progressDialog.dismiss();
    }

    @Override
    public void onPastJobEmptyFromPresenter(String messge) {
        progressDialog.dismiss();
        img_nodata.setVisibility(View.VISIBLE);
      //  Toast.makeText(context, ""+messge, Toast.LENGTH_SHORT).show();
    }
}
