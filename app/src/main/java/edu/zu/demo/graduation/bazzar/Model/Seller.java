package edu.zu.demo.graduation.bazzar.Model;

public class Seller extends User {
    private String city;
    private String storeName;
    private String storeType;
    private String typeCategory;
    private String email;
    private String location;
    private String imageProfile;
    private String timeDelivery; // delete
    private String areaName;
    private int numberFeedback;
    private double  ratting;
    private double deliveryPrice;
    private double minimumPrice;
    private boolean  state;
    private boolean deliveryService;
    private boolean onlinePaymentService;


    public Seller (){

    }

    public Seller(String storeName, String imageProfile, String  timeDelivery, double ratting, boolean state) {
        this.storeName = storeName;
        this.imageProfile = imageProfile;
        this.timeDelivery = timeDelivery;
        this.ratting = ratting;
        this.state = state;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public int getNumberFeedback() {
        return numberFeedback;
    }

    public void setNumberFeedback(int numberFeedback) {
        this.numberFeedback = numberFeedback;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public boolean isState() {
        return state;
    }

    public boolean isDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(boolean deliveryService) {
        this.deliveryService = deliveryService;
    }

    public boolean isOnlinePaymentService() {
        return onlinePaymentService;
    }

    public void setOnlinePaymentService(boolean onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }



    public double getRatting() {
        return ratting;
    }

    public void setRatting(double ratting) {
        this.ratting = ratting;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String  getTimeDelivery() {
        return timeDelivery;
    }

    public boolean setTimeDelivery(String timeDelivery) {
        for (int i = 0; i < timeDelivery.length(); i++) {
            if (!Character.isDigit(timeDelivery.charAt(i))) {
                return false;
            }
        }
        this.timeDelivery = timeDelivery;
        return true;

    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean flag = !email.isEmpty() && email.matches(emailPattern);
        if(flag)
            this.email = email;
        return flag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }




}
