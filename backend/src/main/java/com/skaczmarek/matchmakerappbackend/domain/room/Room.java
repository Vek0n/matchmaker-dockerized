package com.skaczmarek.matchmakerappbackend.domain.room;
import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private RoomStatus roomStatus;

    private int maxPlayers;

    private String gameType;

    private long creationDate;

    @ManyToMany
    @JoinTable(
            name="players_in_room",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> playersList;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;



    public Room() {
    }

    public Room(long id, RoomDTO roomDTO) {
        this.id = id;
        this.playersList = roomDTO.getPlayersList();
        this.game = roomDTO.getGame();
        this.maxPlayers = roomDTO.getMaxPlayers();
    }

    public Room(RoomDTO roomDTO){
        this.playersList = roomDTO.getPlayersList();
        this.game = roomDTO.getGame();
        this.maxPlayers = roomDTO.getMaxPlayers();
    }

    public Room(List<Player> playerList, Game game, int maxPlayers, String gameType, RoomStatus roomStatus) {
        this.playersList = playerList;
        this.game = game;
        this.maxPlayers = maxPlayers;
        this.gameType = gameType;
        this.roomStatus = roomStatus;
        Instant instant = Instant.now();
        this.creationDate = instant.toEpochMilli();

    }


    public Room(Room room, List<Player> playersList){
        this.id = room.getId();
        this.roomStatus = room.roomStatus;
        this.game = room.getGame();
        this.maxPlayers = room.getMaxPlayers();
        this.gameType = room.getGameType();
        this.playersList = playersList;
        this.creationDate = room.getCreationDate();
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

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public long getCreationDate() {
        return creationDate;
    }

}
