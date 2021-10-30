package codegym.vn.demosecurity.entities;

import javax.persistence.*;

@Entity
@Table (name = "account")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    public Account() {
    }

    public Account(Long id, String accountName, String password) {
        this.id = id;
        this.accountName = accountName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
