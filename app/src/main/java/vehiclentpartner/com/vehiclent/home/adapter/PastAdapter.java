package vehiclentpartner.com.vehiclent.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.pastdetails.PastDetailsActivity;
import vehiclentpartner.com.vehiclent.responseModelClasses.PastJobListingResponseModel;
import vehiclentpartner.com.vehiclent.upcommingDetails.UpCommingDetailsActivity;
import vehiclentpartner.com.vehiclent.util.Utility;

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.MyViewHolder> {

    Context context;
    List<PastJobListingResponseModel.Datum> pastjobresponseModelList;

    public PastAdapter(Context context, List<PastJobListingResponseModel.Datum> pastjobresponseModelList) {

        this.context = context;
        this.pastjobresponseModelList = pastjobresponseModelList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_past_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

        //String job_id=pastjobresponseModelList.get(position).getId();

        Glide.with(context).load(pastjobresponseModelList.get(position).getProfilePic())
                .placeholder(R.drawable.placeholder)
                .into(myViewHolder.img_vehical);

        myViewHolder.tv_jobsname.setText(pastjobresponseModelList.get(position).getFirstName() + " " + pastjobresponseModelList.get(position).getLastName());
        myViewHolder.tv_jobstime.setText(pastjobresponseModelList.get(position).getCreateddate());
        myViewHolder.tv_car_care.setText(pastjobresponseModelList.get(position).getServiceName());
       // myViewHolder.tv_car_model.setText("Car Model : " + pastjobresponseModelList.get(position).getServiceName());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PastDetailsActivity.class);
                intent.putExtra("job_id", pastjobresponseModelList.get(position).getId());
                intent.putExtra("job_images",pastjobresponseModelList.get(position).getProfilePic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pastjobresponseModelList.size();
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
