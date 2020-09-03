package xml.team7.zahtjevservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SviZahtjeviDTO {
    private List<ZahtjevDTO> all;
    private List<ZahtjevDTO> pending;
    private List<ZahtjevDTO> paid;
    private List<ZahtjevDTO> finished;
}
