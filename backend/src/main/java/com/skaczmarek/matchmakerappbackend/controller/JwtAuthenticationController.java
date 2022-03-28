package com.skaczmarek.matchmakerappbackend.controller;

import com.skaczmarek.matchmakerappbackend.config.JwtTokenUtil;
import com.skaczmarek.matchmakerappbackend.domain.jwt.JwtRequest;
import com.skaczmarek.matchmakerappbackend.domain.jwt.JwtResponse;
import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserDTO;
import com.skaczmarek.matchmakerappbackend.domain.user.UserRole;
import com.skaczmarek.matchmakerappbackend.service.JwtUserService;
import com.skaczmarek.matchmakerappbackend.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        long userId = userDetailsService.getUserId(
                    userDetails.getUsername());

        UserRole userRole = userDetailsService.getUserRole(userId);

        return ResponseEntity.ok(new JwtResponse(token,userId, userRole));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        return userDetailsService.getAllUsers();
    }

    @GetMapping(value = "/users/{userId}")
    public User getUser(@PathVariable long userId) throws UserNotFoundException {
        return userDetailsService.getUser(userId);
    }

    @GetMapping(value = "/users/role/{userId}")
    public UserRole getUserRole(@PathVariable long userId) throws UserNotFoundException {
        return userDetailsService.getUserRole(userId);
    }

    @PostMapping(value = "/users/check/{username}")
    public ResponseEntity<?> checkUsernameAvailability (@PathVariable String username) {
        Boolean isUsernameAvailable = userDetailsService.checkUsernameAvailability(username);
//        return new ResponseEntity<>(isUsernameAvailable, HttpStatus.OK);
        return ResponseEntity.ok(isUsernameAvailable);
    }

//    @DeleteMapping(value = "/users/{userId}")
//    public boolean deleteUser(@PathVariable long userId) throws UserNotFoundException{
//        return userDetailsService.deleteUser(userId);
//    }
}