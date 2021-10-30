package codegym.vn.demosecurity.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class RoleRepository {
    @Autowired
    EntityManager entityManager;

    public List<String> getRolesName(String accountName) {
        //HQL
        String sql = "Select r.role.roleName from AccountRole r where r.account.accountName = :accountName";
        Query query =this.entityManager.createQuery(sql, String.class);
        query.setParameter("accountName", accountName);
        return query.getResultList();
    }
}
