package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Escolaridad;
import com.roylan.expedientes_etp.database.repositories.EscolaridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a la escolaridad de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarEscolaridad {

    @Autowired
    private EscolaridadRepository e_repo;

    public GestionarEscolaridad() {
    }

    /**
     * Esta funcionalidad permite registar una nueva escolaridad.
     *
     * @param e Nueva escolaridad.
     */
    public void adicionar(Escolaridad e) {
        e_repo.save(e);
    }

    /**
     * Esta funcionalidad permite obtener una escolaridad mediante su identificador.
     *
     * @param idE Identificador de la escolaridad.
     * @return <code>Escolaridad</code> Escolaridad obtenida.
     *
     * <code>null</code> Si no existe la escolaridad.
     */
    @Cacheable(cacheNames = "escolaridad_id")
    public Escolaridad obtenerId(long idE) {
        return e_repo.findByIdEscolaridad(idE);
    }

    /**
     * Esta funcionalidad permite listar todas las escolaridades registradas.
     *
     * @return <code>List</code> Todas las escolaridades registradas.
     */
    @Cacheable(cacheNames = "lst_escolaridades")
    public List<Escolaridad> listar() {
        return e_repo.findAll();
    }
}


