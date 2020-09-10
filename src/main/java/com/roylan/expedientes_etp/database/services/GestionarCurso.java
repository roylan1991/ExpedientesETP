package com.roylan.expedientes_etp.database.services;


import com.roylan.expedientes_etp.database.entities.Curso;
import com.roylan.expedientes_etp.database.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona las operaciones referentes al curso de una especialidad.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarCurso {

    @Autowired
    private CursoRepository c_repo;

    public GestionarCurso() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo curso.
     *
     * @param c Nuevo curso.
     */
    public void adicionar(Curso c) {
        c_repo.save(c);
    }

    /**
     * Esta funcionalidad permite obtener un curso mediante su identificador.
     *
     * @param idC Identificador del curso.
     * @return <code>Curso</code> Curso obtenido.
     *
     * <code>null</code> Si no existe el curso.
     */
    @Cacheable(cacheNames = "curso_id")
    public Curso obtenerId(long idC) {
        return c_repo.findByIdCurso(idC);
    }

    /**
     * Esta funcionalidad permite listar todos los cursos registrados.
     *
     * @return <code>List</code> Todos los cursos registrados.
     */
    @Cacheable(cacheNames = "lst_cursos")
    public List<Curso> listar() {
        return c_repo.findAll();
    }
}


