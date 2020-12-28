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
public class FromIssueDTOToIssue implements Converter<IssueDTO, Issue> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FromLabelDTOToLabel fromLabelDTOToLabel;

    @Override
    public Issue convert(IssueDTO issueDTO) {
        Issue issue = modelMapper.map(issueDTO, Issue.class);
        Set<Label> newLabels= new HashSet<>();
        for (LabelDTO labelDto:issueDTO.getLabelDTOSet()) {
            Label label = fromLabelDTOToLabel.convert(labelDto);
            newLabels.add(label);
        }
        issue.setLabels(newLabels);
        return issue;
    }
}
