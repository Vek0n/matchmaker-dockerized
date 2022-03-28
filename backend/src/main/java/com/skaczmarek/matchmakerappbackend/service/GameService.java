package com.skaczmarek.matchmakerappbackend.service;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import com.skaczmarek.matchmakerappbackend.domain.game.GameDTO;
import com.skaczmarek.matchmakerappbackend.repository.GameRepository;
import com.skaczmarek.matchmakerappbackend.service.exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Game addGame(GameDTO gameDTO){
        return gameRepository.save(new Game(gameDTO));
    }

    public Game getGame(long id) throws GameNotFoundException {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    public Game updateGame(GameDTO gameDTO, long id) {
        return gameRepository.save(new Game(id, gameDTO));
    }

    public boolean deleteGame(long id) throws GameNotFoundException{
        Game game = gameRepository
                .findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        gameRepository.delete(game);
        return true;
    }
}
