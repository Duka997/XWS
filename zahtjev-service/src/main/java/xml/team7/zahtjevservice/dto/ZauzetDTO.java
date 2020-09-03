package xml.team7.zahtjevservice.dto;

import xml.team7.zahtjevservice.util.JsonJodaDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.joda.time.DateTime;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZauzetDTO {
    private Long id;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime doo;
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    private DateTime od;
    private Long voziloId;
    private List<Long> oglasId;

    public DateTime getOd(DateTime dateFrom) {
        return this.od;
    }

    public DateTime getDoo(DateTime dateTo) {
        return this.doo;
    }
}
