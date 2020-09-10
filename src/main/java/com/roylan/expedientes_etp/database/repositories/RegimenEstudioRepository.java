package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.RegimenEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimenEstudioRepository extends JpaRepository<RegimenEstudio, Long> {

    RegimenEstudio findByIdRegimen(long idM);

    @Modifying
    @Query("update RegimenEstudio set internos=?2, internosHembras=?3, seminternos=?4, seminternosHembras=?5 where idRegimen=?1")
    void updateRegimenCaratula(long idR, int internos, int internosH, int semint, int semintH);
}
