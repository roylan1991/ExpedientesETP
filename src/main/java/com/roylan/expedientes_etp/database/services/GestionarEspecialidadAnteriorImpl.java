package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.EspecialidadAnterior;
import com.roylan.expedientes_etp.database.repositories.EspecialidadAnteriorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a una especialidad anterior.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarEspecialidadAnteriorImpl implements IGestionarService<EspecialidadAnterior> {

    @Autowired
    private EspecialidadAnteriorRepository e_repo;

    public GestionarEspecialidadAnteriorImpl() {
    }

    /**
     * Esta funcionalidad permite registar una nueva especialidad anterior.
     *
     * @param e Nueva especialidad anterior.
     */
    public void adicionar(EspecialidadAnterior e) {
        e_repo.save(e);
    }

    /**
     * Esta funcionalidad permite obtener una especialidad anterior mediante su identificador.
     *
     * @param idE Identificador de la especialidad anterior.
     * @return <code>EspecialidadAnterior</code> Especialidad anterior obtenida.
     *
     * <code>null</code> Si no existe la especialidad anterior.
     */
    public EspecialidadAnterior obtenerId(long idE) {
        return e_repo.findByIdEspAnterior(idE);
    }

    /**
     * Esta funcionalidad permite editar una especialidad anterior.
     *
     * @param idE     Identificador de la especialidad anterior a editar.
     * @param n_datos Nuevos datos de la especialidad anterior.
     */
    public void update(long idE, EspecialidadAnterior n_datos) {
        e_repo.updateEspecialidadAnterior(idE, n_datos.getMatriculaInicial(), n_datos.getAltasTotal(), n_datos.getAltasPorTraslado(), n_datos.getMatricInicialAjustada(), n_datos.getMatriculaFinal(), n_datos.getAprobados(), n_datos.getBajasTotal(), n_datos.getBajasPorTraslado());
    }

    /**
     * Esta funcionalidad permite eliminar una especialidad anterior.
     *
     * @param idE Identificador de la especialidad anterior.
     */
    public void eliminar(long idE) {
        EspecialidadAnterior e = obtenerId(idE);

        e_repo.delete(e);
    }

    /**
     * Esta funcionalidad permite listar todas las especialidades anteriores registradas.
     *
     * @return <code>List</code> Todas las especialidades anteriores registradas.
     */
    public List<EspecialidadAnterior> listar() {
        return e_repo.findAll();
    }

    /**
     * Esta funcionalidad permite obtener las especialidades anteriores de un municipio según un determinado curso y familia.
     *
     * @param idM     Identificador del municipio.
     * @param idC     Identificador del curso.
     * @param familia Tipo de familia.
     * @return <code>List</code> Especialidades Anteriores.
     */
    public List<EspecialidadAnterior> especialidadesAnterioresFamiliaMcpio(long idM, long idC, String familia) {
        return e_repo.especialidadesAnterioresFamiliaMcpio(idM, idC, familia);
    }

    /**
     * Esta funcionalidad permite obtener las especialidades anteriores de una provincia según un determinado curso y familia.
     *
     * @param idP     Identificador de la provincia.
     * @param idC     Identificador del curso.
     * @param familia Tipo de familia.
     * @return <code>List</code> Especialidades Anteriores.
     */
    public List<EspecialidadAnterior> especialidadesAnterioresFamiliaProv(long idP, long idC, String familia) {
        return e_repo.especialidadesAnterioresFamiliaProv(idP, idC, familia);
    }

    /**
     * Esta funcionalidad permite obtener las especialidades anteriores del país según un determinado curso y familia.
     *
     * @param idC     Identificador del curso.
     * @param familia Tipo de familia.
     * @return <code>List</code> Especialidades Anteriores.
     */
    public List<EspecialidadAnterior> especialidadesAnterioresFamiliaNac(long idC, String familia) {
        return e_repo.especialidadesAnterioresFamiliaNac(idC, familia);
    }

    /**
     * Esta funcionalidad permite obtener las especialidades anteriores de un centro.
     *
     * @param idC Identificador del centro.
     * @return <code>List</code> Especialidades Anteriores.
     */
    public List<EspecialidadAnterior> especialidadesAnterioresCentro(long idC) {
        return e_repo.especialidadesAnterioresCentro(idC);
    }

    /**
     * Esta funcionalidad permite obtener las matrículas iniciales totales según un determinado año de estudio, nivel y curso.
     *
     * @param anno  Identificador del año de estudio.
     * @param nivel Identificador del Nivel.
     * @param curso Identificador del Curso.
     * @return <code>int</code> Matrículas iniciales.
     */
    public int matriculasInicialesSegunAnnoNivelCurso(long anno, long nivel, long curso) {
        Integer valor = e_repo.matriculasInicialesSegunAnnoNivelCurso(anno, nivel, curso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los aprobados totoles según un determinado año de estudio, nivel y curso.
     *
     * @param anno  Identificador del año de estudio.
     * @param nivel Identificador del Nivel.
     * @param curso Identificador del Curso.
     * @return <code>int</code> Aprobados.
     */
    public int aprobadosSegunAnnoNivelCurso(long anno, long nivel, long curso) {
        Integer valor = e_repo.aprobadosSegunAnnoNivelCurso(anno, nivel, curso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }
}