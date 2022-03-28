package com.skaczmarek.matchmakerappbackend.domain.player;
import com.skaczmarek.matchmakerappbackend.domain.user.User;

public class PlayerDTO {

    private long id;
    private User user;
    private String gameRank;
    private long level;


    public PlayerDTO() {
    }

    public PlayerDTO(String gameRank, long level) {
        this.gameRank = gameRank;
        this.level = level;
    }

    public PlayerDTO(User user, String gameRank, long level) {
        this.user = user;
        this.gameRank = gameRank;
        this.level = level;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGameRank() {
        return gameRank;
    }

    public void setGameRank(String gameRank) {
        this.gameRank = gameRank;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
}
