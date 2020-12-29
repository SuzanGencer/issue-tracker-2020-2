package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.repo.LabelRepository;
import com.kodstar.issuetracker.service.LabelService;

import com.kodstar.issuetracker.utils.impl.FromLabelDTOToLabel;
import com.kodstar.issuetracker.utils.impl.FromLabelToLabelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final ModelMapper modelMapper;
    private final FromLabelDTOToLabel fromLabelDTOToLabel;
    private final FromLabelToLabelDTO fromLabelToLabelDTO;


    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository, ModelMapper modelMapper, FromLabelDTOToLabel fromLabelDTOToLabel, FromLabelToLabelDTO fromLabelToLabelDTO) {
        this.labelRepository = labelRepository;
        this.modelMapper = modelMapper;
        this.fromLabelDTOToLabel = fromLabelDTOToLabel;
        this.fromLabelToLabelDTO = fromLabelToLabelDTO;
    }

    @Override
    public LabelDTO createLabel(LabelDTO labelDTO) {
        Label label = fromLabelDTOToLabel.convert(labelDTO);
        LabelDTO labelDto =  fromLabelToLabelDTO.convert(labelRepository.save(label));
        return labelDto;
    }

    @Override
    public Set<LabelDTO> getAllLabels() {
        Set<LabelDTO> labelDTOSet = new HashSet<>();
        for (LabelDTO labelDTO :
                fromLabelToLabelDTO.convertAll((List<Label>) labelRepository.findAll())) {
            labelDTOSet.add(labelDTO);
        }
        return labelDTOSet;
    }




}
