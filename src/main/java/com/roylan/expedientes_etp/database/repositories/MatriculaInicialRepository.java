package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.MatriculaInicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaInicialRepository extends JpaRepository<MatriculaInicial, Long> {

    MatriculaInicial findByIdMatricula(long idM);
}
