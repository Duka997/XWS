package xml.team7.voziloservice.service;

import feign.FeignException;
import javassist.NotFoundException;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.client.RequestClient;
import xml.team7.voziloservice.dto.VoziloDTO;
import xml.team7.voziloservice.dto.ZauzetDTO;
import xml.team7.voziloservice.generated.PostOccupiedRequest;
import xml.team7.voziloservice.generated.TOccupied;
import xml.team7.voziloservice.model.Oglas;
import xml.team7.voziloservice.model.Vozilo;
import xml.team7.voziloservice.model.ZahtjevZaIznajmljivanje;
import xml.team7.voziloservice.model.Zauzet;
import xml.team7.voziloservice.repository.OglasRepository;
import xml.team7.voziloservice.repository.ZauzetRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZauzetService {

    @Autowired
    private ZauzetRepository zauzetRepository;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RequestClient requestClient;

    @Autowired
    private OglasRepository oglasRepository;

    public void saveRequestAsOccupied(ZahtjevZaIznajmljivanje request) {
        Zauzet occupied = new Zauzet();
        occupied.setVozilo(request.getOglas().getVozilo());
        occupied.setOd(request.getOd());
        occupied.setDoo(request.getDoo());
        this.zauzetRepository.save(occupied);
    }

    public ResponseEntity<Void> newOccupied(ZauzetDTO occupiedDTO) throws NotFoundException {
        if(occupiedDTO.getOd().isAfter(occupiedDTO.getDoo()) || occupiedDTO.getOd().isBefore(DateTime.now())){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Zauzet> occupiedss = this.zauzetRepository.findAllByVoziloId(occupiedDTO.getVoziloId());
        boolean flag = checkOccupied(occupiedss, occupiedDTO);
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

        try {
            this.requestClient.getRequestsHistory(occupiedDTO);
        } catch (FeignException.NotFound e) {
        }

        occupied = this.zauzetRepository.save(occupied);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public long newOccupied(PostOccupiedRequest postOccupiedRequest) throws NotFoundException {
        TOccupied tOccupied = postOccupiedRequest.getOccupiedRequest();
        Zauzet occupied = new Zauzet();
        occupied.setOd(new DateTime(tOccupied.getDateFrom()));
        occupied.setDoo(new DateTime(tOccupied.getDateTo()));

        Vozilo car = voziloService.getVozilo(tOccupied.getCarId());
        occupied.setVozilo(car);

        List<Long> adsId = new ArrayList<>();
        List<Oglas> ads = this.oglasRepository.findAllByVoziloId(car.getId());
        for (Oglas a: ads){
            tOccupied.getAdsId().add(a.getId());
        }

        ZauzetDTO occupiedDTO = new ZauzetDTO();
        occupiedDTO.setOglasId(tOccupied.getAdsId());
        occupiedDTO.setDoo(occupied.getDoo());
        occupiedDTO.setOd(occupied.getOd());

        try {
            this.requestClient.getRequestsHistory(occupiedDTO);
        } catch (FeignException.NotFound e) {
        }
        occupied = this.zauzetRepository.save(occupied);

        return occupied.getId();
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
