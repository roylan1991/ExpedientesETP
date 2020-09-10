package com.roylan.expedientes_etp.controller;

import com.lowagie.text.DocumentException;
import com.roylan.expedientes_etp.business.*;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.*;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoExcelImpl;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoPDFImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class ExpedienteController {

    @Autowired
    private ValidacionCentroImpl centros;

    @Autowired
    private ValidacionEspecialidadCentroImpl especialidadesCentros;

    @Autowired
    private GestionarEspecialidadActualImpl especialidadesActuales;

    @Autowired
    private GestionarEspecialidadAnteriorImpl especialidadesAnteriores;

    @Autowired
    private ValidacionRangoEdadImpl rangosEdades;

    @Autowired
    private ValidacionCaratulaImpl caratulas;

    @Autowired
    private ValidacionComposicionPersonalImpl personal;

    @Autowired
    private GestionarNivel niveles;

    @Autowired
    private GestionarRama ramas;

    @Autowired
    private ValidacionPlanillaDatosImpl planillas;

    @Autowired
    private GenerarDocumentoPDFImpl generarDocumentoPDF;

    @Autowired
    private GenerarDocumentoExcelImpl generarDocumentoExcel;

    @Autowired
    private GestionarAnnoEstudio annoEstudio;

    @Autowired
    private HttpSession session;

    @Autowired
    private Validaciones v;

    private static List<Ficha> lst_ficha_aux = new ArrayList<>();
    private static Centro ultimo_cen_visitado;

    @GetMapping(path = {"/expedientes"})
    public String lstExpedientes(HttpServletRequest request, Model m) {
        List<Centro> lst_centros = centros.getCentrosSegunRol(centros, getUsuarioAutenticado());

        session.setAttribute("url", request.getServletPath());

        if (!lst_centros.isEmpty()) {
            m.addAttribute("lst_centros", lst_centros);
            session.setAttribute("url", request.getServletPath());
            return "expediente/lst_expedientes";
        }
        return "redirect:lst_centros";
    }

    @GetMapping(path = {"/ficha"})
    public String fichaCentro(@RequestParam(name = "idE") long idE, Model m) {

        try {
            Centro centro = centros.validarObtenerId(idE);

            m.addAttribute("lst_fichas", datosFicha(centro));
            m.addAttribute("datos_centro", centro);

            return "expediente/ficha";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/caratula"})
    public String editarCaratula(@RequestParam(name = "idE") long idE, Model m) {

        Validaciones.ValoresIncorrectos = false;
        try {
            Centro centro = centros.validarObtenerId(idE);

            if (!m.containsAttribute("CaratulaError")) {
                m.addAttribute("caratula", centro.getCaratula());
            }

            m.addAttribute("datos_centro", centro);
            activarCampos_SoloUsuario(m, "Usuario");

            return "expediente/caratula";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/caratula"})
    public String editarCaratulaPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, RedirectAttributes r, Model m) throws Exception {

        Caratula caratula = centros.validarObtenerId(idE).getCaratula();
        long idC = caratula.getIdCaratula();
        Caratula n_datos = null;

        try {
            int internosTM = v.numeroValido(request.getParameter("matricula_internos_tm"));
            int seminternosTM = v.numeroValido(request.getParameter("matricula_seminternos_tm"));
            int internosHembrasTM = v.numeroValido(request.getParameter("matricula_internos_hembras_tm"));
            int seminternosHembrasTM = v.numeroValido(request.getParameter("matricula_seminternos_hembras_tm"));

            int internosOC = v.numeroValido(request.getParameter("matricula_internos_oc"));
            int seminternosOC = v.numeroValido(request.getParameter("matricula_seminternos_oc"));
            int internosHembrasOC = v.numeroValido(request.getParameter("matricula_internos_hembras_oc"));
            int seminternosHembrasOC = v.numeroValido(request.getParameter("matricula_seminternos_hembras_oc"));

            RegimenEstudio tm = new RegimenEstudio(internosTM, internosHembrasTM, seminternosTM, seminternosHembrasTM, niveles.obtenerId(1));
            RegimenEstudio oc = new RegimenEstudio(internosOC, internosHembrasOC, seminternosOC, seminternosHembrasOC, niveles.obtenerId(2));

            n_datos = new Caratula(caratula.getMatriculaInicialTM(), tm, caratula.getMatriculaInicialOC(), oc, caratula.getMatriculaInicialCPT_TM(), caratula.getMatriculaFinalTM(), caratula.getMatriculaFinalOC(), caratula.getMatriculaFinalCPT_TM());

            if (Validaciones.ValoresIncorrectos) {
                n_datos.setIdCaratula(-1);
            }

            caratulas.validarActualizar(idC, n_datos);
        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("caratula", n_datos);
            m.addAttribute("CaratulaError", true);

            return editarCaratula(idE, m);
        }

        r.addAttribute("idE", idE);
        return "redirect:/caratula";
    }

    @GetMapping(path = {"/profesores_asignatura"})
    public String editarProfesoresPorAsignatura(@RequestParam(name = "idE") long idE, Model m) {

        Validaciones.ValoresIncorrectos = false;

        try {
            Centro c = centros.validarObtenerId(idE);

            if (!m.containsAttribute("ProfesorAsignaturaError")) {

                ComposicionPersonal cp = c.getComposicionPersonal();
                m.addAttribute("fijos", cp.getProfesoresFijos());
                m.addAttribute("contratados", cp.getProfesoresContratados());
            }

            m.addAttribute("datos_centro", c);
            activarCampos_SoloUsuario(m, "Usuario");

            return "expediente/profesores_asignatura";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/profesores_asignatura"})
    public String editarProfesoresPorAsignaturaPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, RedirectAttributes r, Model m) throws Exception {

        ProfesorAsignatura pc = null;
        ProfesorAsignatura pf = null;

        try {
            // Profesores Fijos
            int fijosEspanol = v.numeroValido(request.getParameter("fijosEspanol"));
            int fijosNoTituladoEspanol = v.numeroValido(request.getParameter("fijosNoTituladoEspanol"));
            int fijosNoTituladoEstudiandoEspanol = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoEspanol"));
            int fijosLiteratura = v.numeroValido(request.getParameter("fijosLiteratura"));
            int fijosNoTituladoLiteratura = v.numeroValido(request.getParameter("fijosNoTituladoLiteratura"));
            int fijosNoTituladoEstudiandoLiteratura = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoLiteratura"));
            int fijosMatematica = v.numeroValido(request.getParameter("fijosMatematica"));
            int fijosNoTituladoMatematica = v.numeroValido(request.getParameter("fijosNoTituladoMatematica"));
            int fijosNoTituladoEstudiandoMatematica = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoMatematica"));
            int fijosFisica = v.numeroValido(request.getParameter("fijosFisica"));
            int fijosNoTituladoFisica = v.numeroValido(request.getParameter("fijosNoTituladoFisica"));
            int fijosNoTituladoEstudiandoFisica = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoFisica"));
            int fijosQuimica = v.numeroValido(request.getParameter("fijosQuimica"));
            int fijosNoTituladoQuimica = v.numeroValido(request.getParameter("fijosNoTituladoQuimica"));
            int fijosNoTituladoEstudiandoQuimica = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoQuimica"));
            int fijosBiologia = v.numeroValido(request.getParameter("fijosBiologia"));
            int fijosNoTituladoBiologia = v.numeroValido(request.getParameter("fijosNoTituladoBiologia"));
            int fijosNoTituladoEstudiandoBiologia = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoBiologia"));
            int fijosHistoria = v.numeroValido(request.getParameter("fijosHistoria"));
            int fijosNoTituladoHistoria = v.numeroValido(request.getParameter("fijosNoTituladoHistoria"));
            int fijosNoTituladoEstudiandoHistoria = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoHistoria"));
            int fijosIdiomaExtranjero = v.numeroValido(request.getParameter("fijosIdiomaExtranjero"));
            int fijosNoTituladoIdiomaExtranjero = v.numeroValido(request.getParameter("fijosNoTituladoIdiomaExtranjero"));
            int fijosNoTituladoEstudiandoIdiomaExtranjero = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoIdiomaExtranjero"));
            int fijosEdcnFisica = v.numeroValido(request.getParameter("fijosEdcnFisica"));
            int fijosNoTituladoEdcnFisica = v.numeroValido(request.getParameter("fijosNoTituladoEdcnFisica"));
            int fijosNoTituladoEstudiandoEdcnFisica = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoEdcnFisica"));
            int fijosEdcnArtistica = v.numeroValido(request.getParameter("fijosEdcnArtistica"));
            int fijosNoTituladoEdcnArtistica = v.numeroValido(request.getParameter("fijosNoTituladoEdcnArtistica"));
            int fijosNoTituladoEstudiandoEdcnArtistica = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoEdcnArtistica"));
            int fijosMarxismo = v.numeroValido(request.getParameter("fijosMarxismo"));
            int fijosNoTituladoMarxismo = v.numeroValido(request.getParameter("fijosNoTituladoMarxismo"));
            int fijosNoTituladoEstudiandoMarxismo = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoMarxismo"));
            int fijosFundamento = v.numeroValido(request.getParameter("fijosFundamento"));
            int fijosNoTituladoFundamento = v.numeroValido(request.getParameter("fijosNoTituladoFundamento"));
            int fijosNoTituladoEstudiandoFundamento = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoFundamento"));
            int fijosComputacion = v.numeroValido(request.getParameter("fijosComputacion"));
            int fijosNoTituladoComputacion = v.numeroValido(request.getParameter("fijosNoTituladoComputacion"));
            int fijosNoTituladoEstudiandoComputacion = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoComputacion"));
            int fijosMilitar = v.numeroValido(request.getParameter("fijosMilitar"));
            int fijosNoTituladoMilitar = v.numeroValido(request.getParameter("fijosNoTituladoMilitar"));
            int fijosNoTituladoEstudiandoMilitar = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoMilitar"));
            int fijosPractica = v.numeroValido(request.getParameter("fijosPractica"));
            int fijosNoTituladoPractica = v.numeroValido(request.getParameter("fijosNoTituladoPractica"));
            int fijosNoTituladoEstudiandoPractica = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoPractica"));
            int fijosBasicas = v.numeroValido(request.getParameter("fijosBasicas"));
            int fijosNoTituladoBasicas = v.numeroValido(request.getParameter("fijosNoTituladoBasicas"));
            int fijosNoTituladoEstudiandoBasicas = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoBasicas"));
            int fijosEjercicio = v.numeroValido(request.getParameter("fijosEjercicio"));
            int fijosNoTituladoEjercicio = v.numeroValido(request.getParameter("fijosNoTituladoEjercicio"));
            int fijosNoTituladoEstudiandoEjercicio = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoEjercicio"));
            int fijosInstructor = v.numeroValido(request.getParameter("fijosInstructor"));
            int fijosNoTituladoInstructor = v.numeroValido(request.getParameter("fijosNoTituladoInstructor"));
            int fijosNoTituladoEstudiandoInstructor = v.numeroValido(request.getParameter("fijosNoTituladoEstudiandoInstructor"));

            pf = new ProfesorAsignatura(new ProfesorFijo(fijosEspanol, fijosNoTituladoEspanol, fijosNoTituladoEstudiandoEspanol), new ProfesorFijo(fijosLiteratura, fijosNoTituladoLiteratura, fijosNoTituladoEstudiandoLiteratura), new ProfesorFijo(fijosMatematica, fijosNoTituladoMatematica, fijosNoTituladoEstudiandoMatematica), new ProfesorFijo(fijosFisica, fijosNoTituladoFisica, fijosNoTituladoEstudiandoFisica), new ProfesorFijo(fijosQuimica, fijosNoTituladoQuimica, fijosNoTituladoEstudiandoQuimica), new ProfesorFijo(fijosBiologia, fijosNoTituladoBiologia, fijosNoTituladoEstudiandoBiologia), new ProfesorFijo(fijosHistoria, fijosNoTituladoHistoria, fijosNoTituladoEstudiandoHistoria), new ProfesorFijo(fijosIdiomaExtranjero, fijosNoTituladoIdiomaExtranjero, fijosNoTituladoEstudiandoIdiomaExtranjero), new ProfesorFijo(fijosEdcnFisica, fijosNoTituladoEdcnFisica, fijosNoTituladoEstudiandoEdcnFisica), new ProfesorFijo(fijosEdcnArtistica, fijosNoTituladoEdcnArtistica, fijosNoTituladoEstudiandoEdcnArtistica), new ProfesorFijo(fijosMarxismo, fijosNoTituladoMarxismo, fijosNoTituladoEstudiandoMarxismo), new ProfesorFijo(fijosFundamento, fijosNoTituladoFundamento, fijosNoTituladoEstudiandoFundamento), new ProfesorFijo(fijosComputacion, fijosNoTituladoComputacion, fijosNoTituladoEstudiandoComputacion), new ProfesorFijo(fijosMilitar, fijosNoTituladoMilitar, fijosNoTituladoEstudiandoMilitar), new ProfesorFijo(fijosPractica, fijosNoTituladoPractica, fijosNoTituladoEstudiandoPractica), new ProfesorFijo(fijosBasicas, fijosNoTituladoBasicas, fijosNoTituladoEstudiandoBasicas), new ProfesorFijo(fijosEjercicio, fijosNoTituladoEjercicio, fijosNoTituladoEstudiandoEjercicio), new ProfesorFijo(fijosInstructor, fijosNoTituladoInstructor, fijosNoTituladoEstudiandoInstructor));

            // Profesores Contratados
            int contratadosEspanol = v.numeroValido(request.getParameter("contratadosEspanol"));
            int contratadosNoProfesionalesEspanol = v.numeroValido(request.getParameter("contratadosNoProfesionalesEspanol"));
            int contratadosNoProfesionalesEstudiandoEspanol = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoEspanol"));
            int contratadosLiteratura = v.numeroValido(request.getParameter("contratadosLiteratura"));
            int contratadosNoProfesionalesLiteratura = v.numeroValido(request.getParameter("contratadosNoProfesionalesLiteratura"));
            int contratadosNoProfesionalesEstudiandoLiteratura = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoLiteratura"));
            int contratadosMatematica = v.numeroValido(request.getParameter("contratadosMatematica"));
            int contratadosNoProfesionalesMatematica = v.numeroValido(request.getParameter("contratadosNoProfesionalesMatematica"));
            int contratadosNoProfesionalesEstudiandoMatematica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoMatematica"));
            int contratadosFisica = v.numeroValido(request.getParameter("contratadosFisica"));
            int contratadosNoProfesionalesFisica = v.numeroValido(request.getParameter("contratadosNoProfesionalesFisica"));
            int contratadosNoProfesionalesEstudiandoFisica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoFisica"));
            int contratadosQuimica = v.numeroValido(request.getParameter("contratadosQuimica"));
            int contratadosNoProfesionalesQuimica = v.numeroValido(request.getParameter("contratadosNoProfesionalesQuimica"));
            int contratadosNoProfesionalesEstudiandoQuimica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoQuimica"));
            int contratadosBiologia = v.numeroValido(request.getParameter("contratadosBiologia"));
            int contratadosNoProfesionalesBiologia = v.numeroValido(request.getParameter("contratadosNoProfesionalesBiologia"));
            int contratadosNoProfesionalesEstudiandoBiologia = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoBiologia"));
            int contratadosHistoria = v.numeroValido(request.getParameter("contratadosHistoria"));
            int contratadosNoProfesionalesHistoria = v.numeroValido(request.getParameter("contratadosNoProfesionalesHistoria"));
            int contratadosNoProfesionalesEstudiandoHistoria = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoHistoria"));
            int contratadosIdiomaExtranjero = v.numeroValido(request.getParameter("contratadosIdiomaExtranjero"));
            int contratadosNoProfesionalesIdiomaExtranjero = v.numeroValido(request.getParameter("contratadosNoProfesionalesIdiomaExtranjero"));
            int contratadosNoProfesionalesEstudiandoIdiomaExtranjero = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoIdiomaExtranjero"));
            int contratadosEdcionFisica = v.numeroValido(request.getParameter("contratadosEdcionFisica"));
            int contratadosNoProfesionalesEdcionFisica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEdcionFisica"));
            int contratadosNoProfesionalesEstudiandoEdcionFisica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoEdcionFisica"));
            int contratadosEdcionArtistica = v.numeroValido(request.getParameter("contratadosEdcionArtistica"));
            int contratadosNoProfesionalesEdcionArtistica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEdcionArtistica"));
            int contratadosNoProfesionalesEstudiandoEdcionArtistica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoEdcionArtistica"));
            int contratadosMarxismo = v.numeroValido(request.getParameter("contratadosMarxismo"));
            int contratadosNoProfesionalesMarxismo = v.numeroValido(request.getParameter("contratadosNoProfesionalesMarxismo"));
            int contratadosNoProfesionalesEstudiandoMarxismo = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoMarxismo"));
            int contratadosFundamento = v.numeroValido(request.getParameter("contratadosFundamento"));
            int contratadosNoProfesionalesFundamento = v.numeroValido(request.getParameter("contratadosNoProfesionalesFundamento"));
            int contratadosNoProfesionalesEstudiandoFundamento = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoFundamento"));
            int contratadosComputacion = v.numeroValido(request.getParameter("contratadosComputacion"));
            int contratadosNoProfesionalesComputacion = v.numeroValido(request.getParameter("contratadosNoProfesionalesComputacion"));
            int contratadosNoProfesionalesEstudiandoComputacion = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoComputacion"));
            int contratadosMilitar = v.numeroValido(request.getParameter("contratadosMilitar"));
            int contratadosNoProfesionalesMilitar = v.numeroValido(request.getParameter("contratadosNoProfesionalesMilitar"));
            int contratadosNoProfesionalesEstudiandoMilitar = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoMilitar"));
            int contratadosPractica = v.numeroValido(request.getParameter("contratadosPractica"));
            int contratadosNoProfesionalesPractica = v.numeroValido(request.getParameter("contratadosNoProfesionalesPractica"));
            int contratadosNoProfesionalesEstudiandoPractica = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoPractica"));
            int contratadosBasicas = v.numeroValido(request.getParameter("contratadosBasicas"));
            int contratadosNoProfesionalesBasicas = v.numeroValido(request.getParameter("contratadosNoProfesionalesBasicas"));
            int contratadosNoProfesionalesEstudiandoBasicas = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoBasicas"));
            int contratadosEjercicio = v.numeroValido(request.getParameter("contratadosEjercicio"));
            int contratadosNoProfesionalesEjercicio = v.numeroValido(request.getParameter("contratadosNoProfesionalesEjercicio"));
            int contratadosNoProfesionalesEstudiandoEjercicio = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoEjercicio"));
            int contratadosInstructor = v.numeroValido(request.getParameter("contratadosInstructor"));
            int contratadosNoProfesionalesInstructor = v.numeroValido(request.getParameter("contratadosNoProfesionalesInstructor"));
            int contratadosNoProfesionalesEstudiandoInstructor = v.numeroValido(request.getParameter("contratadosNoProfesionalesEstudiandoInstructor"));

            pc = new ProfesorAsignatura(new ProfesorContrato(contratadosEspanol, contratadosNoProfesionalesEspanol, contratadosNoProfesionalesEstudiandoEspanol), new ProfesorContrato(contratadosLiteratura, contratadosNoProfesionalesLiteratura, contratadosNoProfesionalesEstudiandoLiteratura), new ProfesorContrato(contratadosMatematica, contratadosNoProfesionalesMatematica, contratadosNoProfesionalesEstudiandoMatematica), new ProfesorContrato(contratadosFisica, contratadosNoProfesionalesFisica, contratadosNoProfesionalesEstudiandoFisica), new ProfesorContrato(contratadosQuimica, contratadosNoProfesionalesQuimica, contratadosNoProfesionalesEstudiandoQuimica), new ProfesorContrato(contratadosBiologia, contratadosNoProfesionalesBiologia, contratadosNoProfesionalesEstudiandoBiologia), new ProfesorContrato(contratadosHistoria, contratadosNoProfesionalesHistoria, contratadosNoProfesionalesEstudiandoHistoria), new ProfesorContrato(contratadosIdiomaExtranjero, contratadosNoProfesionalesIdiomaExtranjero, contratadosNoProfesionalesEstudiandoIdiomaExtranjero), new ProfesorContrato(contratadosEdcionFisica, contratadosNoProfesionalesEdcionFisica, contratadosNoProfesionalesEstudiandoEdcionFisica), new ProfesorContrato(contratadosEdcionArtistica, contratadosNoProfesionalesEdcionArtistica, contratadosNoProfesionalesEstudiandoEdcionArtistica), new ProfesorContrato(contratadosMarxismo, contratadosNoProfesionalesMarxismo, contratadosNoProfesionalesEstudiandoMarxismo), new ProfesorContrato(contratadosFundamento, contratadosNoProfesionalesFundamento, contratadosNoProfesionalesEstudiandoFundamento), new ProfesorContrato(contratadosComputacion, contratadosNoProfesionalesComputacion, contratadosNoProfesionalesEstudiandoComputacion), new ProfesorContrato(contratadosMilitar, contratadosNoProfesionalesMilitar, contratadosNoProfesionalesEstudiandoMilitar), new ProfesorContrato(contratadosPractica, contratadosNoProfesionalesPractica, contratadosNoProfesionalesEstudiandoPractica), new ProfesorContrato(contratadosBasicas, contratadosNoProfesionalesBasicas, contratadosNoProfesionalesEstudiandoBasicas), new ProfesorContrato(contratadosEjercicio, contratadosNoProfesionalesEjercicio, contratadosNoProfesionalesEstudiandoEjercicio), new ProfesorContrato(contratadosInstructor, contratadosNoProfesionalesInstructor, contratadosNoProfesionalesEstudiandoInstructor));
            ComposicionPersonal n_datos = new ComposicionPersonal(pf, pc);

            Centro c = centros.validarObtenerId(idE);
            long idP = c.getComposicionPersonal().getIdPersonal();

            if (Validaciones.ValoresIncorrectos) {
                n_datos.setIdPersonal(-1);
            }

            personal.validarActualizarProfesoresAsig(idP, n_datos);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("ProfesorAsignaturaError", true);
            m.addAttribute("fijos", pf);
            m.addAttribute("contratados", pc);

            return editarProfesoresPorAsignatura(idE, m);
        }

        r.addAttribute("idE", idE);
        return "redirect:/personal";
    }

    @GetMapping(path = {"/personal"})
    public String editarComposicionPersonal(@RequestParam(name = "idE") long idE, Model m) {

        Validaciones.ValoresIncorrectos = false;

        try {
            Centro c = centros.validarObtenerId(idE);

            if (!m.containsAttribute("PersonalError")) {
                m.addAttribute("personal", c.getComposicionPersonal());
            }

            m.addAttribute("datos_centro", c);
            activarCampos_SoloUsuario(m, "Usuario");

            if (exito) {
                m.addAttribute("exito", exito);
                exito = false;
            }

            return "expediente/personal";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/personal"})
    public String editarComposicionPersonalPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, Model m, RedirectAttributes r) {

        ComposicionPersonal cp, n_datos = null;

        try {
            // Docentes
            int totalProfesoresHembras = v.numeroValido(request.getParameter("totalProfesoresHembras"));
            int totalBibliotecarios = v.numeroValido(request.getParameter("totalBibliotecarios"));
            int totalBibliotecariosHembras = v.numeroValido(request.getParameter("totalBibliotecariosHembras"));
            int totalInstructoresArte = v.numeroValido(request.getParameter("totalInstructoresArte"));
            int totalInstructoresArteHembras = v.numeroValido(request.getParameter("totalInstructoresArteHembras"));
            int totalPsicopedagogos = v.numeroValido(request.getParameter("totalPsicopedagogos"));
            int totalPsicopedagogosHembras = v.numeroValido(request.getParameter("totalPsicopedagogosHembras"));
            int alumnosMaestro = v.numeroValido(request.getParameter("alumnosMaestro"));

            // No Docentes
            int totalNoDocente = v.numeroValido(request.getParameter("totalNoDocente"));
            int totalNoDocenteHembras = v.numeroValido(request.getParameter("totalNoDocenteHembras"));

            // Inactivos
            int totalProfesoresInactivos = v.numeroValido(request.getParameter("profesoresInactivos"));
            int otrosDocentesInactivos = v.numeroValido(request.getParameter("otrosDocentesInactivos"));
            int noDocentesInactivos = v.numeroValido(request.getParameter("noDocentesInactivos"));

            Centro centro = centros.validarObtenerId(idE);

            n_datos = new ComposicionPersonal(new Personal(0, totalProfesoresHembras), new Personal(totalBibliotecarios, totalBibliotecariosHembras), new Personal(totalInstructoresArte, totalInstructoresArteHembras), new Personal(totalPsicopedagogos, totalPsicopedagogosHembras), alumnosMaestro, new Personal(totalNoDocente, totalNoDocenteHembras), totalProfesoresInactivos, otrosDocentesInactivos, noDocentesInactivos);

            cp = centro.getComposicionPersonal();
            long idP = cp.getIdPersonal();
            n_datos.getDocentes().setTotal(cp.getDocentes().getTotal());

            if (Validaciones.ValoresIncorrectos) {
                n_datos.setIdPersonal(-1);
            }

            personal.validarActualizar(idP, n_datos);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("personal", n_datos);
            m.addAttribute("PersonalError", true);

            return editarComposicionPersonal(idE, m);
        }

        if (cp.getNoDocentes().getTotal() + cp.getTotalInactivos() == 0) {
            exito = true;
        }
        r.addAttribute("idE", idE);
        return "redirect:/personal";
    }

    @GetMapping(path = {"/rango_edades"})
    public String editarRangoEdades(@RequestParam(name = "idE") long idE, Model m) {

        Validaciones.ValoresIncorrectos = false;

        try {
            Centro centro = centros.validarObtenerId(idE);

            if (!m.containsAttribute("RangoEdadesError")) {
                m.addAttribute("rango", centro.getRangosEdades());
            }

            m.addAttribute("datos_centro", centro);
            activarCampos_SoloUsuario(m, "Usuario");

            return "expediente/rango_edades";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/rango_edades"})
    public String editarRangoEdadesPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, RedirectAttributes r, Model m) throws Exception {

        Centro centro = centros.validarObtenerId(idE);
        RangoEdad re = centro.getRangosEdades();
        long idR = re.getIdRangoEdad();
        RangoEdad n_datos = null;

        try {
            int totalMenos15 = v.numeroValido(request.getParameter("menos15"));
            int menos15Hembras = v.numeroValido(request.getParameter("menos15Hembras"));
            int totalEntre15_16 = v.numeroValido(request.getParameter("entre15_16"));
            int entre15_16Hembras = v.numeroValido(request.getParameter("entre15_16Hembras"));
            int totalMas16 = v.numeroValido(request.getParameter("mas16"));
            int mas16Hembras = v.numeroValido(request.getParameter("mas16Hembras"));

            int totalMenos15CPT = v.numeroValido(request.getParameter("menos15CPT"));
            int menos15HembrasCPT = v.numeroValido(request.getParameter("menos15HembrasCPT"));
            int totalEntre15_16CPT = v.numeroValido(request.getParameter("entre15_16CPT"));
            int entre15_16HembrasCPT = v.numeroValido(request.getParameter("entre15_16HembrasCPT"));
            int totalMas16CPT = v.numeroValido(request.getParameter("mas16CPT"));
            int mas16HembrasCPT = v.numeroValido(request.getParameter("mas16HembrasCPT"));

            Rango diurno = new Rango(totalMenos15, menos15Hembras, totalEntre15_16, entre15_16Hembras, totalMas16, mas16Hembras);
            Rango cpt = new Rango(totalMenos15CPT, menos15HembrasCPT, totalEntre15_16CPT, entre15_16HembrasCPT, totalMas16CPT, mas16HembrasCPT);

            n_datos = new RangoEdad(diurno, cpt);
            n_datos.setCentro(centro);

            if (Validaciones.ValoresIncorrectos) {
                n_datos.setIdRangoEdad(-1);
            }

            rangosEdades.validarActualizar(idR, n_datos);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("rango", n_datos);
            m.addAttribute("RangoEdadesError", true);

            return editarRangoEdades(idE, m);
        }

        r.addAttribute("idE", idE);
        return "redirect:/rango_edades";
    }

    private boolean exito = false;

    @GetMapping(path = {"/planilla_datos"})
    public String addPlanillaDatos(@RequestParam(name = "idE") long idE, Model m) {

        Validaciones.ValoresIncorrectos = false;

        try {
            Centro c = centros.validarObtenerId(idE);
            m.addAttribute("datos_centro", c);
            m.addAttribute("especialidades", especialidadesCentros.validarListarSinCaptar(idE));
            PlanillaDatos pd;

            if (!m.containsAttribute("PlanillaDatosError")) {
                List<EspecialidadActual> act = new ArrayList<>();
                act.add(new EspecialidadActual());
                act.add(new EspecialidadActual());
                act.add(new EspecialidadActual());
                act.add(new EspecialidadActual());

                List<EspecialidadAnterior> ant = new ArrayList<>();
                ant.add(new EspecialidadAnterior());
                ant.add(new EspecialidadAnterior());
                ant.add(new EspecialidadAnterior());
                ant.add(new EspecialidadAnterior());

                pd = new PlanillaDatos(ant, act, 0, 0, 0, 0, 0, 0);
                m.addAttribute("bien", true);
            } else {
                m.addAttribute("mal", true);
                pd = (PlanillaDatos) m.getAttribute("planilla");
            }

            m.addAttribute("planilla", pd);
            activarCampos_SoloUsuario(m, "Usuario");

            if (exito) {
                m.addAttribute("exito", exito);
                exito = false;
            }
            return "expediente/planilla_datos";
        } catch (Exception e) {
            return "error/error404";
        }
    }

    @PostMapping(path = {"/planilla_datos"})
    public String addPlanillaDatosPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, RedirectAttributes r, Model m) {

        PlanillaDatos pd = null;

        try {
            Centro centro = centros.validarObtenerId(idE);

            long idEspecialidad = Integer.parseInt(request.getParameter("especialidades"));
            EspecialidadCentro espCentro = especialidadesCentros.validarObtenerId(idEspecialidad);

            // Año Escolar Terminado
            int matriculaInicial1ro = v.numeroValido(request.getParameter("matr_inicial1"));
            int altas1ro = v.numeroValido(request.getParameter("altas1"));
            int altasTraslado1ro = v.numeroValido(request.getParameter("altas_traslado1"));
            int matriculaInicialAjustada1ro = v.numeroValido(request.getParameter("matr_inicial_aj1"));
            int matriculaFinal1ro = v.numeroValido(request.getParameter("matr_final1"));
            int aprobados1ro = v.numeroValido(request.getParameter("aprobados1"));
            int bajas1ro = v.numeroValido(request.getParameter("bajas1"));
            int bajasTraslado1ro = v.numeroValido(request.getParameter("bajas_traslado1"));

            int matriculaInicial2do = v.numeroValido(request.getParameter("matr_inicial2"));
            int altas2do = v.numeroValido(request.getParameter("altas2"));
            int altasTraslado2do = v.numeroValido(request.getParameter("altas_traslado2"));
            int matriculaInicialAjustada2do = v.numeroValido(request.getParameter("matr_inicial_aj2"));
            int matriculaFinal2do = v.numeroValido(request.getParameter("matr_final2"));
            int aprobados2do = v.numeroValido(request.getParameter("aprobados2"));
            int bajas2do = v.numeroValido(request.getParameter("bajas2"));
            int bajasTraslado2do = v.numeroValido(request.getParameter("bajas_traslado2"));

            int matriculaInicial3ro = v.numeroValido(request.getParameter("matr_inicial3"));
            int altas3ro = v.numeroValido(request.getParameter("altas3"));
            int altasTraslado3ro = v.numeroValido(request.getParameter("altas_traslado3"));
            int matriculaInicialAjustada3ro = v.numeroValido(request.getParameter("matr_inicial_aj3"));
            int matriculaFinal3ro = v.numeroValido(request.getParameter("matr_final3"));
            int aprobados3ro = v.numeroValido(request.getParameter("aprobados3"));
            int bajas3ro = v.numeroValido(request.getParameter("bajas3"));
            int bajasTraslado3ro = v.numeroValido(request.getParameter("bajas_traslado3"));

            int matriculaInicial4to = v.numeroValido(request.getParameter("matr_inicial4"));
            int altas4to = v.numeroValido(request.getParameter("altas4"));
            int altasTraslado4to = v.numeroValido(request.getParameter("altas_traslado4"));
            int matriculaInicialAjustada4to = v.numeroValido(request.getParameter("matr_inicial_aj4"));
            int matriculaFinal4to = v.numeroValido(request.getParameter("matr_final4"));
            int aprobados4to = v.numeroValido(request.getParameter("aprobados4"));
            int bajas4to = v.numeroValido(request.getParameter("bajas4"));
            int bajasTraslado4to = v.numeroValido(request.getParameter("bajas_traslado4"));

            List<EspecialidadAnterior> espAnt = new LinkedList<>();
            espAnt.add(new EspecialidadAnterior(annoEstudio.obtenerId(1), matriculaInicial1ro, altas1ro, altasTraslado1ro, matriculaInicialAjustada1ro, matriculaFinal1ro, aprobados1ro, bajas1ro, bajasTraslado1ro));
            espAnt.add(new EspecialidadAnterior(annoEstudio.obtenerId(2), matriculaInicial2do, altas2do, altasTraslado2do, matriculaInicialAjustada2do, matriculaFinal2do, aprobados2do, bajas2do, bajasTraslado2do));
            espAnt.add(new EspecialidadAnterior(annoEstudio.obtenerId(3), matriculaInicial3ro, altas3ro, altasTraslado3ro, matriculaInicialAjustada3ro, matriculaFinal3ro, aprobados3ro, bajas3ro, bajasTraslado3ro));
            espAnt.add(new EspecialidadAnterior(annoEstudio.obtenerId(4), matriculaInicial4to, altas4to, altasTraslado4to, matriculaInicialAjustada4to, matriculaFinal4to, aprobados4to, bajas4to, bajasTraslado4to));

            // Año Escolar Iniciado
            int grupos1ro = v.numeroValido(request.getParameter("grupos1"));
            int nuevoIngreso1ro = v.numeroValido(request.getParameter("n_igr1"));
            int nuevoIngresoTraslado1ro = v.numeroValido(request.getParameter("n_igr_traslado1"));
            int repitentes1ro = v.numeroValido(request.getParameter("repitentes1"));
            int repitentesTraslado1ro = v.numeroValido(request.getParameter("repitentes_traslado1"));
            int reingresos1ro = v.numeroValido(request.getParameter("reingresos1"));
            int matriculaActual1ro = v.numeroValido(request.getParameter("matr_actual1"));
            int matriculaActualHembras1ro = v.numeroValido(request.getParameter("matr_actual_hembras1"));

            int grupos2do = v.numeroValido(request.getParameter("grupos2"));
            int nuevoIngreso2do = v.numeroValido(request.getParameter("n_igr2"));
            int nuevoIngresoTraslado2do = v.numeroValido(request.getParameter("n_igr_traslado2"));
            int repitentes2do = v.numeroValido(request.getParameter("repitentes2"));
            int repitentesTraslado2do = v.numeroValido(request.getParameter("repitentes_traslado2"));
            int reingresos2do = v.numeroValido(request.getParameter("reingresos2"));
            int matriculaActual2do = v.numeroValido(request.getParameter("matr_actual2"));
            int matriculaActualHembras2do = v.numeroValido(request.getParameter("matr_actual_hembras2"));

            int grupos3ro = v.numeroValido(request.getParameter("grupos3"));
            int nuevoIngreso3ro = v.numeroValido(request.getParameter("n_igr3"));
            int nuevoIngresoTraslado3ro = v.numeroValido(request.getParameter("n_igr_traslado3"));
            int repitentes3ro = v.numeroValido(request.getParameter("repitentes3"));
            int repitentesTraslado3ro = v.numeroValido(request.getParameter("repitentes_traslado3"));
            int reingresos3ro = v.numeroValido(request.getParameter("reingresos3"));
            int matriculaActual3ro = v.numeroValido(request.getParameter("matr_actual3"));
            int matriculaActualHembras3ro = v.numeroValido(request.getParameter("matr_actual_hembras3"));

            int grupos4to = v.numeroValido(request.getParameter("grupos4"));
            int nuevoIngreso4to = v.numeroValido(request.getParameter("n_igr4"));
            int nuevoIngresoTraslado4to = v.numeroValido(request.getParameter("n_igr_traslado4"));
            int repitentes4to = v.numeroValido(request.getParameter("repitentes4"));
            int repitentesTraslado4to = v.numeroValido(request.getParameter("repitentes_traslado4"));
            int reingresos4to = v.numeroValido(request.getParameter("reingresos4"));
            int matriculaActual4to = v.numeroValido(request.getParameter("matr_actual4"));
            int matriculaActualHembras4to = v.numeroValido(request.getParameter("matr_actual_hembras4"));

            List<EspecialidadActual> espAct = new LinkedList<>();
            espAct.add(new EspecialidadActual(annoEstudio.obtenerId(1), grupos1ro, nuevoIngreso1ro, nuevoIngresoTraslado1ro, repitentes1ro, repitentesTraslado1ro, reingresos1ro, matriculaActual1ro, matriculaActualHembras1ro));
            espAct.add(new EspecialidadActual(annoEstudio.obtenerId(2), grupos2do, nuevoIngreso2do, nuevoIngresoTraslado2do, repitentes2do, repitentesTraslado2do, reingresos2do, matriculaActual2do, matriculaActualHembras2do));
            espAct.add(new EspecialidadActual(annoEstudio.obtenerId(3), grupos3ro, nuevoIngreso3ro, nuevoIngresoTraslado3ro, repitentes3ro, repitentesTraslado3ro, reingresos3ro, matriculaActual3ro, matriculaActualHembras3ro));
            espAct.add(new EspecialidadActual(annoEstudio.obtenerId(4), grupos4to, nuevoIngreso4to, nuevoIngresoTraslado4to, repitentes4to, repitentesTraslado4to, reingresos4to, matriculaActual4to, matriculaActualHembras4to));

            int totalProcdGraduados = v.numeroValido(request.getParameter("total_graduados"));
            int totalProcdPosiblesGraduados = v.numeroValido(request.getParameter("total_posibles_graduados"));
            int totalProcdNuevosIngresos = v.numeroValido(request.getParameter("total_nuevo_ingreso"));
            int hembProcdGraduados = v.numeroValido(request.getParameter("hembras_graduadas"));
            int hembProcPosiblesGraduados = v.numeroValido(request.getParameter("hembras_posibles_graduadas"));
            int hembProcNuevIngresos = v.numeroValido(request.getParameter("hembras_nuevo_ingreso"));

            pd = new PlanillaDatos(centro, espCentro, espAnt, espAct, totalProcdGraduados, hembProcdGraduados, totalProcdPosiblesGraduados, hembProcPosiblesGraduados, totalProcdNuevosIngresos, hembProcNuevIngresos);

            if (Validaciones.ValoresIncorrectos) {
                pd.setIdPlanilla(-1);
            }

            planillas.validarAdicionar(pd);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("PlanillaDatosError", true);
            m.addAttribute("planilla", pd);

            return addPlanillaDatos(idE, m);
        }
        exito = true;
        r.addAttribute("idE", idE);
        return "redirect:/planilla_datos";
    }

    @GetMapping(path = {"/lst_planillas"})
    public String lstPlanillasDatos(@RequestParam(name = "idE") long idE, Model m) {

        try {
            Centro c = centros.validarObtenerId(idE);
            m.addAttribute("planillas", planillas.validarListarPlanillasDatosCentro(c.getIdCentro()));
            m.addAttribute("datos_centro", c);

            return "expediente/lst_planillas_datos";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/del_planilla_datos"})
    public String eliminarPlanillaDatos(@RequestParam(name = "idP") long idP, RedirectAttributes r) {

        try {
            PlanillaDatos p = planillas.validarObtenerId(idP);
            planillas.validarEliminar(idP);

            r.addAttribute("idE", p.getCentro().getIdCentro());
            return "redirect:/lst_planillas";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/editar_planilla_datos"})
    public String editarPlanillaDatos(@RequestParam(name = "idP") long idP, Model m) {

        Validaciones.ValoresIncorrectos = false;

        try {
            PlanillaDatos pd;
            if (m.containsAttribute("EditarPlanillaDatosError")) {
                pd = (PlanillaDatos) m.getAttribute("planilla");
            } else {
                pd = planillas.validarObtenerId(idP);
            }

            int añosFaltan = 4 - pd.getEspecialidadesActuales().size();
            pd.setEspecialidadesActuales(planillas.ordenarEspActualesPorAñoEstudio(pd, añosFaltan));
            pd.setEspecialidadesAnteriores(planillas.ordenarEspAnterioresPorAñoEstudio());

            m.addAttribute("planilla", pd);
            m.addAttribute("datos_centro", pd.getCentro());

            return "expediente/editar_planilla_datos";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/editar_planilla_datos"})
    public String editarPlanillaDatosPOST(HttpServletRequest request, @RequestParam(name = "idP") long idP, RedirectAttributes r, Model m) throws Exception {

        PlanillaDatos pd = planillas.validarObtenerId(idP);
        Centro centro = pd.getCentro();

        List<EspecialidadActual> actuales = pd.getEspecialidadesActuales();
        List<EspecialidadAnterior> anteriores = pd.getEspecialidadesAnteriores();

        actuales.sort((EspecialidadActual p1, EspecialidadActual p2) -> new String(p1.getAnnoEstudio().getTipoAnno()).compareTo(p2.getAnnoEstudio().getTipoAnno()));
        anteriores.sort((EspecialidadAnterior p1, EspecialidadAnterior p2) -> new String(p1.getAnnoEstudio().getTipoAnno()).compareTo(p2.getAnnoEstudio().getTipoAnno()));

        PlanillaDatos n_datos = null;

        try {
            int idEspecialidad = Integer.parseInt(request.getParameter("especialidades"));
            EspecialidadCentro espCentro = especialidadesCentros.validarObtenerId(idEspecialidad);

            // Año Escolar Terminado
            int matriculaInicial1ro = v.numeroValido(request.getParameter("matr_inicial1"));
            int altas1ro = v.numeroValido(request.getParameter("altas1"));
            int altasTraslado1ro = v.numeroValido(request.getParameter("altas_traslado1"));
            int matriculaInicialAjustada1ro = v.numeroValido(request.getParameter("matr_inicial_aj1"));
            int matriculaFinal1ro = v.numeroValido(request.getParameter("matr_final1"));
            int aprobados1ro = v.numeroValido(request.getParameter("aprobados1"));
            int bajas1ro = v.numeroValido(request.getParameter("bajas1"));
            int bajasTraslado1ro = v.numeroValido(request.getParameter("bajas_traslado1"));

            int matriculaInicial2do = v.numeroValido(request.getParameter("matr_inicial2"));
            int altas2do = v.numeroValido(request.getParameter("altas2"));
            int altasTraslado2do = v.numeroValido(request.getParameter("altas_traslado2"));
            int matriculaInicialAjustada2do = v.numeroValido(request.getParameter("matr_inicial_aj2"));
            int matriculaFinal2do = v.numeroValido(request.getParameter("matr_final2"));
            int aprobados2do = v.numeroValido(request.getParameter("aprobados2"));
            int bajas2do = v.numeroValido(request.getParameter("bajas2"));
            int bajasTraslado2do = v.numeroValido(request.getParameter("bajas_traslado2"));

            int matriculaInicial3ro = v.numeroValido(request.getParameter("matr_inicial3"));
            int altas3ro = v.numeroValido(request.getParameter("altas3"));
            int altasTraslado3ro = v.numeroValido(request.getParameter("altas_traslado3"));
            int matriculaInicialAjustada3ro = v.numeroValido(request.getParameter("matr_inicial_aj3"));
            int matriculaFinal3ro = v.numeroValido(request.getParameter("matr_final3"));
            int aprobados3ro = v.numeroValido(request.getParameter("aprobados3"));
            int bajas3ro = v.numeroValido(request.getParameter("bajas3"));
            int bajasTraslado3ro = v.numeroValido(request.getParameter("bajas_traslado3"));

            int matriculaInicial4to = v.numeroValido(request.getParameter("matr_inicial4"));
            int altas4to = v.numeroValido(request.getParameter("altas4"));
            int altasTraslado4to = v.numeroValido(request.getParameter("altas_traslado4"));
            int matriculaInicialAjustada4to = v.numeroValido(request.getParameter("matr_inicial_aj4"));
            int matriculaFinal4to = v.numeroValido(request.getParameter("matr_final4"));
            int aprobados4to = v.numeroValido(request.getParameter("aprobados4"));
            int bajas4to = v.numeroValido(request.getParameter("bajas4"));
            int bajasTraslado4to = v.numeroValido(request.getParameter("bajas_traslado4"));

            // Año Escolar Iniciado
            int grupos1ro = v.numeroValido(request.getParameter("grupos1"));
            int nuevoIngreso1ro = v.numeroValido(request.getParameter("n_igr1"));
            int nuevoIngresoTraslado1ro = v.numeroValido(request.getParameter("n_igr_traslado1"));
            int repitentes1ro = v.numeroValido(request.getParameter("repitentes1"));
            int repitentesTraslado1ro = v.numeroValido(request.getParameter("repitentes_traslado1"));
            int reingresos1ro = v.numeroValido(request.getParameter("reingresos1"));
            int matriculaActual1ro = v.numeroValido(request.getParameter("matr_actual1"));
            int matriculaActualHembras1ro = v.numeroValido(request.getParameter("matr_actual_hembras1"));

            int grupos2do = v.numeroValido(request.getParameter("grupos2"));
            int nuevoIngreso2do = v.numeroValido(request.getParameter("n_igr2"));
            int nuevoIngresoTraslado2do = v.numeroValido(request.getParameter("n_igr_traslado2"));
            int repitentes2do = v.numeroValido(request.getParameter("repitentes2"));
            int repitentesTraslado2do = v.numeroValido(request.getParameter("repitentes_traslado2"));
            int reingresos2do = v.numeroValido(request.getParameter("reingresos2"));
            int matriculaActual2do = v.numeroValido(request.getParameter("matr_actual2"));
            int matriculaActualHembras2do = v.numeroValido(request.getParameter("matr_actual_hembras2"));

            int grupos3ro = v.numeroValido(request.getParameter("grupos3"));
            int nuevoIngreso3ro = v.numeroValido(request.getParameter("n_igr3"));
            int nuevoIngresoTraslado3ro = v.numeroValido(request.getParameter("n_igr_traslado3"));
            int repitentes3ro = v.numeroValido(request.getParameter("repitentes3"));
            int repitentesTraslado3ro = v.numeroValido(request.getParameter("repitentes_traslado3"));
            int reingresos3ro = v.numeroValido(request.getParameter("reingresos3"));
            int matriculaActual3ro = v.numeroValido(request.getParameter("matr_actual3"));
            int matriculaActualHembras3ro = v.numeroValido(request.getParameter("matr_actual_hembras3"));

            int grupos4to = v.numeroValido(request.getParameter("grupos4"));
            int nuevoIngreso4to = v.numeroValido(request.getParameter("n_igr4"));
            int nuevoIngresoTraslado4to = v.numeroValido(request.getParameter("n_igr_traslado4"));
            int repitentes4to = v.numeroValido(request.getParameter("repitentes4"));
            int repitentesTraslado4to = v.numeroValido(request.getParameter("repitentes_traslado4"));
            int reingresos4to = v.numeroValido(request.getParameter("reingresos4"));
            int matriculaActual4to = v.numeroValido(request.getParameter("matr_actual4"));
            int matriculaActualHembras4to = v.numeroValido(request.getParameter("matr_actual_hembras4"));

            for (int i = 0; i < actuales.size(); i++) {
                if (i == 0) {
                    actuales.get(0).setParametros(grupos1ro, nuevoIngreso1ro, nuevoIngresoTraslado1ro, repitentes1ro, repitentesTraslado1ro, reingresos1ro, matriculaActual1ro, matriculaActualHembras1ro);
                    anteriores.get(0).setParametros(matriculaInicial1ro, altas1ro, altasTraslado1ro, matriculaInicialAjustada1ro, matriculaFinal1ro, aprobados1ro, bajas1ro, bajasTraslado1ro);
                } else if (i == 1) {
                    actuales.get(1).setParametros(grupos2do, nuevoIngreso2do, nuevoIngresoTraslado2do, repitentes2do, repitentesTraslado2do, reingresos2do, matriculaActual2do, matriculaActualHembras2do);
                    anteriores.get(1).setParametros(matriculaInicial2do, altas2do, altasTraslado2do, matriculaInicialAjustada2do, matriculaFinal2do, aprobados2do, bajas2do, bajasTraslado2do);
                } else if (i == 2) {
                    actuales.get(2).setParametros(grupos3ro, nuevoIngreso3ro, nuevoIngresoTraslado3ro, repitentes3ro, repitentesTraslado3ro, reingresos3ro, matriculaActual3ro, matriculaActualHembras3ro);
                    anteriores.get(2).setParametros(matriculaInicial3ro, altas3ro, altasTraslado3ro, matriculaInicialAjustada3ro, matriculaFinal3ro, aprobados3ro, bajas3ro, bajasTraslado3ro);
                } else {
                    actuales.get(3).setParametros(grupos4to, nuevoIngreso4to, nuevoIngresoTraslado4to, repitentes4to, repitentesTraslado4to, reingresos4to, matriculaActual4to, matriculaActualHembras4to);
                    anteriores.get(3).setParametros(matriculaInicial4to, altas4to, altasTraslado4to, matriculaInicialAjustada4to, matriculaFinal4to, aprobados4to, bajas4to, bajasTraslado4to);
                }
            }

            int totalProcdGraduados = v.numeroValido(request.getParameter("total_graduados"));
            int totalProcdPosiblesGraduados = v.numeroValido(request.getParameter("total_posibles_graduados"));
            int totalProcdNuevosIngresos = v.numeroValido(request.getParameter("total_nuevo_ingreso"));
            int hembProcdGraduados = v.numeroValido(request.getParameter("hembras_graduadas"));
            int hembProcPosiblesGraduados = v.numeroValido(request.getParameter("hembras_posibles_graduadas"));
            int hembProcNuevIngresos = v.numeroValido(request.getParameter("hembras_nuevo_ingreso"));

            n_datos = new PlanillaDatos(centro, espCentro, anteriores, actuales, totalProcdGraduados, hembProcdGraduados, totalProcdPosiblesGraduados, hembProcPosiblesGraduados, totalProcdNuevosIngresos, hembProcNuevIngresos);

            if (Validaciones.ValoresIncorrectos) {
                n_datos.setIdPlanilla(-1);
            } else {
                n_datos.setIdPlanilla(idP);
            }

            planillas.validarActualizar(idP, n_datos);
        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("planilla", n_datos);
            m.addAttribute("EditarPlanillaDatosError", true);

            return editarPlanillaDatos(idP, m);
        }

        r.addAttribute("idE", n_datos.getCentro().getIdCentro());
        return "redirect:/lst_planillas";
    }


    @GetMapping(path = {"/lst_esp_anteriores"})
    public String lstEspecialidadesAnteriores(@RequestParam(name = "idE") long idE, Model m) {

        try {
            Centro c = centros.validarObtenerId(idE);
            m.addAttribute("especialidades", especialidadesAnteriores.especialidadesAnterioresCentro(idE));
            m.addAttribute("datos_centro", c);

            return "expediente/lst_esp_anteriores";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/lst_esp_actuales"})
    public String lstEspecialidadesActuales(@RequestParam(name = "idE") long idE, Model m) {
        try {
            Centro c = centros.validarObtenerId(idE);
            m.addAttribute("especialidades", especialidadesActuales.especialidadesActualesCentro(idE));
            m.addAttribute("datos_centro", c);

            return "expediente/lst_esp_actuales";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @RequestMapping("/prof_asig_pdf")
    public String generarProfAsigPdf(HttpServletResponse response, @RequestParam(name = "idE") long idE) {
        try {
            Centro cen = centros.validarObtenerId(idE);
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignatura(cen, response);
            return null;
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @RequestMapping("/prof_asig_excel")
    public String generarProfAsigExcel(HttpServletResponse response, @RequestParam(name = "idE") long idE) {
        try {
            Centro cen = centros.validarObtenerId(idE);
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignatura(cen, response);
            return null;
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @RequestMapping("/rango_pdf")
    public String generarRangoEdadPdf(HttpServletResponse response, @RequestParam(name = "idE") long idE) {
        try {
            Centro cen = centros.validarObtenerId(idE);
            generarDocumentoPDF.generarDocumentoPdf_RangoEdad(cen, response);
            return null;
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @RequestMapping("/rango_excel")
    public String generarCentrosExcel(HttpServletResponse response, @RequestParam(name = "idE") long idE) {
        try {
            Centro cen = centros.validarObtenerId(idE);
            generarDocumentoExcel.generarDocumentoExcel_RangoEdad(cen, response);
            return null;
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @RequestMapping("/ficha_pdf")
    public String generarFichaPdfCentro(HttpServletResponse response, @RequestParam(name = "idE") long idE) throws FileNotFoundException, DocumentException {
        if (!lst_ficha_aux.isEmpty()) {
            if (ultimo_cen_visitado.getIdCentro() == idE) {
                generarDocumentoPDF.generarDocumentoPdf_FichaCentro(ultimo_cen_visitado, lst_ficha_aux, response);
                return null;
            }
        }
        return generarDocumento(idE, response, "PDF");
    }

    @RequestMapping("/ficha_excel")
    public String generarFichaExcelCentro(HttpServletResponse response, @RequestParam(name = "idE") long idE) {
        if (!lst_ficha_aux.isEmpty()) {
            if (ultimo_cen_visitado.getIdCentro() == idE) {
                generarDocumentoExcel.generarDocumentoExcel_FichaCentro(ultimo_cen_visitado, lst_ficha_aux, response);
                return null;
            }
        }
        return generarDocumento(idE, response, "EXCEL");
    }

    private String generarDocumento(long idE, HttpServletResponse response, String tipoDocumento) {
        try {
            Centro cen = centros.validarObtenerId(idE);
            if (tipoDocumento.equals("PDF")) {
                generarDocumentoPDF.generarDocumentoPdf_FichaCentro(cen, datosFicha(cen), response);
            } else if (tipoDocumento.equals("EXCEL")) {
                generarDocumentoExcel.generarDocumentoExcel_FichaCentro(cen, datosFicha(cen), response);
            }
            return null;
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }

    public void activarCampos_SoloUsuario(Model m, String rol) {
        if (!getUsuarioAutenticado().getRol().getTipoRol().equals(rol)) {
            m.addAttribute("disabled", true);
        } else {
            m.addAttribute("disabled", false);
        }
        v.ocultarOpciones(getUsuarioAutenticado(), rol, m);
    }

    private List<Ficha> datosFicha(Centro centro) {
        long idE = centro.getIdCentro();
        Caratula car = centro.getCaratula();
        ComposicionPersonal per = centro.getComposicionPersonal();
        Personal doc = per.getDocentes();
        MatriculaInicial miTM = car.getMatriculaInicialTM(), miOC = car.getMatriculaInicialOC();

        lst_ficha_aux = new ArrayList<>();
        ultimo_cen_visitado = centro;

        int matricula = miTM.getTotal() + miOC.getTotal();
        int matriculaHembras = miTM.getTotalHembras() + miOC.getTotalHembras();
        int matriculaCPT = car.getMatriculaInicialCPT_TM().getTotal();
        int matriculaHembrasCPT = car.getMatriculaInicialCPT_TM().getTotalHembras();

        int interno = car.getRegimenTM().getInternos() + car.getRegimenOC().getInternos();
        int seminternos = car.getRegimenTM().getSeminternos() + car.getRegimenOC().getSeminternos();
        int nuevosIngresos = miTM.getNuevoIngreso() + miOC.getNuevoIngreso();
        int nuevosIngresosCPT = car.getMatriculaInicialCPT_TM().getNuevoIngreso();

        int frenteAula = doc.getTotal();
        int totalDocentes = per.getTotalDocentes();
        int totalDocentesHembras = per.getTotalDocentesHembras();

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }

        lst_ficha_aux.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_ficha_aux.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesCentroRama(idE, "Diurno", ram);
            matH = especialidadesActuales.matriculasActualesHembrasCentroRama(idE, "Diurno", ram);
            nIngr = especialidadesActuales.nuevosIngresosTotalCentroRama(idE, "Diurno", ram);
            lst_ficha_aux.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_ficha_aux.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_ficha_aux;
    }
}