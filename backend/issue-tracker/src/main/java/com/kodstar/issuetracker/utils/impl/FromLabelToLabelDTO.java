package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromLabelToLabelDTO implements Converter<Label, LabelDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LabelDTO convert(Label label) {
        LabelDTO labelDTO = modelMapper.map(label, LabelDTO.class);
        return labelDTO;
    }
}
