package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Especialidad;
import com.roylan.expedientes_etp.database.entities.Familia;
import com.roylan.expedientes_etp.database.entities.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    Especialidad findByIdEspecialidad(long idE);

    Especialidad findByCodEspecialidad(String codE);

    List<Especialidad> findAllByOrderByNivelAscNombEspecialidadAsc();

    @Modifying
    @Query("update Especialidad set codEspecialidad=?2, nombEspecialidad=?3, familia=?4, nivel=?5 where idEspecialidad=?1")
    void updateEspecialidad(long idE, String codE, String nomE, Familia f, Nivel n);
}
