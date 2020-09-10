package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByIdCurso(long idC);
}
