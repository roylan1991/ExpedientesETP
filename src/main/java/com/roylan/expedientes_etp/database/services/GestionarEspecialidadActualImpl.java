package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.EspecialidadActual;
import com.roylan.expedientes_etp.database.repositories.EspecialidadActualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a una especialidad
 * actual.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarEspecialidadActualImpl implements IGestionarService<EspecialidadActual> {

    @Autowired
    private EspecialidadActualRepository e_repo;

    public GestionarEspecialidadActualImpl() {
    }

    /**
     * Esta funcionalidad permite registar una nueva especialidad actual.
     *
     * @param e Nueva especialidad actual.
     */
    public void adicionar(EspecialidadActual e) {
        e_repo.save(e);
    }

    /**
     * Esta funcionalidad permite obtener una especialidad actual mediante su identificador.
     *
     * @param idE Identificador de la especialidad actual.
     * @return <code>EspecialidadActual</code> Especialidad actual obtenida.
     *
     * <code>null</code> Si no existe la especialidad actual.
     */
    public EspecialidadActual obtenerId(long idE) {
        return e_repo.findByIdEspActual(idE);
    }

    /**
     * Esta funcionalidad permite editar una especialidad actual.
     *
     * @param idE     Identificador de la especialidad actual a editar.
     * @param n_datos Nuevos datos de la especialidad actual.
     */
    public void update(long idE, EspecialidadActual n_datos) {
        e_repo.updateEspecialidadActual(idE, n_datos.getCantGrupos(), n_datos.getnIngPromovidosTotal(), n_datos.getnIngPromovidosPorTraslado(), n_datos.getRepitentesTotal(), n_datos.getRepitentesPorTraslado(), n_datos.getReingresos(), n_datos.getMatriculaActualTotal(), n_datos.getMatriculaActualHembras());
    }

    /**
     * Esta funcionalidad permite eliminar una especialidad actual.
     *
     * @param idE Identificador de la especialidad actual.
     */
    public void eliminar(long idE) {
        EspecialidadActual e = obtenerId(idE);

        e_repo.delete(e);
    }

    /**
     * Esta funcionalidad permite listar todas las especialidades actuales registradas.
     *
     * @return <code>List</code> Todas las especialidades actuales registradas.
     */
    public List<EspecialidadActual> listar() {
        return e_repo.findAll();
    }

    /**
     * Esta funcionalidad permite obtener las especialidades actuales de un municipio según un determinado curso y familia.
     *
     * @param idM     Identificador del municipio.
     * @param idC     Identificador del curso.
     * @param familia Tipo de familia.
     * @return <code>List</code> Especialidades Actuales.
     */
    public List<EspecialidadActual> especialidadesActualesFamiliaMcpio(long idM, long idC, String familia) {
        return e_repo.especialidadesActualesFamiliaMcpio(idM, idC, familia);
    }

    /**
     * Esta funcionalidad permite obtener las especialidades actuales de una provincia según un determinado curso y familia.
     *
     * @param idP     Identificador de la provincia.
     * @param idC     Identificador del curso.
     * @param familia Tipo de familia.
     * @return <code>List</code> Especialidades Actuales.
     */
    public List<EspecialidadActual> especialidadesActualesFamiliaProv(long idP, long idC, String familia) {
        return e_repo.especialidadesActualesFamiliaProv(idP, idC, familia);
    }

    /**
     * Esta funcionalidad permite obtener las especialidades actuales del país según un determinado curso y familia.
     *
     * @param idC     Identificador del curso.
     * @param familia Tipo de familia.
     * @return <code>List</code> Especialidades Actuales.
     */
    public List<EspecialidadActual> especialidadesActualesFamiliaNac(long idC, String familia) {
        return e_repo.especialidadesActualesFamiliaNac(idC, familia);
    }

    /**
     * Esta funcionalidad permite obtener las especialidades actuales de un centro.
     *
     * @param idC Identificador del centro.
     * @return <code>List</code> Especialidades Anctuales.
     */
    public List<EspecialidadActual> especialidadesActualesCentro(long idC) {
        return e_repo.especialidadesActualesCentro(idC);
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de un centro según las especialidades de una determinada rama y curso.
     *
     * @param idE   Identificador del centro.
     * @param curso Tipo de Curso(Diurno-V/Nocturno).
     * @param rama  Rama de estudio.
     * @return <code>int</code> Matrícula actual.
     */
    public int matriculasActualesCentroRama(long idE, String curso, String rama) {
        Integer valor = e_repo.matriculasActualesCentroRama(idE, curso, rama);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de hembras de un centro según las especialidades de una determinada rama y curso.
     *
     * @param idE   Identificador del centro.
     * @param curso Tipo de Curso(Diurno-V/Nocturno).
     * @param rama  Rama de estudio.
     * @return <code>int</code> Matrícula actual de hembras.
     */
    public int matriculasActualesHembrasCentroRama(long idE, String curso, String rama) {
        Integer valor = e_repo.matriculasActualesHembrasCentroRama(idE, curso, rama);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de un centro según las especialidades de una determinada rama y curso.
     *
     * @param idE   Identificador del centro.
     * @param curso Tipo de Curso(Diurno-V/Nocturno).
     * @param rama  Rama de estudio.
     * @return <code>int</code> Nuevos Ingresos.
     */
    public int nuevosIngresosTotalCentroRama(long idE, String curso, String rama) {
        Integer valor = e_repo.nuevosIngresosTotalCentroRama(idE, curso, rama);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de un municipio según las especialidades de una determinada rama y curso.
     *
     * @param idM  Identificador del municipio.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual municipal.
     */
    public int matriculasActualesMcpioRama(long idM, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesMcpioRama(idM, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de hembras de un municipio según las especialidades de una determinada rama y curso.
     *
     * @param idM  Identificador del municipio.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual de hembras municipal.
     */
    public int matriculasActualesHembrasMcpioRama(long idM, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesHembrasMcpioRama(idM, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de un municipio según las especialidades de una determinada rama y curso.
     *
     * @param idM  Identificador del municipio.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Nuevos ingresos municipal.
     */
    public int nuevosIngresosTotalMcpioRama(long idM, String rama, long idC) {
        Integer valor = e_repo.nuevosIngresosTotalMcpioRama(idM, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de una provincia según las especialidades de una determinada rama y curso.
     *
     * @param idP  Identificador de la provincia.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual provincial.
     */
    public int matriculasActualesProvRama(long idP, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesProvRama(idP, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de hembras de una provincia según las especialidades de una determinada rama y curso.
     *
     * @param idP  Identificador de la provincia.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual de hembras provincial.
     */
    public int matriculasActualesHembrasProvRama(long idP, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesHembrasProvRama(idP, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de una provincia según las especialidades de una determinada rama y curso.
     *
     * @param idP  Identificador de la provincia.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Nuevos ingresos provincial.
     */
    public int nuevosIngresosTotalProvRama(long idP, String rama, long idC) {
        Integer valor = e_repo.nuevosIngresosTotalProvRama(idP, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual del país según las especialidades de una determinada rama y curso.
     *
     * @param rama Rama de estudio.
     * @param idC  Identificador del Curso.
     * @return <code>int</code> Matrícula actual nacional.
     */
    public int matriculasActualesNacRama(String rama, long idC) {
        Integer valor = e_repo.matriculasActualesNacRama(rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener la matrícula actual de hembras del país según las especialidades de una determinada rama y curso.
     *
     * @param rama Rama de estudio.
     * @param idC  Identificador del Curso.
     * @return <code>int</code> Matrícula actual de hembras nacional.
     */
    public int matriculasActualesHembrasNacRama(String rama, long idC) {
        Integer valor = e_repo.matriculasActualesHembrasNacRama(rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos del país según las especialidades de una determinada rama y curso.
     *
     * @param rama Rama de estudio.
     * @param idC  Identificador del Curso.
     * @return <code>int</code> Nuevos ingresos nacional.
     */
    public int nuevosIngresosTotalNacRama(String rama, long idC) {
        Integer valor = e_repo.nuevosIngresosTotalNacRama(rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la matrícula actual de un municipio según las especialidades de una determinada rama y curso.
     *
     * @param idM  Identificador del municipio.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual turquino municipal.
     */
    public int matriculasActualesMcpioTurquinoRama(long idM, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesMcpioTurquinoRama(idM, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la matrícula actual de hembras de un municipio según las especialidades de una determinada rama y curso.
     *
     * @param idM  Identificador del municipio.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual de hembras turquino municipal.
     */
    public int matriculasActualesHembrasMcpioTurquinoRama(long idM, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesHembrasMcpioTurquinoRama(idM, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los nuevos ingresos de un municipio según las especialidades de una determinada rama y curso.
     *
     * @param idM  Identificador del municipio.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Nuevos ingresos turquino municipal.
     */
    public int nuevosIngresosTotalMcpioTurquinoRama(long idM, String rama, long idC) {
        Integer valor = e_repo.nuevosIngresosTotalMcpioTurquinoRama(idM, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la matrícula actual de una provincia según las especialidades de una determinada rama y curso.
     *
     * @param idP  Identificador de la provincia.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual turquino provincial.
     */
    public int matriculasActualesProvTurquinoRama(long idP, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesProvTurquinoRama(idP, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la matrícula actual de hembras de una provincia según las especialidades de una determinada rama y
     * curso.
     *
     * @param idP  Identificador de la provincia.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Matrícula actual de hembras turquino
     * provincial.
     */
    public int matriculasActualesHembrasProvTurquinoRama(long idP, String rama, long idC) {
        Integer valor = e_repo.matriculasActualesHembrasProvTurquinoRama(idP, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los nuevos ingresos de una provincia según las especialidades de una determinada rama y curso.
     *
     * @param idP  Identificador de la provincia.
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Nuevos ingresos turquino provincial.
     */
    public int nuevosIngresosTotalProvTurquinoRama(long idP, String rama, long idC) {
        Integer valor = e_repo.nuevosIngresosTotalProvTurquinoRama(idP, rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la matrícula actual del país según las especialidades de una determinada rama y curso.
     *
     * @param rama Rama de estudio.
     * @param idC  Identificador del Curso.
     * @return <code>int</code> Matrícula actual turquino nacional.
     */
    public int matriculasActualesNacTurquinoRama(String rama, long idC) {
        Integer valor = e_repo.matriculasActualesNacTurquinoRama(rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural la matrícula actual de hembras del país según las especialidades de una determinada rama y curso.
     *
     * @param rama Rama de estudio.
     * @param idC  Identificador del Curso.
     * @return <code>int</code> Matrícula actual de hembras turquino nacional.
     */
    public int matriculasActualesHembrasNacTurquinoRama(String rama, long idC) {
        Integer valor = e_repo.matriculasActualesHembrasNacTurquinoRama(rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener del sector rural los nuevos ingresos del
     * país según las especialidades de una determinada rama y curso.
     *
     * @param rama Rama de estudio.
     * @param idC  Identificador del curso.
     * @return <code>int</code> Nuevos ingresos turquino nacional.
     */
    public int nuevosIngresosTotalNacTurquinoRama(String rama, long idC) {
        Integer valor = e_repo.nuevosIngresosTotalNacTurquinoRama(rama, idC);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas actuales totales según un determinado año de estudio, nivel y curso.
     *
     * @param anno  Identificador del año de estudio.
     * @param nivel Identificador del nivel.
     * @param curso Identificador del curso.
     * @return <code>int</code> Matrículas actuales.
     */
    public int matriculasActualesSegunAnnoNivelCurso(long anno, long nivel, long curso) {
        Integer valor = e_repo.matriculasActualesSegunAnnoNivelCurso(anno, nivel, curso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener las especialidades actuales según un año de estudio y nivel.
     *
     * @param anno  Identificador del año de estudio.
     * @param nivel Identificador del nivel.
     * @return <code>List</code> Cantidad de Grupos.
     */
    public List<EspecialidadActual> espActualSegunAnnoCursoNivel(long anno, long nivel) {
        return e_repo.espActualSegunAnnoCursoNivel(anno, nivel);
    }


    /**
     * Esta funcionalidad permite obtener la matrícula total de una familia.
     *
     * @param idF Identificador de la familia.
     * @param idC Identificador del curso.
     * @return <code>int</code> Matrícula total.
     */
    public int matriculaTotalNacSegunFamilia(long idF, long idC) {
        Integer valor = e_repo.matriculaTotalNacSegunFamilia(idF, idC);

        if (valor == null) {
            return 0;
        }

        return valor;
    }
}
