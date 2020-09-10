package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.AnnoEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnoEstudioRepository extends JpaRepository<AnnoEstudio, Long> {

    AnnoEstudio findByIdAnno(long idA);
}