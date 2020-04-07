package edu.uark.registerapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.models.api.CartItem;
import edu.uark.registerapp.models.api.SearchResult;

@RestController
@RequestMapping(value = "/checkout")

public class CheckoutRestController {
   
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody List<SearchResult> search(final HttpServletRequest request) throws Exception {
        
        //SUBSTITUTE FOR DATABASE
        ArrayList<SearchResult> products = new ArrayList<SearchResult>();
        SearchResult apple = new SearchResult();
        apple.setProductID("0001");
        apple.setProductName("Apple");
        apple.setProductPrice(0.75);
        products.add(apple);
        SearchResult pear = new SearchResult();
        pear.setProductID("0002");
        pear.setProductName("Pear");
        pear.setProductPrice(0.65);
        products.add(pear);
        SearchResult tomato = new SearchResult();
        tomato.setProductID("0003");
        tomato.setProductName("Tomato");
        tomato.setProductPrice(0.80);
        products.add(tomato);        

        //ACTUAL CODE
        String productID = request.getParameter("pid");
                   
        List<SearchResult> searchResults = new ArrayList<SearchResult>(); //CHANGE TO WHAT SEARCH CLASS RETURNS
        
        //SEARCH SUBSTITUTE
        if(!productID.isEmpty()) {
            for(SearchResult result : products) { //Search database substitute
                if(result.getProductID().contains(productID)) {
                    SearchResult product = new SearchResult();
                    product.setProductID(result.getProductID());
                    product.setProductName(result.getProductName());
                    product.setProductPrice(result.getProductPrice());
                    searchResults.add(product);
                }
            }
        }
        
        //ACTUAL CODE
        return searchResults;
    }
   
   
   
    @RequestMapping(value = "/submitCart", method = RequestMethod.POST)
    public @ResponseBody List<CartItem> checkout(@RequestBody final List<CartItem> cart) throws Exception {
        
        //TEST CODE PRINTS OUT CART
        for(CartItem item : cart) {
            System.out.println("Product ID: " + item.getProductID() + " Quantity: " + item.getProductQuantity());
        }
        
        //WHAT GETS RETURNED
        List<CartItem> searchResults = new ArrayList<CartItem>();
        return searchResults;
    }
       
}