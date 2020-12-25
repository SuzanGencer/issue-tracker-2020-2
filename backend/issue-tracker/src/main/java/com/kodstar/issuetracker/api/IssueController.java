package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.service.LabelService;
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
@CrossOrigin("*")
public class IssueController {

    private final IssueService issueService;
    private final IssueConverter issueConverter;
    private final LabelService labelService;

    @Autowired
    public IssueController(IssueService issueService, LabelService labelService, IssueConverter issueConverter) {
        this.issueService = issueService;
        this.issueConverter = issueConverter;
        this.labelService = labelService;
    }

    //Internal server error handled

    @PostMapping("/issue")

    public ResponseEntity<IssueDTO> createIssue(@Valid @NonNull @RequestBody Issue issue) {

        if (issueService.findByTitle(issue.getTitle()) != null) {
            return new ResponseEntity("Issue is already exists", HttpStatus.BAD_REQUEST);
        } else {
            try {
                Issue savedIssue = issueService.createIssue(issue);
                IssueDTO issueDTO = issueConverter.convert(savedIssue);
                return new ResponseEntity<>(issueDTO, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity("Db Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("/issues")
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        List<IssueDTO> issueDTOList = issueConverter.convertAll(issueService.getAllIssues());
        return new ResponseEntity<>(issueDTOList, HttpStatus.OK);
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
