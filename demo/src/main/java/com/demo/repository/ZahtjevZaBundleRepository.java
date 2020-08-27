package com.demo.repository;

import com.demo.model.BundleZahtjev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtjevZaBundleRepository extends JpaRepository<BundleZahtjev, Long> {

}
