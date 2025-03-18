package com.andra.proyecto.Service.Impl;

import com.andra.proyecto.Entities.Home;
import com.andra.proyecto.Entities.Users;
import com.andra.proyecto.Exception.ResourceNotFoundException;
import com.andra.proyecto.Repository.HomeRepository;
import com.andra.proyecto.Repository.UserRepository;
import com.andra.proyecto.Service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeRepository homeRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Home registerHome(Home home) {
        Home newHome = new Home();
        newHome.setId(home.getId());
        newHome.setHomeNumber(home.getHomeNumber());
        newHome.setAddress(home.getAddress());
        newHome.setResidentsNumber(home.getResidentsNumber());
        homeRepository.save(newHome);
        return newHome;
    }

    @Override
    public Home getHomeById(String homeID) {
        return homeRepository.findById(homeID).orElseThrow(() ->
                new ResourceNotFoundException("Home with id " + homeID+ " not found"));
    }

    @Override
    public List<Home> getAllHomes() {
        return homeRepository.findAll();
    }

    @Override
    public Home updateHome(String homeID, Home updatedHome) {
        Home home = homeRepository.findById(homeID).orElseThrow(() -> new ResourceNotFoundException("Home with id: " + homeID + " not found"));
        home.setId(updatedHome.getId());
        home.setHomeNumber(updatedHome.getHomeNumber());
        home.setAddress(updatedHome.getAddress());
        home.setResidentsNumber(updatedHome.getResidentsNumber());
        //home.setUsers(userRepository.findByGmail(updatedHome.getUsers().get(0).getGmail()));
        // TODO : AGREGAR LA LISTA DE PERMISOS

        homeRepository.save(home);
        return home;
    }

    @Override
    public void deleteHome(String homeID) {
        Home home = homeRepository.findById(homeID).orElseThrow(() -> new ResourceNotFoundException("Home with id: " + homeID+ " not found"));

        homeRepository.delete(home);
    }
}
