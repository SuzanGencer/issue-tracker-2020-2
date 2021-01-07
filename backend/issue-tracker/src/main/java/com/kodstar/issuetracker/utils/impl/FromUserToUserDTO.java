package com.kodstar.issuetracker.utils.impl;


import com.kodstar.issuetracker.auth.User;
import com.kodstar.issuetracker.dto.UserDTO;
import com.kodstar.issuetracker.utils.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromUserToUserDTO implements Converter<User, UserDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO=modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
