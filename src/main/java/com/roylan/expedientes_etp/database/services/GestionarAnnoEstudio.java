package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.AnnoEstudio;
import com.roylan.expedientes_etp.database.repositories.AnnoEstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a un año de estudio.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarAnnoEstudio {

    @Autowired
    private AnnoEstudioRepository a_repo;

    public GestionarAnnoEstudio() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo año de estudio.
     *
     * @param a Nuevo año de estudio.
     */
    public void adicionar(AnnoEstudio a) {
        a_repo.save(a);
    }

    /**
     * Esta funcionalidad permite obtener un año de estudio mediante su identificador.
     *
     * @param idA Identificador del año.
     * @return <code>AnnoEstudio</code> Año obtenido.
     *
     * <code>null</code> Si no existe el año.
     */
    @Cacheable(cacheNames = "anno_id")
    public AnnoEstudio obtenerId(long idA) {
        return a_repo.findByIdAnno(idA);
    }

    /**
     * Esta funcionalidad permite listar todos los años registrados.
     *
     * @return <code>List</code> Todos los años registrados.
     */
    @Cacheable(cacheNames = "lst_annos")
    public List<AnnoEstudio> listar() {
        return a_repo.findAll();
    }
}