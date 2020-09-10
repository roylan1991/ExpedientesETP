package com.roylan.expedientes_etp.database.repositories;

import com.roylan.expedientes_etp.database.entities.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumenRepository extends JpaRepository<Nivel, Long> {

    /*CARATULA - MUNICIPAL*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasTotalesMcpio(long idM, long n1, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasTotalesHembrasMcpio(long idM, long n1, long c1);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalesMcpio(long idM, long n1, long c1);

    @Query("select sum(mi.nuevoIngresoHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalesHembrasMcpio(long idM, long n1, long c1);

    @Query("select sum(mf.matriculaFinal) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasFinalesTotalesMcpio(long idM, long n1, long c1);

    @Query("select sum(mf.aprobados) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer aprobadosTotalesMcpio(long idM, long n1, long c1);

    @Query("select sum(mf.graduados) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer graduadosTotalesMcpio(long idM, long n1, long c1);

    @Query("SELECT SUM(c.regimenTM.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer internosTMTotalesMcpio(long idM);

    @Query("SELECT SUM(c.regimenOC.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer internosOCTotalesMcpio(long idM);

    @Query("SELECT SUM(c.regimenTM.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer seminternosTMTotalesMcpio(long idM);

    @Query("SELECT SUM(c.regimenOC.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer seminternosOCTotalesMcpio(long idM);

    @Query("SELECT SUM(c.regimenTM.internosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer internosTMTotalesHembrasMcpio(long idM);

    @Query("SELECT SUM(c.regimenOC.internosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer internosOCTotalesHembrasMcpio(long idM);

    @Query("SELECT SUM(c.regimenTM.seminternosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer seminternosTMTotalesHembrasMcpio(long idM);

    @Query("SELECT SUM(c.regimenOC.seminternosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer seminternosOCTotalesHembrasMcpio(long idM);


    /*CARATULA - PROVINCIAL*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasTotalesProv(long idP, long n1, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasTotalesHembrasProv(long idP, long n1, long c1);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalesProv(long idP, long n1, long c1);

    @Query("select sum(mi.nuevoIngresoHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer nuevosIngresosTotalesHembrasProv(long idP, long n1, long c1);

    @Query("select sum(mf.matriculaFinal) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer matriculasFinalesTotalesProv(long idP, long n1, long c1);

    @Query("select sum(mf.aprobados) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer aprobadosTotalesProv(long idP, long n1, long c1);

    @Query("select sum(mf.graduados) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and n.idNivel=?2 and cur.idCurso=?3")
    Integer graduadosTotalesProv(long idP, long n1, long c1);

    @Query("SELECT SUM(c.regimenTM.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer internosTMTotalesProv(long idP);

    @Query("SELECT SUM(c.regimenOC.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer internosOCTotalesProv(long idP);

    @Query("SELECT SUM(c.regimenTM.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer seminternosTMTotalesProv(long idP);

    @Query("SELECT SUM(c.regimenOC.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer seminternosOCTotalesProv(long idP);

    @Query("SELECT SUM(c.regimenTM.internosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer internosTMTotalesHembrasProv(long idP);

    @Query("SELECT SUM(c.regimenOC.internosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer internosOCTotalesHembrasProv(long idP);

    @Query("SELECT SUM(c.regimenTM.seminternosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer seminternosTMTotalesHembrasProv(long idP);

    @Query("SELECT SUM(c.regimenOC.seminternosHembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer seminternosOCTotalesHembrasProv(long idP);

    /*CARATULA NACIONAL*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer matriculasTotalesNac(long n1, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer matriculasTotalesHembrasNac(long n1, long c1);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer nuevosIngresosTotalesNac(long n1, long c1);

    @Query("select sum(mi.nuevoIngresoHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Nivel as n on mi.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer nuevosIngresosTotalesHembrasNac(long n1, long c1);

    @Query("select sum(mf.matriculaFinal) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer matriculasFinalesTotalesNac(long n1, long c1);

    @Query("select sum(mf.aprobados) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer aprobadosTotalesNac(long n1, long c1);

    @Query("select sum(mf.graduados) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaFinal as mf on car.idCaratula=mf.caratula.idCaratula inner join Curso as cur on mf.curso.idCurso=cur.idCurso inner join Nivel as n on mf.nivel.idNivel=n.idNivel where n.idNivel=?1 and cur.idCurso=?2")
    Integer graduadosTotalesNac(long n1, long c1);

    @Query("SELECT SUM(c.regimenTM.internos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer internosTMTotalesNac();

    @Query("SELECT SUM(c.regimenOC.internos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer internosOCTotalesNac();

    @Query("SELECT SUM(c.regimenTM.seminternos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer seminternosTMTotalesNac();

    @Query("SELECT SUM(c.regimenOC.seminternos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer seminternosOCTotalesNac();

    @Query("SELECT SUM(c.regimenTM.internosHembras) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer internosTMTotalesHembrasNac();

    @Query("SELECT SUM(c.regimenOC.internosHembras) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer internosOCTotalesHembrasNac();

    @Query("SELECT SUM(c.regimenTM.seminternosHembras) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer seminternosTMTotalesHembrasNac();

    @Query("SELECT SUM(c.regimenOC.seminternosHembras) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula")
    Integer seminternosOCTotalesHembrasNac();


    /*COMPOSICION DEL PERSONAL - MUNICIPAL*/
    @Query("SELECT SUM(cp.docentes.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresDocentesTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.docentes.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresDocentesHembrasTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.totalProfesoresFijos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresFijosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijosNoTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresFijosNoTiTuladosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijosNoTituladosEstudiando) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresFijosNoTituladosEstudiandoTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.totalProfesoresContratados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresContratadosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratadosNoProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresContratadosNoProfesionalesTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratadosNoProfesionalesEstudiando) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresContratadosNoProfesionalesEstudiandoTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.bibliotecarios.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer bibliotecariosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.bibliotecarios.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer bibliotecariosHembrasTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.instructoresArte.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer instructoresArteTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.instructoresArte.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer instructoresArteHembrasTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.psicopedagogos.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer psicopedagogosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.psicopedagogos.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer psicopedagogosHembrasTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.alumnosMaestros) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer alumnosMaestrosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.noDocentes.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer noDocentesTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.noDocentes.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer noDocentesHembrasTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.totalProfesoresInactivos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer profesoresInactivosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.otrosDocentesInactivos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer otrosDocentesInactivosTotalesMcpio(long idM);

    @Query("SELECT SUM(cp.noDocentesInactivos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer noDocentesInactivosTotalesMcpio(long idM);


    /*COMPOSICION DEL PERSONAL - PROVINCIAL*/
    @Query("SELECT SUM(cp.docentes.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresDocentesTotalesProv(long idP);

    @Query("SELECT SUM(cp.docentes.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresDocentesHembrasTotalesProv(long idP);

    @Query("SELECT SUM(cp.totalProfesoresFijos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresFijosTotalesProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijosNoTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresFijosNoTiTuladosTotalesProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijosNoTituladosEstudiando) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresFijosNoTituladosEstudiandoTotalesProv(long idP);

    @Query("SELECT SUM(cp.totalProfesoresContratados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresContratadosTotalesProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratadosNoProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresContratadosNoProfesionalesTotalesProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratadosNoProfesionalesEstudiando) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresContratadosNoProfesionalesEstudiandoTotalesProv(long idP);

    @Query("SELECT SUM(cp.bibliotecarios.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer bibliotecariosTotalesProv(long idP);

    @Query("SELECT SUM(cp.bibliotecarios.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer bibliotecariosHembrasTotalesProv(long idP);

    @Query("SELECT SUM(cp.instructoresArte.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer instructoresArteTotalesProv(long idP);

    @Query("SELECT SUM(cp.instructoresArte.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer instructoresArteHembrasTotalesProv(long idP);

    @Query("SELECT SUM(cp.psicopedagogos.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer psicopedagogosTotalesProv(long idP);

    @Query("SELECT SUM(cp.psicopedagogos.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer psicopedagogosHembrasTotalesProv(long idP);

    @Query("SELECT SUM(cp.alumnosMaestros) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer alumnosMaestrosTotalesProv(long idP);

    @Query("SELECT SUM(cp.noDocentes.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer noDocentesTotalesProv(long idP);

    @Query("SELECT SUM(cp.noDocentes.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer noDocentesHembrasTotalesProv(long idP);

    @Query("SELECT SUM(cp.totalProfesoresInactivos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer profesoresInactivosTotalesProv(long idP);

    @Query("SELECT SUM(cp.otrosDocentesInactivos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer otrosDocentesInactivosTotalesProv(long idP);

    @Query("SELECT SUM(cp.noDocentesInactivos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer noDocentesInactivosTotalesProv(long idP);


    /*COMPOSICION DEL PERSONAL - NACIONAL*/
    @Query("SELECT SUM(cp.docentes.total) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresDocentesTotalesNac();

    @Query("SELECT SUM(cp.docentes.hembras) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresDocentesHembrasTotalesNac();

    @Query("SELECT SUM(cp.totalProfesoresFijos) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresFijosTotalesNac();

    @Query("SELECT SUM(cp.profesoresFijosNoTitulados) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresFijosNoTiTuladosTotalesNac();

    @Query("SELECT SUM(cp.profesoresFijosNoTituladosEstudiando) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresFijosNoTituladosEstudiandoTotalesNac();

    @Query("SELECT SUM(cp.totalProfesoresContratados) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresContratadosTotalesNac();

    @Query("SELECT SUM(cp.profesoresContratadosNoProfesionales) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresContratadosNoProfesionalesTotalesNac();

    @Query("SELECT SUM(cp.profesoresContratadosNoProfesionalesEstudiando) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresContratadosNoProfesionalesEstudiandoTotalesNac();

    @Query("SELECT SUM(cp.bibliotecarios.total) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer bibliotecariosTotalesNac();

    @Query("SELECT SUM(cp.bibliotecarios.hembras) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer bibliotecariosHembrasTotalesNac();

    @Query("SELECT SUM(cp.instructoresArte.total) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer instructoresArteTotalesNac();

    @Query("SELECT SUM(cp.instructoresArte.hembras) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer instructoresArteHembrasTotalesNac();

    @Query("SELECT SUM(cp.psicopedagogos.total) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer psicopedagogosTotalesNac();

    @Query("SELECT SUM(cp.psicopedagogos.hembras) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer psicopedagogosHembrasTotalesNac();

    @Query("SELECT SUM(cp.alumnosMaestros) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer alumnosMaestrosTotalesNac();

    @Query("SELECT SUM(cp.noDocentes.total) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer noDocentesTotalesNac();

    @Query("SELECT SUM(cp.noDocentes.hembras) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer noDocentesHembrasTotalesNac();

    @Query("SELECT SUM(cp.totalProfesoresInactivos) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer profesoresInactivosTotalesNac();

    @Query("SELECT SUM(cp.otrosDocentesInactivos) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer otrosDocentesInactivosTotalesNac();

    @Query("SELECT SUM(cp.noDocentesInactivos) FROM Centro as cen  inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer noDocentesInactivosTotalesNac();


    /*PROFESORES POR ASIGNATURAS - MUNICIPAL*/
    @Query("SELECT SUM(cp.profesoresFijos.profesorEspanol.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosEspanolMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEspanol.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEspanolMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEspanol.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEspanolMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorLiteratura.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosLiteraturaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorLiteratura.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoLiteraturaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorLiteratura.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoLiteraturaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorMatematica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosMatematicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMatematica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoMatematicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMatematica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoMatematicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosFisicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFisica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoFisicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFisica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoFisicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorQuimica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosQuimicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorQuimica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoQuimicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorQuimica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoQuimicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorBiologia.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosBiologiaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBiologia.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoBiologiaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBiologia.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoBiologiaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorHistoria.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosHistoriaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorHistoria.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoHistoriaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorHistoria.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoHistoriaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorIngles.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosInglesMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorIngles.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoInglesMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorIngles.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoInglesMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorEducacionFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosEducacionFisicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionFisica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEducacionFisicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionFisica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEducacionFisicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorEducacionArtistica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosEducacionArtisticaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionArtistica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEducacionArtisticaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionArtistica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEducacionArtisticaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorMarxismo.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosMarxismoMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMarxismo.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoMarxismoMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMarxismo.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoMarxismoMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorFundamento.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosFundamentoMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFundamento.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoFundamentoMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFundamento.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoFundamentoMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorComputacion.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosComputacionMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorComputacion.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoComputacionMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorComputacion.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoComputacionMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorMilitar.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosMilitarMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMilitar.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoMilitarMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMilitar.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoMilitarMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorPractica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosPracticaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorPractica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoPracticaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorPractica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoPracticaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorBasica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosBasicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBasica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoBasicaMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBasica.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoBasicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorEjercicio.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosEjercicioMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEjercicio.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEjercicioMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEjercicio.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEjercicioMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresFijos.profesorInstructor.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresFijosInstructorMcpio(long idM);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorInstructor.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoInstructorMcpio(long idM);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorInstructor.idTipoProfesor=pf.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoInstructorMcpio(long idM);


    @Query("SELECT SUM(cp.profesoresContratados.profesorEspanol.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosEspanolMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEspanol.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEspanolMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEspanol.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEspanolMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorLiteratura.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosLiteraturaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorLiteratura.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesLiteraturaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorLiteratura.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoLiteraturaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorMatematica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosMatematicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMatematica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesMatematicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMatematica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMatematicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosFisicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFisica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesFisicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFisica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoFisicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorQuimica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosQuimicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorQuimica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesQuimicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorQuimica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoQuimicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorBiologia.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosBiologiaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBiologia.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesBiologiaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBiologia.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoBiologiaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorHistoria.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosHistoriaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorHistoria.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesHistoriaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorHistoria.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoHistoriaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorIngles.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosInglesMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorIngles.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesInglesMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorIngles.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoInglesMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorEducacionFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosEducacionFisicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionFisica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEducacionFisicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionFisica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEducacionFisicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorEducacionArtistica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosEducacionArtisticaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionArtistica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEducacionArtisticaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionArtistica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEducacionArtisticaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorMarxismo.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosMarxismoMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMarxismo.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesMarxismoMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMarxismo.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMarxismoMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorFundamento.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosFundamentoMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFundamento.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesFundamentoMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFundamento.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoFundamentoMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorComputacion.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosComputacionMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorComputacion.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesComputacionMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorComputacion.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoComputacionMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorMilitar.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosMilitarMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMilitar.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesMilitarMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMilitar.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMilitarMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorPractica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosPracticaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorPractica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesPracticaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorPractica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoPracticaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorBasica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosBasicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBasica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesBasicaMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBasica.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoBasicaMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorEjercicio.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosEjercicioMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEjercicio.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEjercicioMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEjercicio.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEjercicioMcpio(long idM);

    @Query("SELECT SUM(cp.profesoresContratados.profesorInstructor.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosInstructorMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorInstructor.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesInstructorMcpio(long idM);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorInstructor.idTipoProfesor=pc.idTipoProfesor where mun.idMunicipio=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoInstructorMcpio(long idM);


    /*PROFESORES POR ASIGNATURAS - PROVINCIAL*/
    @Query("SELECT SUM(cp.profesoresFijos.profesorEspanol.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosEspanolProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEspanol.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEspanolProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEspanol.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEspanolProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorLiteratura.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosLiteraturaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorLiteratura.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoLiteraturaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorLiteratura.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoLiteraturaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorMatematica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosMatematicaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMatematica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoMatematicaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMatematica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoMatematicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosFisicaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFisica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoFisicaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFisica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoFisicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorQuimica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosQuimicaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorQuimica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoQuimicaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorQuimica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoQuimicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorBiologia.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosBiologiaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBiologia.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoBiologiaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBiologia.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoBiologiaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorHistoria.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosHistoriaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorHistoria.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoHistoriaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorHistoria.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoHistoriaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorIngles.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosInglesProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorIngles.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoInglesProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorIngles.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoInglesProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorEducacionFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosEducacionFisicaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionFisica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEducacionFisicaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionFisica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEducacionFisicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorEducacionArtistica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosEducacionArtisticaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionArtistica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEducacionArtisticaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionArtistica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEducacionArtisticaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorMarxismo.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosMarxismoProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMarxismo.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoMarxismoProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMarxismo.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoMarxismoProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorFundamento.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosFundamentoProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFundamento.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoFundamentoProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFundamento.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoFundamentoProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorComputacion.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosComputacionProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorComputacion.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoComputacionProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorComputacion.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoComputacionProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorMilitar.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosMilitarProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMilitar.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoMilitarProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMilitar.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoMilitarProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorPractica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosPracticaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorPractica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoPracticaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorPractica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoPracticaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorBasica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosBasicaProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBasica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoBasicaProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBasica.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoBasicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorEjercicio.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosEjercicioProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEjercicio.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEjercicioProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEjercicio.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoEjercicioProv(long idP);

    @Query("SELECT SUM(cp.profesoresFijos.profesorInstructor.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosInstructorProv(long idP);

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorInstructor.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoInstructorProv(long idP);

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorInstructor.idTipoProfesor=pf.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresFijosNoTituladoEstudiandoInstructorProv(long idP);


    @Query("SELECT SUM(cp.profesoresContratados.profesorEspanol.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosEspanolProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEspanol.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEspanolProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEspanol.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEspanolProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorLiteratura.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosLiteraturaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorLiteratura.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesLiteraturaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorLiteratura.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoLiteraturaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorMatematica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosMatematicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMatematica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesMatematicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMatematica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMatematicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosFisicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFisica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesFisicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFisica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoFisicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorQuimica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosQuimicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorQuimica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesQuimicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorQuimica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoQuimicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorBiologia.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosBiologiaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBiologia.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesBiologiaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBiologia.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoBiologiaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorHistoria.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosHistoriaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorHistoria.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesHistoriaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorHistoria.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoHistoriaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorIngles.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosInglesProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorIngles.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesInglesProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorIngles.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoInglesProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorEducacionFisica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosEducacionFisicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionFisica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEducacionFisicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionFisica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEducacionFisicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorEducacionArtistica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosEducacionArtisticaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionArtistica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEducacionArtisticaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionArtistica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEducacionArtisticaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorMarxismo.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosMarxismoProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMarxismo.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesMarxismoProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMarxismo.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMarxismoProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorFundamento.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosFundamentoProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFundamento.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesFundamentoProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFundamento.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoFundamentoProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorComputacion.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosComputacionProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorComputacion.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesComputacionProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorComputacion.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoComputacionProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorMilitar.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosMilitarProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMilitar.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesMilitarProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMilitar.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMilitarProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorPractica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosPracticaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorPractica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesPracticaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorPractica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoPracticaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorBasica.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosBasicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBasica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesBasicaProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBasica.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoBasicaProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorEjercicio.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosEjercicioProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEjercicio.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEjercicioProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEjercicio.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEjercicioProv(long idP);

    @Query("SELECT SUM(cp.profesoresContratados.profesorInstructor.totalProfesores) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosInstructorProv(long idP);

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorInstructor.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesInstructorProv(long idP);

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorInstructor.idTipoProfesor=pc.idTipoProfesor inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoInstructorProv(long idP);


    /*PROFESORES POR ASIGNATURAS - NACIONAL*/
    @Query("SELECT SUM(cp.profesoresFijos.profesorEspanol.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosEspanolNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEspanol.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEspanolNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEspanol.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoEspanolNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorLiteratura.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosLiteraturaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorLiteratura.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoLiteraturaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorLiteratura.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoLiteraturaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorMatematica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosMatematicaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMatematica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoMatematicaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMatematica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoMatematicaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorFisica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosFisicaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFisica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoFisicaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFisica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoFisicaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorQuimica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosQuimicaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorQuimica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoQuimicaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorQuimica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoQuimicaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorBiologia.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosBiologiaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBiologia.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoBiologiaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBiologia.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoBiologiaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorHistoria.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosHistoriaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorHistoria.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoHistoriaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorHistoria.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoHistoriaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorIngles.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosInglesNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorIngles.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoInglesNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorIngles.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoInglesNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorEducacionFisica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosEducacionFisicaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionFisica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEducacionFisicaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionFisica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoEducacionFisicaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorEducacionArtistica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosEducacionArtisticaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionArtistica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEducacionArtisticaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEducacionArtistica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoEducacionArtisticaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorMarxismo.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosMarxismoNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMarxismo.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoMarxismoNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMarxismo.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoMarxismoNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorFundamento.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosFundamentoNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFundamento.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoFundamentoNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorFundamento.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoFundamentoNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorComputacion.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosComputacionNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorComputacion.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoComputacionNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorComputacion.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoComputacionNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorMilitar.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosMilitarNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMilitar.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoMilitarNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorMilitar.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoMilitarNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorPractica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosPracticaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorPractica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoPracticaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorPractica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoPracticaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorBasica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosBasicaNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBasica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoBasicaNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorBasica.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoBasicaNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorEjercicio.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosEjercicioNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEjercicio.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEjercicioNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorEjercicio.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoEjercicioNac();

    @Query("SELECT SUM(cp.profesoresFijos.profesorInstructor.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresFijosInstructorNac();

    @Query("SELECT SUM(pf.noTitulados) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorInstructor.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoInstructorNac();

    @Query("SELECT SUM(pf.noTituladosEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorFijo as pf on cp.profesoresFijos.profesorInstructor.idTipoProfesor=pf.idTipoProfesor")
    Integer totalProfesoresFijosNoTituladoEstudiandoInstructorNac();


    @Query("SELECT SUM(cp.profesoresContratados.profesorEspanol.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosEspanolNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEspanol.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEspanolNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEspanol.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEspanolNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorLiteratura.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosLiteraturaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorLiteratura.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesLiteraturaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorLiteratura.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoLiteraturaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorMatematica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosMatematicaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMatematica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesMatematicaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMatematica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMatematicaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorFisica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosFisicaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFisica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesFisicaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFisica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoFisicaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorQuimica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosQuimicaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorQuimica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesQuimicaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorQuimica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoQuimicaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorBiologia.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosBiologiaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBiologia.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesBiologiaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBiologia.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoBiologiaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorHistoria.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosHistoriaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorHistoria.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesHistoriaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorHistoria.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoHistoriaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorIngles.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosInglesNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorIngles.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesInglesNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorIngles.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoInglesNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorEducacionFisica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosEducacionFisicaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionFisica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEducacionFisicaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionFisica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEducacionFisicaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorEducacionArtistica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosEducacionArtisticaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionArtistica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEducacionArtisticaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEducacionArtistica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEducacionArtisticaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorMarxismo.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosMarxismoNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMarxismo.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesMarxismoNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMarxismo.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMarxismoNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorFundamento.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosFundamentoNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFundamento.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesFundamentoNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorFundamento.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoFundamentoNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorComputacion.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosComputacionNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorComputacion.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesComputacionNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorComputacion.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoComputacionNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorMilitar.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosMilitarNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMilitar.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesMilitarNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorMilitar.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoMilitarNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorPractica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosPracticaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorPractica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesPracticaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorPractica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoPracticaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorBasica.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosBasicaNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBasica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesBasicaNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorBasica.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoBasicaNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorEjercicio.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosEjercicioNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEjercicio.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEjercicioNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorEjercicio.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoEjercicioNac();

    @Query("SELECT SUM(cp.profesoresContratados.profesorInstructor.totalProfesores) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal")
    Integer totalProfesoresContratadosInstructorNac();

    @Query("SELECT SUM(pc.noProfesionales) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorInstructor.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesInstructorNac();

    @Query("SELECT SUM(pc.noProfesionalesEstudiando)FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join ProfesorContrato as pc on cp.profesoresContratados.profesorInstructor.idTipoProfesor=pc.idTipoProfesor")
    Integer totalProfesoresContratadosNoProfesionalesEstudiandoInstructorNac();


    /*RANGO DE EDADES - MUNICIPAL*/
    @Query("SELECT SUM(r.totalMenos15) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio where m.idMunicipio=?1 and cur.idCurso=?2")
    Integer menos15TotalesMcpio(long idM, long curso);

    @Query("SELECT SUM(r.hembrasMenos15) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio where m.idMunicipio=?1 and cur.idCurso=?2")
    Integer menos15HembrasTotalesMcpio(long idM, long curso);

    @Query("SELECT SUM(r.totalEntre15_16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio where m.idMunicipio=?1 and cur.idCurso=?2")
    Integer entre15Y16TotalesMcpio(long idM, long curso);

    @Query("SELECT SUM(r.hembrasEntre15_16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio where m.idMunicipio=?1 and cur.idCurso=?2")
    Integer entre15Y16HembrasTotalesMcpio(long idM, long curso);

    @Query("SELECT SUM(r.totalMayores16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio where m.idMunicipio=?1 and cur.idCurso=?2")
    Integer mas16TotalesMcpio(long idM, long curso);

    @Query("SELECT SUM(r.hembrasMayores16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio where m.idMunicipio=?1 and cur.idCurso=?2")
    Integer mas16HembrasTotalesMcpio(long idM, long curso);


    /*RANGO DE EDADES - PROVINCIAL*/
    @Query("SELECT SUM(r.totalMenos15) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer menos15TotalesProv(long idP, long curso);

    @Query("SELECT SUM(r.hembrasMenos15) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer menos15HembrasTotalesProv(long idP, long curso);

    @Query("SELECT SUM(r.totalEntre15_16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer entre15Y16TotalesProv(long idP, long curso);

    @Query("SELECT SUM(r.hembrasEntre15_16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer entre15Y16HembrasTotalesProv(long idP, long curso);

    @Query("SELECT SUM(r.totalMayores16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer mas16TotalesProv(long idP, long curso);

    @Query("SELECT SUM(r.hembrasMayores16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso inner join Municipio as m on c.mcpio.idMunicipio=m.idMunicipio inner join Provincia as p on m.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer mas16HembrasTotalesProv(long idP, long curso);


    /*RANGO DE EDADES - NACIONAL*/
    @Query("SELECT SUM(r.totalMenos15) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer menos15TotalesNac(long curso);

    @Query("SELECT SUM(r.hembrasMenos15) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer menos15HembrasTotalesNac(long curso);

    @Query("SELECT SUM(r.totalEntre15_16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer entre15Y16TotalesNac(long curso);

    @Query("SELECT SUM(r.hembrasEntre15_16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer entre15Y16HembrasTotalesNac(long curso);

    @Query("SELECT SUM(r.totalMayores16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer mas16TotalesNac(long curso);

    @Query("SELECT SUM(r.hembrasMayores16) FROM Centro as c inner join RangoEdad as re on c.rangosEdades.idRangoEdad=re.idRangoEdad inner join Rango as r on re.idRangoEdad=r.rangoEdad.idRangoEdad inner join Curso as cur on r.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer mas16HembrasTotalesNac(long curso);


    /*FICHA MUNICIPAL*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and cur.idCurso=?2")
    Integer matriculasTotalesMcpio(long idM, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and cur.idCurso=?2")
    Integer matriculasTotalesHembrasMcpio(long idM, long c1);

    @Query("SELECT SUM(re.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer internosTotalesMcpio(long idM);

    @Query("SELECT SUM(re.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1")
    Integer seminternosTotalesMcpio(long idM);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and cur.idCurso=?2")
    Integer nuevosIngresosTotalesMcpio(long idM, long c1);


    /*FICHA MUNICIPAL TURQUINO*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and cur.idCurso=?2 and(c.sector.idSector=2 or c.sector.idSector=4)")
    Integer matriculasTotalesTurquinoMcpio(long idM, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and cur.idCurso=?2 and(c.sector.idSector=2 or c.sector.idSector=4)")
    Integer matriculasTotalesHembrasTurquinoMcpio(long idM, long c1);

    @Query("SELECT SUM(re.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1 and(cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer internosTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(re.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where mun.idMunicipio=?1 and(cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer seminternosTotalesTurquinoMcpio(long idM);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio where mun.idMunicipio=?1 and cur.idCurso=?2 and(c.sector.idSector=2 or c.sector.idSector=4)")
    Integer nuevosIngresosTotalesTurquinoMcpio(long idM, long c1);


    /*FICHA PROVINCIAL*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer matriculasTotalesProv(long idP, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer matriculasTotalesHembrasProv(long idP, long c1);

    @Query("SELECT SUM(re.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where p.idProvincia=?1")
    Integer internosTotalesProv(long idP);

    @Query("SELECT SUM(re.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where p.idProvincia=?1")
    Integer seminternosTotalesProv(long idP);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2")
    Integer nuevosIngresosTotalesProv(long idP, long c1);


    /*FICHA PROVINCIAL TURQUINO*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2 and (c.sector.idSector=2 or c.sector.idSector=4)")
    Integer matriculasTotalesTurquinoProv(long idP, long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2 and (c.sector.idSector=2 or c.sector.idSector=4)")
    Integer matriculasTotalesHembrasTurquinoProv(long idP, long c1);

    @Query("SELECT SUM(re.internos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where p.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer internosTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(re.seminternos) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula where p.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer seminternosTotalesTurquinoProv(long idP);

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso inner join Municipio as mun on c.mcpio.idMunicipio=mun.idMunicipio inner join Provincia as p on mun.prov.idProvincia=p.idProvincia where p.idProvincia=?1 and cur.idCurso=?2 and (c.sector.idSector=2 or c.sector.idSector=4)")
    Integer nuevosIngresosTotalesTurquinoProv(long idP, long c1);


    /*FICHA NACIONAL*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer matriculasTotalesNac(long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer matriculasTotalesHembrasNac(long c1);

    @Query("SELECT SUM(re.internos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula")
    Integer internosTotalesNac();

    @Query("SELECT SUM(re.seminternos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula")
    Integer seminternosTotalesNac();

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso where cur.idCurso=?1")
    Integer nuevosIngresosTotalesNac(long c1);


    /*FICHA NACIONAL TURQUINO*/
    @Query("select sum(mi.total) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso where cur.idCurso=?1 and (c.sector.idSector=2 or c.sector.idSector=4)")
    Integer matriculasTotalesTurquinoNac(long c1);

    @Query("select sum(mi.totalHembras) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso where cur.idCurso=?1 and (c.sector.idSector=2 or c.sector.idSector=4)")
    Integer matriculasTotalesHembrasTurquinoNac(long c1);

    @Query("SELECT SUM(re.internos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer internosTotalesTurquinoNac();

    @Query("SELECT SUM(re.seminternos) FROM Centro as cen inner join Caratula as c on cen.caratula.idCaratula=c.idCaratula inner join RegimenEstudio re on re.caratula.idCaratula=c.idCaratula and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer seminternosTotalesTurquinoNac();

    @Query("select sum(mi.nuevoIngreso) FROM Centro as c inner join Caratula as car on c.caratula.idCaratula=car.idCaratula inner join MatriculaInicial as mi on car.idCaratula=mi.caratula.idCaratula inner join Curso as cur on mi.curso.idCurso=cur.idCurso where cur.idCurso=?1 and (c.sector.idSector=2 or c.sector.idSector=4)")
    Integer nuevosIngresosTotalesTurquinoNac(long c1);


    /*PERSONAL TURQUINO MUNICIPAL*/
    @Query("SELECT SUM(cp.bibliotecarios.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer bibliotecariosTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(cp.bibliotecarios.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer bibliotecariosHembrasTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(cp.instructoresArte.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer instructoresArteTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(cp.instructoresArte.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer instructoresArteHembrasTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(cp.psicopedagogos.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer psicopedagogosTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(cp.psicopedagogos.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer psicopedagogosHembrasTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(cp.alumnosMaestros) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer alumnosMaestrosTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(per.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Personal as per on cp.docentes.idPersonal=per.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer profesoresDocentesTotalesTurquinoMcpio(long idM);

    @Query("SELECT SUM(per.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Personal as per on cp.docentes.idPersonal=per.idPersonal where mun.idMunicipio=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer profesoresDocentesHembrasTotalesTurquinoMcpio(long idM);


    /*PERSONAL TURQUINO PROVINCIAL*/
    @Query("SELECT SUM(cp.bibliotecarios.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer bibliotecariosTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.bibliotecarios.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer bibliotecariosHembrasTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.instructoresArte.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer instructoresArteTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.instructoresArte.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer instructoresArteHembrasTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.psicopedagogos.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer psicopedagogosTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.psicopedagogos.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer psicopedagogosHembrasTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.alumnosMaestros) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer alumnosMaestrosTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.docentes.total) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer profesoresDocentesTotalesTurquinoProv(long idP);

    @Query("SELECT SUM(cp.docentes.hembras) FROM Centro as cen inner join Municipio as mun on cen.mcpio.idMunicipio=mun.idMunicipio inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal inner join Provincia as prov on mun.prov.idProvincia=prov.idProvincia where prov.idProvincia=?1 and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer profesoresDocentesHembrasTotalesTurquinoProv(long idP);


    /*PERSONAL TURQUINO NACIONAL*/
    @Query("SELECT SUM(cp.bibliotecarios.total) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer bibliotecariosTotalesTurquinoNac();

    @Query("SELECT SUM(cp.bibliotecarios.hembras) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer bibliotecariosHembrasTotalesTurquinoNac();

    @Query("SELECT SUM(cp.instructoresArte.total) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer instructoresArteTotalesTurquinoNac();

    @Query("SELECT SUM(cp.instructoresArte.hembras) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer instructoresArteHembrasTotalesTurquinoNac();

    @Query("SELECT SUM(cp.psicopedagogos.total) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer psicopedagogosTotalesTurquinoNac();

    @Query("SELECT SUM(cp.psicopedagogos.hembras) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer psicopedagogosHembrasTotalesTurquinoNac();

    @Query("SELECT SUM(cp.alumnosMaestros) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer alumnosMaestrosTotalesTurquinoNac();

    @Query("SELECT SUM(cp.docentes.total) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer profesoresDocentesTotalesTurquinoNac();

    @Query("SELECT SUM(cp.docentes.hembras) FROM Centro as cen inner join ComposicionPersonal as cp on cen.personal.idPersonal=cp.idPersonal and (cen.sector.idSector=2 or cen.sector.idSector=4)")
    Integer profesoresDocentesHembrasTotalesTurquinoNac();
}