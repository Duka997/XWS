package xml.team7.pretragaservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.pretragaservice.dto.CjenovnikDTO;
import xml.team7.pretragaservice.model.Cjenovnik;
import xml.team7.pretragaservice.repository.CjenovnikRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CjenovnikService {

    @Autowired
    private CjenovnikRepository cjenovnikRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<?> dodajCjenovnik(CjenovnikDTO cjenovnikDTO) {

        Cjenovnik cjenovnik = modelMapper.map(cjenovnikDTO, Cjenovnik.class);
        this.cjenovnikRepository.save(cjenovnik);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> izmjeniCjenovnik(CjenovnikDTO cjenovnikDTO) {

        Cjenovnik cjenovnik = this.cjenovnikRepository.getOne(cjenovnikDTO.getId());

        cjenovnik.setId(cjenovnikDTO.getId());
        cjenovnik.setCijenaPoDanu(cjenovnikDTO.getCijenaPoDanu());
        cjenovnik.setCijenaPoKM(cjenovnikDTO.getCijenaPoKM());
        cjenovnik.setCijenaCDW(cjenovnikDTO.getCijenaCDW());
        cjenovnik.setPopust(cjenovnikDTO.getPopust());

        this.cjenovnikRepository.save(cjenovnik);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
