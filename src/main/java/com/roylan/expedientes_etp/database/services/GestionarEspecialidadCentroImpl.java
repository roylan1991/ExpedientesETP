package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.repositories.EspecialidadCentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a una especialidad de un
 * centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarEspecialidadCentroImpl implements IGestionarService<EspecialidadCentro> {

    @Autowired
    private EspecialidadCentroRepository e_repo;

    @Autowired
    private GestionarPlanillaDatosImpl planillas;

    public GestionarEspecialidadCentroImpl() {
    }

    /**
     * Esta funcionalidad permite registar una nueva especialidad a un centro.
     *
     * @param e Nueva especialidad.
     */
    @CacheEvict(cacheNames = {"lst_esp_centro", "lst_esp_centro_sde", "lst_esp_centro_sc", "lst_esp_centro_scap", "lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void adicionar(EspecialidadCentro e) {
        e_repo.save(e);

        Centro centro = e.getCentro();
        planillas.actualizarProgreso(centro);
    }

    /**
     * Esta funcionalidad permite obtener una especialidad de un centro mediante su
     * identificador.
     *
     * @param idE Identificador de la especialidad.
     * @return <code>EspecialidadCentro</code> EspecialidadCentro obtenida.
     *
     * <code>null</code> Si no existe la especialidad.
     */
    @Cacheable(cacheNames = "esp_centro_id")
    public EspecialidadCentro obtenerId(long idE) {
        return e_repo.findByIdEspecialidad(idE);
    }

    /**
     * Esta funcionalidad permite editar una especialidad de un centro.
     *
     * @param idEsp   Identificador de la especialidad a editar.
     * @param n_datos Nuevos datos de la especialidad.
     */
    @CacheEvict(cacheNames = {"lst_esp_centro", "esp_centro_id", "lst_esp_centro_sde", "lst_esp_centro_sc", "lst_esp_centro_scap", "lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void update(long idEsp, EspecialidadCentro n_datos) {
        e_repo.updateCaptadaEspecialidadCentro(idEsp, n_datos.getFueCaptada());
    }

    /**
     * Esta funcionalidad permite eliminar una especialidad de un centro.
     *
     * @param idE Identificador de la especialidad.
     */
    @CacheEvict(cacheNames = {"lst_esp_centro", "esp_centro_id", "lst_esp_centro_sde", "lst_esp_centro_sc", "lst_esp_centro_scap", "lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void eliminar(long idE) {
        EspecialidadCentro ec = obtenerId(idE);
        PlanillaDatos pd = planillas.obtenerSegunEspCentro(ec.getIdEspecialidad());

        e_repo.delete(ec);

        if (pd != null) {
            planillas.eliminar(pd.getIdPlanilla());
            planillas.actualizarCaratula(pd);
        } else {
            planillas.actualizarProgreso(ec.getCentro());
        }
    }

    /**
     * Esta funcionalidad permite listar todas las especialidades de un centro
     * registradas.
     *
     * @return <code>List</code> Todas las especialidades registradas.
     */
    @Cacheable(cacheNames = "lst_esp_centro")
    public List<EspecialidadCentro> listar() {
        return e_repo.findAll();
    }

    /**
     * Esta funcionalidad permite obtener una especialidad de un centro según su código, duración y escolaridad.
     *
     * @param centro Centro.
     * @param codEsp Código de la especialidad.
     * @param dur    Duración.
     * @param esc    Escolaridad.
     * @return <code>EspecialidadCentro</code> Especialidad de un centro.
     */
    @Cacheable(cacheNames = "lst_esp_centro_sde")
    public EspecialidadCentro obtenerEspecialidadCentroSegunDuracionEscolaridad(Centro centro, String codEsp, Duracion dur, Escolaridad esc) {
        return e_repo.findByCentroAndEspecialidad_CodEspecialidadAndDuracionAndEscolaridad(centro, codEsp, dur, esc);
    }

    /**
     * Esta funcionalidad permite listar todas las especialidades de un centro.
     *
     * @param centro Centro.
     * @return <code>List</code> Todas las especialidades de un centro
     * registradas.
     */
    @Cacheable(cacheNames = "lst_esp_centro_sc")
    public List<EspecialidadCentro> listarEspecialidadesDeUnCentro(Centro centro) {
        return e_repo.findAllByCentroIdCentroOrderByEspecialidadAscEscolaridadAscDuracionAsc(centro);
    }

    /**
     * Esta funcionalidad permite listar todas las especialidades de un centro según hayan sido captadas o no en la Planilla de Datos.
     *
     * @param idC        Identificador del centro.
     * @param fueCaptada Si fue captada o no.
     * @return <code>List</code> Todas las especialidades de un centro.
     */
    @Cacheable(cacheNames = "lst_esp_centro_scap")
    public List<EspecialidadCentro> listarEspecialidadesSegunCaptacion(long idC, boolean fueCaptada) {
        return e_repo.findAllByCentroIdCentroAndFueCaptadaOrderByEspecialidadAsc(idC, fueCaptada);
    }
}