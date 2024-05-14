package com.iTech.services;

import com.iTech.models.Room;
import com.iTech.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    @Override
    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return this.roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room createRoom(Room room) {
        this.roomRepository.save(room);
        return room;
    }

    @Override
    public Room updateRoomById(Long id, Room room) {
        return null;
    }

    @Override
    public void deleteRoomById(Long id) {
    }
}
