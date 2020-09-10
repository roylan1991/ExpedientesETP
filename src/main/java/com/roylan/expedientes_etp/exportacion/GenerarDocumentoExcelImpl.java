package com.roylan.expedientes_etp.exportacion;

import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarProvincia;
import com.roylan.expedientes_etp.database.services.GestionarResumen;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Esta clase gestiona la exportación de la información a un documento Excel.
 *
 * @author Roylan Bressler
 * @since 1.0
 */
@Service
public class GenerarDocumentoExcelImpl implements IGenerarDocumento {

    private HSSFWorkbook libro;

    private HSSFSheet hoja;

    private HSSFRow fila;

    private FileOutputStream fileOutputStream;

    private File ruta;

    private String[] titulo, contenido;

    private String nombreHoja, nombreTitulo, nombreTitulo1, nombreTitulo2, nombreExcel;

    private int cantColumnas, fila1Elementos = 6, totalF, noTitulado, noTituladoEstudiando, totalC, noProfesional, noProfesionalEstudiando;

    private HSSFCellStyle estiloCeldasTitulo, estiloCeldasElementosIzq, estiloCeldasElementosCen;

    @Autowired
    private GestionarProvincia provincias;

    @Autowired
    private GestionarResumen resumenes;

    public GenerarDocumentoExcelImpl() {
    }

