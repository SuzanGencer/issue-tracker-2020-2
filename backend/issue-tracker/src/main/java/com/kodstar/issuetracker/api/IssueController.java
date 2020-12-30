package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.service.LabelsOfIssueService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
    private final LabelsOfIssueService labelsOfIssueService;


    @Autowired
    public IssueController(IssueService issueService, LabelsOfIssueService labelsOfIssueService) {
        this.issueService = issueService;
        this.labelsOfIssueService = labelsOfIssueService;
    }

    @GetMapping("/issues")
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        return new ResponseEntity(issueService.getAllIssues(), HttpStatus.OK);
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<IssueDTO> findIssueById(@PathVariable("issueId") Long issueId) {
        return new ResponseEntity(issueService.findById(issueId), HttpStatus.OK);
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

    @DeleteMapping("/issues/{selectedIssueIds}")
    public void deleteSelectedIssues(@PathVariable List<Long> selectedIssueIds) {
        issueService.deleteSelectedIssues(selectedIssueIds);
    }


    @PutMapping("issue/{issueId}/{labelId}")
    public ResponseEntity<IssueDTO> removeLabelFromIssue(@PathVariable Long labelId, @PathVariable Long issueId) {
        return new ResponseEntity(labelsOfIssueService.removeLabelFromIssue(labelId, issueId), HttpStatus.OK);
    }

    @GetMapping("issues/search/title/{keyword}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByTitleKeyword(@PathVariable String keyword) {
        return new ResponseEntity(issueService.findALlByTitleKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("issues/search/description/{keyword}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByDescKeyword(@PathVariable String keyword) {
        return new ResponseEntity(issueService.findALlByDescKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("issues/search/label/{labelId}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByDescKeyword(@PathVariable Long labelId) {
        return new ResponseEntity(issueService.findALlIssuesByLabel(labelId), HttpStatus.OK);
    }

    @GetMapping("issues/sort/updateTime")
    public ResponseEntity<List<IssueDTO>> ALlIssuesSortedByUpdateTime() {
        return new ResponseEntity(issueService.findALlIssuesSortedByUpdateTime(), HttpStatus.OK);
    }

}
