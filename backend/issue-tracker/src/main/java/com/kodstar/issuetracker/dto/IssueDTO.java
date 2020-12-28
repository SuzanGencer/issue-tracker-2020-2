package com.kodstar.issuetracker.dto;

import com.kodstar.issuetracker.entity.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueDTO implements Serializable {

    private Long id;
    @NotBlank(message = "Title must not be blank!")
    private String title;
    private String description;
    private Set<LabelDTO> labelDTOSet;

}