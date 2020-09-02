package xml.team7.zahtjevservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KomentarDTO {
    private Long id;
    private String tekst;
    private boolean odobren;
    private Long voziloId;
    private Long userId;
    private String userUsername;
    private Long oglasId;
    private String role;
    private Long refId;
}
