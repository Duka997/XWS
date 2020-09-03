package xml.team7.zahtjevservice.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xml.team7.zahtjevservice.client.VoziloClient;
import xml.team7.zahtjevservice.dto.*;
import xml.team7.zahtjevservice.exception.InvalidOperationException;
import xml.team7.zahtjevservice.exception.NotFoundException;
import xml.team7.zahtjevservice.model.*;
import xml.team7.zahtjevservice.repository.UserRepository;
import xml.team7.zahtjevservice.repository.ZahtjevZaIznajmljivanjeRepository;

import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class ZahtjevZaIznajmljivanjeService {


    private ZahtjevZaIznajmljivanjeRepository zahtjevZaIznajmljivanjeRepository;
    private OglasService oglasService;
    private UserService userService;

    @Autowired
    private ZahtjevZaBundleService zahtjevZaBundleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoziloClient carClient;

    @Autowired
    public ZahtjevZaIznajmljivanjeService(ZahtjevZaIznajmljivanjeRepository rentRequestRepository, OglasService adService,
                              UserService userService) {
        this.zahtjevZaIznajmljivanjeRepository = rentRequestRepository;
        this.oglasService = adService;
        this.userService = userService;
    }

    public List<ZahtjevDTO> getRequestDTOS(List<ZahtjevZaIznajmljivanje> requests) {
        List<ZahtjevDTO> retVal = new ArrayList<>();
        for (ZahtjevZaIznajmljivanje request : requests) {
            retVal.add(ZahtjevDTO.builder()
                    .id(request.getId())
                    .doo(request.getDoo())
                    .od(request.getOd())
                    .mjestoPreuzimanja(request.getMjestoPreuzimanja())
                    .oglasId(request.getOglas().getId())
                    .oglas(new OglasDTO(request.getOglas()))
                    .status(String.valueOf(request.getStatus()))
                    .userId(request.getUser().getId())
                    .user(new UserDTO(request.getUser()))
                    .bundleId(request.getBundle() != null ? request.getBundle().getId() : -1)
                    .build());
        }
        return retVal;
    }

    public ResponseEntity<?> getAll(Long userId) {
        //User user = this.userDetailsService.getLoggedInUser();
        User user = this.userRepository.findUserById(userId);
        List<ZahtjevZaIznajmljivanje> requests = this.zahtjevZaIznajmljivanjeRepository.findAllByOglas_User_Id(userId);

        List<ZahtjevDTO> retVal = getRequestDTOS(requests);
        SviZahtjeviDTO requestsDTO = this.filterRequests(retVal);

        return new ResponseEntity<>(requestsDTO, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllHistory(Long userId) {
        //User user = this.userDetailsService.getLoggedInUser();
        User user = this.userRepository.findUserById(userId);
        List<ZahtjevZaIznajmljivanje> requests = this.zahtjevZaIznajmljivanjeRepository.findAllByUserId(userId);

        List<ZahtjevDTO> retVal = getRequestDTOS(requests);
        SviZahtjeviDTO requestsDTO = this.filterRequests(retVal);

        return new ResponseEntity<>(requestsDTO, HttpStatus.OK);
    }

    public SviZahtjeviDTO filterRequests(List<ZahtjevDTO> retVal) {
        SviZahtjeviDTO requestsDTO = new SviZahtjeviDTO();
        List<ZahtjevDTO> pending = new ArrayList<>();
        List<ZahtjevDTO> paid = new ArrayList<>();
        List<ZahtjevDTO> finished = new ArrayList<>();
        retVal.forEach(req -> {
            if (req.getStatus().equals(StatusZahtjeva.PENDING.toString())) {
                pending.add(req);
            } else if (req.getStatus().equals(StatusZahtjeva.PAID.toString())) {
                if (req.getDoo().isBefore(new DateTime())) {
                    finished.add(req);
                } else {
                    paid.add(req);
                }
            }
        });

        requestsDTO.setAll(retVal);
        requestsDTO.setPending(pending);
        requestsDTO.setPaid(paid);
        requestsDTO.setFinished(finished);
        return requestsDTO;
    }

    public ResponseEntity<?> newRequests(KorpaDTO shoppingCart, Long userId) {
        this.zahtjevZaBundleService.newBundles(shoppingCart.getBundles(), userId);

        for (ZahtjevDTO request : shoppingCart.getRequests()) {
            ZahtjevZaIznajmljivanje rentRequest = newRequest(request, null, userId);
            rentRequest = this.zahtjevZaIznajmljivanjeRepository.save(rentRequest);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ZahtjevZaIznajmljivanje newRequest(ZahtjevDTO request, BundleZahtjev bundle, Long userId) {
        Oglas ad = this.oglasService.getAdById(request.getOglasId());
        User user = this.userService.getUserById(userId);

        ZahtjevZaIznajmljivanje rentRequest = new ZahtjevZaIznajmljivanje();
        rentRequest.setMjestoPreuzimanja(request.getMjestoPreuzimanja());
        rentRequest.setOd(request.getOd());
        rentRequest.setDoo(request.getDoo());
        rentRequest.setDatumKreiranja(new DateTime());
        rentRequest.setOglas(ad);
        rentRequest.setStatus(StatusZahtjeva.PENDING);
        rentRequest.setBundle(bundle);

        rentRequest.setUser(user);

        return rentRequest;
    }

    //accept zahtev za iznajmljivanje
    public ResponseEntity<?> acceptRequest(Long requestId) {
        ZahtjevZaIznajmljivanje request = this.zahtjevZaIznajmljivanjeRepository.findById(requestId).orElseThrow(() -> new NotFoundException("Request with given id was not found"));
        acceptRequest2(request);

        this.zahtjevZaIznajmljivanjeRepository.save(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");

        try {
            ZauzetDTO occupiedDTO = new ZauzetDTO();
            occupiedDTO.setOd(request.getOd());
            occupiedDTO.setDoo(request.getDoo());
            occupiedDTO.setVoziloId(request.getOglas().getId());
            this.carClient.addNewRequestOccupation(occupiedDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    public void acceptRequest2(ZahtjevZaIznajmljivanje request) {
        if (!request.getStatus().equals(StatusZahtjeva.PENDING))
            throw new InvalidOperationException("Rent request cannot be accepted, it's not in pending state, but has status: " + request.getStatus());

        List<ZahtjevZaIznajmljivanje> requests2 = zahtjevZaIznajmljivanjeRepository.findAll();
        for(ZahtjevZaIznajmljivanje req: requests2) {
            if (req.getOglas().getId() == request.getOglas().getId() && !req.getStatus().equals(StatusZahtjeva.PENDING)) {
                boolean flag = checkDates(req, request);
                if(flag)
                    throw new InvalidOperationException("Rent request cannot be accepted, it's not in pending state, but has status: " + req.getStatus());
            }
        }

        request.setStatus(StatusZahtjeva.RESERVED);
        request.setStatus(StatusZahtjeva.PAID);

        List<ZahtjevZaIznajmljivanje> requests = this.zahtjevZaIznajmljivanjeRepository.findByOglasId(request.getOglas().getId());
        List<Long> adsId = new ArrayList<>();
        for (ZahtjevZaIznajmljivanje r: requests)
            adsId.add(r.getOglas().getId());

        ZauzetDTO occupiedDTO = ZauzetDTO.builder()
                .id(request.getId())
                .od(request.getOd())
                .doo(request.getDoo())
                .oglasId(adsId)
                .build();
        cancelOccupiedRequests(occupiedDTO);
    }

    public boolean checkDates(ZahtjevZaIznajmljivanje req, ZahtjevZaIznajmljivanje request) {
        boolean flag = false;

        DateTime occupiedFrom = req.getOd();
        DateTime occupiedTo = req.getDoo();
        DateTime rentFrom = request.getOd();
        DateTime rentTo = request.getDoo();

        if (rentFrom.isAfter(occupiedFrom) && rentTo.isBefore(occupiedTo)) {
            flag = true;
        }
        if (rentFrom.isBefore(occupiedFrom) && rentTo.isAfter(occupiedTo)) {
            flag = true;
        }
        if (rentFrom.isBefore(occupiedFrom) && rentTo.isBefore(occupiedTo) && rentTo.isAfter(occupiedFrom)) {
            flag = true;
        }
        if (rentFrom.isAfter(occupiedFrom) && rentTo.isAfter(occupiedTo) && rentFrom.isBefore(occupiedTo)) {
            flag = true;
        }
        if (rentFrom.isEqual(occupiedFrom) && rentTo.isEqual(occupiedTo)) {
            flag = true;
        }

        return flag;
    }

    public ResponseEntity<?> cancelOccupiedRequests(ZauzetDTO occupiedDTO) {
        DateTime occupiedFrom = occupiedDTO.getOd();
        DateTime occupiedTo = occupiedDTO.getDoo();

        for (Long id: occupiedDTO.getOglasId()){
            List<ZahtjevZaIznajmljivanje> rentRequests = this.zahtjevZaIznajmljivanjeRepository.findByOglasId(id);
            for (ZahtjevZaIznajmljivanje rent : rentRequests){
                DateTime rentFrom = rent.getOd();
                DateTime rentTo = rent.getDoo();
                if(rent.getStatus().equals(StatusZahtjeva.PENDING)) {
                    if (rentFrom.isAfter(occupiedFrom) && rentTo.isBefore(occupiedTo)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                    if (rentFrom.isBefore(occupiedFrom) && rentTo.isAfter(occupiedTo)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                    if (rentFrom.isBefore(occupiedFrom) && rentTo.isBefore(occupiedTo) && rentTo.isAfter(occupiedFrom)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                    if (rentFrom.isAfter(occupiedFrom) && rentTo.isAfter(occupiedTo) && rentFrom.isBefore(occupiedTo)) {
                        rent.setStatus(StatusZahtjeva.CANCELED);
                        this.zahtjevZaIznajmljivanjeRepository.save(rent);
                    }
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> cancelRequest(Long requestId) {
        ZahtjevZaIznajmljivanje rentRequest = this.zahtjevZaIznajmljivanjeRepository.findById(requestId).orElseThrow(() -> new NotFoundException("Request with given id was not found"));
        if (rentRequest.getStatus().equals(StatusZahtjeva.PAID))
            throw new InvalidOperationException("Rent request has already been paid. It cannot be cancelled.");
        rentRequest.setStatus(StatusZahtjeva.CANCELED);
        this.zahtjevZaIznajmljivanjeRepository.save(rentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
