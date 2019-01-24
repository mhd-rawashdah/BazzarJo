package edu.zu.demo.graduation.bazzar.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.zu.demo.graduation.bazzar.Model.Offer;
import edu.zu.demo.graduation.bazzar.R;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {
    ArrayList<Offer> offerArrayList;

    public OffersAdapter(ArrayList<Offer> offerArrayList){
        this.offerArrayList=offerArrayList;
    }

    @Override
    public  OffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_offers, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( OffersAdapter.MyViewHolder holder, int position) {
        Offer offer=offerArrayList.get(position);
        holder.imageOffer.setImageResource(offer.getImageOffer());
    }

    @Override
    public int getItemCount() {
        return offerArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageOffer;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageOffer=itemView.findViewById(R.id.CardViewOffers_imageOffer);
        }
    }
}
