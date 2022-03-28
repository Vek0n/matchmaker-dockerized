package com.skaczmarek.matchmakerappbackend.controller;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import com.skaczmarek.matchmakerappbackend.domain.game.GameDTO;
import com.skaczmarek.matchmakerappbackend.service.exceptions.GameNotFoundException;
import com.skaczmarek.matchmakerappbackend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan("com.skaczmarek.matchmakerappbackend")
@CrossOrigin(origins = "*")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/games")
    public List<Game> getAllCars(){
        return gameService.getAllGames();
    }

    @PostMapping(value = "/games")
    public Game addGame(@RequestBody GameDTO gameDTO){
        return gameService.addGame(gameDTO);
    }

    @GetMapping(value = "/games/{id}")
    public Game getGame(@PathVariable long id) throws GameNotFoundException {
        return gameService.getGame(id);
    }

    @PutMapping(value = "/games/{id}")
    public Game updateGame(@RequestBody GameDTO gameDTO, @PathVariable long id) throws GameNotFoundException{
        return gameService.updateGame(gameDTO, id);
    }

    @DeleteMapping(value = "/games/{id}")
    public boolean deleteGame(@PathVariable long id) throws GameNotFoundException{
        return gameService.deleteGame(id);
    }

}
