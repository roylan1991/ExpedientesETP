package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.Usuario;
import com.roylan.expedientes_etp.database.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a un municipio.
 *
 * @author Roylan Bressler
 * @see List
 * @since 1.0
 */
@Service
@Transactional
public class GestionarMunicipio {

    @Autowired
    private MunicipioRepository m_repo;

    public GestionarMunicipio() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo municipio.
     *
     * @param m Nuevo Municipio.
     */
    public void adicionar(Municipio m) {
        m_repo.save(m);
    }

    /**
     * Esta funcionalidad permite obtener un municipio mediante su identificador.
     *
     * @param idM Identificador del municipio.
     * @return <code>Municipio</code> Municipio obtenido.
     *
     * <code>null</code> Si no existe el municipio.
     */
    @Cacheable(cacheNames = "municipio_id")
    public Municipio obtenerId(long idM) {
        return m_repo.findByIdMunicipio(idM);
    }

    /**
     * Esta funcionalidad permite eliminar un municipio.
     *
     * @param idM Identificador del municipio.
     */
    public void eliminar(long idM) {
        m_repo.delete(obtenerId(idM));
    }

    /**
     * Esta funcionalidad permite listar todos los municipios registrados.
     *
     * @return <code>List</code> Todos los municipios registrados.
     */
    @Cacheable(cacheNames = "lst_municipios")
    public List<Municipio> listar() {
        return m_repo.findAll();
    }

    /**
     * Esta funcionalidad permite listar todos los municipios registrados en una provincia.
     *
     * @param idP Identificador de la provincia.
     * @return <code>List</code> Todos los municipios registrados en esa provincia.
     */
    @Cacheable(cacheNames = "lst_municipios_sp")
    public List<Municipio> listarSegunProvincia(long idP) {
        return m_repo.findAllByProvIdProvinciaOrderByNombMunicipio(idP);
    }

    /**
     * Esta funcionalidad lista todos los municipios registrados según el rol del usuario autenticado.
     *
     * @param municipios  Municipios.
     * @param autenticado Usuario autenticado.
     * @return <code>List</code> Municipios registrados.
     */
    @Cacheable(cacheNames = "lst_municipios_sr")
    public List<Municipio> getMunicipiosSegunRol(GestionarMunicipio municipios, Usuario autenticado) {
        if (autenticado.getRol().getTipoRol().equals("Supervisor")) {
            return municipios.listarSegunProvincia(autenticado.getMcpio().getProv().getIdProvincia());
        } else if (autenticado.getRol().getTipoRol().equals("Usuario")) {
            List<Municipio> mcpio = new LinkedList<>();
            mcpio.add(autenticado.getMcpio());
            return mcpio;
        }
        return municipios.listar();
    }
}