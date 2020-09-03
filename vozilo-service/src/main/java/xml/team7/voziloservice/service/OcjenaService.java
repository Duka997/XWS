package xml.team7.voziloservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.dto.OcjenaDTO;
import xml.team7.voziloservice.generated.GetGradeResponse;
import xml.team7.voziloservice.generated.TGrade;
import xml.team7.voziloservice.model.Ocjena;
import xml.team7.voziloservice.model.Oglas;
import xml.team7.voziloservice.model.User;
import xml.team7.voziloservice.model.Vozilo;
import xml.team7.voziloservice.repository.OcjenaRepository;
import xml.team7.voziloservice.repository.OglasRepository;
import xml.team7.voziloservice.repository.UserRepository;
import xml.team7.voziloservice.repository.VoziloRepository;

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
    private OglasRepository oglasRepository;

    @Autowired
    private VoziloRepository voziloRepository;

    public ResponseEntity<?> kreirajOcjenu(OcjenaDTO ocenaDTO) {
        Ocjena ocena = modelMapper.map(ocenaDTO, Ocjena.class);
        User user = this.userRepository.findByUsername(ocenaDTO.getUserUsername());
        ocena.setUser(user);
        Oglas oglas = this.oglasRepository.getOne(ocenaDTO.getOglasId());
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

    public GetGradeResponse getOcjeneSoap(long gradeRequest) {
        Oglas oglas = oglasRepository.getOne(gradeRequest);
        Vozilo vozilo = oglas.getVozilo();
        List<Ocjena> ocjene = this.ocjenaRepository.findAllByVoziloId(vozilo.getId());

        GetGradeResponse response = new GetGradeResponse();
        for(Ocjena o: ocjene){
            TGrade tGrade = new TGrade();
            tGrade.setGrade((int)o.getOcjena());
            tGrade.setId(o.getId());
            tGrade.setUserUsername(o.getUser().getUsername());
            tGrade.setAdId(o.getVozilo().getId());
            response.getGrades().add(tGrade);
        }
        return response;
    }
}
