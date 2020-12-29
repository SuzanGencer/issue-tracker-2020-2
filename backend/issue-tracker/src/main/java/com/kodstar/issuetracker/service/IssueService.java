package com.kodstar.issuetracker.service;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IssueService {

    IssueDTO createIssue(IssueDTO issue);

    IssueDTO editIssue(Long issueId, IssueDTO issue);

    void deleteIssue(Long issueId);
 
    List<IssueDTO> getAllIssues();

    IssueDTO findById(Long issueId);


    void deleteSelectedIssues(List<Long> selectedIssueIds);

    List<IssueDTO> findALlByTitleKeyword(String keyword);

    List<IssueDTO> findALlByDescKeyword(String keyword);

}
