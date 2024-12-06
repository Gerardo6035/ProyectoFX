package proyectofx;

import org.junit.Test;
import static org.junit.Assert.*;

public class CocheDatosTest {
    
    @Test
    public void testCrearCocheAleatorio() {
        // Prueba con dimensiones aleatorias
        int anchura = 800;
        int altura = 600;
        
        // Ejecutamos el método varias veces para probar diferentes direcciones
        for (int i = 0; i < 20; i++) {
            Coche coche = CocheDatos.crearCocheAleatorio(anchura, altura);
            
            // Verificamos que el coche no sea nulo
            assertNotNull("El coche no debería ser nulo", coche);
            
            // Verificamos que las coordenadas estén dentro de rangos razonables
            assertTrue("Coordenada x fuera de rango", 
                coche.getPosX() >= -50 && coche.getPosX() <= anchura + 50);
            assertTrue("Coordenada y fuera de rango", 
                coche.getPosY() >= -50 && coche.getPosY() <= altura + 50);
            
            // Verificamos que la velocidad sea correcta
            double velocidadTotal = Math.sqrt(
                coche.getSpeedX() * coche.getSpeedX() + 
                coche.getSpeedY() * coche.getSpeedY()
            );
            assertEquals("La velocidad debería ser 6", 
                CocheDatos.VELOCIDAD_COCHE, velocidadTotal, 0.001);
        }
    }
}