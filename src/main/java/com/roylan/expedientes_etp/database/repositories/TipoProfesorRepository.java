package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.TipoProfesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProfesorRepository extends JpaRepository<TipoProfesor, Long> {

    TipoProfesor findByIdTipoProfesor(long idTP);
}
