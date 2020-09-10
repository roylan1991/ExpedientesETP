package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Rango;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RangoRepository extends JpaRepository<Rango, Long> {

    Rango findByIdRango(long idR);

    @Modifying
    @Query("update Rango set totalMenos15=?2, hembrasMenos15=?3, totalEntre15_16=?4, hembrasEntre15_16=?5, totalMayores16=?6, hembrasMayores16=?7 where idRango=?1")
    void updateRango(long idR, int menos15, int menos15H, int entre15_16, int entre15_16H, int mas16, int mas16H);
}
