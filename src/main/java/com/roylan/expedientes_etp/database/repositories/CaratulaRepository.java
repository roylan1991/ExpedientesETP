package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Caratula;
import com.roylan.expedientes_etp.database.entities.MatriculaFinal;
import com.roylan.expedientes_etp.database.entities.MatriculaInicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CaratulaRepository extends JpaRepository<Caratula, Long> {

    Caratula findByIdCaratula(long idC);

    @Modifying
    @Query("update Caratula set matriculaInicialTM=?2, matriculaInicialOC=?3, matriculaInicialCPT_TM=?4, matriculaFinalTM=?5, matriculaFinalOC=?6, matriculaFinalCPT_TM=?7 where idCaratula=?1")
    void updateCaratula(long idC, MatriculaInicial iTM, MatriculaInicial iOC, MatriculaInicial iCPTTM, MatriculaFinal fTM, MatriculaFinal fOC, MatriculaFinal fCPTTM);

    //Carátula de centros
    @Query("select sum(ea.matriculaActualTotal) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasTotalesDeCentro(long idC, long n1, long c1);

    @Query("SELECT SUM(ea.matriculaActualHembras) FROM EspecialidadActual as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasTotalesHembrasDeCentro(long idC, long n1, long c1);

    @Query("SELECT SUM(pd.procdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalesDeCentro(long idC, long n1, long c1);

    @Query("SELECT SUM(pd.hembProcdNuevosIngresos) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalesHembrasDeCentro(long idC, long n1, long c1);

    @Query("SELECT SUM(ea.matriculaFinal) FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasFinalesTotalesDeCentro(long idC, long n1, long c1);

    @Query("SELECT SUM(ea.aprobados) FROM EspecialidadAnterior as ea inner join PlanillaDatos as pd on ea.planilla.idPlanilla=pd.idPlanilla inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer aprobadosTotalesDeCentro(long idC, long n1, long c1);

    @Query("SELECT SUM(pd.procdGraduados) FROM PlanillaDatos as pd inner join EspecialidadCentro as ec on pd.especialidadCentro.idEspecialidad=ec.idEspecialidad inner join Especialidad as e on ec.especialidad.idEspecialidad=e.idEspecialidad inner join Curso as cur on ec.curso.idCurso=cur.idCurso inner join Nivel as n on e.nivel.idNivel=n.idNivel where pd.centro.idCentro=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer graduadosTotalesDeCentro(long idC, long n1, long c1);
}