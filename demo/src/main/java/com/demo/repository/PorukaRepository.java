package com.demo.repository;

import com.demo.dto.PorukaDTO;
import com.demo.model.Poruka;
import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long> {

    List<Poruka>findAll();

    List<PorukaDTO> findAllByPrimalacId(Long id );

    List<PorukaDTO> findAllByPosiljalacId(Long id);
}
