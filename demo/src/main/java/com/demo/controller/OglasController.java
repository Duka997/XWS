package com.demo.controller;

import com.demo.dto.OglasDTO;
import com.demo.model.Oglas;
import com.demo.service.OglasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/oglasi")
public class OglasController{

        @Autowired
        private OglasService oglasService;


        @PostMapping("/dodaj")
        public ResponseEntity<?> addNew(@RequestBody OglasDTO oglasDTO) {
            return this.oglasService.noviOglas(oglasDTO);
        }
}