package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.Nivel;
import com.roylan.expedientes_etp.database.repositories.NivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a el nivel de estudio de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarNivel {

    @Autowired
    private NivelRepository n_repo;

    public GestionarNivel() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo nivel.
     *
     * @param n Nuevo nivel.
     */
    public void adicionar(Nivel n) {
        n_repo.save(n);
    }

    /**
     * Esta funcionalidad permite obtener un nivel de estudio mediante su identificador.
     *
     * @param idN Identificador del nivel de estudio.
     * @return <code>Nivel</code> Nivel de estudio obtenido.
     *
     * <code>null</code> Si no existe el nivel.
     */
    @Cacheable(cacheNames = "nivel_id")
    public Nivel obtenerId(long idN) {
        return n_repo.findByIdNivel(idN);
    }

    /**
     * Esta funcionalidad permite listar todos los niveles de estudio registrados.
     *
     * @return <code>List</code> Todos los niveles de estudios registrados.
     */
    @Cacheable(cacheNames = "lst_niveles")
    public List<Nivel> listar() {
        return n_repo.findAll();
    }
}


