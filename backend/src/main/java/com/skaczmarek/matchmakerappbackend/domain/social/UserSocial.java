package com.skaczmarek.matchmakerappbackend.domain.social;

import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserDTO;

import javax.persistence.*;

@Entity
@Table(name = "UserSocial")
public class UserSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSociaId;

    private String steamUsername;
    private String epicGamesUsername;
    private String originUsername;
    private String uplayUsername;

    public UserSocial() {
    }

    public UserSocial(String steamUsername, String epicGamesUsername, String originUsername, String uplayUsername) {
        this.steamUsername = steamUsername;
        this.epicGamesUsername = epicGamesUsername;
        this.originUsername = originUsername;
        this.uplayUsername = uplayUsername;
    }

    public UserSocial (UserDTO userDTO){
        this.steamUsername = userDTO
                .getUserSocial()
                .getSteamUsername();

        this.epicGamesUsername = userDTO
                .getUserSocial()
                .getEpicGamesUsername();

        this.originUsername = userDTO
                .getUserSocial()
                .getOriginUsername();

        this.uplayUsername = userDTO
                .getUserSocial()
                .getUplayUsername();
    }


    public long getUserSociaId() {
        return userSociaId;
    }

    public void setUserSociaId(long userSociaId) {
        this.userSociaId = userSociaId;
    }

    public String getSteamUsername() {
        return steamUsername;
    }

    public void setSteamUsername(String steamUsername) {
        this.steamUsername = steamUsername;
    }

    public String getEpicGamesUsername() {
        return epicGamesUsername;
    }

    public void setEpicGamesUsername(String epicGamesUsername) {
        this.epicGamesUsername = epicGamesUsername;
    }

    public String getOriginUsername() {
        return originUsername;
    }

    public void setOriginUsername(String originUsername) {
        this.originUsername = originUsername;
    }

    public String getUplayUsername() {
        return uplayUsername;
    }

    public void setUplayUsername(String uplayUsername) {
        this.uplayUsername = uplayUsername;
    }
}
