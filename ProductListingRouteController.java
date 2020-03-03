package controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/productlisting.html")

public class ProductListingRouteController {
    @RequestMapping(method = RequestMethod.GET)  
    public ModelAndView productListingGetRoute(@RequestParam Map<String, String> map) {
        ModelAndView modelAndView = new ModelAndView("productListingView");
        Boolean elevatedEmployee = true;
        if(elevatedEmployee) {
            modelAndView.addObject("elevatedUser", elevatedEmployee);
        }
        return modelAndView;
    }
}
