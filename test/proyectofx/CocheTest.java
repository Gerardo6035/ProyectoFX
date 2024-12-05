package proyectofx;

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CocheTest {

    @Test
    public void testMover() {
        Coche coche = new Coche(100, 100, 2, 3);
        coche.mover();
        assertEquals(102, coche.getX(), 0.001);
        assertEquals(103, coche.getY(), 0.001);
    }

    @Test
    public void testDibujar() {
        // Crea una imagen para dibujar el coche
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Crea un coche
        Coche coche = new Coche(50, 50, 2, 0); // Moviéndose hacia la derecha

        // Dibuja el coche en la imagen
        coche.dibujar(g2d);

        // Verifica que algunos píxeles del área del coche tengan el color esperado
        // (esto es una prueba básica, podrías hacerla más exhaustiva)
        assertColorEquals(coche.color, image.getRGB(60, 55)); // Color del cuerpo
        assertColorEquals(Color.CYAN, image.getRGB(60, 50)); // Color de la ventana
        assertColorEquals(Color.YELLOW, image.getRGB(85, 55)); // Color de la luz delantera

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
    public void testGetBounds() {
        Coche coche = new Coche(100, 100, 2, 3);
        Rectangle bounds = coche.getBounds();
        assertEquals(100, bounds.x);
        assertEquals(100, bounds.y);
        assertEquals(40, bounds.width);  // WIDTH constante en la clase Coche
        assertEquals(20, bounds.height); // HEIGHT constante en la clase Coche
    }

    // Puedes agregar más pruebas para los getters si es necesario
    // ...
}