package com.demo.service;

import com.demo.dto.TipGorivaDTO;
import com.demo.model.TipGoriva;
import com.demo.repository.TipGorivaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipGorivaService {
    @Autowired
    private TipGorivaRepository vrstaGorivaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TipGoriva findById(Long id) throws AccessDeniedException {
        TipGoriva u = vrstaGorivaRepository.findById(id).orElseGet(null);
        return u;
    }


    public TipGoriva save(TipGorivaDTO mDTO) {
        TipGoriva m = new TipGoriva();
        m.setNaziv(mDTO.getNaziv());
        m.setObrisan(false);

        m = this.vrstaGorivaRepository.save(m);
        return m;
    }

    public List<TipGorivaDTO> findAll() throws AccessDeniedException {
        List<TipGoriva> result = vrstaGorivaRepository.findAll();
        List<TipGorivaDTO> finalResult = new ArrayList<>();
        for(TipGoriva tipGoriva : result){
            if(!tipGoriva.isObrisan()){
                finalResult.add(modelMapper.map(tipGoriva, TipGorivaDTO.class));
            }
        }
        return finalResult;
    }

    public TipGoriva edit(TipGorivaDTO vrstaGorivaDTO){
        TipGoriva tipGoriva = this.findById(vrstaGorivaDTO.getId());
        tipGoriva.setId(vrstaGorivaDTO.getId());
        tipGoriva.setNaziv(vrstaGorivaDTO.getNaziv());
        this.vrstaGorivaRepository.save(tipGoriva);
        return tipGoriva;
    }

    public void delete(Long id){
        TipGoriva tipGoriva = this.findById(id);
        tipGoriva.setObrisan(true);
        this.vrstaGorivaRepository.save(tipGoriva);
    }

    public ResponseEntity<?> getAll() {
        List<TipGoriva> result = vrstaGorivaRepository.findAll();
        List<TipGorivaDTO> vrstaGorivaDTOS = new ArrayList<>();

        for(TipGoriva t: result){
            vrstaGorivaDTOS.add(modelMapper.map(t, TipGorivaDTO.class));
        }

        return new ResponseEntity<>(vrstaGorivaDTOS, HttpStatus.OK);
    }
}
