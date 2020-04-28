package edu.uark.registerapp.models.api;
import java.util.UUID;

public class CartItem {
    UUID productID;
    int productQuantity;

    public UUID getProductID() {
        return productID;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductID(String productID) {
        this.productID = UUID.fromString(productID);
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}