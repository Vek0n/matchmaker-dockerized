package com.skaczmarek.matchmakerappbackend.service;


import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserBuilder;
import com.skaczmarek.matchmakerappbackend.domain.user.UserDTO;
import com.skaczmarek.matchmakerappbackend.domain.user.UserDTOBuilder;
import com.skaczmarek.matchmakerappbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
class JwtUserServiceTest {

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder bcryptEncoderMock;

    @InjectMocks
    JwtUserService jwtUserServiceMock;


//    @Test
//    void shouldSaveUser(){
//        //Given
//        User givenUser = new UserBuilder().defaultUser().build();
//        UserDTO givenUserDTO = new UserDTOBuilder().defaultUser().build();
//        given(userRepositoryMock.save(any(User.class))).willReturn(givenUser);
//        given(bcryptEncoderMock.encode(any(CharSequence.class))).willReturn("testPassword");
//        //When
//        User result = jwtUserServiceMock.save(givenUserDTO);
//        //Then
//        assertEquals(result, givenUser);
//    }


    @Test
    void shouldLoadUserByUsername(){
        //Given
        User givenUser = new UserBuilder().defaultUser().build();
        given(userRepositoryMock.findByUsername(any(String.class))).willReturn(givenUser);
        //When
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(givenUser.getUsername(), givenUser.getPassword(),
                new ArrayList<>());
        UserDetails result = jwtUserServiceMock.loadUserByUsername(givenUser.getUsername());
        //Then
        assertEquals(result, userDetails);
    }


}
