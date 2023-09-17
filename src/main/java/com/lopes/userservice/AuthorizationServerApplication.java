package com.lopes.userservice;

import com.lopes.userservice.auth.AuthenticationService;
import com.lopes.userservice.auth.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.lopes.userservice.domain.user.Role.ADMIN;
import static com.lopes.userservice.domain.user.Role.MANAGER;

/**
 * The type Authorization server application.
 */
@Slf4j
@SpringBootApplication
public class AuthorizationServerApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            log.info("Admin token: " + service.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@mail.com")
                    .password("password")
                    .role(MANAGER)
                    .build();
            log.info("Manager token: " + service.register(manager).getAccessToken());

        };
    }

}
