package com.demo.service;

import com.demo.dto.OcjenaDTO;
import com.demo.model.Ocjena;
import com.demo.model.Oglas;
import com.demo.model.User;
import com.demo.model.Vozilo;
import com.demo.repository.OcjenaRepository;
import com.demo.repository.UserRepository;
import com.demo.repository.VoziloRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OcjenaService {

    @Autowired
    private OcjenaRepository ocjenaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OglasService oglasService;

    @Autowired
    private VoziloRepository voziloRepository;

    public ResponseEntity<?> kreirajOcjenu(OcjenaDTO ocenaDTO) {
        Ocjena ocena = modelMapper.map(ocenaDTO, Ocjena.class);
        User user = this.userRepository.findByUsername(ocenaDTO.getUserUsername());
        ocena.setUser(user);
        Oglas oglas = this.oglasService.findById(ocenaDTO.getOglasId());
        ocena.setOglas(oglas);
        ocena.setVozilo(oglas.getVozilo());
        Vozilo vozilo = oglas.getVozilo();
        double sum = 0;
        if(vozilo.getOcjene().size() != 0) {
            for (Ocjena o : vozilo.getOcjene()) {
                sum = sum + o.getOcjena();
            }
            vozilo.setOcjena(sum/vozilo.getOcjene().size());
        }

        this.voziloRepository.save(vozilo);
        this.ocjenaRepository.save(ocena);

        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> getOcjeneZaAuto(Long voziloId) {
        List<Ocjena> ocenas = this.ocjenaRepository.findAllByVoziloId(voziloId);
        List<OcjenaDTO> ocenaDTOS = new ArrayList<>();

        for(Ocjena o: ocenas){
            OcjenaDTO ocenaDTO = modelMapper.map(o, OcjenaDTO.class);
            ocenaDTO.setVoziloId(o.getVozilo().getId());
            ocenaDTO.setOglasId(o.getOglas().getId());
            User user = this.userRepository.getOne(o.getUser().getId());
            ocenaDTO.setUserUsername(user.getUsername());
            ocenaDTOS.add(ocenaDTO);
        }

        return new ResponseEntity<>(ocenaDTOS, HttpStatus.OK);
    }
}
