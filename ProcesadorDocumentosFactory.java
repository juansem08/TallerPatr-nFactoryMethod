public abstract class ProcesadorDocumentosFactory {

    public abstract ProcesadorDocumento crearProcesador();

    // Procesamiento por lotes con manejo de errores
    public String procesarLote(Documento[] documentos) {
        StringBuilder sb = new StringBuilder();
        ProcesadorDocumento procesador = crearProcesador();
        for (Documento doc : documentos) {
            try {
                String resultado = procesador.procesar(doc);
                sb.append(resultado).append("\n");
            } catch (Exception e) {
                sb.append("Error procesando '")
                        .append(doc.getNombre())
                        .append("' (")
                        .append(doc.getPais())
                        .append("): ")
                        .append(e.getMessage())
                        .append("\n");
            }
        }
        return sb.toString();
    }
}

