package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.service.IssueService;
import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IssueController {


    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/issue")
    public ResponseEntity<Issue> createIssue(@Valid @NonNull @RequestBody Issue issue) {
        Issue savedIssue = issueService.createIssue(issue);
        return new ResponseEntity<>(savedIssue, HttpStatus.OK);
    }


}
