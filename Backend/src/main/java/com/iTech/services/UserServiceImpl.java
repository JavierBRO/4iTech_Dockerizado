package com.iTech.services;

import com.iTech.models.User;
import com.iTech.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User create(@RequestBody() User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUserById(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User userFromDatabase = userOptional.get();
            userFromDatabase.setFirstName(user.getFirstName());
            userFromDatabase.setLastName(user.getLastName());
            userFromDatabase.setEmail(user.getEmail());
            userFromDatabase.setPhone(user.getPhone());
            userFromDatabase.setUserName(user.getUserName());
            userFromDatabase.setPassword(user.getPassword());
            userFromDatabase.setAddress(user.getAddress());
            userFromDatabase.setUserRole(user.getUserRole());
            userFromDatabase.setPhotoUrl(user.getPhotoUrl());

            return userRepository.save(userFromDatabase);
        } else
            throw new NoSuchElementException("Usuario no encontrado en BD.");
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            userRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}
