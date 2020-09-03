package xml.team7.voziloservice.controller;

import org.bouncycastle.util.encoders.Base64;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.VoziloDTO;
import xml.team7.voziloservice.service.VoziloService;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/vozilo")
public class VoziloController {
    @Autowired
    private VoziloService voziloService;

    @PostMapping("/dodaj/{agentId}")
    public ResponseEntity add(@RequestBody VoziloDTO mDTO, @PathVariable Long agentId) throws  SQLException, AccessDeniedException, NotFoundException {
        voziloService.dodajNovoVozilo(mDTO, agentId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get")
    public ResponseEntity<?> findAllVozila() throws AccessDeniedException {
        return this.voziloService.findAllVozila();
    }

    @GetMapping(value = "/getVozila/{username}")
    public ResponseEntity<?> getAllVozila(@PathVariable String username){
        return this.voziloService.getAllVozila(username);
    }

    @GetMapping(value = "/statistics/{ownersID}")
    public ResponseEntity<?> getStatistics(@PathVariable Long ownersID){
        return voziloService.getCarStatistics(ownersID);
    }

}
