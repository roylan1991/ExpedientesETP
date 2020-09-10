package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.business.IValidacion;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class AyudaController {

    @Autowired
    private GestionarSector sectores;

    @Autowired
    private GestionarRolUsuario roles;

    @Autowired
    private GestionarMunicipio municipios;

    @Autowired
    private GestionarNivel niveles;

    @Autowired
    private GestionarFamilia familias;

    @Autowired
    private IValidacion<Especialidad> especialidades;

    @Autowired
    private GestionarCurso cursos;

    @Autowired
    private GestionarDuracion duraciones;

    @Autowired
    private GestionarEscolaridad escolaridades;

    @Autowired
    private HttpSession session;


    @GetMapping(path = {"/ayuda_introduccion"})
    public String introduccion() {
        return "ayuda/introduccion";
    }

    @GetMapping(path = {"/ayuda_centro"})
    public String nuevoCentro(Model m) {
        m.addAttribute("lst_sectores", lstSectores());
        return "ayuda/centro";
    }

    @GetMapping(path = {"/ayuda_esp_centro"})
    public String especialidadCentro(Model m) {
        m.addAttribute("lst_especialidades", lstEspecialidades());
        m.addAttribute("lst_duraciones", lstDuraciones());
        m.addAttribute("lst_escolaridades", lstEscolaridades());
        m.addAttribute("lst_cursos", lstCursos());

        return "ayuda/esp_centro";
    }

    @GetMapping(path = {"/ayuda_roles"})
    public String rolesUsuario() {
        return "ayuda/roles";
    }

    @GetMapping(path = {"/ayuda_cuentas"})
    public String cuentasUsuario(Model m) {
        m.addAttribute("lst_roles", lstRoles());
        m.addAttribute("lst_municipios", lstMunicipios());
        return "ayuda/cuentas_usuario";
    }

    @GetMapping(path = {"/ayuda_especialidad"})
    public String especialidades(Model m) {
        m.addAttribute("lst_familias", lstFamilias());
        m.addAttribute("lst_niveles", lstNiveles());
        return "ayuda/especialidades";
    }

    @GetMapping(path = {"/ayuda_flujo"})
    public String flujoTrabajo() {
        return "ayuda/flujo";
    }

    private List<Sector> lstSectores() {
        return sectores.listar();
    }

    private List<RolUsuario> lstRoles() {
        return roles.listar();
    }

    private List<Familia> lstFamilias() {
        return familias.listar();
    }

    private List<Nivel> lstNiveles() {
        return niveles.listar();
    }

    private List<Municipio> lstMunicipios() {
        return municipios.listar();
    }

    private List<Especialidad> lstEspecialidades() {
        return especialidades.validarListar();
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