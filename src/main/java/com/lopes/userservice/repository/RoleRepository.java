package com.lopes.userservice.repository;

import com.lopes.userservice.domain.Role;
import com.lopes.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    User findByName(String name);
}
