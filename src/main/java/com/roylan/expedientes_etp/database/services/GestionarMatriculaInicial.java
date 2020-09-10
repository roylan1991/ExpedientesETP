package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.MatriculaInicial;
import com.roylan.expedientes_etp.database.repositories.MatriculaInicialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a las matrículas iniciales de un curso.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarMatriculaInicial {

    @Autowired
    private MatriculaInicialRepository m_repo;

    public GestionarMatriculaInicial() {
    }

    /**
     * Esta funcionalidad permite obtener una matrícula inicial mediante su identificador.
     *
     * @param idM Identificador de la matrícula inicial.
     * @return <code>MatriculaInicial</code> Matricula inicial obtenida.
     *
     * <code>null</code> Si no existe la matrícula inicial.
     */
    public MatriculaInicial obtenerId(long idM) {
        return m_repo.findByIdMatricula(idM);
    }

    /**
     * Esta funcionalidad permite listar todas las matrículas iniciales registradas.
     *
     * @return <code>List</code> Todas las matrículas iniciales registradas.
     */
    public List<MatriculaInicial> listar() {
        return m_repo.findAll();
    }
}


