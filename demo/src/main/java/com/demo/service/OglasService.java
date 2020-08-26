package com.demo.service;

import com.demo.dto.OglasDTO;
import com.demo.dto.OglasUKorpiDTO;
import com.demo.exception.NotFoundException;
import com.demo.model.*;
import com.demo.repository.OglasRepository;
import com.demo.repository.OglasUKorpiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private OglasUKorpiRepository oglasUKorpiRepository;

    private ModelMapper modelMapper;

    public ResponseEntity<?> getAds() {
        List<Oglas> oglasi = this.oglasRepository.findAll();
        List<OglasDTO> oglasiDTOS = new ArrayList<>();
        for(Oglas o: oglasi){
            //OglasDTO oglasDTO = modelMapper.map(o, OglasDTO.class);
            OglasDTO oglasDTO = new OglasDTO(o);
            oglasiDTOS.add(oglasDTO);
        }
        return new ResponseEntity<>(oglasiDTOS, HttpStatus.OK);
    }

    public OglasUKorpi saveInCart(OglasUKorpiDTO oglasUKorpiDTO) {
        OglasUKorpi oglasUKorpi = new OglasUKorpi();
        oglasUKorpi.setId(oglasUKorpiDTO.getId());
        oglasUKorpi.setOd(oglasUKorpiDTO.getOd());
        oglasUKorpi.setDoo(oglasUKorpiDTO.getDoo());
        oglasUKorpi.setUser(new User(oglasUKorpiDTO.getUser()));
        oglasUKorpi.setUserCartId(oglasUKorpiDTO.getUser().getId()); //oglas se nalazi kod ovog usera u korpi
        oglasUKorpi.setVozilo(new Vozilo(oglasUKorpiDTO.getVozilo()));

        oglasUKorpi = this.oglasUKorpiRepository.save(oglasUKorpi);
        return oglasUKorpi;
    }

    public ResponseEntity<?> getCartAds() {
        List<OglasUKorpi> oglasi = this.oglasUKorpiRepository.findAll();
        List<OglasUKorpiDTO> oglasiDTOS = new ArrayList<>();
        for(OglasUKorpi o: oglasi){
            //OglasDTO oglasDTO = modelMapper.map(o, OglasDTO.class);
            OglasUKorpiDTO oglasDTO = new OglasUKorpiDTO(o);
            oglasiDTOS.add(oglasDTO);
        }
        return new ResponseEntity<>(oglasiDTOS, HttpStatus.OK);
    }

    public ResponseEntity<?> removeAdFromCart(Long oglasId) {
        OglasUKorpi oglasUKorpi = this.oglasUKorpiRepository.findById(oglasId).orElseThrow(() -> new NotFoundException("Ad with given id was not found"));
        this.oglasUKorpiRepository.deleteById(oglasId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Oglas getAdById(Long adId) {
        return this.oglasRepository.findById(adId).orElseThrow(() -> new NotFoundException("Ad with given id was not found."));
    }
}
