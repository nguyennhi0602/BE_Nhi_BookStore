package com.nhi.bookstore.configurations;

import com.nhi.bookstore.model.Role;
import com.nhi.bookstore.model.User;
import com.nhi.bookstore.repositories.RoleReponsitory;
import com.nhi.bookstore.repositories.UserReponsitory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Configuration
@Profile({"!test"})
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleReponsitory roleReponsitory;

    @Autowired
    private UserReponsitory userReponsitory;

    @Value("${jwt-key}")
    private String signingKey;

    private void addRoleIfMissing(String name) {
        if (roleReponsitory.findByNameContaining(name) == null) {
            roleReponsitory.save(new Role(name));
        }
    }

    private void addUserIfMissing(String userName, String password, String... roles) {
        if (userReponsitory.findByUserName(userName) == null) {
            User user = new User(userName, "First name", "Last name", new BCryptPasswordEncoder().encode(password), "email", 1);
            user.setRoles(new HashSet<>());
            for (String role : roles) {
                user.getRoles().add(roleReponsitory.findByNameContaining(role));
            }
            userReponsitory.save(user);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addRoleIfMissing("ROLE_ADMIN");
        addRoleIfMissing("ROLE_MEMBER");
//        addUserIfMissing("nhi", "123", "ROLE_MEMBER");
//        addUserIfMissing("thu", "12345678", "ROLE_MEMBER");
//        addUserIfMissing("admin", "000", "ROLE_MEMBER", "ROLE_ADMIN");
        if (signingKey == null || signingKey.length() == 0) {
            String jws = Jwts.builder()
                    .setSubject("BookStore")
                    .signWith(SignatureAlgorithm.HS256, "BookStoreApi").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }
    }
}
