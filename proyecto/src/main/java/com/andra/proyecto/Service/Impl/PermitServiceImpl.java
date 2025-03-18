package com.andra.proyecto.Service.Impl;

import com.andra.proyecto.Entities.Permit;
import com.andra.proyecto.Exception.ResourceNotFoundException;
import com.andra.proyecto.Repository.HomeRepository;
import com.andra.proyecto.Repository.PermitRepository;
import com.andra.proyecto.Repository.UserRepository;
import com.andra.proyecto.Service.PermitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PermitServiceImpl implements PermitService {

    @Autowired
    private PermitRepository permitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Override
    public Permit registerPermit(Permit permit, String residentGmail, String visitantGmail, String homeNumber) {
        Permit newPermit = new Permit();

        newPermit.setDate(permit.getDate());
        newPermit.setHour(permit.getHour());
        newPermit.setEntries(permit.getEntries());
        newPermit.setStatus(permit.getStatus());
        newPermit.setHome(homeRepository.findByHomeNumber(homeNumber));
        //newPermit.setResident(userRepository.findByGmail(residentGmail).get(0));
        //newPermit.setVisitor(userRepository.findByGmail(visitantGmail).get(0));

        permitRepository.save(newPermit);
        return newPermit;
    }

    @Override
    public Permit getPermitById(UUID permitID) {
        return permitRepository.findById(permitID).orElseThrow(() ->  new ResourceNotFoundException("Permit with id " + permitID+ " not found"));
    }

    @Override
    public List<Permit> getPermits() {
        return permitRepository.findAll();
    }

    @Override
    public Permit updatePermit(UUID permitId, Permit updatedPermit) {
        return null;
    }

    @Override
    public void deletePermit(UUID permitID) {

    }

    @Override
    public List<Permit> getPermitByHome(String homeNumber) {
        return List.of();
    }

    @Override
    public List<Permit> getPermitByVisitant(UUID visitantID) {
        return List.of();
    }

    @Override
    public List<Permit> getPermitByResident(UUID residentID) {
        return List.of();
    }
}
