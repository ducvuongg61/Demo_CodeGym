package codegym.repository;

import codegym.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository2 extends CrudRepository<User, Integer> {
    List<User> findUsersByNameContaining(String name);
    List<User> findUsersByAge(int age);
    @Query("select u from User u where u.age between ?1 and ?2")
    List<User> findUserByAgeFromTo(int age1, int age2);

}
