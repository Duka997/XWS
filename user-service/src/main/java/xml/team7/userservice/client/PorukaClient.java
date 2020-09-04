package xml.team7.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.userservice.dto.UserDTO;

@FeignClient(name = "poruka")
public interface PorukaClient {

    @PostMapping(value = "", consumes = "application/json")
    Void add(@RequestBody UserDTO userDTO);

    @PutMapping(value = "", consumes = "application/json")
    Void edit(@RequestBody UserDTO userDTO);
}
