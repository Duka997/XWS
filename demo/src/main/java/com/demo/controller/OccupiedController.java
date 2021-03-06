package com.demo.controller;

import com.demo.dto.ZauzetDTO;
import com.demo.service.ZauzetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/occupied")
public class OccupiedController {

    @Autowired
    private ZauzetService zauzetService;

    @PostMapping
    public ResponseEntity<?> addNew(@RequestBody ZauzetDTO occupiedDTO) {
        return this.zauzetService.newOccupied(occupiedDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getNew(@PathVariable Long id ) {
        return this.zauzetService.getOccupied(id);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getOccupationUser(@PathVariable Long userId ) {
        return this.zauzetService.getOccupiedByUser(userId);
    }
}
