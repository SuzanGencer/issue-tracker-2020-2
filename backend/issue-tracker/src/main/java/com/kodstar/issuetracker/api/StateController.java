package com.kodstar.issuetracker.api;

import com.kodstar.issuetracker.dto.StateDTO;
import com.kodstar.issuetracker.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateController {
    private final StateService stateService;

    @PostMapping("state")
    public ResponseEntity<StateDTO> createState(@Valid @NonNull @RequestBody StateDTO state) {
        return new ResponseEntity(stateService.createState(state), HttpStatus.CREATED);
    }

    @GetMapping("state")
    public ResponseEntity<List<StateDTO>> getAllStates() {
        return new ResponseEntity<List<StateDTO>>(stateService.getAllStates(), HttpStatus.OK);
    }

    @DeleteMapping("state/{stateId}")
    public void deleteState(@PathVariable Long stateId) {
        stateService.deleteState(stateId);
    }

    @PutMapping("state/{stateId}")
    public ResponseEntity<StateDTO> editState(@PathVariable Long stateId, @RequestBody StateDTO stateDTO) {
        return new ResponseEntity(stateService.editState(stateId, stateDTO), HttpStatus.OK);
    }
}

