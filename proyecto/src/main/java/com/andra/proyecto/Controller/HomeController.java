package com.andra.proyecto.Controller;


import com.andra.proyecto.Entities.Home;
import com.andra.proyecto.Entities.Users;
import com.andra.proyecto.Service.HomeService;
import com.andra.proyecto.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/homes/")
public class HomeController {

    @Autowired
    private final HomeService homeService;

    // Crear hogar
    @PostMapping(value = "register")
    public ResponseEntity<Home> registerHome(@RequestBody Home home){
        try {
            Home savedHome = homeService.registerHome(home);
            return new ResponseEntity<>(savedHome, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Buscar hogar por ID
    @GetMapping("{id}")
    public ResponseEntity<Home> getHomeById(@PathVariable String id){
        try {
            Home home = homeService.getHomeById(id);
            return new ResponseEntity<>(home, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Home>> getAllHome(){
        try {
            List<Home> allHomes = homeService.getAllHomes();
            return new ResponseEntity<>(allHomes, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Home> updateHome(@PathVariable("id") String homeID, @RequestBody Home updateHome){
        try {
            Home updatedHome = homeService.updateHome(homeID, updateHome);
            return new ResponseEntity<>(updatedHome, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Home> deleteHome(@PathVariable("id") String homeID){
        try {
            homeService.deleteHome(homeID);
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
