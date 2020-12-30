package com.kodstar.issuetracker.utils.impl;

import com.kodstar.issuetracker.dto.CommentDTO;
import com.kodstar.issuetracker.entity.Comment;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromCommentDTOToComment implements Converter<CommentDTO, Comment> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Comment convert(CommentDTO commentDTO) {
        Comment comment=modelMapper.map(commentDTO,Comment.class);
        return comment;
    }
}
