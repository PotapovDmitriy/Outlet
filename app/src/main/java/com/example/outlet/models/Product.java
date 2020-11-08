package com.example.outlet.models;

public class Product {
    private String globalItemId;
    private String id;
    private String productId;
    private String maintenanceGroup;
    private boolean sale;
    private String imagePath;
    private int currentPrice;
    private int originalPrice;

    public Product(String globalItemId, String id, String productId, String maintenanceGroup, boolean sale, String imagePath, int currentPrice, int originalPrice) {
        this.globalItemId = globalItemId;
        this.id = id;
        this.productId = productId;
        this.maintenanceGroup = maintenanceGroup;
        this.sale = sale;
        this.imagePath = imagePath;
        this.currentPrice = currentPrice;
        this.originalPrice = originalPrice;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
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

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
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

    public int getCurrentPrice() {
        return currentPrice;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }
}
