package com.skaczmarek.matchmakerappbackend.domain.social;

public class UserSocialDTO {
    private String steamUsername;
    private String epicGamesUsername;
    private String originUsername;
    private String uplayUsername;


    public UserSocialDTO(String steamUsername, String epicGamesUsername, String originUsername, String uplayUsername) {
        this.steamUsername = steamUsername;
        this.epicGamesUsername = epicGamesUsername;
        this.originUsername = originUsername;
        this.uplayUsername = uplayUsername;
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
