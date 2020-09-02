package xml.team7.voziloservice.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.client.PretragaClient;
import xml.team7.voziloservice.dto.KlasaAutomobilaDTO;
import xml.team7.voziloservice.model.KlasaAutomobila;
import xml.team7.voziloservice.repository.KlasaAutomobilaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlasaAutomobilaService {

    @Autowired
    private KlasaAutomobilaRepository klasaAutoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PretragaClient pretragaClient;

    public KlasaAutomobila findById(Long id) throws AccessDeniedException {
        KlasaAutomobila u = klasaAutoRepository.findById(id).orElseGet(null);
        return u;
    }


    public KlasaAutomobila save(KlasaAutomobilaDTO mDTO) {
        KlasaAutomobila m = new KlasaAutomobila();
        m.setNaziv(mDTO.getNaziv());
        m.setObrisan(false);
        m = this.klasaAutoRepository.save(m);
        mDTO.setId(m.getId());
        this.pretragaClient.addKlasa(mDTO);
        return m;
    }

    public ResponseEntity<?> findAll() {

        List<KlasaAutomobila> result = klasaAutoRepository.findAll();
        List<KlasaAutomobilaDTO> klasaAutomobilaDTOS = new ArrayList<>();

        for(KlasaAutomobila ma: result){
            if(!ma.isObrisan()) {
                klasaAutomobilaDTOS.add(modelMapper.map(ma, KlasaAutomobilaDTO.class));
            }
        }

        return new ResponseEntity<>(klasaAutomobilaDTOS, HttpStatus.OK);
    }
    public KlasaAutomobila edit(KlasaAutomobilaDTO klasaAutomobilaDTO) throws java.nio.file.AccessDeniedException {
        KlasaAutomobila klasaAutomobila = this.findById(klasaAutomobilaDTO.getId());
        klasaAutomobila.setId(klasaAutomobilaDTO.getId());
        klasaAutomobila.setNaziv(klasaAutomobilaDTO.getNaziv());
        this.klasaAutoRepository.save(klasaAutomobila);
        return klasaAutomobila;
    }

    public void delete(Long id) throws java.nio.file.AccessDeniedException {
        KlasaAutomobila klasaAutomobila = this.findById(id);
        klasaAutomobila.setObrisan(true);
        this.klasaAutoRepository.save(klasaAutomobila);
    }

}
