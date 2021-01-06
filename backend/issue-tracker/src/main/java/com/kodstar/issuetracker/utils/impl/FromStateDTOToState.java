package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.StateDTO;
import com.kodstar.issuetracker.entity.State;
import com.kodstar.issuetracker.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


@Service
public class FromStateDTOToState implements Converter<StateDTO,State> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public State convert(StateDTO stateDTO) {
        State state=modelMapper.map(stateDTO, State.class);
        return state;
    }
}
