package xml.team7.voziloservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.KomentarDTO;
import xml.team7.voziloservice.service.KomentarService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/komentar")
public class KomentarController {

    @Autowired
    private KomentarService komentarService;


    @PostMapping()
    private ResponseEntity<?> dodajKomentar(@RequestBody KomentarDTO komentarDTO) {
        return this.komentarService.kreirajKomentar(komentarDTO, false);
    }

    @PostMapping(value = "/odgovor")
    private ResponseEntity<?> dodajKomentarReply(@RequestBody KomentarDTO komentarDTO) {
        return this.komentarService.kreirajKomentar(komentarDTO, true);
    }

    @GetMapping(value = "/getKomentareVozilo/{voziloId}")
    private ResponseEntity<?> pokupiKomentare(@PathVariable Long voziloId){
        return this.komentarService.pokupiKomentare(voziloId);
    }

    @GetMapping(value = "/provjeri/{username}/{id}")
    private ResponseEntity<?> provjerikomentar(@PathVariable Long id, @PathVariable String username){
        return this.komentarService.provjeriKomentar(username, id);
    }

    @GetMapping()
    private ResponseEntity<?> pokupiKomentareSve(){
        return this.komentarService.pokupiSveKomentare();
    }

    @GetMapping(value = "/odobri/{flag}/{id}")
    private ResponseEntity<?> odobriKomentar(@PathVariable Boolean flag, @PathVariable Long id){
        return this.komentarService.odobriKomentar(flag, id);
    }
}
