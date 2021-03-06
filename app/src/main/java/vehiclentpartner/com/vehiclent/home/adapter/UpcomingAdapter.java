package vehiclentpartner.com.vehiclent.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.congratulations.CongratulationActivity;
import vehiclentpartner.com.vehiclent.optActivity.OTPActivity;
import vehiclentpartner.com.vehiclent.pastdetails.PastDetailsActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.UpCommingJobListingResponseModel;
import vehiclentpartner.com.vehiclent.upcommingDetails.UpCommingDetailsActivity;
import vehiclentpartner.com.vehiclent.util.Utility;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder> {

    Context context;
    List<UpCommingJobListingResponseModel.Datum> upcomingItemList;

    public UpcomingAdapter(Context context,List<UpCommingJobListingResponseModel.Datum> upcomingItemList) {

        this.context = context;
        this.upcomingItemList = upcomingItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_upcoming_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int position) {

        Glide.with(context).load(upcomingItemList.get(position).getProfilePic())
                .placeholder(R.drawable.placeholder)
                .into(myViewHolder.img_vehical);

        myViewHolder.tv_jobsname.setText(upcomingItemList.get(position).getFirstName() + " " + upcomingItemList.get(position).getLastName());
        myViewHolder.tv_jobstime.setText(upcomingItemList.get(position).getCreateddate());
        myViewHolder.tv_car_care.setText(upcomingItemList.get(position).getServiceName());
       // myViewHolder.tv_car_model.setText("Car Model : " + upcomingItemList.get(position).getServiceName());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (upcomingItemList.get(position).getPayment().equals("0")){

                    Intent intent=new Intent(context, OTPActivity.class);
                    intent.putExtra("job_id", upcomingItemList.get(position).getId());
                    intent.putExtra("service_name", upcomingItemList.get(position).getServiceName());
                    intent.putExtra("user_id", upcomingItemList.get(position).getUserid());
                    intent.putExtra("first_name", upcomingItemList.get(position).getFirstName());
                    intent.putExtra("last_name", upcomingItemList.get(position).getLastName());

                    context.startActivity(intent);
                }else {
                    Intent intent=new Intent(context, CongratulationActivity.class);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return upcomingItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_jobsname)
        TextView tv_jobsname;

        @BindView(R.id.tv_jobstime)
        TextView tv_jobstime;

        @BindView(R.id.tv_car_care)
        TextView tv_car_care;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.tv_status)
        TextView tv_status;

        @BindView(R.id.tv_car_model)
        TextView tv_car_model;

        @BindView(R.id.img_vehical)
        CircleImageView img_vehical;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tv_jobsname.setTypeface(Utility.typeFaceForBoldText(context));
            tv_jobstime.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_car_care.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_car_model.setTypeface(Utility.typeFaceForRegulerText(context));
            status.setTypeface(Utility.typeFaceForRegulerText(context));
            tv_status.setTypeface(Utility.typeFaceForRegulerText(context));
        }
    }
}
