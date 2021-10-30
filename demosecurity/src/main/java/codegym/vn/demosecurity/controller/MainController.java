package codegym.vn.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public String userInfo() {
        return "user";
    }

    @RequestMapping(value = "/admin/info", method = RequestMethod.GET)
    public String adminInfo() {
        return "admin";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String denied() {
        return "denied";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logout() {
        return "logout";
    }

}
