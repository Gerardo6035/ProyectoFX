package proyectofx;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;

public class SonidosTest {

    @Test
    public void testReproducirVerde() {
        Sonidos sonidos = new Sonidos();

        // Verifica que la ruta del archivo de sonido para "verde" sea correcta
        String expectedPath = "C:\\Users\\52229\\Downloads\\S21002431CruceVial\\ProyectoFX\\resources\\Avance.wav";
        assertEquals(expectedPath, sonidos.AVANCE_SONIDO); // Necesitas hacer que 'avance' sea accesible (público o con un getter)

        // Puedes verificar que el archivo exista
        File audioFile = new File(sonidos.AVANCE_SONIDO);
        assertTrue(audioFile.exists());
    }

    @Test
    public void testReproducirRojo() {
        Sonidos sonidos = new Sonidos();

        // Verifica que la ruta del archivo de sonido para "rojo" sea correcta
        String expectedPath = "C:\\Users\\52229\\Downloads\\S21002431CruceVial\\ProyectoFX\\resources\\Detengase.wav";
        assertEquals(expectedPath, sonidos.DETENGASE_SONIDO); // Necesitas hacer que 'detengase' sea accesible

        // Puedes verificar que el archivo exista
        File audioFile = new File(sonidos.DETENGASE_SONIDO);
        assertTrue(audioFile.exists());
    }

    @Test
    public void testReproducirAmbos() {
        Sonidos sonidos = new Sonidos();

        assertNotNull(sonidos.AVANCE_SONIDO);
        assertNotNull(sonidos.DETENGASE_SONIDO);
    }
}
