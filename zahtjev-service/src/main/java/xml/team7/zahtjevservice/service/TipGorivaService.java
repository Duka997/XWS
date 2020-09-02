package xml.team7.zahtjevservice.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.TipGorivaDTO;
import xml.team7.zahtjevservice.model.TipGoriva;
import xml.team7.zahtjevservice.repository.TipGorivaRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TipGorivaService {
    @Autowired
    private TipGorivaRepository vrstaGorivaRepository;

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




    public ResponseEntity<?> getAll() {
        List<TipGoriva> result = vrstaGorivaRepository.findAll();
        List<TipGorivaDTO> vrstaGorivaDTOS = new ArrayList<>();

        for(TipGoriva t: result){
            if(t.isObrisan()==false) {
                vrstaGorivaDTOS.add(modelMapper.map(t, TipGorivaDTO.class));
            }
        }

        return new ResponseEntity<>(vrstaGorivaDTOS, HttpStatus.OK);
    }
}
