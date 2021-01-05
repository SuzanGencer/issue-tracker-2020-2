package com.kodstar.issuetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //LabelName cant be @id because one label can be used with many issues. so it musn t be unique at the issue creation time.
    @NotBlank(message = "invalid input, Label name can't be null")
    @Column()
    private String labelName;

    @Column()
    private String labelColor;


}
