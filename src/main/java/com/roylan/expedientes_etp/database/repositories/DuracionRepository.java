package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Duracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuracionRepository extends JpaRepository<Duracion, Long> {

    Duracion findByIdDuracion(long td);
}
