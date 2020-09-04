package com.demo.controller;

import com.demo.dto.TipGorivaDTO;
import com.demo.model.TipGoriva;
import com.demo.service.TipGorivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/tipgoriva")
public class TipGorivaController {
    @Autowired
    private TipGorivaService vrstaGorivaService;


    @PostMapping("/dodaj")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity add(@RequestBody TipGorivaDTO mDTO) {
        vrstaGorivaService.save(mDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
  //  @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?> loadAll() {
        return this.vrstaGorivaService.getAll();
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return this.vrstaGorivaService.getAll();
    }

    @PutMapping("/edit")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipGoriva> edit(@RequestBody  TipGorivaDTO vrstaGorivaDTO){
        return new ResponseEntity<>(this.vrstaGorivaService.edit(vrstaGorivaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        vrstaGorivaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
