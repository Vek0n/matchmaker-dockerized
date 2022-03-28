package com.skaczmarek.matchmakerappbackend.domain.user;

public class UserDTOBuilder {
    private String username;
    private String password;
    private UserRole userRole;

    public UserDTOBuilder defaultUser(){
        this.username = "Grzmot";
        this.password = "pass";
        this.userRole = UserRole.USER;
        return this;
    }

    public UserDTO build(){
        return new UserDTO(this.username, this.password, this.userRole);
    }
}
