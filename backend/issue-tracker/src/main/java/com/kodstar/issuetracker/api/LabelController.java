package com.kodstar.issuetracker.api;


import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("issues")
public class LabelController {

    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping("labels")
    public ResponseEntity<Set<LabelDTO>> getAllLabels() {
        return new ResponseEntity(labelService.getAllLabels(), HttpStatus.OK);
    }

    @PostMapping("label")
    public ResponseEntity<LabelDTO> createLabel(@Valid @NonNull @RequestBody LabelDTO labelDTO) {
        return new ResponseEntity(labelService.createLabel(labelDTO), HttpStatus.CREATED);
    }



}
