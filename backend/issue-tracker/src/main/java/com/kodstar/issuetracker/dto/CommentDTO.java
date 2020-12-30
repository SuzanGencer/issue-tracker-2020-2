package com.kodstar.issuetracker.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class CommentDTO implements Serializable {
    private Long id;
    @NotBlank
    private String comment;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
