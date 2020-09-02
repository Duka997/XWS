package xml.team7.voziloservice.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.dto.KomentarDTO;
import xml.team7.voziloservice.generated.GetCommentResponse;
import xml.team7.voziloservice.generated.PostCommentResponse;
import xml.team7.voziloservice.generated.TComment;
import xml.team7.voziloservice.model.Komentar;
import xml.team7.voziloservice.model.Oglas;
import xml.team7.voziloservice.model.User;
import xml.team7.voziloservice.model.Vozilo;
import xml.team7.voziloservice.repository.KomentarRepository;
import xml.team7.voziloservice.repository.OglasRepository;
import xml.team7.voziloservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class KomentarService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PostCommentResponse postCommentSoap(TComment commentRequest) {
        log.info("Comment service - creating comment");
        Komentar komentar = Komentar.builder()
                .odobren(false)
                .tekst(commentRequest.getText())
                .build();
        User user = this.userRepository.findByUsername(commentRequest.getUserUsername());
        Oglas oglas = this.oglasRepository.getOne(commentRequest.getAdId());
        Vozilo vozilo = oglas.getVozilo();

        komentar.setVozilo(vozilo);
        komentar.setOglas(oglas);
        komentar.setUser(user);

        komentar = this.komentarRepository.save(komentar);
        log.info("Comment service - comment created");

        PostCommentResponse response = new PostCommentResponse();
        response.setCommentResponse(komentar.getId());
        return response;
    }

    public GetCommentResponse getCommentsSoap(long commentRequest) {
        Oglas oglas = oglasRepository.getOne(commentRequest);
        Vozilo vozilo = oglas.getVozilo();

        List<Komentar> komentars = this.komentarRepository.findByVoziloIdAndOdobren(vozilo.getId(), true);
        List<KomentarDTO> komentarDTOS = transformComments(komentars);

        GetCommentResponse response = new GetCommentResponse();
        for(KomentarDTO c: komentarDTOS){
            response.getComments().add(modelMapper.map(c, TComment.class));
        }
        log.info("Comment service - comment retrived");
        return response;
    }

    public List<KomentarDTO> transformComments(List<Komentar> komentars){
        List<KomentarDTO> komentarDTOS = new ArrayList<>();
        log.info("Comment service - creating dto for comments");
        for (Komentar komentar: komentars){
            KomentarDTO komentarDTO = KomentarDTO.builder()
                    .odobren(komentar.isOdobren())
                    .id(komentar.getId())
                    .tekst(komentar.getTekst())
                    .voziloId(komentar.getVozilo().getId())
                    .oglasId(komentar.getOglas().getId())
                    .userUsername(komentar.getUser().getUsername())
                    .build();
            komentarDTOS.add(komentarDTO);
        }

        return komentarDTOS;
    }

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
