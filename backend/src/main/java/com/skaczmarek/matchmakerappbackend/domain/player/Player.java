package com.skaczmarek.matchmakerappbackend.domain.player;


import com.skaczmarek.matchmakerappbackend.domain.room.Room;
import com.skaczmarek.matchmakerappbackend.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String gameRank;

    private long level;

    @ManyToMany(mappedBy = "playersList")
    private List<Room> rooms;

    public Player() {
    }

    public Player(User user, String gameRank, long level) {
        this.user = user;
        this.gameRank = gameRank;
        this.level = level;
    }

    public Player(User user, PlayerDTO playerDTO) {
        this.user = user;
        this.gameRank = playerDTO.getGameRank();
        this.level = playerDTO.getLevel();
    }

    public Player(PlayerDTO playerDTO, long playerId, User user){
        this.id = playerId;
        this.user = user;
        this.gameRank = playerDTO.getGameRank();
        this.level = playerDTO.getLevel();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
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
