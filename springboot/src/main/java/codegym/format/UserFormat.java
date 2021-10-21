package codegym.format;

import codegym.entity.User;
import codegym.service.iml.UserServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class UserFormat implements Formatter<User> {
    private UserServiceImpl2 userService;

    @Autowired
    public UserFormat(UserServiceImpl2 userService) {
        this.userService = userService;
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return userService.getUser(Integer.parseInt(text));
    }

    @Override
    public String print(User object, Locale locale) {
        return String.format("[%d]", object.getId());
    }
}
