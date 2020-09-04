package xml.team7.voziloservice.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.dto.IzvjestajDTO;
import xml.team7.voziloservice.model.Izvjestaj;
import xml.team7.voziloservice.model.Oglas;
import xml.team7.voziloservice.model.Vozilo;
import xml.team7.voziloservice.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private OglasService oglasService;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ReportRepository reportRepository;

    public Izvjestaj newReport(IzvjestajDTO reportDTO) throws NotFoundException {
        Oglas ad = oglasService.getCar(reportDTO.getOglasId());
        Vozilo car = ad.getVozilo();

        car = voziloService.updateCarsKilometrage(car, reportDTO.getKilometraza());

        Izvjestaj report = new Izvjestaj();
        report.setVozilo(car);
        report.setKomentar(reportDTO.getCommentReport());
        report.setPredjeniKilometri(reportDTO.getKilometraza());


        report = this.reportRepository.save(report);

        return report;
    }

    public ResponseEntity<?> newReport_ResponseEntity(IzvjestajDTO reportDTO) throws NotFoundException {
        Izvjestaj report = newReport(reportDTO);

        reportDTO.setId(report.getId());

        return new ResponseEntity<IzvjestajDTO>(reportDTO, HttpStatus.CREATED);
    }

}
