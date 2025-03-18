package com.andra.proyecto.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "QR")
public class QR {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "users_uuid")
    private Users users;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "home_uuid")
    private Home home;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "token_uuid")
    private Token token;

    @Column
    private Date date;

    @Column
    private Time hour;
}
