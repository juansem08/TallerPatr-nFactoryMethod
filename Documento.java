public class Documento {
    private String nombre;
    private String tipo;
    private String formato;
    private String pais;
    private String rutaArchivo;

    public Documento(String nombre, String tipo, String formato, String pais, String rutaArchivo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.formato = formato;
        this.pais = pais;
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFormato() {
        return formato;
    }

    public String getPais() {
        return pais;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }
}

