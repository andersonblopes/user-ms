package com.lopes.userservice;

import com.lopes.userservice.domain.Role;
import com.lopes.userservice.domain.User;
import com.lopes.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * The type Userservice application.
 */
@SpringBootApplication
public class UserserviceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Run command line runner.
     *
     * @param userService the user service
     * @return the command line runner
     */
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            // Create roles
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            // Create users
            userService.saveUser(new User(null, "John Travolta", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Will Smith", "will", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jin Carry", "jin", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Arnold Schwarzenegger", "arnold", "1234", new ArrayList<>()));

            // Add roles
            userService.addRoleToUser("ROLE_USER", "john");
            userService.addRoleToUser("ROLE_MANAGER", "john");
            userService.addRoleToUser("ROLE_MANAGER", "will");
            userService.addRoleToUser("ROLE_ADMIN", "jin");
            userService.addRoleToUser("ROLE_SUPER_ADMIN", "arnold");
            userService.addRoleToUser("ROLE_ADMIN", "arnold");
            userService.addRoleToUser("ROLE_USER", "arnold");
        };
    }

}
