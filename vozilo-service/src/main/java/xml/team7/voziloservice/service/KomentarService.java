package xml.team7.voziloservice.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        /*User user = this.userRepository.findByUsername(commentRequest.getUserUsername());
        Oglas oglas = this.oglasRepository.getOne(commentRequest.getAdId());
        Vozilo vozilo = oglas.getVozilo();

        komentar.setVozilo(vozilo);
        komentar.setOglas(oglas);
        komentar.setUser(user);*/

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
}
