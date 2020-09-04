package xml.team7.zahtjevservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.zahtjevservice.dto.ZauzetDTO;

@FeignClient(name = "vozilo")
public interface VoziloClient {

    @PostMapping(value = "/occupied/rent", consumes = "application/json")
    ResponseEntity<?> addNewRequestOccupation(@RequestBody ZauzetDTO occupiedDTO);

}
