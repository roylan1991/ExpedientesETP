package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.business.ValidacionUsuarioImpl;
import com.roylan.expedientes_etp.business.Validaciones;
import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.RolUsuario;
import com.roylan.expedientes_etp.database.entities.Usuario;
import com.roylan.expedientes_etp.database.services.GestionarMunicipio;
import com.roylan.expedientes_etp.database.services.GestionarRolUsuario;
import com.roylan.expedientes_etp.security.EncriptarPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas de los usuarios.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class UsuarioController {

    @Autowired
    private ValidacionUsuarioImpl usuarios;

    @Autowired
    private GestionarRolUsuario roles;

    @Autowired
    private GestionarMunicipio municipios;

    @Autowired
    private EncriptarPassword encriptar;

    @Autowired
    private Validaciones v;

    @Autowired
    private HttpSession session;

    @GetMapping(path = {"/", "/login"})
    public String login() {
        String url = (String) session.getAttribute("url");

        if (url != null) {
            return "redirect:" + url;
        }
        return "login/login";
    }

    @GetMapping(path = {"/nuevo_usuario"})
    public String nuevoUsuario(Model m) {

        m.addAttribute("lst_roles", lstRoles());
        m.addAttribute("lst_municipios", lstMunicipios());

        return "login/nuevo_usuario";
    }

    @PostMapping(path = {"/nuevo_usuario"})
    public String nuevoUsuarioPOST(HttpServletRequest request, Model m) {

        Usuario u;
        try {
            String nombDesc = request.getParameter("nombre");
            String nombUsuario = request.getParameter("usuario");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            v.passwordIgual(password1, password2, 1);
            int mun = Integer.parseInt(request.getParameter("municipio"));
            Municipio mcpio = municipios.obtenerId(mun);
            int rol = Integer.parseInt(request.getParameter("rol"));
            RolUsuario r = roles.obtenerId(rol);

            boolean estado = false;
            if (request.getParameter("estado").equals("Activo")) {
                estado = true;
            }

            u = new Usuario(nombUsuario, nombDesc, encriptar.encriptar(password1), estado, mcpio, r);
            usuarios.validarAdicionar(u);

        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            return nuevoUsuario(m);
        }
        return "redirect:lst_usuarios";
    }

    @GetMapping(path = {"/editar_usuario"})
    public String editarUsuario(@RequestParam(name = "idU") long idU, Model m) {

        try {
            Usuario u = usuarios.validarObtenerId(idU);
            boolean bloquear = false, ocultarPassword = true;

            String tipoRol = getUsuarioAutenticado().getRol().getTipoRol();
            long idUAutenticado = getUsuarioAutenticado().getIdUsuario();

            if (tipoRol.equals("Usuario")) {
                if (idU != idUAutenticado) {
                    return "redirect:/error403";
                }
            } else if (tipoRol.equals("Administrador")) {
                if (u.getRol().getTipoRol().equals("Usuario")) {
                    return "redirect:/error403";
                }
            }

            if (idU == idUAutenticado) {
                if (getUsuarioAutenticado().getRol().getTipoRol().equals("Usuario")) {
                    bloquear = true;
                }
                ocultarPassword = false;
            }

            m.addAttribute("bloquear", bloquear);
            m.addAttribute("ocultarPassword", ocultarPassword);
            m.addAttribute("datos_usuario", u);
            m.addAttribute("lst_roles", lstRoles());
            m.addAttribute("lst_municipios", lstMunicipios());

            return "login/editar_usuario";
        } catch (
                Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/editar_usuario"})
    public String editarUsuarioPOST(@RequestParam(name = "idU") long idU, HttpServletRequest request) throws Exception {

        Usuario actual = usuarios.validarObtenerId(idU);

        String nombDesc = request.getParameter("nombre");
        if (nombDesc == null) {
            nombDesc = actual.getNombDescriptivo();
        }

        String nombUsuario = actual.getNombUsuario();
        String password = actual.getPassword();

        String est = request.getParameter("estado");
        boolean estado = true;

        if (est == null) {
            estado = actual.getEstado();
        } else if (est.equals("false") || est.equals("Inactivo")) {
            estado = false;
        }

        Municipio mcpio;
        String mc = request.getParameter("municipio");

        if (mc != null) {
            mcpio = municipios.obtenerId(Integer.parseInt(mc));
        } else {
            mcpio = actual.getMcpio();
        }
        RolUsuario rol = actual.getRol();

        Usuario u = new Usuario(nombUsuario, nombDesc, password, estado, mcpio, rol);
        usuarios.validarActualizar(idU, u);

        if (idU == getUsuarioAutenticado().getIdUsuario()) {
            u.setIdUsuario(idU);
            session.setAttribute("usuarioAutenticado", u);
        }

        return "redirect:/lst_usuarios";
    }

    @GetMapping(path = {"/del_usuario"})
    public String eliminarUsuario(@RequestParam(name = "idU") long idU) {

        try {
            Usuario u = usuarios.validarObtenerId(idU);
            /*Para que un mismo usuario no logre eliminarse*/
            if (idU != getUsuarioAutenticado().getIdUsuario()) {
                if (getUsuarioAutenticado().getRol().getTipoRol().equals("Administrador")) {
                    if (u.getRol().getTipoRol().equals("Usuario")) {
                        return "redirect:/error403";
                    }
                }
                usuarios.validarEliminar(idU);
            }

            return "redirect:lst_usuarios";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping(path = {"/cambiar_password"})
    public String cambioPassword(@RequestParam(name = "idU") long idU, Model m) {

        try {
            Usuario u = usuarios.validarObtenerId(idU);

            if (idU != getUsuarioAutenticado().getIdUsuario()) {
                return "redirect:/error403";
            }

            m.addAttribute("datos_usuario", u);
            return "login/cambiar_password";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PostMapping(path = {"/cambiar_password"})
    public String cambioPasswordPOST(@RequestParam(name = "idU") long idU, HttpServletRequest request, RedirectAttributes r, Model m) {

        Usuario n_datos;
        try {
            Usuario actual = usuarios.validarObtenerId(idU);
            String passwordActual = request.getParameter("password");

            if (!encriptar.passwordMatch(passwordActual, actual.getPassword())) {
                v.passwordIgual(actual.getPassword(), passwordActual, -1);
            }

            String passwordNuevo = request.getParameter("password1");
            String passwordConfirmar = request.getParameter("password2");
            v.passwordIgual(passwordNuevo, passwordConfirmar, 1);

            String nombDesc = actual.getNombDescriptivo();
            String nombUsuario = actual.getNombUsuario();
            boolean estado = actual.getEstado();
            Municipio mcpio = actual.getMcpio();
            RolUsuario rol = actual.getRol();

            n_datos = new Usuario(nombUsuario, nombDesc, encriptar.encriptar(passwordNuevo), estado, mcpio, rol);

            usuarios.validarActualizar(idU, n_datos);
        } catch (Exception ex) {
            m.addAttribute("error", true);
            m.addAttribute("mensaje_error", ex.getMessage());

            return cambioPassword(idU, m);
        }

        r.addAttribute("idU", idU);
        return "redirect:/editar_usuario";
    }

    @GetMapping(path = {"/lst_usuarios"})
    public String listadoUsuarios(Model m) {

        List<Usuario> lst;

        Usuario aut = getUsuarioAutenticado();

        if (!aut.getRol().getTipoRol().equals("Administrador")) {
            lst = usuarios.validarListarSegunProvincia("Administrador", aut.getMcpio().getProv());
        } else {
            lst = usuarios.validarListar();
        }

        if (aut.getRol().getTipoRol().contains("Usuario")) {
            m.addAttribute("ocultar", true);
        }

        m.addAttribute("usuarios", lst);
        return "login/lst_usuarios";
    }

    private List<RolUsuario> lstRoles() {
        List<RolUsuario> lst_roles = new LinkedList<>();

        String rol = getUsuarioAutenticado().getRol().getTipoRol();

        if (rol.equals("Supervisor")) {
            lst_roles.add(roles.obtenerId(3));
        } else if (rol.equals("Administrador")) {
            lst_roles.add(roles.obtenerId(2));
        }
        return lst_roles;
    }

    private List<Municipio> lstMunicipios() {
        return municipios.getMunicipiosSegunRol(municipios, getUsuarioAutenticado());
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }
}