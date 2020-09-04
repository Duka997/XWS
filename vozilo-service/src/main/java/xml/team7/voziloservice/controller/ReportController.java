package xml.team7.voziloservice.controller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.team7.voziloservice.dto.IzvjestajDTO;
import xml.team7.voziloservice.service.ReportService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "api/report")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> newReport (@RequestBody IzvjestajDTO reportDTO) throws NotFoundException {
        return reportService.newReport_ResponseEntity(reportDTO);
    }
}
