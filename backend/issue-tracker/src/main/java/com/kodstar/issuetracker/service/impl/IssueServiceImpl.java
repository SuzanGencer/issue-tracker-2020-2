package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);

    }

    @Override
    public List<Issue> getAllIssues() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Override
    public Issue editIssue(Long issueId, IssueDTO issue) {
        Issue updatedIssue = issueRepository.findById(issueId).orElseThrow(() -> new NoSuchElementException());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(issue,updatedIssue);
        return issueRepository.save(updatedIssue);

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
