package com.authentication.repository;

import com.authentication.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Roles,Integer> {

    Set<Roles> findByRole(String role_admin);
}
