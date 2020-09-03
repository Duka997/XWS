package xml.team7.zahtjevservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.OglasDTO;
import xml.team7.zahtjevservice.dto.OglasUKorpiDTO;
import xml.team7.zahtjevservice.exception.NotFoundException;
import xml.team7.zahtjevservice.model.Oglas;
import xml.team7.zahtjevservice.model.OglasUKorpi;
import xml.team7.zahtjevservice.repository.OglasRepository;
import xml.team7.zahtjevservice.repository.OglasUKorpiRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private OglasUKorpiRepository oglasUKorpiRepository;

    public Oglas findById(Long id) {
        return this.oglasRepository.getOne(id);
    }

    public List<Oglas> findAll() throws AccessDeniedException {
        List<Oglas> result = oglasRepository.findAll();
        return result;
    }

    public ResponseEntity<?> getAds() {
        List<Oglas> oglasi = this.oglasRepository.findAll();
        List<OglasDTO> oglasiDTOS = new ArrayList<>();
        for(Oglas o: oglasi){
            //OglasDTO oglasDTO = modelMapper.map(o, OglasDTO.class);
            OglasDTO oglasDTO = new OglasDTO(o);
            oglasiDTOS.add(oglasDTO);
        }
        return new ResponseEntity<>(oglasiDTOS, HttpStatus.OK);
    }

    public OglasUKorpi saveInCart(OglasUKorpiDTO oglasUKorpiDTO, Long userId) {
        OglasUKorpi oglasUKorpi = new OglasUKorpi();
        oglasUKorpi.setAdId(oglasUKorpiDTO.getId());
        oglasUKorpi.setUserId(oglasUKorpiDTO.getUserId());
        oglasUKorpi.setUserCartId(userId); //oglas se nalazi kod ovog usera u korpi

        oglasUKorpi = this.oglasUKorpiRepository.save(oglasUKorpi);
        return oglasUKorpi;
    }

    public ResponseEntity<?> getCartAds(Long userId) {
        List<OglasUKorpi> oglasi = this.oglasUKorpiRepository.findAll();
        List<OglasUKorpiDTO> oglasiDTOS = new ArrayList<>();
        for(OglasUKorpi o: oglasi){
            //OglasDTO oglasDTO = modelMapper.map(o, OglasDTO.class);
            OglasUKorpiDTO oglasDTO = new OglasUKorpiDTO(o);
            if(o.getUserCartId() == userId)
                oglasiDTOS.add(oglasDTO);
        }
        return new ResponseEntity<>(oglasiDTOS, HttpStatus.OK);
    }

    public ResponseEntity<?> removeAdFromCart(Long oglasId) {
        OglasUKorpi oglasUKorpi = this.oglasUKorpiRepository.findById(oglasId).orElseThrow(() -> new NotFoundException("Ad with given id was not found"));
        this.oglasUKorpiRepository.deleteById(oglasId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Oglas getAdById(Long adId) {
        return this.oglasRepository.findById(adId).orElseThrow(() -> new NotFoundException("Ad with given id was not found."));
    }

    public Oglas getCar(Long ad_id) {
        if (ad_id == null) ad_id = 1L;
        Oglas ad = oglasRepository.findById(ad_id).orElseThrow(() -> new NotFoundException("Ad with given id was not found"));

        return  ad;
    }
}
