package xml.team7.zahtjevservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import xml.team7.zahtjevservice.model.OglasUKorpi;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OglasUKorpiDTO {

    private Long id;
    private Long userId;
    private Long userCartId;    //id usera kod kog se nalazi oglas
    private Long adId;

    public OglasUKorpiDTO(OglasUKorpi o) {
        this.id = o.getId();
        this.userId = o.getUserId();
        this.userCartId = o.getUserCartId();
        this.adId = o.getAdId();
    }
}
