package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadCentroRepository extends JpaRepository<EspecialidadCentro, Long> {

    EspecialidadCentro findByIdEspecialidad(long id);

    @Modifying
    @Query("update EspecialidadCentro set especialidad=?2, curso=?3, duracion=?4, escolaridad=?5, fueCaptada=?6 where idEspecialidad=?1")
    void updateEspecialidadCentro(long idEsp, Especialidad e, Curso curso, Duracion duracion, Escolaridad escolaridad, boolean fueCaptada);

    @Modifying
    @Query("update EspecialidadCentro set fueCaptada=?2 where idEspecialidad=?1")
    void updateCaptadaEspecialidadCentro(long idEsp, boolean fueCaptada);

    @Modifying
    @Query("update EspecialidadCentro set fueCaptada=?2 where centro.idCentro=?1")
    void updateCaptadaTodasEspecialidadCentro(long idC, boolean fueCaptada);

    EspecialidadCentro findByCentroAndEspecialidad_CodEspecialidadAndDuracionAndEscolaridad(Centro centro, String codEsp, Duracion dur, Escolaridad esc);

    @Query("select ec FROM EspecialidadCentro as ec inner join Especialidad as esp on ec.especialidad.idEspecialidad=esp.idEspecialidad where ec.centro=?1 order by ec.curso.idCurso asc, esp.nivel.idNivel asc, ec.escolaridad.idEscolaridad asc, esp.nombEspecialidad asc")
    List<EspecialidadCentro> findAllByCentroIdCentroOrderByEspecialidadAscEscolaridadAscDuracionAsc(Centro centro);

    List<EspecialidadCentro> findAllByCentroIdCentroAndFueCaptadaOrderByEspecialidadAsc(long idC, boolean fueCaptada);
}