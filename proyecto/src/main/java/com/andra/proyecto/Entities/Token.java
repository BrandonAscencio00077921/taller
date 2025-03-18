package com.andra.proyecto.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "TOKEN")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp", updatable = false)
    private Date timestamp;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_uuid")
    @JsonIgnore
    private Users users;

    @OneToMany(mappedBy = "token")
    @JsonIgnore
    private List<QR> QRs;

    public Token(String content, Users user) {
        super();
        this.content = content;
        this.users = user;
        this.timestamp = Date.from(Instant.now());
        this.active = true;
    }
}
