import java.io.File;
import java.io.FileWriter;

public class ProcesadorColombia implements ProcesadorDocumento {

    @Override
    public String procesar(Documento documento) throws Exception {
        if (!"CO".equalsIgnoreCase(documento.getPais())) {
            throw new Exception("El procesador de Colombia solo acepta documentos de pais CO.");
        }

        validarTipo(documento.getTipo());
        validarFormato(documento.getFormato());

        File archivo = new File(documento.getRutaArchivo());
        if (!archivo.exists()) {
            throw new Exception("El archivo no existe en la ruta especificada.");
        }

        long tamano = archivo.length();

        // Crear carpeta de salida y archivo de reporte
        File carpetaSalida = new File(archivo.getParentFile(), "salida_CO");
        carpetaSalida.mkdirs();

        File reporte = new File(carpetaSalida, documento.getNombre() + ".log");

        try (FileWriter writer = new FileWriter(reporte)) {
            writer.write("Resultado de procesamiento (Colombia)\n");
            writer.write("Documento: " + documento.getNombre() + "\n");
            writer.write("Tipo: " + documento.getTipo() + "\n");
            writer.write("Formato: " + documento.getFormato() + "\n");
            writer.write("Ruta original: " + documento.getRutaArchivo() + "\n");
            writer.write("Tamaño: " + tamano + " bytes\n");
        }

        return "Colombia - Procesando " + documento.getTipo()
                + " (" + documento.getNombre() + ") en formato "
                + documento.getFormato() + " | Ruta: " + documento.getRutaArchivo()
                + " | Tamaño: " + tamano + " bytes"
                + " | Archivo de salida: " + reporte.getAbsolutePath()
                + " ... OK";
    }

    private void validarTipo(String tipo) throws Exception {
        for (String t : ConfiguracionDocumentos.TIPOS_DOCUMENTO) {
            if (t.equalsIgnoreCase(tipo)) {
                return;
            }
        }
        throw new Exception("Tipo de documento no soportado: " + tipo);
    }

    private void validarFormato(String formato) throws Exception {
        for (String f : ConfiguracionDocumentos.FORMATOS_DOCUMENTO) {
            if (f.equalsIgnoreCase(formato)) {
                return;
            }
        }
        throw new Exception("Formato de documento no soportado: " + formato);
    }
}

