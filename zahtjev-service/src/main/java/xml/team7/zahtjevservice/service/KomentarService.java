package xml.team7.zahtjevservice.service;




import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.KomentarDTO;
import xml.team7.zahtjevservice.model.Komentar;
import xml.team7.zahtjevservice.model.Oglas;
import xml.team7.zahtjevservice.model.User;
import xml.team7.zahtjevservice.repository.KomentarRepository;
import xml.team7.zahtjevservice.repository.OglasRepository;
import xml.team7.zahtjevservice.repository.UserRepository;
import xml.team7.zahtjevservice.repository.VoziloRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OglasRepository oglasRepository;


    private ModelMapper modelMapper;


    public ResponseEntity<?> pokupiKomentare(Long id) {
        List<Komentar> komentari = this.komentarRepository.findAllByVoziloIdAndOdobren(id, true);
        List<KomentarDTO> komentariDTO = new ArrayList<>();

        List<KomentarDTO> komentariDTOSoap = new ArrayList<>();
        List<Oglas> oglasi = this.oglasRepository.findAllByVoziloId(id);


        for(Komentar k: komentari){
            User user = this.userRepository.getOne(k.getUser().getId());
            KomentarDTO komentarDTO = KomentarDTO.builder()
                    .id(k.getId())
                    .oglasId(k.getOglas().getId())
                    .odobren(k.isOdobren())
                    .tekst(k.getTekst())
                    .userId(k.getUser().getId())
                    .userUsername(user.getUsername())
                    .build();
            komentariDTO.add(komentarDTO);
        }

        for(KomentarDTO k: komentariDTOSoap){
            if(!komentariDTO.contains(k)){
                komentariDTO.add(k);
            }
        }
        return  new ResponseEntity<>(komentariDTO, HttpStatus.OK);
    }

    public ResponseEntity<?> pokupiSveKomentare() {
        List<Komentar> komentari = this.komentarRepository.findAllByOdobren(false);
        List<KomentarDTO> komentariDTO = new ArrayList<>();

        for(Komentar k: komentari){
            User user = this.userRepository.getOne(k.getUser().getId());
            KomentarDTO komentarDTO = KomentarDTO.builder()
                    .id(k.getId())
                    .oglasId(k.getOglas().getId())
                    .odobren(k.isOdobren())
                    .tekst(k.getTekst())
                    .userId(k.getUser().getId())
                    .userUsername(user.getUsername())
                    .build();
            komentariDTO.add(komentarDTO);
        }
        return  new ResponseEntity<>(komentariDTO, HttpStatus.OK);
    }

    public ResponseEntity<?> odobriKomentar(Boolean flag, Long id) {
        if(flag == true){
            Komentar komentar = this.komentarRepository.getOne(id);
            komentar.setOdobren(true);
            this.komentarRepository.save(komentar);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            this.komentarRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public ResponseEntity<?> provjeriKomentar(String username, Long id) {
        User user = this.userRepository.findByUsername(username);
        Komentar komentarprovjera = this.komentarRepository.findByUserIdAndOglasId(user.getId(), id);

        if (komentarprovjera != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
