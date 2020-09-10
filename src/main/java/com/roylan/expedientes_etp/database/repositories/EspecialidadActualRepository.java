package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.EspecialidadActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadActualRepository extends JpaRepository<EspecialidadActual, Long> {

    EspecialidadActual findByIdEspActual(long id);

    @Query("select ea FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where pd.centro.idCentro=?1 order by ec.duracion.tipoDuracion, e.nombEspecialidad, ae.idAnno")
    List<EspecialidadActual> especialidadesActualesCentro(long idC);

    @Modifying
    @Query("update EspecialidadActual set cantGrupos=?2,nIngPromovidosTotal=?3, nIngPromovidosPorTraslado=?4, repitentesTotal=?5, repitentesPorTraslado=?6, reingresos=?7, matriculaActualTotal=?8, matriculaActualHembras=?9 where idEspActual=?1")
    void updateEspecialidadActual(long idE, int cantGrupos, int nIngPromovidosTotal, int nIngPromovidosPorTraslado, int repitentesTotal, int repitentesPorTraslado, int reingresos, int matriculaActualTotal, int matriculaActualHembras);

    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as c on ec.curso.idCurso=c.idCurso where pd.centro.idCentro=?1 and c.tipoCurso=?2 and r.tipoRama=?3")
    Integer matriculasActualesCentroRama(long idE, String curso, String rama);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as c on ec.curso.idCurso=c.idCurso where pd.centro.idCentro=?1 and c.tipoCurso=?2 and r.tipoRama=?3")
    Integer matriculasActualesHembrasCentroRama(long idE, String curso, String rama);

    @Query("select sum(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as c on ec.curso.idCurso=c.idCurso where pd.centro.idCentro=?1 and c.tipoCurso=?2 and r.tipoRama=?3")
    Integer nuevosIngresosTotalCentroRama(long idE, String curso, String rama);


    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso where m.idMunicipio=?1 and r.tipoRama=?2 and cur.idCurso=?3")
    Integer matriculasActualesMcpioRama(long idM, String rama, long curso);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso where m.idMunicipio=?1 and r.tipoRama=?2 and cur.idCurso=?3")
    Integer matriculasActualesHembrasMcpioRama(long idM, String rama, long curso);

    @Query("select sum(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso where m.idMunicipio=?1 and r.tipoRama=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalMcpioRama(long idM, String rama, long curso);

    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso where p.idProvincia=?1 and r.tipoRama=?2 and cur.idCurso=?3")
    Integer matriculasActualesProvRama(long idP, String rama, long curso);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso where p.idProvincia=?1 and r.tipoRama=?2 and cur.idCurso=?3")
    Integer matriculasActualesHembrasProvRama(long idP, String rama, long curso);

    @Query("select sum(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso where p.idProvincia=?1 and r.tipoRama=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalProvRama(long idP, String rama, long curso);


    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as cur on ec.curso.idCurso=cur.idCurso where r.tipoRama=?1 and cur.idCurso=?2")
    Integer matriculasActualesNacRama(String rama, long curso);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as cur on ec.curso.idCurso=cur.idCurso where r.tipoRama=?1 and cur.idCurso=?2")
    Integer matriculasActualesHembrasNacRama(String rama, long curso);

    @Query("select sum(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as cur on ec.curso.idCurso=cur.idCurso where r.tipoRama=?1 and cur.idCurso=?2")
    Integer nuevosIngresosTotalNacRama(String rama, long curso);

    /*TURQUINO*/
    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso where m.idMunicipio=?1 and r.tipoRama=?2 and cur.idCurso=?3 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer matriculasActualesMcpioTurquinoRama(long idM, String rama, long curso);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama  inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso where m.idMunicipio=?1 and r.tipoRama=?2 and cur.idCurso=?3 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer matriculasActualesHembrasMcpioTurquinoRama(long idM, String rama, long curso);

    @Query("select sum(pd.procdNuevosIngresos) FROM  PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso where m.idMunicipio=?1 and r.tipoRama=?2 and cur.idCurso=?3 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer nuevosIngresosTotalMcpioTurquinoRama(long idM, String rama, long curso);

    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama  inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso where p.idProvincia=?1 and r.tipoRama=?2 and cur.idCurso=?3 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer matriculasActualesProvTurquinoRama(long idP, String rama, long curso);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama  inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso where p.idProvincia=?1 and r.tipoRama=?2 and cur.idCurso=?3 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer matriculasActualesHembrasProvTurquinoRama(long idP, String rama, long curso);

    @Query("select sum(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso where p.idProvincia=?1 and r.tipoRama=?2 and cur.idCurso=?3 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer nuevosIngresosTotalProvTurquinoRama(long idP, String rama, long curso);

    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as cur on ec.curso.idCurso=cur.idCurso where r.tipoRama=?1 and cur.idCurso=?2 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer matriculasActualesNacTurquinoRama(String rama, long curso);

    @Query("select sum(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as cur on ec.curso.idCurso=cur.idCurso where r.tipoRama=?1 and cur.idCurso=?2 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer matriculasActualesHembrasNacTurquinoRama(String rama, long curso);

    @Query("select sum(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Rama as r on f.rama.idRama=r.idRama inner join Curso as cur on ec.curso.idCurso=cur.idCurso where r.tipoRama=?1 and cur.idCurso=?2 and (pd.centro.sector.idSector=2 or pd.centro.sector.idSector=4)")
    Integer nuevosIngresosTotalNacTurquinoRama(String rama, long curso);


    /* RESUMEN CURSO INICIADO */
    @Query("select ea FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where m.idMunicipio=?1 and cur.idCurso=?2 and f.tipoFamilia=?3 order by e.nivel.tipoNivel DESC, e.nombEspecialidad, ae.tipoAnno")
    List<EspecialidadActual> especialidadesActualesFamiliaMcpio(long idM, long curso, String familia);

    @Query("select ea FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Municipio as m on pd.centro.mcpio.idMunicipio=m.idMunicipio inner join Provincia as prov on m.prov.idProvincia=prov.idProvincia inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where prov.idProvincia=?1 and cur.idCurso=?2 and f.tipoFamilia=?3 order by e.nivel.tipoNivel DESC, e.nombEspecialidad, ae.tipoAnno")
    List<EspecialidadActual> especialidadesActualesFamiliaProv(long idP, long curso, String familia);

    @Query("select ea FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where cur.idCurso=?1 and f.tipoFamilia=?2 order by e.nivel.tipoNivel DESC, e.nombEspecialidad, ae.tipoAnno")
    List<EspecialidadActual> especialidadesActualesFamiliaNac(long curso, String familia);


    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Nivel as niv on e.nivel.idNivel=niv.idNivel inner join Curso as c on ec.curso.idCurso=c.idCurso inner join AnnoEstudio as ae on ea.annoEstudio.idAnno=ae.idAnno where ae.idAnno=?1 and niv.idNivel=?2 and c.idCurso=?3")
    Integer matriculasActualesSegunAnnoNivelCurso(long anno, long nivel, long curso);

    @Query("select ea FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Nivel as niv on e.nivel.idNivel=niv.idNivel inner join Curso as cur on ec.curso.idCurso=cur.idCurso where ea.annoEstudio.idAnno=?1 and ea.cantGrupos>0 and niv.idNivel=?2 and cur.idCurso=1")
    List<EspecialidadActual> espActualSegunAnnoCursoNivel(long anno, long nivel);


    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Familia as f on e.familia.idFamilia=f.idFamilia join Curso as cur on ec.curso.idCurso=cur.idCurso where f.idFamilia=?1 and cur.idCurso=?2")
    Integer matriculaTotalNacSegunFamilia(long idF, long idC);
}
