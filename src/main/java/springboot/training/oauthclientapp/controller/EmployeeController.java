package springboot.training.oauthclientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
    public ModelAndView getEmployeeInfo(){
        return new ModelAndView("getEmployees");
    }
}
