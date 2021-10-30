package codegym.vn.demosecurity.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtils {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}
