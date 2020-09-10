package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.TipoProfesor;
import com.roylan.expedientes_etp.database.repositories.TipoProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a los tipos de profesores.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarTipoProfesor {

    @Autowired
    private TipoProfesorRepository tp_repo;

    public GestionarTipoProfesor() {
    }

    /**
     * Esta funcionalidad permite obtener un tipo de profesor mediante su identificador.
     *
     * @param idP Identificador del profesor.
     * @return <code>TipoProfesor</code> Profesor obtenido.
     *
     * <code>null</code> Si no existe el profesor.
     */
    public TipoProfesor obtenerId(long idP) {
        return tp_repo.findByIdTipoProfesor(idP);
    }

    /**
     * Esta funcionalidad permite listar todos los tipos de profesores registrados.
     *
     * @return <code>List</code> Todos los tipos de profesores registrados.
     */
    public List<TipoProfesor> listar() {
        return tp_repo.findAll();
    }
}


