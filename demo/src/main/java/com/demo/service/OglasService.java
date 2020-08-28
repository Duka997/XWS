package com.demo.service;

import com.demo.dto.*;
import com.demo.dto.OglasDTO;
import com.demo.model.Cjenovnik;
import com.demo.model.Oglas;
import com.demo.model.User;
import com.demo.model.Vozilo;
import com.demo.repository.CjenovnikRepository;
import com.demo.repository.OglasRepository;
import com.demo.repository.UserRepository;
import com.demo.dto.OglasUKorpiDTO;
import com.demo.exception.NotFoundException;
import com.demo.model.*;
import com.demo.repository.OglasRepository;
import com.demo.repository.OglasUKorpiRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private CustomUserDetailsService userService;


    @Autowired
    private CjenovnikRepository cjenovnikRepository;

      @Autowired
    private OglasUKorpiRepository oglasUKorpiRepository;





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


    public ResponseEntity<Void> noviOglas(OglasDTO oglasDTO) {
        Vozilo vozilo = this.voziloService.findById(oglasDTO.getVozilo().getId());
         Cjenovnik cjenovnik = this.cjenovnikRepository.getOne(oglasDTO.getCjenovnikID());
        User user = userService.getUserById(oglasDTO.getUserId());

        //   Agent pom = agentService.findById(oglasDTO.getUserId());
        if(user.getImeKompanije() == null || user.getImeKompanije().equals("")){
        System.out.println("null - agent je");
        if(user.getOcjene().size()>2){
            return new ResponseEntity<>(HttpStatus.valueOf("Korisnik ne moze dodati vise od 3 oglasa"));
         }
         }

        Oglas oglas = new Oglas();
        oglas.setVozilo(vozilo);
        oglas.setDostupan(oglasDTO.isDostupan());
        oglas.setOd(oglasDTO.getOd());
        oglas.setDoo(oglasDTO.getDoo());
        oglas.setMjestoPreuzimanja(oglasDTO.getMestoPreuzimanja());
         oglas.setUser(user);
         oglas.setCjenovnik(cjenovnik);

        //   log.info("Sending soap request to oglas service");
        //   TOglas tOglas = new TOglas();
        //   try {
        //        PostOglasResponse response = this.oglasClient.postOglas(tOglas);
        //        log.info("Soap request successfully finished");
        //      } catch (Exception e) {
        //         e.printStackTrace();
        //     }

        this.oglasRepository.save(oglas);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    public ResponseEntity<?> pretraziOglase(PretragaDTO pretraga, int page, String sort) {
        if(pretraga.getOd().isAfter(pretraga.getDoo()) || pretraga.getOd().isBefore(DateTime.now().plusDays(2))){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<OglasDTO> oglasDTOS = new ArrayList<>();
        String mjesto = pretraga.getMjestoPreuzimanja();
        DateTime od = pretraga.getOd();
        DateTime doo = pretraga.getDoo();
        Long markaId = pretraga.getMarka().getId();
        String model = pretraga.getMarka().getModel();
        Long gorivoId = pretraga.getGorivo().getId();
        Long mjenjacId = pretraga.getMjenjac().getId();
        Long klasaId = pretraga.getKlasa().getId();
        Double cijenaOd = pretraga.getCijenaOd();
        Double cijenaDo = pretraga.getCijenaDo();
        if(cijenaDo == 0)
            cijenaDo = 1000000.0;
        Double kilometrazaDo = pretraga.getKilometrazaDo();
        if (kilometrazaDo == 0)
            kilometrazaDo = 1000000.0;
        Double kilometrazaOd = pretraga.getKilometrazaOd();
        Double kilometrazaDozvoljena = pretraga.getKilometrazaDozvoljena();
        Integer brojMjestaDjeca = pretraga.getBrojDjecijihMjesta();
        Boolean cdw = pretraga.getCdw();

        Sort sortby = sortBy(sort);
        Pageable pageable = PageRequest.of(page, 10, sortby);
        Page<Oglas> oglasi = this.oglasRepository.pretraziOglase(mjesto, od, doo, model, markaId, klasaId, gorivoId, mjenjacId, cijenaOd, cijenaDo,
                kilometrazaOd, kilometrazaDo, kilometrazaDozvoljena, cdw, brojMjestaDjeca, pageable);

        for (Oglas o: oglasi){
            OglasDTO oglasDTO = modelMapper.map(o, OglasDTO.class);
            oglasDTO.setPages(oglasi.getTotalPages());
            oglasDTOS.add(oglasDTO);
        }

        return new ResponseEntity<>(oglasDTOS, HttpStatus.OK);
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
}
