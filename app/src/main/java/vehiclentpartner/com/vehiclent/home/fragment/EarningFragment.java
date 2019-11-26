package vehiclentpartner.com.vehiclent.home.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.home.adapter.EarningAdapter;
import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.fragment.IEarningFragment.IPEarningListingFragment;
import vehiclentpartner.com.vehiclent.home.presenter.PEarningFragmnet;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;
import vehiclentpartner.com.vehiclent.util.SavePref;
import vehiclentpartner.com.vehiclent.util.Utility;

public class EarningFragment extends Fragment implements IEarningListingFragment {

    @BindView(R.id.upcomingRecyclerView)
    RecyclerView upcomingRecyclerView;

    @BindView(R.id.tv_totalEarning)
    TextView tv_totalEarning;

    @BindView(R.id.img_nodata)
    ImageView img_nodata;

    Context context;
    ProgressDialog progressDialog;
    SavePref savePref;
    String partner_id="",totalearning="";

    EarningAdapter upcomingAdapter;
    IPEarningListingFragment ipEarningListingFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_earning, container, false);
        ButterKnife.bind(this, view);

        context = this.getContext();
        progressDialog = new ProgressDialog(context);
        savePref = new SavePref(context);
        partner_id = savePref.getid();
        ipEarningListingFragment = new PEarningFragmnet(this);

        if (Utility.isNetworkConnected(context)){
            progressDialog=Utility.showLoader(context);
            ipEarningListingFragment.doearningListing(partner_id);

        }else {
            Toast.makeText(context, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    @Override
    public void onearningListingFromPresenter(int statusValue) {

        progressDialog.dismiss();

    }

    @Override
    public void onearningListingSuccessFromPresenter(EarningListResponseModel earningListResponseModel) {

        progressDialog.dismiss();

       String totalearning=earningListResponseModel.getTotalearning();
        tv_totalEarning.setText("Total Earning"+" "+"\u20B9"+""+  totalearning);

        if (earningListResponseModel != null && earningListResponseModel.getData().size() > 0) {
            img_nodata.setVisibility(View.GONE);
            upcomingAdapter = new EarningAdapter(context, earningListResponseModel.getData());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            upcomingRecyclerView.setLayoutManager(layoutManager);

            upcomingRecyclerView.setAdapter(upcomingAdapter);

        } else {

        }
    }
    @Override
    public void onearningListingFailedFromPresenter(String messge) {
        progressDialog.dismiss();
    }

    @Override
    public void onearningListingEmptyFromPresenter(String messge) {
        progressDialog.dismiss();
        img_nodata.setVisibility(View.VISIBLE);
        Toast.makeText(context, ""+messge, Toast.LENGTH_SHORT).show();
      //  Glide.with(this).load(R.raw.no_images).into(img_nodata);
    }


}
