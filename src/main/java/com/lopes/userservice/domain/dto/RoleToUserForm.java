package com.lopes.userservice.domain.dto;

import lombok.Data;

/**
 * The type Role to user form.
 */
@Data
public class RoleToUserForm {

    /**
     * The Username.
     */
    private String username;

    /**
     * The Role name.
     */
    private String roleName;
}
