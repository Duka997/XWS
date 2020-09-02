package xml.team7.pretragaservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import xml.team7.pretragaservice.dto.KlasaAutomobilaDTO;
import xml.team7.pretragaservice.model.KlasaAutomobila;
import xml.team7.pretragaservice.repository.KlasaAutomobilaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlasaAutomobilaService {

    @Autowired
    private KlasaAutomobilaRepository klasaAutoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> getKlase() {
        List<KlasaAutomobila> result = klasaAutoRepository.findAll();
        List<KlasaAutomobilaDTO> klasaAutomobilaDTOS = new ArrayList<>();

        for(KlasaAutomobila ma: result){
            if(!ma.getObrisan()) {
                klasaAutomobilaDTOS.add(modelMapper.map(ma, KlasaAutomobilaDTO.class));
            }
        }

        return new ResponseEntity<>(klasaAutomobilaDTOS, HttpStatus.OK);
    }
}
