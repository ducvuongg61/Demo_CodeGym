package codegym.controller;

import codegym.entity.User;
import codegym.service.iml.UserServiceImpl2;
import codegym.validator.AgeCustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/user")
public class UserController {
    UserServiceImpl2 userService;

    @Autowired
    public UserController(UserServiceImpl2 userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String nameSearch,
                        @RequestParam(required = false) String ageSearch,
                        @CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,
                        Model model, HttpServletRequest request,
                        HttpServletResponse response) {
        List<User> users;
        hitCounter++;

        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        cookie.setMaxAge(10);
        response.addCookie(cookie);

        if (nameSearch != null && !nameSearch.isEmpty()) {
            users = userService.getListUserByName(nameSearch);

        } else if(ageSearch != null && !ageSearch.isEmpty()) {
            users = userService.getListUserByAge(Integer.parseInt(ageSearch));
        } else {
            users = userService.getListUser();
        }
        model.addAttribute("cookies", new ArrayList<>(Arrays.asList(request.getCookies())));
        model.addAttribute("users", users);
        model.addAttribute("count", hitCounter);
        model.addAttribute("user1", "Linh");
        model.addAttribute("user2", "Bao");
        return "user/index";
    }

    @GetMapping("/create")
    public String displayCreate(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "create");
        return "user/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute User user, BindingResult bindingResult, Model model) {
        new AgeCustomValidation().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "user/create";
        }
        userService.saveOrEdit(user);
        return "redirect:/user/index";
    }

    @GetMapping("/edit/{id}")
    public String displayEdit(@PathVariable int id ,Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");
        return "user/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/user/index";
    }

    @GetMapping("/detail/{id}")
    public String displayDetail(@PathVariable("id") User user ,Model model) {
//        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/detail";
    }
}
