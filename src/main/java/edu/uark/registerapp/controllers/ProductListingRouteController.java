package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import edu.uark.registerapp.commands.products.ProductQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;


@Controller
@RequestMapping(value = "/productListing.html") 
public class ProductListingRouteController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showProductListing(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView(ViewNames.PRODUCT_LISTING.getViewName());
        
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        final Optional<ActiveUserEntity> activeUserEntity = activeUserRepository.findBySessionKey(sessionId);
        if(activeUserEntity.isPresent()) {
            int classification = activeUserEntity.get().getClassification();
            if(classification == 501 || classification == 701) {
                modelAndView.addObject("elevatedUser", true); //defaults in view to false
            }
        }
        try {
            modelAndView.addObject(ViewModelNames.PRODUCTS.getValue(), this.productQuery.execute());
        } catch (final Exception e) {
            modelAndView.addObject(ViewModelNames.ERROR_MESSAGE.getValue(), e.getMessage());
            modelAndView.addObject(ViewModelNames.PRODUCTS.getValue(), (new Product[0]));
        }
	return modelAndView;
    }
    
    // Properties
    @Autowired
    ActiveUserRepository activeUserRepository;
    private ProductQuery productQuery;
}
    

