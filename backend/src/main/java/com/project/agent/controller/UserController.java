package com.project.agent.controller;

import com.project.agent.dto.UserDTO;
import com.project.agent.dto.LoginDTO;
import com.project.agent.exceptions.InvalidDataException;
import com.project.agent.security.TokenUtils;
import com.project.agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        try{
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(), loginDTO.getPassword());

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload user details so we can generate token
            UserDetails details = userDetailsService.
                    loadUserByUsername(loginDTO.getUsername());

            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        try {
            userService.registerUser(userDTO, "AGENT_ROLE");
        } catch (InvalidDataException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
