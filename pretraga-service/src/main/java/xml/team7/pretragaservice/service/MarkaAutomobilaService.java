package xml.team7.pretragaservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.pretragaservice.dto.KlasaAutomobilaDTO;
import xml.team7.pretragaservice.dto.MarkaAutomobilaDTO;
import xml.team7.pretragaservice.model.KlasaAutomobila;
import xml.team7.pretragaservice.model.MarkaAutomobila;
import xml.team7.pretragaservice.repository.MarkaAutomobilaRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkaAutomobilaService {
    @Autowired
    private MarkaAutomobilaRepository markaAutomobilaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<?> getMarke() {
        List<MarkaAutomobila> result = markaAutomobilaRepository.findAll();
        List<MarkaAutomobilaDTO> markaAutomobilaDTOS = new ArrayList<>();

        for(MarkaAutomobila ma: result){
            if(!ma.getObrisan()) {
                markaAutomobilaDTOS.add(modelMapper.map(ma, MarkaAutomobilaDTO.class));
            }
        }

        return new ResponseEntity<>(markaAutomobilaDTOS, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllModels(Long markId) {
        List<MarkaAutomobila> result = markaAutomobilaRepository.findAllById(markId);
        List<String> modeli = new ArrayList<>();

        for(MarkaAutomobila ma: result){
            modeli.add(ma.getModel());
        }

        return new ResponseEntity<>(modeli, HttpStatus.OK);
    }
}
