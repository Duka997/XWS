package xml.team7.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.userservice.dto.UserDTO;

@FeignClient(name = "vozilo")
public interface VoziloClient {

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody UserDTO userDTO);

    @PutMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> edit(@RequestBody UserDTO userDTO);

}
