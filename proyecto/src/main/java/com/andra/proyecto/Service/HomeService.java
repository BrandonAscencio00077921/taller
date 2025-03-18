package com.andra.proyecto.Service;

import com.andra.proyecto.Entities.Home;
import com.andra.proyecto.Entities.Users;

import java.util.List;
import java.util.UUID;

public interface HomeService {

    // Home Management
    Home registerHome(Home home);
    Home getHomeById(String homeID);
    List<Home> getAllHomes();
    Home updateHome(String homeID, Home updatedHome);
    void deleteHome(String homeID);

}
