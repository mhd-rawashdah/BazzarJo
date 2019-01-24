package edu.zu.demo.graduation.bazzar.Model;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Offer {
    private int id ;
    private int sellerId;
    private String orginalPrice;
    private String offerPrice;
    private int imageOffer;

    public Offer(int id, int sellerId, String orginalPrice, String offerPrice, int imageOffer) {
        this.id = id;
        this.sellerId = sellerId;
        this.orginalPrice = orginalPrice;
        this.offerPrice = offerPrice;
        this.imageOffer = imageOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(String orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getImageOffer() {
        return imageOffer;
    }

    public void setImageOffer(int imageOffer) {
        this.imageOffer = imageOffer;
    }
}
