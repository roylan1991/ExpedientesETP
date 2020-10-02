package com.roylan.expedientes_etp.database.services;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.repositories.PlanillaDatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase gestiona todas las operaciones referentes a las planillas de datos de un centro.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
@Transactional
public class GestionarPlanillaDatosImpl implements IGestionarService<PlanillaDatos> {

    @Autowired
    private PlanillaDatosRepository p_repo;

    @Autowired
    private GestionarCaratulaImpl caratula_serv;

    @Autowired
    private GestionarCentroImpl centro_serv;

    @Autowired
    private GestionarEspecialidadCentroImpl esp_centro_serv;

    @Autowired
    private GestionarEspecialidadActualImpl ac_serv;

    @Autowired
    private GestionarEspecialidadAnteriorImpl an_serv;

    public GestionarPlanillaDatosImpl() {
    }

    /**
     * Esta funcionalidad permite registar una nueva planilla de datos.
     *
     * @param p Nueva planilla.
     */
    @CacheEvict(cacheNames = {"lst_esp_centro_scap"}, allEntries = true)
    public void adicionar(PlanillaDatos p) {
        EspecialidadCentro ec = p.getEspecialidadCentro();
        ec.setFueCaptada(true);

        esp_centro_serv.update(ec.getIdEspecialidad(), ec);

        PlanillaDatos pd = p_repo.save(p);

        EspecialidadActual ea;
        EspecialidadAnterior eant;
        List<EspecialidadActual> lst_act = p.getEspecialidadesActuales();
        List<EspecialidadAnterior> lst_ant = p.getEspecialidadesAnteriores();

        for (int i = 0; i < lst_act.size(); i++) {
            ea = lst_act.get(i);
            eant = lst_ant.get(i);
            ea.setPlanilla(pd);
            eant.setPlanilla(pd);

            ac_serv.adicionar(ea);
            an_serv.adicionar(eant);
        }

        actualizarCaratula(pd);
    }

    /**
     * Esta funcionalidad permite obtener una planilla de datos mediante su identificador.
     *
     * @param idP Identificador de la planilla.
     * @return <code>PlanillaDatos</code> Planilla obtenida.
     *
     * <code>null</code> Si no existe la planilla.
     */
    public PlanillaDatos obtenerId(long idP) {
        return p_repo.findByIdPlanilla(idP);
    }

    /**
     * Esta funcionalidad permite obtener una planilla de datos mediante la especialidad de un centro.
     *
     * @param espC Identificador de la especialidad de un centro.
     * @return <code>PlanillaDatos</code> Planilla obtenida.
     *
     * <code>null</code> Si no existe la planilla.
     */
    public PlanillaDatos obtenerSegunEspCentro(long espC) {
        return p_repo.findByEspecialidadCentroIdEspecialidad(espC);
    }

