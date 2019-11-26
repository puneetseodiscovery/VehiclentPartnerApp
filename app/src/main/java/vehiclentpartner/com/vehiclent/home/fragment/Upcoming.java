package vehiclentpartner.com.vehiclent.home.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.adapter.PastAdapter;
import vehiclentpartner.com.vehiclent.home.adapter.UpcomingAdapter;
import vehiclentpartner.com.vehiclent.home.fragment.IPastJobs.IPPastJobsFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IPUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IUpcomming.IUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PUpcoomingJobListingFragment;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;

public class Upcoming extends Fragment implements IUpcoomingJobListingFragment {

    @BindView(R.id.upcomingRecyclerView)
    RecyclerView upcomingRecyclerView;

    @BindView(R.id.img_nodata)
    ImageView img_nodata;

    UpcomingAdapter upcomingAdapter;
    Context context;
    ProgressDialog progressDialog;
    SavePref savePref;

    IPUpcoomingJobListingFragment iUpcoomingJobListingFragment;

    String partner_id;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, view);

        context = this.getContext();
        progressDialog = new ProgressDialog(context);
        savePref = new SavePref(context);
        iUpcoomingJobListingFragment=new PUpcoomingJobListingFragment(this);
        partner_id = savePref.getid();

        if (Utility.isNetworkConnected(context)){
            progressDialog=Utility.showLoader(context);
            iUpcoomingJobListingFragment.doUpcomingJobListing(partner_id);

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }



        return view;
    }

    @Override
    public void onUpcomingJobListingFromPresenter(int statusValue) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpcomingJobListingSuccessFromPresenter(UpCommingJobListingResponseModel upCommingJobListingResponseModel) {

        progressDialog.dismiss();

        if (upCommingJobListingResponseModel != null && upCommingJobListingResponseModel.getData().size() > 0) {
            img_nodata.setVisibility(View.GONE);
            upcomingAdapter = new UpcomingAdapter(context, upCommingJobListingResponseModel.getData());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            upcomingRecyclerView.setLayoutManager(layoutManager);
            upcomingRecyclerView.setAdapter(upcomingAdapter);

        } else {

        }
    }

    @Override
    public void onUpcomingJobListingFailedFromPresenter(String messge) {
        progressDialog.dismiss();
    }

    @Override
    public void onUpcomingJobListingEmptyResponseFromPresenter(String message) {
        progressDialog.dismiss();
        img_nodata.setVisibility(View.VISIBLE);
      //  Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }
}
