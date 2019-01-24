package edu.zu.demo.graduation.bazzar.Model;

import java.util.ArrayList;

public class SubCategory {
    private int id ;
    private String name;
    private ArrayList<Items> itemsList=new ArrayList<>();

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

    public ArrayList<Items> getItems() {
        return itemsList;
    }

  public void addItems(Items items){
        this.itemsList.add(items);
  }



}
