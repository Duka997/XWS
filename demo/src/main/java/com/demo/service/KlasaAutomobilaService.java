package com.demo.service;

import com.demo.client.VoziloClient;
import com.demo.dto.KlasaAutomobilaDTO;
import com.demo.generated.PostKlasaAutomobilaResponse;
import com.demo.generated.TKlasaAutomobila;
import com.demo.model.KlasaAutomobila;
import com.demo.repository.KlasaAutomobilaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlasaAutomobilaService {

    @Autowired
    private KlasaAutomobilaRepository klasaAutoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VoziloClient voziloClient;

    public KlasaAutomobila findById(Long id) throws AccessDeniedException {
        KlasaAutomobila u = klasaAutoRepository.findById(id).orElseGet(null);
        return u;
    }


    public KlasaAutomobila save(KlasaAutomobilaDTO mDTO) {
        KlasaAutomobila m = new KlasaAutomobila();
        m.setNaziv(mDTO.getNaziv());
        m.setObrisan(false);
        m = this.klasaAutoRepository.save(m);

        TKlasaAutomobila tComment = new TKlasaAutomobila();
        tComment.setNaziv(mDTO.getNaziv());
        tComment.setObrisan(mDTO.isObrisan());

        try {
            PostKlasaAutomobilaResponse response = this.voziloClient.postNewKlasa(tComment);
            //komentar.setRefId(response.getCommentResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //penaci u tklasa i pozovi u try //cjenovnik primjer
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
