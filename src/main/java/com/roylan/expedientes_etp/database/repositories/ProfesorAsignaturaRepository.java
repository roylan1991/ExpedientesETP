package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.ProfesorAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorAsignaturaRepository extends JpaRepository<ProfesorAsignatura, Long> {

    ProfesorAsignatura findByIdProfAsig(long idP);
}
