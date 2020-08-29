package com.demo.service;

import com.demo.dto.PorukaDTO;
import com.demo.model.Poruka;
import com.demo.model.User;
import com.demo.repository.PorukaRepository;
import com.demo.repository.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PorukaService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PorukaRepository porukaRepository;

    public void posaljiPoruku(PorukaDTO porukaDTO) {
        Poruka poruka = new Poruka();
        poruka.setSadrzaj(porukaDTO.getSadrzaj());
        poruka.setDatumSlanja(DateTime.now());
        poruka.setPosiljalacId(porukaDTO.getPosiljalacId());
        poruka.setPrimalacId(porukaDTO.getPrimalacId());
        porukaRepository.save(poruka);
    }

    public List<PorukaDTO> getMojePoruke (Long id){
        List<Poruka> messages = porukaRepository.findAll();
        List<PorukaDTO> messageDTOS = new ArrayList<>();
        for(Poruka por: messages){
            if(por.getPrimalacId().equals(id)){
                PorukaDTO porukaDTO = new PorukaDTO(por.getId(), por.getSadrzaj(), por.getPosiljalacId(), por.getPrimalacId());
                messageDTOS.add(porukaDTO);
            }
        }
        return messageDTOS;
    }

    public List<PorukaDTO> getPoslatePoruke (Long id){
        List<Poruka> messages = porukaRepository.findAll();
        List<PorukaDTO> messageDTOS = new ArrayList<>();
        for(Poruka por: messages){
            if(por.getPosiljalacId().equals(id)){
                PorukaDTO porukaDTO = new PorukaDTO(por.getId(), por.getSadrzaj(), por.getPosiljalacId(), por.getPrimalacId());
                messageDTOS.add(porukaDTO);
            }
        }
        return messageDTOS;
    }


}
