package com.skaczmarek.matchmakerappbackend.domain.room;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import com.skaczmarek.matchmakerappbackend.domain.player.Player;

import java.util.List;

public class RoomDTO {

    private long id;
    private List<Player> playersList;
    private Game game;
    private int maxPlayers;
    private String gameType;


    public RoomDTO(List<Player> playersList, Game game, int maxPlayers, String gameType) {
        this.playersList = playersList;
        this.game = game;
        this.maxPlayers = maxPlayers;
        this.gameType = gameType;
    }

    public RoomDTO(long id, List<Player> playersList, Game game, int maxPlayers) {
        this.id = id;
        this.playersList = playersList;
        this.game = game;
        this.maxPlayers = maxPlayers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
