package xml.team7.voziloservice.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.client.PretragaClient;
import xml.team7.voziloservice.dto.MarkaAutomobilaDTO;
import xml.team7.voziloservice.generated.PostMarkaResponse;
import xml.team7.voziloservice.generated.TMarkaAutomobila;
import xml.team7.voziloservice.model.MarkaAutomobila;
import xml.team7.voziloservice.repository.MarkaAutomobilaRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkaAutomobilaService {
    @Autowired
    private MarkaAutomobilaRepository markaAutomobilaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PretragaClient pretragaClient;


    public MarkaAutomobila findById(Long id) throws AccessDeniedException {
        MarkaAutomobila u = markaAutomobilaRepository.findById(id).orElseGet(null);
        return u;
    }


    public MarkaAutomobila save(MarkaAutomobilaDTO mDTO) {
        MarkaAutomobila m = new MarkaAutomobila(mDTO);
        m.setObrisan(false);
        m = this.markaAutomobilaRepository.save(m);
        mDTO.setId(m.getId());
        this.pretragaClient.addMarka(mDTO);
        return m;
    }


    public ResponseEntity<?> getAll() {

        List<MarkaAutomobila> result = markaAutomobilaRepository.findAll();
        List<MarkaAutomobilaDTO> markaAutomobilaDTOS = new ArrayList<>();

        for(MarkaAutomobila ma: result){
            if(!ma.isObrisan()) {
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
    public MarkaAutomobila edit(MarkaAutomobilaDTO markaAutomobilaDTO) throws AccessDeniedException {
        MarkaAutomobila markaAutomobila = this.findById(markaAutomobilaDTO.getId());
        markaAutomobila.setId(markaAutomobilaDTO.getId());
        markaAutomobila.setModel(markaAutomobilaDTO.getModel());
        markaAutomobila.setNazivMarke(markaAutomobilaDTO.getNazivMarke());
        this.markaAutomobilaRepository.save(markaAutomobila);
        return markaAutomobila;
    }

    public void delete(Long id) throws AccessDeniedException {
        MarkaAutomobila markaAutomobila = this.findById(id);
        markaAutomobila.setObrisan(true);
        this.markaAutomobilaRepository.save(markaAutomobila);
    }

    public PostMarkaResponse postMarkaSoap(TMarkaAutomobila markaRequest) {
        MarkaAutomobila m = new MarkaAutomobila();
        m.setId(markaRequest.getId());
        m.setNazivMarke(markaRequest.getNazivMarke());
        m.setModel(markaRequest.getModel());
        m.setObrisan(markaRequest.isObrisan());
        m=this.markaAutomobilaRepository.save(m);
        PostMarkaResponse response = new PostMarkaResponse();
        response.setMarkaResponse(m.getId());
        return response;
    }
}
