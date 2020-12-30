package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.CommentDTO;
import com.kodstar.issuetracker.entity.Comment;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromCommentToCommentDTO implements Converter<Comment,CommentDTO> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDTO convert(Comment comment) {
        CommentDTO commentDTO=modelMapper.map(comment,CommentDTO.class);
        return commentDTO;
    }


}
