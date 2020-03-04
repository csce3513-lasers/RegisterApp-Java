package edu.uark.registerapp.controllers;

import java.util.UUID;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@Controller
@RequestMapping(value = "/productDetail") //correct routename?

public class ProductDetailRouteController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(HttpServletRequest req) {
        ModelAndView modelAndView;
        
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionId);
        
        if(activeUserEntity.isPresent()) {
            int classification = activeUserEntity.get().getClassification();
            if(classification == 501 || classification == 701) {
                modelAndView = new ModelAndView(ViewNames.PRODUCT_DETAIL.getViewName());
                modelAndView.addObject("disabledField", false); //defaults in view to true
                modelAndView.addObject("elevatedUser", true); //defaults in view to false
                modelAndView.addObject(ViewModelNames.PRODUCT.getValue(), (new Product()).setLookupCode(StringUtils.EMPTY).setCount(0));
            }
            else {
                modelAndView = new ModelAndView("redirect:/productListing.html"); 
            }
        }
        else {
            modelAndView = new ModelAndView("redirect:/productListing.html"); 
        }
        return modelAndView;
    }

    @RequestMapping(value = "/productListing.html/{productId}", method = RequestMethod.GET) 
    public ModelAndView startWithProduct(@PathVariable final UUID productId, HttpServletRequest req) {
        ModelAndView modelAndView;
        
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionId);
        
        if(activeUserEntity.isPresent()) {
            int classification = activeUserEntity.get().getClassification();
            if(classification == 501 || classification == 701) {
                modelAndView = new ModelAndView(ViewNames.PRODUCT_DETAIL.getViewName());
                modelAndView.addObject("disabledField", false); //defaults in view to true
                modelAndView.addObject("elevatedUser", true); //defaults in view to false
                try {
                    modelAndView.addObject(ViewModelNames.PRODUCT.getValue(), this.productQuery.setProductId(productId).execute());
                } catch (final Exception e) {
                    modelAndView.addObject(ViewModelNames.ERROR_MESSAGE.getValue(), e.getMessage());
                    modelAndView.addObject(ViewModelNames.PRODUCT.getValue(), (new Product()).setCount(0).setLookupCode(StringUtils.EMPTY));
                }
            }
            else {
                modelAndView = new ModelAndView("redirect:/productListing.html"); 
            }
        }
        else {
            modelAndView = new ModelAndView("redirect:/productListing.html");
        }
        return modelAndView;
    }
    

    
    // Properties
    @Autowired
    private ProductQuery productQuery;
    
    @Autowired
    ActiveUserRepository activeUserRepository;
}
