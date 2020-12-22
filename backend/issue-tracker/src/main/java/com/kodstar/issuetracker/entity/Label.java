package com.kodstar.issuetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "invalid input, Label name can't be null")
    @Column()
    private String labelName;




}
