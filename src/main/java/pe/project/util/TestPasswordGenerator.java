package pe.project.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordGenerator {

    //método ejecutable
    public static void main(String[] args) {
        String password = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);

    }
}
