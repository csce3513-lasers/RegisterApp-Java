package edu.uark.registerapp.models.api;

import java.util.UUID;


public class SearchResult {
    UUID productID;
    String productLookUpCode;
    double productPrice;

    public UUID getProductID() {
        return productID;
    }
    public String getproductLookUpCode() {
        return productLookUpCode;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductID(UUID id) {
        this.productID = id;
    }
    public void setproductLookUpCode(String string) {
        this.productLookUpCode = string;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}