package com.nhi.bookstore.repositories;

import com.nhi.bookstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleReponsitory extends JpaRepository<Role, Integer> {
    Role findByNameContaining(String name);
}
