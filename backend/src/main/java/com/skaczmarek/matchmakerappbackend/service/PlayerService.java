package com.skaczmarek.matchmakerappbackend.service;
import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTO;
import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.repository.PlayerRepository;
import com.skaczmarek.matchmakerappbackend.repository.UserRepository;
import com.skaczmarek.matchmakerappbackend.service.exceptions.PlayerNotFoundException;
import com.skaczmarek.matchmakerappbackend.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private UserRepository userRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, UserRepository userRepository) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    public Player createPlayer (long userId, PlayerDTO playerDTO) throws UserNotFoundException {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return playerRepository.save(new Player(user, playerDTO));
    }


    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }


    public Player getPlayer(long playerId) throws PlayerNotFoundException {
        return playerRepository
                .findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }


    public boolean deletePlayer(long playerId) throws PlayerNotFoundException {
        Player player = playerRepository
                .findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
        playerRepository.delete(player);
        return true;
    }


    public Player updatePlayer(PlayerDTO playerDTO, long playerId) throws PlayerNotFoundException {
        Player player = playerRepository
                .findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        User user = player.getUser();

        return playerRepository.save(new Player(playerDTO, playerId, user));
    }


    public List<Player> getAllPlayerInstancesOfUser(long userId) throws PlayerNotFoundException {
        List<Player> playerList = playerRepository.findPlayerByUser_UserId(userId);

        if (!playerList.isEmpty()) return playerList;
        else throw new PlayerNotFoundException();
    }


    public boolean deleteAllPlayerInstancesOfUser(long userId) throws PlayerNotFoundException{
        List<Player> playerList = playerRepository.findPlayerByUser_UserId(userId);

        if(!playerList.isEmpty()){
            for(Player player : playerList){
                playerRepository.delete(player);
            }
            return true;
        }else{
            throw new PlayerNotFoundException();
        }
    }

}
