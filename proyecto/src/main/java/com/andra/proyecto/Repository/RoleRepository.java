package com.andra.proyecto.Repository;

import com.andra.proyecto.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByCode(String rol);
}
