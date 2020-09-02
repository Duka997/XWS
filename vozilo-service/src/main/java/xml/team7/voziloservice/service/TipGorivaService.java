package xml.team7.voziloservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import xml.team7.voziloservice.client.PretragaClient;
import xml.team7.voziloservice.dto.TipGorivaDTO;
import xml.team7.voziloservice.generated.PostGorivoResponse;
import xml.team7.voziloservice.generated.TTipGoriva;
import xml.team7.voziloservice.model.TipGoriva;
import xml.team7.voziloservice.repository.TipGorivaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipGorivaService {
    @Autowired
    private TipGorivaRepository vrstaGorivaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PretragaClient pretragaClient;

    public TipGoriva findById(Long id) throws AccessDeniedException {
        TipGoriva u = vrstaGorivaRepository.findById(id).orElseGet(null);
        return u;
    }


    public TipGoriva save(TipGorivaDTO mDTO) {
        TipGoriva m = new TipGoriva();
        m.setNaziv(mDTO.getNaziv());
        m.setObrisan(false);
        mDTO.setId(m.getId());
        this.pretragaClient.addTipGoriva(mDTO);
        m = this.vrstaGorivaRepository.save(m);
        return m;
    }

    public List<TipGorivaDTO> findAll() throws AccessDeniedException {
        List<TipGoriva> result = vrstaGorivaRepository.findAll();
        List<TipGorivaDTO> finalResult = new ArrayList<>();
        for(TipGoriva tipGoriva : result){
            if(!tipGoriva.isObrisan()){
                finalResult.add(modelMapper.map(tipGoriva, TipGorivaDTO.class));
            }
        }
        return finalResult;
    }

    public TipGoriva edit(TipGorivaDTO vrstaGorivaDTO){
        TipGoriva tipGoriva = this.findById(vrstaGorivaDTO.getId());
        tipGoriva.setId(vrstaGorivaDTO.getId());
        tipGoriva.setNaziv(vrstaGorivaDTO.getNaziv());
        this.vrstaGorivaRepository.save(tipGoriva);
        return tipGoriva;
    }

    public void delete(Long id){
        TipGoriva tipGoriva = this.findById(id);
        tipGoriva.setObrisan(true);
        this.vrstaGorivaRepository.save(tipGoriva);
    }

    public ResponseEntity<?> getAll() {
        List<TipGoriva> result = vrstaGorivaRepository.findAll();
        List<TipGorivaDTO> vrstaGorivaDTOS = new ArrayList<>();

        for(TipGoriva t: result){
            if(t.isObrisan()==false) {
                vrstaGorivaDTOS.add(modelMapper.map(t, TipGorivaDTO.class));
            }
        }

        return new ResponseEntity<>(vrstaGorivaDTOS, HttpStatus.OK);
    }

    public PostGorivoResponse postGorivoSoap(TTipGoriva gorivoRequest) {
        TipGoriva m = new TipGoriva();
        m.setId(gorivoRequest.getId());
        m.setNaziv(gorivoRequest.getNaziv());
        m.setObrisan(gorivoRequest.isObrisan());
        m = this.vrstaGorivaRepository.save(m);

        PostGorivoResponse response = new PostGorivoResponse();
        response.setGorivoResponse(m.getId());
        return response;
    }
}
