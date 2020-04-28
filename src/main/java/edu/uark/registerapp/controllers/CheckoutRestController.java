package edu.uark.registerapp.controllers;

import edu.uark.registerapp.commands.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.commands.products.ProductByLookupCodeQuery;
import edu.uark.registerapp.commands.products.ProductByPartialLookupCodeQuery;
import edu.uark.registerapp.commands.products.ProductCreateCommand;
import edu.uark.registerapp.commands.products.ProductQuery;
import edu.uark.registerapp.commands.products.ProductsQuery;
import edu.uark.registerapp.commands.transactions.TransactionCreateCommand;
import edu.uark.registerapp.models.api.CartItem;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.api.SearchResult;
import edu.uark.registerapp.models.api.Transaction;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping(value = "/checkout")

public class CheckoutRestController extends BaseRouteController {
   
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody List<SearchResult> search(final HttpServletRequest request) throws Exception
     {
        
        //SUBSTITUTE FOR DATABASE
        ArrayList<SearchResult> products = new ArrayList<SearchResult>();
        /*
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
        */

        //ACTUAL CODE
        String productID = request.getParameter("data");
        //List<SearchResult> searchResults = new ArrayList<SearchResult>(); //CHANGE TO WHAT SEARCH CLASS RETURNS

        //CONVERTING FROM PRODUCT TO SEARCHRESULT
        try {
            this.productByLookupCodeQuery.setLookupCode(productID);
            Product singleProduct = this.productByLookupCodeQuery.execute();
            SearchResult singleResult = new SearchResult();
            singleResult.setProductID(singleProduct.getId());
            singleResult.setProductLookUpCode(singleProduct.getLookupCode());
            singleResult.setProductPrice(singleProduct.getPrice());
            products.add(singleResult);

        }
        catch(final NotFoundException e) {
            this.productByPartialLookupCodeQuery.setPartialLookupCode(productID);
            
            //CONVERT THE RETURNED ARRAY TO AN ARRAYLIST FOR EASIER USE
            ArrayList<Product> allProducts = new ArrayList<Product>(Arrays.asList(this.productByPartialLookupCodeQuery.execute()));
            
            allProducts.forEach(singleProduct->{
                //FROM HERE
                SearchResult singleResult = new SearchResult();
                singleResult.setProductID(singleProduct.getId());
                singleResult.setProductLookUpCode(singleProduct.getLookupCode());
                singleResult.setProductPrice(singleProduct.getPrice());
                products.add(singleResult);
                //TO HERE COULD BE REPLACED WITH, BUT NOT TESTED
                //products.add(convertProductToSearchResult(singleProduct)); //METHOD COMMENTED OUT BELOW
            });
			//CONVERTING FROM PARTIAL PRODUCT LOOKUPCODE TO SEARCHRESULT
           /* productByPartialLookupCodeQuery.setPartialLookupCode("up");
            Product allProducts = productByPartialLookupCodeQuery.execute();
            SearchResult allResults = new SearchResult();
            allResults.setProductID(allProducts.getPartialLookupCode());
            allResults.setProductPrice(allProducts.getPrice());
            products.add(allResults);*/
        }
        finally {
            return products;
        }      
        /*
        //SEARCH SUBSTITUTE (WILL ADD PARTIAL SEARCH LATER)
        if(!productID.isEmpty()) {
            for(SearchResult result : products) { //Search database substitute
                if(result.getProductID().contains(productID)) {
                    SearchResult product = new SearchResult();
                    product.setProductID(result.getProductID());
                    product.setProductPrice(result.getProductPrice());
                    searchResults.add(product);
                }
            }
        }
        */
    }
   
   
   
    @RequestMapping(value = "/submitCart", method = RequestMethod.POST)
    public @ResponseBody UUID checkout(@RequestBody final List<CartItem> cart, final HttpServletRequest request) throws Exception {
        
        //TEST CODE PRINTS OUT CART
        /*for(CartItem item : cart) {
            System.out.println("Product ID: " + item.getProductID() + " Quantity: " + item.getProductQuantity());
        }*/
        
        long totalPrice = cart.get(cart.size()-1).getTotalPrice(); // total price is stored on the last index
        cart.remove(cart.size()-1);  
	 
	    
	//UPDATE THE STOCK?
        for(CartItem item : cart) {
            this.productByLookupCodeQuery.setLookupCode(item.getProductLookUpCode());
            Product singleProduct = this.productByLookupCodeQuery.execute();
            singleProduct.setCount(singleProduct.getCount() - item.getProductQuantity());
            this.productUpdateCommand.setApiProduct(singleProduct);
            this.productUpdateCommand.execute();
        }
	   
	    
	//CREATE THE TRANSACTION IN THE DATABASE?    
        final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);
        UUID cashierID = activeUserEntity.get().getEmployeeId();
                
        this.transaction.setCashierId(cashierID);
        this.transaction.setTotal(totalPrice);
        UUID transactionID = this.transaction.getReferenceId();
        
        return transactionID;
    }
    
     /*
    private SearchResult convertProductToSearchResult(Product singleProduct) {
        SearchResult singleResult = new SearchResult();
        singleResult.setProductID(singleProduct.getId());
        singleResult.setProductLookUpCode(singleProduct.getLookupCode());
        singleResult.setProductPrice(singleProduct.getPrice());
        return singleResult;
    }
    */
       
    @Autowired
    private ProductCreateCommand productCreateCommand;
    @Autowired
    private TransactionCreateCommand transactionCreateCommand;
    @Autowired
    private ProductByLookupCodeQuery productByLookupCodeQuery;
    @Autowired
    private ProductQuery productQuery;
    @Autowired
    private ProductsQuery productsQuery;
    @Autowired
    private ProductByPartialLookupCodeQuery productByPartialLookupCodeQuery;
    @Autowired
    private Transaction transaction;
    @Autowired
    private ProductCreateCommand productUpdateCommand;
}
