package com.skaczmarek.matchmakerappbackend.domain.jwt;

import com.skaczmarek.matchmakerappbackend.domain.user.UserRole;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final long userId;
    private final UserRole userRole;


    public JwtResponse(String jwttoken, long userId, UserRole userRole) {
        this.jwttoken = jwttoken;
        this.userId = userId;
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }
    public long getUserId() {
        return userId;
    }
    public String getToken() {
        return this.jwttoken;
    }


}