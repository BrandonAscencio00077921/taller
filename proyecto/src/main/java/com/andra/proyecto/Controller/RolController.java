package com.andra.proyecto.Controller;

import com.andra.proyecto.Entities.Role;
import com.andra.proyecto.Service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = rolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
