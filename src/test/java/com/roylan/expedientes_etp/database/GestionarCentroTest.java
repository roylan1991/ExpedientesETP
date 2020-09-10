package com.roylan.expedientes_etp.database;

import com.roylan.expedientes_etp.database.entities.Centro;
import com.roylan.expedientes_etp.database.entities.Municipio;
import com.roylan.expedientes_etp.database.entities.Provincia;
import com.roylan.expedientes_etp.database.entities.Sector;
import com.roylan.expedientes_etp.database.services.GestionarCentroImpl;
import com.roylan.expedientes_etp.database.services.GestionarMunicipio;
import com.roylan.expedientes_etp.database.services.GestionarProvincia;
import com.roylan.expedientes_etp.database.services.GestionarSector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GestionarCentroTest {

    @Autowired
    private GestionarCentroImpl centros;

    @Autowired
    private GestionarMunicipio mcpios;

    @Autowired
    private GestionarSector sector;

    @Autowired
    private GestionarProvincia provc;

    @Before
    public void init() {
        Provincia prov = new Provincia();
        prov.setCodProvincia("34");
        prov.setNombProvincia("Santiago de Cuba");
        provc.adicionar(prov);

        mcpios.adicionar(new Municipio("06", "Santiago de Cuba", provc.obtenerId(1)));

        sector.adicionar(new Sector("Urbano"));
    }

    @Test
    public void crud() {
        Centro c = new Centro();
        c.setNombCentro("Pepito Tey");
        c.setCodCentro("34060000");
        c.setDireccion("La Risueña s/n");
        c.setSector(sector.obtenerId(1));
        c.setMcpio(mcpios.obtenerId(1));

        centros.adicionar(c);
        Centro cBD = centros.obtenerCodigo("34060000");

        assertEquals(cBD.getNombCentro(), "Pepito Tey");
        assertEquals(cBD.getDireccion(), "La Risueña s/n");

        c.setNombCentro("Pepito");
        c.setCodCentro("34060001");

        centros.update(1, c);
        cBD = centros.obtenerCodigo("34060001");

        assertEquals(cBD.getNombCentro(), "Pepito");
        assertNotEquals(cBD.getCodCentro(), "34060000");

        centros.eliminar(1);
        assertEquals(centros.obtenerId(1), null);

        mostrar(centros.listar());
    }

    private void mostrar(List<Centro> lst) {
        System.out.println("\n[CENTROS]");

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println("\n");
    }
}