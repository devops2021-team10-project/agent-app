package com.project.agent.service;

import com.project.agent.dto.UserDTO;
import com.project.agent.exceptions.InvalidDataException;

public interface UserService {

    void registerUser(UserDTO userDTO, String typeOFUser) throws InvalidDataException;
}
