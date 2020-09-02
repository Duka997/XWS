package xml.team7.pretragaservice.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;
import xml.team7.pretragaservice.model.TipGoriva;
import xml.team7.pretragaservice.util.JsonJodaDateTimeSerializer;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PretragaDTO {
    private String mjestoPreuzimanja;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime od;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime doo;
    private MarkaAutomobilaDTO marka;
    private TipGoriva gorivo;
    private TipMjenjacaDTO mjenjac;
    private KlasaAutomobilaDTO klasa;
    private double cijenaOd;
    private double cijenaDo;
    private double kilometrazaOd;
    private double kilometrazaDo;
    private double kilometrazaDozvoljena;
    private Boolean cdw;
    private int brojDjecijihMjesta;
}
