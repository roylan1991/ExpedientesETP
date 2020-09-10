package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Caratula;
import com.roylan.expedientes_etp.database.entities.RegimenEstudio;
import com.roylan.expedientes_etp.database.repositories.CaratulaRepository;
import com.roylan.expedientes_etp.database.repositories.RegimenEstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a la carátula de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarCaratulaImpl implements IGestionarService<Caratula> {

    @Autowired
    private CaratulaRepository c_repo;

    @Autowired
    private RegimenEstudioRepository r_repo;

    public GestionarCaratulaImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nueva carátula.
     *
     * @param c Nueva carátula.
     */
    public void adicionar(Caratula c) {
        c_repo.save(c);
    }

    /**
     * Esta funcionalidad permite obtener la carátula de un centro mediante su identificador.
     *
     * @param idC Identificador de la carátula.
     * @return <code>Caratula</code> Carátula obtenida.
     *
     * <code>null</code> Si no existe la carátula.
     */
    public Caratula obtenerId(long idC) {
        return c_repo.findByIdCaratula(idC);
    }

    /**
     * Esta funcionalidad permite editar la carátula de un centro.
     *
     * @param idC     Identificador de la carátula a editar.
     * @param n_datos Nuevos datos de la carátula.
     */
    public void update(long idC, Caratula n_datos) {
        c_repo.updateCaratula(idC, n_datos.getMatriculaInicialTM(), n_datos.getMatriculaInicialOC(), n_datos.getMatriculaInicialCPT_TM(), n_datos.getMatriculaFinalTM(), n_datos.getMatriculaFinalOC(), n_datos.getMatriculaFinalCPT_TM());
    }

    /**
     * Esta funcionalidad permite editar el régimen de estudio de la carátula de un centro.
     *
     * @param idC     Identificador de la carátula a editar.
     * @param n_datos Datos del régimen de estudio.
     */
    @Cacheable(cacheNames = "regimen_caratula")
    public void updateRegimenCaratula(long idC, Caratula n_datos) {
        Caratula c = obtenerId(idC);

        RegimenEstudio re = n_datos.getRegimenTM();
        r_repo.updateRegimenCaratula(c.getRegimenTM().getIdRegimen(), re.getInternos(), re.getInternosHembras(), re.getSeminternos(), re.getSeminternosHembras());

        re = n_datos.getRegimenOC();
        r_repo.updateRegimenCaratula(c.getRegimenOC().getIdRegimen(), re.getInternos(), re.getInternosHembras(), re.getSeminternos(), re.getSeminternosHembras());
    }

    /**
     * Esta funcionalidad permite eliminar la carátula de un centro.
     *
     * @param idC Identificador de la carátula.
     */
    public void eliminar(long idC) {
        Caratula p = obtenerId(idC);

        c_repo.delete(p);
    }

    /**
     * Esta funcionalidad permite listar todas las carátulas de centros registradas.
     *
     * @return <code>List</code> Todos las carátulas registradas.
     */
    public List<Caratula> listar() {
        return c_repo.findAll();
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de las especialidades actuales de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Matrículas totales.
     */
    public int matriculasTotalesDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.matriculasTotalesDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas totales de hembras de las especialidades actuales de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Matrículas totales de hembras.
     */
    public int matriculasTotalesHembrasDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.matriculasTotalesHembrasDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos de las planillas de datos de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Nuevos ingresos.
     */
    public int nuevosIngresosTotalesDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.nuevosIngresosTotalesDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los nuevos ingresos hembras de las planillas de datos de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Nuevos ingresos hembras.
     */
    public int nuevosIngresosTotalesHembrasDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.nuevosIngresosTotalesHembrasDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener las matrículas finales totales de las especialidades actuales de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Matrículas finales totales.
     */
    public int matriculasFinalesTotalesDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.matriculasFinalesTotalesDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los aprobados totales de las especialidades actuales de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Aprobados totales.
     */
    public int aprobadosTotalesDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.aprobadosTotalesDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }

    /**
     * Esta funcionalidad permite obtener los graduados totales de las planillas de datos de un centro.
     *
     * @param idC     Identificador del centro.
     * @param idNivel Identificador del Nivel(TM-OC).
     * @param idCurso Identificador del Curso(Diurno-V/Nocturno).
     * @return <code>Integer</code> Graduados totales.
     */
    public int graduadosTotalesDeCentro(long idC, long idNivel, long idCurso) {
        Integer valor = c_repo.graduadosTotalesDeCentro(idC, idNivel, idCurso);

        if (valor == null) {
            return 0;
        }
        return valor;
    }
}