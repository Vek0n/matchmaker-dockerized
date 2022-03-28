package com.skaczmarek.matchmakerappbackend.domain.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import com.skaczmarek.matchmakerappbackend.domain.social.UserSocial;
import com.skaczmarek.matchmakerappbackend.domain.social.UserSocialDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private UserRole userRole;

    @Column
    private UserStatus userStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userSociaId")
    private UserSocial userSocial;


    public User() {
    }

    User(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    User(String username, String password, UserSocial userSocial) {
        this.username = username;
        this.password = password;
        this.userSocial = userSocial;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public UserSocial getUserSocial() {
        return userSocial;
    }

    public void setUserSocial(UserSocial userSocial) {
        this.userSocial = userSocial;
    }
}