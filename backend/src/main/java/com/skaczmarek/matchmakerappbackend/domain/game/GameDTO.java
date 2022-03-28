package com.skaczmarek.matchmakerappbackend.domain.game;

import java.util.List;

public class GameDTO {
    private long id;
    private String gameName;
    private List<String> availableRanks;
    private List<String> gameTypes;
    private String gameLogoImageLink;

    public GameDTO(String gameName, List<String> availableRanks, List<String> gameTypes, String gameLogoImageLink) {
        this.gameName = gameName;
        this.availableRanks = availableRanks;
        this.gameTypes = gameTypes;
        this.gameLogoImageLink = gameLogoImageLink;
    }

    public String getGameLogoImageLink() {
        return gameLogoImageLink;
    }

    public void setGameLogoImageLink(String gameLogoImageLink) {
        this.gameLogoImageLink = gameLogoImageLink;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<String> getAvailableRanks() {
        return availableRanks;
    }

    public void setAvailableRanks(List<String> availableRanks) {
        this.availableRanks = availableRanks;
    }

    public List<String> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(List<String> gameTypes) {
        this.gameTypes = gameTypes;
    }
}
