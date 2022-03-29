package com.skaczmarek.matchmakerappbackend.controller;
import com.skaczmarek.matchmakerappbackend.domain.player.PlayerDTO;
import com.skaczmarek.matchmakerappbackend.domain.room.CreateRoomDTO;
import com.skaczmarek.matchmakerappbackend.domain.room.Room;
import com.skaczmarek.matchmakerappbackend.domain.room.RoomStatus;
import com.skaczmarek.matchmakerappbackend.service.RoomService;
import com.skaczmarek.matchmakerappbackend.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping(value = "/room")
    public Room createRoom(@RequestBody CreateRoomDTO createRoomDTO) throws UserNotFoundException, GameNotFoundException {
        return roomService.createRoom(createRoomDTO);
    }


    @GetMapping(value = "/room/{roomId}")
    public Room getRoom(@PathVariable long roomId) throws RoomNotFoundException {
        return roomService.getRoom(roomId);
    }

    @GetMapping(value = "/room")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }


    @GetMapping(value = "/room/open")
    public List<Room> getAllOpenRooms(){
        return roomService.getAllRoomsWithStatus(RoomStatus.OPEN);
    }

    @GetMapping(value = "/room/closed")
    public List<Room> getAllClosedRooms(){
        return roomService.getAllRoomsWithStatus(RoomStatus.CLOSED);
    }

    @GetMapping(value = "/room/without")
    public List<Room> getAllRoomsWithoutUser(){
        return roomService.getAllRoomsWithoutUser();
    }

    @GetMapping(value = "/room/open/current")
    public List<Room> getAllOpenRoomsWithUser(){
        return roomService.getAllRoomsWithUserWithStatus(RoomStatus.OPEN);
    }

    @GetMapping(value = "/room/closed/current")
    public List<Room> getAllClosedRoomsWithUser(){
        return roomService.getAllRoomsWithUserWithStatus(RoomStatus.CLOSED);
    }

    @GetMapping(value = "/room/newest")
    public List<Room> getNewestRoomWithUser(){
        return roomService.getNewestRoomWithUser();
    }

    @PostMapping(value = "/room/{roomId}")
    public Room addPlayerToRoomUsingUserId(@PathVariable long roomId, @RequestBody PlayerDTO playerDTO) throws RoomNotFoundException, UserNotFoundException, RoomIsFullException, RoomIsClosedException {
        return roomService.addPlayerToTheRoomUsingUserId(roomId, playerDTO);
    }

    @DeleteMapping(value = "/room/{roomId}")
    public boolean deleteRoom (@PathVariable long roomId) throws RoomNotFoundException {
        return roomService.deleteRoom(roomId);
    }

    @GetMapping(value = "/room/{roomId}/removeCurrentUser")
    public Room removeUserFromRoom (@PathVariable long roomId) throws PlayerNotFoundException, RoomNotFoundException {
        return roomService.removeUserFromRoom(roomId);
    }


}
