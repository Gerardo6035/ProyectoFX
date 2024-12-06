
package proyectofx;


import javax.swing.*;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class MainApp extends JFrame {
    public PanelPrincipal simulador;
    public PanelPrincipal getSimulador() {
        return simulador;
    }

    public void setSimulador(PanelPrincipal simulador) {
        this.simulador = simulador;
    }
    private Timer timerActualizarSimulacion;
    private Timer timerGeneradorVehiculo;
    private Timer timerCambiarSemaforos;
    private int contadorVertical = 0;
    private int contadorHorizontal = 0;

    public MainApp() {
        setTitle("Simulación de Tráfico");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Cambiamos esto para manejar el cierre manualmente
        setLocationRelativeTo(null);
        
        simulador = new PanelPrincipal();
        add(simulador);
        
        manejoTimers();
    
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                cerrarVentana();
            }
        });
    }


     private void cerrarVentana() {
        int confirmation = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea salir?",
            "Confirmar Salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
                
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            guardarSimulacion();
            JOptionPane.showMessageDialog(
                this,
                    "\t\t DATOS RECOPILADOS:\n Vehículos Sur: "+ simulador.getContadorNorteSur()+"\n Vehículos Este: " + simulador.getesteCount()+
                            "\n Vehículos Oeste: " + simulador.getoesteCount()+"\n\n"+
                "Los datos de la simulación se han guardado correctamente en: \n                            simulation_history.txt",
                "Guardado Exitoso",
                JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
     
    private void manejoTimers() {
        // Timer principal para la actualización de la simulación (60 FPS)
        timerActualizarSimulacion = new Timer(16, e -> {
            simulador.actualizarCoches();
            repaint();
        });
        timerActualizarSimulacion.start();

        // Timer para generar nuevos vehículos
        timerGeneradorVehiculo = new Timer(2000, e -> {
            simulador.agregarCoche();
        });
        timerGeneradorVehiculo.start();

        // Timer para cambiar los semáforos
        timerCambiarSemaforos = new Timer(10000, e -> {
            simulador.cambiarLuzSemaforos();
        });
        timerCambiarSemaforos.start();
    }

     public void guardarSimulacion() {
        try {
            FileWriter writer = new FileWriter("simulation_history.txt", true);
            LocalDateTime now = LocalDateTime.now();
            String data = String.format(
                "%s - Norte/Sur: %d, Oeste: %d%n, Este: %d%n",
                now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                simulador.getContadorNorteSur(),
                simulador.getoesteCount(),
                simulador.getesteCount()
            );
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}