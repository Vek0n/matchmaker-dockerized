package com.skaczmarek.matchmakerappbackend.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBuilder {
    private long id;
    private String gameName;
    private List<String> availableRanks;
    private List<String> gameTypes;

    public GameBuilder() {
    }

    public GameBuilder defaultGame(){
        List<String> ranks = new ArrayList<>();
        Collections.addAll(ranks, "Silver 1","Silver 2","Silver 3","Silver 4","Silver Elite", "Silver Elite Master" );
        List<String> gameTypes = new ArrayList<>();
        Collections.addAll(gameTypes,"Competitive", "Casual", "Gun-Game");
        this.id = 1;
        this.gameName = "Counter Strike: Global Offensive";
        this.availableRanks = ranks;
        this.gameTypes = gameTypes;
        return this;
    }


    public Game build(){
        return new Game(id, gameName, availableRanks, gameTypes);
    }
}
