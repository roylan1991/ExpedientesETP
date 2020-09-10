package com.roylan.expedientes_etp.exportacion;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.roylan.expedientes_etp.database.entities.*;
import com.roylan.expedientes_etp.database.services.GestionarProvincia;
import com.roylan.expedientes_etp.database.services.GestionarResumen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase gestiona la exportación de la información a un documento PDF.
 *
 * @author Roylan Bressler
 * @since 1.0
 */

@Service
public class GenerarDocumentoPDFImpl implements IGenerarDocumento {

    private Document document;

    private PdfPTable table;

    private Font fuenteEncabezado, fuenteCuerpo;

    private File ruta;

    private String subTituloTabla, subTituloTabla1;

    private List<PdfPCell> elementos;

    private List<Paragraph> titulos;

    private int totalF, noTitulado, noTituladoEstudiando, totalC, noProfesional, noProfesionalEstudiando;

    @Autowired
    private GestionarProvincia provincias;

    @Autowired
    private GestionarResumen resumenes;

    public GenerarDocumentoPDFImpl() {
        fuenteEncabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.BLACK);
        fuenteCuerpo = FontFactory.getFont("Arial", 8, Color.BLACK);
    }

    /**
     * Esta funcionalidad configura el formato que tendrá el documento PDF.
     *
     * @param nombrePDF Nombre que tendrá el título(contenido) y el nombre del documento final exportado.
     * @throws DocumentException     Si el documento no se encuentra.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     */
    private void propiedadesPDF(String nombrePDF) throws DocumentException, FileNotFoundException {
        // margenIzq, margenDer, margenSup, margenInf
        document = new Document(PageSize.LETTER.rotate(), 10f, 10f, 25, 25);

        ruta = new File(nombrePDF + ".pdf");

        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        Font fuenteTitulos = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.BLACK);

        Paragraph tituloS = new Paragraph("Información estadística de inicio de curso", fuenteTitulos);
        tituloS.setAlignment(Element.ALIGN_CENTER);

        Paragraph titulo = new Paragraph(subTituloTabla, fuenteTitulos);
        Paragraph subtitulo = new Paragraph(subTituloTabla1, fuenteTitulos);

        tituloS.setSpacingBefore(15);
        titulo.setSpacingBefore(25);

        document.add(tituloS);
        document.addTitle(tituloS.getContent());
        document.add(titulo);
        document.addTitle(titulo.getContent());
        document.add(subtitulo);
        document.addTitle(subtitulo.getContent());

        document.addAuthor("Roylan A. Bressler Camps");
        document.addCreator("iText & Spring Framework");
    }

    /**
     * Esta funcionalidad configura el estilo que tendrá cada elemento(celda) del título de la tabla(1ra fila).
     */
    public void crearEncabezadoTabla() {

        // Para que el texto ocupe el 100% del espacio de la celda
        table.setWidthPercentage(100);

        if (!subTituloTabla1.equals(" ")) {
            table.setSpacingBefore(15);
        }

        for (Paragraph p : titulos) {
            PdfPCell obj = new PdfPCell(p);

            obj.setBorderColor(Color.BLACK);
            obj.setHorizontalAlignment(Element.ALIGN_CENTER);
            obj.setVerticalAlignment(Element.ALIGN_MIDDLE);
            obj.setBackgroundColor(Color.YELLOW);
            obj.setExtraParagraphSpace(10f);
            obj.setPaddingTop(10);
            table.addCell(obj);
        }
    }

    /**
     * Esta funcionalidad configura el estilo que tendrá cada elemento(celda) del cuerpo de la tabla.
     */
    public void crearCuerpoTabla() {

        for (PdfPCell obj : elementos) {
            estiloValorCeldaCentrado(obj);
        }
    }

    /**
     * Esta funcionalidad se encarga de mostrar el cuadro de diálogo que permitirá: Abrir o Descargar el documento PDF.
     *
     * @param response Respuesta HTTP.
     * @throws IOException       Si el fichero no se encuentra.
     * @throws DocumentException Si el documento no se encuentra.
     */
    public void cuadroDialogo(HttpServletResponse response) throws IOException, DocumentException {

        document.add(table);
        document.close();

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
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los centros ETP.
     *
     * @param lst_centros Centros ETP que serán el contenido el cuerpo de la tabla.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_Centros(List<Centro> lst_centros, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "CentrosETP";
        subTituloTabla = "Centros Politécnicos";
        subTituloTabla1 = " ";
        propiedadesPDF(nombrePDF);

        table = new PdfPTable(8);

        float[] anchoColumnas = {0.38f, 0.8f, 2.1f, 4f, 0.8f, 1.32f, 1.38f, 1.38f};
        table.setWidths(anchoColumnas);

        titulos = new ArrayList<>();

        titulos.add(new Paragraph("No.", fuenteEncabezado));
        titulos.add(new Paragraph("Código", fuenteEncabezado));
        titulos.add(new Paragraph("Nombre del Centro", fuenteEncabezado));
        titulos.add(new Paragraph("Dirección Particular", fuenteEncabezado));
        titulos.add(new Paragraph("Teléfono", fuenteEncabezado));
        titulos.add(new Paragraph("Sector", fuenteEncabezado));
        titulos.add(new Paragraph("Municipio", fuenteEncabezado));
        titulos.add(new Paragraph("Provincia", fuenteEncabezado));

        crearEncabezadoTabla();
        Centro ce;

        for (int i = 0; i < lst_centros.size(); i++) {
            ce = lst_centros.get(i);

            elementos = new ArrayList<>();
            elementos.add(new PdfPCell(new Paragraph(Integer.toString(i + 1), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(0));
            elementos.add(new PdfPCell(new Paragraph(ce.getCodCentro(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(1));
            elementos.add(new PdfPCell(new Paragraph(ce.getNombCentro(), fuenteCuerpo)));
            estiloValorCeldaIzquierda(elementos.get(2));
            elementos.add(new PdfPCell(new Paragraph(ce.getDireccion(), fuenteCuerpo)));
            estiloValorCeldaIzquierda(elementos.get(3));
            elementos.add(new PdfPCell(new Paragraph(ce.getTelefono(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(4));
            elementos.add(new PdfPCell(new Paragraph(ce.getSector().getTipoSector(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(5));
            elementos.add(new PdfPCell(new Paragraph(ce.getMcpio().getNombMunicipio(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(6));
            elementos.add(new PdfPCell(new Paragraph(ce.getMcpio().getProv().getNombProvincia(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(7));
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de las especialidades ETP.
     *
     * @param lst_esp  Especialidades ETP que serán el contenido el cuerpo de la tabla.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_Especialidades(List<Especialidad> lst_esp, HttpServletResponse response) throws DocumentException, FileNotFoundException {
        String nombrePDF = "EspecialidadesETP";
        subTituloTabla = "Especialidades de estudio en politécnicos";
        subTituloTabla1 = " ";
        propiedadesPDF(nombrePDF);

        table = new PdfPTable(6);

        float[] anchoColumnas = {0.5f, 0.8f, 4.4f, 3.4f, 1.2f, 0.7f};
        table.setWidths(anchoColumnas);

        titulos = new ArrayList<>();

        titulos.add(new Paragraph("No.", fuenteEncabezado));
        titulos.add(new Paragraph("Código", fuenteEncabezado));
        titulos.add(new Paragraph("Nombre de Especialidad", fuenteEncabezado));
        titulos.add(new Paragraph("Familia", fuenteEncabezado));
        titulos.add(new Paragraph("Rama", fuenteEncabezado));
        titulos.add(new Paragraph("Nivel", fuenteEncabezado));

        crearEncabezadoTabla();
        Especialidad esp;

        for (int i = 0; i < lst_esp.size(); i++) {

            esp = lst_esp.get(i);

            elementos = new ArrayList<>();
            estiloValorCeldaCentrado(new PdfPCell(new Paragraph(Integer.toString(i + 1), fuenteCuerpo)));
            estiloValorCeldaCentrado(new PdfPCell(new Paragraph(esp.getCodEspecialidad(), fuenteCuerpo)));
            estiloValorCeldaIzquierda(new PdfPCell(new Paragraph(esp.getNombEspecialidad(), fuenteCuerpo)));
            estiloValorCeldaIzquierda(new PdfPCell(new Paragraph(esp.getFamilia().getTipoFamilia(), fuenteCuerpo)));
            estiloValorCeldaCentrado(new PdfPCell(new Paragraph(esp.getFamilia().getRama().getTipoRama(), fuenteCuerpo)));
            estiloValorCeldaCentrado(new PdfPCell(new Paragraph(esp.getNivel().getTipoNivel(), fuenteCuerpo)));
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void estiloValorCeldaIzquierda(PdfPCell celda) {
        celda.setBorderColor(Color.BLACK);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setBackgroundColor(Color.WHITE);
        celda.setExtraParagraphSpace(4f);
        celda.setPaddingTop(4);
        table.addCell(celda);
    }

    private void estiloValorCeldaCentrado(PdfPCell celda) {
        celda.setBorderColor(Color.BLACK);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setBackgroundColor(Color.WHITE);
        celda.setExtraParagraphSpace(4f);
        celda.setPaddingTop(4);
        table.addCell(celda);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de las especialidades de estudio de un centro ETP.
     *
     * @param centro     Centro.
     * @param esp_centro Especialidades del centro.
     * @param response   Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_EspecialidadesCentros(Centro centro, List<EspecialidadCentro> esp_centro, HttpServletResponse response) throws DocumentException, FileNotFoundException {
        String nombrePDF = "EspecialidadesCentro";
        subTituloTabla = "Especialidades de estudio";
        subTituloTabla1 = "Politécnico: " + centro.getNombCentro();
        propiedadesPDF(nombrePDF);

        table = new PdfPTable(7);

        float[] anchoColumnas = {0.5f, 0.9f, 4.6f, 1f, 1f, 0.6f, 1f};
        table.setWidths(anchoColumnas);

        titulos = new ArrayList<>();

        titulos.add(new Paragraph("No.", fuenteEncabezado));
        titulos.add(new Paragraph("Código", fuenteEncabezado));
        titulos.add(new Paragraph("Nombre de Especialidad", fuenteEncabezado));
        titulos.add(new Paragraph("Duración (Semestres)", fuenteEncabezado));
        titulos.add(new Paragraph("Escolaridad", fuenteEncabezado));
        titulos.add(new Paragraph("Nivel", fuenteEncabezado));
        titulos.add(new Paragraph("Curso", fuenteEncabezado));

        crearEncabezadoTabla();

        EspecialidadCentro esp;
        for (int i = 0; i < esp_centro.size(); i++) {

            esp = esp_centro.get(i);

            elementos = new ArrayList<>();
            elementos.add(new PdfPCell(new Paragraph(Integer.toString(i + 1), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(0));
            elementos.add(new PdfPCell(new Paragraph(esp.getEspecialidad().getCodEspecialidad(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(1));
            elementos.add(new PdfPCell(new Paragraph(esp.getEspecialidad().getNombEspecialidad(), fuenteCuerpo)));
            estiloValorCeldaIzquierda(elementos.get(2));
            elementos.add(new PdfPCell(new Paragraph(String.valueOf(esp.getDuracion().getTipoDuracion()), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(3));
            elementos.add(new PdfPCell(new Paragraph(esp.getEscolaridad().getTipoEscolaridad(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(4));
            elementos.add(new PdfPCell(new Paragraph(esp.getEspecialidad().getNivel().getTipoNivel(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(5));
            elementos.add(new PdfPCell(new Paragraph(esp.getCurso().getTipoCurso(), fuenteCuerpo)));
            estiloValorCeldaCentrado(elementos.get(6));
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los profesores por asignaturas de los centros ETP.
     *
     * @param centro   Centro.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ProfAsignatura(Centro centro, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ProfAsignatura";
        subTituloTabla = "Profesores fijos y contratados por asignaturas";
        subTituloTabla1 = "Politécnico: " + centro.getNombCentro();

        propiedadesPDF(nombrePDF);

        encabezadoProfAsignatura();

        ComposicionPersonal cp = centro.getComposicionPersonal();
        datosProfAsignatura(cp, response);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los profesores por asignaturas de un municipio.
     *
     * @param cp       Composición del personal municipal.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ProfAsignaturaMcpal(ComposicionPersonal cp, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ProfAsignaturasMcpal";
        subTituloTabla = "Profesores fijos y contratados por asignaturas";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        propiedadesPDF(nombrePDF);

        encabezadoProfAsignatura();
        datosProfAsignatura(cp, response);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los profesores por asignaturas de una provincia.
     *
     * @param cp       Composición del personal provincial.
     * @param prov     Provincia.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ProfAsignaturaProv(ComposicionPersonal cp, Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ProfAsignaturasProv";
        subTituloTabla = "Profesores fijos y contratados por asignaturas";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        propiedadesPDF(nombrePDF);

        encabezadoProfAsignatura();
        datosProfAsignatura(cp, response);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los profesores por asignaturas del país.
     *
     * @param cp       Composición del personal nacional.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ProfAsignaturaNac(ComposicionPersonal cp, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ProfAsignaturasNac";
        subTituloTabla = "Profesores fijos y contratados por asignaturas";
        subTituloTabla1 = "Resumen Nacional";

        propiedadesPDF(nombrePDF);

        encabezadoProfAsignatura();
        datosProfAsignatura(cp, response);
    }

    private void encabezadoProfAsignatura() throws DocumentException {
        table = new PdfPTable(7);
        table.setSpacingBefore(10);

        float[] anchoColumnas = {1.7f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f};

        table.setWidths(anchoColumnas);

        formatoFilasCombinadasEncabezado("Asignaturas", 2, Color.YELLOW);
        formatoColumnasCombinadasEncabezado("Profesores Fijos", 3);
        formatoColumnasCombinadasEncabezado("Profesores Contratados", 3);

        titulos = new ArrayList<>();
        titulos.add(new Paragraph("Total Fijos", fuenteEncabezado));
        titulos.add(new Paragraph("No Titulado", fuenteEncabezado));
        titulos.add(new Paragraph("No Titul. Estud", fuenteEncabezado));
        titulos.add(new Paragraph("Total Contratados", fuenteEncabezado));
        titulos.add(new Paragraph("No Profesionales", fuenteEncabezado));
        titulos.add(new Paragraph("No Prof. Estud", fuenteEncabezado));
        crearEncabezadoTabla();
    }

    private void datosProfAsignatura(ComposicionPersonal cp, HttpServletResponse response) {
        ProfesorAsignatura pf = cp.getProfesoresFijos();
        ProfesorAsignatura pc = cp.getProfesoresContratados();

        totalF = noTitulado = noTituladoEstudiando = totalC = noProfesional = noProfesionalEstudiando = 0;
        datosProfesorFijo("Español", (ProfesorFijo) pf.getProfesorEspanol());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorEspanol());

        datosProfesorFijo("Literatura", (ProfesorFijo) pf.getProfesorLiteratura());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorLiteratura());

        datosProfesorFijo("Matemática", (ProfesorFijo) pf.getProfesorMatematica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorMatematica());

        datosProfesorFijo("Física", (ProfesorFijo) pf.getProfesorFisica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorFisica());

        datosProfesorFijo("Química", (ProfesorFijo) pf.getProfesorQuimica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorQuimica());

        datosProfesorFijo("Biología", (ProfesorFijo) pf.getProfesorBiologia());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorBiologia());

        datosProfesorFijo("Historia", (ProfesorFijo) pf.getProfesorHistoria());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorHistoria());

        datosProfesorFijo("Idioma Extranjero", (ProfesorFijo) pf.getProfesorIngles());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorIngles());

        datosProfesorFijo("Educación Física", (ProfesorFijo) pf.getProfesorEducacionFisica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorEducacionFisica());

        datosProfesorFijo("Educación Artística", (ProfesorFijo) pf.getProfesorEducacionArtistica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorEducacionArtistica());

        datosProfesorFijo("Marxismo Leninismo", (ProfesorFijo) pf.getProfesorMarxismo());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorMarxismo());

        datosProfesorFijo("Fundamento de los Conocimientos Políticos", (ProfesorFijo) pf.getProfesorFundamento());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorFundamento());

        datosProfesorFijo("Computación", (ProfesorFijo) pf.getProfesorComputacion());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorComputacion());

        datosProfesorFijo("Preparación Militar", (ProfesorFijo) pf.getProfesorMilitar());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorMilitar());

        datosProfesorFijo("Enseñanza Práctica", (ProfesorFijo) pf.getProfesorPractica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorPractica());

        datosProfesorFijo("Básicas Específicas", (ProfesorFijo) pf.getProfesorBasica());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorBasica());

        datosProfesorFijo("Ejercicio de la Profesión", (ProfesorFijo) pf.getProfesorEjercicio());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorEjercicio());

        datosProfesorFijo("Instructor", (ProfesorFijo) pf.getProfesorInstructor());
        datosProfesorContrato((ProfesorContrato) pc.getProfesorInstructor());

        elementos.add(new PdfPCell(new Paragraph("Total", fuenteEncabezado)));
        formatoTotales(0, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(totalF, 0), fuenteEncabezado)));
        formatoTotales(1, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(noTitulado, 0), fuenteEncabezado)));
        formatoTotales(2, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(noTituladoEstudiando, 0), fuenteEncabezado)));
        formatoTotales(3, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(totalC, 0), fuenteEncabezado)));
        formatoTotales(4, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(noProfesional, 0), fuenteEncabezado)));
        formatoTotales(5, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(noProfesionalEstudiando, 0), fuenteEncabezado)));
        formatoTotales(6, Color.YELLOW);

        try {
            cuadroDialogo(response);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private void datosProfesorFijo(String asig, ProfesorFijo f) {
        elementos = new ArrayList<>();
        estiloValorCeldaIzquierda(new PdfPCell(new Paragraph(asig, fuenteCuerpo)));

        int fijos = f.getTotalProfesores(), noTitulado = f.getNoTitulados(), noTituladoEstudiando = f.getNoTituladosEstudiando();

        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(fijos, 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(noTitulado, 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(noTituladoEstudiando, 1), fuenteCuerpo)));
        this.totalF += fijos;
        this.noTitulado += noTitulado;
        this.noTituladoEstudiando += noTituladoEstudiando;
    }

    private void datosProfesorContrato(ProfesorContrato c) {
        int contratos = c.getTotalProfesores(), noProfesional = c.getNoProfesionales(), noProfesionalEstudiando = c.getNoProfesionalesEstudiando();

        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(contratos, 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(noProfesional, 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(noProfesionalEstudiando, 1), fuenteCuerpo)));
        this.totalC += contratos;
        this.noProfesional += noProfesional;
        this.noProfesionalEstudiando += noProfesionalEstudiando;
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los rangos de edades de los centros ETP.
     *
     * @param centro   Centro ETP al cual sus rangos de edades serán contenidos en el cuerpo de la tabla.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_RangoEdad(Centro centro, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "RangosEdades";
        subTituloTabla = "Matrícula por edades, sexo y tipo de curso";
        subTituloTabla1 = "Politécnico: " + centro.getNombCentro();

        propiedadesPDF(nombrePDF);

        datosRangoEdad(centro.getRangosEdades());

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los rangos de edades de un municipio.
     *
     * @param rango    Rango de edad que será contenido en el cuerpo de la tabla.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_RangoEdadMcpal(RangoEdad rango, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "RangosEdadesMcpal";
        subTituloTabla = "Matrícula por edades, sexo y tipo de curso";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        propiedadesPDF(nombrePDF);
        datosRangoEdad(rango);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los rangos de edades de una provincia.
     *
     * @param rango    Rango de edad que será contenido en el cuerpo de la tabla.
     * @param prov     Provincia.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_RangoEdadProv(RangoEdad rango, Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "RangosEdadesProv";
        subTituloTabla = "Matrícula por edades, sexo y tipo de curso";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        propiedadesPDF(nombrePDF);
        datosRangoEdad(rango);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los rangos de edades del país.
     *
     * @param rango    Rango de edad que será contenido en el cuerpo de la tabla.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_RangoEdadNac(RangoEdad rango, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "RangosEdadesNac";
        subTituloTabla = "Matrícula por edades, sexo y tipo de curso";
        subTituloTabla1 = "Resumen Nacional";

        propiedadesPDF(nombrePDF);
        datosRangoEdad(rango);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha de un centro.
     *
     * @param centro   Centros ETP que será el contenido del cuerpo de la tabla.
     * @param fichas   Elementos de la ficha.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaCentro(Centro centro, List<Ficha> fichas, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaPolitecnico";
        subTituloTabla = "Ficha: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Politécnico: " + centro.getNombCentro();
        propiedadesPDF(nombrePDF);

        table = new PdfPTable(10);
        enzabezadoTablaFicha();

        float[] anchoColumnas = {2.2f, 1.38f, 1.38f, 1.38f, 1.45f, 1.38f, 1.38f, 1.38f, 1.40f, 1.25f};
        titulosFicha(anchoColumnas);

        Font fuente;
        int pos;

        for (int i = 0; i < fichas.size(); i++) {
            Ficha f = fichas.get(i);
            elementos = new ArrayList<>();

            fuente = fuenteCuerpo;
            pos = i;

            if (i == 0 || i == 1 || i == 7) {
                fuente = fuenteEncabezado;
                pos = 0;
            }

            elementosFicha(fuente, pos, i, f);

            crearCuerpoTabla();
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void elementosFicha(Font fuente, int pos, int i, Ficha f) {
        elementos.add(new PdfPCell(new Paragraph(f.getConcepto(), fuente)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getTotal(), pos), fuente)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getHembras(), pos), fuente)));
        if (i > 1) {
            elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getInternos(), i), fuente)));
            elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getSeminternos(), i), fuente)));
        } else {
            elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getInternos(), 0), fuente)));
            elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getSeminternos(), 0), fuente)));
        }
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getNuevosIngresos(), pos), fuente)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getDocentesTotal(), i), fuente)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getDocentesHembras(), i), fuente)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getFrenteAula(), i), fuente)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(f.getRelacionAlumProf(), i), fuente)));
    }

    private void titulosFicha(float[] anchoColumnas) throws DocumentException {
        table.setWidths(anchoColumnas);

        titulos = new ArrayList<>();
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("De ellos: Hembras", fuenteEncabezado));
        titulos.add(new Paragraph("Internos", fuenteEncabezado));
        titulos.add(new Paragraph("Seminternos", fuenteEncabezado));
        titulos.add(new Paragraph("Nuevos Ingresos", fuenteEncabezado));
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("De ellos: Hembras", fuenteEncabezado));
        titulos.add(new Paragraph("Frente Aulas", fuenteEncabezado));

        crearEncabezadoTabla();
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha de un municipio.
     *
     * @param fichas   Elementos de la ficha.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaMcpal(List<Ficha> fichas, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaMcpal";
        subTituloTabla = "Ficha: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        propiedadesPDF(nombrePDF);
        datosFicha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha de una provincia.
     *
     * @param fichas   Elementos de la ficha.
     * @param nomProv  Nombre de la provincia.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaProv(List<Ficha> fichas, String nomProv, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaProv";
        subTituloTabla = "Ficha: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Provincia: " + nomProv;

        propiedadesPDF(nombrePDF);
        datosFicha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha nacional.
     *
     * @param fichas   Elementos de la ficha.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaNac(List<Ficha> fichas, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaNac";
        subTituloTabla = "Ficha: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Resumen Nacional";

        propiedadesPDF(nombrePDF);
        datosFicha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha turquino de un municipio.
     *
     * @param fichas   Elementos de la ficha.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaTurquinoMcpal(List<Ficha> fichas, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaTurquinoMcpal";
        subTituloTabla = "Ficha Turquino: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        propiedadesPDF(nombrePDF);
        datosFicha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha turquino de una provincia.
     *
     * @param fichas   Elementos de la ficha.
     * @param prov     Provincia.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaTurquinoProv(List<Ficha> fichas, Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaTurquinoProv";
        subTituloTabla = "Ficha Turquino: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        propiedadesPDF(nombrePDF);
        datosFicha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la ficha turquino nacional.
     *
     * @param fichas   Elementos de la ficha.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_FichaTurquinoNac(List<Ficha> fichas, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "FichaTurquinoNac";
        subTituloTabla = "Ficha Turquino: Matrículas, nuevos ingresos y personal docente";
        subTituloTabla1 = "Resumen Nacional";

        propiedadesPDF(nombrePDF);
        datosFicha(fichas);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del resumen de matrículas finales, aprobados y graduados por años de estudio, especialidades y familias de un municipio.
     *
     * @param esp_familia Listado de especialidades por familias.
     * @param mcpio       Municipio.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenCursoTerminadoMcpio(List<List<EspecialidadAnterior>> esp_familia, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ResumenCursoTerminadoMcpal";
        subTituloTabla = "Resumen de matrículas finales, aprobados y graduados por años de estudio, especialidades y familias (Curso Diurno)";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        datosCursoTerminado(nombrePDF, esp_familia, "Municipal");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del resumen de matrículas finales, aprobados y graduados por años de estudio, especialidades y familias de una provincia.
     *
     * @param esp_familia Listado de especialidades por familias.
     * @param prov        Provincia.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenCursoTerminadoProv(List<List<EspecialidadAnterior>> esp_familia, Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ResumenCursoTerminadoProv";
        subTituloTabla = "Resumen de matrículas finales, aprobados y graduados por años de estudio, especialidades y familias (Curso Diurno)";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        datosCursoTerminado(nombrePDF, esp_familia, "Provincial");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del resumen de matrículas finales, aprobados y graduados por años de estudio, especialidades y familias del país.
     *
     * @param esp_familia Listado de especialidades por familias.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenCursoTerminadoNac(List<List<EspecialidadAnterior>> esp_familia, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ResumenCursoTerminadoNac";
        subTituloTabla = "Resumen de matrículas finales, aprobados y graduados por años de estudio, especialidades y familias (Curso Diurno)";
        subTituloTabla1 = "Resumen Nacional";

        datosCursoTerminado(nombrePDF, esp_familia, "Nacional");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void datosCursoTerminado(String nombrePDF, List<List<EspecialidadAnterior>> esp_familia, String region) throws FileNotFoundException, DocumentException {
        encabezadoResumenCurso(nombrePDF);
        formatoColumnasCombinadasEncabezado("Matrícula Final", 5);
        formatoColumnasCombinadasEncabezado("Aprobados", 5);
        formatoColumnasCombinadasEncabezado("Graduados", 2);

        subTitulosResumenCurso();

        int[] total_mcpio = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, total_tm = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, total_oc = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] valores, tm, oc;
        PlanillaDatos pd;
        EspecialidadAnterior sub;
        Especialidad e;
        List<EspecialidadAnterior> esp, subList;
        String nombreEsp, familia, name;

        for (int i = 0; i < esp_familia.size(); i++) {
            esp = esp_familia.get(i);

            tm = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            oc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

            for (int j = 0; j < esp.size(); j++) {

                valores = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                pd = esp.get(j).getPlanilla();

                int contarEsp = 1, vecino = j + 1, paso = 4;
                while (esp.get(j).getAnnoEstudio().equals(esp.get(vecino).getAnnoEstudio())) {
                    contarEsp++;
                    vecino++;
                }

                if (pd.getEspecialidadCentro().getEspecialidad().getNivel().getTipoNivel().equals("O.C")) {
                    paso = 2;
                }

                subList = esp.subList(j, j + (paso * contarEsp));

                e = pd.getEspecialidadCentro().getEspecialidad();
                nombreEsp = e.getNivel().getTipoNivel() + " " + e.getNombEspecialidad();
                int aprobadosT = 0, matriFinalT = 0, cont = 0, pos = 1, longLista = subList.size(), salto = longLista / paso;

                for (int k = 0; k < longLista; k++) {
                    sub = subList.get(k);

                    int matriFinal = sub.getMatriculaFinal(), aprobados = sub.getAprobados();
                    matriFinalT += matriFinal;
                    aprobadosT += aprobados;
                    valores[pos] += matriFinal;
                    valores[pos + 5] += aprobados;

                    if (k < salto) {
                        valores[10] += sub.getPlanilla().getProcdGraduados();
                        valores[11] += sub.getPlanilla().getHembProcdGraduados();
                    }

                    if (salto != 1) {
                        cont++;
                        if (cont % salto == 0) {
                            pos++;
                        }
                    } else {
                        pos++;
                    }
                }
                j = actualizarDatosArreglos(total_tm, total_oc, tm, oc, j, valores, contarEsp, paso, nombreEsp, aprobadosT, matriFinalT);
            }

            familia = esp.get(0).getPlanilla().getEspecialidadCentro().getEspecialidad().getFamilia().getTipoFamilia();

            name = "Total TM - " + familia;
            datosArreglo(tm, total_mcpio, name, fuenteEncabezado);

            name = "Total OC - " + familia;
            datosArreglo(oc, total_mcpio, name, fuenteEncabezado);
        }
        paraTotales(region, total_mcpio, total_tm, total_oc);
    }

    private void encabezadoResumenCurso(String nombrePDF) throws DocumentException, FileNotFoundException {
        propiedadesPDF(nombrePDF);

        table = new PdfPTable(13);
        formatoFilasCombinadasEncabezado("Especialidades/Familia", 2, Color.YELLOW);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del resumen de matrículas iniciales, hembras y nuevos ingresos por años de estudio, especialidades y familias de un municipio.
     *
     * @param esp_familia Listado de especialidades por familias.
     * @param mcpio       Municipio.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenCursoIniciadoMcpio(List<List<EspecialidadActual>> esp_familia, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ResumenCursoIniciadoMcpal";
        subTituloTabla = "Resumen de matrículas iniciales, hembras y nuevos ingresos por años de estudio, especialidades y familias (Curso Diurno)";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        datosCursoIniciado(nombrePDF, esp_familia, "Municipal");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del resumen de matrículas iniciales, hembras y nuevos ingresos por años de estudio, especialidades y familias de una provincia.
     *
     * @param esp_familia Listado de especialidades por familias.
     * @param prov        Provincia.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenCursoIniciadoProv(List<List<EspecialidadActual>> esp_familia, Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ResumenCursoIniciadoProv";
        subTituloTabla = "Resumen de matrículas iniciales, hembras y nuevos ingresos por años de estudio, especialidades y familias (Curso Diurno)";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        datosCursoIniciado(nombrePDF, esp_familia, "Provincial");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del resumen de matrículas iniciales, hembras y nuevos ingresos por años de estudio, especialidades y familias del país.
     *
     * @param esp_familia Listado de especialidades por familias.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenCursoIniciadoNac(List<List<EspecialidadActual>> esp_familia, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "ResumenCursoIniciadoProv";
        subTituloTabla = "Resumen de matrículas iniciales, hembras y nuevos ingresos por años de estudio, especialidades y familias (Curso Diurno)";
        subTituloTabla1 = "Resumen Nacional";

        datosCursoIniciado(nombrePDF, esp_familia, "Nacional");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void datosCursoIniciado(String nombrePDF, List<List<EspecialidadActual>> esp_familia, String region) throws FileNotFoundException, DocumentException {
        encabezadoResumenCurso(nombrePDF);
        formatoColumnasCombinadasEncabezado("Matrícula Inicial", 5);
        formatoColumnasCombinadasEncabezado("Matrícula Inicial Hembras", 5);
        formatoColumnasCombinadasEncabezado("Nuevos Ingresos", 2);

        subTitulosResumenCurso();

        int[] total_mcpio = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, total_tm = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, total_oc = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] valores, tm, oc;
        List<EspecialidadActual> esp, subList;
        PlanillaDatos pd;
        Especialidad e;
        String nombreEsp, familia, name;
        EspecialidadActual sub;

        for (int i = 0; i < esp_familia.size(); i++) {
            esp = esp_familia.get(i);

            tm = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            oc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

            for (int j = 0; j < esp.size(); j++) {

                valores = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                pd = esp.get(j).getPlanilla();

                int contarEsp = 1, vecino = j + 1, paso = 4;
                while (esp.get(j).getAnnoEstudio().equals(esp.get(vecino).getAnnoEstudio())) {
                    contarEsp++;
                    vecino++;
                }

                if (pd.getEspecialidadCentro().getEspecialidad().getNivel().getTipoNivel().equals("O.C")) {
                    paso = 2;
                }

                subList = esp.subList(j, j + (paso * contarEsp));

                e = pd.getEspecialidadCentro().getEspecialidad();
                nombreEsp = e.getNivel().getTipoNivel() + " " + e.getNombEspecialidad();
                int matActualHembrasT = 0, matActualT = 0, cont = 0, pos = 1, longLista = subList.size(), salto = longLista / paso;

                for (int k = 0; k < longLista; k++) {
                    sub = subList.get(k);

                    int matActual = sub.getMatriculaActualTotal(), matActualHembras = sub.getMatriculaActualHembras();
                    matActualT += matActual;
                    matActualHembrasT += matActualHembras;
                    valores[pos] += matActual;
                    valores[pos + 5] += matActualHembras;

                    if (k < salto) {
                        valores[10] += sub.getPlanilla().getProcdNuevosIngresos();
                        valores[11] += sub.getPlanilla().getHembProcdNuevosIngresos();
                    }

                    if (salto != 1) {
                        cont++;
                        if (cont % salto == 0) {
                            pos++;
                        }
                    } else {
                        pos++;
                    }
                }
                j = actualizarDatosArreglos(total_tm, total_oc, tm, oc, j, valores, contarEsp, paso, nombreEsp, matActualHembrasT, matActualT);
            }

            familia = esp.get(0).getPlanilla().getEspecialidadCentro().getEspecialidad().getFamilia().getTipoFamilia();

            name = "Total TM - " + familia;
            datosArreglo(tm, total_mcpio, name, fuenteEncabezado);

            name = "Total OC - " + familia;
            datosArreglo(oc, total_mcpio, name, fuenteEncabezado);
        }
        paraTotales(region, total_mcpio, total_tm, total_oc);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la matrícula inicial por sexo, curso y régimen de un municipio.
     *
     * @param car      Carátula municipal.
     * @param mcpio    Municipio.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenMatriculaInicialMcpio(Caratula car, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "MatInicialMcpal";
        subTituloTabla = "Matrícula inicial por sexo, tipo de curso y régimen";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        propiedadesPDF(nombrePDF);

        datosMatriculaInicial(car, "Municipal");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la matrícula inicial por sexo, curso y régimen de una provincia.
     *
     * @param car      Carátula provincial.
     * @param prov     Provincia.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenMatriculaInicialProv(Caratula car, Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "MatInicialProv";
        subTituloTabla = "Matrícula inicial por sexo, tipo de curso y régimen";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        propiedadesPDF(nombrePDF);

        datosMatriculaInicial(car, "Provincial");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de la matrícula inicial por sexo, curso y régimen del país.
     *
     * @param car      Carátula nacional.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_ResumenMatriculaInicialNac(Caratula car, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "MatInicialNac";
        subTituloTabla = "Matrícula inicial por sexo, tipo de curso y régimen";
        subTituloTabla1 = "Resumen Nacional";

        propiedadesPDF(nombrePDF);

        datosMatriculaInicial(car, "Nacional");

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void datosMatriculaInicial(Caratula car, String region) {
        table = new PdfPTable(7);
        table.setSpacingBefore(10);

        float[] anchoColumnas = {1.7f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f};

        try {
            table.setWidths(anchoColumnas);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        formatoFilasCombinadasEncabezado("Tipo de Nivel", 2, Color.YELLOW);
        formatoColumnasCombinadasEncabezado("Matrícula", 2);
        formatoColumnasCombinadasEncabezado("Internos", 2);
        formatoColumnasCombinadasEncabezado("Seminternos", 2);

        titulos = new ArrayList<>();
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Hembras", fuenteEncabezado));
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Hembras", fuenteEncabezado));
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Hembras", fuenteEncabezado));
        crearEncabezadoTabla();

        MatriculaInicial mi_tm = car.getMatriculaInicialTM();
        RegimenEstudio rg_tm = car.getRegimenTM();
        MatriculaInicial mi_oc = car.getMatriculaInicialOC();
        RegimenEstudio rg_oc = car.getRegimenOC();

        datosMatriculaInicialMcpal("Técnico Medio", mi_tm, rg_tm);
        datosMatriculaInicialMcpal("Obrero Calificado", mi_oc, rg_oc);
        elementos.add(new PdfPCell(new Paragraph("Total Curso Diurno", fuenteEncabezado)));
        formatoTotales(0, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm.getTotal() + mi_oc.getTotal(), 0), fuenteEncabezado)));
        formatoTotales(1, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm.getTotalHembras() + mi_oc.getTotalHembras(), 0), fuenteEncabezado)));
        formatoTotales(2, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getInternos() + rg_oc.getInternos(), 0), fuenteEncabezado)));
        formatoTotales(3, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getInternosHembras() + rg_oc.getInternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(4, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getSeminternos() + rg_oc.getSeminternos(), 0), fuenteEncabezado)));
        formatoTotales(5, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getSeminternosHembras() + rg_oc.getSeminternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(6, Color.LIGHT_GRAY);

        MatriculaInicial mi_tm_cpt = car.getMatriculaInicialCPT_TM();
        RegimenEstudio rg_cpt = new RegimenEstudio(0, 0, 0, 0);

        datosMatriculaInicialMcpal("Técnico Medio", mi_tm_cpt, rg_cpt);
        elementos.add(new PdfPCell(new Paragraph("Total Curso V/Nocturno", fuenteEncabezado)));
        formatoTotales(0, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm_cpt.getTotal(), 0), fuenteEncabezado)));
        formatoTotales(1, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm_cpt.getTotalHembras(), 0), fuenteEncabezado)));
        formatoTotales(2, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(0, 0), fuenteEncabezado)));
        formatoTotales(3, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(0, 0), fuenteEncabezado)));
        formatoTotales(4, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(0, 0), fuenteEncabezado)));
        formatoTotales(5, Color.LIGHT_GRAY);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(0, 0), fuenteEncabezado)));
        formatoTotales(6, Color.LIGHT_GRAY);

        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Total T.M", fuenteEncabezado)));
        formatoTotales(0, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm.getTotal() + mi_tm_cpt.getTotal(), 0), fuenteEncabezado)));
        formatoTotales(1, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm.getTotalHembras() + mi_tm_cpt.getTotalHembras(), 0), fuenteEncabezado)));
        formatoTotales(2, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getInternos(), 0), fuenteEncabezado)));
        formatoTotales(3, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getInternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(4, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getSeminternos(), 0), fuenteEncabezado)));
        formatoTotales(5, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getSeminternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(6, Color.YELLOW);

        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Total O.C", fuenteEncabezado)));
        formatoTotales(0, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_oc.getTotal(), 0), fuenteEncabezado)));
        formatoTotales(1, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_oc.getTotalHembras(), 0), fuenteEncabezado)));
        formatoTotales(2, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_oc.getInternos(), 0), fuenteEncabezado)));
        formatoTotales(3, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_oc.getInternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(4, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_oc.getSeminternos(), 0), fuenteEncabezado)));
        formatoTotales(5, Color.YELLOW);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_oc.getSeminternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(6, Color.YELLOW);

        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Total " + region, fuenteEncabezado)));
        formatoTotales(0, Color.ORANGE);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm.getTotal() + mi_tm_cpt.getTotal() + mi_oc.getTotal(), 0), fuenteEncabezado)));
        formatoTotales(1, Color.ORANGE);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(mi_tm.getTotalHembras() + mi_tm_cpt.getTotalHembras() + mi_oc.getTotalHembras(), 0), fuenteEncabezado)));
        formatoTotales(2, Color.ORANGE);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getInternos() + rg_oc.getInternos(), 0), fuenteEncabezado)));
        formatoTotales(3, Color.ORANGE);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getInternosHembras() + rg_oc.getInternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(4, Color.ORANGE);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getSeminternos() + rg_oc.getSeminternos(), 0), fuenteEncabezado)));
        formatoTotales(5, Color.ORANGE);
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rg_tm.getSeminternosHembras() + rg_oc.getSeminternosHembras(), 0), fuenteEncabezado)));
        formatoTotales(6, Color.ORANGE);
    }

    private void datosMatriculaInicialMcpal(String nivel, MatriculaInicial mi, RegimenEstudio rg) {
        elementos = new ArrayList<>();
        estiloValorCeldaIzquierda(new PdfPCell(new Paragraph(nivel, fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(mi.getTotal(), 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(mi.getTotalHembras(), 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(rg.getInternos(), 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(rg.getInternosHembras(), 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(rg.getSeminternos(), 1), fuenteCuerpo)));
        estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(rg.getSeminternosHembras(), 1), fuenteCuerpo)));
    }

    private void subTitulosResumenCurso() throws DocumentException {
        float[] anchoColumnas = {4.6f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
        table.setWidths(anchoColumnas);

        titulos = new ArrayList<>();

        titulos.add(new Paragraph("Total", fuenteEncabezado));

        for (int i = 0; i < 2; i++) {
            titulos.add(new Paragraph("I", fuenteEncabezado));
            titulos.add(new Paragraph("II", fuenteEncabezado));
            titulos.add(new Paragraph("III", fuenteEncabezado));
            titulos.add(new Paragraph("IV", fuenteEncabezado));
            titulos.add(new Paragraph("Total", fuenteEncabezado));
        }
        titulos.add(new Paragraph("Hembras", fuenteEncabezado));

        crearEncabezadoTabla();
    }

    private int actualizarDatosArreglos(int[] total_tm, int[] total_oc, int[] tm, int[] oc, int j, int[] valores, int contarEsp, int paso, String nombreEsp, int valor2, int valor1) {
        valores[0] = valor1;
        valores[5] = valor2;

        if (paso == 4) {
            datosArreglo(valores, tm, nombreEsp, fuenteCuerpo);
            sumarDatos(valores, total_tm);
        } else {
            datosArreglo(valores, oc, nombreEsp, fuenteCuerpo);
            sumarDatos(valores, total_oc);
        }
        j += (paso * contarEsp) - 1;
        return j;
    }

    private void paraTotales(String region, int[] total_mcpio, int[] total_tm, int[] total_oc) {
        elementos = new ArrayList<>();

        for (int m = 0; m < total_tm.length + 1; m++) {
            if (m == 0) {
                elementos.add(new PdfPCell(new Paragraph("Total TM - " + region, fuenteEncabezado)));
            } else {
                elementos.add(new PdfPCell(new Paragraph(Integer.toString(total_tm[m - 1]), fuenteEncabezado)));
            }
            formatoTotales(m, Color.YELLOW);
        }

        elementos = new ArrayList<>();

        for (int m = 0; m < total_oc.length + 1; m++) {
            if (m == 0) {
                elementos.add(new PdfPCell(new Paragraph("Total OC - " + region, fuenteEncabezado)));
            } else {
                elementos.add(new PdfPCell(new Paragraph(Integer.toString(total_oc[m - 1]), fuenteEncabezado)));
            }
            formatoTotales(m, Color.YELLOW);
        }

        elementos = new ArrayList<>();

        for (int m = 0; m < total_mcpio.length + 1; m++) {
            if (m == 0) {
                elementos.add(new PdfPCell(new Paragraph("Total " + region, fuenteEncabezado)));
            } else {
                elementos.add(new PdfPCell(new Paragraph(Integer.toString(total_mcpio[m - 1]), fuenteEncabezado)));
            }
            formatoTotales(m, Color.ORANGE);
        }
    }

    private void formatoTotales(int m, Color color) {
        elementos.get(m).setBorderColor(Color.BLACK);
        elementos.get(m).setHorizontalAlignment(Element.ALIGN_CENTER);
        elementos.get(m).setVerticalAlignment(Element.ALIGN_MIDDLE);
        elementos.get(m).setBackgroundColor(color);
        elementos.get(m).setExtraParagraphSpace(4f);
        elementos.get(m).setPaddingTop(4);
        table.addCell(elementos.get(m));
    }

    private void datosArreglo(int[] origen, int[] destino, String nombre, Font fuente) {

        if (origen[0] != 0 || origen[5] != 0 || origen[10] != 0) {

            elementos = new ArrayList<>();
            elementos.add(new PdfPCell(new Paragraph(nombre, fuente)));

            if (nombre.contains("Total")) {
                elementos.get(0).setHorizontalAlignment(Element.ALIGN_CENTER);
                elementos.get(0).setBackgroundColor(Color.LIGHT_GRAY);
            } else {
                elementos.get(0).setHorizontalAlignment(Element.ALIGN_LEFT);
                elementos.get(0).setBackgroundColor(Color.WHITE);
            }
            elementos.get(0).setBorderColor(Color.BLACK);
            elementos.get(0).setVerticalAlignment(Element.ALIGN_MIDDLE);
            elementos.get(0).setPaddingBottom(4);
            table.addCell(elementos.get(0));

            for (int m = 0; m < origen.length; m++) {

                if (nombre.contains("Total")) {
                    elementos.add(new PdfPCell(new Paragraph(valorCelda(origen[m], 0), fuenteEncabezado)));
                    elementos.get(m + 1).setBackgroundColor(Color.LIGHT_GRAY);
                } else {
                    elementos.add(new PdfPCell(new Paragraph(valorCelda(origen[m], 1), fuenteCuerpo)));
                    elementos.get(m + 1).setBackgroundColor(Color.WHITE);
                }

                elementos.get(m + 1).setBorderColor(Color.BLACK);
                elementos.get(m + 1).setHorizontalAlignment(Element.ALIGN_CENTER);
                elementos.get(m + 1).setVerticalAlignment(Element.ALIGN_MIDDLE);
                elementos.get(m + 1).setPaddingBottom(4);
                table.addCell(elementos.get(m + 1));

                destino[m] += origen[m];
            }
        }
    }

    private void sumarDatos(int[] origen, int[] destino) {
        for (int m = 0; m < origen.length; m++) {
            destino[m] += origen[m];
        }
    }

    private void formatoColumnasCombinadasEncabezado(String texto, int columnasACombinar) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuenteEncabezado));
        cell.setColspan(columnasACombinar);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(Color.ORANGE);
        cell.setExtraParagraphSpace(5f);
        cell.setPaddingTop(3);
        table.addCell(cell);
    }

    private void formatoFilasCombinadasEncabezado(String texto, int filasACombinar, Color color) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuenteEncabezado));
        cell.setRowspan(filasACombinar);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(color);
        table.addCell(cell);
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

        elementos = new ArrayList<>();
        PdfPCell cel = new PdfPCell(new Paragraph("Menores de 15 años", fuenteEncabezado));
        cel.setRowspan(2);
        elementos.add(cel);
        elementos.add(new PdfPCell(new Paragraph("Total", fuenteEncabezado)));

        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getTotalMenos15() + rango.getCpt().getTotalMenos15()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getDiurno().getTotalMenos15(), 1), fuenteCuerpo)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getCpt().getTotalMenos15(), 1), fuenteCuerpo)));
        crearCuerpoTabla();
        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Hembras", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getHembrasMenos15() + rango.getCpt().getHembrasMenos15()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getDiurno().getHembrasMenos15(), 1), fuenteCuerpo)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getCpt().getHembrasMenos15(), 1), fuenteCuerpo)));
        crearCuerpoTabla();
        elementos = new ArrayList<>();
        cel = new PdfPCell(new Paragraph("Entre 15 y 16 años", fuenteEncabezado));
        cel.setRowspan(2);
        elementos.add(cel);
        elementos.add(new PdfPCell(new Paragraph("Total", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getTotalEntre15_16() + rango.getCpt().getTotalEntre15_16()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getDiurno().getTotalEntre15_16(), 1), fuenteCuerpo)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getCpt().getTotalEntre15_16(), 1), fuenteCuerpo)));
        crearCuerpoTabla();
        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Hembras", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getHembrasEntre15_16() + rango.getCpt().getHembrasEntre15_16()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getDiurno().getHembrasEntre15_16(), 1), fuenteCuerpo)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getCpt().getHembrasEntre15_16(), 1), fuenteCuerpo)));
        crearCuerpoTabla();
        elementos = new ArrayList<>();
        cel = new PdfPCell(new Paragraph("Mayores de 16 años", fuenteEncabezado));
        cel.setRowspan(2);
        elementos.add(cel);
        elementos.add(new PdfPCell(new Paragraph("Total", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getTotalMayores16() + rango.getCpt().getTotalMayores16()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getDiurno().getTotalMayores16(), 1), fuenteCuerpo)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getCpt().getTotalMayores16(), 1), fuenteCuerpo)));
        crearCuerpoTabla();
        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Hembras", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getHembrasMayores16() + rango.getCpt().getHembrasMayores16()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getDiurno().getHembrasMayores16(), 1), fuenteCuerpo)));
        elementos.add(new PdfPCell(new Paragraph(valorCelda(rango.getCpt().getHembrasMayores16(), 1), fuenteCuerpo)));
        crearCuerpoTabla();

        elementos = new ArrayList<>();
        cel = new PdfPCell(new Paragraph("Total", fuenteEncabezado));
        cel.setRowspan(2);
        elementos.add(cel);
        elementos.add(new PdfPCell(new Paragraph("Total", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getMatriculaRangoTotal() + rango.getCpt().getMatriculaRangoTotal()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getMatriculaRangoTotal()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getCpt().getMatriculaRangoTotal()), fuenteEncabezado)));
        crearCuerpoTabla();
        elementos = new ArrayList<>();
        elementos.add(new PdfPCell(new Paragraph("Hembras", fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getMatriculaRangoTotalHembras() + rango.getCpt().getMatriculaRangoTotalHembras()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getDiurno().getMatriculaRangoTotalHembras()), fuenteEncabezado)));
        elementos.add(new PdfPCell(new Paragraph(String.valueOf(rango.getCpt().getMatriculaRangoTotalHembras()), fuenteEncabezado)));
        crearCuerpoTabla();
    }

    private void encabezadoRango() {
        table = new PdfPTable(5);
        table.setSpacingBefore(10);

        float[] anchoColumnas = {0.7f, 0.6f, 0.6f, 0.6f, 0.6f};

        try {
            table.setWidths(anchoColumnas);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        titulos = new ArrayList<>();

        formatoColumnasCombinadasEncabezado("Rangos", 2);

        titulos.add(new Paragraph("Total ETP", fuenteEncabezado));
        titulos.add(new Paragraph("Curso Diurno", fuenteEncabezado));
        titulos.add(new Paragraph("Curso V/Nocturno", fuenteEncabezado));

        crearEncabezadoTabla();
    }

    private void datosFicha(List<Ficha> fichas) throws DocumentException {
        table = new PdfPTable(10);

        enzabezadoTablaFicha();

        float[] anchoColumnas = {2.2f, 1.38f, 1.38f, 1.38f, 1.45f, 1.38f, 1.38f, 1.38f, 1.40f, 1.25f};

        titulosFicha(anchoColumnas);
        Font fuente;
        int pos;
        Ficha f;

        for (int i = 0; i < fichas.size(); i++) {
            f = fichas.get(i);
            elementos = new ArrayList<>();

            fuente = fuenteCuerpo;

            if (i == 0 || i == 1 || i == 7) {
                fuente = fuenteEncabezado;
                pos = 0;
            } else {
                pos = i;
            }
            elementosFicha(fuente, pos, i, f);

            crearCuerpoTabla();
        }
    }

    private void enzabezadoTablaFicha() {
        table.setSpacingBefore(10);

        formatoFilasCombinadasEncabezado("Educación", 2, Color.YELLOW);
        formatoColumnasCombinadasEncabezado("Matrícula", 5);
        formatoColumnasCombinadasEncabezado("Personal Docente", 3);
        formatoFilasCombinadasEncabezado("Relación Alumnos/ Profesor", 2, Color.YELLOW);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del personal de los centros de un municipio.
     *
     * @param lst_centros Centros.
     * @param mcpio       Municipio.
     * @param response    Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_PersonalMcpal(List<Centro> lst_centros, Municipio mcpio, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "PersonalMcpal";
        subTituloTabla = "Composición general del personal";
        subTituloTabla1 = "Municipio: " + mcpio.getNombMunicipio() + " (" + mcpio.getProv().getNombProvincia() + ")";

        propiedadesPDF(nombrePDF);

        table = new PdfPTable(14);
        table.setSpacingBefore(10);

        float[] anchoColumnas = {1.8f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f};
        table.setWidths(anchoColumnas);

        formatoFilasCombinadasEncabezado("Centro", 2, Color.YELLOW);
        encabezadoPersonal();

        Centro cen;
        int[] total = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < lst_centros.size(); i++) {
            cen = lst_centros.get(i);

            elementos = new ArrayList<>();

            PdfPCell celda = new PdfPCell(new Paragraph(cen.getNombCentro(), fuenteCuerpo));
            estiloValorCeldaIzquierda(celda);

            datosPersonal(total, cen.getComposicionPersonal());
        }

        PdfPCell celda = new PdfPCell(new Paragraph("Total Municipal", fuenteEncabezado));
        datosTotalPersonal(response, total, celda);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del personal de una provincia.
     *
     * @param prov     Municipio.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_PersonalProv(Provincia prov, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "PersonalProv";
        subTituloTabla = "Composición general del personal";
        subTituloTabla1 = "Provincia: " + prov.getNombProvincia();

        propiedadesPDF(nombrePDF);

        table = new PdfPTable(14);
        table.setSpacingBefore(10);

        float[] anchoColumnas = {1.7f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f};
        table.setWidths(anchoColumnas);

        formatoFilasCombinadasEncabezado("Municipios", 2, Color.YELLOW);
        encabezadoPersonal();

        ComposicionPersonal cp;
        int[] total = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        List<Municipio> mcpios = prov.getMcpios();

        for (int i = 0; i < mcpios.size(); i++) {

            elementos = new ArrayList<>();

            cp = getDatosPersonalProv(mcpios.get(i), resumenes);
            PdfPCell celda = new PdfPCell(new Paragraph(mcpios.get(i).getNombMunicipio(), fuenteCuerpo));
            estiloValorCeldaIzquierda(celda);
            datosPersonal(total, cp);
        }

        PdfPCell celda = new PdfPCell(new Paragraph("Total Provincial", fuenteEncabezado));
        datosTotalPersonal(response, total, celda);
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF del personal nacional.
     *
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_PersonalNac(HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "PersonalNac";
        subTituloTabla = "Composición general del personal";
        subTituloTabla1 = "Resumen Nacional";

        propiedadesPDF(nombrePDF);

        table = new PdfPTable(14);
        table.setSpacingBefore(10);

        float[] anchoColumnas = {1.7f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f, 0.8f};
        table.setWidths(anchoColumnas);

        formatoFilasCombinadasEncabezado("Provincias", 2, Color.YELLOW);
        encabezadoPersonal();

        ComposicionPersonal cp;
        List<Provincia> prov = provincias.listar();
        int[] total = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < prov.size(); i++) {

            elementos = new ArrayList<>();

            cp = getDatosPersonalNac(prov, i, resumenes);

            PdfPCell celda = new PdfPCell(new Paragraph(prov.get(i).getNombProvincia(), fuenteCuerpo));
            estiloValorCeldaIzquierda(celda);
            datosPersonal(total, cp);
        }

        PdfPCell celda = new PdfPCell(new Paragraph("Total Nacional", fuenteEncabezado));
        datosTotalPersonal(response, total, celda);
    }

    static ComposicionPersonal getDatosPersonalNac(List<Provincia> prov, int i, GestionarResumen resumenes) {
        long idP = prov.get(i).getIdProvincia();
        int profesores, profesoresH, bibliotecarios, bibliotecariosH, instructores, instructoresH, psicopedagogos, psicopedagogosH, alumnosMaestros, noDocentes, noDocentesH, profesoresInactivos, otrosDocInactivos, noDocInactivos;

        profesores = resumenes.profesoresDocentesTotalesProv(idP);
        profesoresH = resumenes.profesoresDocentesHembrasTotalesProv(idP);
        bibliotecarios = resumenes.bibliotecariosTotalesProv(idP);
        bibliotecariosH = resumenes.bibliotecariosHembrasTotalesProv(idP);
        instructores = resumenes.instructoresArteTotalesProv(idP);
        instructoresH = resumenes.instructoresArteHembrasTotalesProv(idP);
        psicopedagogos = resumenes.psicopedagogosTotalesProv(idP);
        psicopedagogosH = resumenes.psicopedagogosHembrasTotalesProv(idP);
        alumnosMaestros = resumenes.alumnosMaestrosTotalesProv(idP);
        noDocentes = resumenes.noDocentesTotalesProv(idP);
        noDocentesH = resumenes.noDocentesHembrasTotalesProv(idP);
        profesoresInactivos = resumenes.profesoresInactivosTotalesProv(idP);
        otrosDocInactivos = resumenes.otrosDocentesInactivosTotalesProv(idP);
        noDocInactivos = resumenes.noDocentesInactivosTotalesProv(idP);

        return new ComposicionPersonal(new Personal(profesores, profesoresH), new Personal(bibliotecarios, bibliotecariosH), new Personal(instructores, instructoresH), new Personal(psicopedagogos, psicopedagogosH), alumnosMaestros, new Personal(noDocentes, noDocentesH), profesoresInactivos, otrosDocInactivos, noDocInactivos);
    }

    static ComposicionPersonal getDatosPersonalProv(Municipio mcpio, GestionarResumen resumenes) {
        long idM = mcpio.getIdMunicipio();
        int profesores, profesoresH, bibliotecarios, bibliotecariosH, instructores, instructoresH, psicopedagogos, psicopedagogosH, alumnosMaestros, noDocentes, noDocentesH, profesoresInactivos, otrosDocInactivos, noDocInactivos;

        profesores = resumenes.profesoresDocentesTotalesMcpio(idM);
        profesoresH = resumenes.profesoresDocentesHembrasTotalesMcpio(idM);
        bibliotecarios = resumenes.bibliotecariosTotalesMcpio(idM);
        bibliotecariosH = resumenes.bibliotecariosHembrasTotalesMcpio(idM);
        instructores = resumenes.instructoresArteTotalesMcpio(idM);
        instructoresH = resumenes.instructoresArteHembrasTotalesMcpio(idM);
        psicopedagogos = resumenes.psicopedagogosTotalesMcpio(idM);
        psicopedagogosH = resumenes.psicopedagogosHembrasTotalesMcpio(idM);
        alumnosMaestros = resumenes.alumnosMaestrosTotalesMcpio(idM);
        noDocentes = resumenes.noDocentesTotalesMcpio(idM);
        noDocentesH = resumenes.noDocentesHembrasTotalesMcpio(idM);
        profesoresInactivos = resumenes.profesoresInactivosTotalesMcpio(idM);
        otrosDocInactivos = resumenes.otrosDocentesInactivosTotalesMcpio(idM);
        noDocInactivos = resumenes.noDocentesInactivosTotalesMcpio(idM);

        return new ComposicionPersonal(new Personal(profesores, profesoresH), new Personal(bibliotecarios, bibliotecariosH), new Personal(instructores, instructoresH), new Personal(psicopedagogos, psicopedagogosH), alumnosMaestros, new Personal(noDocentes, noDocentesH), profesoresInactivos, otrosDocInactivos, noDocInactivos);
    }

    private void datosTotalPersonal(HttpServletResponse response, int[] total, PdfPCell celda) throws DocumentException {
        formatoCeldaTotales(celda, Color.YELLOW);

        for (int i = 0; i < total.length; i++) {
            celda = new PdfPCell(new Paragraph(valorCelda(total[i], 0), fuenteEncabezado));
            formatoCeldaTotales(celda, Color.YELLOW);
        }

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void datosPersonal(int[] total, ComposicionPersonal cp) {
        int valor;
        valor = cp.getTotalDocentes();
        total[0] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getTotalDocentesHembras();
        total[1] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getDocentes().getTotal();
        total[2] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        Personal ndoc = cp.getNoDocentes();
        valor = ndoc.getTotal();
        total[3] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = ndoc.getHembras();
        total[4] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getOtrosDocentesTotal();
        total[5] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getBibliotecarios().getTotal();
        total[6] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getInstructoresArte().getTotal();
        total[7] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getPsicopedagogos().getTotal();
        total[8] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getTotalInactivos();
        total[9] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getTotalProfesoresInactivos();
        total[10] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getOtrosDocentesInactivos();
        total[11] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        valor = cp.getNoDocentesInactivos();
        total[12] += valor;
        elementos.add(new PdfPCell(new Paragraph(valorCelda(valor, 1), fuenteCuerpo)));
        crearCuerpoTabla();
    }

    private void encabezadoPersonal() {
        formatoColumnasCombinadasEncabezado("Docentes", 3);
        formatoColumnasCombinadasEncabezado("No Docentes", 2);
        formatoColumnasCombinadasEncabezado("Otros Docentes", 4);
        formatoColumnasCombinadasEncabezado("Inactivos", 4);

        titulos = new ArrayList<>();
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Hembras", fuenteEncabezado));
        titulos.add(new Paragraph("F.Aula", fuenteEncabezado));
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Hembras", fuenteEncabezado));
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Bibliot", fuenteEncabezado));
        titulos.add(new Paragraph("I.Arte", fuenteEncabezado));
        titulos.add(new Paragraph("Psicopg", fuenteEncabezado));
        titulos.add(new Paragraph("Total", fuenteEncabezado));
        titulos.add(new Paragraph("Profesor", fuenteEncabezado));
        titulos.add(new Paragraph("O.Doct", fuenteEncabezado));
        titulos.add(new Paragraph("N.Doct", fuenteEncabezado));
        crearEncabezadoTabla();
    }

    private void formatoCeldaTotales(PdfPCell celda, Color color) {
        celda.setBorderColor(Color.BLACK);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setBackgroundColor(color);
        celda.setExtraParagraphSpace(3f);
        celda.setPaddingTop(3);
        table.addCell(celda);
    }


    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los grupos por rangos de T.M.
     *
     * @param gr       Listado de grupos por rangos.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_GruposRangosTM(List<GrupoRango> gr, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "GruposRangos";
        subTituloTabla = "Grupos por Rangos de Matrícula (Curso Diurno)";
        subTituloTabla1 = "Nivel: Técnico Medio";

        propiedadesPDF(nombrePDF);

        table = new PdfPTable(8);
        float[] anchoColumnas = {0.5f, 0.8f, 0.7f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f};
        table.setWidths(anchoColumnas);

        datosRangoGrupos(gr);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Esta funcionalidad gestiona el proceso para exportar el documento PDF de los grupos por rangos de O.C.
     *
     * @param gr       Listado de grupos por rangos.
     * @param response Respuesta.
     * @throws DocumentException     Si no se encuentra el documento.
     * @throws FileNotFoundException Si no se encuentra el fichero.
     */
    public void generarDocumentoPdf_GruposRangosOC(List<GrupoRango> gr, HttpServletResponse response) throws FileNotFoundException, DocumentException {
        String nombrePDF = "GruposRangos";
        subTituloTabla = "Grupos por Rangos de Matrícula (Curso Diurno)";
        subTituloTabla1 = "Nivel: Obrero Calificado";

        propiedadesPDF(nombrePDF);

        table = new PdfPTable(8);
        float[] anchoColumnas = {0.5f, 0.8f, 0.7f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f};
        table.setWidths(anchoColumnas);

        datosRangoGrupos(gr);

        try {
            cuadroDialogo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void datosRangoGrupos(List<GrupoRango> gr) {

        formatoFilasCombinadasEncabezado("Año", 2, Color.YELLOW);
        formatoFilasCombinadasEncabezado("Concepto", 2, Color.YELLOW);
        formatoFilasCombinadasEncabezado("Total", 2, Color.YELLOW);
        formatoColumnasCombinadasEncabezado("Rangos de Matrícula", 5);

        titulos = new ArrayList<>();
        titulos.add(new Paragraph("1 - 15", fuenteEncabezado));
        titulos.add(new Paragraph("16 - 30", fuenteEncabezado));
        titulos.add(new Paragraph("31 - 35", fuenteEncabezado));
        titulos.add(new Paragraph("36 - 40", fuenteEncabezado));
        titulos.add(new Paragraph("+ 40", fuenteEncabezado));
        crearEncabezadoTabla();

        int lon = gr.size();
        Font fuente = fuenteCuerpo;

        for (int i = 0; i < lon; i++) {
            GrupoRango g = gr.get(i);

            if (i < lon - 1) {
                formatoFilasCombinadasEncabezado(annoEstudioString(i + 1), 4, Color.WHITE);
            } else {
                formatoFilasCombinadasEncabezado("Total", 4, Color.ORANGE);
                fuente = fuenteEncabezado;
            }
            estiloValorCeldaCentrado(new PdfPCell(new Paragraph("Grupos", fuente)));
            for (int j = 0; j < 6; j++) {
                estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(g.getGrupos()[j], 1), fuente)));
            }
            estiloValorCeldaCentrado(new PdfPCell(new Paragraph("Matrícula", fuente)));
            for (int j = 0; j < 6; j++) {
                estiloValorCeldaCentrado(new PdfPCell(new Paragraph(valorCelda(g.getMatrAtendida()[j], 1), fuente)));
            }
            formatoCeldaTotales(new PdfPCell(new Paragraph("Alumnos/Grupos", fuente)), Color.LIGHT_GRAY);
            for (int j = 0; j < 6; j++) {
                formatoCeldaTotales(new PdfPCell(new Paragraph(valorCelda(g.getAlumnosGrupo()[j], 0), fuente)), Color.LIGHT_GRAY);
            }
            formatoCeldaTotales(new PdfPCell(new Paragraph("% Matrícula Total", fuente)), Color.LIGHT_GRAY);
            for (int j = 0; j < 6; j++) {
                formatoCeldaTotales(new PdfPCell(new Paragraph(valorCelda(g.getPorcMatr()[j], 0) + " %", fuente)), Color.LIGHT_GRAY);
            }
        }
    }

    public String annoEstudioString(int anno) {
        if (anno == 1) {
            return "I Año";
        } else if (anno == 2) {
            return "II Año";
        } else if (anno == 3) {
            return "III Año";
        }
        return "IV Año";
    }
}
