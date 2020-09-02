package xml.team7.voziloservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.dto.UserDTO;
import xml.team7.voziloservice.model.User;
import xml.team7.voziloservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> add(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> edit(UserDTO userDTO) {
        User user = this.userRepository.getOne(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAddress(userDTO.getAddress());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public User getUserById(Long userId) {
    User byId = userRepository.findById(userId).get();
        return byId;
    }
}
