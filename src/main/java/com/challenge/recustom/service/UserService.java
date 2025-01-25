package com.challenge.recustom.service;

import com.challenge.recustom.model.Role;
import com.challenge.recustom.model.User;
import com.challenge.recustom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity getUser(String email) {
        String methodName = "getUser";
        log.info("Starting :::::: " + methodName);
        try {
            Optional<User> user = userRepository.findById(email);
            if (user.isEmpty()) {
                return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(user, HttpStatus.OK);
            }
        } catch (Exception ex) {
            log.error("Exception in " + methodName + " :: " + ex);
            return new ResponseEntity<>(email, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getUsers() {
        String methodName = "getUsers";
        log.info("Starting :::::: " + methodName);
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity(users, HttpStatus.OK);
            }
        } catch (Exception ex) {
            log.error("Exception in " + methodName + " :: " + ex);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> save(User user) {
        String methodName = "save";
        log.info("Starting :::::: " + methodName);
        ResponseEntity<User> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            boolean existsById = userRepository.existsById(user.getEmail());
            if (!existsById) {
                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(user, HttpStatus.ALREADY_REPORTED);
            }

        } catch (Exception ex) {
            log.error("Exception in " + methodName + " :: " + ex);
        }
        return response;
    }

    public ResponseEntity<User> update(User user) {
        String methodName = "update";
        log.info("Starting :::::: " + methodName);
        try {
            User updateUser = userRepository.findById(user.getEmail()).orElseThrow(() -> null);
            updateUser.setName(user.getName());
            updateUser.setRole(user.getRole());

            userRepository.save(updateUser);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception in " + methodName + " :: " + ex);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity delete(String email) {
        String methodName = "delete";
        log.info("Starting :::::: " + methodName);
        ResponseEntity<User> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            Optional<User> user = userRepository.findById(email);
            if (!user.isEmpty()) {
                userRepository.delete(user.get());
                return new ResponseEntity<>(email, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(email, HttpStatus.NO_CONTENT);
            }

        } catch (Exception ex) {
            log.error("Exception in " + methodName + " :: " + ex);
            return new ResponseEntity<>(email, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}