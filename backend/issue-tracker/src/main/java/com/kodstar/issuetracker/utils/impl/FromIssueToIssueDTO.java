package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class FromIssueToIssueDTO implements Converter<Issue, IssueDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FromLabelToLabelDTO fromLabelToLabelDTO;

    @Override
    public IssueDTO convert(Issue issue) {
        IssueDTO issueDTO = modelMapper.map(issue, IssueDTO.class);
        Set<LabelDTO> newLabelDTOs= new HashSet<>();
        for (Label label:issue.getLabels()) {
            LabelDTO labelDTO = fromLabelToLabelDTO.convert(label);
            newLabelDTOs.add(labelDTO);
        }
        issueDTO.setLabelDTOSet(newLabelDTOs);
        return issueDTO;
    }

}
