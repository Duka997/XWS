package com.demo.service;

import com.demo.dto.CjenovnikDTO;
import com.demo.model.Cjenovnik;
import com.demo.model.User;
import com.demo.repository.CjenovnikRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CjenovnikService {

    @Autowired
    private CjenovnikRepository cjenovnikRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<?> getCjenovnik(Long id) {
        User u = userService.getUserById(id);
        List<Cjenovnik> cjenovnici = this.cjenovnikRepository.findAllByUserUsername(u.getUsername());
        List<CjenovnikDTO> cjenovnikDTOS = new ArrayList<>();

        for(Cjenovnik c: cjenovnici){
            CjenovnikDTO cjenovnikDTO = modelMapper.map(c, CjenovnikDTO.class);
            cjenovnikDTO.setUserUsername(c.getUser().getUsername());

            cjenovnikDTOS.add(cjenovnikDTO);
        }

        return new ResponseEntity<>(cjenovnikDTOS, HttpStatus.OK);
    }

}
