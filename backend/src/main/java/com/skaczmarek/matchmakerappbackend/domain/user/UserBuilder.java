package com.skaczmarek.matchmakerappbackend.domain.user;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;

import java.util.ArrayList;

public class UserBuilder {
    private long id;
    private String username;
    private String password;
//    private ArrayList<Game> gameList;

    public UserBuilder() {
    }

    public UserBuilder defaultUser(){
        this.id = 1;
        this.username = "Grzmot";
        this.password = "pass";
//        this.gameList = new ArrayList<>();
        return this;
    }

    public UserBuilder withGames(ArrayList<Game> games){
//        this.gameList = games;
        return this;
    }

    public User build(){
        return new User(id,username, password);
    }

}
