package xml.team7.voziloservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoziloSaNajviseKomentaraDTO {
    private Long id;
    private Long markaId;
    private Long modelId;
    private String nazivMarke;
    private String model;
    private double brojKomentara;
}
