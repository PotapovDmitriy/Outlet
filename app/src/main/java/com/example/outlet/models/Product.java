package com.example.outlet.models;

public class Product {
    private String name;
    private String shopName;
    private String sex;
    private String URL;
    private String imageURL;
    private int salePrice;
    private int originPrice;
    private String category;

    public Product(String name, String shopName, String sex, String URL, String imageURL, int salePrice, int originPrice, String category) {
        this.name = name;
        this.shopName = shopName;
        this.sex = sex;
        this.URL = URL;
        this.imageURL = imageURL;
        this.salePrice = salePrice;
        this.originPrice = originPrice;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public void setOriginPrice(int originPrice) {
        this.originPrice = originPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getShopName() {
        return shopName;
    }

    public String getSex() {
        return sex;
    }

    public String getURL() {
        return URL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public String getCategory() {
        return category;
    }
}
