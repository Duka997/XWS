package xml.team7.voziloservice.service;


import javassist.NotFoundException;
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
import xml.team7.voziloservice.client.PretragaClient;
import xml.team7.voziloservice.dto.OglasDTO;
import xml.team7.voziloservice.model.Cjenovnik;
import xml.team7.voziloservice.model.Oglas;
import xml.team7.voziloservice.model.User;
import xml.team7.voziloservice.model.Vozilo;
import xml.team7.voziloservice.repository.CjenovnikRepository;
import xml.team7.voziloservice.repository.OglasRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private UserService userService;


    @Autowired
    private CjenovnikRepository cjenovnikRepository;

    @Autowired
    private PretragaClient pretragaClient;





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
        if(user.getOglasi().size()>2){
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


        this.oglasRepository.save(oglas);
        oglasDTO.setId(oglas.getId());
        this.pretragaClient.addNewOglas(oglasDTO);

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




    public Oglas getAdById(Long adId) throws NotFoundException {
        return this.oglasRepository.findById(adId).orElseThrow(() -> new NotFoundException("Ad with given id was not found."));
    }




    public Oglas getCar(Long ad_id) throws NotFoundException {
        if (ad_id == null) ad_id = 1L;
        Oglas ad = oglasRepository.findById(ad_id).orElseThrow(() -> new NotFoundException("Ad with given id was not found"));

        return  ad;
    }
}
