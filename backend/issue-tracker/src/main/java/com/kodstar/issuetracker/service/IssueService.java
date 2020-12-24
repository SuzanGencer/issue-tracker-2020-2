package com.kodstar.issuetracker.service;

import com.kodstar.issuetracker.entity.Issue;

import java.util.List;




public interface IssueService {

   Issue createIssue(Issue issue);

   Issue findByTitle(String title);

   List<Issue> getAllIssues();

   void editIssue(Long issueId, Issue issue);

   void deleteIssue(Long issueId);

}
