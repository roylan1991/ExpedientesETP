package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Rango;
import com.roylan.expedientes_etp.database.entities.RangoEdad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RangoEdadRepository extends JpaRepository<RangoEdad, Long> {

    RangoEdad findByIdRangoEdad(long idR);

    @Modifying
    @Query("update RangoEdad set diurno=?2, cpt=?3 where idRangoEdad=?1")
    void updateRangoEdad(long idR, Rango diurno, Rango cpt);
}