package com.andra.proyecto.Service;

import com.andra.proyecto.Dto.UserLoginDTO;
import com.andra.proyecto.Entities.Token;
import com.andra.proyecto.Entities.Users;

import java.util.List;
import java.util.UUID;

public interface UserService {

    // Login management
    Users findOneByIdentifier(String username);
    Users findUserAuthenticated(UserLoginDTO info);

    // Token management
    Token registerToken(Users user) throws Exception;
    Boolean isTokenValid(Users user, String token);
    void cleanTokens(Users user) throws Exception;

    // User management
    Users registerUser(Users user);
    Users getUserById(UUID userID);
    List<Users> getAllUsers();
    Users updateUser(UUID userID, Users user);
    Users assignRole(String gmail, String role);
    void deleteUser(UUID userID);
}
