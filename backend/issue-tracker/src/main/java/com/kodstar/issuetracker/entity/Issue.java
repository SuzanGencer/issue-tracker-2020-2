package com.kodstar.issuetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Moon on 12/20/2020
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ISSUE")

public class Issue implements Serializable {

    // TODO Look for generated strategy
    // TODO validations

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "varchar(250)")
    @NotBlank
    private String title;

    @Column( columnDefinition = "varchar(1500)")
    private String description;

}
