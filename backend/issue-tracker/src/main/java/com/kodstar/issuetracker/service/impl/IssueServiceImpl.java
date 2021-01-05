package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.dto.CommentDTO;
import com.kodstar.issuetracker.dto.IssueDTO;
import com.kodstar.issuetracker.entity.Comment;
import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.State;
import com.kodstar.issuetracker.exceptionhandler.InvalidQueryParameterException;
import com.kodstar.issuetracker.exceptionhandler.IssueTrackerNotFoundException;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.repo.StateRepository;
import com.kodstar.issuetracker.service.CommentService;
import com.kodstar.issuetracker.service.IssueService;
import com.kodstar.issuetracker.utils.impl.FromCommentDTOToComment;
import com.kodstar.issuetracker.utils.impl.FromIssueToIssueDTO;
import com.kodstar.issuetracker.utils.impl.FromIssueDTOToIssue;
import com.kodstar.issuetracker.utils.impl.FromLabelToLabelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class IssueServiceImpl implements IssueService {


    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final FromIssueToIssueDTO fromIssueToIssueDTO;
    private final FromIssueDTOToIssue fromIssueDTOToIssue;
    private final FromLabelToLabelDTO fromLabelToLabelDTO;
    private final CommentService commentService;
    private final FromCommentDTOToComment fromCommentDTOtoComment;

    private final StateRepository stateRepository;

    private final static String ASCENDING="asc" ;
    private final static String DESCENDING="desc" ;
    private final static String ORDER_TYPE_ERROR_MESSAGE=" Recieved OrderType is : %s .\nOrder Type must be asc or desc.";


    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper,
                            FromIssueToIssueDTO fromIssueToIssueDTO, FromIssueDTOToIssue fromIssueDTOToIssue,
                            FromLabelToLabelDTO fromLabelToLabelDTO, CommentService commentService,
                            FromCommentDTOToComment fromCommentDTOtoComment,StateRepository stateRepository) {

        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.fromIssueToIssueDTO = fromIssueToIssueDTO;
        this.fromIssueDTOToIssue = fromIssueDTOToIssue;
        this.fromLabelToLabelDTO = fromLabelToLabelDTO;
        this.commentService = commentService;
        this.fromCommentDTOtoComment = fromCommentDTOtoComment;
        this.stateRepository = stateRepository;
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
    public List<IssueDTO> findALlIssuesByLabel(String keyword) {
        List<IssueDTO> issueDTOList = fromIssueToIssueDTO.convertAll(issueRepository.findALlIssuesByLabel(keyword));
        return issueDTOList;
    }

    @Transactional
    @Override
    public IssueDTO addComment(Long issueId, CommentDTO commentDTO) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(NoSuchElementException::new);
        Comment addedComment = commentService.createComment(fromCommentDTOtoComment.convert(commentDTO));
        issue.getComments().add(addedComment);
        return fromIssueToIssueDTO.convert(issueRepository.save(issue));
    }

    @Override
    public void deleteComment(Long issueId,Long commentId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(()->new IssueTrackerNotFoundException("Issue",issueId.toString()));
        Optional<Comment> comment=issue.getComments().stream()
                .filter(x->x.getId()==commentId)
                .findFirst();
        if(comment.isPresent()){
            issue.getComments().remove(comment.get());
        }else{
            throw new IssueTrackerNotFoundException("Comment",commentId.toString());
        }
        issueRepository.save(issue);
    }

    @Override
    public List<IssueDTO> getAllIssuesOrderByCreateTime(boolean isAscending) {
        if (isAscending) {
            return fromIssueToIssueDTO.convertAll(issueRepository.findAllByOrderByCreateTime());
        } else {
            return fromIssueToIssueDTO.convertAll(issueRepository.findAllByOrderByCreateTimeDesc());
        }

    }

    @Override
    public IssueDTO updateState(Long issueId, Long stateId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(()->new IssueTrackerNotFoundException("Issue",issueId.toString()));
        State state = stateRepository.findById(stateId)
                .orElseThrow(()->new IssueTrackerNotFoundException("State",stateId.toString()));
        issue.setState(state);
        return fromIssueToIssueDTO.convert(issueRepository.save(issue));

    public List<IssueDTO> getAllIssuesOrderByUpdateTime(boolean isAscending) {
        if (isAscending) {
            return fromIssueToIssueDTO.convertAll(issueRepository.findAllByOrderByUpdateTime());
        } else {
            return fromIssueToIssueDTO.convertAll(issueRepository.findAllByOrderByUpdateTimeDesc());
        }

    }
    public List<IssueDTO> getAllIssuesSort( String orderType, String byWhichSort) {
       if (byWhichSort == null) {
            return getAllIssues();
        }

        if (byWhichSort.equalsIgnoreCase("createDate")) {
            if (orderType.equalsIgnoreCase(ASCENDING)) {
                return getAllIssuesOrderByCreateTime(true);
            } else if (orderType.equalsIgnoreCase(DESCENDING)) {
                return getAllIssuesOrderByCreateTime(false);
            } else {
                throw new InvalidQueryParameterException(String.format(ORDER_TYPE_ERROR_MESSAGE, orderType));
            }
        } else if (byWhichSort.equalsIgnoreCase("update")) {
            if (orderType.equalsIgnoreCase(ASCENDING)) {
                return getAllIssuesOrderByUpdateTime(true);

            } else if (orderType.equalsIgnoreCase(DESCENDING)) {
                return getAllIssuesOrderByUpdateTime(false);
            } else {
                throw new InvalidQueryParameterException(String.format(ORDER_TYPE_ERROR_MESSAGE, orderType));
            }

        } else {
            throw new InvalidQueryParameterException(String.format(ORDER_TYPE_ERROR_MESSAGE, orderType));
        }
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
