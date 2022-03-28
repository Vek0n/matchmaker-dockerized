package com.skaczmarek.matchmakerappbackend.domain.room;

import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTO;

public class CreateRoomDTO {
    private long userId;
    private long gameId;
    private int maxPlayers;
    private String gameType;
    private PlayerDTO player;

    public CreateRoomDTO() {
    }

    public CreateRoomDTO(long userId, long gameId, int maxPlayers, String gameType, PlayerDTO player) {
        this.userId = userId;
        this.gameId = gameId;
        this.maxPlayers = maxPlayers;
        this.gameType = gameType;
        this.player = player;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }
}
