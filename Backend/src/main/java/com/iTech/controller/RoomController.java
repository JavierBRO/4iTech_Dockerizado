package com.iTech.controller;

import com.iTech.exception.ConflictDeleteException;
import com.iTech.models.Room;
import com.iTech.models.User;
import com.iTech.models.UserRole;
import com.iTech.repository.CommentRepository;
import com.iTech.repository.KeynoteRepository;
import com.iTech.repository.RoomRepository;
import com.iTech.security.SecurityUtils;
import com.iTech.services.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
public class RoomController {
    List<Room> rooms;

    private  CommentRepository commentRepository;
    private final KeynoteRepository keynoteRepository;
    private final RoomRepository roomRepository;
    private FileService fileService;


    @GetMapping("rooms")
    public ResponseEntity<List<Room>> findAll(){
        return ResponseEntity.ok(roomRepository.findAll());
    }
    @GetMapping("rooms/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent())
            return ResponseEntity.ok(roomOptional.get());
        else
            return ResponseEntity.notFound().build();
    }
    /*
    // OPCION 1
    @PostMapping("rooms")
    public ResponseEntity<Room> create(@RequestBody Room room){
        return ResponseEntity.ok(roomRepository.save(room));
    }
    @PutMapping("rooms/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody Room room){
        if(!roomRepository.existsById(id)) return ResponseEntity.notFound().build();
        Optional<Room> roomOptional = roomRepository.findById(id);
        if(roomOptional.isPresent()){
            Room roomFromDB = roomOptional.get();
            roomFromDB.setCapacity(room.getCapacity()); // este método solo permite modificar el aforo de la sala.
            roomRepository.save(roomFromDB);
            return ResponseEntity.ok(roomFromDB);
        } else
            return ResponseEntity.notFound().build();
    }
    */


     // OPCION 2, TENIENDO EN CUENTA MULTIPARTFILE DE ROOM

    @PostMapping("rooms")
    public Room create(
            @RequestParam(value = "photo", required = false) MultipartFile file, Room room) {

        if (file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            room.setPhotoUrl(fileName);
        } else {
            room.setPhotoUrl("cafeteria.jpeg");
        }

        return this.roomRepository.save(room);
    }


    @PutMapping("rooms/{id}")
    public ResponseEntity<Room> update(@RequestParam(value = "photo", required = false) MultipartFile file,
                                          Room room,
                                          @PathVariable Long id) {

        if (!this.roomRepository.existsById(id))
            return ResponseEntity.notFound().build();

        if (file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            room.setPhotoUrl(fileName);
        }

        return ResponseEntity.ok(this.roomRepository.save(room));
    }

@DeleteMapping("rooms/{id}")
public void deleteById(@PathVariable Long id) {


           // Opción 1 : borrar el room, pero antes desasociar o borrar aquellos objetos que apunten room
    //     Room room = this.roomRepository.findById(id).orElseThrow();
    //     User user = SecurityUtils.getCurrentUser().orElseThrow();

    // if (user.getUserRole().equals(UserRole.ADMIN)
    // )
    //     try {
    //         this.commentRepository.deleteByKeynoteId(id);
    //         this.keynoteRepository.deleteByRoomId(id);
    //         this.roomRepository.deleteById(id);
    //     } catch (Exception e) {
    //         log.error("Error borrando room", e);
    //         throw new ConflictDeleteException("No es posible borrar la sala.");
    //     }
    // }
             // Opción 2 : mediante un booleano visible, pasarlo a false y guardar
             
 
    Room room = this.roomRepository.findById(id).orElseThrow();
    User user = SecurityUtils.getCurrentUser().orElseThrow();
        if (user.getUserRole().equals(UserRole.ADMIN)) {
            room.setVisible(false);
            roomRepository.save(room);
        }
        
    }


}
