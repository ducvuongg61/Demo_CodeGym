package codegym.restcontroller;

import codegym.entity.User;
import codegym.service.iml.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    UserServiceImpl2 service;

    @RequestMapping(value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getList() {
        return service.getListUser();
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getUserById(@PathVariable int id) {
        User user = service.getUser(id);
        if (user == null) {
            return "Not found";
        }
        return user;
    }

    @RequestMapping(value = "/create",
            method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@RequestBody User user) {
        service.saveUser(user);
        return "Save user success";
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        service.editUser(user);
        return "Edit user success";
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteUser(@PathVariable int id) {
        User user = service.getUser(id);
        service.deleteUser(user);
        return "Delete user success";
    }

    @RequestMapping(value = "/searchByName",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> deleteUser(@RequestParam String name) {
        List<User> users = service.getListUserByName(name);
        return users;
    }
}
