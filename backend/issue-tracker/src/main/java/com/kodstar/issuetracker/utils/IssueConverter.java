package com.kodstar.issuetracker.utils;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class IssueConverter implements Converter<Issue, IssueDTO>{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IssueDTO convert(Issue issue) {
            IssueDTO issueDTO=modelMapper.map(issue,IssueDTO.class);
            return issueDTO;
    }
}
