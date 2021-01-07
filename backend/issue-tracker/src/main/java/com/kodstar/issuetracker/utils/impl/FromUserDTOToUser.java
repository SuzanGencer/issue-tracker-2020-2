package com.kodstar.issuetracker.utils.impl;


import com.kodstar.issuetracker.auth.User;
import com.kodstar.issuetracker.dto.UserDTO;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromUserDTOToUser implements Converter<UserDTO, User> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User convert(UserDTO userDTO) {
        User user=modelMapper.map(userDTO, User.class);
        return user;
    }
}

