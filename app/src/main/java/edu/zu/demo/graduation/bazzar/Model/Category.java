package edu.zu.demo.graduation.bazzar.Model;

import java.util.ArrayList;

public class Category {
    private  int id;
    private String name;
    private String picture;
    private ArrayList<SubCategory> subCategory=new ArrayList<>();
    private ArrayList<Seller> sellers=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<SubCategory> getSubCategory() {
        return subCategory;
    }

    public ArrayList<Seller> getSeller() {
        return sellers;
    }

    public void  addSubCategory(SubCategory subCategory){
        this.subCategory.add(subCategory);
 }
 public void AddSeller(Seller seller){
        this.sellers.add(seller);
 }
}
