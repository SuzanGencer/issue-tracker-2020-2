package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.entity.Comment;
import com.kodstar.issuetracker.repo.CommentRepository;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;


    @Autowired
    public CommentServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
