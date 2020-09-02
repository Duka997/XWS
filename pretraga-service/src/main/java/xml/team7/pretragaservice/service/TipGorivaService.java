package xml.team7.pretragaservice.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import xml.team7.pretragaservice.dto.KlasaAutomobilaDTO;
import xml.team7.pretragaservice.dto.TipGorivaDTO;
import xml.team7.pretragaservice.model.KlasaAutomobila;
import xml.team7.pretragaservice.model.TipGoriva;
import xml.team7.pretragaservice.repository.TipGorivaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipGorivaService {
    @Autowired
    private TipGorivaRepository vrstaGorivaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> getGoriva() {

        List<TipGoriva> result = vrstaGorivaRepository.findAll();
        List<TipGorivaDTO> finalResult = new ArrayList<>();
        for(TipGoriva tipGoriva : result){
            if(!tipGoriva.getObrisan()){
                finalResult.add(modelMapper.map(tipGoriva, TipGorivaDTO.class));
            }
        }
        return new ResponseEntity<>(finalResult, HttpStatus.OK);
    }

    public TipGoriva save(TipGorivaDTO mDTO) {
        TipGoriva m = new TipGoriva();
        m.setNaziv(mDTO.getNaziv());
        m.setObrisan(false);
        m.setId(mDTO.getId());
        m = this.vrstaGorivaRepository.save(m);
        return m;
    }

}
