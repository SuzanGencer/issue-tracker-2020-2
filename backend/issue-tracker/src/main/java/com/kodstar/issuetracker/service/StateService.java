package com.kodstar.issuetracker.service;

import com.kodstar.issuetracker.dto.StateDTO;

import java.util.List;

public interface StateService {

    StateDTO createState(StateDTO stateDTO);

    List<StateDTO> getAllStates();

    void deleteState(Long stateId);

    StateDTO editState(Long stateId, StateDTO stateDTO);

}