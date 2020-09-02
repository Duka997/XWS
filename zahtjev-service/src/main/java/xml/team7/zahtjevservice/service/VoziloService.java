package xml.team7.zahtjevservice.service;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.dto.KlasaAutomobilaDTO;
import xml.team7.zahtjevservice.dto.MarkaAutomobilaDTO;
import xml.team7.zahtjevservice.dto.TipGorivaDTO;
import xml.team7.zahtjevservice.dto.VoziloDTO;
import xml.team7.zahtjevservice.exception.NotFoundException;
import xml.team7.zahtjevservice.model.*;
import xml.team7.zahtjevservice.repository.UserRepository;
import xml.team7.zahtjevservice.repository.VoziloRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.file.AccessDeniedException;
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

    public Vozilo dodajNovoVozilo(VoziloDTO carDTO, Long agentId) throws SQLException, Base64DecodingException, java.nio.file.AccessDeniedException {

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
    public Slika extractImage(String string) throws SQLException, Base64DecodingException {
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


    public Vozilo getCarWithHighestGradeByOwnersId(Set<Vozilo> cars){
        /* Returns null if all cars have 0 grades. */

        float maxAverageGrade = 0;
        Vozilo carWithBHighestAverageGrade = new Vozilo();

        for(Vozilo c : cars){
            if (getAverageGrade(c) == null)
                continue;

            if (getAverageGrade(c) > maxAverageGrade){
                maxAverageGrade = getAverageGrade(c);
                carWithBHighestAverageGrade = c;
            }
        }

        if (maxAverageGrade == 0)
            return null;

        return carWithBHighestAverageGrade;
    }

    public Float getAverageGrade(Vozilo car){
        /* Returns null if car has no grades. */
        /* Returns null if car equals to null. */

        if (car == null)
            return null;

        int sum = 0;
        for (Ocjena g : car.getOcjene()){
            sum += g.getOcjena();
        }

        if (sum == 0) {
            return null;
        } else {
            Float averageGrade = new Float(0);
            averageGrade = (float) sum / car.getOcjene().size();
            return averageGrade;
        }
    }

    public Vozilo getCarWithMostCommentsByOwnersId(Set<Vozilo> cars){
        /* Returns null if all cars have 0 comments. */
        int maxComments = 0;
        Vozilo carWithMostComments = new Vozilo();

        for(Vozilo c : cars){
            if (c.getKomentari().size() > maxComments){
                maxComments = c.getKomentari().size();
                carWithMostComments = c;
            }
        }

        if (maxComments == 0)
            return null;

        return carWithMostComments;
    }

    public Vozilo getCarWithMostKilometersByOwnersId(Set<Vozilo> cars){
        /* Returns null if all cars have kilometrage equal to 0. */

        double mostKilometers = 0;
        Vozilo carWithMostKilometers = new Vozilo();

        for (Vozilo c : cars){
            if (c.getKilometraza() > mostKilometers){
                mostKilometers = c.getKilometraza();
                carWithMostKilometers = c;
            }
        }

        if (mostKilometers == 0)
            return null;

        return carWithMostKilometers;
    }

    public Vozilo getVozilo(Long id) {
        if (id == null) id = 1L;
        Vozilo vozilo = voziloRepository.findById(id).orElseThrow(() -> new NotFoundException("Car with given id was not found"));

        return vozilo;
    }

    public List<Vozilo> getVoziloByUserId(Long id) {
        User user = this.userService.getUserById(id);
        List<Vozilo> vozila = this.voziloRepository.findAllByUserId(user.getId());
        return vozila;
    }

    public ResponseEntity<?> findAllVozilaOfUser(Long userId) {
        User user = this.userService.getUserById(userId);
        List<Vozilo> cars = this.voziloRepository.findAllByUserId(user.getId());
        List<VoziloDTO> carInfoDTOS = new ArrayList<>();

        for (Vozilo car : cars) {
            VoziloDTO carInfoDTO = new VoziloDTO();
            carInfoDTO.setId(car.getId());
            carInfoDTO.setTipGoriva(new TipGorivaDTO(car.getTipGoriva()));
            MarkaAutomobilaDTO modelDTO = new MarkaAutomobilaDTO();
            modelDTO.setId(car.getMarkaAutomobila().getId());
            modelDTO.setNazivMarke(car.getMarkaAutomobila().getNazivMarke());
            modelDTO.setModel(car.getMarkaAutomobila().getModel());
            KlasaAutomobilaDTO markDTO = new KlasaAutomobilaDTO();
            markDTO.setId(car.getKlasaAutomobila().getId());
            markDTO.setNaziv(car.getKlasaAutomobila().getNaziv());
            carInfoDTO.setMarkaAutomobila(modelDTO);
            carInfoDTO.setKlasaAutomobila(markDTO);
            carInfoDTOS.add(carInfoDTO);
        }
        return new ResponseEntity<>(carInfoDTOS, HttpStatus.OK);
    }

    public Vozilo updateCarsKilometrage(Vozilo car, double newKilometers){
        car.setKilometraza(car.getKilometraza() + newKilometers);
        return car;
    }
}
