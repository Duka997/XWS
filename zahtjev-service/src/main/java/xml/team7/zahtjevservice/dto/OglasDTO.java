package xml.team7.zahtjevservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import xml.team7.zahtjevservice.model.Oglas;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OglasDTO {

    private Long id;
    private UserDTO user;
    private Long userId;

    public OglasDTO() {
    }

    public OglasDTO(Oglas o) {
        this.userId = o.getUser().getId();
        this.id = o.getId();
        this.user = new UserDTO(o.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
