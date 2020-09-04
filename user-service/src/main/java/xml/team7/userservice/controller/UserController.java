package xml.team7.userservice.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.userservice.dto.UserDTO;
import xml.team7.userservice.security.JwtAuthenticationRequest;
import xml.team7.userservice.service.CustomUserDetailsService;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(value = "*")
@Slf4j
@RequestMapping(produces = "application/json")
public class UserController {
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public UserController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @SneakyThrows
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, HttpServletResponse httpServletResponse) {
        log.info("Controller /login reached by user " + jwtAuthenticationRequest.getUsername() + " for token verification.");
        return this.userDetailsService.login(jwtAuthenticationRequest, httpServletResponse);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        log.info("Controller /register reached by user: " + userDTO.getUsername());
        return this.userDetailsService.register(userDTO);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<?> verify(@RequestBody String token) {
        log.info("Controller /verify invoked with token - " + token);
        return this.userDetailsService.verify(token);
    }


    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        this.userDetailsService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> getUsers() {
        return this.userDetailsService.getUsers();
    }

    @GetMapping(value = "/requests")
    public ResponseEntity<?> getRegistrationRequests() {
        return this.userDetailsService.getRequests();
    }

    @GetMapping(value = "/enable/{userId}")
    public ResponseEntity<Void> enable(@PathVariable Long userId) {
        this.userDetailsService.enable(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/disable/{userId}")
    public ResponseEntity<Void> disable(@PathVariable Long userId) {
        this.userDetailsService.disable(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/accept/{id}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long id) {
        return this.userDetailsService.accept(id);
    }

    @PostMapping(value = "/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id) {
        return this.userDetailsService.reject(id);
    }


    @GetMapping(value = "/rentPrivilege/{privilege}/{id}")
    public ResponseEntity<?> rentPrivileges(@PathVariable Boolean privilege, @PathVariable Long id) {
        boolean flag =  this.userDetailsService.rentPrivilege(privilege, id);
        if(flag == true){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "")
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO) {
        log.info("Controller /edit reached by user: " + userDTO.getUsername());
        return this.userDetailsService.edit(userDTO);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        log.info("Auth controller - get all users");
        return this.userDetailsService.getUser(username);
    }
}
