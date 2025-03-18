package com.andra.proyecto.Repository;

import com.andra.proyecto.Entities.Home;
import com.andra.proyecto.Entities.Permit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PermitRepository extends JpaRepository<Permit, UUID> {

    // Método para encontrar los permisos de un hogar
    List<Permit> findPermitsByHome(Home home);
}
