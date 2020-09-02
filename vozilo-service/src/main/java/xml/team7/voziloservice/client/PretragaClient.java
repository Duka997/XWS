package xml.team7.voziloservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.voziloservice.dto.CjenovnikDTO;

@FeignClient(name = "pretraga")
public interface PretragaClient {

    @PostMapping(value = "/api/cjenovnik/dodaj", consumes = "application/json")
    Void dodajCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO);

    @PostMapping (value = "/api/cjenovnik/izmjeni", consumes = "application/json")
    Void izmjeniCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO);
}
