package codegym.service.iml;

import codegym.entity.User;
import codegym.repository.UserRepository;
import codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(int id) {
        return userRepository.getUser(id);
    }

    @Override
    public List<User> getListUser() {
        return userRepository.getListUser();
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public void editUser(User user) {
        userRepository.editUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    @Override
    public void saveOrEdit(User user) {
        userRepository.saveOrEdit(user);
    }

    @Override
    public List<User> getListUserByName(String name) {
        return userRepository.getListUserByName(name);
    }

    @Override
    public List<User> getListUserByAge(int age) {
        return userRepository.getListUserByAge(age);
    }
}
