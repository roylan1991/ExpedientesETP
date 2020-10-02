package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.AnnoEstudio;
import com.roylan.expedientes_etp.database.entities.EspecialidadActual;
import com.roylan.expedientes_etp.database.entities.EspecialidadAnterior;
import com.roylan.expedientes_etp.database.entities.PlanillaDatos;
import com.roylan.expedientes_etp.database.services.GestionarPlanillaDatosImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Esta clase valida que se gestionen correctamente todas las operaciones referentes a las planillas de datos.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Component
public class ValidacionPlanillaDatosImpl implements IValidacion<PlanillaDatos> {

    @Autowired
    private GestionarPlanillaDatosImpl p_serv;

    private List<EspecialidadAnterior> espAnterioresOrdenadasPorAñoEstudio;

    public ValidacionPlanillaDatosImpl() {
    }

    /**
     * Esta funcionalidad registra una nueva planilla luego de ser comprobado que sus respectivos datos estén correctos.
     *
     * @param p Datos de la planilla.
     * @throws Exception Si la planilla de datos ya fue registrada.
     * @throws Exception Si los datos no están sintácticamente correctos.
     * @throws Exception Si los datos no están correctos.
     */
    public void validarAdicionar(PlanillaDatos p) throws Exception {
        if (p.getIdPlanilla() == -1) {
            throw new Exception("Estos campos de datos solo admiten números válidos!");
        }

        validarPlanilla(p);
        p_serv.adicionar(p);
    }

    /**
     * Esta funcionalidad devuelve una planilla de datos luego de comprobar que se encuentra registrada.
     *
     * @param idP Identificador de la planilla.
     * @return <code>PlanillaDatos</code> Planilla obtenida.
     * @throws Exception Si la planilla no se encuentra registrada.
     */
    public PlanillaDatos validarObtenerId(long idP) throws Exception {

        PlanillaDatos pd = p_serv.obtenerId(idP);

        if (pd == null) {
            throw new Exception("Esta planilla de datos no se encuentra registrada!");
        }

        return pd;
    }

    /**
     * Esta funcionalidad edita una planilla de datos luego de ser comprobado que sus nuevos datos estén correctos.
     *
     * @param idP     Identificador de la planilla que se editará.
     * @param n_datos Nuevos datos de la planilla.
     * @throws Exception Si los datos no están sintácticamente correctos.
     * @throws Exception Si los datos no están correctos.
     */
    public void validarActualizar(long idP, PlanillaDatos n_datos) throws Exception {

        if (n_datos.getIdPlanilla() == -1) {
            throw new Exception("Estos campos de datos solo admiten números válidos!");
        }

        validarPlanilla(n_datos);

        p_serv.update(idP, n_datos);
    }

    /**
     * Esta funcionalidad elimina una planilla de datos luego de comprobar que se encuentra registrada.
     *
     * @param idP Identificador de la planilla que será eliminada.
     * @throws Exception Si la planilla no se encuentra registrada.
     */
    public void validarEliminar(long idP) throws Exception {

        if (p_serv.obtenerId(idP) == null) {
            throw new Exception("Esta planilla de datos no se encuentra registrada!");
        }

        p_serv.eliminar(idP);
    }

    /**
     * Esta funcionalidad lista todas las planillas de datos que se encuentren registradas.
     *
     * @return <code>List</code> Todas las planillas registradas.
     */
    public List<PlanillaDatos> validarListar() {
        return p_serv.listar();
    }

    /**
     * Esta funcionalidad lista todas las planillas de datos de un centro.
     *
     * @param idC Identificador del centro.
     * @return <code>List</code> Todas las planillas registradas.
     */
    public List<PlanillaDatos> validarListarPlanillasDatosCentro(long idC) {
        return p_serv.listarPlanillasDatosCentro(idC);
    }

    /**
     * Esta funcionalidad convierte el tiempo de estudio de una especialidad de semestres a años.
     *
     * @param duracion Duración en semestres.
     * @return <code>int</code> Duración en años.
     */
    private int convertirDuracion_Año(long duracion) {
        return (int) ((duracion / 2) + (duracion % 2));
    }

