package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.ProfesorContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorContratoRepository extends JpaRepository<ProfesorContrato, Long> {

    ProfesorContrato findByIdTipoProfesor(long idTP);

    @Modifying
    @Query("update ProfesorContrato set totalProfesores=?2, noProfesionales=?3, noProfesionalesEstudiando=?4 where idTipoProfesor=?1")
    void updateProfesorContrato(long idP, int totalProfesores, int noProfesionales, int noProfesionalesEstudiando);
}