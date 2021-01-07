package com.kodstar.issuetracker.service;


import com.kodstar.issuetracker.dto.LabelDTO;
import com.kodstar.issuetracker.dto.UserDTO;

import java.util.Set;

public interface UserService {

    Set<UserDTO> getAllUsers();

    UserDTO getUserById(Long userId);
}
