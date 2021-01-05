package com.kodstar.issuetracker.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LabelDTO  implements Serializable {

        private Long id;

        @NotBlank(message = "Label name must not be blank!")
        private String labelName;

        private String labelColor;


}
