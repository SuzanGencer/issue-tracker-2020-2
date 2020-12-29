package com.kodstar.issuetracker.service;


import com.kodstar.issuetracker.dto.IssueDTO;
import org.springframework.data.jpa.repository.Query;

public interface LabelsOfIssueService {


    IssueDTO removeLabelFromIssue(Long labelId, Long issueId);
}
