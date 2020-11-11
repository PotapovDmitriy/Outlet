package com.example.outlet.models;

public class Product {
    private String globalItemId;
    private String id;
    private String productId;
    private String maintenanceGroup;
    private boolean sale;
    private String imagePath;
    private String currentPrice;
    private String originalPrice;
    private String Name;
    private String URL;

    public Product(String globalItemId, String id, String productId, String maintenanceGroup, boolean sale, String imagePath, String currentPrice, String originalPrice, String name, String url) {
        this.globalItemId = globalItemId;
        this.id = id;
        this.productId = productId;
        this.maintenanceGroup = maintenanceGroup;
        this.sale = sale;
        this.imagePath = imagePath;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
        Name = name;
        URL = url;
    }

    public void setGlobalItemId(String globalItemId) {
        this.globalItemId = globalItemId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setMaintenanceGroup(String maintenanceGroup) {
        this.maintenanceGroup = maintenanceGroup;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGlobalItemId() {
        return globalItemId;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getMaintenanceGroup() {
        return maintenanceGroup;
    }

    public boolean isSale() {
        return sale;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getName() {
        return Name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
