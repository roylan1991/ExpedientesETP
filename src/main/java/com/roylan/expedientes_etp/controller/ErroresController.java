package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.database.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Esta clase gestiona los errores de los códigos de estados HTTP del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
public class ErroresController implements ErrorController {

    @Autowired
    private HttpSession session;

    @GetMapping(path = {"/error"})
    public String manejadorErrores(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            //Parámetros inválidos
            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return error400();
            }

            //Acceso denegado
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return error403();
            }

            //URL inválida o recurso inexistente
            if (statusCode == HttpStatus.NOT_FOUND.value() || statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return error404();
            }

            //Servicio no disponible por mantenimiento
            if (statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                return error503();
            }
        }
        return error404();
    }

    // @GetMapping(path = {"/error400"})
    public String error400() {
        return "error/error400";
    }

    @GetMapping(path = {"/error403"})
    public String error403() {
        return "error/error403";
    }

    //@GetMapping(path = {"/error404"})
    public String error404() {
        return "error/error404";
    }

    @GetMapping(path = {"/error500"})
    public String error500() {
        return "error/error500";
    }

    //@GetMapping(path = {"/error503"})
    public String error503() {
        return "error/error503";
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }

    public String getErrorPath() {
        return null;
    }
}
