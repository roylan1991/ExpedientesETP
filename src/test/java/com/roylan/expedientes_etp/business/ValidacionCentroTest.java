package com.roylan.expedientes_etp.business;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.*;
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
public class ValidacionCentroTest {

    @Autowired
    private ValidacionCentroImpl centros;

    @Autowired
    private GestionarMunicipio mcpios;

    @Autowired
    private GestionarUsuarioImpl usuarios;

    @Autowired
    private GestionarRolUsuario rol_usuario;

    @Autowired
    private GestionarSector sector;

    @Autowired
    private GestionarProvincia provc;

    private Centro c;

    @Before
    public void init() {
        RolUsuario r = new RolUsuario();
        r.setTipoRol("Usuario");
        rol_usuario.adicionar(r);

        Provincia prov = new Provincia();
        prov.setCodProvincia("34");
        prov.setNombProvincia("Santiago de Cuba");
        provc.adicionar(prov);

        mcpios.adicionar(new Municipio("06", "Santiago de Cuba", provc.obtenerId(1)));

        sector.adicionar(new Sector("Urbano"));

        c = new Centro();
        c.setNombCentro("Pepito");
        c.setCodCentro("3406");
        c.setDireccion("La Risueña s/n");
        c.setSector(sector.obtenerId(1));
        c.setMcpio(mcpios.obtenerId(1));

        Usuario user = new Usuario();
        user.setNombUsuario("scu");
        user.setRol(rol_usuario.obtenerId(1));
        user.setEstado(true);
        user.setMcpio(mcpios.obtenerId(1));
        usuarios.adicionar(user);
    }

    @Test
    public void crud() throws Exception {
        try {
            centros.validarAdicionar(c);
        } catch (Exception e) {
            System.out.println("Código de centro incorrecto: Prueba Exitosa");
        }

        c.setCodCentro("34060000");
        centros.validarAdicionar(c);

        try {
            centros.validarAdicionar(c);
        } catch (Exception e) {
            System.out.println("Centro ya registrado: Prueba Exitosa");
        }

        Centro cBD = centros.validarObtenerCodigo(c.getCodCentro());
        assertEquals(cBD.getNombCentro(), c.getNombCentro());
        assertEquals(cBD.getCodCentro(), c.getCodCentro());

        Centro c2 = new Centro();
        c2.setNombCentro("Julius Fucik");
        c2.setCodCentro("34061111");
        c2.setDireccion("Algun lugar s/n");
        c2.setSector(sector.obtenerId(1));
        c2.setMcpio(mcpios.obtenerId(1));
        centros.validarAdicionar(c2);

        c.setCodCentro("34061111");

        try {
            centros.validarActualizar(1, c);
        } catch (Exception e) {
            System.out.println("Actualizando centro con código utilizado por otro centro: Prueba Exitosa");
        }

        c.setCodCentro("34060000");
        c.setNombCentro("Pepito Tey");
        centros.validarActualizar(1, c);

        cBD = centros.validarObtenerId(1, usuarios.obtenerId(1));
        assertEquals(cBD.getNombCentro(), c.getNombCentro());
        assertEquals(cBD.getCodCentro(), c.getCodCentro());

        mostrar(centros.validarListar());

        centros.validarEliminar(1);
        centros.validarEliminar(2);

        try {
            centros.validarObtenerId(1, usuarios.obtenerId(1));
        } catch (Exception e) {
            System.out.println("Centro ya eliminado: Prueba Exitosa");
        }
    }

    private void mostrar(List<Centro> lst) {
        System.out.println("\n[CENTROS]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}