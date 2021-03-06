package xml.team7.voziloservice.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.ZauzetDTO;
import xml.team7.voziloservice.service.ZauzetService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/occupied")
public class OccupiedController {

    @Autowired
    private ZauzetService zauzetService;

    @PostMapping
    public ResponseEntity<?> addNew(@RequestBody ZauzetDTO occupiedDTO) throws NotFoundException {
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

    @PostMapping(value = "/rent")
    public ResponseEntity<?> addNewRequestOccupation(@RequestBody ZauzetDTO zauzetDTO) throws NotFoundException {
        return this.zauzetService.newOccupiedRequest(zauzetDTO);
    }
}
