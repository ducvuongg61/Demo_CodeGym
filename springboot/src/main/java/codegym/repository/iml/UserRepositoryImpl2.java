package codegym.repository.iml;

import codegym.entity.User;
import codegym.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl2 implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getListUser() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public void saveOrEdit(User user) {
        if (user.getId() == 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> getListUserByName(String name) {
        // HSQL != query
        return entityManager.createQuery("select u from User u where u.name like :name", User.class)
                .setParameter("name", "%"+ name + "%")
                .getResultList();
    }

    @Override
    public List<User> getListUserByAge(int age) {
        return entityManager.createNamedQuery("findCustomerByAge")
                .setParameter(1, age)
                .getResultList();
    }
}
