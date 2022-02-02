package com.project.agent.service;

import com.project.agent.domain.Agent;
import com.project.agent.domain.Authority;
import com.project.agent.domain.User;
import com.project.agent.domain.UserAuthority;
import com.project.agent.dto.UserDTO;
import com.project.agent.exceptions.InvalidDataException;
import com.project.agent.repository.AuthorityRepository;
import com.project.agent.repository.UserAuthorityRepository;
import com.project.agent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    public void registerUser(UserDTO userDTO, String typeOFUser) throws InvalidDataException {

        User user;

        if(typeOFUser.equalsIgnoreCase("AGENT_ROLE")) {
            user = new Agent(userDTO);
        }else {
            throw new InvalidDataException("Invalid user role");
        }

        Optional<User> oUser = userRepository.findByUsername(userDTO.getUsername());

        if(oUser.isPresent()) {
            throw new InvalidDataException("Username already exists");
        }

        UserAuthority authority = new UserAuthority();

        Optional<Authority> oauth = authorityRepository.findByName(typeOFUser);

        if(oauth.isEmpty()) {
            throw new InvalidDataException("Given authority does not exists");
        }

        Authority auth = oauth.get();

        auth.getUserAuthorities().add(authority);
        authority.setAuthority(auth);
        authority.setUser(user);
        System.out.println(authority.getAuthority().getName());
        user.getUserAuthorities().add(authority);
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        user.setPassword(enc.encode(user.getPassword()));
        userRepository.save(user);
        userAuthorityRepository.save(authority);
    }

}
