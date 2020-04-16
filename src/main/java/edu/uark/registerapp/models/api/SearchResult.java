package edu.uark.registerapp.models.api;


public class SearchResult {
    String productID;
    String productLookUpCode;
    double productPrice;

    public String getProductID() {
        return productID;
    }
    public String getproductLookUpCode() {
        return productLookUpCode;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductID(String string) {
        this.productID = string;
    }
    public void setproductLookUpCode(String string) {
        this.productLookUpCode = string;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}