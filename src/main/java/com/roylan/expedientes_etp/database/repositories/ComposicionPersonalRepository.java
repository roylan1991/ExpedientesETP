package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.ComposicionPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposicionPersonalRepository extends JpaRepository<ComposicionPersonal, Long> {

    ComposicionPersonal findByIdPersonal(long idP);

    @Modifying
    @Query("update ComposicionPersonal set alumnosMaestros=?2, totalProfesoresInactivos=?3, otrosDocentesInactivos=?4, noDocentesInactivos=?5 where idPersonal=?1")
    void updatePersonal(long idP, int alumnosMaestros, int totalProfesoresInactivos, int otrosDocentesInactivos, int noDocentesInactivos);

    @Modifying
    @Query("update ComposicionPersonal set totalProfesoresFijos=?2, profesoresFijosNoTitulados=?3, profesoresFijosNoTituladosEstudiando=?4, totalProfesoresContratados=?5, profesoresContratadosNoProfesionales=?6,  profesoresContratadosNoProfesionalesEstudiando=?7 where idPersonal=?1")
    void updatePersonalTotalizado(long idP, int totalProfesoresFijos, int totalProfesoresFijosNoTitulados, int profesoresFijosNoTituladosEstudiando, int totalProfesoresContratados, int totalProfesoresContratadosNoProfesionales, int totalProfesoresContratadosNoProfesionalesEstudiando);
}
