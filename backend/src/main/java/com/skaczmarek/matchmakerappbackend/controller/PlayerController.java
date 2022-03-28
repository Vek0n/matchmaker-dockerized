package com.skaczmarek.matchmakerappbackend.controller;

import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTO;
import com.skaczmarek.matchmakerappbackend.service.PlayerService;
import com.skaczmarek.matchmakerappbackend.service.exceptions.PlayerNotFoundException;
import com.skaczmarek.matchmakerappbackend.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping(value = "/players/{userId}")
    public Player createPlayer(@PathVariable long userId, @RequestBody PlayerDTO playerDTO) throws UserNotFoundException {
        return playerService.createPlayer(userId, playerDTO);
    }

    @GetMapping(value = "/players")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }


    @GetMapping(value = "/players/{playerId}")
    public Player getPlayer(@PathVariable long playerId) throws PlayerNotFoundException {
        return playerService.getPlayer(playerId);
    }

    @DeleteMapping(value = "/players/{playerId}")
    public boolean deletePlayer(@PathVariable long playerId) throws PlayerNotFoundException{
        return playerService.deletePlayer(playerId);
    }

    @PutMapping(value = "/players/{playerId}")
    public Player updatePlayer(@RequestBody PlayerDTO playerDTO, @PathVariable long playerId) throws PlayerNotFoundException{
        return playerService.updatePlayer(playerDTO, playerId);
    }

    @GetMapping(value = "/players/user/{userId}")
    public List<Player> getAllPlayerInstancesOfUser(@PathVariable long userId) throws PlayerNotFoundException {
        return playerService.getAllPlayerInstancesOfUser(userId);
    }

    @DeleteMapping(value = "/players/user/{userId}")
    public boolean deleteAllPlayerInstancesOfUser(@PathVariable long userId) throws PlayerNotFoundException {
        return playerService.deleteAllPlayerInstancesOfUser(userId);
    }



}
