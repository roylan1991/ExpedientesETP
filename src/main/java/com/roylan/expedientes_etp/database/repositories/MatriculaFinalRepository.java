package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.MatriculaFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaFinalRepository extends JpaRepository<MatriculaFinal, Long> {

    MatriculaFinal findByIdMatricula(long idM);
}
