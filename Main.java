// Archivo principal de arranque: Main.java

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaProcesamiento ventana = new VentanaProcesamiento();
                ventana.setVisible(true);
            }
        });
    }
}

