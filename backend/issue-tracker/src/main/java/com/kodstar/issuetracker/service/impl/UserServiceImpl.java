package com.kodstar.issuetracker.service.impl;

import com.kodstar.issuetracker.auth.User;
import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.dto.UserDTO;

import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.repo.UserRepository;
import com.kodstar.issuetracker.service.UserService;
import com.kodstar.issuetracker.utils.impl.FromUserDTOToUser;
import com.kodstar.issuetracker.utils.impl.FromUserToUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final FromUserDTOToUser fromUserDTOToUserDTO;
    private final FromUserToUserDTO fromUserToUserDTO;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, FromUserDTOToUser fromUserDTOToUserDTO, FromUserToUserDTO fromUserToUserDTO) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.fromUserDTOToUserDTO = fromUserDTOToUserDTO;
        this.fromUserToUserDTO = fromUserToUserDTO;
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        Set<UserDTO> userDTOSet = new HashSet<>();
        for (UserDTO userDTO :
                fromUserToUserDTO.convertAll((List<User>) userRepository.findAll())) {
            userDTOSet.add(userDTO);
        }
        return userDTOSet;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        UserDTO userDTO = fromUserToUserDTO.convert(user);

        return userDTO;
    }

}
