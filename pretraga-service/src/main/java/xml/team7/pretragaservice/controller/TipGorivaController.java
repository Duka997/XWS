package xml.team7.pretragaservice.controller;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.pretragaservice.service.TipGorivaService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/tipgoriva")
public class TipGorivaController {
    @Autowired
    private TipGorivaService vrstaGorivaService;


    @GetMapping("/get")
    public ResponseEntity<?> getGoriva() {
        return vrstaGorivaService.getGoriva();
    }
}
