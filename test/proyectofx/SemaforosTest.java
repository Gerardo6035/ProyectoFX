package proyectofx;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SemaforosTest {

    @Test
    public void testDibujar() {
        // Crea una imagen para dibujar el semáforo
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Crea un semáforo
        Semaforos semaforo = new Semaforos(20, 20);

        // Dibuja el semáforo en rojo
        semaforo.dibujar(g2d);
        assertColorEquals(Color.RED, image.getRGB(25, 25)); // Rojo
        assertColorEquals(Color.DARK_GRAY, image.getRGB(25, 50)); // Amarillo
        assertColorEquals(Color.DARK_GRAY, image.getRGB(25, 75)); // Verde

        // Cambia el semáforo a verde
        semaforo.setGreen(true);

        // Dibuja el semáforo en verde
        semaforo.dibujar(g2d);
        assertColorEquals(Color.DARK_GRAY, image.getRGB(25, 25)); // Rojo
        assertColorEquals(Color.DARK_GRAY, image.getRGB(25, 50)); // Amarillo
        assertColorEquals(Color.GREEN, image.getRGB(25, 75)); // Verde

        g2d.dispose();
    }

    // Método auxiliar para comparar colores
    private void assertColorEquals(Color expected, int actualRGB) {
        Color actual = new Color(actualRGB);
        assertEquals(expected.getRed(), actual.getRed());
        assertEquals(expected.getGreen(), actual.getGreen());
        assertEquals(expected.getBlue(), actual.getBlue());
    }

    @Test
    public void testSetGreen() {
        Semaforos semaforo = new Semaforos(20, 20);
        assertFalse(semaforo.esverde()); // Inicialmente en rojo

        semaforo.setGreen(true);
        assertTrue(semaforo.esverde()); // Verde

        semaforo.setGreen(false);
        assertFalse(semaforo.esverde()); // Rojo de nuevo
    }

    @Test
    public void testEsVerde() {
        Semaforos semaforo = new Semaforos(20, 20);
        assertFalse(semaforo.esverde()); // Inicialmente en rojo
    }
}
