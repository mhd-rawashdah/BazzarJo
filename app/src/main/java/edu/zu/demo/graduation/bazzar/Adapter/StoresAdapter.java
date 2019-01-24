package edu.zu.demo.graduation.bazzar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.zu.demo.graduation.bazzar.Model.Bus;
import edu.zu.demo.graduation.bazzar.Model.Seller;
import edu.zu.demo.graduation.bazzar.R;
import edu.zu.demo.graduation.bazzar.BasicActivity.StoerePageActivity;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.MyViewHolder> {
    private ArrayList<Seller> listStore;
    private Context mContext;

    public StoresAdapter(Context mContext, ArrayList<Seller> listStore) {
        this.listStore = listStore;
        this.mContext = mContext;
    }

    @Override
    public StoresAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.store_row, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolder.cardViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StoerePageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("seller_Id",listStore.get(viewHolder.getAdapterPosition()).getId());
                bundle.putString("phoneNumber",listStore.get(viewHolder.getAdapterPosition()).getPhoneNumber());
                bundle.putString("storeName", listStore.get(viewHolder.getAdapterPosition()).getStoreName());
                bundle.putString("storeType",listStore.get(viewHolder.getAdapterPosition()).getStoreType());
                bundle.putBoolean("status", listStore.get(viewHolder.getAdapterPosition()).getState());
                bundle.putBoolean("isDeliveryService",listStore.get(viewHolder.getAdapterPosition()).isDeliveryService());
                bundle.putBoolean("isOnlinePayment",listStore.get(viewHolder.getAdapterPosition()).isOnlinePaymentService());
                bundle.putDouble("deliveryPrice",listStore.get(viewHolder.getAdapterPosition()).getDeliveryPrice());
                bundle.putDouble("minimumPrice",listStore.get(viewHolder.getAdapterPosition()).getMinimumPrice());
                bundle.putString("deliveryTime", listStore.get(viewHolder.getAdapterPosition()).getTimeDelivery());
                bundle.putDouble("ratingStore", listStore.get(viewHolder.getAdapterPosition()).getRatting());
                bundle.putInt("numFeedback",listStore.get(viewHolder.getAdapterPosition()).getNumberFeedback());
                bundle.putString("imageStore", listStore.get(viewHolder.getAdapterPosition()).getImageProfile());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoresAdapter.MyViewHolder holder, int position) {
        Seller seller = listStore.get(position);

        holder.storeName.setText(seller.getStoreName());
        holder.storeType.setText(seller.getStoreType());
        if (seller.getState()) {
            holder.stateStore.setText(R.string.online);
            holder.stateStore.setTextColor(Color.parseColor("#09c606"));
        } else {
            holder.stateStore.setText(R.string.closed);
            holder.stateStore.setTextColor(Color.GRAY);
        }

        holder.ratingBar.setRating((float) seller.getRatting());
        holder.numFeedback.setText("(" + seller.getNumberFeedback() + ")");
        holder.timeDelivery.setText("Time: " + seller.getTimeDelivery() + " m");
        if (!seller.isDeliveryService()) {
            holder.shippingImage.setVisibility(View.GONE);
        } else {
            holder.shippingImage.setVisibility(View.VISIBLE);
        }
        if (!seller.isOnlinePaymentService()){
            holder.onlinePayImage.setVisibility(View.GONE);
        }else
        {
            holder.onlinePayImage.setVisibility(View.VISIBLE);
        }

        Picasso.with(mContext).load(seller.getImageProfile()).placeholder(R.drawable.loading_background)
                .error(R.drawable.loading_background)
                .into(holder.storeImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        //  Toast.makeText(mContext,"sucess download",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public int getItemCount() {
        return listStore.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView storeImage, shippingImage, cashPayImage, onlinePayImage;
        TextView storeName, storeType, stateStore, numFeedback, timeDelivery;
        RatingBar ratingBar;
        CardView cardViewContainer;

        public MyViewHolder(View itemView) {
            super(itemView);
            storeImage = itemView.findViewById(R.id.StoreRaw_storeImage);
            storeName = itemView.findViewById(R.id.StoreRaw_storeName);
            storeType = itemView.findViewById(R.id.StoreRaw_storeType);
            stateStore = itemView.findViewById(R.id.StoreRaw_sate);
            timeDelivery = itemView.findViewById(R.id.StoreRaw_timeDelivery);
            cardViewContainer = itemView.findViewById(R.id.StoreRaw_container);
            ratingBar = itemView.findViewById(R.id.StoreRaw_ratingBar);
            numFeedback = itemView.findViewById(R.id.StoreRaw_numberFeedbacks);

            // not use yet
            cashPayImage = itemView.findViewById(R.id.StoreRaw_cashPayImg);
            onlinePayImage = itemView.findViewById(R.id.StoreRaw_onlinePayImg);
            shippingImage = itemView.findViewById(R.id.StoreRaw_shippingImg);
        }
    }
}
