package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.StateDTO;
import com.kodstar.issuetracker.entity.State;
import com.kodstar.issuetracker.exceptionhandler.IssueTrackerNotFoundException;
import com.kodstar.issuetracker.repo.StateRepository;
import com.kodstar.issuetracker.service.StateService;
import com.kodstar.issuetracker.utils.impl.FromStateDTOToState;
import com.kodstar.issuetracker.utils.impl.FromStateToStateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final FromStateToStateDTO fromStateToStateDTO;
    private final FromStateDTOToState fromStateDTOToState;
    private final ModelMapper modelMapper;


    @Override
    public StateDTO createState(StateDTO stateDTO) {
        State state = fromStateDTOToState.convert(stateDTO);
        StateDTO newStateDTO = fromStateToStateDTO.convert(stateRepository.save(state));
        return newStateDTO;
    }

    @Override
    public List<StateDTO> getAllStates() {
        List<StateDTO> stateDTOList = fromStateToStateDTO.convertAll((List<State>) stateRepository.findAll());
        return stateDTOList;
    }

    @Override
    public void deleteState(Long stateId) {
        stateRepository.deleteById(stateId);
    }

    @Override
    public StateDTO editState(Long stateId, StateDTO stateDTO) {
        State state = stateRepository.findById(stateId)
                .orElseThrow(()->new IssueTrackerNotFoundException("State",stateId.toString()));
        state.setName(stateDTO.getName());
        return fromStateToStateDTO.convert(stateRepository.save(state));
    }
}
