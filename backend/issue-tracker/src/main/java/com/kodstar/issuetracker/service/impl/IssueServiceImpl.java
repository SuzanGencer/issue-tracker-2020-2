package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.CommentDTO;
import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Comment;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.CommentService;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.impl.FromCommentDTOToComment;
import com.kodstar.issuetracker.utils.impl.FromIssueToIssueDTO;
import com.kodstar.issuetracker.utils.impl.FromIssueDTOToIssue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final FromIssueToIssueDTO fromIssueToIssueDTO;
    private final FromIssueDTOToIssue fromIssueDTOToIssue;
    private final CommentService commentService;
    private final FromCommentDTOToComment fromCommentDTOtoComment;


    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, FromIssueToIssueDTO fromIssueToIssueDTO, FromIssueDTOToIssue fromIssueDTOToIssue, CommentService commentService, FromCommentDTOToComment fromCommentDTOtoComment) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.fromIssueToIssueDTO = fromIssueToIssueDTO;
        this.fromIssueDTOToIssue = fromIssueDTOToIssue;
        this.commentService = commentService;
        this.fromCommentDTOtoComment = fromCommentDTOtoComment;
    }

    @Override
    public IssueDTO createIssue(IssueDTO idt) {
        Issue issue = fromIssueDTOToIssue.convert(idt);
        IssueDTO issueDto = fromIssueToIssueDTO.convert(issueRepository.save(issue));
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
    @Transactional
    public IssueDTO addComment(Long issueId, CommentDTO commentDTO) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(NoSuchElementException::new);
        Comment addedComment = commentService.createComment(fromCommentDTOtoComment.convert(commentDTO));
        issue.getComments().add(addedComment);
        return fromIssueToIssueDTO.convert(issueRepository.save(issue));
    }

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
