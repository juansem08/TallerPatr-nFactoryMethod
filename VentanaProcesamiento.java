import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VentanaProcesamiento extends JFrame {

    private JTextField txtArchivo;
    private JComboBox<String> comboPais;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboFormato;
    private JTextArea areaLog;

    private File archivoSeleccionado;

    private final List<Documento> documentos = new ArrayList<>();

    public VentanaProcesamiento() {
        setTitle("Procesamiento de Documentos (Factory Method)");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panelInputs = new JPanel(new GridLayout(5, 2, 5, 5));

        txtArchivo = new JTextField();
        txtArchivo.setEditable(false);
        comboPais = new JComboBox<>(new String[]{"CO", "MX"});
        comboTipo = new JComboBox<>(ConfiguracionDocumentos.TIPOS_DOCUMENTO);
        comboFormato = new JComboBox<>(ConfiguracionDocumentos.FORMATOS_DOCUMENTO);

        JButton btnSeleccionar = new JButton("Seleccionar archivo...");

        panelInputs.add(new JLabel("Archivo:"));
        panelInputs.add(txtArchivo);

        panelInputs.add(new JLabel("País:"));
        panelInputs.add(comboPais);

        panelInputs.add(new JLabel("Tipo de documento:"));
        panelInputs.add(comboTipo);

        panelInputs.add(new JLabel("Formato:"));
        panelInputs.add(comboFormato);

        panelInputs.add(new JLabel(""));
        panelInputs.add(btnSeleccionar);

        JButton btnAgregar = new JButton("Agregar al lote");
        JButton btnProcesar = new JButton("Procesar lote");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnProcesar);

        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(areaLog);

        getContentPane().setLayout(new BorderLayout(5, 5));
        getContentPane().add(panelInputs, BorderLayout.NORTH);
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(scrollLog, BorderLayout.SOUTH);

        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                seleccionarArchivo();
            }
        });

        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregarDocumento();
            }
        });

        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                procesarLote();
            }
        });
    }

    private void seleccionarArchivo() {
        JFileChooser chooser = new JFileChooser();
        int resultado = chooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = chooser.getSelectedFile();
            txtArchivo.setText(archivoSeleccionado.getAbsolutePath());

            // Inferir formato desde la extensión
            String nombre = archivoSeleccionado.getName().toLowerCase();
            for (String formato : ConfiguracionDocumentos.FORMATOS_DOCUMENTO) {
                if (nombre.endsWith(formato)) {
                    comboFormato.setSelectedItem(formato);
                    break;
                }
            }
        }
    }

    private void agregarDocumento() {
        if (archivoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un archivo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = archivoSeleccionado.getName();
        String pais = (String) comboPais.getSelectedItem();
        String tipo = (String) comboTipo.getSelectedItem();
        String formato = (String) comboFormato.getSelectedItem();

        Documento doc = new Documento(nombre, tipo, formato, pais, archivoSeleccionado.getAbsolutePath());
        documentos.add(doc);
        areaLog.append("Agregado al lote: " + nombre + " | " + pais + " | " + tipo + " | " + formato + "\n");
    }

    private void procesarLote() {
        if (documentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay documentos en el lote.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<Documento> docsCO = new ArrayList<>();
        List<Documento> docsMX = new ArrayList<>();

        for (Documento doc : documentos) {
            if ("CO".equalsIgnoreCase(doc.getPais())) {
                docsCO.add(doc);
            } else if ("MX".equalsIgnoreCase(doc.getPais())) {
                docsMX.add(doc);
            }
        }

        StringBuilder textoFinal = new StringBuilder();

        if (!docsCO.isEmpty()) {
            ProcesadorDocumentosFactory factoryColombia = new ProcesadorColombiaFactory();
            textoFinal.append("=== Procesando lote Colombia ===\n");
            textoFinal.append(factoryColombia.procesarLote(
                    docsCO.toArray(new Documento[0]))
            );
            textoFinal.append("\n");
        }

        if (!docsMX.isEmpty()) {
            ProcesadorDocumentosFactory factoryMexico = new ProcesadorMexicoFactory();
            textoFinal.append("=== Procesando lote Mexico ===\n");
            textoFinal.append(factoryMexico.procesarLote(
                    docsMX.toArray(new Documento[0]))
            );
            textoFinal.append("\n");
        }

        if (textoFinal.length() == 0) {
            textoFinal.append("No hay documentos con pais CO o MX para procesar.\n");
        }

        VentanaResultados ventana = new VentanaResultados(textoFinal.toString());
        ventana.setVisible(true);
    }
}

