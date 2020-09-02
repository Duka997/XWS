package xml.team7.zahtjevservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.OcjenaDTO;
import xml.team7.zahtjevservice.model.Ocjena;
import xml.team7.zahtjevservice.model.Oglas;
import xml.team7.zahtjevservice.model.User;
import xml.team7.zahtjevservice.model.Vozilo;
import xml.team7.zahtjevservice.repository.OcjenaRepository;
import xml.team7.zahtjevservice.repository.UserRepository;
import xml.team7.zahtjevservice.repository.VoziloRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OcjenaService {

    @Autowired
    private OcjenaRepository ocjenaRepository;

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
        Vozilo vozilo = oglas.getVozilo();
        ocena.setVozilo(vozilo);

        double sum = 0;
        if(vozilo.getOcjene().size() != 0) {
            for (Ocjena o : vozilo.getOcjene()) {
                sum = sum + o.getOcjena();
            }
            vozilo.setOcjena(sum/vozilo.getOcjene().size());
        }

        Ocjena ocjenaProvjera = this.ocjenaRepository.findByUserIdAndOglasId(user.getId(),oglas.getId());

        if(ocjenaProvjera != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    public ResponseEntity<?> provjeriOcjenu(String username, Long id) {
        User user = this.userRepository.findByUsername(username);
        Ocjena ocjenaProvjera = this.ocjenaRepository.findByUserIdAndOglasId(user.getId(), id);

        if (ocjenaProvjera != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
