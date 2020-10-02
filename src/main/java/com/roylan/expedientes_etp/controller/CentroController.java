package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.business.ValidacionCentroImpl;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarSector;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoExcelImpl;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoPDFImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
public class CentroController {

    @Autowired
    private ValidacionCentroImpl centros;

    @Autowired
    private GestionarSector sectores;

    @Autowired
    GenerarDocumentoPDFImpl generarDocumentoPDF;

    @Autowired
    GenerarDocumentoExcelImpl generarDocumentoExcel;

    @Autowired
    private HttpSession session;

    private static List<Centro> lst_centros_aux = new ArrayList<>();

    @GetMapping(path = {"/nuevo_centro"})
    public String nuevoCentro(Model m) {
        m.addAttribute("lst_sectores", lstSectores());

        return "centro/nuevo_centro";
    }

    @PostMapping(path = {"/nuevo_centro"})
    public String nuevoCentroPOST(HttpServletRequest request, Model m) {

        Centro c;

        try {
            String cod = request.getParameter("codigo");
            m.addAttribute("codigo", cod);
            String nombre = request.getParameter("nombre");
            m.addAttribute("nombre", nombre);
            String direccion = request.getParameter("direccion");
            m.addAttribute("direccion", direccion);
            String telefono = request.getParameter("telefono");
            m.addAttribute("telefono", telefono);
            Sector sector = sectores.obtenerId(Integer.parseInt(request.getParameter("sectores")));
            Municipio mcpio = getUsuarioAutenticado().getMcpio();

            c = new Centro(cod, nombre, direccion, telefono, sector, mcpio);

            centros.validarAdicionar(c);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            return nuevoCentro(m);
        }

        return "redirect:/lst_centros";
    }

    @GetMapping(path = {"/editar_centro"})
    public String editarCentro(@RequestParam(name = "idE") long idE, Model m) {

        try {
            Centro esc = centros.validarObtenerId(idE);
            m.addAttribute("datos_escuela", esc);
            m.addAttribute("lst_sectores", lstSectores());

            return "centro/editar_centro";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/editar_centro"})
    public String editarCentroPOST(HttpServletRequest request, @RequestParam(name = "idE") long idE, Model m) {

        Centro n_datos;
        try {
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            Sector sector = sectores.obtenerId(Integer.parseInt(request.getParameter("sectores")));
            Municipio mcpio = getUsuarioAutenticado().getMcpio();

            n_datos = new Centro(codigo, nombre, direccion, telefono, sector, mcpio);

            centros.validarActualizar(idE, n_datos);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            return editarCentro(idE, m);
        }

        return "redirect:/lst_centros";
    }

    @GetMapping(path = {"/del_centro"})
    public String eliminarCentro(@RequestParam(name = "idE") long idE) {

        try {
            centros.validarEliminar(idE);

            return "redirect:/lst_centros";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/lst_centros"})
    public String centros(Model m) {

        m.addAttribute("lst_centros", lstCentros());

        if (!getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
            m.addAttribute("ocultar", true);
        }

        return "centro/lst_centros";
    }

    @RequestMapping("/centros_pdf")
    public void generarCentrosPdf(HttpServletResponse response) throws Exception {
        if (lst_centros_aux.isEmpty()) {
            generarDocumentoPDF.generarDocumentoPdf_Centros(lstCentros(), response);
        } else {
            generarDocumentoPDF.generarDocumentoPdf_Centros(lst_centros_aux, response);
        }
    }

    @RequestMapping("/centros_excel")
    public void generarCentrosExcel(HttpServletResponse response) {
        if (lst_centros_aux.isEmpty()) {
            generarDocumentoExcel.generarDocumentoExcel_Centros(lstCentros(), response);
        } else {
            generarDocumentoExcel.generarDocumentoExcel_Centros(lst_centros_aux, response);
        }
    }

    private List<Sector> lstSectores() {
        return sectores.listar();
    }

    private List<Centro> lstCentros() {
        lst_centros_aux = centros.getCentrosSegunRol(centros, getUsuarioAutenticado());
        return lst_centros_aux;
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }
}
