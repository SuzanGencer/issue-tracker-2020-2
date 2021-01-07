package com.kodstar.issuetracker.dto;

import com.kodstar.issuetracker.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private Set<LabelDTO> labels;
    private List<CommentDTO> comments =new ArrayList<>();
    private LocalDateTime createTime;
    private StateDTO state;
    private LocalDateTime updateTime;
    private Set<User> assignees = new HashSet<>();

}