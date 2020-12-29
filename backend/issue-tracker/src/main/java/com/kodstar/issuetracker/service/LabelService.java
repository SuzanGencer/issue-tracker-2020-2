package com.kodstar.issuetracker.service;

import com.kodstar.issuetracker.dto.LabelDTO;


import java.util.Set;


public interface LabelService {

    LabelDTO createLabel(LabelDTO labelDTO);
    Set<LabelDTO> getAllLabels();


}