    /**
     * Esta funcionalidad convierte un año de estudio a uno romano.
     *
     * @param año Número del año de estudio.
     * @return <code>String</code> Número de años romanos.
     */
    private String añoRomano(int año) {
        if (año == 1) {
            return "I Año";
        }
        if (año == 2) {
            return "II Año";
        }
        if (año == 3) {
            return "III Año";
        }
        return "IV Año";
    }

    /**
     * Esta funcionalidad completa a un listado de Especialidades Actuales los años de estudios que le faltan y luego los ordena alfabéticamente.
     *
     * @param pd         Planilla de Datos con las Especialidades Actuales.
     * @param añosFaltan Años de estudio que le faltan.
     * @return <code>EspecialidadActual</code> Especialidades Actuales ordenadas alfabéticamente.
     */
    public List<EspecialidadActual> ordenarEspActualesPorAñoEstudio(PlanillaDatos pd, int añosFaltan) {

        List<EspecialidadActual> actuales = pd.getEspecialidadesActuales();
        List<EspecialidadAnterior> anteriores = pd.getEspecialidadesAnteriores();

        int complt = 4 - añosFaltan + 1;

        for (int i = 0; i < añosFaltan; i++) {
            actuales.add(new EspecialidadActual(new AnnoEstudio(complt + i), 0, 0, 0, 0, 0, 0, 0, 0, pd));
            anteriores.add(new EspecialidadAnterior(new AnnoEstudio(complt + i), 0, 0, 0, 0, 0, 0, 0, 0, pd));
        }

        actuales.sort((EspecialidadActual p1, EspecialidadActual p2) -> new String(p1.getAnnoEstudio().getTipoAnno()).compareTo(p2.getAnnoEstudio().getTipoAnno()));
        anteriores.sort((EspecialidadAnterior p1, EspecialidadAnterior p2) -> new String(p1.getAnnoEstudio().getTipoAnno()).compareTo(p2.getAnnoEstudio().getTipoAnno()));

        this.espAnterioresOrdenadasPorAñoEstudio = anteriores;

        return actuales;
    }

    /**
     * Esta funcionalidad devuelve un listado de Especialidades Anteriores ordenado alfabéticamente por años de estudios.
     *
     * @return <code>List</code> Especialidades Anteriores ordenadas alfabéticamente.
     */
    public List<EspecialidadAnterior> ordenarEspAnterioresPorAñoEstudio() {
        return this.espAnterioresOrdenadasPorAñoEstudio;
    }

