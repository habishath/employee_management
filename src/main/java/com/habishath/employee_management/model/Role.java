package com.habishath.employee_management.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(
                Permission.USER_READ
            )
    ),
    MANAGER(
            Set.of(
                Permission.USER_READ,
                Permission.MANAGER_READ,
                Permission.MANAGER_CREATE
            )
    ),
    ADMIN(
            Set.of(
                    Permission.USER_READ,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_CREATE,
                    Permission.ADMIN_READ,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<>(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
