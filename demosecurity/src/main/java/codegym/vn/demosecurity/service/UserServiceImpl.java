package codegym.vn.demosecurity.service;

import codegym.vn.demosecurity.entities.Account;
import codegym.vn.demosecurity.repository.AccountRepository;
import codegym.vn.demosecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        // get user trong database ra
        Account account = accountRepository.getAccountsByAccountName(accountName);
        if (account == null) {
            throw new UsernameNotFoundException("User " + accountName + " was not found");
        }

        // Lấy các quyền của user trong database
        List<String> roles = roleRepository.getRolesName(accountName);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (roles != null) {
            // Chuyển các quyền từ kiểu String sang kiểu GrantedAuthority của Spring Security
            for (String role: roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(grantedAuthority);
            }
        }

        // Tạo User theo cách của Spring Security
        UserDetails userDetails = new User(account.getAccountName(), account.getPassword(),
                grantedAuthorities);

        return userDetails;
    }
}
