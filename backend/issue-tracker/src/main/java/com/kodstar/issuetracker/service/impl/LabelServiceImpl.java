package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.repo.LabelRepository;
import com.kodstar.issuetracker.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Set<Label> getAllLabels() {
       Set<Label> labelSet = new HashSet<>();
        for (Label label:labelRepository.findAll() ) {
            labelSet.add(label);
       }

        return labelSet;
    }
}
