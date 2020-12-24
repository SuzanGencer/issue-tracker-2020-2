package com.kodstar.issuetracker.dto;

import com.kodstar.issuetracker.entity.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueDTO {
    private Long id;
    private String title;
    private String description;
    private Set<Label> labels;
}