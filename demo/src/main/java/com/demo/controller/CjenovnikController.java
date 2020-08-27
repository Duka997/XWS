package com.demo.controller;

import com.demo.service.CjenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cjenovnik")
public class CjenovnikController {

    @Autowired
    private CjenovnikService cjenovnikService;

    @GetMapping(value = "/getCjenovnik/{id}")
    private ResponseEntity<?> getCjenovnik(@PathVariable Long id){
        return this.cjenovnikService.getCjenovnik(id);
    }


}
