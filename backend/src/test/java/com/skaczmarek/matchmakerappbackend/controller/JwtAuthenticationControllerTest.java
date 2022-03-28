package com.skaczmarek.matchmakerappbackend.controller;

import com.skaczmarek.matchmakerappbackend.config.JwtTokenUtil;
import com.skaczmarek.matchmakerappbackend.domain.jwt.JwtResponse;
import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserBuilder;
import com.skaczmarek.matchmakerappbackend.domain.user.UserDTO;
import com.skaczmarek.matchmakerappbackend.service.JwtUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//
//@RunWith(SpringRunner.class)
//@WebMvcTest(JwtAuthenticationController.class)
////@ComponentScan(basePackages = {"com.skaczmarek.matchmakerappbackend"})
//
//class JwtAuthenticationControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private JwtUserService jwtUserServiceMock;
//
//    @MockBean
//    private JwtTokenUtil jwtTokenUtilMock;
//
//
////    @Test
//    void registerUser() throws Exception{
//        User givenUser = new UserBuilder()
//                .defaultUser()
//                .build();
//
//        given(jwtUserServiceMock.save(any(UserDTO.class))).willReturn(givenUser);
//
//        mvc.perform(post("/register")
//            .content(
//            "{\"username\":\"Grzmot\"," +
//            " \"password\":\"pass\"}")
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.username").value(givenUser.getUsername()));
//    }
//
//
////    @Test
//    void authenticate() throws Exception{
//        User givenUser = new UserBuilder().defaultUser().build();
//
//        given(jwtUserServiceMock.loadUserByUsername(any(String.class)))
//                .willReturn(new org.springframework.security.core.userdetails.User(givenUser.getUsername(), givenUser.getPassword(),
//                        new ArrayList<>()));
//
//        given(jwtTokenUtilMock.generateToken(any(UserDetails.class))).willReturn("testToken");
//
//        mvc.perform(post("/authenticate")
//            .content(
//            "{\"username\":\"Grzmot\"," +
//            " \"password\":\"pass\"}")
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
//
//    }
//}
