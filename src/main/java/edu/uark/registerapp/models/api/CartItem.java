package edu.uark.registerapp.models.api;
import java.util.UUID;

public class CartItem {
    UUID productID;
    int productQuantity;
    String productLookUpCode;
    long totalPrice;


    public UUID getProductID() {
        return productID;
    }
    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductLookUpCode(){
        return productLookUpCode;
    }
    public void setProductID(String productID) {
        this.productID = UUID.fromString(productID);
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductLookupCode(String productLookUpCode){
        this.productLookUpCode = productLookUpCode;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = (long) totalPrice;
    }

    public long getTotalPrice() {
        return totalPrice;
    }
}