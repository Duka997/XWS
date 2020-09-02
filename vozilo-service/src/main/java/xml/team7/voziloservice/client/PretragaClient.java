package xml.team7.voziloservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.voziloservice.dto.*;

@FeignClient(name = "pretraga")
public interface PretragaClient {

    @PostMapping(value = "/api/cjenovnik/dodaj", consumes = "application/json")
    Void dodajCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO);

    @PostMapping(value = "/api/cjenovnik/izmjeni", consumes = "application/json")
    Void izmjeniCjenovnik(@RequestBody CjenovnikDTO cjenovnikDTO);

    @PostMapping(value = "/api/klasa/dodaj", consumes = "application/json")
    Void addKlasa(@RequestBody KlasaAutomobilaDTO mDTO);

    @PostMapping("/api/marka/dodaj")
    Void addMarka(@RequestBody MarkaAutomobilaDTO mDTO);

    @PostMapping("/api/tipgoriva/dodaj")
    Void addTipGoriva(@RequestBody TipGorivaDTO mDTO);

    @PostMapping("/api/tipmjenjaca/dodaj")
    Void addTipMjenjaca(@RequestBody TipMjenjacaDTO mDTO);

    @PostMapping("/api/oglas/dodaj")
   Void addNewOglas(@RequestBody OglasDTO oglasDTO);
}