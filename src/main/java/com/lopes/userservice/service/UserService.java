package com.lopes.userservice.service;

import com.lopes.userservice.domain.Role;
import com.lopes.userservice.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String roleName, String username);

    User getUser(String username);

    List<User> getUser();
}
