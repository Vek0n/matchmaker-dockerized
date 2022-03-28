package com.skaczmarek.matchmakerappbackend.service;

import com.skaczmarek.matchmakerappbackend.domain.game.Game;
import com.skaczmarek.matchmakerappbackend.domain.player.Player;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTO;
import com.skaczmarek.matchmakerappbackend.domain.room.CreateRoomDTO;
import com.skaczmarek.matchmakerappbackend.domain.room.Room;
import com.skaczmarek.matchmakerappbackend.domain.room.RoomStatus;
import com.skaczmarek.matchmakerappbackend.domain.user.User;
import com.skaczmarek.matchmakerappbackend.repository.GameRepository;
import com.skaczmarek.matchmakerappbackend.repository.PlayerRepository;
import com.skaczmarek.matchmakerappbackend.repository.RoomRepository;
import com.skaczmarek.matchmakerappbackend.repository.UserRepository;
import com.skaczmarek.matchmakerappbackend.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {


    private PlayerRepository playerRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private GameRepository gameRepository;

    @Autowired
    public RoomService(PlayerRepository playerRepository, UserRepository userRepository, RoomRepository roomRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.gameRepository = gameRepository;
    }


    public Room createRoom(CreateRoomDTO createRoomDTO) throws UserNotFoundException, GameNotFoundException {
        long userId = createRoomDTO.getUserId();
        long gameId = createRoomDTO.getGameId();
        int maxPlayers = createRoomDTO.getMaxPlayers();
        PlayerDTO playerDTO = createRoomDTO.getPlayer();
        String gameType = createRoomDTO.getGameType();

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Player player = playerRepository.save(new Player(user, playerDTO));

        Game game = gameRepository
                .findById(gameId)
                .orElseThrow(()-> new GameNotFoundException(gameId));

        Room room = new Room(Collections.singletonList(player),game, maxPlayers, gameType, RoomStatus.OPEN);

        return roomRepository.save(room);
    }


    public Room getRoom(long roomId) throws RoomNotFoundException {
        return roomRepository
                .findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }



    public List<Room>getAllRoomsWithUserWithStatus(long userId, RoomStatus status){
        List<Room> allRooms = roomRepository.findAll();

        return findRoomsWithUser(allRooms, userId)
                .stream()
                .filter(r -> r.getRoomStatus().equals(status))
                .collect(Collectors.toList());
    }


    public List<Room>getNewestRoomWithUser(long userId){
        List<Room> allRooms = roomRepository.findAll();
        List<Room> roomsWithPlayer = findRoomsWithUser(allRooms, userId);

        Room newestRoom;
        if (roomsWithPlayer.size() <= 1) return roomsWithPlayer;
        else{
            newestRoom = roomsWithPlayer.get(0);
            for (Room r : roomsWithPlayer){
                if(r.getCreationDate() > newestRoom.getCreationDate()){
                    newestRoom = r;
                }
            }
        return Collections.singletonList(newestRoom);
        }
    }


    private List<Room> findRoomsWithUser(List<Room> listOfRooms, long userId){
        boolean foundUser = false;
        List<Room> roomsWithPlayer = new LinkedList<>();
        for (Room r : listOfRooms) {
            for (Player p : r.getPlayersList()) {
                if (p.getUser().getUserId() == userId) {
                    foundUser = true;
                }
            }
            if (foundUser) {
                roomsWithPlayer.add(r);
            }
            foundUser = false;
        }
        return roomsWithPlayer;
    }


    public List<Room> getAllRoomsWithoutUser(long userId) {
        List<Room> allRooms = roomRepository.findAll();
        List<Room> roomsWithoutUser = new LinkedList<>();
        boolean foundUser = false;
        boolean isRoomOpen = false;
        for (Room r : allRooms){
            if(r.getRoomStatus().equals(RoomStatus.OPEN)){
                isRoomOpen = true;
            }
            for (Player p : r.getPlayersList()) {
                if (p.getUser().getUserId() == userId) {
                    foundUser = true;
                }
            }
            if (!foundUser && isRoomOpen) {
                roomsWithoutUser.add(r);
            }
            foundUser = false;
            isRoomOpen = false;
        }
        return roomsWithoutUser;
    }



    public Room addPlayerToTheRoomUsingUserId(long roomId, long userId, PlayerDTO playerDTO) throws RoomNotFoundException, UserNotFoundException, RoomIsFullException, RoomIsClosedException {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));

        if (room.getRoomStatus() == RoomStatus.CLOSED){
            throw new RoomIsClosedException(roomId);
        }
        else if (room.getMaxPlayers() == room.getPlayersList().size()){
            throw new RoomIsFullException(roomId);
        }

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Player player = playerRepository.save(new Player(user, playerDTO));

        List<Player> playerList = room.getPlayersList();
        playerList.add(player);

        if (playerList.size() == room.getMaxPlayers()){
            room.setRoomStatus(RoomStatus.CLOSED);
        }
        return roomRepository.save(new Room(room, playerList));
    }



    public boolean deleteRoom(long roomId) throws RoomNotFoundException {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));
        roomRepository.delete(room);
        return true;
    }



    public Room removeUserFromRoom(long roomId, long userId) throws RoomNotFoundException, PlayerNotFoundException {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(roomId));

        List<Player> playerList = room.getPlayersList();

        if (playerList.size() == 1){
            roomRepository.delete(room);
            return null;
        }

        playerList.removeIf(player -> player
                .getUser()
                .getUserId() == userId);

        return roomRepository.save(new Room(room, playerList));
    }


    public List<Room> getAllRoomsWithStatus(RoomStatus roomStatus) {
        List<Room> allRooms = roomRepository.findAll();
        return allRooms
                .stream()
                .filter(r -> r.getRoomStatus().equals(roomStatus))
                .collect(Collectors.toList());
    }
}
