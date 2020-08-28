package com.demo.dto;

import com.demo.util.JsonJodaLocalTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZauzetDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaLocalTimeSerializer.class)
    private DateTime dateTo;
    @JsonSerialize(using = JsonJodaLocalTimeSerializer.class)
    private DateTime dateFrom;
    private Long carId;
    private VoziloDTO car;
    private List<Long> adsId;
}
