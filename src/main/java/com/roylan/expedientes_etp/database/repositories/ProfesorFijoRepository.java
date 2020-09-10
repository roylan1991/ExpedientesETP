package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.ProfesorFijo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorFijoRepository extends JpaRepository<ProfesorFijo, Long> {

    ProfesorFijo findByIdTipoProfesor(long idTP);

    @Modifying
    @Query("update ProfesorFijo set totalProfesores=?2, noTitulados=?3, noTituladosEstudiando=?4 where idTipoProfesor=?1")
    void updateProfesorFijo(long idP, int totalProfesores, int noTitulados, int noTituladosEstudiando);
}
