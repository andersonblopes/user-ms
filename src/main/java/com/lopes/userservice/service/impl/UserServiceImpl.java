package com.lopes.userservice.service.impl;

import com.lopes.userservice.domain.Role;
import com.lopes.userservice.domain.User;
import com.lopes.userservice.repository.RoleRepository;
import com.lopes.userservice.repository.UserRepository;
import com.lopes.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     * The User repository.
     */
    private final UserRepository userRepository;
    /**
     * The Role repository.
     */
    private final RoleRepository roleRepository;
    /**
     * The Password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            String message = "User not found in the database.";
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * Save user user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User saveUser(User user) {

        log.info("Saving new user {} to the database", user.getName());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Save role role.
     *
     * @param role the role
     * @return the role
     */
    @Override
    public Role saveRole(Role role) {

        log.info("Saving new role {} to the database", role.getName());

        return roleRepository.save(role);
    }

    /**
     * Add role to user.
     *
     * @param roleName the role name
     * @param username the username
     */
    @Override
    public void addRoleToUser(String roleName, String username) {

        log.info("Adding new role {} to user {}", roleName, username);

        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    @Override
    public User getUser(String username) {

        log.info("Fetching user {}", username);

        return userRepository.findByUsername(username);
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    @Override
    public List<User> getUsers() {

        log.info("Fetching all users");

        return userRepository.findAll();
    }


}
