package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);

    }

    @Override
    public List<Issue> getAllIssues() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Override
    public void editIssue(Long issueId, Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue findByTitle(String title) {
        return issueRepository.findByTitle(title);
    }


}
