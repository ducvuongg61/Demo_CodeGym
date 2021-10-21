package codegym.repository.iml;

import codegym.entity.User;
import codegym.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class UserRepositoryImpl implements UserRepository {
//    private static HashMap<Integer, User> users = new HashMap<>();
    @Autowired
    private SessionFactory sessionFactory;

//    static {
//        users.put(1, new User(1, "Hao", 22, "Da Nang"));
//        users.put(2, new User(2, "Tam", 21, "Da Nang"));
//        users.put(3, new User(3, "Hien", 23, "Da Nang"));
//        users.put(4, new User(4, "Thang", 20, "Da Nang"));
//        users.put(5, new User(5, "Nam", 19, "Da Nang"));
//    }

    @Override
    public User getUser(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getListUser() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void editUser(User user) {
        sessionFactory.getCurrentSession().update(user);

    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public void saveOrEdit(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getListUserByName(String name) {
        return null;
    }

    @Override
    public List<User> getListUserByAge(int age) {
        return null;
    }
}

