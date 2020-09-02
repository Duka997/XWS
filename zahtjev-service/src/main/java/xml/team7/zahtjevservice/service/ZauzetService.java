package xml.team7.zahtjevservice.service;

import xml.team7.zahtjevservice.dto.VoziloDTO;
import xml.team7.zahtjevservice.dto.ZauzetDTO;
import xml.team7.zahtjevservice.model.*;
import xml.team7.zahtjevservice.repository.ZahtjevZaIznajmljivanjeRepository;
import xml.team7.zahtjevservice.repository.ZauzetRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZauzetService {

    @Autowired
    private ZauzetRepository zauzetRepository;

    @Autowired
    private ZahtjevZaIznajmljivanjeRepository zahtjevZaIznajmljivanjeRepository;

    @Autowired
    private VoziloService voziloService;


    private ModelMapper modelMapper;

    @Autowired
    private ZahtjevZaBundleService zahtjevZaBundleService;

    public void saveRequestAsOccupied(ZahtjevZaIznajmljivanje request) {
        Zauzet occupied = new Zauzet();
        occupied.setVozilo(request.getOglas().getVozilo());
        occupied.setOd(request.getOd());
        occupied.setDoo(request.getDoo());
        this.zauzetRepository.save(occupied);
    }

    public ResponseEntity<?> newOccupied(ZauzetDTO occupiedDTO) {
        if(occupiedDTO.getOd().isAfter(occupiedDTO.getDoo()) || occupiedDTO.getOd().isBefore(DateTime.now())){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ZahtjevZaIznajmljivanje> rentRequestss = this.zahtjevZaIznajmljivanjeRepository.findAllByOglasVoziloIdAndStatus(occupiedDTO.getVoziloId(), StatusZahtjeva.PAID);
        List<Zauzet> occupiedss = this.zauzetRepository.findAllByVoziloId(occupiedDTO.getVoziloId());

        boolean flag = checkPaids(rentRequestss, occupiedDTO);

        if(flag == true){
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        flag = checkOccupied(occupiedss, occupiedDTO);

        if(flag == true){
            return  new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Zauzet occupied = new Zauzet();
        occupied.setOd(occupiedDTO.getOd());
        occupied.setDoo(occupiedDTO.getDoo());

        Vozilo car = voziloService.getVozilo(occupiedDTO.getVoziloId());
        occupied.setVozilo(car);
        List<Long> adsId = new ArrayList<>();
        for (Oglas a: car.getOglasi()){
            adsId.add(a.getId());
        }
        occupiedDTO.setOglasId(adsId);

        occupied = this.zauzetRepository.save(occupied);

        DateTime occupiedFrom = occupiedDTO.getOd();
        DateTime occupiedTo = occupiedDTO.getDoo();

        for (Long id: occupiedDTO.getOglasId()){
            List<ZahtjevZaIznajmljivanje> rentRequests = this.zahtjevZaIznajmljivanjeRepository.findByOglasId(id);
            for (ZahtjevZaIznajmljivanje rent : rentRequests){
                DateTime rentFrom = rent.getOd();
                DateTime rentTo = rent.getDoo();
                if(rent.getStatus().equals(StatusZahtjeva.PENDING)) {
                    if (!rentFrom.isBefore(occupiedFrom) && !rentTo.isAfter(occupiedTo)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        if(rent.getBundle() != null){
                            this.zahtjevZaBundleService.cancelBundle(rent.getBundle().getId());
                        }
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                    if (!rentFrom.isAfter(occupiedFrom) && !rentTo.isBefore(occupiedTo)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        if(rent.getBundle() != null){
                            this.zahtjevZaBundleService.cancelBundle(rent.getBundle().getId());
                        }
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                    if (!rentFrom.isAfter(occupiedFrom) && !rentTo.isAfter(occupiedTo) && !rentTo.isBefore(occupiedFrom)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        if(rent.getBundle() != null){
                            this.zahtjevZaBundleService.cancelBundle(rent.getBundle().getId());
                        }
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                    if (!rentFrom.isBefore(occupiedFrom) && !rentTo.isBefore(occupiedTo) && !rentFrom.isAfter(occupiedTo)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        if(rent.getBundle() != null){
                            this.zahtjevZaBundleService.cancelBundle(rent.getBundle().getId());
                        }
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public boolean checkPaids(List<ZahtjevZaIznajmljivanje> rentRequests, ZauzetDTO occupiedDTO){
        DateTime occupiedFrom = occupiedDTO.getOd();
        DateTime occupiedTo = occupiedDTO.getDoo();

        for(ZahtjevZaIznajmljivanje r: rentRequests){
            DateTime rentFrom = r.getOd();
            DateTime rentTo = r.getDoo();
            if(!rentFrom.isBefore(occupiedFrom) && !rentTo.isAfter(occupiedTo))
                return true;
            if(!rentFrom.isAfter(occupiedFrom) && !rentTo.isBefore(occupiedTo))
                return true;
            if(!rentFrom.isAfter(occupiedFrom) && !rentTo.isAfter(occupiedTo) && !rentTo.isBefore(occupiedFrom))
                return true;
            if(!rentFrom.isBefore(occupiedFrom) && !rentTo.isBefore(occupiedTo) && !rentFrom.isAfter(occupiedTo))
                return true;
        }
        return false;
    }

    public boolean checkOccupied(List<Zauzet> occupieds, ZauzetDTO occupiedDTO){
        DateTime occupiedFrom = occupiedDTO.getOd();
        DateTime occupiedTo = occupiedDTO.getDoo();

        for(Zauzet o: occupieds){
            DateTime rentFrom = o.getOd();
            DateTime rentTo = o.getDoo();
            if(!rentFrom.isBefore(occupiedFrom) && !rentTo.isAfter(occupiedTo))
                return true;
            if(!rentFrom.isAfter(occupiedFrom) && !rentTo.isBefore(occupiedTo))
                return true;
            if(!rentFrom.isAfter(occupiedFrom) && !rentTo.isAfter(occupiedTo) && !rentTo.isBefore(occupiedFrom))
                return true;
            if(!rentFrom.isBefore(occupiedFrom) && !rentTo.isBefore(occupiedTo) && !rentFrom.isAfter(occupiedTo))
                return true;
        }
        return false;
    }

    public ResponseEntity<?> getOccupied(Long id) {
        Zauzet occupied = zauzetRepository.getOne(id);
        ZauzetDTO occupiedDTO = new ZauzetDTO();
        occupiedDTO.getDoo(occupied.getDoo());
        occupiedDTO.getOd(occupied.getOd());
        occupiedDTO.setVoziloId(occupied.getVozilo().getId());
        occupiedDTO.setId(occupied.getId());

        return new ResponseEntity<>(occupiedDTO, HttpStatus.FOUND);
    }

    public ResponseEntity<?> getOccupiedByUser(Long userId) {
        List<Vozilo> cars = this.voziloService.getVoziloByUserId(userId);
        List<ZauzetDTO> occupiedDTOS = new ArrayList<>();
        for(Vozilo c: cars){
            List<Zauzet> occupieds = this.zauzetRepository.findAllByVoziloAndDooAfter(c, DateTime.now());
            for(Zauzet o: occupieds){
                ZauzetDTO occupiedDTO = new ZauzetDTO();
                occupiedDTO.setVozilo(modelMapper.map(o.getVozilo(), VoziloDTO.class));
                occupiedDTO.setOd(o.getOd());
                occupiedDTO.setDoo(o.getDoo());
                occupiedDTO.setVoziloId(o.getVozilo().getId());
                occupiedDTOS.add(occupiedDTO);
            }
        }

        return new ResponseEntity<>(occupiedDTOS, HttpStatus.OK);
    }
}
