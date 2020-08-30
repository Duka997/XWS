package com.demo.service;

import com.demo.dto.IzvjestajDTO;
import com.demo.model.Izvjestaj;
import com.demo.model.Oglas;
import com.demo.model.Vozilo;
import com.demo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private OglasService oglasService;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private RacunService racunService;

    public Izvjestaj newReport(IzvjestajDTO reportDTO){
        Oglas ad = oglasService.getCar(reportDTO.getOglasId());
        Vozilo car = ad.getVozilo();

        car = voziloService.updateCarsKilometrage(car, reportDTO.getKilometraza());

        Izvjestaj report = new Izvjestaj();
        report.setVozilo(car);
        report.setKomentar(reportDTO.getCommentReport());
        report.setPredjeniKilometri(reportDTO.getKilometraza());

        if(ad.getDozvoljenaKilometraza() != 0 && ad.getDozvoljenaKilometraza() < report.getPredjeniKilometri()){
            this.racunService.createBill(report.getPredjeniKilometri()-ad.getDozvoljenaKilometraza(), ad.getCjenovnik(), reportDTO.getUserId());
        }

        report = this.reportRepository.save(report);

        return report;
    }

    public ResponseEntity<?> newReport_ResponseEntity(IzvjestajDTO reportDTO) {
        Izvjestaj report = newReport(reportDTO);

        reportDTO.setId(report.getId());

        return new ResponseEntity<IzvjestajDTO>(reportDTO, HttpStatus.CREATED);
    }

}
