package xml.team7.porukaservice.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import xml.team7.porukaservice.dto.UserDTO;
import xml.team7.porukaservice.exception.NotFoundException;
import xml.team7.porukaservice.model.User;
import xml.team7.porukaservice.repository.UserRepository;


@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    public ResponseEntity<?> add(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> edit(UserDTO userDTO) {
        User user = this.userRepository.getOne(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
