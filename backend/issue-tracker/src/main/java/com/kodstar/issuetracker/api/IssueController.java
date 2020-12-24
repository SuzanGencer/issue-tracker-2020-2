package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.IssueConverter;
import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
public class IssueController {


    private final IssueService issueService;
    private final IssueConverter issueConverter;

    @Autowired
    public IssueController(IssueService issueService, IssueConverter issueConverter) {
        this.issueService = issueService;
        this.issueConverter=issueConverter;
    }

    @PostMapping("/issue")
    public ResponseEntity<IssueDTO> createIssue(@Valid @NonNull @RequestBody Issue issue){

        if(issueService.findByTitle(issue.getTitle()) !=null)
        {
            return new ResponseEntity("Issue is already exists", HttpStatus.BAD_REQUEST);
        }else {
            Issue savedIssue = issueService.createIssue(issue);
            IssueDTO issueDTO=issueConverter.convert(savedIssue);
            return new ResponseEntity<>(issueDTO, HttpStatus.OK);
        }
    }
    @GetMapping("/issues")
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        List<IssueDTO> issueDTOList=issueConverter.convertAll(issueService.getAllIssues());
        return new ResponseEntity<>(issueDTOList, HttpStatus.OK);
    }
/*
// This part written simply for only test the getAllLabels

    @GetMapping("/issues")
    public ResponseEntity<List<Issue>> getAllIssues(){
        List<Issue> allIssues = new ArrayList<>();
        allIssues = issueService.getAllIssues();

        return new ResponseEntity<>(allIssues, HttpStatus.OK);
    }

    @GetMapping("/issues/labels")
    public ResponseEntity<Set<Label>> getAllLabels(){

        List<Issue> allIssues = new ArrayList<>();
        allIssues = issueService.getAllIssues();

        Set<Label> labels = new HashSet<>();

        for (Issue issue: allIssues) {
            for (Label label:issue.getLabels()) {
                labels.add(label);
            }
        }

        return new ResponseEntity<>(labels, HttpStatus.OK);
    }

    */



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



}