    /**
     * Esta funcionalidad permite editar los datos de una planilla de datos.
     *
     * @param idP     Identificador de la planilla a editar.
     * @param n_datos Nuevos datos de la planilla.
     */
    @CacheEvict(cacheNames = {"lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void update(long idP, PlanillaDatos n_datos) {
        p_repo.updatePlanilla(idP, n_datos.getProcdGraduados(), n_datos.getHembProcdGraduados(), n_datos.getProcdPosiblesGraduados(), n_datos.getHembProcdPosiblesGraduados(), n_datos.getProcdNuevosIngresos(), n_datos.getHembProcdNuevosIngresos());

        actualizarCaratula(n_datos);
    }

    /**
     * Esta funcionalidad permite eliminar una planilla de datos.
     *
     * @param idP Identificador de la planilla.
     */
    @CacheEvict(cacheNames = {"lst_esp_centro_scap", "lst_centro", "lst_centro_mcpio", "lst_centro_prov"}, allEntries = true)
    public void eliminar(long idP) {
        PlanillaDatos pd = obtenerId(idP);
        pd.getEspecialidadCentro().setFueCaptada(false);

        p_repo.delete(pd);

        actualizarCaratula(pd);
    }

    /**
     * Esta funcionalidad permite listar todas las planillas de datos registradas.
     *
     * @return <code>List</code> Todas las planillas registradas.
     */
    public List<PlanillaDatos> listar() {
        return p_repo.findAll();
    }

    /**
     * Esta funcionalidad permite listar todas las planillas de datos registradas en un centro.
     *
     * @param idC Identificador del Centro.
     * @return <code>List</code> Todas las planillas registradas.
     */
    public List<PlanillaDatos> listarPlanillasDatosCentro(long idC) {
        return p_repo.findAllByCentroIdCentroOrderByIdPlanilla(idC);
    }

    /**
     * Esta funcionalidad permite eliminar todas las planillas de datos registradas en un centro.
     *
     * @param idC Identificador del Centro.
     */
    public void vaciarPlanillasDatosCentro(long idC) {

        esp_centro_serv.updateCaptadaTodo(idC, false);
        p_repo.deleteAllByCentroIdCentro(idC);

        Centro centro = centro_serv.obtenerId(idC);
        Caratula car = centro.getCaratula();

        MatriculaInicial matrTM = car.getMatriculaInicialTM();
        matrTM.setParametros();
        MatriculaInicial matrOC = car.getMatriculaInicialOC();
        matrOC.setParametros();
        MatriculaInicial matrCPT_TM = car.getMatriculaInicialCPT_TM();
        matrCPT_TM.setParametros();
        MatriculaFinal mfTM = car.getMatriculaFinalTM();
        mfTM.setParametros();
        MatriculaFinal mfOC = car.getMatriculaFinalOC();
        mfOC.setParametros();
        MatriculaFinal mfCPT_TM = car.getMatriculaFinalCPT_TM();
        mfCPT_TM.setParametros();

        caratula_serv.update(car.getIdCaratula(), car);

        actualizarProgreso(centro);
    }

    /**
     * Esta funcionalidad actualiza los datos de la carátula de un centro.
     *
     * @param pd Planilla de datos.
     */
    public void actualizarCaratula(PlanillaDatos pd) {
        Centro centro = pd.getCentro();
        long idCentro = centro.getIdCentro();
        Caratula car = centro.getCaratula();

        long nivel = pd.getEspecialidadCentro().getEspecialidad().getNivel().getIdNivel();
        long curso = pd.getEspecialidadCentro().getCurso().getIdCurso();

        if (nivel == 1 && curso == 1) {
            car.getMatriculaInicialTM().setTotal(caratula_serv.matriculasTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialTM().setTotalHembras(caratula_serv.matriculasTotalesHembrasDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialTM().setNuevoIngreso(caratula_serv.nuevosIngresosTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialTM().setNuevoIngresoHembras(caratula_serv.nuevosIngresosTotalesHembrasDeCentro(idCentro, nivel, curso));

            car.getMatriculaFinalTM().setMatriculaFinal(caratula_serv.matriculasFinalesTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaFinalTM().setAprobados(caratula_serv.aprobadosTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaFinalTM().setGraduados(caratula_serv.graduadosTotalesDeCentro(idCentro, nivel, curso));
        } else if (nivel == 2 && curso == 1) {
            car.getMatriculaInicialOC().setTotal(caratula_serv.matriculasTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialOC().setTotalHembras(caratula_serv.matriculasTotalesHembrasDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialOC().setNuevoIngreso(caratula_serv.nuevosIngresosTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialOC().setNuevoIngresoHembras(caratula_serv.nuevosIngresosTotalesHembrasDeCentro(idCentro, nivel, curso));

            car.getMatriculaFinalOC().setMatriculaFinal(caratula_serv.matriculasFinalesTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaFinalOC().setAprobados(caratula_serv.aprobadosTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaFinalOC().setGraduados(caratula_serv.graduadosTotalesDeCentro(idCentro, nivel, curso));
        } else if (nivel == 1 && curso == 2) {
            car.getMatriculaInicialCPT_TM().setTotal(caratula_serv.matriculasTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialCPT_TM().setTotalHembras(caratula_serv.matriculasTotalesHembrasDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialCPT_TM().setNuevoIngreso(caratula_serv.nuevosIngresosTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaInicialCPT_TM().setNuevoIngresoHembras(caratula_serv.nuevosIngresosTotalesHembrasDeCentro(idCentro, nivel, curso));

            car.getMatriculaFinalCPT_TM().setMatriculaFinal(caratula_serv.matriculasFinalesTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaFinalCPT_TM().setAprobados(caratula_serv.aprobadosTotalesDeCentro(idCentro, nivel, curso));
            car.getMatriculaFinalCPT_TM().setGraduados(caratula_serv.graduadosTotalesDeCentro(idCentro, nivel, curso));
        }

        caratula_serv.update(car.getIdCaratula(), car);
        actualizarProgreso(centro);
    }

    /**
     * Esta funcionalidad actualiza el progreso de un centro.
     *
     * @param centro Centro.
     */
    public void actualizarProgreso(Centro centro) {

        long idCentro = centro.getIdCentro();
        int espCaptadas = esp_centro_serv.listarEspecialidadesSegunCaptacion(idCentro, true).size();

        if (espCaptadas != 0) {
            float progreso = 0, total = 0;
            Caratula car = centro.getCaratula();
            RangoEdad rangoEdad = centro.getRangosEdades();

            Rango ran = rangoEdad.getDiurno();

            if (car.getMatriculaInicialTM().getTotal() + car.getMatriculaInicialOC().getTotal() == ran.getMatriculaRangoTotal()) {
                progreso++;
            }

            if (car.getMatriculaInicialTM().getTotalHembras() + car.getMatriculaInicialOC().getTotalHembras() == ran.getMatriculaRangoTotalHembras()) {
                progreso++;
            }

            ran = rangoEdad.getCpt();

            if (car.getMatriculaInicialCPT_TM().getTotal() == ran.getMatriculaRangoTotal()) {
                progreso++;
            }

            if (car.getMatriculaInicialCPT_TM().getTotalHembras() == ran.getMatriculaRangoTotalHembras()) {
                progreso++;
            }

            total += 4;

            progreso += espCaptadas;
            total += esp_centro_serv.listarEspecialidadesDeUnCentro(centro).size();
            centro_serv.updateProgresoCentro(idCentro, (int) (progreso / total * 100));
        } else {
            centro_serv.updateProgresoCentro(idCentro, espCaptadas);
        }
    }
}