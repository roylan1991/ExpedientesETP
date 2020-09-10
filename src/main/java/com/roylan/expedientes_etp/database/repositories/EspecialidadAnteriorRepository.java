package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.EspecialidadAnterior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadAnteriorRepository extends JpaRepository<EspecialidadAnterior, Long> {

    EspecialidadAnterior findByIdEspAnterior(long id);

    @Modifying
    @Query("update EspecialidadAnterior set matriculaInicial=?2,altasTotal=?3, altasPorTraslado=?4, matricInicialAjustada=?5, matriculaFinal=?6, aprobados=?7, bajasTotal=?8, bajasPorTraslado=?9 where idEspAnterior=?1")
    void updateEspecialidadAnterior(long idE, int matriculaInicial, int altasTotal, int altasPorTraslado, int matricInicialAjustada, int matriculaFinal, int aprobados, int bajasTotal, int bajasPorTraslado);

    @Query("select ea FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where pd.centro.idCentro=?1 order by ec.duracion.tipoDuracion, e.nombEspecialidad, ae.idAnno")
    List<EspecialidadAnterior> especialidadesAnterioresCentro(long idC);


    /* RESUMEN CURSO TERMINADO */
    @Query("select ea FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where m.idMunicipio=?1 and cur.idCurso=?2 and f.tipoFamilia=?3 order by e.nivel.tipoNivel DESC, e.nombEspecialidad, ae.tipoAnno")
    List<EspecialidadAnterior> especialidadesAnterioresFamiliaMcpio(long idM, long curso, String familia);

    @Query("select ea FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia  inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as prov on m.prov.idProvincia=prov.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where prov.idProvincia=?1 and cur.idCurso=?2 and f.tipoFamilia=?3 order by e.nivel.tipoNivel DESC, e.nombEspecialidad, ae.tipoAnno")
    List<EspecialidadAnterior> especialidadesAnterioresFamiliaProv(long idP, long curso, String familia);

    @Query("select ea FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where cur.idCurso=?1 and f.tipoFamilia=?2 order by e.nivel.tipoNivel DESC, e.nombEspecialidad, ae.tipoAnno")
    List<EspecialidadAnterior> especialidadesAnterioresFamiliaNac(long curso, String familia);

    @Query("select sum(ea.matriculaInicial) FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Nivel as niv on e.nivel.idNivel=niv.idNivel inner join Curso as c on ec.curso.idCurso=c.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where ae.idAnno=?1 and niv.idNivel=?2 and c.idCurso=?3")
    Integer matriculasInicialesSegunAnnoNivelCurso(long anno, long nivel, long curso);

    @Query("select sum(ea.aprobados) FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Nivel as niv on e.nivel.idNivel=niv.idNivel inner join Curso as c on ec.curso.idCurso=c.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where ae.idAnno=?1 and niv.idNivel=?2 and c.idCurso=?3")
    Integer aprobadosSegunAnnoNivelCurso(long anno, long nivel, long curso);
}
