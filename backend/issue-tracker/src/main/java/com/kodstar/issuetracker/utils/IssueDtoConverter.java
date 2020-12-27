package com.kodstar.issuetracker.utils;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueDtoConverter implements Converter<IssueDTO, Issue>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Issue convert(IssueDTO issueDTO) {
        Issue issue = modelMapper.map(issueDTO, Issue.class);
        return issue;
    }
}
