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
@Table(name = "ANONYMOUS")
public class Anonymous {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "vigilant_uuid")
    @JsonIgnore
    private Users vigilant;

    @Column
    private String visitor; // Autoridad o servicio

    @Column
    private String home; // Número de casa a visitar

    @Column
    private Date date;

    @Column
    private Time hour;
}
