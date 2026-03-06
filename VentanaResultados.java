import javax.swing.*;
import java.awt.*;

public class VentanaResultados extends JFrame {

    public VentanaResultados(String texto) {
        setTitle("Resultados de Procesamiento de Documentos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(area);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }
}

