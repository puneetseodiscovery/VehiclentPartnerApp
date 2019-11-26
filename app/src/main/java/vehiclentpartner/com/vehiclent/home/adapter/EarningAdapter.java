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
import vehiclentpartner.com.vehiclent.earningDetails.EarningDetailsActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.EarningListResponseModel;
import vehiclentpartner.com.vehiclent.util.Utility;

public class EarningAdapter extends RecyclerView.Adapter<EarningAdapter.MyViewHolder> {

    Context context;
    List<EarningListResponseModel.Datum> earningList;

    public EarningAdapter(Context context, List<EarningListResponseModel.Datum> upcomingItemList) {

        this.context = context;
        this.earningList = upcomingItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.upcoming_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

        Glide.with(context).load(earningList.get(position).getProfilePic())
                .error(R.drawable.placeholder)
                .into(myViewHolder.img_vehical);

        myViewHolder.tv_jobsname.setText(earningList.get(position).getFirstName() + " " + earningList.get(position).getLastName());
        myViewHolder.tv_jobstime.setText(earningList.get(position).getCreateddate());
        myViewHolder.tv_car_care.setText(earningList.get(position).getServiceName());
       // myViewHolder.tv_car_model.setText("Car Model : " + upcomingItemList.get(position).getServiceName());


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EarningDetailsActivity.class);
                intent.putExtra("job_id",earningList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return earningList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_jobsname)
        TextView tv_jobsname;

        @BindView(R.id.tv_jobstime)
        TextView tv_jobstime;

        @BindView(R.id.tv_car_care)
        TextView tv_car_care;

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

        }
    }
}
