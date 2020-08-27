package com.demo.dto;

import com.demo.model.TipGoriva;
import com.demo.utils.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
