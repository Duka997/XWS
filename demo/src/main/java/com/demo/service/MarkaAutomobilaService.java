package com.demo.service;

import com.demo.client.VoziloClient;
import com.demo.dto.MarkaAutomobilaDTO;
import com.demo.generated.PostMarkaResponse;
import com.demo.generated.TMarkaAutomobila;
import com.demo.model.MarkaAutomobila;
import com.demo.repository.MarkaAutomobilaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private VoziloClient voziloClient;

    public MarkaAutomobila findById(Long id) throws AccessDeniedException {
        MarkaAutomobila u = markaAutomobilaRepository.findById(id).orElseGet(null);
        return u;
    }


    public MarkaAutomobila save(MarkaAutomobilaDTO mDTO) {
        MarkaAutomobila m = new MarkaAutomobila(mDTO);
        m.setObrisan(false);
        m = this.markaAutomobilaRepository.save(m);

        TMarkaAutomobila tComment = new TMarkaAutomobila();
        tComment.setNazivMarke(mDTO.getNazivMarke());
        tComment.setModel(mDTO.getModel());
        tComment.setObrisan(mDTO.isObrisan());

        try {
            PostMarkaResponse response = this.voziloClient.postNewMarka(tComment);
            //komentar.setRefId(response.getCommentResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
