package com.demo.controller;

import com.demo.dto.CjenovnikDTO;
import com.demo.service.CjenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cjenovnik")
public class CjenovnikController {

    @Autowired
    private CjenovnikService cjenovnikService;

    @GetMapping(value = "/getCjenovnik/{id}")
    private ResponseEntity<?> getCjenovnik(@PathVariable Long id){
        return this.cjenovnikService.getCjenovnik(id);
    }

    @PostMapping("/dodaj")
    private ResponseEntity<?> dodajCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO){
        return this.cjenovnikService.dodajCjenovnik(cjenovnikDTO);
    }

    @PostMapping ("/izmjeni")
    private ResponseEntity<?> izmjeniCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO){
        return this.cjenovnikService.izmjeniCjenovnik(cjenovnikDTO);
    }

    @GetMapping(value = "/getCjenovnikUsername/{username}")
    private ResponseEntity<?> getCjenovnik(@PathVariable String username){
        return this.cjenovnikService.getCjenovnikUsername(username);
    }

}
