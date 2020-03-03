package controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/productdetail.html")

public class ProductDetailRouteController {
    @RequestMapping(method = RequestMethod.GET)  
    public ModelAndView productDetailGetRoute(@RequestParam Map<String, String> map) {
        ModelAndView modelAndView;
        Boolean elevatedEmployee = false; // placeholder
        if(elevatedEmployee) {
            modelAndView = new ModelAndView("productDetailView");
            modelAndView.addObject("disabledField", !elevatedEmployee);
            modelAndView.addObject("elevatedUser", elevatedEmployee);
        }
        else {
            modelAndView = new ModelAndView("redirect:/productlisting.html");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/api/saveproduct", method = RequestMethod.POST)  
    public ModelAndView productsaveRoute(@RequestParam Map<String, String> map) {
        ModelAndView modelAndView;
        Boolean elevatedEmployee = true; // placeholder
        if(!elevatedEmployee) {
            modelAndView = new ModelAndView("redirect:/productlisting.html");
        }
        else {
            // save the product
            modelAndView = new ModelAndView();
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/api/deleteproduct", method = RequestMethod.POST)  
    public ModelAndView productdeleteRoute(@RequestParam Map<String, String> map) {
        ModelAndView modelAndView;
        Boolean elevatedEmployee = true; // placeholder
        if(!elevatedEmployee) {
            modelAndView = new ModelAndView("redirect:/productlisting.html");
        }
        else {
            // delete the product 
            modelAndView = new ModelAndView();
        }
        return modelAndView;
    }
}
