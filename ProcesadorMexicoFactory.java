public class ProcesadorMexicoFactory extends ProcesadorDocumentosFactory {

    @Override
    public ProcesadorDocumento crearProcesador() {
        return new ProcesadorMexico();
    }
}

