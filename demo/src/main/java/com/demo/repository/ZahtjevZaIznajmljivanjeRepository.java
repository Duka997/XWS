package com.demo.repository;

import com.demo.model.User;
import com.demo.model.ZahtjevZaIznajmljivanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZahtjevZaIznajmljivanjeRepository extends JpaRepository<ZahtjevZaIznajmljivanje, Long> {

    List<ZahtjevZaIznajmljivanje> findByUser(User user);
    List<ZahtjevZaIznajmljivanje> findAllByOglas_User_Id(Long id);
    List<ZahtjevZaIznajmljivanje> findByOglasId(Long id);
}
