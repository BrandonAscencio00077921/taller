package com.andra.proyecto.Repository;

import com.andra.proyecto.Entities.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeRepository extends JpaRepository<Home, String> {

    // Método para encontrar una casa por su número
    Home findByHomeNumber(String homeNumber);
}
