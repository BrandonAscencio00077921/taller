package com.andra.proyecto.Service.Impl;

import com.andra.proyecto.Dto.UserLoginDTO;
import com.andra.proyecto.Entities.Role;
import com.andra.proyecto.Entities.Token;
import com.andra.proyecto.Entities.Users;
import com.andra.proyecto.Exception.ResourceNotFoundException;
import com.andra.proyecto.Repository.RoleRepository;
import com.andra.proyecto.Repository.TokenRepository;
import com.andra.proyecto.Repository.UserRepository;
import com.andra.proyecto.Service.UserService;
import com.andra.proyecto.Utils.JWTTools;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public Users findOneByIdentifier(String username) {
        return userRepository.findByUsername(username);
    }

//    @Override
//    public Users findUserAuthenticated() {
//        String username = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//
//        //return userRepository.findOneByUsernameOrGmail(username, username);
//        return userRepository.findByUsername(username);
//    }

    @Override
    public Users findUserAuthenticated(UserLoginDTO info) {
        return userRepository.findByUsername(info.username);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(Users user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(Users user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUsersAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(Exception::new);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(Users user) throws Exception {
        List<Token> tokens = tokenRepository.findByUsersAndActive(user, true);

        tokens.forEach(token -> {
            if(!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
    }

    @Override
    public Users registerUser(Users users) {
        Users user = new Users();
        user.setId(users.getId());
        user.setLastName(users.getLastName());
        user.setFirstName(users.getFirstName());
        //user.setHome(users.getHome());
        user.setGmail(users.getGmail());
        //user.setRoles(roleRepository.findByCode(users.getRoles().get(0).getCode()));

        /*Users users = UserMapper.mapToUser(userDto);
        Users savedUsers = userRepository.save(users);*/
        userRepository.save(user);
        //return UserMapper.mapToUserDto(savedUsers);
        return user;
    }

    @Override
    public Users getUserById(UUID userID) {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with id " + userID + " not found"));
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users updateUser(UUID userID, Users updateUser) {
        Users user = userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userID + " not found"));

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setRoles(roleRepository.findByCode(updateUser.getRoles().get(0).getCode()));

        return userRepository.save(user);
    }

    @Override
    public Users assignRole(String gmail, String role) {
        Users user = userRepository.findByGmail(gmail);
        user.setRoles(roleRepository.findByCode(role));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID userID) {
        Users users = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userID + " not found"));

        userRepository.deleteById(userID);
    }
}
