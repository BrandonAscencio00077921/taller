package com.andra.proyecto.Service;

import com.andra.proyecto.Entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolService {

    List<Role> getAllRoles();
}
