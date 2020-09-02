package xml.team7.voziloservice.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.client.PretragaClient;
import xml.team7.voziloservice.dto.CjenovnikDTO;
import xml.team7.voziloservice.model.Cjenovnik;
import xml.team7.voziloservice.model.User;
import xml.team7.voziloservice.repository.CjenovnikRepository;
import xml.team7.voziloservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CjenovnikService {

    @Autowired
    private CjenovnikRepository cjenovnikRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PretragaClient pretragaClient;


    public ResponseEntity<?> getCjenovnik(Long id) {
        User u = userRepository.getOne(id);
        List<Cjenovnik> cjenovnici = this.cjenovnikRepository.findAllByUserUsername(u.getUsername());
        List<CjenovnikDTO> cjenovnikDTOS = new ArrayList<>();

        for(Cjenovnik c: cjenovnici){
            CjenovnikDTO cjenovnikDTO = modelMapper.map(c, CjenovnikDTO.class);
            cjenovnikDTO.setUserUsername(c.getUser().getUsername());

            cjenovnikDTOS.add(cjenovnikDTO);
        }

        return new ResponseEntity<>(cjenovnikDTOS, HttpStatus.OK);
    }

    public ResponseEntity<?> dodajCjenovnik(CjenovnikDTO cjenovnikDTO) {

        Cjenovnik cjenovnik = modelMapper.map(cjenovnikDTO, Cjenovnik.class);
        User user = this.userRepository.findByUsername(cjenovnikDTO.getUserUsername());
        cjenovnik.setUser(user);

        cjenovnik = this.cjenovnikRepository.save(cjenovnik);
        cjenovnikDTO.setId(cjenovnik.getId());

        this.pretragaClient.dodajCjenovnik(cjenovnikDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> izmjeniCjenovnik(CjenovnikDTO cjenovnikDTO) {

        Cjenovnik cjenovnik = this.cjenovnikRepository.getOne(cjenovnikDTO.getId());

        cjenovnik.setId(cjenovnikDTO.getId());
        cjenovnik.setCijenaPoDanu(cjenovnikDTO.getCijenaPoDanu());
        cjenovnik.setCijenaPoKM(cjenovnikDTO.getCijenaPoKM());
        cjenovnik.setCijenaCDW(cjenovnikDTO.getCijenaCDW());
        cjenovnik.setPopust(cjenovnikDTO.getPopust());

        this.pretragaClient.izmjeniCjenovnik(cjenovnikDTO);
        this.cjenovnikRepository.save(cjenovnik);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getCjenovnikUsername(String username) {

        List<Cjenovnik> cjenovnici = this.cjenovnikRepository.findAllByUserUsername(username);
        List<CjenovnikDTO> cjenovnikDTOS = new ArrayList<>();

        for(Cjenovnik c: cjenovnici){
            CjenovnikDTO cjenovnikDTO = modelMapper.map(c, CjenovnikDTO.class);
            cjenovnikDTO.setUserUsername(c.getUser().getUsername());

            cjenovnikDTOS.add(cjenovnikDTO);
        }

        return new ResponseEntity<>(cjenovnikDTOS,HttpStatus.OK);
    }
}
