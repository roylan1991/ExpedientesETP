package com.roylan.expedientes_etp.controller;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarEspecialidadActualImpl;
import com.roylan.expedientes_etp.database.services.GestionarFamilia;
import com.roylan.expedientes_etp.database.services.GestionarIndicador;
import com.roylan.expedientes_etp.database.services.GestionarResumen;
import com.roylan.expedientes_etp.exportacion.GenerarDocumentoPDFImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase gestiona el conjunto de vistas de las operaciones básicas del sistema.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/indicadores")
public class IndicadorController {

    @Autowired
    private GrupoRango gr;

    @Autowired
    private GestionarIndicador ind;

    @Autowired
    private HttpSession session;

    @Autowired
    private GestionarResumen resumen;

    @Autowired
    private GestionarFamilia fam_serv;

    @Autowired
    private GestionarEspecialidadActualImpl ea;

    @Autowired
    GenerarDocumentoPDFImpl generarDocumentoPDF;

    private String nivel_visitado;
    private List<GrupoRango> lst_gr_aux = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("0.0");

    @GetMapping(path = {"/retencion"})
    public String retencionEscolar(Model m) {

        String value;
        int idTM = 1, idOC = 2, idC = 1;

        for (int i = 1; i < 5; i++) {
            value = "dato" + i + "TM";
            m.addAttribute(value, Float.parseFloat(df.format(ind.porcientoRetencionAnno(i, idTM, idC)).replace(',', '.')));
            if (i < 3) {
                value = "dato" + i + "OC";
                m.addAttribute(value, Float.parseFloat(df.format(ind.porcientoRetencionAnno(i, idOC, idC)).replace(',', '.')));
            }
        }

        int finalTM = resumen.matriculasFinalesTotalesNac(idTM, idC);
        int finalOC = resumen.matriculasFinalesTotalesNac(idOC, idC);
        m.addAttribute("matriculaFinalTM", finalTM);
        m.addAttribute("matriculaFinalOC", finalOC);

        int graduadosTM = resumen.graduadosTotalesNac(idTM, idC);
        int graduadosOC = resumen.graduadosTotalesNac(idOC, idC);
        m.addAttribute("graduadosTM", graduadosTM);
        m.addAttribute("graduadosOC", graduadosOC);

        int nuevoIngresosTM = resumen.nuevosIngresosTotalesNac(idTM, idC);
        int nuevoIngresosOC = resumen.nuevosIngresosTotalesNac(idOC, idC);
        m.addAttribute("nuevoIngresoTM", nuevoIngresosTM);
        m.addAttribute("nuevoIngresoOC", nuevoIngresosOC);

        int esperadaTM = finalTM - graduadosTM + nuevoIngresosTM;
        int esperadaOC = finalOC - graduadosOC + nuevoIngresosOC;
        m.addAttribute("esperadaTM", esperadaTM);
        m.addAttribute("esperadaOC", esperadaOC);

        int inicialTM = resumen.matriculasTotalesNac(idTM, idC);
        int inicialOC = resumen.matriculasTotalesNac(idOC, idC);
        m.addAttribute("matriculaInicialTM", inicialTM);
        m.addAttribute("matriculaInicialOC", inicialOC);

        int bajasTM = esperadaTM - inicialTM;
        int bajasOC = esperadaOC - inicialOC;
        m.addAttribute("bajasTM", bajasTM);
        m.addAttribute("bajasOC", bajasOC);

        float porcBajasTM, porcBajasOC;
        if (finalTM == 0) {
            porcBajasTM = 0;
        } else {
            porcBajasTM = Float.parseFloat(df.format((float) bajasTM / (float) finalTM * 100).replace(',', '.'));
        }
        if (finalOC == 0) {
            porcBajasOC = 0;
        } else {
            porcBajasOC = Float.parseFloat(df.format((float) bajasOC / (float) finalOC * 100).replace(',', '.'));
        }

        m.addAttribute("porcientoBajasTM", porcBajasTM);
        m.addAttribute("porcientoBajasOC", porcBajasOC);

        m.addAttribute("porcientoRetencionTM", 100 - porcBajasTM);
        m.addAttribute("porcientoRetencionOC", 100 - porcBajasOC);

        return "indicador/retencion";
    }

