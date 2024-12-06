package proyectofx;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class PanelPrincipalTest {

    @Test
    public void testActualizarCoches() {
        PanelPrincipal panel = new PanelPrincipal();
        List<Coche> coches = new ArrayList<>();

        // Crea algunos coches con diferentes direcciones y velocidades
        coches.add(new Coche(440, 100, 0, 5));  // Sur
        coches.add(new Coche(100, 270, 5, 0));  // Este
        coches.add(new Coche(700, 340, -5, 0)); // Oeste

        panel.carro = coches; // Asigna la lista de coches al panel

        // Simula la actualización de los coches
        panel.actualizarCoches();

        // Verifica que las posiciones de los coches se hayan actualizado
        assertEquals(440, coches.get(0).getPosX(), 0.001); // Sur: x se mantiene igual
        assertEquals(105, coches.get(0).getPosY(), 0.001); // Sur: y aumenta

        assertEquals(105, coches.get(1).getPosX(), 0.001); // Este: x aumenta
        assertEquals(270, coches.get(1).getPosY(), 0.001); // Este: y se mantiene igual

        assertEquals(695, coches.get(2).getPosX(), 0.001); // Oeste: x disminuye
        assertEquals(340, coches.get(2).getPosY(), 0.001); // Oeste: y se mantiene igual
    }

    @Test
    public void testVerificarAlto() {
        PanelPrincipal panel = new PanelPrincipal();
        Coche cocheSur = new Coche(440, 200, 0, 5); // Coche en dirección sur

        // Simula un semáforo en rojo para la dirección sur
        Semaforos semaforoSur = panel.semaforos.get(0);
        semaforoSur.setGreen(false);

        // Verifica que el coche deba detenerse en el semáforo en rojo
        assertTrue(panel.verificarAlto(cocheSur));

        // Simula un semáforo en verde
        semaforoSur.setGreen(true);

        // Verifica que el coche no deba detenerse con el semáforo en verde
        assertFalse(panel.verificarAlto(cocheSur));
    }

    @Test
    public void testAgregarCoche() {
        PanelPrincipal panel = new PanelPrincipal();
        int numCochesInicial = panel.carro.size();

        panel.agregarCoche();

        // Verifica que se haya agregado un coche a la lista
        assertEquals(numCochesInicial + 1, panel.carro.size());
    }

    @Test
    public void testCambiarLuzSemaforos() {
        PanelPrincipal panel = new PanelPrincipal();

        // Simula que el semáforo vertical está en verde inicialmente
        panel.semaforos.get(0).setGreen(true);

        // Cambia las luces de los semáforos
        panel.cambiarLuzSemaforos();

        // Verifica que el semáforo vertical ahora esté en rojo
        assertFalse(panel.semaforos.get(0).esverde());

        // Verifica que los semáforos horizontales ahora estén en verde
        assertTrue(panel.semaforos.get(1).esverde());
        assertTrue(panel.semaforos.get(2).esverde());
    }

    @Test
    public void testActualizarContador() {
        PanelPrincipal panel = new PanelPrincipal();
        Coche cocheSur = new Coche(440, 100, 0, 5); // Sur
        Coche cocheEste = new Coche(100, 270, 5, 0); // Este
        Coche cocheOeste = new Coche(700, 340, -5, 0); // Oeste

        panel.actualizarContador(cocheSur);
        assertEquals(1, panel.getContadorNorteSur());

        panel.actualizarContador(cocheEste);
        assertEquals(1, panel.getesteCount());

        panel.actualizarContador(cocheOeste);
        assertEquals(1, panel.getoesteCount());
    }

    @Test
    public void testYaCruzo() {
        PanelPrincipal panel = new PanelPrincipal();

        // Simula un coche que ha cruzado el límite horizontal
        Coche cocheFuera = new Coche(1000, 300, 5, 0);
        assertTrue(panel.yaCruzo(cocheFuera));

        // Simula un coche que ha cruzado el límite vertical
        cocheFuera = new Coche(440, -100, 0, 5);
        assertTrue(panel.yaCruzo(cocheFuera));

        // Simula un coche dentro de los límites
        Coche cocheDentro = new Coche(400, 300, 5, 0);
        assertFalse(panel.yaCruzo(cocheDentro));
    }
}