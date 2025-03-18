package com.andra.proyecto.Service.Impl;

import com.andra.proyecto.Entities.Role;
import com.andra.proyecto.Repository.RoleRepository;
import com.andra.proyecto.Service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolServiceImpl implements RolService {

    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
