package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.impl.FromIssueToIssueDTO;
import com.kodstar.issuetracker.utils.impl.FromIssueDTOToIssue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final FromIssueToIssueDTO fromIssueToIssueDTO;
    private final FromIssueDTOToIssue fromIssueDTOToIssue;


    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, FromIssueToIssueDTO fromIssueToIssueDTO, FromIssueDTOToIssue fromIssueDTOToIssue) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.fromIssueToIssueDTO = fromIssueToIssueDTO;
        this.fromIssueDTOToIssue = fromIssueDTOToIssue;
    }

    @Override
    public IssueDTO createIssue(IssueDTO idt) {
        Issue issue = fromIssueDTOToIssue.convert(idt);
        IssueDTO issueDto =  fromIssueToIssueDTO.convert(issueRepository.save(issue));
        return issueDto;
    }

    @Override
    public List<IssueDTO> getAllIssues() {
        List<IssueDTO> issueDTOList = fromIssueToIssueDTO.convertAll(issueRepository.findAll());
        return issueDTOList;
    }

    @Override
    public IssueDTO findById(Long issueId) {
        IssueDTO issueDTO = fromIssueToIssueDTO.convert(
                issueRepository.findById(issueId)
                        .orElseThrow(NoSuchElementException::new));

        return issueDTO;
    }

    @Override
    public List<IssueDTO> findALlByTitleKeyword(String keyword) {
        List<IssueDTO> issueDTOList = fromIssueToIssueDTO.convertAll(issueRepository.findALlByTitleKeyword(keyword));
        return issueDTOList;
    }

    @Override
    public List<IssueDTO> findALlByDescKeyword(String keyword) {
        List<IssueDTO> issueDTOList = fromIssueToIssueDTO.convertAll(issueRepository.findALlByDescKeyword(keyword));
        return issueDTOList;
    }

    @Override
    public IssueDTO editIssue(Long issueId, IssueDTO issue) {
        Issue updatedIssue = issueRepository.findById(issueId)
                .orElseThrow(NoSuchElementException::new);

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(issue,updatedIssue);

        IssueDTO issueDTO = fromIssueToIssueDTO.convert(issueRepository.save(updatedIssue));

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
