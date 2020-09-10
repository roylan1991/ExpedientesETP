package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.*;
import com.roylan.expedientes_etp.security.EncriptarPassword;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ValidacionUsuarioTest {

    @Autowired
    private ValidacionUsuarioImpl usuarios;

    @Autowired
    private EncriptarPassword encriptar;

    @Autowired
    private GestionarMunicipio mcpios;

    @Autowired
    private GestionarRolUsuario rol;

    @Autowired
    private GestionarProvincia provc;

    private Usuario u;

    @Before
    public void init() {
        Provincia prov = new Provincia();
        prov.setCodProvincia("34");
        prov.setNombProvincia("Santiago de Cuba");
        provc.adicionar(prov);

        mcpios.adicionar(new Municipio("06", "Santiago de Cuba", provc.obtenerId(1)));

        rol.adicionar(new RolUsuario("Administrador"));

        u = new Usuario();
        u.setNombUsuario("1admin");
        u.setNombDescriptivo("Administrador");
        u.setPassword(encriptar.encriptar("secreto"));
        u.setEstado(true);
        u.setRol(rol.obtenerId(1));
        u.setMcpio(mcpios.obtenerId(1));
    }

    @Test
    public void crud() throws Exception {
        try {
            usuarios.validarAdicionar(u);
        } catch (Exception e) {
            System.out.println("Nombre de usuario incorrecto: Prueba Exitosa");
        }

        u.setNombUsuario("admin");
        usuarios.validarAdicionar(u);

        Usuario uBD = usuarios.validarObtenerId(1);
        assertEquals(uBD.getNombUsuario(), u.getNombUsuario());
        assertEquals(uBD.getNombDescriptivo(), u.getNombDescriptivo());
        assertEquals(encriptar.passwordMatch("secreto", uBD.getPassword()), true);

        u.setNombDescriptivo("Roylan Bressler");
        u.setPassword(encriptar.encriptar("privado"));

        usuarios.validarActualizar(1, u);

        uBD = usuarios.validarObtenerId(1);
        assertEquals(uBD.getNombUsuario(), u.getNombUsuario());
        assertEquals(uBD.getNombDescriptivo(), u.getNombDescriptivo());
        assertEquals(encriptar.passwordMatch("privado", uBD.getPassword()), true);

        mostrar(usuarios.validarListar());

        usuarios.validarEliminar(1);

        try {
            usuarios.validarObtenerId(1);
        } catch (Exception e) {
            System.out.println("Uuario ya eliminado: Prueba Exitosa");
        }
    }

    private void mostrar(List<Usuario> lst) {
        System.out.println("\n[USUARIOS:]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}