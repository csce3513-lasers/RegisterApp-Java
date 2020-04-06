package edu.uark.registerapp.models.api;

public class SearchResult {
    String productID;
    String productName;
    double productPrice;

    public String getProductID() {
        return productID;
    }
    public String getProductName() {
        return productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}