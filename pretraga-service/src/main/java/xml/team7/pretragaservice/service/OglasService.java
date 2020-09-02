package xml.team7.pretragaservice.service;

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
import xml.team7.pretragaservice.dto.CjenovnikDTO;
import xml.team7.pretragaservice.dto.OglasDTO;
import xml.team7.pretragaservice.dto.PretragaDTO;
import xml.team7.pretragaservice.dto.VoziloDTO;
import xml.team7.pretragaservice.model.Oglas;
import xml.team7.pretragaservice.repository.OglasRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OglasService {
    @Autowired
    private OglasRepository oglasRepository;

    @Autowired
    private ModelMapper modelMapper;

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
