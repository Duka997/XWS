package xml.team7.voziloservice.service;



import org.bouncycastle.util.encoders.Base64;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.dto.MarkaAutomobilaDTO;
import xml.team7.voziloservice.dto.VoziloDTO;
import xml.team7.voziloservice.generated.PostVoziloResponse;
import xml.team7.voziloservice.generated.TMarkaAutomobila;
import xml.team7.voziloservice.generated.TVozilo;
import xml.team7.voziloservice.model.*;
import xml.team7.voziloservice.repository.UserRepository;
import xml.team7.voziloservice.repository.VoziloRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VoziloService {

    @Autowired
    private VoziloRepository voziloRepository;

    @Autowired
    private MarkaAutomobilaService markaAutomobilaService;

    @Autowired
    private TipGorivaService vrstaGorivaService;

    @Autowired
    private KlasaAutomobilaService klasaAutomobilaService;

    @Autowired
    private  TipMjenjacaService tipMjenjacaService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    public List<Vozilo> findAll() {
        List<Vozilo> result = voziloRepository.findAll();
        return result;
    }

    public ResponseEntity<?> findAllVozila(){

        List<Vozilo> vozila = this.voziloRepository.findAll();
        List<VoziloDTO> vozilaDTO = new ArrayList<>();

        for(Vozilo v: vozila){
            VoziloDTO voziloDTO = modelMapper.map(v, VoziloDTO.class);
            vozilaDTO.add(voziloDTO);
        }


        return new ResponseEntity<>(vozilaDTO, HttpStatus.OK);
    }

    public Vozilo findById(Long id) throws AccessDeniedException {
        Vozilo u = voziloRepository.findById(id).orElseGet(null);
        return u;
    }

    public Vozilo dodajNovoVozilo(VoziloDTO carDTO, Long agentId) throws SQLException, java.nio.file.AccessDeniedException, NotFoundException {

        System.out.println("marka "+carDTO.getMarkaAutomobila().getId());
        MarkaAutomobila markaAutomobilak = this.markaAutomobilaService.findById(carDTO.getMarkaAutomobila().getId());

        System.out.println("gorivo"+carDTO.getTipGoriva().getId() + markaAutomobilak.getNazivMarke());
        TipGoriva gorivo = this.vrstaGorivaService.findById(carDTO.getTipGoriva().getId());

        System.out.println("klasa"+carDTO.getKlasaAutomobila().getId() + gorivo.getNaziv());
        KlasaAutomobila klasaAutomobila = this.klasaAutomobilaService.findById(carDTO.getKlasaAutomobila().getId());

        System.out.println("mjenjac"+carDTO.getTipMjenjaca().getId() + klasaAutomobila.getNaziv());
        TipMjenjaca tipMjenjaca = this.tipMjenjacaService.findById(carDTO.getTipMjenjaca().getId());
        System.out.println("mjenjac"+ tipMjenjaca.getNaziv());

        System.out.println(markaAutomobilak.getId()+gorivo.getId()+klasaAutomobila.getId()+tipMjenjaca.getId());

        Set<Slika> slike = new HashSet<>();
        for (String slika: carDTO.getSlike()) {
            Slika pom = extractImage(slika);
            slike.add(pom);
        }

        Vozilo vozilo = new Vozilo();
        User user = userRepository.findById(agentId).orElseThrow(() -> new NotFoundException("User with given id was not found."));
        vozilo.setCijena(carDTO.getCijena());
        vozilo.setKilometraza(carDTO.getKilometraza());
        vozilo.setMozePreciKM(carDTO.getMozePreciKM());
        vozilo.setBrSjedistaZaDjecu(carDTO.getBrSjedistaZaDjecu());
        vozilo.setOcjena(carDTO.getOcjena());
        vozilo.setMarkaAutomobila(markaAutomobilak);
        vozilo.setTipMjenjaca(tipMjenjaca);
        vozilo.setKlasaAutomobila(klasaAutomobila);
        vozilo.setTipGoriva(gorivo);
        vozilo.setImaAndroid(carDTO.getImaAndroid());
        vozilo.setColiisionDamageWavier(carDTO.isColiisionDamageWavier());
        vozilo.setSlike(slike);
        vozilo.setUser(user);
        vozilo = this.voziloRepository.save(vozilo);
        return vozilo;
    }
    public Slika extractImage(String string) throws SQLException {
        String[] parts = string.split(",");
        byte[] decodedByte = Base64.decode(parts[1]);
        String[] info = parts[0].split("/");
        String type = info[1].split(";")[0];
        Slika myImage = new Slika();
        myImage.setImage(new SerialBlob(decodedByte));
        myImage.setInfo(parts[0]);
        myImage.setTip(type);
        return myImage;
    }


    public ResponseEntity<?> getAllVozila(String username) {
        User user = this.userRepository.findByUsername(username);

        List<Vozilo> vozila = this.voziloRepository.findAllByUserId(user.getId());
        List<VoziloDTO> voziloDTOS = new ArrayList<>();

        for(Vozilo v: vozila){
            VoziloDTO vDTO = new VoziloDTO();
            vDTO.setId(v.getId());

            vDTO.setMarkaAutomobila(modelMapper.map(v.getMarkaAutomobila(), MarkaAutomobilaDTO.class));

            voziloDTOS.add(vDTO);
        }

        return new ResponseEntity<>(voziloDTOS,HttpStatus.OK);

    }


    public Vozilo getVozilo(Long id) throws NotFoundException {
        if (id == null) id = 1L;
        Vozilo vozilo = voziloRepository.findById(id).orElseThrow(() -> new NotFoundException("Car with given id was not found"));

        return vozilo;
    }



}
