package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.service.LabelService;
import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@CrossOrigin("*")
public class IssueController {

    private final IssueService issueService;
    private final LabelService labelService;

    @Autowired
    public IssueController(IssueService issueService, LabelService labelService) {
        this.issueService = issueService;
        this.labelService = labelService;
    }

    @GetMapping("/issues")
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        return new ResponseEntity(issueService.getAllIssues(), HttpStatus.OK);
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<IssueDTO> findIssueById(@PathVariable("issueId") Long issueId) {
        return new ResponseEntity(issueService.findById(issueId), HttpStatus.OK);
    }

    @GetMapping("issues/labels")
    public ResponseEntity<Set<Label>> getAllLabels() {
        return new ResponseEntity(labelService.getAllLabels(), HttpStatus.OK);
    }

    @PostMapping("/issue")
    public ResponseEntity<IssueDTO> createIssue(@Valid @NonNull @RequestBody IssueDTO issue) {
        return new ResponseEntity(issueService.createIssue(issue), HttpStatus.CREATED);
    }

    @PutMapping("issue/{issueId}")
    public ResponseEntity<IssueDTO> editIssue(@PathVariable("issueId") Long issueId, @RequestBody IssueDTO issueDTO) {
        return new ResponseEntity(issueService.editIssue(issueId, issueDTO), HttpStatus.OK);
    }

    @DeleteMapping("issue/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
    }


}
