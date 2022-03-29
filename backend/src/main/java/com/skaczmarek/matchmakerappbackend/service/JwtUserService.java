package com.skaczmarek.matchmakerappbackend.service;

import java.util.ArrayList;
import java.util.List;

import com.skaczmarek.matchmakerappbackend.domain.social.UserSocial;
import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserDTO;
import com.skaczmarek.matchmakerappbackend.domain.user.UserRole;
import com.skaczmarek.matchmakerappbackend.domain.user.UserStatus;
import com.skaczmarek.matchmakerappbackend.repository.UserRepository;
import com.skaczmarek.matchmakerappbackend.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public User save(UserDTO user) {
        User newUser = new User();
        UserSocial userSocial = new UserSocial(user);

        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setUserSocial(userSocial);
        newUser.setUserRole(user.getUserRole());
        newUser.setUserStatus(UserStatus.ACTIVE);

        return userRepository.save(newUser);
    }

    public long getUserId(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user.getUserId();
    }

    public UserRole getUserRole() throws UserNotFoundException {
        long userId = getUserIdFromToken();
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));
        return user.getUserRole();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public boolean deleteUser(long id) throws UserNotFoundException{
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
        return true;
    }

    public Boolean checkUsernameAvailability(String username) {
        User user = userRepository
                .findByUsername(username);
        return user == null;
    }

    private long getUserIdFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository
                .findByUsername(username)
                .getUserId();
    }

}