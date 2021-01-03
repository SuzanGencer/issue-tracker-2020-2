package com.kodstar.issuetracker.service;

import com.kodstar.issuetracker.dto.CommentDTO;
import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Comment;
import com.kodstar.issuetracker.entity.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

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

    List<IssueDTO> findALlIssuesByLabel(String keyword);

    IssueDTO addComment(Long issueId, CommentDTO commentDTO);

    void deleteComment(Long issueId, Long commentId);

    List<IssueDTO> getAllIssuesOrderByCreateTime(boolean isAscending);

    List<IssueDTO> getAllIssuesOrderByUpdateTime(boolean isAscending);

    List<IssueDTO> getAllIssuesSort( String orderType, String byWhichSort);



}
