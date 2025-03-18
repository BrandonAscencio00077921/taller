package com.andra.proyecto.Repository;

import com.andra.proyecto.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID>
{
    // Método para buscar usuario por su nombre
    Users findByUsername(String username);

    // Método para buscar usuario por su gmail
    Users findByGmail(String gmail);
}
