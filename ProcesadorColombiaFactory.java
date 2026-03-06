public class ProcesadorColombiaFactory extends ProcesadorDocumentosFactory {

    @Override
    public ProcesadorDocumento crearProcesador() {
        return new ProcesadorColombia();
    }
}

