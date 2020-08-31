package xml.team7.zuul.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xml.team7.zuul.dto.RoleDTO;

@FeignClient(name = "user")
public interface UserClient {

    @PostMapping(value = "/verify")
    RoleDTO verify(@RequestBody String token);
}
