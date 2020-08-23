package com.demo.controller;

import com.demo.dto.UserDTO;
import com.demo.security.JwtAuthenticationRequest;
import com.demo.service.CustomUserDetailsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(value = "*")
@RestController
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
}
