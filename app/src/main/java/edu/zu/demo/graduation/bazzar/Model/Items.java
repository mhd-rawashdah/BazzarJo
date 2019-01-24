package edu.zu.demo.graduation.bazzar.Model;

public class Items {
    private int Id;
    private int seller_Id;
    private String itemName;
    private String desc;
    private int imageId;
    private double itemPrice;

    public Items(int id, int seller_Id, String itemName, String desc, int imageId, double itemPrice) {
        Id = id;
        this.seller_Id = seller_Id;
        this.itemName = itemName;
        this.desc = desc;
        this.imageId = imageId;
        this.itemPrice = itemPrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSeller_Id() {
        return seller_Id;
    }

    public void setSeller_Id(int seller_Id) {
        this.seller_Id = seller_Id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
