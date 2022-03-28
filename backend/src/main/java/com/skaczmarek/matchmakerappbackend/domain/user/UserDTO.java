package com.skaczmarek.matchmakerappbackend.domain.user;

import com.skaczmarek.matchmakerappbackend.domain.social.UserSocialDTO;

public class UserDTO {
    private String username;
    private String password;
    private UserSocialDTO userSocial;
    private UserRole userRole;

    UserDTO(){
    }

    UserDTO(String username, String password, UserRole userRole){
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

//    UserDTO(String username, String password, UserSocialDTO userSocialDTO){
//        this.username = username;
//        this.password = password;
//        this.userSocial = userSocialDTO;
//    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSocialDTO getUserSocial() {
        return userSocial;
    }

    public void setUserSocial(UserSocialDTO userSocial) {
        this.userSocial = userSocial;
    }
}