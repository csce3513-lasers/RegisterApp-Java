package edu.uark.registerapp.models.api;

public class CartItem {
    String productID;
    int productQuantity;

    public String getProductID() {
        return productID;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}