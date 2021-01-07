package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.dto.CommentDTO;
import com.kodstar.issuetracker.dto.IssueDTO;

import com.kodstar.issuetracker.service.IssueService;
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
    private final static String ASCENDING="asc" ;
    private final static String DESCENDING="desc" ;
    private final static String ORDER_TYPE_ERROR_MESSAGE=" Recieved OrderType is : %s .\nOrder Type must be asc or desc.";

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;

    }


   @GetMapping("/issues")
   public ResponseEntity<List<IssueDTO>> getAllIssues(@RequestParam(required = false, defaultValue = ASCENDING, value = "orderType") String orderType,
                                                      @RequestParam(required = false, value = "byWhichSort") String byWhichSort) {

           return new ResponseEntity<>(issueService.getAllIssuesSort(orderType,byWhichSort), HttpStatus.OK);

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

    @PostMapping("issue/labels/{issueId}/{labelId}")
    public ResponseEntity<IssueDTO> addLabel(@PathVariable Long labelId, @PathVariable Long issueId) {
        return new ResponseEntity(issueService.addLabel(labelId, issueId), HttpStatus.OK);
    }

    @PutMapping("issue/labels/{issueId}/{labelId}")
    public ResponseEntity<IssueDTO> removeLabelFromIssue(@PathVariable Long labelId, @PathVariable Long issueId) {
        return new ResponseEntity(issueService.removeLabelFromIssue(labelId, issueId), HttpStatus.OK);
    }

    @PostMapping("issue/users/{issueId}/{userId}")
    public ResponseEntity<IssueDTO> addAssignee(@PathVariable Long userId, @PathVariable Long issueId) {
        return new ResponseEntity(issueService.addAssignee(userId, issueId), HttpStatus.OK);
    }

    @PutMapping("issue/users/{issueId}/{userId}")
    public ResponseEntity<IssueDTO> removeAssigneeFromIssue(@PathVariable Long userId, @PathVariable Long issueId) {
        return new ResponseEntity(issueService.removeAssigneeFromIssue(userId, issueId), HttpStatus.OK);
    }



    @GetMapping("issues/search/title/{keyword}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByTitleKeyword(@PathVariable String keyword) {
        return new ResponseEntity(issueService.findALlByTitleKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("issues/search/description/{keyword}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByDescKeyword(@PathVariable String keyword) {
        return new ResponseEntity(issueService.findALlByDescKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("issues/search/label/{keyword}")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByLabelKeyword(@PathVariable String keyword) {
        return new ResponseEntity(issueService.findALlIssuesByLabel(keyword), HttpStatus.OK);
    }

    @PostMapping("issue/{issueId}/comment")
    public ResponseEntity<IssueDTO> addComment(@RequestBody CommentDTO commentDTO, @PathVariable Long issueId) {
        return new ResponseEntity(issueService.addComment(issueId, commentDTO), HttpStatus.OK);
    }


    @DeleteMapping("issue/{issueId}/comment/{commentId}")
    public void deleteComment(@PathVariable Long issueId, @PathVariable Long commentId) {
        issueService.deleteComment( issueId, commentId);
    }

    @PutMapping("issue/{issueId}/state/{stateId}")
    public ResponseEntity<IssueDTO> updateStateOfIssue(@PathVariable Long issueId, @PathVariable Long stateId) {
        return new ResponseEntity(issueService.updateState(issueId, stateId), HttpStatus.OK);
    }

}
