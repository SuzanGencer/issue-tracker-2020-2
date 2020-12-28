package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.repo.LabelRepository;
import com.kodstar.issuetracker.service.LabelsOfIssueService;
import com.kodstar.issuetracker.utils.impl.FromIssueToIssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.NoSuchElementException;

@Service
public class LabelsOfIssueServiceImpl implements LabelsOfIssueService {
    private final LabelRepository labelRepository;
    private final IssueRepository issueRepository;
    private final FromIssueToIssueDTO fromIssueToIssueDTO;


    @Autowired
    public LabelsOfIssueServiceImpl(LabelRepository labelRepository, IssueRepository issueRepository, FromIssueToIssueDTO fromIssueToIssueDTO) {
        this.labelRepository = labelRepository;
        this.issueRepository = issueRepository;
        this.fromIssueToIssueDTO = fromIssueToIssueDTO;

    }

    @Override
   @Transactional
    public IssueDTO removeLabelFromIssue(Long labelId, Long issueId) {
        Issue issue = issueRepository.findById(issueId)
                     .orElseThrow(NoSuchElementException::new);
        Label label = labelRepository.findById(labelId)
                    .orElseThrow(NoSuchElementException::new);
        issue.removeLabel(label);
        IssueDTO issueDTO= fromIssueToIssueDTO.convert(issue);
        return issueDTO;
    }

}
