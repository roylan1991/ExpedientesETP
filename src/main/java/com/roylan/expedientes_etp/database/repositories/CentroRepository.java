package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Centro;
import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Long> {

    Centro findByCodCentro(String codE);

    Centro findByIdCentro(long id);

    @Modifying
    @Query("update Centro set codCentro=?2, nombCentro=?3, direccion=?4, telefono=?5, sector=?6, mcpio=?7 where idCentro=?1")
    void updateCentro(long idE, String codE, String nomE, String dir, String telefono, Sector s, Municipio m);

    @Modifying
    @Query("update Centro set progreso=?2 where idCentro=?1")
    void updateProgresoCentro(long idE, int progreso);

    List<Centro> findAllByOrderByMcpioAscNombCentroAsc();

    List<Centro> findAllByMcpioOrderByMcpioAscNombCentroAsc(Municipio mcpio);

    List<Centro> findAllByMcpioProvOrderByMcpioAscNombCentroAsc(Provincia prov);
}