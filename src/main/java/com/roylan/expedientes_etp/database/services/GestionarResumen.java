package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.repositories.ResumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta clase gestiona todas las operaciones referentes al resumen: municipal, provincial y nacional.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarResumen {

    @Autowired
    private ResumenRepository r_repo;

    public GestionarResumen() {
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de las especialidades actuales de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.matriculasTotalesMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de hembras de las
     * especialidades actuales de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de las planillas de
     * datos de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Nuevos ingresos.
     */
    public int nuevosIngresosTotalesMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos hembras de las
     * planillas de datos de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Nuevos ingresos hembras.
     */
    public int nuevosIngresosTotalesHembrasMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesHembrasMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas finales totales de las
     * especialidades actuales de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas finales totales.
     */
    public int matriculasFinalesTotalesMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.matriculasFinalesTotalesMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los aprobados totales de las
     * especialidades actuales de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Aprobados totales.
     */
    public int aprobadosTotalesMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.aprobadosTotalesMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los graduados totales de las planillas de
     * datos de un municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Graduados totales.
     */
    public int graduadosTotalesMcpio(long idM, long idNivel, long idCurso) {
        Integer num = r_repo.graduadosTotalesMcpio(idM, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM internos totales de las carátulas
     * de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Internos totales TM.
     */
    public int internosTMTotalesMcpio(long idM) {
        Integer num = r_repo.internosTMTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM internos totales hembras de las
     * carátulas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Internos totales hembras TM.
     */
    public int internosTMTotalesHembrasMcpio(long idM) {
        Integer num = r_repo.internosTMTotalesHembrasMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM seminternos totales de las
     * carátulas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Seminternos totales TM.
     */
    public int seminternosTMTotalesMcpio(long idM) {
        Integer num = r_repo.seminternosTMTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM seminternos totales hembras de las
     * carátulas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Seminternos totales hembras TM.
     */
    public int seminternosTMTotalesHembrasMcpio(long idM) {
        Integer num = r_repo.seminternosTMTotalesHembrasMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC internos totales de las carátulas
     * de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Internos totales OC.
     */
    public int internosOCTotalesMcpio(long idM) {
        Integer num = r_repo.internosOCTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC internos totales hembras de las
     * carátulas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Internos totales hembras OC.
     */
    public int internosOCTotalesHembrasMcpio(long idM) {
        Integer num = r_repo.internosOCTotalesHembrasMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC seminternos totales de las
     * carátulas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Seminternos totales OC.
     */
    public int seminternosOCTotalesMcpio(long idM) {
        Integer num = r_repo.seminternosOCTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC seminternos totales hembras de las
     * carátulas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Seminternos totales hembras OC.
     */
    public int seminternosOCTotalesHembrasMcpio(long idM) {
        Integer num = r_repo.seminternosOCTotalesHembrasMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de las
     * especialidades actuales de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.matriculasTotalesProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de hembras de las
     * especialidades actuales de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de las planillas de
     * datos de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Nuevos ingresos.
     */
    public int nuevosIngresosTotalesProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos hembras de las
     * planillas de datos de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Nuevos ingresos hembras.
     */
    public int nuevosIngresosTotalesHembrasProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesHembrasProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas finales totales de las
     * especialidades actuales de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas finales totales.
     */
    public int matriculasFinalesTotalesProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.matriculasFinalesTotalesProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los aprobados totales de las
     * especialidades actuales de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Aprobados totales.
     */
    public int aprobadosTotalesProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.aprobadosTotalesProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los graduados totales de las planillas de
     * datos de una provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Graduados totales.
     */
    public int graduadosTotalesProv(long idP, long idNivel, long idCurso) {
        Integer num = r_repo.graduadosTotalesProv(idP, idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM internos totales de las carátulas
     * de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Internos totales TM.
     */
    public int internosTMTotalesProv(long idP) {
        Integer num = r_repo.internosTMTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM internos totales hembras de las
     * carátulas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Internos totales hembras TM.
     */
    public int internosTMTotalesHembrasProv(long idP) {
        Integer num = r_repo.internosTMTotalesHembrasProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM seminternos totales de las
     * carátulas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Seminternos totales TM.
     */
    public int seminternosTMTotalesProv(long idP) {
        Integer num = r_repo.seminternosTMTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM seminternos totales hembras de las
     * carátulas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Seminternos totales hembras TM.
     */
    public int seminternosTMTotalesHembrasProv(long idP) {
        Integer num = r_repo.seminternosTMTotalesHembrasProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC internos totales de las carátulas
     * de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Internos totales OC.
     */
    public int internosOCTotalesProv(long idP) {
        Integer num = r_repo.internosOCTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC internos totales hembras de las
     * carátulas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Internos totales hembras OC.
     */
    public int internosOCTotalesHembrasProv(long idP) {
        Integer num = r_repo.internosOCTotalesHembrasProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC seminternos totales de las
     * carátulas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Seminternos totales OC.
     */
    public int seminternosOCTotalesProv(long idP) {
        Integer num = r_repo.seminternosOCTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC seminternos totales hembras de las
     * carátulas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Seminternos totales hembras OC.
     */
    public int seminternosOCTotalesHembrasProv(long idP) {
        Integer num = r_repo.seminternosOCTotalesHembrasProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores docentes totales
     * de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores docentes totales.
     */
    public int profesoresDocentesTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresDocentesTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores docentes hembras
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores docentes hembras totales.
     */
    public int profesoresDocentesHembrasTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresDocentesHembrasTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos totales de
     * un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos totales.
     */
    public int profesoresFijosTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresFijosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos no
     * titulados totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados totales.
     */
    public int profesoresFijosNoTituladosTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresFijosNoTiTuladosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos no
     * titulados estudiando totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando totales.
     */
    public int profesoresFijosNoTituladosEstudiandoTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresFijosNoTituladosEstudiandoTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados totales.
     */
    public int profesoresContratadosTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresContratadosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados no
     * profesionales totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales totales.
     */
    public int profesoresContratadosNoProfesionalesTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresContratadosNoProfesionalesTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados no
     * profesionales estudiando totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * totales.
     */
    public int profesoresContratadosNoProfesionalesEstudiandoTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresContratadosNoProfesionalesEstudiandoTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de bibliotecarios totales de
     * un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosTotalesMcpio(long idM) {
        Integer num = r_repo.bibliotecariosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de bibliotecarios hembras
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Bibliotecarios hembras totales.
     */
    public int bibliotecariosHembrasTotalesMcpio(long idM) {
        Integer num = r_repo.bibliotecariosHembrasTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de instructores de arte
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Instructores de arte totales.
     */
    public int instructoresArteTotalesMcpio(long idM) {
        Integer num = r_repo.instructoresArteTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de instructores de arte
     * hembras totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Instructores de arte hembras totales.
     */
    public int instructoresArteHembrasTotalesMcpio(long idM) {
        Integer num = r_repo.instructoresArteHembrasTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de psicopedagogos totales de
     * un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosTotalesMcpio(long idM) {
        Integer num = r_repo.psicopedagogosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de psicopedagogos hembras
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Psicopedagogos hembras totales.
     */
    public int psicopedagogosHembrasTotalesMcpio(long idM) {
        Integer num = r_repo.psicopedagogosHembrasTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de alumnos maestros totales de
     * un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Alumnos maestros totales.
     */
    public int alumnosMaestrosTotalesMcpio(long idM) {
        Integer num = r_repo.alumnosMaestrosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes totales de un
     * municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> No docentes totales.
     */
    public int noDocentesTotalesMcpio(long idM) {
        Integer num = r_repo.noDocentesTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes hembras totales
     * de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> No docentes hembras totales.
     */
    public int noDocentesHembrasTotalesMcpio(long idM) {
        Integer num = r_repo.noDocentesHembrasTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no profesores inactivos
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores inactivos totales.
     */
    public int profesoresInactivosTotalesMcpio(long idM) {
        Integer num = r_repo.profesoresInactivosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de otros docentes inactivos
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Otros docentes inactivos totales.
     */
    public int otrosDocentesInactivosTotalesMcpio(long idM) {
        Integer num = r_repo.otrosDocentesInactivosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes inactivos
     * totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> No docentes inactivos totales.
     */
    public int noDocentesInactivosTotalesMcpio(long idM) {
        Integer num = r_repo.noDocentesInactivosTotalesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores docentes totales
     * de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores docentes totales.
     */
    public int profesoresDocentesTotalesProv(long idP) {
        Integer num = r_repo.profesoresDocentesTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores docentes hembras
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores docentes hembras totales.
     */
    public int profesoresDocentesHembrasTotalesProv(long idP) {
        Integer num = r_repo.profesoresDocentesHembrasTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos totales de
     * una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos totales.
     */
    public int profesoresFijosTotalesProv(long idP) {
        Integer num = r_repo.profesoresFijosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos no
     * titulados totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados totales.
     */
    public int profesoresFijosNoTituladosTotalesProv(long idP) {
        Integer num = r_repo.profesoresFijosNoTiTuladosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos no
     * titulados estudiando totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando totales.
     */
    public int profesoresFijosNoTituladosEstudiandoTotalesProv(long idP) {
        Integer num = r_repo.profesoresFijosNoTituladosEstudiandoTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados totales.
     */
    public int profesoresContratadosTotalesProv(long idP) {
        Integer num = r_repo.profesoresContratadosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados no
     * profesionales totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales totales.
     */
    public int profesoresContratadosNoProfesionalesTotalesProv(long idP) {
        Integer num = r_repo.profesoresContratadosNoProfesionalesTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados no
     * profesionales estudiando totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * totales.
     */
    public int profesoresContratadosNoProfesionalesEstudiandoTotalesProv(long idP) {
        Integer num = r_repo.profesoresContratadosNoProfesionalesEstudiandoTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de bibliotecarios totales de
     * una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosTotalesProv(long idP) {
        Integer num = r_repo.bibliotecariosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de bibliotecarios hembras
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Bibliotecarios hembras totales.
     */
    public int bibliotecariosHembrasTotalesProv(long idP) {
        Integer num = r_repo.bibliotecariosHembrasTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de instructores de arte
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Instructores de arte totales.
     */
    public int instructoresArteTotalesProv(long idP) {
        Integer num = r_repo.instructoresArteTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de instructores de arte
     * hembras totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Instructores de arte hembras totales.
     */
    public int instructoresArteHembrasTotalesProv(long idP) {
        Integer num = r_repo.instructoresArteHembrasTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de psicopedagogos totales de
     * una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosTotalesProv(long idP) {
        Integer num = r_repo.psicopedagogosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de psicopedagogos hembras
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Psicopedagogos hembras totales.
     */
    public int psicopedagogosHembrasTotalesProv(long idP) {
        Integer num = r_repo.psicopedagogosHembrasTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de alumnos maestros totales de
     * una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Alumnos maestros totales.
     */
    public int alumnosMaestrosTotalesProv(long idP) {
        Integer num = r_repo.alumnosMaestrosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes totales de una
     * provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> No docentes totales.
     */
    public int noDocentesTotalesProv(long idP) {
        Integer num = r_repo.noDocentesTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes hembras totales
     * de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> No docentes hembras totales.
     */
    public int noDocentesHembrasTotalesProv(long idP) {
        Integer num = r_repo.noDocentesHembrasTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no profesores inactivos
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores inactivos totales.
     */
    public int profesoresInactivosTotalesProv(long idP) {
        Integer num = r_repo.profesoresInactivosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de otros docentes inactivos
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Otros docentes inactivos totales.
     */
    public int otrosDocentesInactivosTotalesProv(long idP) {
        Integer num = r_repo.otrosDocentesInactivosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes inactivos
     * totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> No docentes inactivos totales.
     */
    public int noDocentesInactivosTotalesProv(long idP) {
        Integer num = r_repo.noDocentesInactivosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Español de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Español.
     */
    public int totalProfesoresFijosEspanolProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosEspanolProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Español de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Español.
     */
    public int totalProfesoresFijosNoTituladoEspanolProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEspanolProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Español de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Español.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEspanolProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEspanolProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Literatura de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Literatura.
     */
    public int totalProfesoresFijosLiteraturaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosLiteraturaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Literatura de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Literatura.
     */
    public int totalProfesoresFijosNoTituladoLiteraturaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoLiteraturaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Literatura de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Literatura.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoLiteraturaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoLiteraturaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Matemática de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Matemática.
     */
    public int totalProfesoresFijosMatematicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosMatematicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Matemática de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Matemática.
     */
    public int totalProfesoresFijosNoTituladoMatematicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMatematicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Matemática de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Matemática.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMatematicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMatematicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Física.
     */
    public int totalProfesoresFijosFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Física.
     */
    public int totalProfesoresFijosNoTituladoFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de estudiando Física.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Química de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Química.
     */
    public int totalProfesoresFijosQuimicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosQuimicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Química de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Química.
     */
    public int totalProfesoresFijosNoTituladoQuimicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoQuimicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Química de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Química.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoQuimicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoQuimicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Biología de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Biología.
     */
    public int totalProfesoresFijosBiologiaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosBiologiaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Biología de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Biología.
     */
    public int totalProfesoresFijosNoTituladoBiologiaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoBiologiaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Biología de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Biología.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoBiologiaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoBiologiaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Historia de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Historia.
     */
    public int totalProfesoresFijosHistoriaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosHistoriaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Historia de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Historia.
     */
    public int totalProfesoresFijosNoTituladoHistoriaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoHistoriaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Historia de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Historia.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoHistoriaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoHistoriaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Idioma Extranjero de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Iidioma Extranjero.
     */
    public int totalProfesoresFijosInglesProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosInglesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Idioma Extranjero de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Iidoma Extranjero.
     */
    public int totalProfesoresFijosNoTituladoInglesProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoInglesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Idioma Extranjero de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Iidoma
     * Extranjero.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoInglesProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoInglesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Educación Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Educación Física.
     */
    public int totalProfesoresFijosEducFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosEducacionFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Educación Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Educación Física.
     */
    public int totalProfesoresFijosNoTituladoEducFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEducacionFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Educación Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Educación Física.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEducFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEducacionFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Educación Artística de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Educación Artística.
     */
    public int totalProfesoresFijosEducArtisticaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosEducacionArtisticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Educación Artística de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Educación
     * Artística.
     */
    public int totalProfesoresFijosNoTituladoEducArtisticaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEducacionArtisticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Educación Artística de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Educación Artística.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEducArtisticaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEducacionArtisticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Marxismo-Leninismo de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Marxismo-Leninismo.
     */
    public int totalProfesoresFijosMarxismoProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosMarxismoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Marxismo-Leninismo de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Marxismo-Leninismo.
     */
    public int totalProfesoresFijosNoTituladoMarxismoProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMarxismoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Marxismo-Leninismo de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Marxismo-Leninismo.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMarxismoProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMarxismoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Fundamento de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Fundamento.
     */
    public int totalProfesoresFijosFundamentoProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosFundamentoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Fundamento de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Fundamento.
     */
    public int totalProfesoresFijosNoTituladoFundamentoProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoFundamentoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Fundamento de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Fundamento.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoFundamentoProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoFundamentoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Computación de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Computación.
     */
    public int totalProfesoresFijosComputacionProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosComputacionProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Computación de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Computación.
     */
    public int totalProfesoresFijosNoTituladoComputacionProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoComputacionProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Computación de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Computación.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoComputacionProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoComputacionProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Preparación Militar de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Preparación Militar.
     */
    public int totalProfesoresFijosMilitarProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosMilitarProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Preparación Militar de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Preparación
     * Militar.
     */
    public int totalProfesoresFijosNoTituladoMilitarProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMilitarProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Preparación Militar de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Preparación Militar.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMilitarProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMilitarProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Enseñanza Práctica de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Enseñanza Práctica.
     */
    public int totalProfesoresFijosPracticaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosPracticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Enseñanza Práctica de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Enseñanza Práctica.
     */
    public int totalProfesoresFijosNoTituladoPracticaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoPracticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Enseñanza Práctica de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Enseñanza Práctica.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoPracticaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoPracticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Básicas Específicas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Básicas Específicas.
     */
    public int totalProfesoresFijosBasicasProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosBasicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Básicas Específicas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Básicas
     * Específicas.
     */
    public int totalProfesoresFijosNoTituladoBasicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoBasicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Básicas Específicas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Básicas
     * Específicas.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoBasicaProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoBasicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Ejercicio de la Profesión de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Ejercicio de la Profesión.
     */
    public int totalProfesoresFijosEjercicioProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosEjercicioProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Ejercicio de la Profesión de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Ejercicio de la
     * Profesión.
     */
    public int totalProfesoresFijosNoTituladoEjercicioProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEjercicioProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Ejercicio de la Profesión de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Ejercicio de la Profesión.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEjercicioProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEjercicioProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Instrucción de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos de Instrucción.
     */
    public int totalProfesoresFijosInstructorProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosInstructorProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Instrucción de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados de Instrucción.
     */
    public int totalProfesoresFijosNoTituladoInstructorProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoInstructorProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Instrucción de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Instrucción.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoInstructorProv(long idP) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoInstructorProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Español de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Español.
     */
    public int totalProfesoresContratadosEspanolProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosEspanolProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de no profesionales de Español de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Español.
     */
    public int totalProfesoresContratadosNoProfesionalEspanolProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEspanolProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Literatura de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Literatura.
     */
    public int totalProfesoresContratadosLiteraturaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosLiteraturaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Literatura de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Literatura.
     */
    public int totalProfesoresContratadosNoProfesionalLiteraturaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesLiteraturaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Matemática de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Matemática.
     */
    public int totalProfesoresContratadosMatematicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosMatematicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Matemática de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Matemática.
     */
    public int totalProfesoresContratadosNoProfesionalMatematicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMatematicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Física.
     */
    public int totalProfesoresContratadosFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Física.
     */
    public int totalProfesoresContratadosNoProfesionalFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Química de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Química.
     */
    public int totalProfesoresContratadosQuimicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosQuimicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Química de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Química.
     */
    public int totalProfesoresContratadosNoProfesionalQuimicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesQuimicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Biología de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Biología.
     */
    public int totalProfesoresContratadosBiologiaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosBiologiaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Biología de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Biología.
     */
    public int totalProfesoresContratadosNoProfesionalBiologiaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesBiologiaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Historia de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Historia.
     */
    public int totalProfesoresContratadosHistoriaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosHistoriaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Historia de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Historia.
     */
    public int totalProfesoresContratadosNoProfesionalHistoriaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesHistoriaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Idioma Extranjero de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Iidioma Extranjero.
     */
    public int totalProfesoresContratadosInglesProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosInglesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Idioma Extranjero de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Iidoma
     * Extranjero.
     */
    public int totalProfesoresContratadosNoProfesionalInglesProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesInglesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Educación Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Educación Física.
     */
    public int totalProfesoresContratadosEducFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosEducacionFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Educación Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Educación
     * Física.
     */
    public int totalProfesoresContratadosNoProfesionalEducFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEducacionFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Educación Artística de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Educación Artística.
     */
    public int totalProfesoresContratadosEducArtisticaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosEducacionArtisticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Educación Artística de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Educación
     * Artística.
     */
    public int totalProfesoresContratadosNoProfesionalEducArtisticaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEducacionArtisticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Marxismo-Leninismo de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosMarxismoProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosMarxismoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Marxismo-Leninismo de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosNoProfesionalMarxismoProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMarxismoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Fundamento de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Fundamento.
     */
    public int totalProfesoresContratadosFundamentoProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosFundamentoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Fundamento de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Fundamento.
     */
    public int totalProfesoresContratadosNoProfesionalFundamentoProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesFundamentoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Computación de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Computación.
     */
    public int totalProfesoresContratadosComputacionProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosComputacionProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Computación de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Computación.
     */
    public int totalProfesoresContratadosNoProfesionalComputacionProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesComputacionProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Preparación Militar de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Preparación Militar.
     */
    public int totalProfesoresContratadosMilitarProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosMilitarProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Preparación Militar de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Preparación Militar.
     */
    public int totalProfesoresContratadosNoProfesionalMilitarProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMilitarProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Enseñanza Práctica de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Enseñanza Práctica.
     */
    public int totalProfesoresContratadosPracticaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosPracticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Enseñanza Práctica de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Enseñanza
     * Práctica.
     */
    public int totalProfesoresContratadosNoProfesionalPracticaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesPracticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Básicas Específicas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Básicas Específicas.
     */
    public int totalProfesoresContratadosBasicasProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosBasicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Básicas Específicas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Básicas
     * Específicas.
     */
    public int totalProfesoresContratadosNoProfesionalBasicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesBasicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Ejercicio de la Profesión de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Ejercicio de la Profesión.
     */
    public int totalProfesoresContratadosEjercicioProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosEjercicioProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Ejercicio de la Profesión de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de Ejercicio
     * de la Profesión.
     */
    public int totalProfesoresContratadosNoProfesionalEjercicioProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEjercicioProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Instrucción de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados de Instrucción.
     */
    public int totalProfesoresContratadosInstructorProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosInstructorProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Instrucción de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Instrucción.
     */
    public int totalProfesoresContratadosNoProfesionalInstructorProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesInstructorProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de no profesionales estudiando de Español de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Español.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEspanolProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEspanolProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Literatura de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Literatura.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoLiteraturaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoLiteraturaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Matemática de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Matemática.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMatematicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMatematicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Física.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Química de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Química.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoQuimicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoQuimicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Biología de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Biología.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoBiologiaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoBiologiaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Historia de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Historia.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoHistoriaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoHistoriaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Idioma Extranjero de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Iidoma Extranjero.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoInglesProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoInglesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Educación Física de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Educación Física.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEducFisicaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEducacionFisicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Educación Artística de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Educación Artística.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEducArtisticaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEducacionArtisticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Marxismo-Leninismo de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMarxismoProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMarxismoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Fundamento de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Fundamento.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoFundamentoProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoFundamentoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Computación de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Computación.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoComputacionProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoComputacionProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Preparación Militar de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Preparación Militar.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMilitarProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMilitarProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Enseñanza Práctica de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Enseñanza Práctica.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoPracticaProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoPracticaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Básicas Específicas de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Básicas Específicas.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoBasicasProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoBasicaProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Ejercicio de la Profesión de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Ejercicio de la Profesión.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEjercicioProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEjercicioProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Instrucción de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Instrucción.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoInstructorProv(long idP) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoInstructorProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Español de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Español.
     */

    public int totalProfesoresFijosEspanolMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosEspanolMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Español de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Español.
     */
    public int totalProfesoresFijosNoTituladoEspanolMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEspanolMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Español de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Español.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEspanolMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEspanolMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Literatura de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Literatura.
     */
    public int totalProfesoresFijosLiteraturaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosLiteraturaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Literatura de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Literatura.
     */
    public int totalProfesoresFijosNoTituladoLiteraturaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoLiteraturaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Literatura de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Literatura.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoLiteraturaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoLiteraturaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Matemática de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Matemática.
     */
    public int totalProfesoresFijosMatematicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosMatematicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Matemática de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Matemática.
     */
    public int totalProfesoresFijosNoTituladoMatematicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMatematicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Matemática de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Matemática.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMatematicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMatematicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Física.
     */
    public int totalProfesoresFijosFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Física.
     */
    public int totalProfesoresFijosNoTituladoFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Física.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Química de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Química.
     */
    public int totalProfesoresFijosQuimicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosQuimicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Química de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Química.
     */
    public int totalProfesoresFijosNoTituladoQuimicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoQuimicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Química de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Química.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoQuimicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoQuimicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Biología de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Biología.
     */
    public int totalProfesoresFijosBiologiaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosBiologiaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Biología de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Biología.
     */
    public int totalProfesoresFijosNoTituladoBiologiaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoBiologiaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Biología de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Biología.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoBiologiaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoBiologiaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Historia de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Historia.
     */
    public int totalProfesoresFijosHistoriaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosHistoriaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Historia de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Historia.
     */
    public int totalProfesoresFijosNoTituladoHistoriaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoHistoriaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Historia de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Historia.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoHistoriaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoHistoriaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Idioma Extranjero de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Iidioma Extranjero.
     */
    public int totalProfesoresFijosInglesMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosInglesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Idioma Extranjero de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Iidoma Extranjero.
     */
    public int totalProfesoresFijosNoTituladoInglesMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoInglesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Idioma Extranjero de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Iidoma
     * Extranjero.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoInglesMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoInglesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Educación Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Educación Física.
     */
    public int totalProfesoresFijosEducFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosEducacionFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Educación Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Educación Física.
     */
    public int totalProfesoresFijosNoTituladoEducFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEducacionFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Educación Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Educación Física.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEducFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEducacionFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Educación Artística de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Educación Artística.
     */
    public int totalProfesoresFijosEducArtisticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosEducacionArtisticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Educación Artística de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Educación
     * Artística.
     */
    public int totalProfesoresFijosNoTituladoEducArtisticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEducacionArtisticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Educación Artística de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Educación Artística.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEducArtisticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEducacionArtisticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Marxismo-Leninismo de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Marxismo-Leninismo.
     */
    public int totalProfesoresFijosMarxismoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosMarxismoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Marxismo-Leninismo de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Marxismo-Leninismo.
     */
    public int totalProfesoresFijosNoTituladoMarxismoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMarxismoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Marxismo-Leninismo de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Marxismo-Leninismo.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMarxismoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMarxismoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Fundamento de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Fundamento.
     */
    public int totalProfesoresFijosFundamentoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosFundamentoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Fundamento de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Fundamento.
     */
    public int totalProfesoresFijosNoTituladoFundamentoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoFundamentoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Fundamento de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Fundamento.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoFundamentoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoFundamentoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Computación de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Computación.
     */
    public int totalProfesoresFijosComputacionMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosComputacionMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Computación de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Computación.
     */
    public int totalProfesoresFijosNoTituladoComputacionMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoComputacionMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Computación de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Computación.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoComputacionMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoComputacionMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Preparación Militar de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Preparación Militar.
     */
    public int totalProfesoresFijosMilitarMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosMilitarMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Preparación Militar de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Preparación
     * Militar.
     */
    public int totalProfesoresFijosNoTituladoMilitarMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMilitarMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Preparación Militar de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Preparación Militar.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMilitarMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMilitarMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Enseñanza Práctica de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Enseñanza Práctica.
     */
    public int totalProfesoresFijosPracticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosPracticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Enseñanza Práctica de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Enseñanza Práctica.
     */
    public int totalProfesoresFijosNoTituladoPracticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoPracticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Enseñanza Práctica de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Enseñanza Práctica.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoPracticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoPracticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Básicas Específicas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Básicas Específicas.
     */
    public int totalProfesoresFijosBasicasMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosBasicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Básicas Específicas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Básicas
     * Específicas.
     */
    public int totalProfesoresFijosNoTituladoBasicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoBasicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Básicas Específicas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Básicas
     * Específicas.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoBasicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoBasicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Ejercicio de la Profesión de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Ejercicio de la Profesión.
     */
    public int totalProfesoresFijosEjercicioMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosEjercicioMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Ejercicio de la Profesión de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Ejercicio de la
     * Profesión.
     */
    public int totalProfesoresFijosNoTituladoEjercicioMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEjercicioMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Ejercicio de la Profesión de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Ejercicio de la Profesión.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEjercicioMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEjercicioMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Instrucción de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos de Instrucción.
     */
    public int totalProfesoresFijosInstructorMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosInstructorMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Instrucción de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados de Instrucción.
     */
    public int totalProfesoresFijosNoTituladoInstructorMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoInstructorMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Instrucción de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Instrucción.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoInstructorMcpio(long idM) {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoInstructorMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Español de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Español.
     */
    public int totalProfesoresContratadosEspanolMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosEspanolMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de no profesionales de Español de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Español.
     */
    public int totalProfesoresContratadosNoProfesionalEspanolMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEspanolMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Literatura de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Literatura.
     */
    public int totalProfesoresContratadosLiteraturaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosLiteraturaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Literatura de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Literatura.
     */
    public int totalProfesoresContratadosNoProfesionalLiteraturaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesLiteraturaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Matemática de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Matemática.
     */
    public int totalProfesoresContratadosMatematicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosMatematicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Matemática de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Matemática.
     */
    public int totalProfesoresContratadosNoProfesionalMatematicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMatematicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Física.
     */
    public int totalProfesoresContratadosFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Física.
     */
    public int totalProfesoresContratadosNoProfesionalFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Química de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Química.
     */
    public int totalProfesoresContratadosQuimicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosQuimicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Química de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Química.
     */
    public int totalProfesoresContratadosNoProfesionalQuimicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesQuimicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Biología de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Biología.
     */
    public int totalProfesoresContratadosBiologiaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosBiologiaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Biología de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Biología.
     */
    public int totalProfesoresContratadosNoProfesionalBiologiaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesBiologiaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Historia de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Historia.
     */
    public int totalProfesoresContratadosHistoriaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosHistoriaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Historia de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Historia.
     */
    public int totalProfesoresContratadosNoProfesionalHistoriaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesHistoriaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Idioma Extranjero de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Iidioma Extranjero.
     */
    public int totalProfesoresContratadosInglesMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosInglesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Idioma Extranjero de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Iidoma
     * Extranjero.
     */
    public int totalProfesoresContratadosNoProfesionalInglesMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesInglesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Educación Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Educación Física.
     */
    public int totalProfesoresContratadosEducFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosEducacionFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Educación Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Educación
     * Física.
     */
    public int totalProfesoresContratadosNoProfesionalEducFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEducacionFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Educación Artística de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Educación Artística.
     */
    public int totalProfesoresContratadosEducArtisticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosEducacionArtisticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Educación Artística de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Educación
     * Artística.
     */
    public int totalProfesoresContratadosNoProfesionalEducArtisticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEducacionArtisticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Marxismo-Leninismo de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosMarxismoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosMarxismoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Marxismo-Leninismo de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosNoProfesionalMarxismoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMarxismoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Fundamento de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Fundamento.
     */
    public int totalProfesoresContratadosFundamentoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosFundamentoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Fundamento de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Fundamento.
     */
    public int totalProfesoresContratadosNoProfesionalFundamentoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesFundamentoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Computación de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Computación.
     */
    public int totalProfesoresContratadosComputacionMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosComputacionMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Computación de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Computación.
     */
    public int totalProfesoresContratadosNoProfesionalComputacionMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesComputacionMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Preparación Militar de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Preparación Militar.
     */
    public int totalProfesoresContratadosMilitarMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosMilitarMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Preparación Militar de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Preparación Militar.
     */
    public int totalProfesoresContratadosNoProfesionalMilitarMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMilitarMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Enseñanza Práctica de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Enseñanza Práctica.
     */
    public int totalProfesoresContratadosPracticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosPracticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Enseñanza Práctica de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Enseñanza
     * Práctica.
     */
    public int totalProfesoresContratadosNoProfesionalPracticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesPracticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Básicas Específicas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Básicas Específicas.
     */
    public int totalProfesoresContratadosBasicasMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosBasicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Básicas Específicas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Básicas
     * Específicas.
     */
    public int totalProfesoresContratadosNoProfesionalBasicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesBasicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Ejercicio de la Profesión de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Ejercicio de la Profesión.
     */
    public int totalProfesoresContratadosEjercicioMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosEjercicioMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Ejercicio de la Profesión de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de Ejercicio
     * de la Profesión.
     */
    public int totalProfesoresContratadosNoProfesionalEjercicioMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEjercicioMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Instrucción de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados de Instrucción.
     */
    public int totalProfesoresContratadosInstructorMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosInstructorMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Instrucción de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Instrucción.
     */
    public int totalProfesoresContratadosNoProfesionalInstructorMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesInstructorMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de no profesionales estudiando de Español de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Español.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEspanolMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEspanolMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Literatura de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Literatura.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoLiteraturaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoLiteraturaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Matemática de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Matemática.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMatematicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMatematicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Física.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Química de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Química.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoQuimicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoQuimicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Biología de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Biología.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoBiologiaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoBiologiaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Historia de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Historia.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoHistoriaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoHistoriaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Idioma Extranjero de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Iidoma Extranjero.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoInglesMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoInglesMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Educación Física de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Educación Física.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEducFisicaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEducacionFisicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Educación Artística de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Educación Artística.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEducArtisticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEducacionArtisticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Marxismo-Leninismo de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMarxismoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMarxismoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Fundamento de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Fundamento.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoFundamentoMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoFundamentoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Computación de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Computación.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoComputacionMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoComputacionMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Preparación Militar de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Preparación Militar.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMilitarMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMilitarMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Enseñanza Práctica de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Enseñanza Práctica.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoPracticaMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoPracticaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Básicas Específicas de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Básicas Específicas.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoBasicasMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoBasicaMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Ejercicio de la Profesión de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Ejercicio de la Profesión.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEjercicioMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEjercicioMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Instrucción de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Instrucción.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoInstructorMcpio(long idM) {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoInstructorMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de las
     * especialidades actuales del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesNac(long idNivel, long idCurso) {
        Integer num = r_repo.matriculasTotalesNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de hembras de las
     * especialidades actuales del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasNac(long idNivel, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de las planillas de
     * datos del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Nuevos ingresos.
     */
    public int nuevosIngresosTotalesNac(long idNivel, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos hembras de las
     * planillas de datos del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Nuevos ingresos hembras.
     */
    public int nuevosIngresosTotalesHembrasNac(long idNivel, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesHembrasNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas finales totales de las
     * especialidades actuales del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas finales totales.
     */
    public int matriculasFinalesTotalesNac(long idNivel, long idCurso) {
        Integer num = r_repo.matriculasFinalesTotalesNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los aprobados totales de las
     * especialidades actuales del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Aprobados totales.
     */
    public int aprobadosTotalesNac(long idNivel, long idCurso) {
        Integer num = r_repo.aprobadosTotalesNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los graduados totales de las planillas de
     * datos del país.
     *
     * @param idNivel Identificador del Nivel.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Graduados totales.
     */
    public int graduadosTotalesNac(long idNivel, long idCurso) {
        Integer num = r_repo.graduadosTotalesNac(idNivel, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM internos totales de las carátulas
     * del país.
     *
     * @return <code>Integer</code> Internos totales TM.
     */
    public int internosTMTotalesNac() {
        Integer num = r_repo.internosTMTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM internos totales hembras de las
     * carátulas del país.
     *
     * @return <code>Integer</code> Internos totales hembras TM.
     */
    public int internosTMTotalesHembrasNac() {
        Integer num = r_repo.internosTMTotalesHembrasNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM seminternos totales de las
     * carátulas del país.
     *
     * @return <code>Integer</code> Seminternos totales TM.
     */
    public int seminternosTMTotalesNac() {
        Integer num = r_repo.seminternosTMTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los TM seminternos totales hembras de las
     * carátulas del país.
     *
     * @return <code>Integer</code> Seminternos totales hembras TM.
     */
    public int seminternosTMTotalesHembrasNac() {
        Integer num = r_repo.seminternosTMTotalesHembrasNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC internos totales de las carátulas
     * del país.
     *
     * @return <code>Integer</code> Internos totales OC.
     */
    public int internosOCTotalesNac() {
        Integer num = r_repo.internosOCTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC internos totales hembras de las
     * carátulas del país.
     *
     * @return <code>Integer</code> Internos totales hembras OC.
     */
    public int internosOCTotalesHembrasNac() {
        Integer num = r_repo.internosOCTotalesHembrasNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC seminternos totales de las
     * carátulas del país.
     *
     * @return <code>Integer</code> Seminternos totales OC.
     */
    public int seminternosOCTotalesNac() {
        Integer num = r_repo.seminternosOCTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los OC seminternos totales hembras de las
     * carátulas del país.
     *
     * @return <code>Integer</code> Seminternos totales hembras OC.
     */
    public int seminternosOCTotalesHembrasNac() {
        Integer num = r_repo.seminternosOCTotalesHembrasNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Español en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Español.
     */
    public int totalProfesoresFijosEspanolNac() {
        Integer num = r_repo.totalProfesoresFijosEspanolNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Español en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Español.
     */
    public int totalProfesoresFijosNoTituladoEspanolNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEspanolNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Español en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Español.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEspanolNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEspanolNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Literatura en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Literatura.
     */
    public int totalProfesoresFijosLiteraturaNac() {
        Integer num = r_repo.totalProfesoresFijosLiteraturaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Literatura en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Literatura.
     */
    public int totalProfesoresFijosNoTituladoLiteraturaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoLiteraturaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Literatura en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Literatura.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoLiteraturaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoLiteraturaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Matemática en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Matemática.
     */
    public int totalProfesoresFijosMatematicaNac() {
        Integer num = r_repo.totalProfesoresFijosMatematicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Matemática en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Matemática.
     */
    public int totalProfesoresFijosNoTituladoMatematicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMatematicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Matemática en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Matemática.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMatematicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMatematicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Física en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Física.
     */
    public int totalProfesoresFijosFisicaNac() {
        Integer num = r_repo.totalProfesoresFijosFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Física en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Física.
     */
    public int totalProfesoresFijosNoTituladoFisicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Física en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Física.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoFisicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Química en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Química.
     */
    public int totalProfesoresFijosQuimicaNac() {
        Integer num = r_repo.totalProfesoresFijosQuimicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Química en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Química.
     */
    public int totalProfesoresFijosNoTituladoQuimicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoQuimicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Química en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Química.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoQuimicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoQuimicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Biología en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Biología.
     */
    public int totalProfesoresFijosBiologiaNac() {
        Integer num = r_repo.totalProfesoresFijosBiologiaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Biología en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Biología.
     */
    public int totalProfesoresFijosNoTituladoBiologiaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoBiologiaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Biología en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Biología.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoBiologiaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoBiologiaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Historia en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Historia.
     */
    public int totalProfesoresFijosHistoriaNac() {
        Integer num = r_repo.totalProfesoresFijosHistoriaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Historia en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Historia.
     */
    public int totalProfesoresFijosNoTituladoHistoriaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoHistoriaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Historia en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Historia.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoHistoriaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoHistoriaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Idioma Extranjero en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Iidioma Extranjero.
     */
    public int totalProfesoresFijosInglesNac() {
        Integer num = r_repo.totalProfesoresFijosInglesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Idioma Extranjero en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Iidoma Extranjero.
     */
    public int totalProfesoresFijosNoTituladoInglesNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoInglesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Idioma Extranjero en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Iidoma
     * Extranjero.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoInglesNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoInglesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Educación Física en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Educación Física.
     */
    public int totalProfesoresFijosEducFisicaNac() {
        Integer num = r_repo.totalProfesoresFijosEducacionFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Educación Física en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Educación Física.
     */
    public int totalProfesoresFijosNoTituladoEducFisicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEducacionFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Educación Física en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Educación Física.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEducFisicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEducacionFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Educación Artística en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Educación Artística.
     */
    public int totalProfesoresFijosEducArtisticaNac() {
        Integer num = r_repo.totalProfesoresFijosEducacionArtisticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Educación Artística en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Educación
     * Artística.
     */
    public int totalProfesoresFijosNoTituladoEducArtisticaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEducacionArtisticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Educación Artística en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Educación Artística.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEducArtisticaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEducacionArtisticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Marxismo-Leninismo en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Marxismo-Leninismo.
     */
    public int totalProfesoresFijosMarxismoNac() {
        Integer num = r_repo.totalProfesoresFijosMarxismoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Marxismo-Leninismo en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Marxismo-Leninismo.
     */
    public int totalProfesoresFijosNoTituladoMarxismoNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMarxismoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Marxismo-Leninismo en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Marxismo-Leninismo.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMarxismoNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMarxismoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Fundamento en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Fundamento.
     */
    public int totalProfesoresFijosFundamentoNac() {
        Integer num = r_repo.totalProfesoresFijosFundamentoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Fundamento en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Fundamento.
     */
    public int totalProfesoresFijosNoTituladoFundamentoNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoFundamentoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Fundamento en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Fundamento.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoFundamentoNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoFundamentoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Computación en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Computación.
     */
    public int totalProfesoresFijosComputacionNac() {
        Integer num = r_repo.totalProfesoresFijosComputacionNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Computación en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Computación.
     */
    public int totalProfesoresFijosNoTituladoComputacionNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoComputacionNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Computación en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Computación.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoComputacionNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoComputacionNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Preparación Militar en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Preparación Militar.
     */
    public int totalProfesoresFijosMilitarNac() {
        Integer num = r_repo.totalProfesoresFijosMilitarNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Preparación Militar en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Preparación
     * Militar.
     */
    public int totalProfesoresFijosNoTituladoMilitarNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoMilitarNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Preparación Militar en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Preparación Militar.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoMilitarNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoMilitarNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Enseñanza Práctica en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Enseñanza Práctica.
     */
    public int totalProfesoresFijosPracticaNac() {
        Integer num = r_repo.totalProfesoresFijosPracticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Enseñanza Práctica en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Enseñanza Práctica.
     */
    public int totalProfesoresFijosNoTituladoPracticaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoPracticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Enseñanza Práctica en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Enseñanza Práctica.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoPracticaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoPracticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Básicas Específicas en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Básicas Específicas.
     */
    public int totalProfesoresFijosBasicasNac() {
        Integer num = r_repo.totalProfesoresFijosBasicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Básicas Específicas en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Básicas
     * Específicas.
     */
    public int totalProfesoresFijosNoTituladoBasicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoBasicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Básicas Específicas en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de Básicas
     * Específicas.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoBasicaNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoBasicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Ejercicio de la Profesión en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Ejercicio de la Profesión.
     */
    public int totalProfesoresFijosEjercicioNac() {
        Integer num = r_repo.totalProfesoresFijosEjercicioNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Ejercicio de la Profesión en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Ejercicio de la
     * Profesión.
     */
    public int totalProfesoresFijosNoTituladoEjercicioNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEjercicioNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Ejercicio de la Profesión en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Ejercicio de la Profesión.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoEjercicioNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoEjercicioNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos de
     * Instrucción en el país.
     *
     * @return <code>Integer</code> Profesores fijos de Instrucción.
     */
    public int totalProfesoresFijosInstructorNac() {
        Integer num = r_repo.totalProfesoresFijosInstructorNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados de Instrucción en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados de Instrucción.
     */
    public int totalProfesoresFijosNoTituladoInstructorNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoInstructorNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * titulados estudiando de Instrucción en el país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando de
     * Instrucción.
     */
    public int totalProfesoresFijosNoTituladoEstudiandoInstructorNac() {
        Integer num = r_repo.totalProfesoresFijosNoTituladoEstudiandoInstructorNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Español en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Español.
     */
    public int totalProfesoresContratadosEspanolNac() {
        Integer num = r_repo.totalProfesoresContratadosEspanolNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de no profesionales de Español en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Español.
     */
    public int totalProfesoresContratadosNoProfesionalEspanolNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEspanolNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Literatura en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Literatura.
     */
    public int totalProfesoresContratadosLiteraturaNac() {
        Integer num = r_repo.totalProfesoresContratadosLiteraturaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Literatura en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Literatura.
     */
    public int totalProfesoresContratadosNoProfesionalLiteraturaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesLiteraturaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Matemática en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Matemática.
     */
    public int totalProfesoresContratadosMatematicaNac() {
        Integer num = r_repo.totalProfesoresContratadosMatematicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Matemática en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Matemática.
     */
    public int totalProfesoresContratadosNoProfesionalMatematicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMatematicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Física en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Física.
     */
    public int totalProfesoresContratadosFisicaNac() {
        Integer num = r_repo.totalProfesoresContratadosFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Física en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Física.
     */
    public int totalProfesoresContratadosNoProfesionalFisicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Química en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Química.
     */
    public int totalProfesoresContratadosQuimicaNac() {
        Integer num = r_repo.totalProfesoresContratadosQuimicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Química en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Química.
     */
    public int totalProfesoresContratadosNoProfesionalQuimicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesQuimicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Biología en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Biología.
     */
    public int totalProfesoresContratadosBiologiaNac() {
        Integer num = r_repo.totalProfesoresContratadosBiologiaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Biología en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Biología.
     */
    public int totalProfesoresContratadosNoProfesionalBiologiaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesBiologiaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Historia en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Historia.
     */
    public int totalProfesoresContratadosHistoriaNac() {
        Integer num = r_repo.totalProfesoresContratadosHistoriaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Historia en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Historia.
     */
    public int totalProfesoresContratadosNoProfesionalHistoriaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesHistoriaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Idioma Extranjero en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Iidioma Extranjero.
     */
    public int totalProfesoresContratadosInglesNac() {
        Integer num = r_repo.totalProfesoresContratadosInglesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Idioma Extranjero en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Iidoma
     * Extranjero.
     */
    public int totalProfesoresContratadosNoProfesionalInglesNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesInglesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Educación Física en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Educación Física.
     */
    public int totalProfesoresContratadosEducFisicaNac() {
        Integer num = r_repo.totalProfesoresContratadosEducacionFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Educación Física en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Educación
     * Física.
     */
    public int totalProfesoresContratadosNoProfesionalEducFisicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEducacionFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Educación Artística en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Educación Artística.
     */
    public int totalProfesoresContratadosEducArtisticaNac() {
        Integer num = r_repo.totalProfesoresContratadosEducacionArtisticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Educación Artística en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Educación
     * Artística.
     */
    public int totalProfesoresContratadosNoProfesionalEducArtisticaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEducacionArtisticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Marxismo-Leninismo en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosMarxismoNac() {
        Integer num = r_repo.totalProfesoresContratadosMarxismoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Marxismo-Leninismo en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosNoProfesionalMarxismoNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMarxismoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Fundamento en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Fundamento.
     */
    public int totalProfesoresContratadosFundamentoNac() {
        Integer num = r_repo.totalProfesoresContratadosFundamentoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Fundamento en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Fundamento.
     */
    public int totalProfesoresContratadosNoProfesionalFundamentoNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesFundamentoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Computación en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Computación.
     */
    public int totalProfesoresContratadosComputacionNac() {
        Integer num = r_repo.totalProfesoresContratadosComputacionNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Computación en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Computación.
     */
    public int totalProfesoresContratadosNoProfesionalComputacionNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesComputacionNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Preparación Militar en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Preparación Militar.
     */
    public int totalProfesoresContratadosMilitarNac() {
        Integer num = r_repo.totalProfesoresContratadosMilitarNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Preparación Militar en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Preparación Militar.
     */
    public int totalProfesoresContratadosNoProfesionalMilitarNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesMilitarNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Enseñanza Práctica en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Enseñanza Práctica.
     */
    public int totalProfesoresContratadosPracticaNac() {
        Integer num = r_repo.totalProfesoresContratadosPracticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Enseñanza Práctica en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Enseñanza
     * Práctica.
     */
    public int totalProfesoresContratadosNoProfesionalPracticaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesPracticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Básicas Específicas en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Básicas Específicas.
     */
    public int totalProfesoresContratadosBasicasNac() {
        Integer num = r_repo.totalProfesoresContratadosBasicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Básicas Específicas en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Básicas
     * Específicas.
     */
    public int totalProfesoresContratadosNoProfesionalBasicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesBasicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Ejercicio de la Profesión en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Ejercicio de la Profesión.
     */
    public int totalProfesoresContratadosEjercicioNac() {
        Integer num = r_repo.totalProfesoresContratadosEjercicioNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Ejercicio de la Profesión en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de Ejercicio
     * de la Profesión.
     */
    public int totalProfesoresContratadosNoProfesionalEjercicioNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEjercicioNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de Instrucción en el país.
     *
     * @return <code>Integer</code> Profesores contratados de Instrucción.
     */
    public int totalProfesoresContratadosInstructorNac() {
        Integer num = r_repo.totalProfesoresContratadosInstructorNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales de Instrucción en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales de
     * Instrucción.
     */
    public int totalProfesoresContratadosNoProfesionalInstructorNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesInstructorNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores
     * contratados de no profesionales estudiando de Español en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Español.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEspanolNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEspanolNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Literatura en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Literatura.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoLiteraturaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoLiteraturaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Matemática en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Matemática.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMatematicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMatematicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Física en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Física.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoFisicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Química en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Química.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoQuimicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoQuimicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Biología en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Biología.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoBiologiaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoBiologiaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Historia en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Historia.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoHistoriaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoHistoriaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Idioma Extranjero en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Iidoma Extranjero.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoInglesNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoInglesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Educación Física en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Educación Física.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEducFisicaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEducacionFisicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Educación Artística en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Educación Artística.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEducArtisticaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEducacionArtisticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Marxismo-Leninismo en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Marxismo-Leninismo.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMarxismoNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMarxismoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Fundamento en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Fundamento.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoFundamentoNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoFundamentoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Computación en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Computación.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoComputacionNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoComputacionNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Preparación Militar en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Preparación Militar.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoMilitarNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoMilitarNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Enseñanza Práctica en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Enseñanza Práctica.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoPracticaNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoPracticaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Básicas Específicas en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Básicas Específicas.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoBasicasNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoBasicaNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Ejercicio de la Profesión en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Ejercicio de la Profesión.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoEjercicioNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoEjercicioNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad total de profesores fijos no
     * profesionales estudiando de Instrucción en el país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * de Instrucción.
     */
    public int totalProfesoresContratadosNoProfesionalEstudiandoInstructorNac() {
        Integer num = r_repo.totalProfesoresContratadosNoProfesionalesEstudiandoInstructorNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores docentes totales
     * del país.
     *
     * @return <code>Integer</code> Profesores docentes totales.
     */
    public int profesoresDocentesTotalesNac() {
        Integer num = r_repo.profesoresDocentesTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores docentes hembras
     * totales del país.
     *
     * @return <code>Integer</code> Profesores docentes hembras totales.
     */
    public int profesoresDocentesHembrasTotalesNac() {
        Integer num = r_repo.profesoresDocentesHembrasTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos totales
     * del país.
     *
     * @return <code>Integer</code> Profesores fijos totales.
     */
    public int profesoresFijosTotalesNac() {
        Integer num = r_repo.profesoresFijosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos no
     * titulados totales del país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados totales.
     */
    public int profesoresFijosNoTituladosTotalesNac() {
        Integer num = r_repo.profesoresFijosNoTiTuladosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores fijos no
     * titulados estudiando totales del país.
     *
     * @return <code>Integer</code> Profesores fijos no titulados estudiando totales.
     */
    public int profesoresFijosNoTituladosEstudiandoTotalesNac() {
        Integer num = r_repo.profesoresFijosNoTituladosEstudiandoTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados
     * totales del país.
     *
     * @return <code>Integer</code> Profesores contratados totales.
     */
    public int profesoresContratadosTotalesNac() {
        Integer num = r_repo.profesoresContratadosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados no
     * profesionales totales del país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales totales.
     */
    public int profesoresContratadosNoProfesionalesTotalesNac() {
        Integer num = r_repo.profesoresContratadosNoProfesionalesTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de profesores contratados no
     * profesionales estudiando totales del país.
     *
     * @return <code>Integer</code> Profesores contratados no profesionales estudiando
     * totales.
     */
    public int profesoresContratadosNoProfesionalesEstudiandoTotalesNac() {
        Integer num = r_repo.profesoresContratadosNoProfesionalesEstudiandoTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de bibliotecarios totales del
     * país.
     *
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosTotalesNac() {
        Integer num = r_repo.bibliotecariosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de bibliotecarios hembras
     * totales del país.
     *
     * @return <code>Integer</code> Bibliotecarios hembras totales.
     */
    public int bibliotecariosHembrasTotalesNac() {
        Integer num = r_repo.bibliotecariosHembrasTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de instructores de arte
     * totales del país.
     *
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteTotalesNac() {
        Integer num = r_repo.instructoresArteTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de instructores de arte
     * hembras totales del país.
     *
     * @return <code>Integer</code> Instructores de Arte hembras totales.
     */
    public int instructoresArteHembrasTotalesNac() {
        Integer num = r_repo.instructoresArteHembrasTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de psicopedagogos totales del
     * país.
     *
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosTotalesNac() {
        Integer num = r_repo.psicopedagogosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de psicopedagogos hembras
     * totales del país.
     *
     * @return <code>Integer</code> Psicopedagogos hembras totales.
     */
    public int psicopedagogosHembrasTotalesNac() {
        Integer num = r_repo.psicopedagogosHembrasTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de alumnos maestros totales
     * del país.
     *
     * @return <code>Integer</code> Alumnos maestros totales.
     */
    public int alumnosMaestrosTotalesNac() {
        Integer num = r_repo.alumnosMaestrosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes totales del
     * país.
     *
     * @return <code>Integer</code> No docentes totales.
     */
    public int noDocentesTotalesNac() {
        Integer num = r_repo.noDocentesTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes hembras totales
     * del país.
     *
     * @return <code>Integer</code> No docentes hembras totales.
     */
    public int noDocentesHembrasTotalesNac() {
        Integer num = r_repo.noDocentesHembrasTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no profesores inactivos
     * totales del país.
     *
     * @return <code>Integer</code> Profesores inactivos totales.
     */
    public int profesoresInactivosTotalesNac() {
        Integer num = r_repo.profesoresInactivosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de otros docentes inactivos
     * totales del país.
     *
     * @return <code>Integer</code> Otros docentes inactivos totales.
     */
    public int otrosDocentesInactivosTotalesNac() {
        Integer num = r_repo.otrosDocentesInactivosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener la cantidad de no docentes inactivos
     * totales del país.
     *
     * @return <code>Integer</code> No docentes inactivos totales.
     */
    public int noDocentesInactivosTotalesNac() {
        Integer num = r_repo.noDocentesInactivosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes con edades menores
     * de 15 años del municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes con edades menores de 15 años.
     */
    public int menos15TotalesMcpio(long idM, long idCurso) {
        Integer num = r_repo.menos15TotalesMcpio(idM, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes hembras con edades
     * menores de 15 años del municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades menores de 15 años.
     */
    public int menos15HembrasTotalesMcpio(long idM, long idCurso) {
        Integer num = r_repo.menos15HembrasTotalesMcpio(idM, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes con edades entre
     * 15 y 16 años del municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes del curso diurno con edades entre 15 y
     * 16 años.
     */
    public int entre15Y16TotalesMcpio(long idM, long idCurso) {
        Integer num = r_repo.entre15Y16TotalesMcpio(idM, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes con edades entre 15 y
     * 16 años del municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades entre 15 y 16 años.
     */
    public int entre15Y16HembrasTotalesMcpio(long idM, long idCurso) {
        Integer num = r_repo.entre15Y16HembrasTotalesMcpio(idM, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes con edades mayores a
     * 16 años del municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes con edades mayores a 16 años.
     */
    public int mas16TotalesMcpio(long idM, long idCurso) {
        Integer num = r_repo.mas16TotalesMcpio(idM, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes hembras con edades
     * mayores a 16 años del municipio.
     *
     * @param idM     Identificador del municipio.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades mayores a 16 años.
     */
    public int mas16HembrasTotalesMcpio(long idM, long idCurso) {
        Integer num = r_repo.mas16HembrasTotalesMcpio(idM, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes con edades menores
     * de 15 años de la provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes con edades menores de 15 años.
     */
    public int menos15TotalesProv(long idP, long idCurso) {
        Integer num = r_repo.menos15TotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes hembras con edades
     * menores de 15 años de la provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades menores de 15 años.
     */
    public int menos15HembrasTotalesProv(long idP, long idCurso) {
        Integer num = r_repo.menos15HembrasTotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes con edades entre
     * 15 y 16 años de la provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes del curso diurno con edades entre 15 y
     * 16 años.
     */
    public int entre15Y16TotalesProv(long idP, long idCurso) {
        Integer num = r_repo.entre15Y16TotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes con edades entre 15 y
     * 16 años de la provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades entre 15 y 16 años.
     */
    public int entre15Y16HembrasTotalesProv(long idP, long idCurso) {
        Integer num = r_repo.entre15Y16HembrasTotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes con edades mayores a
     * 16 años de la provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes con edades mayores a 16 años.
     */
    public int mas16TotalesProv(long idP, long idCurso) {
        Integer num = r_repo.mas16TotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes hembras con edades
     * mayores a 16 años de la provincia.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades mayores a 16 años.
     */
    public int mas16HembrasTotalesProv(long idP, long idCurso) {
        Integer num = r_repo.mas16HembrasTotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes con edades menores
     * de 15 años del país.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes con edades menores de 15 años.
     */
    public int menos15TotalesNac(long idCurso) {
        Integer num = r_repo.menos15TotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes hembras con edades
     * menores de 15 años del país.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades menores de 15 años.
     */
    public int menos15HembrasTotalesNac(long idCurso) {
        Integer num = r_repo.menos15HembrasTotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total de estudiantes con edades entre
     * 15 y 16 años del país.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes del curso diurno con edades entre 15 y
     * 16 años.
     */
    public int entre15Y16TotalesNac(long idCurso) {
        Integer num = r_repo.entre15Y16TotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes con edades entre 15 y
     * 16 años del país.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades entre 15 y 16 años.
     */
    public int entre15Y16HembrasTotalesNac(long idCurso) {
        Integer num = r_repo.entre15Y16HembrasTotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes con edades mayores a
     * 16 años del país.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes con edades mayores a 16 años.
     */
    public int mas16TotalesNac(long idCurso) {
        Integer num = r_repo.mas16TotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener el total estudiantes hembras con edades
     * mayores a 16 años del país.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Estudiantes hembras con edades mayores a 16 años.
     */
    public int mas16HembrasTotalesNac(long idCurso) {
        Integer num = r_repo.mas16HembrasTotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener de un municipio la matrícula total de un
     * determinado tipo de curso.
     *
     * @param idMunicipio Identificador del municipio.
     * @param idCurso     Identificador del curso.
     * @return <code>int</code> Matrícula total.
     */
    public int matriculasTotalesMcpio(long idMunicipio, long idCurso) {
        Integer num = r_repo.matriculasTotalesMcpio(idMunicipio, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener de un municipio la matrícula total de
     * hembras de un determinado tipo de curso.
     *
     * @param idMunicipio Identificador del municipio.
     * @param idCurso     Identificador del curso.
     * @return <code>int</code> Matrícula total de hembras.
     */
    public int matriculasTotalesHembrasMcpio(long idMunicipio, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasMcpio(idMunicipio, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener de un municipio los nuevos ingresos
     * totales de un determinado tipo de curso.
     *
     * @param idMunicipio Identificador del municipio.
     * @param idCurso     Identificador del curso.
     * @return <code>int</code> Nuevos Ingresos totales.
     */
    public int nuevosIngresosTotalesMcpio(long idMunicipio, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesMcpio(idMunicipio, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los internos totales de un municipio.
     *
     * @param idMunicipio Identificador del municipio.
     * @return <code>int</code> Internos totales.
     */
    public int internosTotalesMcpio(long idMunicipio) {
        Integer num = r_repo.internosTotalesMcpio(idMunicipio);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los seminternos totales de un municipio.
     *
     * @param idMunicipio Identificador del municipio.
     * @return <code>int</code> Seminternos totales.
     */
    public int seminternosTotalesMcpio(long idMunicipio) {
        Integer num = r_repo.seminternosTotalesMcpio(idMunicipio);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener de una provincia la matrícula total de un
     * determinado tipo de curso.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesProv(long idP, long idCurso) {
        Integer num = r_repo.matriculasTotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener de una provincia la matrícula total de
     * hembras de un determinado tipo de curso.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasProv(long idP, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener de una provincia los nuevos ingresos
     * totales de un determinado tipo de curso.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>int</code> Nuevos Ingresos totales.
     */
    public int nuevosIngresosTotalesProv(long idP, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los internos totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>int</code> Internos totales.
     */
    public int internosTotalesProv(long idP) {
        Integer num = r_repo.internosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los seminternos totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>int</code> Seminternos totales.
     */
    public int seminternosTotalesProv(long idP) {
        Integer num = r_repo.seminternosTotalesProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del país la matrícula total de un
     * determinado tipo de curso.
     *
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesNac(long idCurso) {
        Integer num = r_repo.matriculasTotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del país la matrícula total de hembras de
     * un determinado tipo de curso.
     *
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasNac(long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del país los nuevos ingresos totales de un
     * determinado tipo de curso.
     *
     * @param idCurso Identificador del curso.
     * @return <code>int</code> Nuevos Ingresos totales.
     */
    public int nuevosIngresosTotalesNac(long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los internos totales del país.
     *
     * @return <code>int</code> Internos totales.
     */
    public int internosTotalesNac() {
        Integer num = r_repo.internosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener los seminternos totales del país.
     *
     * @return <code>int</code> Seminternos totales.
     */
    public int seminternosTotalesNac() {
        Integer num = r_repo.seminternosTotalesNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural de un municipio la
     * matrícula total de un determinado tipo de curso.
     *
     * @param idMunicipio Identificador del municipio.
     * @param idCurso     Identificador del curso.
     * @return <code>int</code> Matrícula total.
     */
    public int matriculasTotalesTurquinoMcpio(long idMunicipio, long idCurso) {
        Integer num = r_repo.matriculasTotalesTurquinoMcpio(idMunicipio, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural de un municipio la
     * matrícula total de hembras de un determinado tipo de curso.
     *
     * @param idMunicipio Identificador del municipio.
     * @param idCurso     Identificador del curso.
     * @return <code>int</code> Matrícula total de hembras.
     */
    public int matriculasTotalesHembrasTurquinoMcpio(long idMunicipio, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasTurquinoMcpio(idMunicipio, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural de un municipio los
     * nuevos ingresos totales de un determinado tipo de curso.
     *
     * @param idMunicipio Identificador del municipio.
     * @param idCurso     Identificador del curso.
     * @return <code>int</code> Nuevos Ingresos totales.
     */
    public int nuevosIngresosTotalesTurquinoMcpio(long idMunicipio, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesTurquinoMcpio(idMunicipio, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los internos totales de
     * un municipio.
     *
     * @param idMunicipio Identificador del municipio.
     * @return <code>int</code> Internos totales.
     */
    public int internosTotalesTurquinoMcpio(long idMunicipio) {
        Integer num = r_repo.internosTotalesTurquinoMcpio(idMunicipio);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los seminternos totales
     * de un municipio.
     *
     * @param idMunicipio Identificador del municipio.
     * @return <code>int</code> Seminternos totales.
     */
    public int seminternosTotalesTurquinoMcpio(long idMunicipio) {
        Integer num = r_repo.seminternosTotalesTurquinoMcpio(idMunicipio);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural de una provincia la
     * matrícula total de un determinado tipo de curso.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesTurquinoProv(long idP, long idCurso) {
        Integer num = r_repo.matriculasTotalesTurquinoProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural de una provincia la
     * matrícula total de hembras de un determinado tipo de curso.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasTurquinoProv(long idP, long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasTurquinoProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural de una provincia los
     * nuevos ingresos totales de un determinado tipo de curso.
     *
     * @param idP     Identificador de la provincia.
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Nuevos Ingresos totales.
     */
    public int nuevosIngresosTotalesTurquinoProv(long idP, long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesTurquinoProv(idP, idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los internos totales de
     * una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Internos totales.
     */
    public int internosTotalesTurquinoProv(long idP) {
        Integer num = r_repo.internosTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los seminternos totales
     * de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Seminternos totales.
     */
    public int seminternosTotalesTurquinoProv(long idP) {
        Integer num = r_repo.seminternosTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural del país la matrícula
     * total de un determinado tipo de curso.
     *
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesTurquinoNac(long idCurso) {
        Integer num = r_repo.matriculasTotalesTurquinoNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural del país la matrícula
     * total de hembras de un determinado tipo de curso.
     *
     * @param idCurso Identificador del Curso.
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasTurquinoNac(long idCurso) {
        Integer num = r_repo.matriculasTotalesHembrasTurquinoNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural del país los nuevos
     * ingresos totales de un determinado tipo de curso.
     *
     * @param idCurso Identificador del curso.
     * @return <code>Integer</code> Nuevos Ingresos totales.
     */
    public int nuevosIngresosTotalesTurquinoNac(long idCurso) {
        Integer num = r_repo.nuevosIngresosTotalesTurquinoNac(idCurso);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los internos totales del
     * país.
     *
     * @return <code>Integer</code> Internos totales.
     */
    public int internosTotalesTurquinoNac() {
        Integer num = r_repo.internosTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los seminternos totales
     * del país.
     *
     * @return <code>Integer</code> Seminternos totales.
     */
    public int seminternosTotalesTurquinoNac() {
        Integer num = r_repo.seminternosTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de bibliotecarios totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.bibliotecariosTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de bibliotecarios hembras totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Bibliotecarios hembras totales.
     */
    public int bibliotecariosHembrasTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.bibliotecariosHembrasTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de
     * instructores de arte totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.instructoresArteTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de instructores de arte hembras totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteHembrasTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.instructoresArteHembrasTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de psicopedagogos totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.psicopedagogosTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de psicopedagogos hembras totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosHembrasTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.psicopedagogosHembrasTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de alumnos
     * maestros totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Alumnos maestros totales.
     */
    public int alumnosMaestrosTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.alumnosMaestrosTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de profesores
     * docentes totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores docentes totales.
     */
    public int profesoresDocentesTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.profesoresDocentesTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de profesores
     * docentes hembras totales de un municipio.
     *
     * @param idM Identificador del municipio.
     * @return <code>Integer</code> Profesores docentes hembras totales.
     */
    public int profesoresDocentesHembrasTotalesTurquinoMcpio(long idM) {
        Integer num = r_repo.profesoresDocentesHembrasTotalesTurquinoMcpio(idM);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de
     * bibliotecarios totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosTotalesTurquinoProv(long idP) {
        Integer num = r_repo.bibliotecariosTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de bibliotecarios hembras totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosHembrasTotalesTurquinoProv(long idP) {
        Integer num = r_repo.bibliotecariosHembrasTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de instructores de arte totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteTotalesTurquinoProv(long idP) {
        Integer num = r_repo.instructoresArteTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de instructores de arte hembras totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteHembrasTotalesTurquinoProv(long idP) {
        Integer num = r_repo.instructoresArteHembrasTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de psicopedagogos totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosTotalesTurquinoProv(long idP) {
        Integer num = r_repo.psicopedagogosTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de psicopedagogos hembras totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosHembrasTotalesTurquinoProv(long idP) {
        Integer num = r_repo.psicopedagogosHembrasTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de alumnos maestros totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Alumnos maestros totales.
     */
    public int alumnosMaestrosTotalesTurquinoProv(long idP) {
        Integer num = r_repo.alumnosMaestrosTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de profesores docentes totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores docentes totales.
     */
    public int profesoresDocentesTotalesTurquinoProv(long idP) {
        Integer num = r_repo.profesoresDocentesTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de profesores
     * docentes hembras totales de una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>Integer</code> Profesores docentes hembras totales.
     */
    public int profesoresDocentesHembrasTotalesTurquinoProv(long idP) {
        Integer num = r_repo.profesoresDocentesHembrasTotalesTurquinoProv(idP);

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de
     * bibliotecarios totales del país.
     *
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosTotalesTurquinoNac() {
        Integer num = r_repo.bibliotecariosTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de bibliotecarios hembras totales del país.
     *
     * @return <code>Integer</code> Bibliotecarios totales.
     */
    public int bibliotecariosHembrasTotalesTurquinoNac() {
        Integer num = r_repo.bibliotecariosHembrasTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de
     * instructores de arte totales del país.
     *
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteTotalesTurquinoNac() {
        Integer num = r_repo.instructoresArteTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de instructores de arte hembras totales del país.
     *
     * @return <code>Integer</code> Instructores de Arte totales.
     */
    public int instructoresArteHembrasTotalesTurquinoNac() {
        Integer num = r_repo.instructoresArteHembrasTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de psicopedagogos totales del país.
     *
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosTotalesTurquinoNac() {
        Integer num = r_repo.psicopedagogosTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de psicopedagogos hembras totales del país.
     *
     * @return <code>Integer</code> Psicopedagogos totales.
     */
    public int psicopedagogosHembrasTotalesTurquinoNac() {
        Integer num = r_repo.psicopedagogosHembrasTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de alumnos
     * maestros totales del país.
     *
     * @return <code>Integer</code> Alumnos maestros totales.
     */
    public int alumnosMaestrosTotalesTurquinoNac() {
        Integer num = r_repo.alumnosMaestrosTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de profesores
     * docentes totales del país.
     *
     * @return <code>Integer</code> Profesores docentes totales.
     */
    public int profesoresDocentesTotalesTurquinoNac() {
        Integer num = r_repo.profesoresDocentesTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la cantidad de profesores
     * docentes hembras totales del país.
     *
     * @return <code>Integer</code> Profesores docentes hembras totales.
     */
    public int profesoresDocentesHembrasTotalesTurquinoNac() {
        Integer num = r_repo.profesoresDocentesHembrasTotalesTurquinoNac();

        if (num == null) {
            return 0;
        }
        return num;
    }
}