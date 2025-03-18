package com.andra.proyecto.Controller;

import com.andra.proyecto.Dto.UserRegisterDTO;
import com.andra.proyecto.Entities.Users;
import com.andra.proyecto.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/administrator/")
public class AdministratorController {

    @Autowired
    private UserService userService;

    // Crear usuario
//    @PostMapping(value = "register")
//    public ResponseEntity<Users> registerUser(@RequestBody UserRegisterDTO userDto) {
//        Users savedUsuario = userService.registerUser(userDto);
//        //return ResponseEntity.ok(savedUsuario);
//        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
//    }

    // Crear usuario
    @PostMapping(value = "register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        try {
            Users savedUsuario = userService.registerUser(user);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getUserById(@PathVariable ("id") UUID userID) {
        try {
            Users user = userService.getUserById(userID);
            return new ResponseEntity<>(user, HttpStatus.FOUND); //return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> allUsers = userService.getAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK); //return ResponseEntity.ok(allUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Users> updateUser(@PathVariable ("id") UUID userID, @RequestBody Users updateUser) {
        try {
            Users updatedUser = userService.updateUser(userID, updateUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED); //return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @PutMapping("assignRole")
    public ResponseEntity<Users> assignRole(@RequestBody Map<String, String> payload) {
        String gmail = payload.get("gmail");
        String role = payload.get("role");
        try {
            Users updatedUser = userService.assignRole(gmail, role);
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED); //return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable ("id") UUID userID) {
        try {
            userService.deleteUser(userID);
            return ResponseEntity.status(HttpStatus.GONE).build(); // return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