    @GetMapping(path = {"/grupos_rangos_tm"})
    public String gruposRangosTM(Model m) {

        gruposPorRangosTM(m);

        return "indicador/grupos_rangos_tm";
    }

    @GetMapping(path = {"/grupos_rangos_oc"})
    public String gruposRangosOC(Model m) {

        gruposPorRangosOC(m);

        return "indicador/grupos_rangos_oc";
    }

    @GetMapping(path = {"/mat_ramas"})
    public String matriculasRamas(Model m) {

        String value;
        Rama r;
        List<Familia> fam = fam_serv.listar();
        int ramaD[] = {0, 0, 0, 0, 0}, ramaN[] = {0, 0, 0, 0, 0};
        int idR, matricula, idCD = 1, idCN = 2;

        for (int i = 0; i < fam.size(); i++) {
            r = fam.get(i).getRama();
            value = "familia" + (i + 1);
            matricula = ea.matriculaTotalNacSegunFamilia(i + 1, idCD);
            m.addAttribute(value, matricula);
            idR = (int) r.getIdRama() - 1;
            ramaD[idR] += matricula;

            value = "familia_" + (i + 1);
            matricula = ea.matriculaTotalNacSegunFamilia(i + 1, idCN);
            m.addAttribute(value, matricula);
            ramaN[idR] += matricula;
        }

        for (int i = 0; i < 5; i++) {
            m.addAttribute("rama" + (i + 1), ramaD[i]);
            m.addAttribute("rama_" + (i + 1), ramaN[i]);
        }

        return "indicador/matricula_rama";
    }

    private void gruposPorRangosTM(Model m) {
        lst_gr_aux = new ArrayList<>();
        nivel_visitado = "T.M";

        int[] arr_grupos1 = new int[6];
        int[] arr_mat1 = new int[6];
        List<EspecialidadActual> act = ea.espActualSegunAnnoCursoNivel(1, 1);
        gruposRangosPorAnno(arr_grupos1, arr_mat1, act);

        int[] arr_grupos2 = new int[6];
        int[] arr_mat2 = new int[6];
        act = ea.espActualSegunAnnoCursoNivel(2, 1);
        gruposRangosPorAnno(arr_grupos2, arr_mat2, act);

        int[] arr_grupos3 = new int[6];
        int[] arr_mat3 = new int[6];
        act = ea.espActualSegunAnnoCursoNivel(3, 1);
        gruposRangosPorAnno(arr_grupos3, arr_mat3, act);

        int[] arr_grupos4 = new int[6];
        int[] arr_mat4 = new int[6];
        act = ea.espActualSegunAnnoCursoNivel(4, 1);
        gruposRangosPorAnno(arr_grupos4, arr_mat4, act);

        int[] arr_gruposT = new int[6];
        int[] arr_matT = new int[6];
        for (int i = 0; i < 6; i++) {
            arr_gruposT[i] = arr_grupos1[i] + arr_grupos2[i] + arr_grupos3[i] + arr_grupos4[i];
            arr_matT[i] = arr_mat1[i] + arr_mat2[i] + arr_mat3[i] + arr_mat4[i];
        }

        GrupoRango datos = gr.grupoRango(arr_grupos1, arr_mat1);
        cargarModelo(m, datos, "grupos1");

        datos = gr.grupoRango(arr_grupos2, arr_mat2);
        cargarModelo(m, datos, "grupos2");

        datos = gr.grupoRango(arr_grupos3, arr_mat3);
        cargarModelo(m, datos, "grupos3");

        datos = gr.grupoRango(arr_grupos4, arr_mat4);
        cargarModelo(m, datos, "grupos4");

        datos = gr.grupoRango(arr_gruposT, arr_matT);
        cargarModelo(m, datos, "grupos5");
    }

    private void cargarModelo(Model m, GrupoRango datos, String grupos) {
        m.addAttribute(grupos, datos);
        lst_gr_aux.add(datos);
    }

