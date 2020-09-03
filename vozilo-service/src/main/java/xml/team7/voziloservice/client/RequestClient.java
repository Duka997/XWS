package xml.team7.voziloservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.voziloservice.dto.CjenovnikDTO;
import xml.team7.voziloservice.dto.ZauzetDTO;

@FeignClient(name = "zahtjev")
public interface RequestClient {

    @PostMapping(value = "/occupied", consumes = "application/json")
    Void getRequestsHistory(@RequestBody ZauzetDTO occupiedDTO);

    @PostMapping(value = "/bill/{kilometrage}/{user_id}")
    Void createBill(@PathVariable double kilometrage, @PathVariable Long user_id, @RequestBody CjenovnikDTO priceList);

    @GetMapping(value = "/cancel/{id}", consumes = "application/json")
    Void cancelRequest(@PathVariable Long id);

    //@PostMapping(value = "/ad")
    //Void postAd(@RequestBody NewAdRequestDTO adDTO);
}
