package com.skaczmarek.matchmakerappbackend.service;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import com.skaczmarek.matchmakerappbackend.domain.game.GameBuilder;
import com.skaczmarek.matchmakerappbackend.domain.game.GameDTO;
import com.skaczmarek.matchmakerappbackend.domain.game.GameDTOBuilder;
import com.skaczmarek.matchmakerappbackend.repository.GameRepository;
import com.skaczmarek.matchmakerappbackend.service.exceptions.GameNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    GameRepository gameRepositoryMock;

    @InjectMocks
    GameService gameServiceMock;


    @Test
    void shouldReturnAllGames(){
        List<Game> givenGames = Collections.singletonList(new GameBuilder().defaultGame().build());
        given(gameRepositoryMock.findAll()).willReturn(givenGames);

        List<Game> result = gameServiceMock.getAllGames();

        assertEquals(result,givenGames);
    }

    @Test
    void shouldGetGame() throws GameNotFoundException {
        Game givenGame = new GameBuilder()
                .defaultGame()
                .build();
        given(gameRepositoryMock.findById(givenGame.getId())).willReturn(Optional.of(givenGame));

        Game result = gameServiceMock.getGame(givenGame.getId());

        assertEquals(result, givenGame);
    }

    @Test
    void shouldAddGame(){
        Game givenGame = new GameBuilder()
                .defaultGame()
                .build();
        GameDTO givenGameDTO = new GameDTOBuilder()
                .defaultGame()
                .build();
        given(gameRepositoryMock.save(any(Game.class))).willReturn(givenGame);

        Game result = gameServiceMock.addGame(givenGameDTO);

        assertEquals(result,givenGame);
    }

    @Test
    void shouldReturnGameNotFoundExceptionWhenGettingAGame(){
        Game givenGame = new GameBuilder()
                .defaultGame()
                .build();

        given(gameRepositoryMock.findById(givenGame.getId())).willReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> {
            gameServiceMock.getGame(givenGame.getId());
        });
    }

    @Test
    void shouldUpdateGame(){
        GameDTO gameToUpdate = new GameDTOBuilder()
                .defaultGame()
                .withGameName("CS")
                .build();

        Game givenGame = new GameBuilder()
                .defaultGame()
                .build();

        given(gameRepositoryMock.save(any(Game.class))).willReturn(givenGame);

        Game result = gameServiceMock.updateGame(gameToUpdate, givenGame.getId());

        assertEquals(result,givenGame);
    }


    @Test
    void shouldDeleteGame() throws GameNotFoundException {
        Game givenGame = new GameBuilder()
                .defaultGame()
                .build();

        given(gameRepositoryMock.findById(givenGame.getId())).willReturn(Optional.of(givenGame));

        boolean result = gameServiceMock.deleteGame(givenGame.getId());

        assertTrue(result);
    }
}
