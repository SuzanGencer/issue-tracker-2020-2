package com.kodstar.issuetracker.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private long id;
    @NotBlank(message = "invalid input, Title can't be null")
    private String username;
    private String password;
}
