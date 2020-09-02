package xml.team7.pretragaservice.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import xml.team7.pretragaservice.dto.KlasaAutomobilaDTO;
import xml.team7.pretragaservice.dto.TipMjenjacaDTO;
import xml.team7.pretragaservice.model.KlasaAutomobila;
import xml.team7.pretragaservice.model.TipMjenjaca;
import xml.team7.pretragaservice.repository.TipMjenjacaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipMjenjacaService {
    @Autowired
    private TipMjenjacaRepository tipMjenjacaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> getMjenjaci() {
        List<TipMjenjaca> result = tipMjenjacaRepository.findAll();
        List<TipMjenjacaDTO> finalResult = new ArrayList<>();
        for(TipMjenjaca tipMjenjaca : result){
            if(!tipMjenjaca.getObrisan()){
                finalResult.add(modelMapper.map(tipMjenjaca, TipMjenjacaDTO.class));
            }
        }
        return new ResponseEntity<>(finalResult, HttpStatus.OK);
    }
}
