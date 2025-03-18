package com.andra.proyecto.Controller;

import com.andra.proyecto.Dto.TokenDTO;
import com.andra.proyecto.Dto.UserLoginDTO;
import com.andra.proyecto.Dto.UserRegisterDTO;
import com.andra.proyecto.Entities.Token;
import com.andra.proyecto.Entities.Users;
import com.andra.proyecto.Repository.RoleRepository;
import com.andra.proyecto.Repository.TokenRepository;
import com.andra.proyecto.Repository.UserRepository;
import com.andra.proyecto.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/")
public class LoginController {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO info, BindingResult validations){
        try {
            // Definir información del usuario
            Users user = new Users();
            user.setFirstName(info.getFirstName());
            user.setLastName(info.getLastName());
            user.setGmail(info.getGmail());
            user.setUsername(info.getUsername());
            user.setPassword(info.getPassword());

            // Formas anteriores que funcionan para definir rol, cuando no se utiliza OAuth2 (no borrar xd)
            //user.setRoles( roleRepository.findByCode("ADMIN"));
            //user.setRoles(roleRepository.findByCode(info.getRoles().get(0).getCode()));

            userRepository.save(user);

            // Crear y almacenar el token basado en el gmail del usuario
            Token token = userService.registerToken(user);
            tokenRepository.save(token);

            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO info, BindingResult validations){
        try {
            Users user = userService.findUserAuthenticated(info);
            Token token = userService.registerToken(user);
            tokenRepository.save(token);
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
