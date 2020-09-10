package com.roylan.expedientes_etp.database.services;


import com.roylan.expedientes_etp.database.entities.MatriculaFinal;
import com.roylan.expedientes_etp.database.repositories.MatriculaFinalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes a las matrículas finales de un curso.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarMatriculaFinal {

    @Autowired
    private MatriculaFinalRepository m_repo;

    public GestionarMatriculaFinal() {
    }

    /**
     * Esta funcionalidad permite obtener una matricula final mediante su identificador.
     *
     * @param idM Identificador de la matrícula final.
     * @return <code>MatriculaFinal</code> Matrícula final obtenida.
     *
     * <code>null</code> Si no existe la matrícula final.
     */
    public MatriculaFinal obtenerId(int idM) {
        return m_repo.findByIdMatricula(idM);
    }

    /**
     * Esta funcionalidad permite listar todas las matrículas finales registradas.
     *
     * @return <code>List</code> Todas las matrículas finales registradas.
     */
    public List<MatriculaFinal> listar() {
        return m_repo.findAll();
    }
}


