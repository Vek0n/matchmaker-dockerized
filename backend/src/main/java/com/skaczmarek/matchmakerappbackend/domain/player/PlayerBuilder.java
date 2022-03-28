package com.skaczmarek.matchmakerappbackend.domain.player;

import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserBuilder;

public class PlayerBuilder {


    private long id;
    private User user;
    private String gameRank;
    private long level;

    public PlayerBuilder() {
    }

    public PlayerBuilder defaultPlayer(){
        UserBuilder userBuilder = new UserBuilder();
        this.id = 1;
        this.user = userBuilder.defaultUser().build();
        this.gameRank = "Silver 1";
        this.level = 13;
        return this;
    }

    public Player build(){
        return new Player(user, gameRank, level);
    }
}
