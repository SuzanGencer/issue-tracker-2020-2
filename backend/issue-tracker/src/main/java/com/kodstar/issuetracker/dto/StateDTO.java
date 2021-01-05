package com.kodstar.issuetracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class StateDTO implements Serializable {

    private Long id;

    @NotBlank(message = "State name can't be null!")
    private String name;
}
