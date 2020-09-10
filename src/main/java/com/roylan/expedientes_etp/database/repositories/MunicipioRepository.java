package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    Municipio findByIdMunicipio(long idM);

    List<Municipio> findAllByProvIdProvinciaOrderByNombMunicipio(long idP);
}
