package com.andra.proyecto.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "HOME")
public class Home {
    @Id
    private String id;

    @Column
    private String homeNumber;

    @Column
    private String address;

    @Column
    private Integer residentsNumber;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<Users> users;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<QR> QRs;

    @OneToMany(mappedBy = "home")
    @JsonIgnore
    private List<Permit> permits;

    public Home(String homeNumber, String address, Integer residentsNumber) {
        this.homeNumber = homeNumber;
        this.address = address;
        this.residentsNumber = residentsNumber;
    }
}
