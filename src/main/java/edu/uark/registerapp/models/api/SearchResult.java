package edu.uark.registerapp.models.api;


public class SearchResult {
    String productID;
    double productPrice;

    public String getProductID() {
        return productID;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductID(String string) {
        this.productID = string;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}