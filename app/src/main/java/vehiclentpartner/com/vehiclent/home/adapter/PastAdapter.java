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
import vehiclentpartner.com.vehiclent.pastdetails.PastDetailsActivity;
import vehiclentpartner.com.vehiclent.upcommingDetails.UpCommingDetailsActivity;

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.MyViewHolder> {

    Context context;
    ArrayList pastItemList;

    public PastAdapter(Context context , ArrayList pastItemList) {

        this.context = context;
        this.pastItemList = pastItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_past_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PastDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pastItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

        }
    }
}
