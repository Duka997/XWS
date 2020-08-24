package com.demo.controller;

import com.demo.dto.TipMjenjacaDTO;
import com.demo.model.TipMjenjaca;
import com.demo.service.TipMjenjacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tipmjenjaca", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipMjenjacaContreoller {
    @Autowired
    private TipMjenjacaService tipMjenjacaService;


    @PostMapping("/dodaj")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity add(@RequestBody TipMjenjacaDTO mDTO) {
        tipMjenjacaService.save(mDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
    public List<TipMjenjacaDTO> loadAll() {
        return this.tipMjenjacaService.findAll();
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return this.tipMjenjacaService.getAll();
    }

    @PutMapping("/edit")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipMjenjaca> edit(@RequestBody TipMjenjacaDTO tipMjenjacaDTO){
        return new ResponseEntity<>(this.tipMjenjacaService.edit(tipMjenjacaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        tipMjenjacaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
