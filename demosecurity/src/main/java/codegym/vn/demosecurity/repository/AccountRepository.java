package codegym.vn.demosecurity.repository;

import codegym.vn.demosecurity.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountsByAccountName(String accountName);
}
