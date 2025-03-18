package com.andra.proyecto.Dto;

import com.andra.proyecto.Entities.Role;
import com.andra.proyecto.Entities.Users;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO extends Users {
    private UUID id;
    private List<Role> roles;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String gmail;
}
