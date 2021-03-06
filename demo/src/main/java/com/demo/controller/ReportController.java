package com.demo.controller;

import com.demo.dto.IzvjestajDTO;
import com.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> newReport (@RequestBody IzvjestajDTO reportDTO){
        return reportService.newReport_ResponseEntity(reportDTO);
    }
}
