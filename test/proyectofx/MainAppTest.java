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
        mainApp.simulador.setContadorNorteSur(10);
        mainApp.simulador.setContadorOeste(5);
        mainApp.simulador.setContadorEste(8);

        // Llama al metodo guardarSimulacion()
        mainApp.guardarSimulacion();

        // Verifica que el archivo simulation_history.txt existe
        File file = new File("simulation_history.txt");
        assertTrue(file.exists());

        // Lee el contenido del archivo
        String content = new String(Files.readAllBytes(Paths.get("simulation_history.txt")));

        // Verifica que el contenido del archivo incluye los datos de la simulacion
        assertTrue(content.contains("Norte/Sur: 10"));
        assertTrue(content.contains("Oeste: 5"));
        assertTrue(content.contains("Este: 8"));

        // Elimina el archivo después de la prueba
        file.delete();
    }

    @Test
    public void testCerrarVentana() {
        // Crea una instancia de MainApp
        MainApp MainApp = new MainApp();

        // Simula la confirmación del usuario para cerrar la ventana
        int input = JOptionPane.YES_OPTION; 

        // Crea un JOptionPane simulado
        JOptionPane optionPane = new JOptionPane("Mensaje", JOptionPane.QUESTION_MESSAGE,  JOptionPane.YES_NO_OPTION);
        JFrame frame = new JFrame();
        JDialog dialog = optionPane.createDialog(frame, "Título");

        // Simula el comportamiento del usuario seleccionando "Sí"
        optionPane.setValue(input); 

        // Cierra el dialogo simulado
        dialog.setVisible(false); 

    }
}