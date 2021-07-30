package com.lopes.userservice.service;

import com.lopes.userservice.domain.Role;
import com.lopes.userservice.domain.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Save user user.
     *
     * @param user the user
     * @return the user
     */
    User saveUser(User user);

    /**
     * Save role role.
     *
     * @param role the role
     * @return the role
     */
    Role saveRole(Role role);

    /**
     * Add role to user.
     *
     * @param roleName the role name
     * @param username the username
     */
    void addRoleToUser(String roleName, String username);

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    User getUser(String username);

    /**
     * Gets users.
     *
     * @return the users
     */
    List<User> getUsers();
}
