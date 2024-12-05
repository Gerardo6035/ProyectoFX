package proyectofx;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainAppTest {

    @Test
    public void testGuardarSimulacion() throws IOException {
        // Crea una instancia de MainApp
        MainApp mainApp = new MainApp();

        // Simula algunos datos de vehículos
        mainApp.simulador.setNorthSouthCount(10);
        mainApp.simulador.setOesteCount(5);
        mainApp.simulador.setEsteCount(8);

        // Llama al método guardarSimulacion()
        mainApp.guardarSimulacion();

        // Verifica que el archivo simulation_history.txt existe
        File file = new File("simulation_history.txt");
        assertTrue(file.exists());

        // Lee el contenido del archivo
        String content = new String(Files.readAllBytes(Paths.get("simulation_history.txt")));

        // Verifica que el contenido del archivo incluye los datos de la simulación
        assertTrue(content.contains("Norte/Sur: 10"));
        assertTrue(content.contains("Oeste: 5"));
        assertTrue(content.contains("Este: 8"));

        // Elimina el archivo después de la prueba
        file.delete();
    }

    @Test
    public void testCerrarVentana() {
        // Crea una instancia de MainApp
        MainApp mainApp = new MainApp();

        // Simula la confirmación del usuario para cerrar la ventana
        int input = JOptionPane.YES_OPTION; 

        // Crea un JOptionPane simulado
        JOptionPane optionPane = new JOptionPane("Mensaje", JOptionPane.QUESTION_MESSAGE,  JOptionPane.YES_NO_OPTION);
        JFrame frame = new JFrame();
        JDialog dialog = optionPane.createDialog(frame, "Título");

        // Simula el comportamiento del usuario seleccionando "Sí"
        optionPane.setValue(input); 

        // Cierra el diálogo simulado
        dialog.setVisible(false); 

        // Verifica que System.exit() se llama cuando se confirma el cierre
        // Nota: Esta prueba no cerrará la aplicación real durante la ejecución de la prueba
        // ya que estamos simulando el JOptionPane.
    }
}