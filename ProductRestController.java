package edu.uark.registerapp.controllers;

import java.util.UUID;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.commands.products.ProductCreateCommand;
import edu.uark.registerapp.commands.products.ProductDeleteCommand;
import edu.uark.registerapp.commands.products.ProductUpdateCommand;
import edu.uark.registerapp.models.api.ApiResponse;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController {
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ApiResponse createProduct(@RequestBody final Product product, HttpServletRequest req) {
            
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionId);
            
        if(activeUserEntity.isPresent()) {
            int classification = activeUserEntity.get().getClassification();
            if(classification == 501 || classification == 701) {
                return this.productCreateCommand.setApiProduct(product).execute();
            }
            else {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setRedirectUrl("/productListing.html"); 
                return apiResponse;
            }
        }
        else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setRedirectUrl("/productListing.html"); 
            return apiResponse;
        }
    }

    @RequestMapping(value = "/productListing.html/{productId}", method = RequestMethod.PUT)
    public @ResponseBody ApiResponse updateProduct(@PathVariable final UUID productId, @RequestBody final Product product, HttpServletRequest req) {
            
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionId);
            
        if(activeUserEntity.isPresent()) {
            int classification = activeUserEntity.get().getClassification();
            if(classification == 501 || classification == 701) {
                return this.productUpdateCommand.setProductId(productId).setApiProduct(product).execute();
            }
            else {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setRedirectUrl("/productListing.html"); 
                return apiResponse;
            }
        }
        else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setRedirectUrl("/productListing.html"); 
            return apiResponse;
        }
    }

    @RequestMapping(value = "/productListing.html/{productId}", method = RequestMethod.DELETE)
    public @ResponseBody ApiResponse deleteProduct(@PathVariable final UUID productId, HttpServletRequest req) {
            
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionId);
          
        if(activeUserEntity.isPresent()) {
            int classification = activeUserEntity.get().getClassification();
            if(classification == 501 || classification == 701) {
                this.productDeleteCommand.setProductId(productId).execute();
                return new ApiResponse();
            }
            else {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setRedirectUrl("/productListing.html"); 
                return apiResponse;
            }
        }
        else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setRedirectUrl("/productListing.html"); 
            return apiResponse;
        }
    }

    // Properties
    @Autowired
    private ProductCreateCommand productCreateCommand;

    @Autowired
    private ProductDeleteCommand productDeleteCommand;

    @Autowired
    private ProductUpdateCommand productUpdateCommand;
        
    @Autowired
    ActiveUserRepository activeUserRepository;
}
