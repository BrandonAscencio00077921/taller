package com.andra.proyecto.Service;

import com.andra.proyecto.Entities.Permit;
import com.andra.proyecto.Entities.Users;

import java.util.List;
import java.util.UUID;

public interface PermitService {

    // Permit Management
    Permit registerPermit(Permit permit, String residentGmail, String visitantGmail, String homeNumber);
    Permit getPermitById(UUID permitID);
    List<Permit> getPermits();
    Permit updatePermit(UUID permitId, Permit updatedPermit);
    void deletePermit(UUID permitID);

    // Método para encontrar los permisos realizados para un hogar
    List<Permit> getPermitByHome (String homeNumber);

    // Método para obtener los permisos realizados por un visitante
    List<Permit> getPermitByVisitant (UUID visitantID);

    // Método para obtener los permisos realizados por un residente
    List<Permit> getPermitByResident (UUID residentID);
}
