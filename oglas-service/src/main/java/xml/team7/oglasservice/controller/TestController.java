package xml.team7.oglasservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
public class TestController {

    @GetMapping
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
