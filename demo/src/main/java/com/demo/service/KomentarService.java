package com.demo.service;

import com.demo.dto.KomentarDTO;
import com.demo.dto.VoziloDTO;
import com.demo.model.Komentar;
import com.demo.model.User;
import com.demo.model.Vozilo;
import com.demo.repository.KomentarRepository;
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

    public ResponseEntity<?> kreirajKomentar(KomentarDTO komentarDTO, String username) {
        Komentar komentar = new Komentar();
        komentar.setOdobren(false);
        komentar.setTekst(komentarDTO.getTekst());

        User user = this.userRepository.findByUsername(username);
        komentar.setUser(user);

        Vozilo vozilo = this.voziloRepository.getOne(komentarDTO.getVozilo().getId());
        komentar.setVozilo(vozilo);

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
                    .odobren(k.isOdobren())
                    .tekst(k.getTekst())
                    .userId(k.getUser().getId())
                    .userUsername(user.getUsername())
                    .vozilo(new VoziloDTO(k.getVozilo()))
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
                    .odobren(k.isOdobren())
                    .tekst(k.getTekst())
                    .userId(k.getUser().getId())
                    .userUsername(user.getUsername())
                    .vozilo(new VoziloDTO(k.getVozilo()))
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
}
