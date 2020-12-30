package com.kodstar.issuetracker.service;


import com.kodstar.issuetracker.dto.IssueDTO;


public interface LabelsOfIssueService {

    IssueDTO removeLabelFromIssue(Long labelId, Long issueId);
}
