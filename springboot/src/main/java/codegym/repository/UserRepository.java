package codegym.repository;

import codegym.entity.User;

import java.util.List;

public interface UserRepository {
    User getUser(int id);
    List<User> getListUser();
    void saveUser(User user);
    void editUser(User user);
    void deleteUser(User user);
    void saveOrEdit(User user);
    public List<User> getListUserByName(String name);
    public List<User> getListUserByAge(int age);
}
