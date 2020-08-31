package com.demo.service;

import com.demo.dto.KomentarDTO;
import com.demo.dto.VoziloDTO;
import com.demo.model.Komentar;
import com.demo.model.Oglas;
import com.demo.model.User;
import com.demo.model.Vozilo;
import com.demo.repository.KomentarRepository;
import com.demo.repository.OglasRepository;
import com.demo.repository.UserRepository;
import com.demo.repository.VoziloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> kreirajKomentar(KomentarDTO komentarDTO, Boolean odgovor) {
        Komentar komentar = new Komentar();
        komentar.setOdobren(false);
        komentar.setTekst(komentarDTO.getTekst());

        User user = this.userRepository.findByUsername(komentarDTO.getUserUsername());
        Oglas oglas = this.oglasRepository.getOne(komentarDTO.getOglasId());
        Vozilo vozilo = oglas.getVozilo();
        komentar.setVozilo(vozilo);
        komentar.setOglas(oglas);
        komentar.setUser(user);

        if(komentarDTO.getRole().equals("ROLE_USER") && odgovor == false) {
            Komentar komentarProvjera = this.komentarRepository.findByUserIdAndOglasId(user.getId(), oglas.getId());
            if (komentarProvjera != null) {
                return new ResponseEntity<>("User already add comment for this car", HttpStatus.BAD_REQUEST);
            }
        }
        
        this.komentarRepository.save(komentar);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    public ResponseEntity<?> pokupiKomentare(Long id) {
        List<Komentar> komentari = this.komentarRepository.findAllByVoziloIdAndOdobren(id, true);
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
