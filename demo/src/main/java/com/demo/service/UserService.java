package com.demo.service;

import com.demo.exception.NotFoundException;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String bax) {
        log.info("User service - get user by username");
        return this.userRepository.findByUsername(bax); // .orElseThrow(() -> new NotFoundException("User with given username was not found."));
    }

    public User getUserById(Long userId) {
        log.info("User service - get user by id");
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with given id was not found"));
    }

    /**
     * Returns user which send the request from token
     * @return User object
     */
    public User getUser() {
        log.info("Get user from username/auth");
        // get username from auth and send request to SecurityService to give you his id
        // or add username field to user class and find it directly here
        return getUserById(1L);
    }

    public User findByUsername(String userUsername) {
        User u = userRepository.findByUsername(userUsername);
        return u;
    }
}
