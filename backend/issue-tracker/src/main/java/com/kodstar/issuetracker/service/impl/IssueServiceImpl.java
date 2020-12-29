package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.IssueConverter;
import com.kodstar.issuetracker.utils.IssueDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final IssueConverter issueConverter;
    private final IssueDtoConverter issueDtoConverter;


    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, IssueConverter issueConverter, IssueDtoConverter issueDtoConverter) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.issueConverter = issueConverter;
        this.issueDtoConverter = issueDtoConverter;
    }

    @Override
    public IssueDTO createIssue(IssueDTO idt) {
        Issue issue = issueDtoConverter.convert(idt);
        IssueDTO issueDto =  issueConverter.convert(issueRepository.save(issue));
        return issueDto;
    }

    @Override
    public List<IssueDTO> getAllIssues() {
        List<IssueDTO> issueDTOList = issueConverter.convertAll(issueRepository.findAll());
        return issueDTOList;
    }

    @Override
    public IssueDTO findById(Long issueId) {
        IssueDTO issueDTO = issueConverter.convert(
                issueRepository.findById(issueId)
                        .orElseThrow(NoSuchElementException::new));

        return issueDTO;
    }

    @Override
    public IssueDTO editIssue(Long issueId, IssueDTO issue) {
        Issue updatedIssue = issueRepository.findById(issueId)
                .orElseThrow(NoSuchElementException::new);

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(issue,updatedIssue);

        IssueDTO issueDTO = issueConverter.convert(issueRepository.save(updatedIssue));

        return issueDTO;

    }

    @Override
    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }


    @Override
    public void deleteSelectedIssues(List<Long> selectedIssueIds) {
        for (Long id:selectedIssueIds) {
            deleteIssue(id);
        }
    }

}
