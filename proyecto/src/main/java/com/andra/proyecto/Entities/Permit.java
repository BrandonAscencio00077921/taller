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
@Table(name = "PERMIT")
public class Permit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "resident_uuid")
    @JsonIgnore
    private Users resident;

    @ManyToOne
    @JoinColumn(name = "home_uuid")
    @JsonIgnore
    private Home home;

    @ManyToOne
    @JoinColumn(name = "visitor_uuid")
    @JsonIgnore
    private Users visitor;

    @Column
    private String date;

    @Column
    private String hour;

    @Column
    private Integer entries;

    @Column
    private String status;
}
