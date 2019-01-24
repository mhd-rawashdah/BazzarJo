package edu.zu.demo.graduation.bazzar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.zu.demo.graduation.bazzar.BasicActivity.StoerePageActivity;
import edu.zu.demo.graduation.bazzar.Model.Seller;
import edu.zu.demo.graduation.bazzar.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    ArrayList<Seller> arrayList=new ArrayList<>();
    Context mContext;

    public SearchAdapter(ArrayList<Seller> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.search_contianer_row, parent, false);
        final SearchAdapter.MyViewHolder viewHolder = new SearchAdapter.MyViewHolder(itemView);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StoerePageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("seller_Id",arrayList.get(viewHolder.getAdapterPosition()).getId());
                bundle.putString("phoneNumber",arrayList.get(viewHolder.getAdapterPosition()).getPhoneNumber());
                bundle.putString("storeName", arrayList.get(viewHolder.getAdapterPosition()).getStoreName());
                bundle.putString("storeType",arrayList.get(viewHolder.getAdapterPosition()).getStoreType());
                bundle.putBoolean("status", arrayList.get(viewHolder.getAdapterPosition()).getState());
                bundle.putBoolean("isDeliveryService",arrayList.get(viewHolder.getAdapterPosition()).isDeliveryService());
                bundle.putBoolean("isOnlinePayment",arrayList.get(viewHolder.getAdapterPosition()).isOnlinePaymentService());
                bundle.putDouble("deliveryPrice",arrayList.get(viewHolder.getAdapterPosition()).getDeliveryPrice());
                bundle.putDouble("minimumPrice",arrayList.get(viewHolder.getAdapterPosition()).getMinimumPrice());
                bundle.putString("deliveryTime", arrayList.get(viewHolder.getAdapterPosition()).getTimeDelivery());
                bundle.putDouble("ratingStore", arrayList.get(viewHolder.getAdapterPosition()).getRatting());
                bundle.putInt("numFeedback",arrayList.get(viewHolder.getAdapterPosition()).getNumberFeedback());
                bundle.putString("imageStore", arrayList.get(viewHolder.getAdapterPosition()).getImageProfile());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      holder.storeNameTxt.setText(arrayList.get(position).getStoreName());
      holder.storeTypeTxt.setText(arrayList.get(position).getStoreType());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
         TextView storeNameTxt;
         TextView storeTypeTxt;
         LinearLayout container;
        public MyViewHolder(View itemView) {
            super(itemView);
            storeNameTxt=itemView.findViewById(R.id.search_container_storeNameTxt);
            storeTypeTxt=itemView.findViewById(R.id.search_container_storeTypeTxt);
            container=itemView.findViewById(R.id.search_container_container);
        }
    }
}
