package vehiclentpartner.com.vehiclent.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.earningDetails.EarningDetailsActivity;
import vehiclentpartner.com.vehiclent.pastdetails.PastDetailsActivity;

public class EarningAdapter extends RecyclerView.Adapter<EarningAdapter.MyViewHolder> {

    Context context;
    ArrayList upcomingItemList;

    public EarningAdapter(Context context , ArrayList upcomingItemList) {

        this.context = context;
        this.upcomingItemList = upcomingItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.upcoming_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EarningDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return upcomingItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

        }
    }
}
