package com.kodstar.issuetracker.auth;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "invalid input, Title can't be null")
    private String username;

    @NotBlank(message = "invalid input, password can't be null")
    private String password;

}