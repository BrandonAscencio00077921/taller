package com.andra.proyecto.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "USERS")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column (unique = true)
    private String username;

    @Column
    private String password;

    @Column (unique = true)
    private String gmail;

//    @Column
//    private Roles role;

//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_code")
    )
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "home_id")
    @JsonIgnore
    private Home home;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @OneToMany(mappedBy = "admin")
    @JsonIgnore
    private List<Device> devices;

    @OneToMany(mappedBy = "vigilant")
    @JsonIgnore
    private List<Anonymous> anonymous;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<QR> QRs;

    @OneToMany(mappedBy = "resident")
    @JsonIgnore
    private List<Permit> residentPermit;

    @OneToMany(mappedBy = "visitor")
    @JsonIgnore
    private List<Permit> visitorPermit;

    public Users(UUID id, List<Role> role, String firstName, String lastName, String username, String password, String gmail) {
        this.id = id;
        this.roles = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        for (Roles role : Roles.values()) {
//            authorities.add(new SimpleGrantedAuthority(role.name()));
//        }
//        return authorities;
//    }
//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = this.roles;
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return authorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (role == Roles.ADMIN) {
//            authorities.add(new SimpleGrantedAuthority(Roles.ADMIN.name()));
//        }
//        else if (role == Roles.RESEN) {
//            authorities.add(new SimpleGrantedAuthority(Roles.RESEN.name()));
//        }
//        else if (role == Roles.VISIT) {
//            authorities.add(new SimpleGrantedAuthority(Roles.VISIT.name()));
//        }
//        else if (role == Roles.RESNO) {
//            authorities.add(new SimpleGrantedAuthority(Roles.RESNO.name()));
//        }
//        else if (role == Roles.VIGIL) {
//            authorities.add(new SimpleGrantedAuthority(Roles.VIGIL.name()));
//        }
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
