package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Escolaridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaridadRepository extends JpaRepository<Escolaridad, Long> {

    Escolaridad findByIdEscolaridad(long idE);
}