    private void gruposPorRangosOC(Model m) {
        lst_gr_aux = new ArrayList<>();

        nivel_visitado = "O.C";

        int[] arr_grupos1 = new int[6];
        int[] arr_mat1 = new int[6];
        List<EspecialidadActual> act = ea.espActualSegunAnnoCursoNivel(1, 2);
        gruposRangosPorAnno(arr_grupos1, arr_mat1, act);

        int[] arr_grupos2 = new int[6];
        int[] arr_mat2 = new int[6];
        act = ea.espActualSegunAnnoCursoNivel(2, 2);
        gruposRangosPorAnno(arr_grupos2, arr_mat2, act);

        int[] arr_gruposT = new int[6];
        int[] arr_matT = new int[6];
        for (int i = 0; i < 6; i++) {
            arr_gruposT[i] = arr_grupos1[i] + arr_grupos2[i];
            arr_matT[i] = arr_mat1[i] + arr_mat2[i];
        }

        GrupoRango datos = gr.grupoRango(arr_grupos1, arr_mat1);
        cargarModelo(m, datos, "grupos1");

        datos = gr.grupoRango(arr_grupos2, arr_mat2);
        cargarModelo(m, datos, "grupos2");

        datos = gr.grupoRango(arr_gruposT, arr_matT);
        cargarModelo(m, datos, "grupos5");
    }

    private void gruposRangosPorAnno(int[] arr_grupos, int[] arr_mat, List<EspecialidadActual> act) {
        float ran;
        int grupos, matAct, rango, resta;

        for (int j = 0; j < act.size(); j++) {

            grupos = act.get(j).getCantGrupos();
            matAct = act.get(j).getMatriculaActualTotal();

            ran = (float) matAct / (float) grupos;
            resta = 0;

            if (matAct % grupos != 0) {
                rango = (int) ran + 1;
            } else {
                rango = (int) ran;
            }

            arr_grupos[0] += grupos;
            arr_mat[0] += matAct;

            int suma = 0;
            for (int i = 0; i < grupos; i++) {
                if (i == grupos - 1) {
                    rango = (int) ran - resta;
                }

                if (i == grupos - 2) {
                    int temp = suma;
                    suma += rango + (int) ran;
                    if (suma != matAct) {
                        resta = suma - matAct - 1;
                        rango = (int) ran;
                    }
                    suma = temp;
                }
                suma += rango;

                if (rango >= 1 && rango <= 15) {
                    arr_grupos[1] += 1;
                    arr_mat[1] += rango;
                    continue;
                }

                if (rango >= 16 && rango <= 30) {
                    arr_grupos[2] += 1;
                    arr_mat[2] += rango;
                    continue;
                }

                if (rango >= 31 && rango <= 35) {
                    arr_grupos[3] += 1;
                    arr_mat[3] += rango;
                    continue;
                }

                if (rango >= 36 && rango <= 40) {
                    arr_grupos[4] += 1;
                    arr_mat[4] += rango;
                    continue;
                }

                if (rango >= 41) {
                    arr_grupos[5] += 1;
                    arr_mat[5] += rango;
                }
            }
        }
    }

    @RequestMapping("/gr_rang_tm_pdf")
    public void generarGruposRangosPdfTM(HttpServletResponse response, Model m) throws Exception {
        if (lst_gr_aux.isEmpty()) {
            gruposPorRangosTM(m);
        } else if (!nivel_visitado.equals("T.M")) {
            gruposPorRangosTM(m);
        }
        generarDocumentoPDF.generarDocumentoPdf_GruposRangosTM(lst_gr_aux, response);
    }

    @RequestMapping("/gr_rang_oc_pdf")
    public void generarGruposRangosPdfOC(HttpServletResponse response, Model m) throws Exception {
        if (lst_gr_aux.isEmpty()) {
            gruposPorRangosOC(m);
        } else if (!nivel_visitado.equals("O.C")) {
            gruposPorRangosOC(m);
        }
        generarDocumentoPDF.generarDocumentoPdf_GruposRangosOC(lst_gr_aux, response);
    }

    @ModelAttribute("usuario_autenticado")
    public Usuario getUsuarioAutenticado() {
        return (Usuario) session.getAttribute("usuarioAutenticado");
    }
}