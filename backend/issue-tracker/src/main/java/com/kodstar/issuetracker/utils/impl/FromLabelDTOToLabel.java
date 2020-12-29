package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FromLabelDTOToLabel implements Converter<LabelDTO, Label> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Label convert(LabelDTO labelDTO) {
        Label label = modelMapper.map(labelDTO, Label.class);
        return label;
    }
}
