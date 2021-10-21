package codegym.service.iml;

import codegym.entity.User;
import codegym.repository.UserRepository2;
import codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl2 implements UserService {
    private UserRepository2 userRepository2;
    @Autowired
    public UserServiceImpl2(UserRepository2 userRepository2) {
        this.userRepository2 = userRepository2;
    }

    @Override
    public User getUser(int id) {
        return userRepository2.findById(id).orElse(null);
    }

    @Override
    public List<User> getListUser() {
        System.out.println("Get list user");
        List<User> users = new ArrayList<>();
        userRepository2.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void saveUser(User user) {
        userRepository2.save(user);
    }

    @Override
    public void editUser(User user) {
        userRepository2.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository2.delete(user);
    }

    @Override
    public void saveOrEdit(User user) {
        userRepository2.save(user);
    }

    @Override
    public List<User> getListUserByName(String name) {
        return userRepository2.findUsersByNameContaining(name);
    }

    @Override
    public List<User> getListUserByAge(int age) {
        return userRepository2.findUsersByAge(age);
    }
}
