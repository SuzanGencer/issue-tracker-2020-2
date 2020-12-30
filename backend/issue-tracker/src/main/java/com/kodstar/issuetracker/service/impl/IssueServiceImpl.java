package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.Converter;
import com.kodstar.issuetracker.utils.impl.FromIssueToIssueDTO;
import com.kodstar.issuetracker.utils.impl.FromIssueDTOToIssue;
import com.kodstar.issuetracker.utils.impl.FromLabelToLabelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final FromIssueToIssueDTO fromIssueToIssueDTO;
    private final FromIssueDTOToIssue fromIssueDTOToIssue;
    private final FromLabelToLabelDTO fromLabelToLabelDTO;


    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, FromIssueToIssueDTO fromIssueToIssueDTO, FromIssueDTOToIssue fromIssueDTOToIssue, FromLabelToLabelDTO fromLabelToLabelDTO) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.fromIssueToIssueDTO = fromIssueToIssueDTO;
        this.fromIssueDTOToIssue = fromIssueDTOToIssue;
        this.fromLabelToLabelDTO = fromLabelToLabelDTO;
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
    public List<IssueDTO> findALlIssuesByLabel(Long labelId) {
        List<IssueDTO> issueDTOList = fromIssueToIssueDTO.convertAll(issueRepository.findALlIssuesByLabel(labelId));
        return issueDTOList;
    }

    @Override
    public List<IssueDTO> findALlIssuesSortedByUpdateTime() {
        List<Issue> issues = issueRepository.findAll(Sort.by("updateDateTime").descending());
        List<IssueDTO> issueDTOList = fromIssueToIssueDTO.convertAll(issues);
        return issueDTOList;
    }

   /* @Override
    public Page<IssueDTO> findAllPaginaton(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Issue> issues = issueRepository.findALl(pageable);
        Page<IssueDTO> dtoPage = issues.map(new Converter<Issue, IssueDTO>() {

            @Override
            public IssueDTO convert(Issue issue) {
                IssueDTO issueDTO = modelMapper.map(issue, IssueDTO.class);
                Set<LabelDTO> newLabelDTOs= new HashSet<>();
                for (Label label:issue.getLabels()) {
                    LabelDTO labelDTO = fromLabelToLabelDTO.convert(label);
                    newLabelDTOs.add(labelDTO);
                }
                issueDTO.setLabels(newLabelDTOs);
                return issueDTO;
            }
        });
        return dtoPage;
    }*/


    @Override
    public IssueDTO editIssue(Long issueId, IssueDTO issue) {
        Issue updatedIssue = issueRepository.findById(issueId)
                .orElseThrow(NoSuchElementException::new);

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(issue, updatedIssue);

        IssueDTO issueDTO = fromIssueToIssueDTO.convert(issueRepository.save(updatedIssue));

        return issueDTO;

    }

    @Override
    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }


    @Override
    public void deleteSelectedIssues(List<Long> selectedIssueIds) {
        for (Long id : selectedIssueIds) {
            deleteIssue(id);
        }
    }
}