    /**
     * Esta funcionalidad valida los datos esenciales de una planilla de datos.
     *
     * @param p Planilla de Datos.
     * @throws Exception Si los datos no están correctos.
     */
    private void validarPlanilla(PlanillaDatos p) throws Exception {
        int duracion = p.getEspecialidadCentro().getDuracion().getTipoDuracion();
        int añosEstudios = convertirDuracion_Año(duracion);
        int verificarPlanillaVacia = 0;

        List<EspecialidadActual> act = p.getEspecialidadesActuales().subList(0, añosEstudios);
        List<EspecialidadAnterior> ant = p.getEspecialidadesAnteriores().subList(0, añosEstudios);
        EspecialidadActual ac;
        EspecialidadAnterior an;

        int año = 1;
        for (int i = 0; i < act.size(); i++) {
            ac = act.get(i);
            an = ant.get(i);

            int matAjustada = an.getMatriculaInicial() + an.getAltasTotal();

            if (matAjustada != an.getMatricInicialAjustada()) {
                throw new Exception("Verifique en [Año Escolar Terminado] la Matrícula Ajustada correspondientes al " + añoRomano(año) + "! Si fuera necesario, verifique además: la Matrícula Inicial y el Total de Altas.");
            }

            if (an.getAltasTotal() < an.getAltasPorTraslado()) {
                throw new Exception("Verifique en [Año Escolar Terminado] las Altas: Total y Por Traslados correspondientes al " + añoRomano(año) + "!");
            }

            if (an.getMatricInicialAjustada() < an.getMatriculaFinal()) {
                throw new Exception("Verifique en [Año Escolar Terminado] las Matrículas: Inicial Ajustada y Final correspondientes al " + añoRomano(año) + "!");
            }

            if (an.getMatriculaFinal() < an.getAprobados()) {
                throw new Exception("Verifique en [Año Escolar Terminado] la Matrícula Final y Aprobados correspondientes al " + añoRomano(año) + "!");
            }

            int matActual = ac.getnIngPromovidosTotal() + ac.getRepitentesTotal() + ac.getReingresos();

            verificarPlanillaVacia += matAjustada + matActual;

            if (matActual != ac.getMatriculaActualTotal()) {
                throw new Exception("Verifique en [Año Escolar Iniciado] la Matrícula Actual correspondientes al " + añoRomano(año) + "! Si fuera necesario, verifique además el Total de: N.Ing/Promovidos, Repitentes y Reingresos");
            }

            int grupos = ac.getCantGrupos();

            if ((matActual != 0 && grupos == 0) || (matActual == 0 && grupos != 0)) {
                throw new Exception("Verifique en [Año Escolar Iniciado] el Total de Grupos correspondientes al " + añoRomano(año) + "! Si fuera necesario verifique la Matrícula Actual Total.");
            }

            if (matActual != 0) {
                if (grupos * 50 < matActual) {
                    throw new Exception("Verifique en [Año Escolar Iniciado] los Totales: Grupos y Matrícula Actual correspondientes al " + añoRomano(año) + "! No se corresponde dicha matrícula con la cantidad de grupos.");
                } else if (grupos != 1) {
                    if (grupos >= matActual) {
                        throw new Exception("Verifique en [Año Escolar Iniciado] los Totales: Grupos y Matrícula Actual correspondientes al " + añoRomano(año) + "! No se corresponde dicha matrícula con la cantidad de grupos.");
                    }
                }
            }

            if (ac.getRepitentesTotal() < ac.getRepitentesPorTraslado()) {
                throw new Exception("Verifique en [Año Escolar Iniciado] los Repitentes: Total y Por Traslados correspondientes al " + añoRomano(año) + "!");
            }

            if (an.getBajasTotal() < an.getBajasPorTraslado()) {
                throw new Exception("Verifique en [Año Escolar Terminado] las Bajas: Total y Por Traslados correspondientes al " + añoRomano(año) + "!");
            }

            if (an.getMatriculaFinal() < an.getAprobados()) {
                throw new Exception("Verifique en [Año Escolar Terminado] la Matrícula Final y Aprobados y correspondientes al " + añoRomano(año) + "!");
            }

            int desaprobados = an.getMatriculaFinal() - an.getAprobados();

            if (ac.getRepitentesTotal() - ac.getRepitentesPorTraslado() > desaprobados) {
                throw new Exception("Verifique en [Año Escolar Iniciado] los Repitentes: Total y Por Traslados correspondientes al " + añoRomano(año) + "! Si fuera necesario, verifique además en [Año Escolar Terminado] la Matrícula Final y Aprobados de este mismo año.");
            }

            if (ac.getMatriculaActualTotal() < ac.getMatriculaActualHembras()) {
                throw new Exception("Verifique en [Año Escolar Iniciado] la Matrícula Actual: Total y Hembras correspondientes al " + añoRomano(año) + "!");
            }

            if (ac.getnIngPromovidosTotal() < ac.getnIngPromovidosPorTraslado()) {
                throw new Exception("Verifique en [Año Escolar Iniciado] los N.Ing/Promovidos: Total y Por Traslados correspondientes al " + añoRomano(año) + "!");
            }

            int totalBajas;

            if (i < (act.size() - 1)) {
                totalBajas = (matAjustada - act.get(i + 1).getnIngPromovidosTotal()) + act.get(i + 1).getnIngPromovidosPorTraslado() - (ac.getRepitentesTotal() - ac.getRepitentesPorTraslado());

                if (matAjustada - totalBajas - (ac.getRepitentesTotal() - ac.getRepitentesPorTraslado()) != (act.get(i + 1).getnIngPromovidosTotal() - act.get(i + 1).getnIngPromovidosPorTraslado())) {
                    throw new Exception("Verifique en [Año Escolar Terminado] la Matrícula Inicial Ajustada y el Total de Bajas correspondientes al " + añoRomano(año) + "! Si fuera necesario, verifique en [Año Escolar Iniciado] los N.Ing/Promovidos: Total y Por Traslado del " + añoRomano(año + 1) + ".");
                }

                if ((act.get(i + 1).getnIngPromovidosTotal() - act.get(i + 1).getnIngPromovidosPorTraslado()) > an.getAprobados()) {
                    throw new Exception("Verifique en [Año Escolar Terminado] el Total de Aprobados correspondientes al " + añoRomano(año) + "! Si fuera necesario, verifique en [Año Escolar Iniciado] los N.Ing/Promovidos: Total y Por Traslado del " + añoRomano(año + 1) + ".");
                }
            } else {
                totalBajas = (matAjustada - an.getAprobados()) - (ac.getRepitentesTotal() - ac.getRepitentesPorTraslado());
                int graduados = an.getAprobados();
                int posiblesGraduados = ac.getMatriculaActualTotal();
                int hembrasPosiblesGraduadas = ac.getMatriculaActualHembras();

                if (graduados != p.getProcdGraduados()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Graduados! Si fuera necesario, verifique los Aprobados del " + añoRomano(año) + ".");
                }
                if (graduados < p.getHembProcdGraduados()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Hembras Graduadas!");
                }

                if (posiblesGraduados != p.getProcdPosiblesGraduados()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Posibles Graduados! Si fuera necesario, verifique la Matrícula Actual Total del " + añoRomano(año) + ".");
                }
                if (hembrasPosiblesGraduadas != p.getHembProcdPosiblesGraduados()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Hembras Posibles Graduadas! Si fuera necesario, verifique la Matrícula Actual de Hembras del " + añoRomano(año) + ".");
                }

                if (posiblesGraduados < hembrasPosiblesGraduadas) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Hembras Posibles Graduadas!");
                }

                if (verificarPlanillaVacia == 0) {
                    throw new Exception("Verifique los datos en general de esta planilla! Si fuera necesario elimine esta especialidad del centro.");
                }
            }

            if (totalBajas != an.getBajasTotal()) {
                throw new Exception("Verifique en [Año Escolar Terminado] el Total de Bajas correspondientes al " + añoRomano(año) + "! Si fuera necesario, verifique además el [Año Escolar Iniciado].");
            }
            if (totalBajas < an.getBajasPorTraslado()) {
                throw new Exception("Verifique en [Año Escolar Terminado] las Bajas correspondientes al " + añoRomano(año) + "!");
            }

            // Cuando sea I Año
            if (i == 0) {
                int nuevosIngresos = ac.getnIngPromovidosTotal();

                if (nuevosIngresos != p.getProcdNuevosIngresos()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Nuevos Ingresos! Si fuera necesario, verifique además el Total de N.Ing/Promovidos del I Año.");
                }

                int hembrasNuevosIngresos = ac.getMatriculaActualHembras() - ac.getReingresos() - ac.getRepitentesTotal();
                if (p.getHembProcdNuevosIngresos() < hembrasNuevosIngresos || p.getHembProcdNuevosIngresos() > ac.getMatriculaActualHembras()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Hembras de Nuevos Ingresos! Si fuera necesario, verifique además el Total de: Repitentes, Reingresos y Matrícula Total de Hembras del I Año.");
                }

                if (nuevosIngresos < p.getHembProcdNuevosIngresos()) {
                    throw new Exception("Verifique en la Procedencia Territorial el Total de Hembras de Nuevos Ingresos!");
                }

                if (ac.getnIngPromovidosPorTraslado() != 0) {
                    throw new Exception("Verifique en [Año Escolar Iniciado] los N.Ing/Promovidos por Traslado correspondientes al I Año! No es posible que existan estudiantes por traslado siendo a su vez Nuevos Ingresos.");
                }
            }
            año++;
        }
        p.setEspecialidadesActuales(act);
        p.setEspecialidadesAnteriores(ant);
    }
}