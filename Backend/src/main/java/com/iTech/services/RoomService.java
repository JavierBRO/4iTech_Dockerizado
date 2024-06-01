package com.iTech.services;

import com.iTech.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    Room createRoom(Room room);
    Room updateRoomById(Long id, Room room);
    void deleteRoomById(Long id);
    List<Room> findRoomVisibleTrue();
}
