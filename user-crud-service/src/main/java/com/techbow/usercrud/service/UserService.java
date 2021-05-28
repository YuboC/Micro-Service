package com.techbow.usercrud.service;

import com.techbow.usercrud.model.User;
import com.techbow.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        User found = userRepository.findById(user.getId()).orElse(null);
        if (found == null) {
            return null;
        }

        found.setName(user.getName());
        found.setEmail(user.getEmail());
        userRepository.save(found);

        return found;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
