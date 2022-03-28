package com.skaczmarek.matchmakerappbackend.service;


import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerBuilder;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTO;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTOBuilder;
import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.domain.user.UserBuilder;
import com.skaczmarek.matchmakerappbackend.repository.PlayerRepository;
import com.skaczmarek.matchmakerappbackend.repository.UserRepository;
import com.skaczmarek.matchmakerappbackend.service.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    PlayerService playerServiceMock;

    @Mock
    PlayerRepository playerRepositoryMock;

    @Mock
    UserRepository userRepositoryMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnAllPlayers(){
        List<Player> givenPlayerList = Collections.singletonList(new PlayerBuilder().defaultPlayer().build());
        given(playerRepositoryMock.findAll()).willReturn(givenPlayerList);

        List<Player> result = playerServiceMock.getAllPlayers();

        assertEquals(result, givenPlayerList);
    }

    @Test
    public void shouldCreatePlayer() throws UserNotFoundException {
        //Given
        Player givenPlayer = new PlayerBuilder()
                .defaultPlayer()
                .build();

        PlayerDTO givenPlayerDTO = new PlayerDTOBuilder()
                .defaultPlayer()
                .build();

        User givenUser = new UserBuilder()
                .defaultUser()
                .build();

        given(userRepositoryMock.findById(givenUser.getUserId())).willReturn(Optional.of(givenUser));
        given(playerRepositoryMock.save(any(Player.class))).willReturn(givenPlayer);

        //When
        Player result = playerServiceMock.createPlayer(givenUser.getUserId(), givenPlayerDTO);

        //Then
        assertEquals(result, givenPlayer);

    }

}
