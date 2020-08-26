package com.demo.service;

import com.demo.dto.OglasDTO;
import com.demo.model.Cjenovnik;
import com.demo.model.Oglas;
import com.demo.model.User;
import com.demo.model.Vozilo;
import com.demo.repository.CjenovnikRepository;
import com.demo.repository.OglasRepository;
import com.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
       // Cjenovnik cjenovnik = this.cjenovnikRepository.getOne(oglasDTO.getCjenovnikID());
        //User user = userService.getUserById(oglasDTO.getUserId());

     //   Agent pom = agentService.findById(oglasDTO.getUserId());
        //if(user.getImeKompanije() == null || user.getImeKompanije().equals("")){
            System.out.println("null - agent je");
           // if(user.getOglasi().size()>2){
            //    return new ResponseEntity<>(HttpStatus.valueOf("Korisnik ne moze dodati vise od 3 oglasa"));
           // }
       // }

        Oglas oglas = new Oglas();
        oglas.setVozilo(vozilo);
        oglas.setDostupan(oglasDTO.isDostupan());
        oglas.setOd(oglasDTO.getOd());
        oglas.setDoo(oglasDTO.getDoo());
        oglas.setMjestoPreuzimanja(oglasDTO.getMestoPreuzimanja());
       // oglas.setUser(user);
       // oglas.setCjenovnik(cjenovnik);

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
}
