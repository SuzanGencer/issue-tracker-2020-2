package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.service.LabelService;
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
@CrossOrigin("*")
public class IssueController {


    private final IssueService issueService;
    private final LabelService labelService;

    @Autowired
    public IssueController(IssueService issueService, LabelService labelService) {
        this.issueService = issueService;
        this.labelService = labelService;
    }

    //Internal server error handled

    @GetMapping("/issues")
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> allIssues;
        allIssues = issueService.getAllIssues();
        return new ResponseEntity<>(allIssues, HttpStatus.OK);
    }


    @PostMapping("/issue")
    public ResponseEntity<Issue> createIssue(@Valid @NonNull @RequestBody Issue issue) {

        if (issueService.findByTitle(issue.getTitle()) != null) {
            return new ResponseEntity("Issue is already exists", HttpStatus.BAD_REQUEST);
        } else {
            try {
                Issue savedIssue = issueService.createIssue(issue);
                return new ResponseEntity<>(savedIssue, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity("Db Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @GetMapping("issues/labels")
    public ResponseEntity<Set<Label>> getAllLabels() {

        try {
            return new ResponseEntity<>(labelService.getAllLabels(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Db Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("issue/{issueId}")
    public void editIssue(@PathVariable("issueId") Long issueId, @RequestBody Issue issue) {
        issueService.editIssue(issueId, issue);
    }

    @DeleteMapping("issue/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
    }

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
