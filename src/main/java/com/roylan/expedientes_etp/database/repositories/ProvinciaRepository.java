package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    Provincia findByIdProvincia(long idP);
}


