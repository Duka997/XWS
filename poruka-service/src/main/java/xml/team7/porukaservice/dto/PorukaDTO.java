package xml.team7.porukaservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PorukaDTO {

    private Long id;

    private String sadrzaj;

    private Long posiljalacId;

    private Long primalacId;
}
