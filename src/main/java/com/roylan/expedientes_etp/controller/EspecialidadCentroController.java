package com.roylan.expedientes_etp.controller;

import com.lowagie.text.DocumentException;
import com.roylan.expedientes_etp.business.IValidacion;
import com.roylan.expedientes_etp.business.ValidacionCentroImpl;
import com.roylan.expedientes_etp.business.ValidacionEspecialidadCentroImpl;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarCurso;
import com.roylan.expedientes_etp.database.services.GestionarDuracion;
import com.roylan.expedientes_etp.database.services.GestionarEscolaridad;
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
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class EspecialidadCentroController {

    @Autowired
    private ValidacionCentroImpl centros;

    @Autowired
    private ValidacionEspecialidadCentroImpl especialidadesCentros;

    @Autowired
    private IValidacion<Especialidad> especialidades;

    @Autowired
    private GestionarCurso cursos;

    @Autowired
    private GestionarDuracion duraciones;

    @Autowired
    private GestionarEscolaridad escolaridades;

    @Autowired
    GenerarDocumentoPDFImpl generarDocumentoPDF;

    @Autowired
    GenerarDocumentoExcelImpl generarDocumentoExcel;

    @Autowired
    private HttpSession session;

    private static List<EspecialidadCentro> lst_esp_aux = new ArrayList<>();
    private static Centro ultimo_cen_visitado;

    @GetMapping(path = {"/nueva_especialidad_centro"})
    public String nuevaEspecialidadCentro(@RequestParam(name = "idE") long idE, Model m) {

        try {
            Centro esc = centros.validarObtenerId(idE);
            m.addAttribute("datos_escuela", esc);
            m.addAttribute("lst_especialidades", lstEspecialidades());
            m.addAttribute("lst_duraciones", lstDuraciones());
            m.addAttribute("lst_escolaridades", lstEscolaridades());
            m.addAttribute("lst_cursos", lstCursos());

            if (!m.containsAttribute("EspCentroError")) {
                m.addAttribute("bien", true);
            } else {
                m.addAttribute("EspCentro", m.getAttribute("EspCentro"));
                m.addAttribute("mal", true);
            }

        } catch (Exception e) {
            return "redirect:/error";
        }

        return "especialidad_centro/nueva_especialidad_centro";
    }

    @PostMapping(path = {"/nueva_especialidad_centro"})
    public String nuevaEspecialidadCentroPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, RedirectAttributes r, Model m) {

        EspecialidadCentro esp = null;

        try {
            Centro centro = centros.validarObtenerId(idE);
            int idEsp = Integer.parseInt(request.getParameter("nombre"));
            int idDuracion = Integer.parseInt(request.getParameter("duraciones"));
            Duracion duracion = duraciones.obtenerId(idDuracion);
            int idEscolaridad = Integer.parseInt(request.getParameter("escolaridades"));
            Escolaridad escolaridad = escolaridades.obtenerId(idEscolaridad);
            int idCurso = Integer.parseInt(request.getParameter("cursos"));
            Curso curso = cursos.obtenerId(idCurso);

            Especialidad e = especialidades.validarObtenerId(idEsp);
            esp = new EspecialidadCentro(e, curso, duracion, escolaridad, centro);

            especialidadesCentros.validarAdicionar(esp);
        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            m.addAttribute("EspCentroError", true);
            m.addAttribute("EspCentro", esp);

            return nuevaEspecialidadCentro(idE, m);
        }

        r.addAttribute("idE", idE);
        return "redirect:/especialidades_centros";
    }

    @GetMapping(path = {"/del_especialidad_centro"})
    public String eliminarEspecialidadCentro(@RequestParam(name = "idEsp") long idEsp, RedirectAttributes r) {

        try {
            EspecialidadCentro e = especialidadesCentros.validarObtenerId(idEsp);
            especialidadesCentros.validarEliminar(idEsp);

            r.addAttribute("idE", e.getCentro().getIdCentro());
            return "redirect:/especialidades_centros";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/especialidades_centros"})
    public String lstEspecialidadesCentros(@RequestParam(name = "idE") long idE, Model m) {

        try {
            Centro centro = centros.validarObtenerId(idE);

            m.addAttribute("datos_escuela", centro);
            m.addAttribute("especialidades_centro", lstEspecialidadesCentro(centro));

            if (!getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
                m.addAttribute("ocultar", true);
            }

            return "especialidad_centro/lst_especialidades_centros";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @RequestMapping("/especialidades_centros_pdf")
    public String generarEspCentrosPdf(@RequestParam(name = "idE") long idE, HttpServletResponse response) throws FileNotFoundException, DocumentException {

        if (!lst_esp_aux.isEmpty()) {
            if (ultimo_cen_visitado.getIdCentro() == idE) {
                generarDocumentoPDF.generarDocumentoPdf_EspecialidadesCentros(ultimo_cen_visitado, lst_esp_aux, response);
                return null;
            }
        }
        return generarDocumento(idE, response, "PDF");
    }

    @RequestMapping("/especialidades_centros_excel")
    public String generarEspCentrosExcel(@RequestParam(name = "idE") long idE, HttpServletResponse response) {
        if (!lst_esp_aux.isEmpty()) {
            if (ultimo_cen_visitado.getIdCentro() == idE) {
                generarDocumentoExcel.generarDocumentoExcel_EspecialidadesCentros(ultimo_cen_visitado, lst_esp_aux, response);
                return null;
            }
        }
        return generarDocumento(idE, response, "EXCEL");
    }

    private String generarDocumento(long idE, HttpServletResponse response, String tipoDocumento) {
        try {
            Centro cen = centros.validarObtenerId(idE);
            if (tipoDocumento.equals("PDF")) {
                generarDocumentoPDF.generarDocumentoPdf_EspecialidadesCentros(cen, lstEspecialidadesCentro(cen), response);
            } else if (tipoDocumento.equals("EXCEL")) {
                generarDocumentoExcel.generarDocumentoExcel_EspecialidadesCentros(cen, lstEspecialidadesCentro(cen), response);
            }
            return null;
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    private List<Especialidad> lstEspecialidades() {
        return especialidades.validarListar();
    }

    private List<EspecialidadCentro> lstEspecialidadesCentro(Centro centro) {
        ultimo_cen_visitado = centro;
        lst_esp_aux = especialidadesCentros.validarListarEspecialidadesDeCentro(centro);
        return especialidadesCentros.validarListarEspecialidadesDeCentro(centro);
    }

    private List<Duracion> lstDuraciones() {
        return duraciones.listar();
    }

    private List<Escolaridad> lstEscolaridades() {
        return escolaridades.listar();
    }

    private List<Curso> lstCursos() {
        return cursos.listar();
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }
}
