package com.andra.proyecto.Repository;

import com.andra.proyecto.Entities.Token;
import com.andra.proyecto.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token> findByUsersAndActive(Users user, Boolean active);
}
