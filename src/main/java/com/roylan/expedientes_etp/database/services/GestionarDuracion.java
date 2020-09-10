package com.roylan.expedientes_etp.database.services;


import com.roylan.expedientes_etp.database.entities.Duracion;
import com.roylan.expedientes_etp.database.repositories.DuracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a la duración de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarDuracion {

    @Autowired
    private DuracionRepository d_repo;

    public GestionarDuracion() {
    }

    /**
     * Esta funcionalidad permite registar una nueva duración.
     *
     * @param d Nueva duración.
     */
    public void adicionar(Duracion d) {
        d_repo.save(d);
    }

    /**
     * Esta funcionalidad permite obtener una duración mediante su identificador.
     *
     * @param idD Identificador de la duración.
     * @return <code>Duracion</code> Duración obtenida.
     *
     * <code>null</code> Si no existe la duración.
     */
    @Cacheable(cacheNames = "duracion_id")
    public Duracion obtenerId(long idD) {
        return d_repo.findByIdDuracion(idD);
    }

    /**
     * Esta funcionalidad permite listar todas las duraciones registradas.
     *
     * @return <code>List</code> Todas las duraciones registradas.
     */
    @Cacheable(cacheNames = "lst_duraciones")
    public List<Duracion> listar() {
        return d_repo.findAll();
    }
}