    /**
     * Esta funcionalidad configura el estilo que tendrá cada elemento(celda) del documento.
     */
    public void crearEncabezadoTabla() {
        ruta = new File(nombreExcel + ".xls");

        try {
            fileOutputStream = new FileOutputStream(ruta);

            libro = new HSSFWorkbook();
            hoja = libro.createSheet(nombreHoja);
            estiloCeldasTitulo = libro.createCellStyle();
            estiloCeldasElementosIzq = libro.createCellStyle();
            estiloCeldasElementosCen = libro.createCellStyle();

            cantColumnas = titulo.length;

            fila = hoja.createRow(0);
            HSSFCell celda = fila.createCell(0);
            fila.setHeight((short) 300);
            nombreTitulo = "Información estadística de inicio de curso";
            celda.setCellValue(nombreTitulo);

            fila = hoja.createRow(1);
            celda = fila.createCell(0);
            fila.setHeight((short) 300);
            celda.setCellValue(nombreTitulo1);

            fila = hoja.createRow(2);
            celda = fila.createCell(0);
            fila.setHeight((short) 300);
            celda.setCellValue(nombreTitulo2);

            fila = hoja.createRow(fila1Elementos - 1);
            for (int i = 0; i < cantColumnas; i++) {
                celda = fila.createCell(i);
                estiloEncabezado(celda, 350);
                celda.setCellValue(titulo[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad configura el estilo que tendrá cada elemento(celda) del cuerpo de la tabla.
     */
    public void crearCuerpoTabla() {

        fila.setHeight((short) 300);

        for (int j = 0; j < cantColumnas; j++) {
            HSSFCell celda = fila.createCell(j);

            orientacionCelda(celda, j);
        }
    }

    /**
     * Esta funcionalidad se encarga de mostrar el cuadro de diálogo que permitirá: Abrir o Descargar el documento PDF.
     *
     * @param response Respuesta HTTP.
     * @throws IOException Si se ha producido un error en la entrada/salida.
     */
    public void cuadroDialogo(HttpServletResponse response) throws IOException {
        libro.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();

        final int BUFFER_SIZE = 4096;

        FileInputStream inputStream = new FileInputStream(ruta);
        response.setHeader("content-disposition", "attachment; filename=" + ruta.getPath());
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int byteRead = -1;
        while ((byteRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, byteRead);
        }

        inputStream.close();
        outputStream.close();
        ruta.delete();
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los centros ETP.
     *
     * @param lst_centros Centros ETP que serán el contenido el cuerpo de la tabla.
     * @param response    Respuesta.
     */
    public void generarDocumentoExcel_Centros(List<Centro> lst_centros, HttpServletResponse response) {
        nombreTitulo1 = nombreHoja = "Centros Politécnicos";
        nombreExcel = "CentrosETP";

        String[] titulo = {"No.", "Código", "Nombre del Centro", "Dirección Particular", "Teléfono", "Sector", "Municipio", "Provincia"};
        this.titulo = titulo;

        crearEncabezadoTabla();

        hoja.setColumnWidth(0, 2000);
        hoja.setColumnWidth(1, 3200);
        hoja.setColumnWidth(2, 8000);
        hoja.setColumnWidth(3, 15000);
        hoja.setColumnWidth(4, 3200);
        hoja.setColumnWidth(5, 4800);
        hoja.setColumnWidth(6, 5200);
        hoja.setColumnWidth(7, 4700);

        for (int i = 0; i < lst_centros.size(); i++) {

            Centro c = lst_centros.get(i);
            String[] contenido = {Integer.toString(i + 1), c.getCodCentro(), c.getNombCentro(), c.getDireccion(), c.getTelefono(), c.getSector().getTipoSector(), c.getMcpio().getNombMunicipio(), c.getMcpio().getProv().getNombProvincia()};
            this.contenido = contenido;

            fila = hoja.createRow(i + fila1Elementos);
            crearCuerpoTabla();
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de las especialidades ETP.
     *
     * @param lst_esp  Especialidades ETP que serán el contenido el cuerpo de la tabla.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_Especialidades(List<Especialidad> lst_esp, HttpServletResponse response) {
        nombreTitulo1 = "Especialidades de Estudio en Politécnicos";
        nombreExcel = nombreHoja = "EspecialidadesETP";

        String[] titulo = {"No.", "Código", "Nombre de Especialidad", "Familia", "Rama", "Nivel"};
        this.titulo = titulo;

        crearEncabezadoTabla();

        hoja.setColumnWidth(0, 2000);
        hoja.setColumnWidth(1, 3200);
        hoja.setColumnWidth(2, 14800);
        hoja.setColumnWidth(3, 10300);
        hoja.setColumnWidth(4, 4000);
        hoja.setColumnWidth(5, 2000);

        for (int i = 0; i < lst_esp.size(); i++) {

            Especialidad e = lst_esp.get(i);
            String[] contenido = {Integer.toString(i + 1), e.getCodEspecialidad(), e.getNombEspecialidad(), e.getFamilia().getTipoFamilia(), e.getFamilia().getRama().getTipoRama(), e.getNivel().getTipoNivel()};
            this.contenido = contenido;

            fila = hoja.createRow(i + fila1Elementos);
            crearCuerpoTabla();
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de las especialidades de estudio de un centro ETP.
     *
     * @param centro     Centro.
     * @param esp_centro Especialidades del centro.
     * @param response   Respuesta.
     */
    public void generarDocumentoExcel_EspecialidadesCentros(Centro centro, List<EspecialidadCentro> esp_centro, HttpServletResponse response) {
        nombreHoja = nombreExcel = "EspecialidadesCentro";
        nombreTitulo1 = "Especialidades de Estudio";
        nombreTitulo2 = "Politécnico: " + centro.getNombCentro();

        String[] titulo = {"No.", "Código", "Nombre de Especialidad", "Duración (Semestres)", "Escolaridad", "Nivel", "Curso"};
        this.titulo = titulo;

        crearEncabezadoTabla();

        hoja.setColumnWidth(0, 2000);
        hoja.setColumnWidth(1, 3200);
        hoja.setColumnWidth(2, 14800);
        hoja.setColumnWidth(3, 5000);
        hoja.setColumnWidth(4, 5200);
        hoja.setColumnWidth(5, 2000);
        hoja.setColumnWidth(6, 3000);

        EspecialidadCentro e;
        for (int i = 0; i < esp_centro.size(); i++) {

            e = esp_centro.get(i);
            String[] contenido = {Integer.toString(i + 1), e.getEspecialidad().getCodEspecialidad(), e.getEspecialidad().getNombEspecialidad(), Integer.toString(e.getDuracion().getTipoDuracion()), e.getEscolaridad().getTipoEscolaridad(), e.getEspecialidad().getNivel().getTipoNivel(), e.getCurso().getTipoCurso()};
            this.contenido = contenido;

            fila = hoja.createRow(i + fila1Elementos);
            crearCuerpoTabla();
        }

        try {
            cuadroDialogo(response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los profesores por asignaturas de los centros ETP.
     *
     * @param centro   Centros ETP que sus rangos de edades serán el contenido el cuerpo de la tabla.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_ProfAsignatura(Centro centro, HttpServletResponse response) {
        nombreExcel = nombreHoja = "ProfAsignatura";
        nombreTitulo1 = "Profesores fijos y contratados por asignaturas";
        nombreTitulo2 = "Politécnico: " + centro.getNombCentro();

        encabezadoProfAsignaturas();

        datosProfAsignaturas(centro.getComposicionPersonal(), response, fila1Elementos);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los profesores por asignaturas de un municipio.
     *
     * @param cp       Composición del personal municipal.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_ProfAsignaturaMcpal(ComposicionPersonal cp, Municipio mcpio, HttpServletResponse response) {
        nombreExcel = nombreHoja = "ProfAsignaturaMcpal";
        nombreTitulo1 = "Profesores fijos y contratados por asignaturas";
        nombreTitulo2 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        encabezadoProfAsignaturas();

        datosProfAsignaturas(cp, response, fila1Elementos);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los profesores por asignaturas de una provincia.
     *
     * @param cp       Composición del personal provincial.
     * @param prov     Provincia.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_ProfAsignaturaProv(ComposicionPersonal cp, Provincia prov, HttpServletResponse response) {
        nombreExcel = nombreHoja = "ProfAsignaturaProv";
        nombreTitulo1 = "Profesores fijos y contratados por asignaturas";
        nombreTitulo2 = "Provincia: " + prov.getNombProvincia();

        encabezadoProfAsignaturas();

        datosProfAsignaturas(cp, response, fila1Elementos);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los profesores por asignaturas del país.
     *
     * @param cp       Composición del personal provincial.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_ProfAsignaturaNac(ComposicionPersonal cp, HttpServletResponse response) {
        nombreExcel = nombreHoja = "ProfAsignaturaNac";
        nombreTitulo1 = "Profesores fijos y contratados por asignaturas";
        nombreTitulo2 = "Resumen Nacional";

        encabezadoProfAsignaturas();

        datosProfAsignaturas(cp, response, fila1Elementos);
    }

    private void encabezadoProfAsignaturas() {
        this.titulo = new String[]{"Asignaturas", "Total Fijos", "No Titulados", "No Titul. Estud", "Total Contratados", "No Profesionales", "No Prof. Estud"};
        crearEncabezadoTabla();
        hoja.setColumnWidth(0, 10000);
        hoja.setColumnWidth(1, 4000);
        hoja.setColumnWidth(2, 4000);
        hoja.setColumnWidth(3, 4000);
        hoja.setColumnWidth(4, 4000);
        hoja.setColumnWidth(5, 4000);
        hoja.setColumnWidth(6, 4000);

        int pos = fila1Elementos - 1;
        fila = hoja.createRow(pos - 1);
        fila.setHeight((short) 350);
        CellRangeAddress region = CellRangeAddress.valueOf("A" + pos + ":A" + (pos + 1));
        hoja.addMergedRegion(region);

        region = CellRangeAddress.valueOf("B" + pos + ":D" + pos);
        hoja.addMergedRegion(region);
        region = CellRangeAddress.valueOf("E" + pos + ":G" + pos);
        hoja.addMergedRegion(region);

        HSSFCell celda = fila.createCell(0);
        celda.setCellValue("Asignaturas");
        estiloEncabezado(celda, 350);

        for (int i = 1; i < 7; i++) {
            celda = fila.createCell(i);
            if (i == 1) {
                celda.setCellValue("Profesores Fijos");
            } else if (i == 4) {
                celda.setCellValue("Profesores Contratados");
            }
            estiloFicha(celda);
        }

        fila = hoja.createRow(pos++);

        for (int i = 0; i < titulo.length; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(titulo[i]);
            estiloEncabezado(celda, 350);
        }
    }

    private void datosProfAsignaturas(ComposicionPersonal cp, HttpServletResponse response, int pos) {
        ProfesorAsignatura pf = cp.getProfesoresFijos();
        ProfesorAsignatura pc = cp.getProfesoresContratados();
        totalF = noTitulado = noTituladoEstudiando = totalC = noProfesional = noProfesionalEstudiando = 0;

        datosProfAsignaturas(pos++, "Español", (ProfesorFijo) pf.getProfesorEspanol(), (ProfesorContrato) pc.getProfesorEspanol());
        datosProfAsignaturas(pos++, "Literatura", (ProfesorFijo) pf.getProfesorLiteratura(), (ProfesorContrato) pc.getProfesorLiteratura());
        datosProfAsignaturas(pos++, "Matemática", (ProfesorFijo) pf.getProfesorMatematica(), (ProfesorContrato) pc.getProfesorMatematica());
        datosProfAsignaturas(pos++, "Física", (ProfesorFijo) pf.getProfesorFisica(), (ProfesorContrato) pc.getProfesorFisica());
        datosProfAsignaturas(pos++, "Química", (ProfesorFijo) pf.getProfesorQuimica(), (ProfesorContrato) pc.getProfesorQuimica());
        datosProfAsignaturas(pos++, "Biología", (ProfesorFijo) pf.getProfesorBiologia(), (ProfesorContrato) pc.getProfesorBiologia());
        datosProfAsignaturas(pos++, "Historia", (ProfesorFijo) pf.getProfesorHistoria(), (ProfesorContrato) pc.getProfesorHistoria());
        datosProfAsignaturas(pos++, "Idioma Extranjero", (ProfesorFijo) pf.getProfesorIngles(), (ProfesorContrato) pc.getProfesorIngles());
        datosProfAsignaturas(pos++, "Educación Física", (ProfesorFijo) pf.getProfesorEducacionFisica(), (ProfesorContrato) pc.getProfesorEducacionFisica());
        datosProfAsignaturas(pos++, "Educación Artística", (ProfesorFijo) pf.getProfesorEducacionArtistica(), (ProfesorContrato) pc.getProfesorEducacionArtistica());
        datosProfAsignaturas(pos++, "Marxismo Leninismo", (ProfesorFijo) pf.getProfesorMarxismo(), (ProfesorContrato) pc.getProfesorMarxismo());
        datosProfAsignaturas(pos++, "Fundamento de los Conocimientos Políticos", (ProfesorFijo) pf.getProfesorFundamento(), (ProfesorContrato) pc.getProfesorFundamento());
        datosProfAsignaturas(pos++, "Computación", (ProfesorFijo) pf.getProfesorComputacion(), (ProfesorContrato) pc.getProfesorComputacion());
        datosProfAsignaturas(pos++, "Preparación Militar", (ProfesorFijo) pf.getProfesorMilitar(), (ProfesorContrato) pc.getProfesorMilitar());
        datosProfAsignaturas(pos++, "Enseñanza Práctica", (ProfesorFijo) pf.getProfesorPractica(), (ProfesorContrato) pc.getProfesorPractica());
        datosProfAsignaturas(pos++, "Básicas Específicas", (ProfesorFijo) pf.getProfesorBasica(), (ProfesorContrato) pc.getProfesorBasica());
        datosProfAsignaturas(pos++, "Ejercicio de la Profesión", (ProfesorFijo) pf.getProfesorEjercicio(), (ProfesorContrato) pc.getProfesorEjercicio());
        datosProfAsignaturas(pos++, "Instructor", (ProfesorFijo) pf.getProfesorInstructor(), (ProfesorContrato) pc.getProfesorInstructor());

        fila = hoja.createRow(pos);
        HSSFCell celda;
        this.contenido = new String[]{"Total", valorCelda(totalF, 0), valorCelda(noTitulado, 0), valorCelda(noTituladoEstudiando, 0), valorCelda(totalC, 0), valorCelda(noProfesional, 0), valorCelda(noProfesionalEstudiando, 0)};
        for (int i = 0; i < contenido.length; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(contenido[i]);
            estiloEncabezado(celda, 350);
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void datosProfAsignaturas(int pos, String asig, ProfesorFijo f, ProfesorContrato c) {
        fila = hoja.createRow(pos);
        int fijos = f.getTotalProfesores(), noTitulado = f.getNoTitulados(), noTituladoEstudiando = f.getNoTituladosEstudiando();
        int contratos = c.getTotalProfesores(), noProfesional = c.getNoProfesionales(), noProfesionalEstudiando = c.getNoProfesionalesEstudiando();

        this.contenido = new String[]{asig, valorCelda(fijos, 1), valorCelda(noTitulado, 1), valorCelda(noTituladoEstudiando, 1), valorCelda(contratos, 1), valorCelda(noProfesional, 1), valorCelda(noProfesionalEstudiando, 1)};
        crearCuerpoTabla();

        this.totalF += fijos;
        this.noTitulado += noTitulado;
        this.noTituladoEstudiando += noTituladoEstudiando;
        this.totalC += contratos;
        this.noProfesional += noProfesional;
        this.noProfesionalEstudiando += noProfesionalEstudiando;
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los rangos de edades de un centro ETP.
     *
     * @param centro   Centros ETP que sus rangos de edades serán el contenido el cuerpo de la tabla.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_RangoEdad(Centro centro, HttpServletResponse response) {
        nombreExcel = nombreHoja = "RangosEdades";
        nombreTitulo1 = "Matrícula por edades, sexo y tipo de curso";
        nombreTitulo2 = "Politécnico: " + centro.getNombCentro();

        datosRangoEdad(centro.getRangosEdades());

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los rangos de edades de un municipio.
     *
     * @param rango    Rango de edad que será contenido en el cuerpo de la tabla.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_RangoEdadMcpal(RangoEdad rango, Municipio mcpio, HttpServletResponse response) {
        nombreExcel = nombreHoja = "RangosEdadesMcpal";
        nombreTitulo1 = "Matrícula por edades, sexo y tipo de curso";
        nombreTitulo2 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        datosRangoEdad(rango);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los rangos de edades de una provincia.
     *
     * @param rango    Rango de edad que será contenido en el cuerpo de la tabla.
     * @param prov     Provincia.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_RangoEdadProv(RangoEdad rango, Provincia prov, HttpServletResponse response) {
        nombreExcel = nombreHoja = "RangosEdadesProv";
        nombreTitulo1 = "Matrícula por edades, sexo y tipo de curso";
        nombreTitulo2 = "Provincia: " + prov.getNombProvincia();

        datosRangoEdad(rango);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de los rangos de edades del país.
     *
     * @param rango    Rango de edad que será contenido en el cuerpo de la tabla.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_RangoEdadNac(RangoEdad rango, HttpServletResponse response) {
        nombreExcel = nombreHoja = "RangosEdadesNac";
        nombreTitulo1 = "Matrícula por edades, sexo y tipo de curso";
        nombreTitulo2 = "Resumen Nacional";

        datosRangoEdad(rango);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha de un centro.
     *
     * @param centro   Centros ETP que será el contenido del cuerpo de la tabla.
     * @param fichas   Elementos de la ficha.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaCentro(Centro centro, List<Ficha> fichas, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaCentro";
        nombreTitulo1 = "Ficha: Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Politécnico: " + centro.getNombCentro();

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha de un municipio.
     *
     * @param fichas   Elementos de la ficha.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaMcpal(List<Ficha> fichas, Municipio mcpio, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaMcpal";
        nombreTitulo1 = "Ficha:  Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha de una provincia.
     *
     * @param fichas   Elementos de la ficha.
     * @param nomProv  Nombre de la provncia.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaProv(List<Ficha> fichas, String nomProv, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaProv";
        nombreTitulo1 = "Ficha:  Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Provincia: " + nomProv;

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha del país.
     *
     * @param fichas   Elementos de la ficha.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaNac(List<Ficha> fichas, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaNac";
        nombreTitulo1 = "Ficha:  Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Resumen Nacional";

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha turquino de un municipio.
     *
     * @param fichas   Elementos de la ficha.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaTurquinoMcpal(List<Ficha> fichas, Municipio mcpio, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaTurquinoMcpal";
        nombreTitulo1 = "Ficha Turquino:  Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha turquino de una provincia.
     *
     * @param fichas   Elementos de la ficha.
     * @param prov     Provncia.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaTurquinoProv(List<Ficha> fichas, Provincia prov, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaTurquinoProv";
        nombreTitulo1 = "Ficha Turquino:  Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Provincia: " + prov.getNombProvincia();

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel de la ficha turquino del país.
     *
     * @param fichas   Elementos de la ficha.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_FichaTurquinoNac(List<Ficha> fichas, HttpServletResponse response) {
        nombreExcel = nombreHoja = "FichaTurquinoNac";
        nombreTitulo1 = "Ficha Turquino:  Matrículas, nuevos ingresos y personal docente";
        nombreTitulo2 = "Resumen Nacional";

        datosficha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel del personal de los centros de un municipio.
     *
     * @param lst_centros Centros
     * @param mcpio       Municipio.
     * @param response    Respuesta.
     */
    public void generarDocumentoExcel_PersonalMcpal(List<Centro> lst_centros, Municipio mcpio, HttpServletResponse response) {
        nombreExcel = nombreHoja = "PersonalMcpal";
        nombreTitulo1 = "Composición general del personal";
        nombreTitulo2 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        String[] titulo = {"Centro", "Total", "Hembras", "F.Aula", "Total", "Hembras", "Total", "Bibliot", "I.Arte", "Psicopdg", "Total", "Profesor", "O.Doct", "N.Doct"};

        anchosColumnas(titulo);
        hoja.setColumnWidth(9, 3000);
        hoja.setColumnWidth(10, 3000);
        hoja.setColumnWidth(11, 3000);
        hoja.setColumnWidth(12, 3000);
        hoja.setColumnWidth(13, 3000);

        encabezadoPersonal();

        Centro cen;
        ComposicionPersonal cp;
        int longitud = lst_centros.size();
        int[] total = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < longitud; i++) {
            cen = lst_centros.get(i);
            cp = cen.getComposicionPersonal();

            datosPersonal(cp, total);
            String[] elementos = new String[]{cen.getNombCentro(), valorCelda(cp.getTotalDocentes(), 1), valorCelda(cp.getTotalDocentesHembras(), 1), valorCelda(cp.getDocentes().getTotal(), 1), valorCelda(cp.getNoDocentes().getTotal(), 1), valorCelda(cp.getNoDocentes().getHembras(), 1), valorCelda(cp.getOtrosDocentesTotal(), 1), valorCelda(cp.getBibliotecarios().getTotal(), 1), valorCelda(cp.getInstructoresArte().getTotal(), 1), valorCelda(cp.getPsicopedagogos().getTotal(), 1), valorCelda(cp.getTotalInactivos(), 1), valorCelda(cp.getNoDocentes().getHembras(), 1), valorCelda(cp.getOtrosDocentesTotal(), 1), valorCelda(cp.getBibliotecarios().getTotal(), 1), valorCelda(cp.getInstructoresArte().getTotal(), 1), valorCelda(cp.getPsicopedagogos().getTotal(), 1), valorCelda(cp.getTotalInactivos(), 1), valorCelda(cp.getTotalProfesoresInactivos(), 1), valorCelda(cp.getOtrosDocentesInactivos(), 1), valorCelda(cp.getNoDocentesInactivos(), 1)};
            formatoCeldaPersonal(i, elementos);
        }

        fila = hoja.createRow(longitud + fila1Elementos);
        this.contenido = new String[]{"Total Municipal", valorCelda(total[0], 0), valorCelda(total[1], 0), valorCelda(total[2], 0), valorCelda(total[3], 0), valorCelda(total[4], 0), valorCelda(total[5], 0), valorCelda(total[6], 0), valorCelda(total[7], 0), valorCelda(total[8], 0), valorCelda(total[9], 0), valorCelda(total[10], 0), valorCelda(total[11], 0), valorCelda(total[12], 0)};
        crearCuerpoTabla();

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formatoCeldaPersonal(int i, String[] elementos) {
        HSSFCell celda;
        this.contenido = elementos;

        fila = hoja.createRow(i + fila1Elementos);
        fila.setHeight((short) 300);

        for (int j = 0; j < cantColumnas; j++) {
            celda = fila.createCell(j);
            orientacionCelda(celda, j);
        }
    }

    private void datosPersonal(ComposicionPersonal cp, int[] total) {
        int valor;
        valor = cp.getTotalDocentes();
        total[0] += valor;
        valor = cp.getTotalDocentesHembras();
        total[1] += valor;
        valor = cp.getDocentes().getTotal();
        total[2] += valor;
        Personal ndoc = cp.getNoDocentes();
        valor = ndoc.getTotal();
        total[3] += valor;
        valor = ndoc.getHembras();
        total[4] += valor;
        valor = cp.getOtrosDocentesTotal();
        total[5] += valor;
        valor = cp.getBibliotecarios().getTotal();
        total[6] += valor;
        valor = cp.getInstructoresArte().getTotal();
        total[7] += valor;
        valor = cp.getPsicopedagogos().getTotal();
        total[8] += valor;
        valor = cp.getTotalInactivos();
        total[9] += valor;
        valor = cp.getTotalProfesoresInactivos();
        total[10] += valor;
        valor = cp.getOtrosDocentesInactivos();
        total[11] += valor;
        valor = cp.getNoDocentesInactivos();
        total[12] += valor;
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel del personal de una provincia.
     *
     * @param prov     Provincia.
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_PersonalProv(Provincia prov, HttpServletResponse response) {
        nombreExcel = nombreHoja = "PersonalProv";
        nombreTitulo1 = "Composición general del personal";
        nombreTitulo2 = "Provincia: " + prov.getNombProvincia();

        String[] titulo = {"Municipios", "Total", "Hembras", "F.Aula", "Total", "Hembras", "Total", "Bibliot", "I.Arte", "Psicopdg", "Total", "Profesor", "O.Doct", "N.Doct"};

        anchosColumnas(titulo);
        hoja.setColumnWidth(9, 3000);
        hoja.setColumnWidth(10, 3000);
        hoja.setColumnWidth(11, 3000);
        hoja.setColumnWidth(12, 3000);
        hoja.setColumnWidth(13, 3000);

        encabezadoPersonal();

        ComposicionPersonal cp;
        List<Municipio> mcpios = prov.getMcpios();
        int longitud = mcpios.size();
        Municipio mc;
        int[] total = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < longitud; i++) {
            mc = mcpios.get(i);

            cp = GenerarDocumentoPDFImpl.getDatosPersonalProv(mc, resumenes);
            datosPersonal(cp, total);
            String[] elementos = new String[]{mc.getNombMunicipio(), valorCelda(cp.getTotalDocentes(), 1), valorCelda(cp.getTotalDocentesHembras(), 1), valorCelda(cp.getDocentes().getTotal(), 1), valorCelda(cp.getNoDocentes().getTotal(), 1), valorCelda(cp.getNoDocentes().getHembras(), 1), valorCelda(cp.getOtrosDocentesTotal(), 1), valorCelda(cp.getBibliotecarios().getTotal(), 1), valorCelda(cp.getInstructoresArte().getTotal(), 1), valorCelda(cp.getPsicopedagogos().getTotal(), 1), valorCelda(cp.getTotalInactivos(), 1), valorCelda(cp.getNoDocentes().getHembras(), 1), valorCelda(cp.getOtrosDocentesTotal(), 1), valorCelda(cp.getBibliotecarios().getTotal(), 1), valorCelda(cp.getInstructoresArte().getTotal(), 1), valorCelda(cp.getPsicopedagogos().getTotal(), 1), valorCelda(cp.getTotalInactivos(), 1), valorCelda(cp.getTotalProfesoresInactivos(), 1), valorCelda(cp.getOtrosDocentesInactivos(), 1), valorCelda(cp.getNoDocentesInactivos(), 1)};
            formatoCeldaPersonal(i, elementos);
        }

        fila = hoja.createRow(longitud + fila1Elementos);
        this.contenido = new String[]{"Total Provincial", valorCelda(total[0], 0), valorCelda(total[1], 0), valorCelda(total[2], 0), valorCelda(total[3], 0), valorCelda(total[4], 0), valorCelda(total[5], 0), valorCelda(total[6], 0), valorCelda(total[7], 0), valorCelda(total[8], 0), valorCelda(total[9], 0), valorCelda(total[10], 0), valorCelda(total[11], 0), valorCelda(total[12], 0)};
        crearCuerpoTabla();

        try {
            cuadroDialogo(response);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento Excel del personal nacional.
     *
     * @param response Respuesta.
     */
    public void generarDocumentoExcel_PersonalNac(HttpServletResponse response) {
        nombreExcel = nombreHoja = "PersonalNac";
        nombreTitulo1 = "Composición general del personal";
        nombreTitulo2 = "Resumen Nacional";

        String[] titulo = {"Provincias", "Total", "Hembras", "F.Aula", "Total", "Hembras", "Total", "Bibliot", "I.Arte", "Psicopdg", "Total", "Profesor", "O.Doct", "N.Doct"};

        anchosColumnas(titulo);
        hoja.setColumnWidth(9, 3000);
        hoja.setColumnWidth(10, 3000);
        hoja.setColumnWidth(11, 3000);
        hoja.setColumnWidth(12, 3000);
        hoja.setColumnWidth(13, 3000);

        encabezadoPersonal();

        ComposicionPersonal cp;
        List<Provincia> prov = provincias.listar();
        int longitud = prov.size();
        int[] total = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < prov.size(); i++) {

            cp = GenerarDocumentoPDFImpl.getDatosPersonalNac(prov, i, resumenes);
            datosPersonal(cp, total);
            String[] elementos = new String[]{prov.get(i).getNombProvincia(), valorCelda(cp.getTotalDocentes(), 1), valorCelda(cp.getTotalDocentesHembras(), 1), valorCelda(cp.getDocentes().getTotal(), 1), valorCelda(cp.getNoDocentes().getTotal(), 1), valorCelda(cp.getNoDocentes().getHembras(), 1), valorCelda(cp.getOtrosDocentesTotal(), 1), valorCelda(cp.getBibliotecarios().getTotal(), 1), valorCelda(cp.getInstructoresArte().getTotal(), 1), valorCelda(cp.getPsicopedagogos().getTotal(), 1), valorCelda(cp.getTotalInactivos(), 1), valorCelda(cp.getNoDocentes().getHembras(), 1), valorCelda(cp.getOtrosDocentesTotal(), 1), valorCelda(cp.getBibliotecarios().getTotal(), 1), valorCelda(cp.getInstructoresArte().getTotal(), 1), valorCelda(cp.getPsicopedagogos().getTotal(), 1), valorCelda(cp.getTotalInactivos(), 1), valorCelda(cp.getTotalProfesoresInactivos(), 1), valorCelda(cp.getOtrosDocentesInactivos(), 1), valorCelda(cp.getNoDocentesInactivos(), 1)};
            formatoCeldaPersonal(i, elementos);
        }

        fila = hoja.createRow(longitud + fila1Elementos);
        this.contenido = new String[]{"Total Nacional", valorCelda(total[0], 0), valorCelda(total[1], 0), valorCelda(total[2], 0), valorCelda(total[3], 0), valorCelda(total[4], 0), valorCelda(total[5], 0), valorCelda(total[6], 0), valorCelda(total[7], 0), valorCelda(total[8], 0), valorCelda(total[9], 0), valorCelda(total[10], 0), valorCelda(total[11], 0), valorCelda(total[12], 0)};
        crearCuerpoTabla();

        try {
            cuadroDialogo(response);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private void anchosColumnas(String[] titulo) {
        this.titulo = titulo;

        crearEncabezadoTabla();

        hoja.setColumnWidth(0, 7000);
        hoja.setColumnWidth(1, 3000);
        hoja.setColumnWidth(2, 3000);
        hoja.setColumnWidth(3, 3000);
        hoja.setColumnWidth(4, 3000);
        hoja.setColumnWidth(5, 3000);
        hoja.setColumnWidth(6, 3000);
        hoja.setColumnWidth(7, 3000);
        hoja.setColumnWidth(8, 3000);
    }

    private void orientacionCelda(HSSFCell celda, int j) {
        celda.setCellValue(contenido[j]);
        if (contenido[j].length() > 0) {
            char car = contenido[j].charAt(0);
            if (car >= '0' && car <= '9') {
                estiloCeldaCentrado(celda);
            } else {
                estiloCeldaIzquierda(celda);
            }
        } else {
            estiloCeldaIzquierda(celda);
        }
    }

    @SuppressWarnings("deprecation")
    private void estiloEncabezado(HSSFCell celda, int altoFila) {
        fila.setHeight((short) altoFila);
        estiloCeldasTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        estiloCeldasTitulo.setVerticalAlignment((short) 60);
        estiloCeldasTitulo.setFillForegroundColor(HSSFColor.YELLOW.index);
        estiloCeldasTitulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCeldasTitulo.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estiloCeldasTitulo.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        estiloCeldasTitulo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estiloCeldasTitulo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        celda.setCellStyle(estiloCeldasTitulo);
    }

    @SuppressWarnings("deprecation")
    private void estiloCeldaIzquierda(HSSFCell celda) {
        estiloCeldasElementosIzq.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        propiedadesEstilo(estiloCeldasElementosIzq, celda);
    }

    @SuppressWarnings("deprecation")
    private void estiloCeldaCentrado(HSSFCell celda) {
        estiloCeldasElementosCen.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        propiedadesEstilo(estiloCeldasElementosCen, celda);
    }

    @SuppressWarnings("deprecation")
    private void estiloFilaCombinadaRango(HSSFCell celda) {
        HSSFCellStyle estilo = libro.createCellStyle();

        estilo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        estilo.setVerticalAlignment((short) 60);
        estilo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        celda.setCellStyle(estilo);
    }

    @SuppressWarnings("deprecation")
    private void propiedadesEstilo(HSSFCellStyle estilo, HSSFCell celda) {
        estilo.setVerticalAlignment((short) 60);
        estilo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        estilo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        celda.setCellStyle(estilo);
    }

    @SuppressWarnings("deprecation")
    private void estiloFicha(HSSFCell celda) {
        HSSFCellStyle estilo = libro.createCellStyle();

        estilo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        estilo.setVerticalAlignment((short) 60);
        estilo.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estilo.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        celda.setCellStyle(estilo);
    }

    private String valorCelda(int num, int pos) {
        if (num == 0 && pos != 0) {
            return "";
        }
        return String.valueOf(num);
    }

    private String valorCelda(float num, int pos) {
        if (num == 0 && pos != 0) {
            return "";
        }
        return String.valueOf(num);
    }

    private void datosRangoEdad(RangoEdad rango) {
        encabezadoRango();

        int pos = fila1Elementos - 1;

        CellRangeAddress region = CellRangeAddress.valueOf("A" + (pos + 1) + ":A" + (pos + 2));
        hoja.addMergedRegion(region);
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Total", String.valueOf(rango.getDiurno().getTotalMenos15() + rango.getCpt().getTotalMenos15()), valorCelda(rango.getDiurno().getTotalMenos15(), 1), valorCelda(rango.getCpt().getTotalMenos15(), 1)};
        crearCuerpoRangoEdad("Menores de 15 años");
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Hembras", String.valueOf(rango.getDiurno().getHembrasMenos15() + rango.getCpt().getHembrasMenos15()), valorCelda(rango.getDiurno().getHembrasMenos15(), 1), valorCelda(rango.getCpt().getHembrasMenos15(), 1)};
        crearCuerpoRangoEdad(" ");

        region = CellRangeAddress.valueOf("A" + (pos + 1) + ":A" + (pos + 2));
        hoja.addMergedRegion(region);
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Total", String.valueOf(rango.getDiurno().getTotalEntre15_16() + rango.getCpt().getTotalEntre15_16()), valorCelda(rango.getDiurno().getTotalEntre15_16(), 1), valorCelda(rango.getCpt().getTotalEntre15_16(), 1)};
        crearCuerpoRangoEdad("Entre 15 y 16 años");
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Hembras", String.valueOf(rango.getDiurno().getHembrasEntre15_16() + rango.getCpt().getHembrasEntre15_16()), valorCelda(rango.getDiurno().getHembrasEntre15_16(), 1), valorCelda(rango.getCpt().getHembrasEntre15_16(), 1)};
        crearCuerpoRangoEdad(" ");

        region = CellRangeAddress.valueOf("A" + (pos + 1) + ":A" + (pos + 2));
        hoja.addMergedRegion(region);
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Total", String.valueOf(rango.getDiurno().getTotalMayores16() + rango.getCpt().getTotalMayores16()), valorCelda(rango.getDiurno().getTotalMayores16(), 1), valorCelda(rango.getCpt().getTotalMayores16(), 1)};
        crearCuerpoRangoEdad("Mayores de 16 años");
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Hembras", String.valueOf(rango.getDiurno().getHembrasMayores16() + rango.getCpt().getHembrasMayores16()), valorCelda(rango.getDiurno().getHembrasMayores16(), 1), valorCelda(rango.getCpt().getHembrasMayores16(), 1)};
        crearCuerpoRangoEdad(" ");

        region = CellRangeAddress.valueOf("A" + (pos + 1) + ":A" + (pos + 2));
        hoja.addMergedRegion(region);
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Total", String.valueOf(rango.getDiurno().getMatriculaRangoTotal() + rango.getCpt().getMatriculaRangoTotal()), String.valueOf(rango.getDiurno().getMatriculaRangoTotal()), String.valueOf(rango.getCpt().getMatriculaRangoTotal())};
        crearCuerpoRangoEdad("Total");
        fila = hoja.createRow(pos++);
        this.contenido = new String[]{"Hembras", String.valueOf(rango.getDiurno().getMatriculaRangoTotalHembras() + rango.getCpt().getMatriculaRangoTotalHembras()), String.valueOf(rango.getDiurno().getMatriculaRangoTotalHembras()), String.valueOf(rango.getCpt().getMatriculaRangoTotalHembras())};
        crearCuerpoRangoEdad(" ");
    }

    public void crearCuerpoRangoEdad(String texto) {
        fila.setHeight((short) 350);

        HSSFCell celda;

        for (int j = 0; j < 5; j++) {
            celda = fila.createCell(j);

            if (j == 0) {
                celda.setCellValue(texto);
                estiloFilaCombinadaRango(celda);
                continue;
            }

            celda.setCellValue(contenido[j - 1]);
            if (contenido[j - 1].length() > 0) {
                char car = contenido[j - 1].charAt(0);
                if (car >= '0' && car <= '9') {
                    estiloCeldaCentrado(celda);
                } else {
                    estiloCeldaIzquierda(celda);
                }
            } else {
                estiloCeldaIzquierda(celda);
            }
        }
    }

    private void datosficha(List<Ficha> fichas) {
        String[] titulo = {"Educación", "Total", "De ellos: Hembras", "Internos", "Seminternos", "Nuevos Ingresos", "Personal Docente", "De ellos: Hembras", "Frente Aulas", "Relación Alumnos/Profesor"};

        formatoTablaFicha(titulo);
        encabezadoFicha();

        Ficha f;
        String[] elementos;

        for (int i = 0; i < fichas.size(); i++) {

            f = fichas.get(i);

            if (i == 0) {
                elementos = new String[]{f.getConcepto(), valorCelda(f.getTotal(), 0), valorCelda(f.getHembras(), 0), valorCelda(f.getInternos(), 0), valorCelda(f.getSeminternos(), 0), valorCelda(f.getNuevosIngresos(), 0), valorCelda(f.getDocentesTotal(), 0), valorCelda(f.getDocentesHembras(), 0), valorCelda(f.getFrenteAula(), 0), valorCelda(f.getRelacionAlumProf(), 0)};
            } else if (i == 1) {
                elementos = new String[]{f.getConcepto(), valorCelda(f.getTotal(), 0), valorCelda(f.getHembras(), 0), valorCelda(f.getInternos(), 0), valorCelda(f.getSeminternos(), 0), valorCelda(f.getNuevosIngresos(), 0), valorCelda(f.getDocentesTotal(), 1), valorCelda(f.getDocentesHembras(), 1), valorCelda(f.getFrenteAula(), 1), valorCelda(f.getRelacionAlumProf(), 1)};
            } else if (i == 7) {
                elementos = new String[]{f.getConcepto(), valorCelda(f.getTotal(), 0), valorCelda(f.getHembras(), 0), valorCelda(f.getInternos(), 1), valorCelda(f.getSeminternos(), 1), valorCelda(f.getNuevosIngresos(), 0), valorCelda(f.getDocentesTotal(), 1), valorCelda(f.getDocentesHembras(), 1), valorCelda(f.getFrenteAula(), 1), valorCelda(f.getRelacionAlumProf(), 1)};
            } else {
                elementos = new String[]{f.getConcepto(), valorCelda(f.getTotal(), 1), valorCelda(f.getHembras(), 1), valorCelda(f.getInternos(), 1), valorCelda(f.getSeminternos(), 1), valorCelda(f.getNuevosIngresos(), 1), valorCelda(f.getDocentesTotal(), 1), valorCelda(f.getDocentesHembras(), 1), valorCelda(f.getFrenteAula(), 1), valorCelda(f.getRelacionAlumProf(), 1)};
            }
            elementosTablaFicha(i, elementos);
        }
    }

    private void elementosTablaFicha(int i, String[] elementos) {
        HSSFCell celda;
        this.contenido = elementos;

        fila = hoja.createRow(i + fila1Elementos);

        if (i > 1 && i < 7) {
            fila.setHeight((short) 300);

            for (int j = 0; j < cantColumnas; j++) {
                celda = fila.createCell(j);
                celda.setCellValue(contenido[j]);
                estiloCeldaCentrado(celda);
            }
        } else {
            crearCuerpoTabla();
        }
    }

    private void encabezadoPersonal() {
        int pos = fila1Elementos - 1;
        fila = hoja.createRow(pos - 1);
        fila.setHeight((short) 350);
        CellRangeAddress region = CellRangeAddress.valueOf("B" + pos + ":D" + pos);
        hoja.addMergedRegion(region);
        region = CellRangeAddress.valueOf("E" + pos + ":F" + pos);
        hoja.addMergedRegion(region);
        region = CellRangeAddress.valueOf("G" + pos + ":J" + pos);
        hoja.addMergedRegion(region);
        region = CellRangeAddress.valueOf("K" + pos + ":N" + pos);
        hoja.addMergedRegion(region);

        HSSFCell celda;
        for (int i = 1; i < 14; i++) {
            celda = fila.createCell(i);
            if (i == 1) {
                celda.setCellValue("Docentes");
            } else if (i == 4) {
                celda.setCellValue("No Docentes");
            } else if (i == 6) {
                celda.setCellValue("Otros Docentes");
            } else if (i == 10) {
                celda.setCellValue("Inactivos");
            }
            estiloFicha(celda);
        }
    }

    private void encabezadoFicha() {
        int pos = fila1Elementos - 1;
        fila = hoja.createRow(pos - 1);
        fila.setHeight((short) 350);
        CellRangeAddress region = CellRangeAddress.valueOf("B" + pos + ":F" + pos);
        hoja.addMergedRegion(region);
        region = CellRangeAddress.valueOf("G" + pos + ":I" + pos);
        hoja.addMergedRegion(region);

        HSSFCell celda;
        for (int i = 1; i < 9; i++) {
            celda = fila.createCell(i);
            if (i == 1) {
                celda.setCellValue("Matrícula");
            } else if (i == 6) {
                celda.setCellValue("Personal Docente");
            }
            estiloFicha(celda);
        }

        fila = hoja.createRow(pos++);

        for (int i = 0; i < titulo.length; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(titulo[i]);
            estiloEncabezado(celda, 600);
        }
    }

    private void formatoTablaFicha(String[] titulo) {
        anchosColumnas(titulo);
        hoja.setColumnWidth(9, 4000);
    }

    private void encabezadoRango() {
        this.titulo = new String[]{};
        crearEncabezadoTabla();
        hoja.setColumnWidth(0, 8000);
        hoja.setColumnWidth(1, 6000);
        hoja.setColumnWidth(2, 6000);
        hoja.setColumnWidth(3, 6000);
        hoja.setColumnWidth(4, 6000);

        int pos = fila1Elementos - 1;
        fila = hoja.createRow(pos - 1);
        fila.setHeight((short) 350);
        CellRangeAddress region = CellRangeAddress.valueOf("A" + pos + ":B" + pos);
        hoja.addMergedRegion(region);

        HSSFCell celda;
        celda = fila.createCell(0);
        celda.setCellValue("Rangos");
        estiloEncabezado(celda, 350);
        celda = fila.createCell(1);
        estiloEncabezado(celda, 350);
        celda = fila.createCell(2);
        celda.setCellValue("Total ETP");
        estiloEncabezado(celda, 350);
        celda = fila.createCell(3);
        celda.setCellValue("Curso Diurno");
        estiloEncabezado(celda, 350);
        celda = fila.createCell(4);
        celda.setCellValue("Curso V/Nocturno");
        estiloEncabezado(celda, 350);
    }
}