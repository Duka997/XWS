package xml.team7.zahtjevservice.service;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.CjenovnikDTO;
import xml.team7.zahtjevservice.dto.OglasDTO;
import xml.team7.zahtjevservice.dto.OglasUKorpiDTO;
import xml.team7.zahtjevservice.dto.VoziloDTO;
import xml.team7.zahtjevservice.exception.NotFoundException;
import xml.team7.zahtjevservice.model.*;
import xml.team7.zahtjevservice.repository.CjenovnikRepository;
import xml.team7.zahtjevservice.repository.OglasRepository;
import xml.team7.zahtjevservice.repository.OglasUKorpiRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private OglasUKorpiRepository oglasUKorpiRepository;

    private ModelMapper modelMapper;

    @Autowired
    private VoziloService voziloService;


    @Autowired
    private CjenovnikRepository cjenovnikRepository;





    public Oglas findById(Long id) {
        return this.oglasRepository.getOne(id);
    }

    public List<Oglas> findAll() throws AccessDeniedException {
        List<Oglas> result = oglasRepository.findAll();
        return result;
    }

    public ResponseEntity<?> pretraziMjesta() {
        List<String> mjesta = new ArrayList<>();
        List<Oglas> oglasi = this.oglasRepository.findAll();

        for(Oglas o: oglasi){
            if(mjesta.contains(o.getMjestoPreuzimanja()))
                continue;

            mjesta.add(o.getMjestoPreuzimanja());
        }

        return new ResponseEntity<>(mjesta, HttpStatus.OK);
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
        oglasUKorpi.setOd(oglasUKorpiDTO.getOd());
        oglasUKorpi.setDoo(oglasUKorpiDTO.getDoo());
        oglasUKorpi.setUserId(oglasUKorpiDTO.getUserId());
        oglasUKorpi.setUserCartId(userId); //oglas se nalazi kod ovog usera u korpi
        oglasUKorpi.setVozilo(new Vozilo(oglasUKorpiDTO.getVozilo()));

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

    public Sort sortBy(String sort){
        if(sort.equals("Cijena rastuce")){
            return Sort.by(Sort.Direction.ASC, "cjenovnik.cijenaPoDanu");
        }else if(sort.equals("Cijena opadajuce")){
            return Sort.by(Sort.Direction.DESC, "cjenovnik.cijenaPoDanu");
        }else if(sort.equals("Kilometraza rastuce")){
            return Sort.by(Sort.Direction.ASC, "vozilo.kilometraza");
        }else if(sort.equals("Kilometraza opadajuce")){
            return Sort.by(Sort.Direction.DESC, "vozilo.kilometraza");
        }else if(sort.equals("Ocjena rastuce")){
            return Sort.by(Sort.Direction.ASC, "vozilo.ocjena");
        }else if(sort.equals("Ocjena opadajuce")){
            return Sort.by(Sort.Direction.DESC, "vozilo.ocjena");
        }
        return Sort.by(Sort.Direction.ASC, "od");
    }

    public ResponseEntity<?> getOneOglasById(Long id) {
        Oglas oglas = this.oglasRepository.getOne(id);
        OglasDTO oglasDTO = new OglasDTO();
        oglasDTO.setOd(oglas.getOd());
        oglasDTO.setDoo(oglas.getDoo());
        oglasDTO.setMestoPreuzimanja(oglas.getMjestoPreuzimanja());
        oglasDTO.setCjenovnik(modelMapper.map(oglas.getCjenovnik(), CjenovnikDTO.class));
        oglasDTO.setDozvoljenaKilometraza(oglas.getDozvoljenaKilometraza());
        oglasDTO.setId(oglas.getId());

        VoziloDTO voziloDTO = modelMapper.map(oglas.getVozilo(), VoziloDTO.class);
        voziloDTO.setOcjena(oglas.getVozilo().getOcjena());

        oglasDTO.setVozilo(voziloDTO);

        return new ResponseEntity<>(oglasDTO, HttpStatus.OK);
    }

    public Oglas getCar(Long ad_id) {
        if (ad_id == null) ad_id = 1L;
        Oglas ad = oglasRepository.findById(ad_id).orElseThrow(() -> new NotFoundException("Ad with given id was not found"));

        return  ad;
    }
}
