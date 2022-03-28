package com.skaczmarek.matchmakerappbackend.domain.player;


public class PlayerDTOBuilder {

    private long id;
    private String gameRank;
    private long level;


    public PlayerDTOBuilder defaultPlayer(){
        this.id = 1;
        this.gameRank = "Silver 1";
        this.level = 13;
        return this;
    }

    public PlayerDTO build(){
        return new PlayerDTO(gameRank, level);
    }
}
