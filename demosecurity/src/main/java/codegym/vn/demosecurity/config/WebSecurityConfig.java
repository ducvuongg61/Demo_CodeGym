package codegym.vn.demosecurity.config;

import codegym.vn.demosecurity.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userService;

    @Bean
    public BCryptPasswordEncoder encoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // kết nối với account trong db
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
        // Các trang yêu cầu quyền
        // http.authorizeRequests().antMatchers("/user/**").access("hasRole('Role_User')");
        http.authorizeRequests().antMatchers("/user/**").access(
                "hasAnyRole('ROLE_USER','ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
        // Nếu access không đúng quyền, giả sử Role_User vào trang /admin/**
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/denied");
        http.authorizeRequests().and()
                .formLogin()
                .defaultSuccessUrl("/user/info")
                .and().logout()
                .logoutSuccessUrl("/logoutSuccessful");
    }


}
