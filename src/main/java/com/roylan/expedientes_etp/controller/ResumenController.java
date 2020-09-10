package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.business.ValidacionCentroImpl;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.*;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoExcelImpl;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoPDFImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class ResumenController {

    @Autowired
    private HttpSession session;

    @Autowired
    private GestionarRama ramas;

    @Autowired
    private GestionarCurso cursos;

    @Autowired
    private GestionarResumen resumen;

    @Autowired
    private GestionarFamilia familias;

    @Autowired
    private ValidacionCentroImpl centros;

    @Autowired
    private GestionarMunicipio municipios;

    @Autowired
    private GestionarProvincia provincias;

    @Autowired
    GenerarDocumentoPDFImpl generarDocumentoPDF;

    @Autowired
    GenerarDocumentoExcelImpl generarDocumentoExcel;

    @Autowired
    private GestionarEspecialidadActualImpl especialidadesActuales;

    @Autowired
    private GestionarEspecialidadAnteriorImpl especialidadesAnteriores;

    private Municipio mcpioSelecionado;
    private Provincia provSelecionada;
    private ComposicionPersonal profM = null, profP = null, profN = null;
    private RangoEdad rangoM = null, rangoP = null, rangoN = null;
    private Caratula carM = null, carP = null, carN = null;
    private List<Ficha> lst_fichaM = new ArrayList<>(), lst_fichaP = new ArrayList<>(), lst_fichaN = new ArrayList<>(),
            lst_fichaMT = new ArrayList<>(), lst_fichaPT = new ArrayList<>(), lst_fichaNT = new ArrayList<>();

    @GetMapping(path = {"/caratula_mcpal"})
    public String caratulaMcpal(Model m) {

        mcpioSelecionado = getUsuarioAutenticado().getMcpio();
        long idMcpioUsuarioAutenticado = mcpioSelecionado.getIdMunicipio();

        configurarOpcionesMcpio(m, idMcpioUsuarioAutenticado);

        m.addAttribute("caratula", caratulaMpcio(idMcpioUsuarioAutenticado));

        return "resumen/caratulas/caratula_mcpal";
    }

    @PostMapping(path = {"/caratula_mcpal"})
    public String caratulaMcpalPOST(HttpServletRequest request, Model m) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);

        String titulo = " : " + mcpioSelecionado.getNombMunicipio() + " (" + mcpioSelecionado.getProv().getNombProvincia() + ")";

        m.addAttribute("titulo", titulo);
        m.addAttribute("municipios", lstMunicipios());
        m.addAttribute("caratula", caratulaMpcio(idMcpioSeleccionado));

        return "resumen/caratulas/caratula_mcpal";
    }

    @GetMapping(path = {"/caratula_prov"})
    public String caratulaProv(Model m) {

        provSelecionada = getUsuarioAutenticado().getMcpio().getProv();
        long idProvUsuarioAutenticado = provSelecionada.getIdProvincia();

        configurarOpcionesProv(m, idProvUsuarioAutenticado);

        m.addAttribute("caratula", caratulaProv(idProvUsuarioAutenticado));

        return "resumen/caratulas/caratula_prov";
    }

    @PostMapping(path = {"/caratula_prov"})
    public String caratulaProvPOST(HttpServletRequest request, Model m) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);

        String titulo = " : " + provSelecionada.getNombProvincia();

        m.addAttribute("titulo", titulo);
        m.addAttribute("provincias", lstProvincias());
        m.addAttribute("caratula", caratulaProv(idProvSeleccionada));

        return "resumen/caratulas/caratula_prov";
    }

    @GetMapping(path = {"/caratula_nac"})
    public String caratulaNacional(Model m) {

        m.addAttribute("caratula", caratulaNac());

        return "resumen/caratulas/caratula_nac";
    }

    @GetMapping(path = {"/personal_mcpal"})
    public String centrosMcpal(Model m) {

        long idMcpioUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getIdMunicipio();

        configurarOpcionesMcpio(m, idMcpioUsuarioAutenticado);

        m.addAttribute("personal", personalMcpio(idMcpioUsuarioAutenticado));
        m.addAttribute("profesores", profesoresMcpio(idMcpioUsuarioAutenticado));

        return "resumen/personales/personal_mcpal";
    }

    @PostMapping(path = {"/personal_mcpal"})
    public String personalMcpalPOST(HttpServletRequest request, Model m) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);

        String titulo = " : " + mcpioSelecionado.getNombMunicipio() + " (" + mcpioSelecionado.getProv().getNombProvincia() + ")";

        m.addAttribute("titulo", titulo);
        m.addAttribute("municipios", lstMunicipios());

        m.addAttribute("personal", personalMcpio(idMcpioSeleccionado));
        m.addAttribute("profesores", profesoresMcpio(idMcpioSeleccionado));

        return "resumen/personales/personal_mcpal";
    }

    @GetMapping(path = {"/personal_prov"})
    public String personalProv(Model m) {

        long idProvUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getProv().getIdProvincia();

        configurarOpcionesProv(m, idProvUsuarioAutenticado);

        m.addAttribute("personal", personalProv(idProvUsuarioAutenticado));
        m.addAttribute("profesores", profesoresProv(idProvUsuarioAutenticado));

        return "resumen/personales/personal_prov";
    }

    @PostMapping(path = {"/personal_prov"})
    public String personalProvPOST(HttpServletRequest request, Model m) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);

        String titulo = " : " + provSelecionada.getNombProvincia();

        m.addAttribute("titulo", titulo);
        m.addAttribute("provincias", lstProvincias());

        m.addAttribute("personal", personalProv(idProvSeleccionada));
        m.addAttribute("profesores", profesoresProv(idProvSeleccionada));


        return "resumen/personales/personal_prov";
    }

    @GetMapping(path = {"/personal_nac"})
    public String personalNacional(Model m) {

        m.addAttribute("personal", personalNac());
        m.addAttribute("profesores", profesoresNac());

        return "resumen/personales/personal_nac";
    }

    @GetMapping(path = {"/rango_mcpal"})
    public String rangoEdadesMcpal(Model m) {

        long idMcpioUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getIdMunicipio();

        configurarOpcionesMcpio(m, idMcpioUsuarioAutenticado);

        m.addAttribute("rango", rangoEdadesMcpio(idMcpioUsuarioAutenticado));

        return "resumen/rangos/rango_mcpal";
    }

    @PostMapping(path = {"/rango_mcpal"})
    public String rangoEdadesMcpalPOST(HttpServletRequest request, Model m) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);

        String titulo = " : " + mcpioSelecionado.getNombMunicipio() + " (" + mcpioSelecionado.getProv().getNombProvincia() + ")";

        m.addAttribute("titulo", titulo);
        m.addAttribute("municipios", lstMunicipios());
        m.addAttribute("rango", rangoEdadesMcpio(idMcpioSeleccionado));

        return "resumen/rangos/rango_mcpal";
    }

    @GetMapping(path = {"/rango_prov"})
    public String rangoEdadesProv(Model m) {

        long idProvUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getProv().getIdProvincia();

        configurarOpcionesProv(m, idProvUsuarioAutenticado);

        m.addAttribute("rango", rangosEdadesProv(idProvUsuarioAutenticado));

        return "resumen/rangos/rango_prov";
    }

    @PostMapping(path = {"/rango_prov"})
    public String rangoEdadesProvPOST(HttpServletRequest request, Model m) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);

        String titulo = " : " + provSelecionada.getNombProvincia();

        m.addAttribute("titulo", titulo);
        m.addAttribute("provincias", lstProvincias());
        m.addAttribute("rango", rangosEdadesProv(idProvSeleccionada));

        return "resumen/rangos/rango_prov";
    }

    @GetMapping(path = {"/rango_nac"})
    public String rangoEdadesNacional(Model m) {

        m.addAttribute("rango", rangosEdadesNac());

        return "resumen/rangos/rango_nac";
    }

    @GetMapping(path = {"/ficha_mcpal"})
    public String fichaMcpal(Model m) {

        long idMcpioUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getIdMunicipio();

        configurarOpcionesMcpio(m, idMcpioUsuarioAutenticado);

        m.addAttribute("lst_fichas", fichaMcpio(idMcpioUsuarioAutenticado));

        return "resumen/fichas/general/ficha_mcpal";
    }

    @PostMapping(path = {"/ficha_mcpal"})
    public String fichaMcpalPOST(HttpServletRequest request, Model m) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);

        String titulo = " : " + mcpioSelecionado.getNombMunicipio() + " (" + mcpioSelecionado.getProv().getNombProvincia() + ")";

        m.addAttribute("titulo", titulo);
        m.addAttribute("municipios", lstMunicipios());
        m.addAttribute("lst_fichas", fichaMcpio(idMcpioSeleccionado));

        return "resumen/fichas/general/ficha_mcpal";
    }

    @GetMapping(path = {"/ficha_prov"})
    public String fichaProv(Model m) {

        long idProvUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getProv().getIdProvincia();

        configurarOpcionesProv(m, idProvUsuarioAutenticado);
        m.addAttribute("lst_fichas", fichaProv(idProvUsuarioAutenticado));

        return "resumen/fichas/general/ficha_prov";
    }

    @PostMapping(path = {"/ficha_prov"})
    public String fichaProvPOST(HttpServletRequest request, Model m) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);

        String titulo = " : " + provSelecionada.getNombProvincia();

        m.addAttribute("titulo", titulo);
        m.addAttribute("provincias", lstProvincias());
        m.addAttribute("lst_fichas", fichaProv(idProvSeleccionada));

        return "resumen/fichas/general/ficha_prov";
    }

    @GetMapping(path = {"/ficha_nac"})
    public String fichaNacional(Model m) {

        m.addAttribute("lst_fichas", fichaNac());

        return "resumen/fichas/general/ficha_nac";
    }

    @GetMapping(path = {"/ficha_turquino_mcpal"})
    public String fichaTurquinoMcpal(Model m) {

        long idMcpioUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getIdMunicipio();

        configurarOpcionesMcpio(m, idMcpioUsuarioAutenticado);

        m.addAttribute("lst_fichas", fichaTurquinoMcpio(idMcpioUsuarioAutenticado));

        return "resumen/fichas/turquino/ficha_mcpal";
    }

    @PostMapping(path = {"/ficha_turquino_mcpal"})
    public String fichaTurquinoMcpalPOST(HttpServletRequest request, Model m) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);

        String titulo = " : " + mcpioSelecionado.getNombMunicipio() + " (" + mcpioSelecionado.getProv().getNombProvincia() + ")";

        m.addAttribute("titulo", titulo);
        m.addAttribute("municipios", lstMunicipios());
        m.addAttribute("lst_fichas", fichaTurquinoMcpio(idMcpioSeleccionado));

        return "resumen/fichas/turquino/ficha_mcpal";
    }

    @GetMapping(path = {"/ficha_turquino_prov"})
    public String fichaTurquinoProv(Model m) throws Exception {

        long idProvUsuarioAutenticado = getUsuarioAutenticado().getMcpio().getProv().getIdProvincia();

        configurarOpcionesProv(m, idProvUsuarioAutenticado);
        m.addAttribute("lst_fichas", fichaTurquinoProv(idProvUsuarioAutenticado));

        return "resumen/fichas/turquino/ficha_prov";
    }

    @PostMapping(path = {"/ficha_turquino_prov"})
    public String fichaTurquinoProvPOST(HttpServletRequest request, Model m) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);

        String titulo = " : " + provSelecionada.getNombProvincia();

        m.addAttribute("titulo", titulo);
        m.addAttribute("provincias", lstProvincias());
        m.addAttribute("lst_fichas", fichaTurquinoProv(idProvSeleccionada));

        return "resumen/fichas/turquino/ficha_prov";
    }

    @GetMapping(path = {"/ficha_turquino_nac"})
    public String fichaTurquinoNacional(Model m) {

        m.addAttribute("lst_fichas", fichaTurquinoNac());

        return "resumen/fichas/turquino/ficha_nac";
    }

    private void configurarOpcionesMcpio(Model m, long idMcpioUsuarioAutenticado) {
        if (getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
            m.addAttribute("ocultar", true);
            Municipio mcpio = municipios.obtenerId(idMcpioUsuarioAutenticado);
            String titulo = " : " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";
            m.addAttribute("titulo", titulo);
        } else {
            m.addAttribute("titulo", " ");
            m.addAttribute("ocultar", false);
            m.addAttribute("oculto", true);
        }
        m.addAttribute("municipios", lstMunicipios());
    }

    private void configurarOpcionesProv(Model m, long idProvUsuarioAutenticado) {

        String rol = getUsuarioAutenticado().getRol().getTipoRol();
        if (rol.equals("Supervisor") || rol.equals("Usuario")) {
            Provincia prov = provincias.obtenerId(idProvUsuarioAutenticado);
            String titulo = " : " + prov.getNombProvincia();
            m.addAttribute("titulo", titulo);
            m.addAttribute("ocultar", true);
        } else {
            m.addAttribute("titulo", " ");
            m.addAttribute("ocultar", false);
            m.addAttribute("oculto", true);
        }
        m.addAttribute("provincias", lstProvincias());
    }

    private Caratula caratulaMpcio(long idMcpioUsuarioAutenticado) {

        int totalTM = resumen.matriculasTotalesMcpio(idMcpioUsuarioAutenticado, 1, 1);
        int totalHembrasTM = resumen.matriculasTotalesHembrasMcpio(idMcpioUsuarioAutenticado, 1, 1);
        int internosTM = resumen.internosTMTotalesMcpio(idMcpioUsuarioAutenticado);
        int internosHembrasTM = resumen.internosTMTotalesHembrasMcpio(idMcpioUsuarioAutenticado);
        int seminternosTM = resumen.seminternosTMTotalesMcpio(idMcpioUsuarioAutenticado);
        int seminternosHembrasTM = resumen.seminternosTMTotalesHembrasMcpio(idMcpioUsuarioAutenticado);
        int nuevoIngresoTM = resumen.nuevosIngresosTotalesMcpio(idMcpioUsuarioAutenticado, 1, 1);
        int nuevoIngresoHembrasTM = resumen.nuevosIngresosTotalesHembrasMcpio(idMcpioUsuarioAutenticado, 1, 1);

        int totalOC = resumen.matriculasTotalesMcpio(idMcpioUsuarioAutenticado, 2, 1);
        int totalHembrasOC = resumen.matriculasTotalesHembrasMcpio(idMcpioUsuarioAutenticado, 2, 1);
        int internosOC = resumen.internosOCTotalesMcpio(idMcpioUsuarioAutenticado);
        int internosHembrasOC = resumen.internosOCTotalesHembrasMcpio(idMcpioUsuarioAutenticado);
        int seminternosOC = resumen.seminternosOCTotalesMcpio(idMcpioUsuarioAutenticado);
        int seminternosHembrasOC = resumen.seminternosOCTotalesHembrasMcpio(idMcpioUsuarioAutenticado);
        int nuevoIngresoOC = resumen.nuevosIngresosTotalesMcpio(idMcpioUsuarioAutenticado, 2, 1);
        int nuevoIngresoHembrasOC = resumen.nuevosIngresosTotalesHembrasMcpio(idMcpioUsuarioAutenticado, 2, 1);

        int matriculaCPT_TM = resumen.matriculasTotalesMcpio(idMcpioUsuarioAutenticado, 1, 2);
        int nuevoIngresoCPT_TM = resumen.nuevosIngresosTotalesMcpio(idMcpioUsuarioAutenticado, 1, 2);

        int matriculaHembrasCPT_TM = resumen.matriculasTotalesHembrasMcpio(idMcpioUsuarioAutenticado, 1, 2);
        int nuevoIngresoHembrasCPT_TM = resumen.nuevosIngresosTotalesHembrasMcpio(idMcpioUsuarioAutenticado, 1, 2);

        int matriculaFinalTM = resumen.matriculasFinalesTotalesMcpio(idMcpioUsuarioAutenticado, 1, 1);
        int aprobadosTM = resumen.aprobadosTotalesMcpio(idMcpioUsuarioAutenticado, 1, 1);
        int graduadosTM = resumen.graduadosTotalesMcpio(idMcpioUsuarioAutenticado, 1, 1);
        int matriculaFinalOC = resumen.matriculasFinalesTotalesMcpio(idMcpioUsuarioAutenticado, 2, 1);
        int aprobadosOC = resumen.aprobadosTotalesMcpio(idMcpioUsuarioAutenticado, 2, 1);
        int graduadosOC = resumen.graduadosTotalesMcpio(idMcpioUsuarioAutenticado, 2, 1);

        int matriculaFinalCPT_TM = resumen.matriculasFinalesTotalesMcpio(idMcpioUsuarioAutenticado, 1, 2);
        int aprobadosCPT_TM = resumen.aprobadosTotalesMcpio(idMcpioUsuarioAutenticado, 1, 2);
        int graduadosCPT_TM = resumen.graduadosTotalesMcpio(idMcpioUsuarioAutenticado, 1, 2);

        carM = new Caratula(new MatriculaInicial(totalTM, totalHembrasTM, nuevoIngresoTM, nuevoIngresoHembrasTM), new RegimenEstudio(internosTM, internosHembrasTM, seminternosTM, seminternosHembrasTM), new MatriculaInicial(totalOC, totalHembrasOC, nuevoIngresoOC, nuevoIngresoHembrasOC), new RegimenEstudio(internosOC, internosHembrasOC, seminternosOC, seminternosHembrasOC), new MatriculaInicial(matriculaCPT_TM, matriculaHembrasCPT_TM, nuevoIngresoCPT_TM, nuevoIngresoHembrasCPT_TM), new MatriculaFinal(matriculaFinalTM, aprobadosTM, graduadosTM), new MatriculaFinal(matriculaFinalOC, aprobadosOC, graduadosOC), new MatriculaFinal(matriculaFinalCPT_TM, aprobadosCPT_TM, graduadosCPT_TM));
        return carM;
    }

    private Caratula caratulaProv(long idProvUsuarioAutenticado) {

        int totalTM = resumen.matriculasTotalesProv(idProvUsuarioAutenticado, 1, 1);
        int totalHembrasTM = resumen.matriculasTotalesHembrasProv(idProvUsuarioAutenticado, 1, 1);
        int internosTM = resumen.internosTMTotalesProv(idProvUsuarioAutenticado);
        int internosHembrasTM = resumen.internosTMTotalesHembrasProv(idProvUsuarioAutenticado);
        int seminternosTM = resumen.seminternosTMTotalesProv(idProvUsuarioAutenticado);
        int seminternosHembrasTM = resumen.seminternosTMTotalesHembrasProv(idProvUsuarioAutenticado);
        int nuevoIngresoTM = resumen.nuevosIngresosTotalesProv(idProvUsuarioAutenticado, 1, 1);
        int nuevoIngresoHembrasTM = resumen.nuevosIngresosTotalesHembrasProv(idProvUsuarioAutenticado, 1, 1);

        int totalOC = resumen.matriculasTotalesProv(idProvUsuarioAutenticado, 2, 1);
        int totalHembrasOC = resumen.matriculasTotalesHembrasProv(idProvUsuarioAutenticado, 2, 1);
        int internosOC = resumen.internosOCTotalesProv(idProvUsuarioAutenticado);
        int internosHembrasOC = resumen.internosOCTotalesHembrasProv(idProvUsuarioAutenticado);
        int seminternosOC = resumen.seminternosOCTotalesProv(idProvUsuarioAutenticado);
        int seminternosHembrasOC = resumen.seminternosOCTotalesHembrasProv(idProvUsuarioAutenticado);
        int nuevoIngresoOC = resumen.nuevosIngresosTotalesProv(idProvUsuarioAutenticado, 2, 1);
        int nuevoIngresoHembrasOC = resumen.nuevosIngresosTotalesHembrasProv(idProvUsuarioAutenticado, 2, 1);

        int matriculaCPT_TM = resumen.matriculasTotalesProv(idProvUsuarioAutenticado, 1, 2);
        int nuevoIngresoCPT_TM = resumen.nuevosIngresosTotalesProv(idProvUsuarioAutenticado, 1, 2);

        int matriculaHembrasCPT_TM = resumen.matriculasTotalesHembrasProv(idProvUsuarioAutenticado, 1, 2);
        int nuevoIngresoHembrasCPT_TM = resumen.nuevosIngresosTotalesHembrasProv(idProvUsuarioAutenticado, 1, 2);

        int matriculaFinalTM = resumen.matriculasFinalesTotalesProv(idProvUsuarioAutenticado, 1, 1);
        int aprobadosTM = resumen.aprobadosTotalesProv(idProvUsuarioAutenticado, 1, 1);
        int graduadosTM = resumen.graduadosTotalesProv(idProvUsuarioAutenticado, 1, 1);
        int matriculaFinalOC = resumen.matriculasFinalesTotalesProv(idProvUsuarioAutenticado, 2, 1);
        int aprobadosOC = resumen.aprobadosTotalesProv(idProvUsuarioAutenticado, 2, 1);
        int graduadosOC = resumen.graduadosTotalesProv(idProvUsuarioAutenticado, 2, 1);

        int matriculaFinalCPT_TM = resumen.matriculasFinalesTotalesProv(idProvUsuarioAutenticado, 1, 2);
        int aprobadosCPT_TM = resumen.aprobadosTotalesProv(idProvUsuarioAutenticado, 1, 2);
        int graduadosCPT_TM = resumen.graduadosTotalesProv(idProvUsuarioAutenticado, 1, 2);

        carP = new Caratula(new MatriculaInicial(totalTM, totalHembrasTM, nuevoIngresoTM, nuevoIngresoHembrasTM), new RegimenEstudio(internosTM, internosHembrasTM, seminternosTM, seminternosHembrasTM), new MatriculaInicial(totalOC, totalHembrasOC, nuevoIngresoOC, nuevoIngresoHembrasOC), new RegimenEstudio(internosOC, internosHembrasOC, seminternosOC, seminternosHembrasOC), new MatriculaInicial(matriculaCPT_TM, matriculaHembrasCPT_TM, nuevoIngresoCPT_TM, nuevoIngresoHembrasCPT_TM), new MatriculaFinal(matriculaFinalTM, aprobadosTM, graduadosTM), new MatriculaFinal(matriculaFinalOC, aprobadosOC, graduadosOC), new MatriculaFinal(matriculaFinalCPT_TM, aprobadosCPT_TM, graduadosCPT_TM));
        return carP;
    }

    private Caratula caratulaNac() {
        int totalTM = resumen.matriculasTotalesNac(1, 1);
        int totalHembrasTM = resumen.matriculasTotalesHembrasNac(1, 1);
        int internosTM = resumen.internosTMTotalesNac();
        int internosHembrasTM = resumen.internosTMTotalesHembrasNac();
        int seminternosTM = resumen.seminternosTMTotalesNac();
        int seminternosHembrasTM = resumen.seminternosTMTotalesHembrasNac();
        int nuevoIngresoTM = resumen.nuevosIngresosTotalesNac(1, 1);
        int nuevoIngresoHembrasTM = resumen.nuevosIngresosTotalesHembrasNac(1, 1);

        int totalOC = resumen.matriculasTotalesNac(2, 1);
        int totalHembrasOC = resumen.matriculasTotalesHembrasNac(2, 1);
        int internosOC = resumen.internosOCTotalesNac();
        int internosHembrasOC = resumen.internosOCTotalesHembrasNac();
        int seminternosOC = resumen.seminternosOCTotalesNac();
        int seminternosHembrasOC = resumen.seminternosOCTotalesHembrasNac();
        int nuevoIngresoOC = resumen.nuevosIngresosTotalesNac(2, 1);
        int nuevoIngresoHembrasOC = resumen.nuevosIngresosTotalesHembrasNac(2, 1);

        int matriculaCPT_TM = resumen.matriculasTotalesNac(1, 2);
        int matriculaHembrasCPT_TM = resumen.matriculasTotalesHembrasNac(1, 2);
        int nuevoIngresoCPT_TM = resumen.nuevosIngresosTotalesNac(1, 2);
        int nuevoIngresoHembrasCPT_TM = resumen.nuevosIngresosTotalesHembrasNac(1, 2);

        int matriculaFinalTM = resumen.matriculasFinalesTotalesNac(1, 1);
        int aprobadosTM = resumen.aprobadosTotalesNac(1, 1);
        int graduadosTM = resumen.graduadosTotalesNac(1, 1);

        int matriculaFinalOC = resumen.matriculasFinalesTotalesNac(2, 1);
        int aprobadosOC = resumen.aprobadosTotalesNac(2, 1);
        int graduadosOC = resumen.graduadosTotalesNac(2, 1);

        int matriculaFinalCPT_TM = resumen.matriculasFinalesTotalesNac(1, 2);
        int aprobadosCPT_TM = resumen.aprobadosTotalesNac(1, 2);
        int graduadosCPT_TM = resumen.graduadosTotalesNac(1, 2);

        carN = new Caratula(new MatriculaInicial(totalTM, totalHembrasTM, nuevoIngresoTM, nuevoIngresoHembrasTM), new RegimenEstudio(internosTM, internosHembrasTM, seminternosTM, seminternosHembrasTM), new MatriculaInicial(totalOC, totalHembrasOC, nuevoIngresoOC, nuevoIngresoHembrasOC), new RegimenEstudio(internosOC, internosHembrasOC, seminternosOC, seminternosHembrasOC), new MatriculaInicial(matriculaCPT_TM, matriculaHembrasCPT_TM, nuevoIngresoCPT_TM, nuevoIngresoHembrasCPT_TM), new MatriculaFinal(matriculaFinalTM, aprobadosTM, graduadosTM), new MatriculaFinal(matriculaFinalOC, aprobadosOC, graduadosOC), new MatriculaFinal(matriculaFinalCPT_TM, aprobadosCPT_TM, graduadosCPT_TM));
        return carN;
    }

    private ComposicionPersonal personalMcpio(long idMcpioUsuarioAutenticado) {

        int totalProfesoresDocentes = resumen.profesoresDocentesTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresDocentesHembras = resumen.profesoresDocentesHembrasTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijos = resumen.profesoresFijosTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTitulados = resumen.profesoresFijosNoTituladosTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladosEstudiando = resumen.profesoresFijosNoTituladosEstudiandoTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratados = resumen.profesoresContratadosTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionales = resumen.profesoresContratadosNoProfesionalesTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalesEstudiando = resumen.profesoresContratadosNoProfesionalesTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalBibliotecarios = resumen.bibliotecariosTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalBibliotecariosHembras = resumen.bibliotecariosHembrasTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalInstructoresArte = resumen.instructoresArteTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalInstructoresArteHembras = resumen.instructoresArteHembrasTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalPsicopedagogos = resumen.psicopedagogosTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalPsicopedagogosHembras = resumen.psicopedagogosHembrasTotalesMcpio(idMcpioUsuarioAutenticado);
        int alumnosMaestros = resumen.alumnosMaestrosTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresNoDocentes = resumen.noDocentesTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresNoDocentesHembras = resumen.noDocentesHembrasTotalesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresInactivos = resumen.profesoresInactivosTotalesMcpio(idMcpioUsuarioAutenticado);
        int otrosDocentesInactivos = resumen.otrosDocentesInactivosTotalesMcpio(idMcpioUsuarioAutenticado);
        int noDocentesInactivos = resumen.noDocentesInactivosTotalesMcpio(idMcpioUsuarioAutenticado);

        return new ComposicionPersonal(new Personal(totalProfesoresDocentes, totalProfesoresDocentesHembras), totalProfesoresFijos, totalProfesoresFijosNoTitulados, totalProfesoresFijosNoTituladosEstudiando, totalProfesoresContratados, totalProfesoresContratadosNoProfesionales, totalProfesoresContratadosNoProfesionalesEstudiando, new Personal(totalBibliotecarios, totalBibliotecariosHembras), new Personal(totalInstructoresArte, totalInstructoresArteHembras), new Personal(totalPsicopedagogos, totalPsicopedagogosHembras), alumnosMaestros, new Personal(totalProfesoresNoDocentes, totalProfesoresNoDocentesHembras), totalProfesoresInactivos, otrosDocentesInactivos, noDocentesInactivos);
    }

    private ComposicionPersonal personalProv(long idProvUsuarioAutenticado) {

        int totalProfesoresDocentes = resumen.profesoresDocentesTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresDocentesHembras = resumen.profesoresDocentesHembrasTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresFijos = resumen.profesoresFijosTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTitulados = resumen.profesoresFijosNoTituladosTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladosEstudiando = resumen.profesoresFijosNoTituladosEstudiandoTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresContratados = resumen.profesoresContratadosTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionales = resumen.profesoresContratadosNoProfesionalesTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalesEstudiando = resumen.profesoresContratadosNoProfesionalesTotalesProv(idProvUsuarioAutenticado);
        int totalBibliotecarios = resumen.bibliotecariosTotalesProv(idProvUsuarioAutenticado);
        int totalBibliotecariosHembras = resumen.bibliotecariosHembrasTotalesProv(idProvUsuarioAutenticado);
        int totalInstructoresArte = resumen.instructoresArteTotalesProv(idProvUsuarioAutenticado);
        int totalInstructoresArteHembras = resumen.instructoresArteHembrasTotalesProv(idProvUsuarioAutenticado);
        int totalPsicopedagogos = resumen.psicopedagogosTotalesProv(idProvUsuarioAutenticado);
        int totalPsicopedagogosHembras = resumen.psicopedagogosHembrasTotalesProv(idProvUsuarioAutenticado);
        int alumnosMaestros = resumen.alumnosMaestrosTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresNoDocentes = resumen.noDocentesTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresNoDocentesHembras = resumen.noDocentesHembrasTotalesProv(idProvUsuarioAutenticado);
        int totalProfesoresInactivos = resumen.profesoresInactivosTotalesProv(idProvUsuarioAutenticado);
        int otrosDocentesInactivos = resumen.otrosDocentesInactivosTotalesProv(idProvUsuarioAutenticado);
        int noDocentesInactivos = resumen.noDocentesInactivosTotalesProv(idProvUsuarioAutenticado);

        return new ComposicionPersonal(new Personal(totalProfesoresDocentes, totalProfesoresDocentesHembras), totalProfesoresFijos, totalProfesoresFijosNoTitulados, totalProfesoresFijosNoTituladosEstudiando, totalProfesoresContratados, totalProfesoresContratadosNoProfesionales, totalProfesoresContratadosNoProfesionalesEstudiando, new Personal(totalBibliotecarios, totalBibliotecariosHembras), new Personal(totalInstructoresArte, totalInstructoresArteHembras), new Personal(totalPsicopedagogos, totalPsicopedagogosHembras), alumnosMaestros, new Personal(totalProfesoresNoDocentes, totalProfesoresNoDocentesHembras), totalProfesoresInactivos, otrosDocentesInactivos, noDocentesInactivos);
    }

    private ComposicionPersonal personalNac() {
        int totalProfesoresDocentes = resumen.profesoresDocentesTotalesNac();
        int totalProfesoresDocentesHembras = resumen.profesoresDocentesHembrasTotalesNac();
        int totalProfesoresFijos = resumen.profesoresFijosTotalesNac();
        int totalProfesoresFijosNoTitulados = resumen.profesoresFijosNoTituladosTotalesNac();
        int totalProfesoresFijosNoTituladosEstudiando = resumen.profesoresFijosNoTituladosEstudiandoTotalesNac();
        int totalProfesoresContratados = resumen.profesoresContratadosTotalesNac();
        int totalProfesoresContratadosNoProfesionales = resumen.profesoresContratadosNoProfesionalesTotalesNac();
        int totalProfesoresContratadosNoProfesionalesEstudiando = resumen.profesoresContratadosNoProfesionalesEstudiandoTotalesNac();
        int totalBibliotecarios = resumen.bibliotecariosTotalesNac();
        int totalBibliotecariosHembras = resumen.bibliotecariosHembrasTotalesNac();
        int totalInstructoresArte = resumen.instructoresArteTotalesNac();
        int totalInstructoresArteHembras = resumen.instructoresArteHembrasTotalesNac();
        int totalPsicopedagogos = resumen.psicopedagogosTotalesNac();
        int totalPsicopedagogosHembras = resumen.psicopedagogosHembrasTotalesNac();
        int alumnosMaestros = resumen.alumnosMaestrosTotalesNac();
        int totalProfesoresNoDocentes = resumen.noDocentesTotalesNac();
        int totalProfesoresNoDocentesHembras = resumen.noDocentesHembrasTotalesNac();
        int totalProfesoresInactivos = resumen.profesoresInactivosTotalesNac();
        int otrosDocentesInactivos = resumen.otrosDocentesInactivosTotalesNac();
        int noDocentesInactivos = resumen.noDocentesInactivosTotalesNac();

        return new ComposicionPersonal(new Personal(totalProfesoresDocentes, totalProfesoresDocentesHembras), totalProfesoresFijos, totalProfesoresFijosNoTitulados, totalProfesoresFijosNoTituladosEstudiando, totalProfesoresContratados, totalProfesoresContratadosNoProfesionales, totalProfesoresContratadosNoProfesionalesEstudiando, new Personal(totalBibliotecarios, totalBibliotecariosHembras), new Personal(totalInstructoresArte, totalInstructoresArteHembras), new Personal(totalPsicopedagogos, totalPsicopedagogosHembras), alumnosMaestros, new Personal(totalProfesoresNoDocentes, totalProfesoresNoDocentesHembras), totalProfesoresInactivos, otrosDocentesInactivos, noDocentesInactivos);
    }

    private ComposicionPersonal profesoresNac() {

        int totalProfesoresFijosEspanol = resumen.totalProfesoresFijosEspanolNac();
        int totalProfesoresFijosNoTituladoEspanol = resumen.totalProfesoresFijosNoTituladoEspanolNac();
        int totalProfesoresFijosNoTituladoEstudiandoEspanol = resumen.totalProfesoresFijosNoTituladoEstudiandoEspanolNac();
        int totalProfesoresFijosLiteratura = resumen.totalProfesoresFijosLiteraturaNac();
        int totalProfesoresFijosNoTituladoLiteratura = resumen.totalProfesoresFijosNoTituladoLiteraturaNac();
        int totalProfesoresFijosNoTituladoEstudiandoLiteratura = resumen.totalProfesoresFijosNoTituladoEstudiandoLiteraturaNac();
        int totalProfesoresFijosMatematica = resumen.totalProfesoresFijosMatematicaNac();
        int totalProfesoresFijosNoTituladoMatematica = resumen.totalProfesoresFijosNoTituladoMatematicaNac();
        int totalProfesoresFijosNoTituladoEstudiandoMatematica = resumen.totalProfesoresFijosNoTituladoEstudiandoMatematicaNac();
        int totalProfesoresFijosFisica = resumen.totalProfesoresFijosFisicaNac();
        int totalProfesoresFijosNoTituladoFisica = resumen.totalProfesoresFijosNoTituladoFisicaNac();
        int totalProfesoresFijosNoTituladoEstudiandoFisica = resumen.totalProfesoresFijosNoTituladoEstudiandoFisicaNac();
        int totalProfesoresFijosQuimica = resumen.totalProfesoresFijosQuimicaNac();
        int totalProfesoresFijosNoTituladoQuimica = resumen.totalProfesoresFijosNoTituladoQuimicaNac();
        int totalProfesoresFijosNoTituladoEstudiandoQuimica = resumen.totalProfesoresFijosNoTituladoEstudiandoQuimicaNac();
        int totalProfesoresFijosBiologia = resumen.totalProfesoresFijosBiologiaNac();
        int totalProfesoresFijosNoTituladoBiologia = resumen.totalProfesoresFijosNoTituladoBiologiaNac();
        int totalProfesoresFijosNoTituladoEstudiandoBiologia = resumen.totalProfesoresFijosNoTituladoEstudiandoBiologiaNac();
        int totalProfesoresFijosHistoria = resumen.totalProfesoresFijosHistoriaNac();
        int totalProfesoresFijosNoTituladoHistoria = resumen.totalProfesoresFijosNoTituladoHistoriaNac();
        int totalProfesoresFijosNoTituladoEstudiandoHistoria = resumen.totalProfesoresFijosNoTituladoEstudiandoHistoriaNac();
        int totalProfesoresFijosIngles = resumen.totalProfesoresFijosInglesNac();
        int totalProfesoresFijosNoTituladoIngles = resumen.totalProfesoresFijosNoTituladoInglesNac();
        int totalProfesoresFijosNoTituladoEstudiandoIngles = resumen.totalProfesoresFijosNoTituladoEstudiandoInglesNac();
        int totalProfesoresFijosEducFisica = resumen.totalProfesoresFijosEducFisicaNac();
        int totalProfesoresFijosNoTituladoEducFisica = resumen.totalProfesoresFijosNoTituladoEducFisicaNac();
        int totalProfesoresFijosNoTituladoEstudiandoEducFisica = resumen.totalProfesoresFijosNoTituladoEstudiandoEducFisicaNac();
        int totalProfesoresFijosEducArtistica = resumen.totalProfesoresFijosEducArtisticaNac();
        int totalProfesoresFijosNoTituladoEducArtistica = resumen.totalProfesoresFijosNoTituladoEducArtisticaNac();
        int totalProfesoresFijosNoTituladoEstudiandoEducArtistica = resumen.totalProfesoresFijosNoTituladoEstudiandoEducArtisticaNac();
        int totalProfesoresFijosMarxismo = resumen.totalProfesoresFijosMarxismoNac();
        int totalProfesoresFijosNoTituladoMarxismo = resumen.totalProfesoresFijosNoTituladoMarxismoNac();
        int totalProfesoresFijosNoTituladoEstudiandoMarxismo = resumen.totalProfesoresFijosNoTituladoEstudiandoMarxismoNac();
        int totalProfesoresFijosFundamento = resumen.totalProfesoresFijosFundamentoNac();
        int totalProfesoresFijosNoTituladoFundamento = resumen.totalProfesoresFijosNoTituladoFundamentoNac();
        int totalProfesoresFijosNoTituladoEstudiandoFundamento = resumen.totalProfesoresFijosNoTituladoEstudiandoFundamentoNac();
        int totalProfesoresFijosComputacion = resumen.totalProfesoresFijosComputacionNac();
        int totalProfesoresFijosNoTituladoComputacion = resumen.totalProfesoresFijosNoTituladoComputacionNac();
        int totalProfesoresFijosNoTituladoEstudiandoComputacion = resumen.totalProfesoresFijosNoTituladoEstudiandoComputacionNac();
        int totalProfesoresFijosMilitar = resumen.totalProfesoresFijosMilitarNac();
        int totalProfesoresFijosNoTituladoMilitar = resumen.totalProfesoresFijosNoTituladoMilitarNac();
        int totalProfesoresFijosNoTituladoEstudiandoMilitar = resumen.totalProfesoresFijosNoTituladoEstudiandoMilitarNac();
        int totalProfesoresFijosPractica = resumen.totalProfesoresFijosPracticaNac();
        int totalProfesoresFijosNoTituladoPractica = resumen.totalProfesoresFijosNoTituladoPracticaNac();
        int totalProfesoresFijosNoTituladoEstudiandoPractica = resumen.totalProfesoresFijosNoTituladoEstudiandoPracticaNac();
        int totalProfesoresFijosBasicas = resumen.totalProfesoresFijosBasicasNac();
        int totalProfesoresFijosNoTituladoBasica = resumen.totalProfesoresFijosNoTituladoBasicaNac();
        int totalProfesoresFijosNoTituladoEstudiandoBasica = resumen.totalProfesoresFijosNoTituladoEstudiandoBasicaNac();
        int totalProfesoresFijosEjercicio = resumen.totalProfesoresFijosEjercicioNac();
        int totalProfesoresFijosNoTituladoEjercicio = resumen.totalProfesoresFijosNoTituladoEjercicioNac();
        int totalProfesoresFijosNoTituladoEstudiandoEjercicio = resumen.totalProfesoresFijosNoTituladoEstudiandoEjercicioNac();
        int totalProfesoresFijosInstructor = resumen.totalProfesoresFijosInstructorNac();
        int totalProfesoresFijosNoTituladoInstructor = resumen.totalProfesoresFijosNoTituladoInstructorNac();
        int totalProfesoresFijosNoTituladoEstudiandoInstructor = resumen.totalProfesoresFijosNoTituladoEstudiandoInstructorNac();

        int totalProfesoresContratadosEspanol = resumen.totalProfesoresContratadosEspanolNac();
        int totalProfesoresContratadosNoProfesionalEspanol = resumen.totalProfesoresContratadosNoProfesionalEspanolNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoEspanol = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEspanolNac();
        int totalProfesoresContratadosLiteratura = resumen.totalProfesoresContratadosLiteraturaNac();
        int totalProfesoresContratadosNoProfesionalLiteratura = resumen.totalProfesoresContratadosNoProfesionalLiteraturaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoLiteratura = resumen.totalProfesoresContratadosNoProfesionalEstudiandoLiteraturaNac();
        int totalProfesoresContratadosMatematica = resumen.totalProfesoresContratadosMatematicaNac();
        int totalProfesoresContratadosNoProfesionalMatematica = resumen.totalProfesoresContratadosNoProfesionalMatematicaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoMatematica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMatematicaNac();
        int totalProfesoresContratadosFisica = resumen.totalProfesoresContratadosFisicaNac();
        int totalProfesoresContratadosNoProfesionalFisica = resumen.totalProfesoresContratadosNoProfesionalFisicaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoFisica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoFisicaNac();
        int totalProfesoresContratadosQuimica = resumen.totalProfesoresContratadosQuimicaNac();
        int totalProfesoresContratadosNoProfesionalQuimica = resumen.totalProfesoresContratadosNoProfesionalQuimicaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoQuimica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoQuimicaNac();
        int totalProfesoresContratadosBiologia = resumen.totalProfesoresContratadosBiologiaNac();
        int totalProfesoresContratadosNoProfesionalBiologia = resumen.totalProfesoresContratadosNoProfesionalBiologiaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoBiologia = resumen.totalProfesoresContratadosNoProfesionalEstudiandoBiologiaNac();
        int totalProfesoresContratadosHistoria = resumen.totalProfesoresContratadosHistoriaNac();
        int totalProfesoresContratadosNoProfesionalHistoria = resumen.totalProfesoresContratadosNoProfesionalHistoriaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoHistoria = resumen.totalProfesoresContratadosNoProfesionalEstudiandoHistoriaNac();
        int totalProfesoresContratadosIngles = resumen.totalProfesoresContratadosInglesNac();
        int totalProfesoresContratadosNoProfesionalIngles = resumen.totalProfesoresContratadosNoProfesionalInglesNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoIngles = resumen.totalProfesoresContratadosNoProfesionalEstudiandoInglesNac();
        int totalProfesoresContratadosEducFisica = resumen.totalProfesoresContratadosEducFisicaNac();
        int totalProfesoresContratadosNoProfesionalEducFisica = resumen.totalProfesoresContratadosNoProfesionalEducFisicaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoEducFisica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEducFisicaNac();
        int totalProfesoresContratadosEducArtistica = resumen.totalProfesoresContratadosEducArtisticaNac();
        int totalProfesoresContratadosNoProfesionalEducArtistica = resumen.totalProfesoresContratadosNoProfesionalEducArtisticaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoEducArtistica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEducArtisticaNac();
        int totalProfesoresContratadosMarxismo = resumen.totalProfesoresContratadosMarxismoNac();
        int totalProfesoresContratadosNoProfesionalMarxismo = resumen.totalProfesoresContratadosNoProfesionalMarxismoNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoMarxismo = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMarxismoNac();
        int totalProfesoresContratadosFundamento = resumen.totalProfesoresContratadosFundamentoNac();
        int totalProfesoresContratadosNoProfesionalFundamento = resumen.totalProfesoresContratadosNoProfesionalFundamentoNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoFundamento = resumen.totalProfesoresContratadosNoProfesionalEstudiandoFundamentoNac();
        int totalProfesoresContratadosComputacion = resumen.totalProfesoresContratadosComputacionNac();
        int totalProfesoresContratadosNoProfesionalComputacion = resumen.totalProfesoresContratadosNoProfesionalComputacionNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoComputacion = resumen.totalProfesoresContratadosNoProfesionalEstudiandoComputacionNac();
        int totalProfesoresContratadosMilitar = resumen.totalProfesoresContratadosMilitarNac();
        int totalProfesoresContratadosNoProfesionalMilitar = resumen.totalProfesoresContratadosNoProfesionalMilitarNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoMilitar = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMilitarNac();
        int totalProfesoresContratadosPractica = resumen.totalProfesoresContratadosPracticaNac();
        int totalProfesoresContratadosNoProfesionalPractica = resumen.totalProfesoresContratadosNoProfesionalPracticaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoPractica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoPracticaNac();
        int totalProfesoresContratadosBasica = resumen.totalProfesoresContratadosBasicasNac();
        int totalProfesoresContratadosNoProfesionalBasica = resumen.totalProfesoresContratadosNoProfesionalBasicaNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoBasica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoBasicasNac();
        int totalProfesoresContratadosEjercicio = resumen.totalProfesoresContratadosEjercicioNac();
        int totalProfesoresContratadosNoProfesionalEjercicio = resumen.totalProfesoresContratadosNoProfesionalEjercicioNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoEjercicio = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEjercicioNac();
        int totalProfesoresContratadosInstructor = resumen.totalProfesoresContratadosInstructorNac();
        int totalProfesoresContratadosNoProfesionalInstructor = resumen.totalProfesoresContratadosNoProfesionalInstructorNac();
        int totalProfesoresContratadosNoProfesionalEstudiandoInstructor = resumen.totalProfesoresContratadosNoProfesionalEstudiandoInstructorNac();

        ProfesorAsignatura pf = new ProfesorAsignatura(new ProfesorFijo(totalProfesoresFijosEspanol, totalProfesoresFijosNoTituladoEspanol, totalProfesoresFijosNoTituladoEstudiandoEspanol), new ProfesorFijo(totalProfesoresFijosLiteratura, totalProfesoresFijosNoTituladoLiteratura, totalProfesoresFijosNoTituladoEstudiandoLiteratura), new ProfesorFijo(totalProfesoresFijosMatematica, totalProfesoresFijosNoTituladoMatematica, totalProfesoresFijosNoTituladoEstudiandoMatematica), new ProfesorFijo(totalProfesoresFijosFisica, totalProfesoresFijosNoTituladoFisica, totalProfesoresFijosNoTituladoEstudiandoFisica), new ProfesorFijo(totalProfesoresFijosQuimica, totalProfesoresFijosNoTituladoQuimica, totalProfesoresFijosNoTituladoEstudiandoQuimica), new ProfesorFijo(totalProfesoresFijosBiologia, totalProfesoresFijosNoTituladoBiologia, totalProfesoresFijosNoTituladoEstudiandoBiologia), new ProfesorFijo(totalProfesoresFijosHistoria, totalProfesoresFijosNoTituladoHistoria, totalProfesoresFijosNoTituladoEstudiandoHistoria), new ProfesorFijo(totalProfesoresFijosIngles, totalProfesoresFijosNoTituladoIngles, totalProfesoresFijosNoTituladoEstudiandoIngles), new ProfesorFijo(totalProfesoresFijosEducFisica, totalProfesoresFijosNoTituladoEducFisica, totalProfesoresFijosNoTituladoEstudiandoEducFisica), new ProfesorFijo(totalProfesoresFijosEducArtistica, totalProfesoresFijosNoTituladoEducArtistica, totalProfesoresFijosNoTituladoEstudiandoEducArtistica), new ProfesorFijo(totalProfesoresFijosMarxismo, totalProfesoresFijosNoTituladoMarxismo, totalProfesoresFijosNoTituladoEstudiandoMarxismo), new ProfesorFijo(totalProfesoresFijosFundamento, totalProfesoresFijosNoTituladoFundamento, totalProfesoresFijosNoTituladoEstudiandoFundamento), new ProfesorFijo(totalProfesoresFijosComputacion, totalProfesoresFijosNoTituladoComputacion, totalProfesoresFijosNoTituladoEstudiandoComputacion), new ProfesorFijo(totalProfesoresFijosMilitar, totalProfesoresFijosNoTituladoMilitar, totalProfesoresFijosNoTituladoEstudiandoMilitar), new ProfesorFijo(totalProfesoresFijosPractica, totalProfesoresFijosNoTituladoPractica, totalProfesoresFijosNoTituladoEstudiandoPractica), new ProfesorFijo(totalProfesoresFijosBasicas, totalProfesoresFijosNoTituladoBasica, totalProfesoresFijosNoTituladoEstudiandoBasica), new ProfesorFijo(totalProfesoresFijosEjercicio, totalProfesoresFijosNoTituladoEjercicio, totalProfesoresFijosNoTituladoEstudiandoEjercicio), new ProfesorFijo(totalProfesoresFijosInstructor, totalProfesoresFijosNoTituladoInstructor, totalProfesoresFijosNoTituladoEstudiandoInstructor));
        ProfesorAsignatura pc = new ProfesorAsignatura(new ProfesorContrato(totalProfesoresContratadosEspanol, totalProfesoresContratadosNoProfesionalEspanol, totalProfesoresContratadosNoProfesionalEstudiandoEspanol), new ProfesorContrato(totalProfesoresContratadosLiteratura, totalProfesoresContratadosNoProfesionalLiteratura, totalProfesoresContratadosNoProfesionalEstudiandoLiteratura), new ProfesorContrato(totalProfesoresContratadosMatematica, totalProfesoresContratadosNoProfesionalMatematica, totalProfesoresContratadosNoProfesionalEstudiandoMatematica), new ProfesorContrato(totalProfesoresContratadosFisica, totalProfesoresContratadosNoProfesionalFisica, totalProfesoresContratadosNoProfesionalEstudiandoFisica), new ProfesorContrato(totalProfesoresContratadosQuimica, totalProfesoresContratadosNoProfesionalQuimica, totalProfesoresContratadosNoProfesionalEstudiandoQuimica), new ProfesorContrato(totalProfesoresContratadosBiologia, totalProfesoresContratadosNoProfesionalBiologia, totalProfesoresContratadosNoProfesionalEstudiandoBiologia), new ProfesorContrato(totalProfesoresContratadosHistoria, totalProfesoresContratadosNoProfesionalHistoria, totalProfesoresContratadosNoProfesionalEstudiandoHistoria), new ProfesorContrato(totalProfesoresContratadosIngles, totalProfesoresContratadosNoProfesionalIngles, totalProfesoresContratadosNoProfesionalEstudiandoIngles), new ProfesorContrato(totalProfesoresContratadosEducFisica, totalProfesoresContratadosNoProfesionalEducFisica, totalProfesoresContratadosNoProfesionalEstudiandoEducFisica), new ProfesorContrato(totalProfesoresContratadosEducArtistica, totalProfesoresContratadosNoProfesionalEducArtistica, totalProfesoresContratadosNoProfesionalEstudiandoEducArtistica), new ProfesorContrato(totalProfesoresContratadosMarxismo, totalProfesoresContratadosNoProfesionalMarxismo, totalProfesoresContratadosNoProfesionalEstudiandoMarxismo), new ProfesorContrato(totalProfesoresContratadosFundamento, totalProfesoresContratadosNoProfesionalFundamento, totalProfesoresContratadosNoProfesionalEstudiandoFundamento), new ProfesorContrato(totalProfesoresContratadosComputacion, totalProfesoresContratadosNoProfesionalComputacion, totalProfesoresContratadosNoProfesionalEstudiandoComputacion), new ProfesorContrato(totalProfesoresContratadosMilitar, totalProfesoresContratadosNoProfesionalMilitar, totalProfesoresContratadosNoProfesionalEstudiandoMilitar), new ProfesorContrato(totalProfesoresContratadosPractica, totalProfesoresContratadosNoProfesionalPractica, totalProfesoresContratadosNoProfesionalEstudiandoPractica), new ProfesorContrato(totalProfesoresContratadosBasica, totalProfesoresContratadosNoProfesionalBasica, totalProfesoresContratadosNoProfesionalEstudiandoBasica), new ProfesorContrato(totalProfesoresContratadosEjercicio, totalProfesoresContratadosNoProfesionalEjercicio, totalProfesoresContratadosNoProfesionalEstudiandoEjercicio), new ProfesorContrato(totalProfesoresContratadosInstructor, totalProfesoresContratadosNoProfesionalInstructor, totalProfesoresContratadosNoProfesionalEstudiandoInstructor));

        profN = new ComposicionPersonal(pf, pc);
        return profN;
    }

    private ComposicionPersonal profesoresProv(long idProvUsuarioAutenticado) {

        int totalProfesoresFijosEspanol = resumen.totalProfesoresFijosEspanolProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEspanol = resumen.totalProfesoresFijosNoTituladoEspanolProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEspanol = resumen.totalProfesoresFijosNoTituladoEstudiandoEspanolProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosLiteratura = resumen.totalProfesoresFijosLiteraturaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoLiteratura = resumen.totalProfesoresFijosNoTituladoLiteraturaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoLiteratura = resumen.totalProfesoresFijosNoTituladoEstudiandoLiteraturaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosMatematica = resumen.totalProfesoresFijosMatematicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoMatematica = resumen.totalProfesoresFijosNoTituladoMatematicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoMatematica = resumen.totalProfesoresFijosNoTituladoEstudiandoMatematicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosFisica = resumen.totalProfesoresFijosFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoFisica = resumen.totalProfesoresFijosNoTituladoFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoFisica = resumen.totalProfesoresFijosNoTituladoEstudiandoFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosQuimica = resumen.totalProfesoresFijosQuimicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoQuimica = resumen.totalProfesoresFijosNoTituladoQuimicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoQuimica = resumen.totalProfesoresFijosNoTituladoEstudiandoQuimicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosBiologia = resumen.totalProfesoresFijosBiologiaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoBiologia = resumen.totalProfesoresFijosNoTituladoBiologiaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoBiologia = resumen.totalProfesoresFijosNoTituladoEstudiandoBiologiaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosHistoria = resumen.totalProfesoresFijosHistoriaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoHistoria = resumen.totalProfesoresFijosNoTituladoHistoriaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoHistoria = resumen.totalProfesoresFijosNoTituladoEstudiandoHistoriaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosIngles = resumen.totalProfesoresFijosInglesProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoIngles = resumen.totalProfesoresFijosNoTituladoInglesProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoIngles = resumen.totalProfesoresFijosNoTituladoEstudiandoInglesProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosEducFisica = resumen.totalProfesoresFijosEducFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEducFisica = resumen.totalProfesoresFijosNoTituladoEducFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEducFisica = resumen.totalProfesoresFijosNoTituladoEstudiandoEducFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosEducArtistica = resumen.totalProfesoresFijosEducArtisticaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEducArtistica = resumen.totalProfesoresFijosNoTituladoEducArtisticaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEducArtistica = resumen.totalProfesoresFijosNoTituladoEstudiandoEducArtisticaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosMarxismo = resumen.totalProfesoresFijosMarxismoProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoMarxismo = resumen.totalProfesoresFijosNoTituladoMarxismoProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoMarxismo = resumen.totalProfesoresFijosNoTituladoEstudiandoMarxismoProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosFundamento = resumen.totalProfesoresFijosFundamentoProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoFundamento = resumen.totalProfesoresFijosNoTituladoFundamentoProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoFundamento = resumen.totalProfesoresFijosNoTituladoEstudiandoFundamentoProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosComputacion = resumen.totalProfesoresFijosComputacionProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoComputacion = resumen.totalProfesoresFijosNoTituladoComputacionProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoComputacion = resumen.totalProfesoresFijosNoTituladoEstudiandoComputacionProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosMilitar = resumen.totalProfesoresFijosMilitarProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoMilitar = resumen.totalProfesoresFijosNoTituladoMilitarProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoMilitar = resumen.totalProfesoresFijosNoTituladoEstudiandoMilitarProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosPractica = resumen.totalProfesoresFijosPracticaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoPractica = resumen.totalProfesoresFijosNoTituladoPracticaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoPractica = resumen.totalProfesoresFijosNoTituladoEstudiandoPracticaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosBasicas = resumen.totalProfesoresFijosBasicasProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoBasica = resumen.totalProfesoresFijosNoTituladoBasicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoBasica = resumen.totalProfesoresFijosNoTituladoEstudiandoBasicaProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosEjercicio = resumen.totalProfesoresFijosEjercicioProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEjercicio = resumen.totalProfesoresFijosNoTituladoEjercicioProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEjercicio = resumen.totalProfesoresFijosNoTituladoEstudiandoEjercicioProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosInstructor = resumen.totalProfesoresFijosInstructorProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoInstructor = resumen.totalProfesoresFijosNoTituladoInstructorProv(idProvUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoInstructor = resumen.totalProfesoresFijosNoTituladoEstudiandoInstructorProv(idProvUsuarioAutenticado);

        int totalProfesoresContratadosEspanol = resumen.totalProfesoresContratadosEspanolProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEspanol = resumen.totalProfesoresContratadosNoProfesionalEspanolProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEspanol = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEspanolProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosLiteratura = resumen.totalProfesoresContratadosLiteraturaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalLiteratura = resumen.totalProfesoresContratadosNoProfesionalLiteraturaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoLiteratura = resumen.totalProfesoresContratadosNoProfesionalEstudiandoLiteraturaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosMatematica = resumen.totalProfesoresContratadosMatematicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalMatematica = resumen.totalProfesoresContratadosNoProfesionalMatematicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoMatematica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMatematicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosFisica = resumen.totalProfesoresContratadosFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalFisica = resumen.totalProfesoresContratadosNoProfesionalFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoFisica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosQuimica = resumen.totalProfesoresContratadosQuimicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalQuimica = resumen.totalProfesoresContratadosNoProfesionalQuimicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoQuimica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoQuimicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosBiologia = resumen.totalProfesoresContratadosBiologiaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalBiologia = resumen.totalProfesoresContratadosNoProfesionalBiologiaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoBiologia = resumen.totalProfesoresContratadosNoProfesionalEstudiandoBiologiaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosHistoria = resumen.totalProfesoresContratadosHistoriaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalHistoria = resumen.totalProfesoresContratadosNoProfesionalHistoriaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoHistoria = resumen.totalProfesoresContratadosNoProfesionalEstudiandoHistoriaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosIngles = resumen.totalProfesoresContratadosInglesProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalIngles = resumen.totalProfesoresContratadosNoProfesionalInglesProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoIngles = resumen.totalProfesoresContratadosNoProfesionalEstudiandoInglesProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosEducFisica = resumen.totalProfesoresContratadosEducFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEducFisica = resumen.totalProfesoresContratadosNoProfesionalEducFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEducFisica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEducFisicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosEducArtistica = resumen.totalProfesoresContratadosEducArtisticaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEducArtistica = resumen.totalProfesoresContratadosNoProfesionalEducArtisticaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEducArtistica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEducArtisticaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosMarxismo = resumen.totalProfesoresContratadosMarxismoProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalMarxismo = resumen.totalProfesoresContratadosNoProfesionalMarxismoProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoMarxismo = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMarxismoProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosFundamento = resumen.totalProfesoresContratadosFundamentoProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalFundamento = resumen.totalProfesoresContratadosNoProfesionalFundamentoProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoFundamento = resumen.totalProfesoresContratadosNoProfesionalEstudiandoFundamentoProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosComputacion = resumen.totalProfesoresContratadosComputacionProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalComputacion = resumen.totalProfesoresContratadosNoProfesionalComputacionProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoComputacion = resumen.totalProfesoresContratadosNoProfesionalEstudiandoComputacionProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosMilitar = resumen.totalProfesoresContratadosMilitarProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalMilitar = resumen.totalProfesoresContratadosNoProfesionalMilitarProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoMilitar = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMilitarProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosPractica = resumen.totalProfesoresContratadosPracticaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalPractica = resumen.totalProfesoresContratadosNoProfesionalPracticaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoPractica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoPracticaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosBasica = resumen.totalProfesoresContratadosBasicasProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalBasica = resumen.totalProfesoresContratadosNoProfesionalBasicaProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoBasica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoBasicasProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosEjercicio = resumen.totalProfesoresContratadosEjercicioProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEjercicio = resumen.totalProfesoresContratadosNoProfesionalEjercicioProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEjercicio = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEjercicioProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosInstructor = resumen.totalProfesoresContratadosInstructorProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalInstructor = resumen.totalProfesoresContratadosNoProfesionalInstructorProv(idProvUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoInstructor = resumen.totalProfesoresContratadosNoProfesionalEstudiandoInstructorProv(idProvUsuarioAutenticado);

        ProfesorAsignatura pf = new ProfesorAsignatura(new ProfesorFijo(totalProfesoresFijosEspanol, totalProfesoresFijosNoTituladoEspanol, totalProfesoresFijosNoTituladoEstudiandoEspanol), new ProfesorFijo(totalProfesoresFijosLiteratura, totalProfesoresFijosNoTituladoLiteratura, totalProfesoresFijosNoTituladoEstudiandoLiteratura), new ProfesorFijo(totalProfesoresFijosMatematica, totalProfesoresFijosNoTituladoMatematica, totalProfesoresFijosNoTituladoEstudiandoMatematica), new ProfesorFijo(totalProfesoresFijosFisica, totalProfesoresFijosNoTituladoFisica, totalProfesoresFijosNoTituladoEstudiandoFisica), new ProfesorFijo(totalProfesoresFijosQuimica, totalProfesoresFijosNoTituladoQuimica, totalProfesoresFijosNoTituladoEstudiandoQuimica), new ProfesorFijo(totalProfesoresFijosBiologia, totalProfesoresFijosNoTituladoBiologia, totalProfesoresFijosNoTituladoEstudiandoBiologia), new ProfesorFijo(totalProfesoresFijosHistoria, totalProfesoresFijosNoTituladoHistoria, totalProfesoresFijosNoTituladoEstudiandoHistoria), new ProfesorFijo(totalProfesoresFijosIngles, totalProfesoresFijosNoTituladoIngles, totalProfesoresFijosNoTituladoEstudiandoIngles), new ProfesorFijo(totalProfesoresFijosEducFisica, totalProfesoresFijosNoTituladoEducFisica, totalProfesoresFijosNoTituladoEstudiandoEducFisica), new ProfesorFijo(totalProfesoresFijosEducArtistica, totalProfesoresFijosNoTituladoEducArtistica, totalProfesoresFijosNoTituladoEstudiandoEducArtistica), new ProfesorFijo(totalProfesoresFijosMarxismo, totalProfesoresFijosNoTituladoMarxismo, totalProfesoresFijosNoTituladoEstudiandoMarxismo), new ProfesorFijo(totalProfesoresFijosFundamento, totalProfesoresFijosNoTituladoFundamento, totalProfesoresFijosNoTituladoEstudiandoFundamento), new ProfesorFijo(totalProfesoresFijosComputacion, totalProfesoresFijosNoTituladoComputacion, totalProfesoresFijosNoTituladoEstudiandoComputacion), new ProfesorFijo(totalProfesoresFijosMilitar, totalProfesoresFijosNoTituladoMilitar, totalProfesoresFijosNoTituladoEstudiandoMilitar), new ProfesorFijo(totalProfesoresFijosPractica, totalProfesoresFijosNoTituladoPractica, totalProfesoresFijosNoTituladoEstudiandoPractica), new ProfesorFijo(totalProfesoresFijosBasicas, totalProfesoresFijosNoTituladoBasica, totalProfesoresFijosNoTituladoEstudiandoBasica), new ProfesorFijo(totalProfesoresFijosEjercicio, totalProfesoresFijosNoTituladoEjercicio, totalProfesoresFijosNoTituladoEstudiandoEjercicio), new ProfesorFijo(totalProfesoresFijosInstructor, totalProfesoresFijosNoTituladoInstructor, totalProfesoresFijosNoTituladoEstudiandoInstructor));
        ProfesorAsignatura pc = new ProfesorAsignatura(new ProfesorContrato(totalProfesoresContratadosEspanol, totalProfesoresContratadosNoProfesionalEspanol, totalProfesoresContratadosNoProfesionalEstudiandoEspanol), new ProfesorContrato(totalProfesoresContratadosLiteratura, totalProfesoresContratadosNoProfesionalLiteratura, totalProfesoresContratadosNoProfesionalEstudiandoLiteratura), new ProfesorContrato(totalProfesoresContratadosMatematica, totalProfesoresContratadosNoProfesionalMatematica, totalProfesoresContratadosNoProfesionalEstudiandoMatematica), new ProfesorContrato(totalProfesoresContratadosFisica, totalProfesoresContratadosNoProfesionalFisica, totalProfesoresContratadosNoProfesionalEstudiandoFisica), new ProfesorContrato(totalProfesoresContratadosQuimica, totalProfesoresContratadosNoProfesionalQuimica, totalProfesoresContratadosNoProfesionalEstudiandoQuimica), new ProfesorContrato(totalProfesoresContratadosBiologia, totalProfesoresContratadosNoProfesionalBiologia, totalProfesoresContratadosNoProfesionalEstudiandoBiologia), new ProfesorContrato(totalProfesoresContratadosHistoria, totalProfesoresContratadosNoProfesionalHistoria, totalProfesoresContratadosNoProfesionalEstudiandoHistoria), new ProfesorContrato(totalProfesoresContratadosIngles, totalProfesoresContratadosNoProfesionalIngles, totalProfesoresContratadosNoProfesionalEstudiandoIngles), new ProfesorContrato(totalProfesoresContratadosEducFisica, totalProfesoresContratadosNoProfesionalEducFisica, totalProfesoresContratadosNoProfesionalEstudiandoEducFisica), new ProfesorContrato(totalProfesoresContratadosEducArtistica, totalProfesoresContratadosNoProfesionalEducArtistica, totalProfesoresContratadosNoProfesionalEstudiandoEducArtistica), new ProfesorContrato(totalProfesoresContratadosMarxismo, totalProfesoresContratadosNoProfesionalMarxismo, totalProfesoresContratadosNoProfesionalEstudiandoMarxismo), new ProfesorContrato(totalProfesoresContratadosFundamento, totalProfesoresContratadosNoProfesionalFundamento, totalProfesoresContratadosNoProfesionalEstudiandoFundamento), new ProfesorContrato(totalProfesoresContratadosComputacion, totalProfesoresContratadosNoProfesionalComputacion, totalProfesoresContratadosNoProfesionalEstudiandoComputacion), new ProfesorContrato(totalProfesoresContratadosMilitar, totalProfesoresContratadosNoProfesionalMilitar, totalProfesoresContratadosNoProfesionalEstudiandoMilitar), new ProfesorContrato(totalProfesoresContratadosPractica, totalProfesoresContratadosNoProfesionalPractica, totalProfesoresContratadosNoProfesionalEstudiandoPractica), new ProfesorContrato(totalProfesoresContratadosBasica, totalProfesoresContratadosNoProfesionalBasica, totalProfesoresContratadosNoProfesionalEstudiandoBasica), new ProfesorContrato(totalProfesoresContratadosEjercicio, totalProfesoresContratadosNoProfesionalEjercicio, totalProfesoresContratadosNoProfesionalEstudiandoEjercicio), new ProfesorContrato(totalProfesoresContratadosInstructor, totalProfesoresContratadosNoProfesionalInstructor, totalProfesoresContratadosNoProfesionalEstudiandoInstructor));

        profP = new ComposicionPersonal(pf, pc);

        return profP;
    }

    private ComposicionPersonal profesoresMcpio(long idMcpioUsuarioAutenticado) {

        int totalProfesoresFijosEspanol = resumen.totalProfesoresFijosEspanolMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEspanol = resumen.totalProfesoresFijosNoTituladoEspanolMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEspanol = resumen.totalProfesoresFijosNoTituladoEstudiandoEspanolMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosLiteratura = resumen.totalProfesoresFijosLiteraturaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoLiteratura = resumen.totalProfesoresFijosNoTituladoLiteraturaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoLiteratura = resumen.totalProfesoresFijosNoTituladoEstudiandoLiteraturaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosMatematica = resumen.totalProfesoresFijosMatematicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoMatematica = resumen.totalProfesoresFijosNoTituladoMatematicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoMatematica = resumen.totalProfesoresFijosNoTituladoEstudiandoMatematicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosFisica = resumen.totalProfesoresFijosFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoFisica = resumen.totalProfesoresFijosNoTituladoFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoFisica = resumen.totalProfesoresFijosNoTituladoEstudiandoFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosQuimica = resumen.totalProfesoresFijosQuimicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoQuimica = resumen.totalProfesoresFijosNoTituladoQuimicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoQuimica = resumen.totalProfesoresFijosNoTituladoEstudiandoQuimicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosBiologia = resumen.totalProfesoresFijosBiologiaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoBiologia = resumen.totalProfesoresFijosNoTituladoBiologiaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoBiologia = resumen.totalProfesoresFijosNoTituladoEstudiandoBiologiaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosHistoria = resumen.totalProfesoresFijosHistoriaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoHistoria = resumen.totalProfesoresFijosNoTituladoHistoriaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoHistoria = resumen.totalProfesoresFijosNoTituladoEstudiandoHistoriaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosIngles = resumen.totalProfesoresFijosInglesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoIngles = resumen.totalProfesoresFijosNoTituladoInglesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoIngles = resumen.totalProfesoresFijosNoTituladoEstudiandoInglesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosEducFisica = resumen.totalProfesoresFijosEducFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEducFisica = resumen.totalProfesoresFijosNoTituladoEducFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEducFisica = resumen.totalProfesoresFijosNoTituladoEstudiandoEducFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosEducArtistica = resumen.totalProfesoresFijosEducArtisticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEducArtistica = resumen.totalProfesoresFijosNoTituladoEducArtisticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEducArtistica = resumen.totalProfesoresFijosNoTituladoEstudiandoEducArtisticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosMarxismo = resumen.totalProfesoresFijosMarxismoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoMarxismo = resumen.totalProfesoresFijosNoTituladoMarxismoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoMarxismo = resumen.totalProfesoresFijosNoTituladoEstudiandoMarxismoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosFundamento = resumen.totalProfesoresFijosFundamentoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoFundamento = resumen.totalProfesoresFijosNoTituladoFundamentoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoFundamento = resumen.totalProfesoresFijosNoTituladoEstudiandoFundamentoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosComputacion = resumen.totalProfesoresFijosComputacionMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoComputacion = resumen.totalProfesoresFijosNoTituladoComputacionMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoComputacion = resumen.totalProfesoresFijosNoTituladoEstudiandoComputacionMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosMilitar = resumen.totalProfesoresFijosMilitarMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoMilitar = resumen.totalProfesoresFijosNoTituladoMilitarMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoMilitar = resumen.totalProfesoresFijosNoTituladoEstudiandoMilitarMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosPractica = resumen.totalProfesoresFijosPracticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoPractica = resumen.totalProfesoresFijosNoTituladoPracticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoPractica = resumen.totalProfesoresFijosNoTituladoEstudiandoPracticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosBasicas = resumen.totalProfesoresFijosBasicasMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoBasica = resumen.totalProfesoresFijosNoTituladoBasicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoBasica = resumen.totalProfesoresFijosNoTituladoEstudiandoBasicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosEjercicio = resumen.totalProfesoresFijosEjercicioMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEjercicio = resumen.totalProfesoresFijosNoTituladoEjercicioMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoEjercicio = resumen.totalProfesoresFijosNoTituladoEstudiandoEjercicioMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosInstructor = resumen.totalProfesoresFijosInstructorMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoInstructor = resumen.totalProfesoresFijosNoTituladoInstructorMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresFijosNoTituladoEstudiandoInstructor = resumen.totalProfesoresFijosNoTituladoEstudiandoInstructorMcpio(idMcpioUsuarioAutenticado);

        int totalProfesoresContratadosEspanol = resumen.totalProfesoresContratadosEspanolMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEspanol = resumen.totalProfesoresContratadosNoProfesionalEspanolMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEspanol = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEspanolMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosLiteratura = resumen.totalProfesoresContratadosLiteraturaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalLiteratura = resumen.totalProfesoresContratadosNoProfesionalLiteraturaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoLiteratura = resumen.totalProfesoresContratadosNoProfesionalEstudiandoLiteraturaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosMatematica = resumen.totalProfesoresContratadosMatematicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalMatematica = resumen.totalProfesoresContratadosNoProfesionalMatematicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoMatematica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMatematicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosFisica = resumen.totalProfesoresContratadosFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalFisica = resumen.totalProfesoresContratadosNoProfesionalFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoFisica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosQuimica = resumen.totalProfesoresContratadosQuimicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalQuimica = resumen.totalProfesoresContratadosNoProfesionalQuimicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoQuimica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoQuimicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosBiologia = resumen.totalProfesoresContratadosBiologiaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalBiologia = resumen.totalProfesoresContratadosNoProfesionalBiologiaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoBiologia = resumen.totalProfesoresContratadosNoProfesionalEstudiandoBiologiaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosHistoria = resumen.totalProfesoresContratadosHistoriaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalHistoria = resumen.totalProfesoresContratadosNoProfesionalHistoriaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoHistoria = resumen.totalProfesoresContratadosNoProfesionalEstudiandoHistoriaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosIngles = resumen.totalProfesoresContratadosInglesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalIngles = resumen.totalProfesoresContratadosNoProfesionalInglesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoIngles = resumen.totalProfesoresContratadosNoProfesionalEstudiandoInglesMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosEducFisica = resumen.totalProfesoresContratadosEducFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEducFisica = resumen.totalProfesoresContratadosNoProfesionalEducFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEducFisica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEducFisicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosEducArtistica = resumen.totalProfesoresContratadosEducArtisticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEducArtistica = resumen.totalProfesoresContratadosNoProfesionalEducArtisticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEducArtistica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEducArtisticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosMarxismo = resumen.totalProfesoresContratadosMarxismoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalMarxismo = resumen.totalProfesoresContratadosNoProfesionalMarxismoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoMarxismo = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMarxismoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosFundamento = resumen.totalProfesoresContratadosFundamentoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalFundamento = resumen.totalProfesoresContratadosNoProfesionalFundamentoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoFundamento = resumen.totalProfesoresContratadosNoProfesionalEstudiandoFundamentoMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosComputacion = resumen.totalProfesoresContratadosComputacionMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalComputacion = resumen.totalProfesoresContratadosNoProfesionalComputacionMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoComputacion = resumen.totalProfesoresContratadosNoProfesionalEstudiandoComputacionMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosMilitar = resumen.totalProfesoresContratadosMilitarMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalMilitar = resumen.totalProfesoresContratadosNoProfesionalMilitarMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoMilitar = resumen.totalProfesoresContratadosNoProfesionalEstudiandoMilitarMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosPractica = resumen.totalProfesoresContratadosPracticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalPractica = resumen.totalProfesoresContratadosNoProfesionalPracticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoPractica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoPracticaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosBasica = resumen.totalProfesoresContratadosBasicasMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalBasica = resumen.totalProfesoresContratadosNoProfesionalBasicaMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoBasica = resumen.totalProfesoresContratadosNoProfesionalEstudiandoBasicasMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosEjercicio = resumen.totalProfesoresContratadosEjercicioMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEjercicio = resumen.totalProfesoresContratadosNoProfesionalEjercicioMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoEjercicio = resumen.totalProfesoresContratadosNoProfesionalEstudiandoEjercicioMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosInstructor = resumen.totalProfesoresContratadosInstructorMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalInstructor = resumen.totalProfesoresContratadosNoProfesionalInstructorMcpio(idMcpioUsuarioAutenticado);
        int totalProfesoresContratadosNoProfesionalEstudiandoInstructor = resumen.totalProfesoresContratadosNoProfesionalEstudiandoInstructorMcpio(idMcpioUsuarioAutenticado);

        ProfesorAsignatura pf = new ProfesorAsignatura(new ProfesorFijo(totalProfesoresFijosEspanol, totalProfesoresFijosNoTituladoEspanol, totalProfesoresFijosNoTituladoEstudiandoEspanol), new ProfesorFijo(totalProfesoresFijosLiteratura, totalProfesoresFijosNoTituladoLiteratura, totalProfesoresFijosNoTituladoEstudiandoLiteratura), new ProfesorFijo(totalProfesoresFijosMatematica, totalProfesoresFijosNoTituladoMatematica, totalProfesoresFijosNoTituladoEstudiandoMatematica), new ProfesorFijo(totalProfesoresFijosFisica, totalProfesoresFijosNoTituladoFisica, totalProfesoresFijosNoTituladoEstudiandoFisica), new ProfesorFijo(totalProfesoresFijosQuimica, totalProfesoresFijosNoTituladoQuimica, totalProfesoresFijosNoTituladoEstudiandoQuimica), new ProfesorFijo(totalProfesoresFijosBiologia, totalProfesoresFijosNoTituladoBiologia, totalProfesoresFijosNoTituladoEstudiandoBiologia), new ProfesorFijo(totalProfesoresFijosHistoria, totalProfesoresFijosNoTituladoHistoria, totalProfesoresFijosNoTituladoEstudiandoHistoria), new ProfesorFijo(totalProfesoresFijosIngles, totalProfesoresFijosNoTituladoIngles, totalProfesoresFijosNoTituladoEstudiandoIngles), new ProfesorFijo(totalProfesoresFijosEducFisica, totalProfesoresFijosNoTituladoEducFisica, totalProfesoresFijosNoTituladoEstudiandoEducFisica), new ProfesorFijo(totalProfesoresFijosEducArtistica, totalProfesoresFijosNoTituladoEducArtistica, totalProfesoresFijosNoTituladoEstudiandoEducArtistica), new ProfesorFijo(totalProfesoresFijosMarxismo, totalProfesoresFijosNoTituladoMarxismo, totalProfesoresFijosNoTituladoEstudiandoMarxismo), new ProfesorFijo(totalProfesoresFijosFundamento, totalProfesoresFijosNoTituladoFundamento, totalProfesoresFijosNoTituladoEstudiandoFundamento), new ProfesorFijo(totalProfesoresFijosComputacion, totalProfesoresFijosNoTituladoComputacion, totalProfesoresFijosNoTituladoEstudiandoComputacion), new ProfesorFijo(totalProfesoresFijosMilitar, totalProfesoresFijosNoTituladoMilitar, totalProfesoresFijosNoTituladoEstudiandoMilitar), new ProfesorFijo(totalProfesoresFijosPractica, totalProfesoresFijosNoTituladoPractica, totalProfesoresFijosNoTituladoEstudiandoPractica), new ProfesorFijo(totalProfesoresFijosBasicas, totalProfesoresFijosNoTituladoBasica, totalProfesoresFijosNoTituladoEstudiandoBasica), new ProfesorFijo(totalProfesoresFijosEjercicio, totalProfesoresFijosNoTituladoEjercicio, totalProfesoresFijosNoTituladoEstudiandoEjercicio), new ProfesorFijo(totalProfesoresFijosInstructor, totalProfesoresFijosNoTituladoInstructor, totalProfesoresFijosNoTituladoEstudiandoInstructor));
        ProfesorAsignatura pc = new ProfesorAsignatura(new ProfesorContrato(totalProfesoresContratadosEspanol, totalProfesoresContratadosNoProfesionalEspanol, totalProfesoresContratadosNoProfesionalEstudiandoEspanol), new ProfesorContrato(totalProfesoresContratadosLiteratura, totalProfesoresContratadosNoProfesionalLiteratura, totalProfesoresContratadosNoProfesionalEstudiandoLiteratura), new ProfesorContrato(totalProfesoresContratadosMatematica, totalProfesoresContratadosNoProfesionalMatematica, totalProfesoresContratadosNoProfesionalEstudiandoMatematica), new ProfesorContrato(totalProfesoresContratadosFisica, totalProfesoresContratadosNoProfesionalFisica, totalProfesoresContratadosNoProfesionalEstudiandoFisica), new ProfesorContrato(totalProfesoresContratadosQuimica, totalProfesoresContratadosNoProfesionalQuimica, totalProfesoresContratadosNoProfesionalEstudiandoQuimica), new ProfesorContrato(totalProfesoresContratadosBiologia, totalProfesoresContratadosNoProfesionalBiologia, totalProfesoresContratadosNoProfesionalEstudiandoBiologia), new ProfesorContrato(totalProfesoresContratadosHistoria, totalProfesoresContratadosNoProfesionalHistoria, totalProfesoresContratadosNoProfesionalEstudiandoHistoria), new ProfesorContrato(totalProfesoresContratadosIngles, totalProfesoresContratadosNoProfesionalIngles, totalProfesoresContratadosNoProfesionalEstudiandoIngles), new ProfesorContrato(totalProfesoresContratadosEducFisica, totalProfesoresContratadosNoProfesionalEducFisica, totalProfesoresContratadosNoProfesionalEstudiandoEducFisica), new ProfesorContrato(totalProfesoresContratadosEducArtistica, totalProfesoresContratadosNoProfesionalEducArtistica, totalProfesoresContratadosNoProfesionalEstudiandoEducArtistica), new ProfesorContrato(totalProfesoresContratadosMarxismo, totalProfesoresContratadosNoProfesionalMarxismo, totalProfesoresContratadosNoProfesionalEstudiandoMarxismo), new ProfesorContrato(totalProfesoresContratadosFundamento, totalProfesoresContratadosNoProfesionalFundamento, totalProfesoresContratadosNoProfesionalEstudiandoFundamento), new ProfesorContrato(totalProfesoresContratadosComputacion, totalProfesoresContratadosNoProfesionalComputacion, totalProfesoresContratadosNoProfesionalEstudiandoComputacion), new ProfesorContrato(totalProfesoresContratadosMilitar, totalProfesoresContratadosNoProfesionalMilitar, totalProfesoresContratadosNoProfesionalEstudiandoMilitar), new ProfesorContrato(totalProfesoresContratadosPractica, totalProfesoresContratadosNoProfesionalPractica, totalProfesoresContratadosNoProfesionalEstudiandoPractica), new ProfesorContrato(totalProfesoresContratadosBasica, totalProfesoresContratadosNoProfesionalBasica, totalProfesoresContratadosNoProfesionalEstudiandoBasica), new ProfesorContrato(totalProfesoresContratadosEjercicio, totalProfesoresContratadosNoProfesionalEjercicio, totalProfesoresContratadosNoProfesionalEstudiandoEjercicio), new ProfesorContrato(totalProfesoresContratadosInstructor, totalProfesoresContratadosNoProfesionalInstructor, totalProfesoresContratadosNoProfesionalEstudiandoInstructor));

        profM = new ComposicionPersonal(pf, pc);
        return profM;
    }

    private RangoEdad rangoEdadesMcpio(long idMcpioUsuarioAutenticado) {

        int cursoD = 1, cursoCPT = 2;
        int totalMenos15 = resumen.menos15TotalesMcpio(idMcpioUsuarioAutenticado, cursoD);
        int menos15Hembras = resumen.menos15HembrasTotalesMcpio(idMcpioUsuarioAutenticado, cursoD);
        int totalEntre15_16 = resumen.entre15Y16TotalesMcpio(idMcpioUsuarioAutenticado, cursoD);
        int entre15_16Hembras = resumen.entre15Y16HembrasTotalesMcpio(idMcpioUsuarioAutenticado, cursoD);
        int totalMas16 = resumen.mas16TotalesMcpio(idMcpioUsuarioAutenticado, cursoD);
        int mas16Hembras = resumen.mas16HembrasTotalesMcpio(idMcpioUsuarioAutenticado, cursoD);

        int totalMenos15CPT = resumen.menos15TotalesMcpio(idMcpioUsuarioAutenticado, cursoCPT);
        int menos15HembrasCPT = resumen.menos15HembrasTotalesMcpio(idMcpioUsuarioAutenticado, cursoCPT);
        int totalEntre15_16CPT = resumen.entre15Y16TotalesMcpio(idMcpioUsuarioAutenticado, cursoCPT);
        int entre15_16HembrasCPT = resumen.entre15Y16HembrasTotalesMcpio(idMcpioUsuarioAutenticado, cursoCPT);
        int totalMas16CPT = resumen.mas16TotalesMcpio(idMcpioUsuarioAutenticado, cursoCPT);
        int mas16HembrasCPT = resumen.mas16HembrasTotalesMcpio(idMcpioUsuarioAutenticado, cursoCPT);

        Rango diurno = new Rango(totalMenos15, menos15Hembras, totalEntre15_16, entre15_16Hembras, totalMas16, mas16Hembras, cursos.obtenerId(cursoD));
        Rango cpt = new Rango(totalMenos15CPT, menos15HembrasCPT, totalEntre15_16CPT, entre15_16HembrasCPT, totalMas16CPT, mas16HembrasCPT, cursos.obtenerId(cursoCPT));

        rangoM = new RangoEdad(diurno, cpt);
        return rangoM;
    }

    private RangoEdad rangosEdadesProv(long idProvUsuarioAutenticado) {

        int cursoD = 1, cursoCPT = 2;
        int totalMenos15 = resumen.menos15TotalesProv(idProvUsuarioAutenticado, cursoD);
        int menos15Hembras = resumen.menos15HembrasTotalesProv(idProvUsuarioAutenticado, cursoD);
        int totalEntre15_16 = resumen.entre15Y16TotalesProv(idProvUsuarioAutenticado, cursoD);
        int entre15_16Hembras = resumen.entre15Y16HembrasTotalesProv(idProvUsuarioAutenticado, cursoD);
        int totalMas16 = resumen.mas16TotalesProv(idProvUsuarioAutenticado, cursoD);
        int mas16Hembras = resumen.mas16HembrasTotalesProv(idProvUsuarioAutenticado, cursoD);

        int totalMenos15CPT = resumen.menos15TotalesProv(idProvUsuarioAutenticado, cursoCPT);
        int menos15HembrasCPT = resumen.menos15HembrasTotalesProv(idProvUsuarioAutenticado, cursoCPT);
        int totalEntre15_16CPT = resumen.entre15Y16TotalesProv(idProvUsuarioAutenticado, cursoCPT);
        int entre15_16HembrasCPT = resumen.entre15Y16HembrasTotalesProv(idProvUsuarioAutenticado, cursoCPT);
        int totalMas16CPT = resumen.mas16TotalesProv(idProvUsuarioAutenticado, cursoCPT);
        int mas16HembrasCPT = resumen.mas16HembrasTotalesProv(idProvUsuarioAutenticado, cursoCPT);

        Rango diurno = new Rango(totalMenos15, menos15Hembras, totalEntre15_16, entre15_16Hembras, totalMas16, mas16Hembras, cursos.obtenerId(cursoD));
        Rango cpt = new Rango(totalMenos15CPT, menos15HembrasCPT, totalEntre15_16CPT, entre15_16HembrasCPT, totalMas16CPT, mas16HembrasCPT, cursos.obtenerId(cursoCPT));

        rangoP = new RangoEdad(diurno, cpt);
        return rangoP;
    }

    private RangoEdad rangosEdadesNac() {
        int cursoD = 1, cursoCPT = 2;
        int totalMenos15 = resumen.menos15TotalesNac(cursoD);
        int menos15Hembras = resumen.menos15HembrasTotalesNac(cursoD);
        int totalEntre15_16 = resumen.entre15Y16TotalesNac(cursoD);
        int entre15_16Hembras = resumen.entre15Y16HembrasTotalesNac(cursoD);
        int totalMas16 = resumen.mas16TotalesNac(cursoD);
        int mas16Hembras = resumen.mas16HembrasTotalesNac(cursoD);

        int totalMenos15CPT = resumen.menos15TotalesNac(cursoCPT);
        int menos15HembrasCPT = resumen.menos15HembrasTotalesNac(cursoCPT);
        int totalEntre15_16CPT = resumen.entre15Y16TotalesNac(cursoCPT);
        int entre15_16HembrasCPT = resumen.entre15Y16HembrasTotalesNac(cursoCPT);
        int totalMas16CPT = resumen.mas16TotalesNac(cursoCPT);
        int mas16HembrasCPT = resumen.mas16HembrasTotalesNac(cursoCPT);

        Rango diurno = new Rango(totalMenos15, menos15Hembras, totalEntre15_16, entre15_16Hembras, totalMas16, mas16Hembras, cursos.obtenerId(cursoD));
        Rango cpt = new Rango(totalMenos15CPT, menos15HembrasCPT, totalEntre15_16CPT, entre15_16HembrasCPT, totalMas16CPT, mas16HembrasCPT, cursos.obtenerId(cursoCPT));

        rangoN = new RangoEdad(diurno, cpt);
        return rangoN;
    }

    private List<Ficha> fichaMcpio(long idM) {
        int idCDiurno = 1, idCursoCPT = 2;

        int matricula = resumen.matriculasTotalesMcpio(idM, idCDiurno);
        int matriculaHembras = resumen.matriculasTotalesHembrasMcpio(idM, idCDiurno);
        int matriculaCPT = resumen.matriculasTotalesMcpio(idM, idCursoCPT);
        int matriculaHembrasCPT = resumen.matriculasTotalesHembrasMcpio(idM, idCursoCPT);

        int interno = resumen.internosTotalesMcpio(idM);
        int seminternos = resumen.seminternosTotalesMcpio(idM);
        int nuevosIngresos = resumen.nuevosIngresosTotalesMcpio(idM, idCDiurno);
        int nuevosIngresosCPT = resumen.nuevosIngresosTotalesMcpio(idM, idCursoCPT);

        int frenteAula = resumen.profesoresDocentesTotalesMcpio(idM);
        int bibliotecarios = resumen.bibliotecariosTotalesMcpio(idM);
        int bibliotecariosH = resumen.bibliotecariosHembrasTotalesMcpio(idM);
        int instructores = resumen.instructoresArteTotalesMcpio(idM);
        int instructoresH = resumen.instructoresArteHembrasTotalesMcpio(idM);
        int psicopedagogos = resumen.psicopedagogosTotalesMcpio(idM);
        int psicopedagogosH = resumen.psicopedagogosHembrasTotalesMcpio(idM);
        int alumnosMaestros = resumen.alumnosMaestrosTotalesMcpio(idM);
        int totalDocentes = frenteAula + bibliotecarios + instructores + psicopedagogos + alumnosMaestros;
        int totalDocentesHembras = resumen.profesoresDocentesHembrasTotalesMcpio(idM) + bibliotecariosH + instructoresH + psicopedagogosH;

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }
        lst_fichaM = new ArrayList<>();

        lst_fichaM.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_fichaM.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesMcpioRama(idM, ram, idCDiurno);
            matH = especialidadesActuales.matriculasActualesHembrasMcpioRama(idM, ram, idCDiurno);
            nIngr = especialidadesActuales.nuevosIngresosTotalMcpioRama(idM, ram, idCDiurno);

            lst_fichaM.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_fichaM.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_fichaM;
    }

    private List<Ficha> fichaProv(long idP) {

        int idCDiurno = 1, idCursoCPT = 2;

        int matricula = resumen.matriculasTotalesProv(idP, idCDiurno);
        int matriculaHembras = resumen.matriculasTotalesHembrasProv(idP, idCDiurno);
        int matriculaCPT = resumen.matriculasTotalesProv(idP, idCursoCPT);
        int matriculaHembrasCPT = resumen.matriculasTotalesHembrasProv(idP, idCursoCPT);

        int interno = resumen.internosTotalesProv(idP);
        int seminternos = resumen.seminternosTotalesProv(idP);
        int nuevosIngresos = resumen.nuevosIngresosTotalesProv(idP, idCDiurno);
        int nuevosIngresosCPT = resumen.nuevosIngresosTotalesProv(idP, idCursoCPT);

        int frenteAula = resumen.profesoresDocentesTotalesProv(idP);
        int bibliotecarios = resumen.bibliotecariosTotalesProv(idP);
        int bibliotecariosH = resumen.bibliotecariosHembrasTotalesProv(idP);
        int instructores = resumen.instructoresArteTotalesProv(idP);
        int instructoresH = resumen.instructoresArteHembrasTotalesProv(idP);
        int psicopedagogos = resumen.psicopedagogosTotalesProv(idP);
        int psicopedagogosH = resumen.psicopedagogosHembrasTotalesProv(idP);
        int alumnosMaestros = resumen.alumnosMaestrosTotalesProv(idP);
        int totalDocentes = frenteAula + bibliotecarios + instructores + psicopedagogos + alumnosMaestros;
        int totalDocentesHembras = resumen.profesoresDocentesHembrasTotalesProv(idP) + bibliotecariosH + instructoresH + psicopedagogosH;

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }
        lst_fichaP = new ArrayList<>();

        lst_fichaP.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_fichaP.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesProvRama(idP, ram, idCDiurno);
            matH = especialidadesActuales.matriculasActualesHembrasProvRama(idP, ram, idCDiurno);
            nIngr = especialidadesActuales.nuevosIngresosTotalProvRama(idP, ram, idCDiurno);

            lst_fichaP.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_fichaP.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_fichaP;
    }

    private List<Ficha> fichaNac() {
        int idCDiurno = 1, idCursoCPT = 2;

        int matricula = resumen.matriculasTotalesNac(idCDiurno);
        int matriculaHembras = resumen.matriculasTotalesHembrasNac(idCDiurno);
        int matriculaCPT = resumen.matriculasTotalesNac(idCursoCPT);
        int matriculaHembrasCPT = resumen.matriculasTotalesHembrasNac(idCursoCPT);

        int interno = resumen.internosTotalesNac();
        int seminternos = resumen.seminternosTotalesNac();
        int nuevosIngresos = resumen.nuevosIngresosTotalesNac(idCDiurno);
        int nuevosIngresosCPT = resumen.nuevosIngresosTotalesNac(idCursoCPT);

        int frenteAula = resumen.profesoresDocentesTotalesNac();
        int bibliotecarios = resumen.bibliotecariosTotalesNac();
        int bibliotecariosH = resumen.bibliotecariosHembrasTotalesNac();
        int instructores = resumen.instructoresArteTotalesNac();
        int instructoresH = resumen.instructoresArteHembrasTotalesNac();
        int psicopedagogos = resumen.psicopedagogosTotalesNac();
        int psicopedagogosH = resumen.psicopedagogosHembrasTotalesNac();
        int alumnosMaestros = resumen.alumnosMaestrosTotalesNac();
        int totalDocentes = frenteAula + bibliotecarios + instructores + psicopedagogos + alumnosMaestros;
        int totalDocentesHembras = resumen.profesoresDocentesHembrasTotalesNac() + bibliotecariosH + instructoresH + psicopedagogosH;

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }

        lst_fichaN = new ArrayList<>();

        lst_fichaN.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_fichaN.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesNacRama(ram, idCDiurno);
            matH = especialidadesActuales.matriculasActualesHembrasNacRama(ram, idCDiurno);
            nIngr = especialidadesActuales.nuevosIngresosTotalNacRama(ram, idCDiurno);

            lst_fichaN.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_fichaN.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_fichaN;
    }

    private List<Ficha> fichaTurquinoMcpio(long idM) {
        int idCDiurno = 1, idCursoCPT = 2;

        int matricula = resumen.matriculasTotalesTurquinoMcpio(idM, idCDiurno);
        int matriculaHembras = resumen.matriculasTotalesHembrasTurquinoMcpio(idM, idCDiurno);
        int matriculaCPT = resumen.matriculasTotalesTurquinoMcpio(idM, idCursoCPT);
        int matriculaHembrasCPT = resumen.matriculasTotalesHembrasTurquinoMcpio(idM, idCursoCPT);

        int interno = resumen.internosTotalesTurquinoMcpio(idM);
        int seminternos = resumen.seminternosTotalesTurquinoMcpio(idM);
        int nuevosIngresos = resumen.nuevosIngresosTotalesTurquinoMcpio(idM, idCDiurno);
        int nuevosIngresosCPT = resumen.nuevosIngresosTotalesTurquinoMcpio(idM, idCursoCPT);

        int frenteAula = resumen.profesoresDocentesTotalesTurquinoMcpio(idM);
        int bibliotecarios = resumen.bibliotecariosTotalesTurquinoMcpio(idM);
        int bibliotecariosH = resumen.bibliotecariosHembrasTotalesMcpio(idM);
        int instructores = resumen.instructoresArteTotalesTurquinoMcpio(idM);
        int instructoresH = resumen.instructoresArteHembrasTotalesTurquinoMcpio(idM);
        int psicopedagogos = resumen.psicopedagogosTotalesTurquinoMcpio(idM);
        int psicopedagogosH = resumen.psicopedagogosHembrasTotalesTurquinoMcpio(idM);
        int alumnosMaestros = resumen.alumnosMaestrosTotalesTurquinoMcpio(idM);
        int totalDocentes = frenteAula + bibliotecarios + instructores + psicopedagogos + alumnosMaestros;
        int totalDocentesHembras = resumen.profesoresDocentesHembrasTotalesTurquinoMcpio(idM) + bibliotecariosH + instructoresH + psicopedagogosH;

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }

        lst_fichaMT = new ArrayList<>();

        lst_fichaMT.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_fichaMT.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesMcpioTurquinoRama(idM, ram, idCDiurno);
            matH = especialidadesActuales.matriculasActualesHembrasMcpioTurquinoRama(idM, ram, idCDiurno);
            nIngr = especialidadesActuales.nuevosIngresosTotalMcpioTurquinoRama(idM, ram, idCDiurno);

            lst_fichaMT.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_fichaMT.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_fichaMT;
    }

    private List<Ficha> fichaTurquinoProv(long idP) {
        int idCDiurno = 1, idCursoCPT = 2;

        int matricula = resumen.matriculasTotalesTurquinoProv(idP, idCDiurno);
        int matriculaHembras = resumen.matriculasTotalesHembrasTurquinoProv(idP, idCDiurno);
        int matriculaCPT = resumen.matriculasTotalesTurquinoProv(idP, idCursoCPT);
        int matriculaHembrasCPT = resumen.matriculasTotalesHembrasTurquinoProv(idP, idCursoCPT);

        int interno = resumen.internosTotalesTurquinoProv(idP);
        int seminternos = resumen.seminternosTotalesTurquinoProv(idP);
        int nuevosIngresos = resumen.nuevosIngresosTotalesTurquinoProv(idP, idCDiurno);
        int nuevosIngresosCPT = resumen.nuevosIngresosTotalesTurquinoProv(idP, idCursoCPT);

        int frenteAula = resumen.profesoresDocentesTotalesTurquinoProv(idP);
        int bibliotecarios = resumen.bibliotecariosTotalesTurquinoProv(idP);
        int bibliotecariosH = resumen.bibliotecariosHembrasTotalesTurquinoProv(idP);
        int instructores = resumen.instructoresArteTotalesTurquinoProv(idP);
        int instructoresH = resumen.instructoresArteHembrasTotalesTurquinoProv(idP);
        int psicopedagogos = resumen.psicopedagogosTotalesTurquinoProv(idP);
        int psicopedagogosH = resumen.psicopedagogosHembrasTotalesTurquinoProv(idP);
        int alumnosMaestros = resumen.alumnosMaestrosTotalesTurquinoProv(idP);
        int totalDocentes = frenteAula + bibliotecarios + instructores + psicopedagogos + alumnosMaestros;
        int totalDocentesHembras = resumen.profesoresDocentesHembrasTotalesTurquinoProv(idP) + bibliotecariosH + instructoresH + psicopedagogosH;

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }

        lst_fichaPT = new ArrayList<>();

        lst_fichaPT.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_fichaPT.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesProvTurquinoRama(idP, ram, idCDiurno);
            matH = especialidadesActuales.matriculasActualesHembrasProvTurquinoRama(idP, ram, idCDiurno);
            nIngr = especialidadesActuales.nuevosIngresosTotalProvTurquinoRama(idP, ram, idCDiurno);

            lst_fichaPT.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_fichaPT.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_fichaPT;
    }

    private List<Ficha> fichaTurquinoNac() {
        int idCDiurno = 1, idCursoCPT = 2;

        int matricula = resumen.matriculasTotalesTurquinoNac(idCDiurno);
        int matriculaHembras = resumen.matriculasTotalesHembrasTurquinoNac(idCDiurno);
        int matriculaCPT = resumen.matriculasTotalesTurquinoNac(idCursoCPT);
        int matriculaHembrasCPT = resumen.matriculasTotalesHembrasTurquinoNac(idCursoCPT);

        int interno = resumen.internosTotalesTurquinoNac();
        int seminternos = resumen.seminternosTotalesTurquinoNac();
        int nuevosIngresos = resumen.nuevosIngresosTotalesTurquinoNac(idCDiurno);
        int nuevosIngresosCPT = resumen.nuevosIngresosTotalesTurquinoNac(idCursoCPT);

        int frenteAula = resumen.profesoresDocentesTotalesTurquinoNac();
        int bibliotecarios = resumen.bibliotecariosTotalesTurquinoNac();
        int bibliotecariosH = resumen.bibliotecariosHembrasTotalesTurquinoNac();
        int instructores = resumen.instructoresArteTotalesTurquinoNac();
        int instructoresH = resumen.instructoresArteHembrasTotalesTurquinoNac();
        int psicopedagogos = resumen.psicopedagogosTotalesTurquinoNac();
        int psicopedagogosH = resumen.psicopedagogosHembrasTotalesTurquinoNac();
        int alumnosMaestros = resumen.alumnosMaestrosTotalesTurquinoNac();
        int totalDocentes = frenteAula + bibliotecarios + instructores + psicopedagogos + alumnosMaestros;
        int totalDocentesHembras = resumen.profesoresDocentesHembrasTotalesTurquinoNac() + +bibliotecariosH + instructoresH + psicopedagogosH;

        float relacion = 0;
        if (frenteAula != 0) {
            if ((matricula + matriculaCPT) != 0) {
                relacion = (matricula + matriculaCPT) / frenteAula;
            }
        }

        lst_fichaNT = new ArrayList<>();

        lst_fichaNT.add(new Ficha("Centro Politécnico", matricula + matriculaCPT, matriculaHembras + matriculaHembrasCPT, interno, seminternos, nuevosIngresos + nuevosIngresosCPT, totalDocentes, totalDocentesHembras, frenteAula, relacion));
        lst_fichaNT.add(new Ficha("Curso Diurno", matricula, matriculaHembras, interno, seminternos, nuevosIngresos, 0, 0, 0, 0));

        int mat, matH, nIngr;
        String ram;
        List<Rama> lst_ramas = ramas.listar();

        for (int i = 0; i < lst_ramas.size(); i++) {
            ram = lst_ramas.get(i).getTipoRama();

            mat = especialidadesActuales.matriculasActualesNacTurquinoRama(ram, idCDiurno);
            matH = especialidadesActuales.matriculasActualesHembrasNacTurquinoRama(ram, idCDiurno);
            nIngr = especialidadesActuales.nuevosIngresosTotalNacTurquinoRama(ram, idCDiurno);

            lst_fichaNT.add(new Ficha(ram, mat, matH, 0, 0, nIngr, 0, 0, 0, 0));
        }
        lst_fichaNT.add(new Ficha("Curso V/Nocturno", matriculaCPT, matriculaHembrasCPT, 0, 0, nuevosIngresosCPT, 0, 0, 0, 0));

        return lst_fichaNT;
    }

    private List<List<EspecialidadAnterior>> resumenCursoTerminadoMcpio(long idM) {
        List<List<EspecialidadAnterior>> esp = new ArrayList<>();
        List<Familia> fam = familias.listar();

        for (int i = 0; i < fam.size(); i++) {
            List<EspecialidadAnterior> lst_esp = especialidadesAnteriores.especialidadesAnterioresFamiliaMcpio(idM, 1, fam.get(i).getTipoFamilia());
            if (!lst_esp.isEmpty()) {
                esp.add(lst_esp);
            }
        }
        return esp;
    }

    private List<List<EspecialidadAnterior>> resumenCursoTerminadoProv(long idP) {
        List<List<EspecialidadAnterior>> esp = new ArrayList<>();
        List<Familia> fam = familias.listar();

        for (int i = 0; i < fam.size(); i++) {
            List<EspecialidadAnterior> lst_esp = especialidadesAnteriores.especialidadesAnterioresFamiliaProv(idP, 1, fam.get(i).getTipoFamilia());
            if (!lst_esp.isEmpty()) {
                esp.add(lst_esp);
            }
        }
        return esp;
    }

    private List<List<EspecialidadAnterior>> resumenCursoTerminadoNac() {
        List<List<EspecialidadAnterior>> esp = new ArrayList<>();
        List<Familia> fam = familias.listar();

        for (int i = 0; i < fam.size(); i++) {
            List<EspecialidadAnterior> lst_esp = especialidadesAnteriores.especialidadesAnterioresFamiliaNac(1, fam.get(i).getTipoFamilia());
            if (!lst_esp.isEmpty()) {
                esp.add(lst_esp);
            }
        }
        return esp;
    }

    private List<List<EspecialidadActual>> resumenCursoIniciadoMcpio(long idM) {
        List<List<EspecialidadActual>> esp = new ArrayList<>();
        List<Familia> fam = familias.listar();

        for (int i = 0; i < fam.size(); i++) {
            List<EspecialidadActual> lst_esp = especialidadesActuales.especialidadesActualesFamiliaMcpio(idM, 1, fam.get(i).getTipoFamilia());
            if (!lst_esp.isEmpty()) {
                esp.add(lst_esp);
            }
        }
        return esp;
    }

    private List<List<EspecialidadActual>> resumenCursoIniciadoProv(long idP) {
        List<List<EspecialidadActual>> esp = new ArrayList<>();
        List<Familia> fam = familias.listar();

        for (int i = 0; i < fam.size(); i++) {
            List<EspecialidadActual> lst_esp = especialidadesActuales.especialidadesActualesFamiliaProv(idP, 1, fam.get(i).getTipoFamilia());
            if (!lst_esp.isEmpty()) {
                esp.add(lst_esp);
            }
        }
        return esp;
    }

    private List<List<EspecialidadActual>> resumenCursoIniciadoNac() {
        List<List<EspecialidadActual>> esp = new ArrayList<>();
        List<Familia> fam = familias.listar();

        for (int i = 0; i < fam.size(); i++) {
            List<EspecialidadActual> lst_esp = especialidadesActuales.especialidadesActualesFamiliaNac(1, fam.get(i).getTipoFamilia());
            if (!lst_esp.isEmpty()) {
                esp.add(lst_esp);
            }
        }
        return esp;
    }

    @GetMapping(path = {"/terminado_mcpal"})
    public String cursoTerminadoMcpal(Model m) {

        mcpioSelecionado = getUsuarioAutenticado().getMcpio();

        if (getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
            return "redirect:/terminado_mcpal_pdf";
        }

        m.addAttribute("municipios", lstMunicipios());
        return "resumen/resumen/terminado_mcpal";
    }

    @PostMapping(path = {"/terminado_mcpal"})
    public String cursoTerminadoMcpalPOST(HttpServletRequest request) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);

        return "redirect:/terminado_mcpal_pdf";
    }

    @GetMapping(path = {"/terminado_prov"})
    public String cursoTerminadoProv(Model m) {

        provSelecionada = getUsuarioAutenticado().getMcpio().getProv();

        m.addAttribute("provincias", lstProvincias());
        return "resumen/resumen/terminado_prov";
    }

    @PostMapping(path = {"/terminado_prov"})
    public String cursoTerminadoProvPOST(HttpServletRequest request) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);
        return "redirect:/terminado_prov_pdf";
    }

    @GetMapping(path = {"/iniciado_mcpal"})
    public String cursoIniciadoMcpal(Model m) {

        mcpioSelecionado = getUsuarioAutenticado().getMcpio();

        if (getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
            return "redirect:/iniciado_mcpal_pdf";
        }

        m.addAttribute("municipios", lstMunicipios());
        return "resumen/resumen/iniciado_mcpal";
    }

    @PostMapping(path = {"/iniciado_mcpal"})
    public String cursoIniciadoMcpalPOST(HttpServletRequest request) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);
        return "redirect:/iniciado_mcpal_pdf";
    }

    @GetMapping(path = {"/iniciado_prov"})
    public String cursoIniciadoProv(Model m) {

        provSelecionada = getUsuarioAutenticado().getMcpio().getProv();

        m.addAttribute("provincias", lstProvincias());
        return "resumen/resumen/iniciado_prov";
    }

    @PostMapping(path = {"/iniciado_prov"})
    public String cursoIniciadoProvPOST(HttpServletRequest request) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);
        return "redirect:/iniciado_prov_pdf";
    }

    @GetMapping(path = {"/mat_inicial_mcpal"})
    public String matriculaInicialMcpal(Model m) {

        mcpioSelecionado = getUsuarioAutenticado().getMcpio();

        if (getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
            return "redirect:/mat_inicial_mcpal_pdf";
        }

        m.addAttribute("municipios", lstMunicipios());
        return "resumen/resumen/mat_inicial_mcpal";
    }

    @PostMapping(path = {"/mat_inicial_mcpal"})
    public String matriculaInicialMcpalPOST(HttpServletRequest request) {

        int idMcpioSeleccionado = Integer.parseInt(request.getParameter("mcpios"));
        mcpioSelecionado = municipios.obtenerId(idMcpioSeleccionado);
        return "redirect:/mat_inicial_mcpal_pdf";
    }

    @GetMapping(path = {"/mat_inicial_prov"})
    public String matriculaInicialProv(Model m) {

        provSelecionada = getUsuarioAutenticado().getMcpio().getProv();

        m.addAttribute("provincias", lstProvincias());
        return "resumen/resumen/mat_inicial_prov";
    }

    @PostMapping(path = {"/mat_inicial_prov"})
    public String matriculaInicialProvPOST(HttpServletRequest request) {

        int idProvSeleccionada = Integer.parseInt(request.getParameter("mcpios"));
        provSelecionada = provincias.obtenerId(idProvSeleccionada);
        return "redirect:/mat_inicial_prov_pdf";
    }

    @RequestMapping("/personal_mcpal_pdf")
    public void generarPersonalMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();
        generarDocumentoPDF.generarDocumentoPdf_PersonalMcpal(centrosMcpal(), mcpioSelecionado, response);
    }

    @RequestMapping("/personal_mcpal_excel")
    public void generarPersonalMcpioExcel(HttpServletResponse response) {
        siNoExisteLocalidad();
        generarDocumentoExcel.generarDocumentoExcel_PersonalMcpal(centrosMcpal(), mcpioSelecionado, response);
    }

    private List<Centro> centrosMcpal() {
        return centros.validarListarSegunMunicipio(mcpioSelecionado);
    }

    @RequestMapping("/personal_prov_pdf")
    public void generarPersonalProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();
        generarDocumentoPDF.generarDocumentoPdf_PersonalProv(provSelecionada, response);
    }

    @RequestMapping("/personal_prov_excel")
    public void generarPersonalProvExcel(HttpServletResponse response) {
        siNoExisteLocalidad();
        generarDocumentoExcel.generarDocumentoExcel_PersonalProv(provSelecionada, response);
    }

    @RequestMapping("/personal_nac_pdf")
    public void generarPersonalNacPdf(HttpServletResponse response) throws Exception {
        generarDocumentoPDF.generarDocumentoPdf_PersonalNac(response);
    }

    @RequestMapping("/personal_nac_excel")
    public void generarPersonalNacExcel(HttpServletResponse response) {
        generarDocumentoExcel.generarDocumentoExcel_PersonalNac(response);
    }

    @RequestMapping("/prof_asig_mcpal_pdf")
    public void generarProfAsignaturaMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (profM != null) {
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignaturaMcpal(profM, mcpioSelecionado, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignaturaMcpal(profesoresMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        }
    }

    @RequestMapping("/prof_asig_mcpal_excel")
    public void generarProfAsignaturaMcpioExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (profM != null) {
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignaturaMcpal(profM, mcpioSelecionado, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignaturaMcpal(profesoresMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        }
    }

    @RequestMapping("/prof_asig_prov_pdf")
    public void generarProfAsignaturaProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (profP != null) {
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignaturaProv(profP, provSelecionada, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignaturaProv(profesoresProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        }
    }

    @RequestMapping("/prof_asig_prov_excel")
    public void generarProfAsignaturaProvExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (profP != null) {
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignaturaProv(profP, provSelecionada, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignaturaProv(profesoresProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        }
    }

    @RequestMapping("/prof_asig_nac_pdf")
    public void generarProfAsignaturaNacPdf(HttpServletResponse response) throws Exception {
        if (profN != null) {
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignaturaNac(profN, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_ProfAsignaturaNac(profesoresNac(), response);
        }
    }

    @RequestMapping("/prof_asig_nac_excel")
    public void generarProfAsignaturaNacExcel(HttpServletResponse response) {
        if (profN != null) {
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignaturaNac(profN, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_ProfAsignaturaNac(profesoresNac(), response);
        }
    }

    @RequestMapping("/rango_mcpal_pdf")
    public void generarRangoEdadMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (rangoM != null) {
            generarDocumentoPDF.generarDocumentoPdf_RangoEdadMcpal(rangoM, mcpioSelecionado, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_RangoEdadMcpal(rangoEdadesMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        }
    }

    @RequestMapping("/rango_mcpal_excel")
    public void generarRangoEdadMcpioExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (rangoM != null) {
            generarDocumentoExcel.generarDocumentoExcel_RangoEdadMcpal(rangoM, mcpioSelecionado, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_RangoEdadMcpal(rangoEdadesMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        }
    }

    @RequestMapping("/rango_prov_pdf")
    public void generarRangoEdadProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (rangoP != null) {
            generarDocumentoPDF.generarDocumentoPdf_RangoEdadProv(rangoP, provSelecionada, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_RangoEdadProv(rangosEdadesProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        }
    }

    @RequestMapping("/rango_prov_excel")
    public void generarRangoEdadProvExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (rangoP != null) {
            generarDocumentoExcel.generarDocumentoExcel_RangoEdadProv(rangoP, provSelecionada, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_RangoEdadProv(rangosEdadesProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        }
    }

    @RequestMapping("/rango_nac_pdf")
    public void generarRangoEdadNacPdf(HttpServletResponse response) throws Exception {
        if (rangoN != null) {
            generarDocumentoPDF.generarDocumentoPdf_RangoEdadNac(rangoN, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_RangoEdadNac(rangosEdadesNac(), response);
        }
    }

    @RequestMapping("/rango_nac_excel")
    public void generarRangoEdadNacExcel(HttpServletResponse response) {
        if (rangoN != null) {
            generarDocumentoExcel.generarDocumentoExcel_RangoEdadNac(rangoN, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_RangoEdadNac(rangosEdadesNac(), response);
        }
    }

    @RequestMapping("/ficha_mcpal_pdf")
    public void generarFichaMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (lst_fichaM.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_FichaMcpal(fichaMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_FichaMcpal(lst_fichaM, mcpioSelecionado, response);
        }
    }

    @RequestMapping("/ficha_mcpal_excel")
    public void generarFichaExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (lst_fichaM.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_FichaMcpal(fichaMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_FichaMcpal(lst_fichaM, mcpioSelecionado, response);
        }
    }

    @RequestMapping("/ficha_prov_pdf")
    public void generarFichaProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (lst_fichaP.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_FichaProv(fichaProv(provSelecionada.getIdProvincia()), provSelecionada.getNombProvincia(), response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_FichaProv(lst_fichaP, provSelecionada.getNombProvincia(), response);
        }
    }

    @RequestMapping("/ficha_prov_excel")
    public void generarFichaProvExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (lst_fichaP.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_FichaProv(fichaProv(provSelecionada.getIdProvincia()), provSelecionada.getNombProvincia(), response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_FichaProv(lst_fichaP, provSelecionada.getNombProvincia(), response);
        }
    }

    @RequestMapping("/ficha_nac_pdf")
    public void generarFichaNacPdf(HttpServletResponse response) throws Exception {
        if (lst_fichaN.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_FichaNac(fichaNac(), response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_FichaNac(lst_fichaN, response);
        }
    }

    @RequestMapping("/ficha_nac_excel")
    public void generarFichaNacExcel(HttpServletResponse response) {
        if (lst_fichaN.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_FichaNac(fichaNac(), response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_FichaNac(lst_fichaN, response);
        }
    }

    @RequestMapping("/ficha_turquino_mcpal_pdf")
    public void generarFichaTurquinoMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (lst_fichaMT.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_FichaTurquinoMcpal(fichaTurquinoMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_FichaTurquinoMcpal(lst_fichaMT, mcpioSelecionado, response);
        }
    }

    @RequestMapping("/ficha_turquino_mcpal_excel")
    public void generarFichaTurquinoExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (lst_fichaMT.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_FichaTurquinoMcpal(fichaTurquinoMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_FichaTurquinoMcpal(lst_fichaMT, mcpioSelecionado, response);
        }
    }

    @RequestMapping("/ficha_turquino_prov_pdf")
    public void generarFichaTurquinoProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (lst_fichaPT.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_FichaTurquinoProv(fichaTurquinoProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_FichaTurquinoProv(lst_fichaPT, provSelecionada, response);
        }
    }

    @RequestMapping("/ficha_turquino_prov_excel")
    public void generarFichaTurquinoProvExcel(HttpServletResponse response) {
        siNoExisteLocalidad();

        if (lst_fichaPT.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_FichaTurquinoProv(fichaTurquinoProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_FichaTurquinoProv(lst_fichaPT, provSelecionada, response);
        }
    }

    @RequestMapping("/ficha_turquino_nac_pdf")
    public void generarFichaTurquinoNacPdf(HttpServletResponse response) throws Exception {
        if (lst_fichaNT.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_FichaTurquinoNac(fichaTurquinoNac(), response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_FichaTurquinoNac(lst_fichaNT, response);
        }
    }

    @RequestMapping("/ficha_turquino_nac_excel")
    public void generarFichaTurquinoNacExcel(HttpServletResponse response) {
        if (lst_fichaNT.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_FichaTurquinoNac(fichaTurquinoNac(), response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_FichaTurquinoNac(lst_fichaNT, response);
        }
    }

    @RequestMapping("/terminado_mcpal_pdf")
    public void generarResumenCursoTerminadoMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();
        generarDocumentoPDF.generarDocumentoPdf_ResumenCursoTerminadoMcpio(resumenCursoTerminadoMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
    }

    @RequestMapping("/iniciado_mcpal_pdf")
    public void generarResumenCursoIniciadoMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();
        generarDocumentoPDF.generarDocumentoPdf_ResumenCursoIniciadoMcpio(resumenCursoIniciadoMcpio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
    }

    @RequestMapping("/terminado_prov_pdf")
    public void generarResumenCursoTerminadoProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();
        generarDocumentoPDF.generarDocumentoPdf_ResumenCursoTerminadoProv(resumenCursoTerminadoProv(provSelecionada.getIdProvincia()), provSelecionada, response);
    }

    @RequestMapping("/iniciado_prov_pdf")
    public void generarResumenCursoIniciadoProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();
        generarDocumentoPDF.generarDocumentoPdf_ResumenCursoIniciadoProv(resumenCursoIniciadoProv(provSelecionada.getIdProvincia()), provSelecionada, response);
    }

    @RequestMapping("/terminado_nac_pdf")
    public void generarResumenCursoTerminadoNacPdf(HttpServletResponse response) throws Exception {
        generarDocumentoPDF.generarDocumentoPdf_ResumenCursoTerminadoNac(resumenCursoTerminadoNac(), response);
    }

    @RequestMapping("/iniciado_nac_pdf")
    public void generarResumenCursoIniciadoNacPdf(HttpServletResponse response) throws Exception {
        generarDocumentoPDF.generarDocumentoPdf_ResumenCursoIniciadoNac(resumenCursoIniciadoNac(), response);
    }

    @RequestMapping("/mat_inicial_mcpal_pdf")
    public void generarResumenMatriculaInicialMcpioPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (carM != null) {
            generarDocumentoPDF.generarDocumentoPdf_ResumenMatriculaInicialMcpio(carM, mcpioSelecionado, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_ResumenMatriculaInicialMcpio(caratulaMpcio(mcpioSelecionado.getIdMunicipio()), mcpioSelecionado, response);
        }
    }

    @RequestMapping("/mat_inicial_prov_pdf")
    public void generarResumenMatriculaIniciaProvPdf(HttpServletResponse response) throws Exception {
        siNoExisteLocalidad();

        if (carP != null) {
            generarDocumentoPDF.generarDocumentoPdf_ResumenMatriculaInicialProv(carP, provSelecionada, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_ResumenMatriculaInicialProv(caratulaProv(provSelecionada.getIdProvincia()), provSelecionada, response);
        }
    }

    @RequestMapping("/mat_inicial_nac_pdf")
    public void generarResumenMatriculaInicialNacPdf(HttpServletResponse response) throws Exception {
        if (carN != null) {
            generarDocumentoPDF.generarDocumentoPdf_ResumenMatriculaInicialNac(carN, response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_ResumenMatriculaInicialNac(caratulaNac(), response);
        }
    }

    private void siNoExisteLocalidad() {
        if (mcpioSelecionado == null) {
            mcpioSelecionado = getUsuarioAutenticado().getMcpio();
        }

        if (provSelecionada == null) {
            provSelecionada = mcpioSelecionado.getProv();
        }
    }

    private List<Municipio> lstMunicipios() {
        return municipios.getMunicipiosSegunRol(municipios, getUsuarioAutenticado());
    }

    private List<Provincia> lstProvincias() {
        return provincias.listar();
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }
}