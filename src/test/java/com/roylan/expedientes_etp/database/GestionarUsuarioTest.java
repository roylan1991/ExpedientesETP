package com.roylan.expedientes_etp.database;

import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.RolUsuario;
import com.roylan.expedientes_etp.database.entities.Usuario;
import com.roylan.expedientes_etp.database.services.GestionarMunicipio;
import com.roylan.expedientes_etp.database.services.GestionarProvincia;
import com.roylan.expedientes_etp.database.services.GestionarRolUsuario;
import com.roylan.expedientes_etp.database.services.GestionarUsuarioImpl;
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
public class GestionarUsuarioTest {

    @Autowired
    private GestionarUsuarioImpl usuario;

    @Autowired
    private EncriptarPassword encript;

    @Autowired
    private GestionarMunicipio mcpios;

    @Autowired
    private GestionarProvincia provc;

    @Autowired
    private GestionarRolUsuario roles;

    @Before
    public void init() {
        Provincia prov = new Provincia();
        prov.setCodProvincia("34");
        prov.setNombProvincia("Santiago de Cuba");
        provc.adicionar(prov);

        mcpios.adicionar(new Municipio("06", "Santiago de Cuba", provc.obtenerId(1)));

        roles.adicionar(new RolUsuario("Administrador"));
    }

    @Test
    public void crud() {
        Usuario u = new Usuario();
        u.setNombUsuario("prueba");
        u.setNombDescriptivo("Prueba");
        u.setEstado(true);
        u.setPassword(encript.encriptar("secreto"));
        u.setMcpio(mcpios.obtenerId(1));
        u.setRol(roles.obtenerId(1));

        usuario.adicionar(u);
        Usuario uBD = usuario.obtenerNomb("prueba");

        assertNotEquals(uBD.getPassword(), "secreto");
        assertTrue(encript.passwordMatch("secreto", uBD.getPassword()));
        assertEquals(uBD.getMcpio().getNombMunicipio(), "Santiago de Cuba");
        assertEquals(uBD.getRol().getTipoRol(), u.getRol().getTipoRol());
        assertEquals(uBD.getEstado(), true);

        u.setNombDescriptivo("Prueba2");
        u.setEstado(false);

        usuario.update(1, u);

        uBD = usuario.obtenerId(1);

        assertEquals(uBD.getNombDescriptivo(), "Prueba2");
        assertEquals(uBD.getEstado(), false);

        usuario.eliminar(1);
        assertEquals(usuario.obtenerId(1), null);

        mostrar(usuario.listar());
    }

    private void mostrar(List<Usuario> lst) {
        System.out.println("\n[USUARIOS]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}