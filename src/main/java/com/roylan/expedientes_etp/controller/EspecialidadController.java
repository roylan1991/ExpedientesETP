package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.business.IValidacion;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarFamilia;
import com.roylan.expedientes_etp.database.services.GestionarNivel;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoExcelImpl;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoPDFImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class EspecialidadController {

    @Autowired
    private IValidacion<Especialidad> especialidades;

    @Autowired
    private GestionarFamilia familias;

    @Autowired
    private GestionarNivel niveles;

    @Autowired
    GenerarDocumentoPDFImpl generarDocumentoPDF;

    @Autowired
    GenerarDocumentoExcelImpl generarDocumentoExcel;

    @Autowired
    private HttpSession session;

    private static List<Especialidad> lst_esp_aux;

    @GetMapping(path = {"/nueva_especialidad"})
    public String nuevaEspecialidad(Model m) {

        m.addAttribute("lst_familias", lstFamilias());
        m.addAttribute("lst_niveles", lstNiveles());

        return "especialidad/nueva_especialidad";
    }

    @PostMapping(path = {"/nueva_especialidad"})
    public String nuevaEspecialidadPOST(HttpServletRequest request, Model m) {

        Especialidad esp;

        try {
            String cod = request.getParameter("codigo");
            m.addAttribute("codigo", cod);
            String nombre = request.getParameter("nombreEsp");
            m.addAttribute("nombreEsp", nombre);
            int idF = Integer.parseInt(request.getParameter("familias"));
            Familia fam = familias.obtenerId(idF);
            int idN = Integer.parseInt(request.getParameter("niveles"));
            Nivel niv = niveles.obtenerId(idN);

            esp = new Especialidad(cod, nombre, fam, niv);

            especialidades.validarAdicionar(esp);
        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            return nuevaEspecialidad(m);
        }
        return "redirect:/especialidades";
    }

    @GetMapping(path = {"/editar_especialidad"})
    public String editarEspecialidad(@RequestParam(name = "idEsp") long idEsp, Model m) {

        try {
            Especialidad esp = especialidades.validarObtenerId(idEsp);
            m.addAttribute("datos_especialidad", esp);
            m.addAttribute("lst_familias", lstFamilias());
            m.addAttribute("lst_niveles", lstNiveles());

            return "especialidad/editar_especialidad";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/editar_especialidad"})
    public String editarEspecialidadPOST(HttpServletRequest request, @RequestParam(name = "idEsp") long idEsp, Model m) {

        Especialidad n_datos;

        try {
            String cod = request.getParameter("codigo");
            String nombre = request.getParameter("nombreEsp");
            long idF = Integer.parseInt(request.getParameter("familias"));
            Familia fam = familias.obtenerId(idF);
            long idN = Integer.parseInt(request.getParameter("niveles"));
            Nivel niv = niveles.obtenerId(idN);

            n_datos = new Especialidad(cod, nombre, fam, niv);

            especialidades.validarActualizar(idEsp, n_datos);
        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            return editarEspecialidad(idEsp, m);
        }

        return "redirect:/especialidades";
    }

    @GetMapping(path = {"/del_especialidad"})
    public String eliminarEspecialidad(@RequestParam(name = "idEsp") long idEsp) {

        try {
            especialidades.validarEliminar(idEsp);
        } catch (Exception e) {
            return "redirect:/error500";
        }

        return "redirect:/especialidades";
    }

    @GetMapping(path = {"/especialidades"})
    public String especialidades(Model m) {

        m.addAttribute("lst_especialidades", lstEspecialidades());

        if (!getUsuarioAutenticado().getRol().getTipoRol().equals("Administrador")) {
            m.addAttribute("ocultar", true);
        }

        return "especialidad/lst_especialidades";
    }

    @RequestMapping("/especialidad_pdf")
    public void generarCentrosPdf(HttpServletResponse response) throws Exception {
        if (lst_esp_aux.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_Especialidades(lstEspecialidades(), response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_Especialidades(lst_esp_aux, response);
        }
    }

    @RequestMapping("/especialidad_excel")
    public void generarCentrosExcel(HttpServletResponse response) {
        if (lst_esp_aux.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_Especialidades(lstEspecialidades(), response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_Especialidades(lst_esp_aux, response);
        }
    }

    private List<Especialidad> lstEspecialidades() {
        lst_esp_aux = especialidades.validarListar();
        return lst_esp_aux;
    }

    private List<Familia> lstFamilias() {
        return familias.listar();
    }

    private List<Nivel> lstNiveles() {
        return niveles.listar();
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }
}