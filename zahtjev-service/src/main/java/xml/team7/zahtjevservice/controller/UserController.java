package xml.team7.zahtjevservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.zahtjevservice.dto.UserDTO;
import xml.team7.zahtjevservice.service.UserService;


@RestController
@CrossOrigin
@Slf4j
@RequestMapping(produces = "application/json")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody UserDTO userDTO) {
        return this.userService.add(userDTO);
    }

    @PutMapping()
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO) {
        return this.userService.edit(userDTO);
    }

}
