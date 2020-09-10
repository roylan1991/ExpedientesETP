package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.repositories.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarCentroImpl implements IGestionarService<Centro> {

    @Autowired
    private CentroRepository e_repo;

    @Autowired
    private GestionarComposicionPersonalImpl compPer;

    @Autowired
    private GestionarCaratulaImpl carat;

    @Autowired
    private GestionarNivel niveles;

    @Autowired
    private GestionarCurso cursos;

    @Autowired
    private GestionarRangoEdadImpl rangos;

    public GestionarCentroImpl() {
    }

    /**
     * Esta funcionalidad permite registar un nuevo centro.
     *
     * @param centro Nuevo centro.
     */
    @CacheEvict(cacheNames = {"lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void adicionar(Centro centro) {
        ProfesorAsignatura pf = new ProfesorAsignatura(new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo(), new ProfesorFijo());
        ProfesorAsignatura pc = new ProfesorAsignatura(new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato(), new ProfesorContrato());
        ComposicionPersonal cp = new ComposicionPersonal(new Personal(), new Personal(), new Personal(), new Personal(), new Personal(), pf, pc);

        Nivel nTM = niveles.obtenerId(1);
        Nivel nOC = niveles.obtenerId(2);
        Curso cDiurno = cursos.obtenerId(1);
        Curso cCPT = cursos.obtenerId(2);

        MatriculaInicial matrTM = new MatriculaInicial(nTM, cDiurno);
        MatriculaInicial matrOC = new MatriculaInicial(nOC, cDiurno);
        MatriculaInicial matrCPT_TM = new MatriculaInicial(nTM, cCPT);
        RegimenEstudio regimTM = new RegimenEstudio(nTM);
        RegimenEstudio regimOC = new RegimenEstudio(nOC);
        MatriculaFinal mfTM = new MatriculaFinal(nTM, cDiurno);
        MatriculaFinal mfOC = new MatriculaFinal(nOC, cDiurno);
        MatriculaFinal mfCPT_TM = new MatriculaFinal(nTM, cCPT);

        Caratula caratula = new Caratula(matrTM, regimTM, matrOC, regimOC, matrCPT_TM, mfTM, mfOC, mfCPT_TM);
        matrTM.setCaratula(caratula);
        matrOC.setCaratula(caratula);
        matrCPT_TM.setCaratula(caratula);
        mfTM.setCaratula(caratula);
        mfOC.setCaratula(caratula);
        mfCPT_TM.setCaratula(caratula);
        regimTM.setCaratula(caratula);
        regimOC.setCaratula(caratula);

        Rango diurno = new Rango(cDiurno);
        Rango cpt = new Rango(cCPT);

        RangoEdad rangoEdades = new RangoEdad(diurno, cpt);
        rangoEdades.getDiurno().setRangoEdad(rangoEdades);
        rangoEdades.getCpt().setRangoEdad(rangoEdades);

        compPer.adicionar(cp);
        carat.adicionar(caratula);
        rangos.adicionar(rangoEdades);
        centro.setCaratula(caratula);
        centro.setRangosEdades(rangoEdades);
        centro.setComposicionPersonal(cp);
        e_repo.save(centro);
    }

    /**
     * Esta funcionalidad permite obtener un centro mediante su identificador.
     *
     * @param idE Identificador del centro.
     * @return <code>Centro</code> Centro obtenido.
     *
     * <code>null</code> Si no existe el centro.
     */
    public Centro obtenerId(long idE) {
        return e_repo.findByIdCentro(idE);
    }

    /**
     * Esta funcionalidad permite obtener un centro mediante su código.
     *
     * @param codE Código del centro.
     * @return <code>Centro</code> Centro obtenido.
     *
     * <code>null</code> Si no existe el centro.
     */
    public Centro obtenerCodigo(String codE) {
        return e_repo.findByCodCentro(codE);
    }

    /**
     * Esta funcionalidad permite editar los datos de un centro.
     *
     * @param idE     Identificador del centro a editar.
     * @param n_datos Nuevos datos del centro.
     */
    @CacheEvict(cacheNames = {"lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void update(long idE, Centro n_datos) {
        e_repo.updateCentro(idE, n_datos.getCodCentro(), n_datos.getNombCentro(), n_datos.getDireccion(), n_datos.getTelefono(), n_datos.getSector(), n_datos.getMcpio());
    }

    /**
     * Esta funcionalidad permite editar el progreso de un centro.
     *
     * @param idE      Identificador del centro a editar.
     * @param progreso Nuevos datos del progreso.
     */
    public void updateProgresoCentro(long idE, int progreso) {
        e_repo.updateProgresoCentro(idE, progreso);
    }

    /**
     * Esta funcionalidad permite eliminar un centro.
     *
     * @param idE Identificador del centro.
     */
    @CacheEvict(cacheNames = {"lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void eliminar(long idE) {
        Centro e = obtenerId(idE);

        e_repo.delete(e);
    }

    /**
     * Esta funcionalidad permite listar todos los centros registrados.
     *
     * @return <code>List</code> Todos los centros registrados.
     */
    @Cacheable(cacheNames = "lst_centro")
    public List<Centro> listar() {
        return e_repo.findAllByOrderByMcpioAscNombCentroAsc();
    }

    /**
     * Esta funcionalidad permite listar todos los centros registrados en un municipio.
     *
     * @param mcpio Municipio.
     * @return <code>List</code> Todos los centros registrados en ese municipio.
     */
    @Cacheable(cacheNames = "lst_centro_mcpio")
    public List<Centro> listarSegunMunicipio(Municipio mcpio) {
        return e_repo.findAllByMcpioOrderByMcpioAscNombCentroAsc(mcpio);
    }

    /**
     * Esta funcionalidad permite listar todos los centros registrados en una provincia.
     *
     * @param prov Provincia.
     * @return <code>List</code> Todos los centros registrados en esa provincia.
     */
    @Cacheable(cacheNames = "lst_centro_prov")
    public List<Centro> listarSegunProvincia(Provincia prov) {
        return e_repo.findAllByMcpioProvOrderByMcpioAscNombCentroAsc(prov);
    }
}