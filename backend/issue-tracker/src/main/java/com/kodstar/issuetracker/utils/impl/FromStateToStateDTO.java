package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.StateDTO;
import com.kodstar.issuetracker.entity.State;
import com.kodstar.issuetracker.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class FromStateToStateDTO implements Converter<State, StateDTO> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public StateDTO convert(State state) {
        StateDTO stateDTO=modelMapper.map(state,StateDTO.class);
        return stateDTO;
    }
}
