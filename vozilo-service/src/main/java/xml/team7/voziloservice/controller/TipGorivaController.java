package xml.team7.voziloservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.TipGorivaDTO;
import xml.team7.voziloservice.model.TipGoriva;
import xml.team7.voziloservice.service.TipGorivaService;

@CrossOrigin
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
