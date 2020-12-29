package com.kodstar.issuetracker.service;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.dto.LabelDTO;


import java.util.Set;


public interface LabelService {

    void deleteLabel(Long labelId);

    LabelDTO createLabel(LabelDTO labelDTO);

    Set<LabelDTO> getAllLabels();

    LabelDTO editLabel(Long labelId, LabelDTO labelDTO);


}
