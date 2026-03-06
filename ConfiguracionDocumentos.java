public final class ConfiguracionDocumentos {

    private ConfiguracionDocumentos() {
        // Evitar instanciación
    }

    public static final String[] TIPOS_DOCUMENTO = {
            "Facturas Electronicas",
            "Contratos Legales",
            "Reportes Financieros",
            "Certificados Digitales",
            "Declaraciones Tributarias"
    };

    public static final String[] FORMATOS_DOCUMENTO = {
            ".pdf", ".doc", ".docx", ".md", ".csv", ".txt", ".xlsx"
    };
}

