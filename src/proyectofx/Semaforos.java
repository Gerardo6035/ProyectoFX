/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofx;

/**
 *
 * @author alexi
 */

import java.awt.*;

public class Semaforos {
    private int x;
    private int y;
    private boolean esverde;
    private static final int tamano_luz = 20;
    private static final int espacio = 25;

    public Semaforos(int x, int y) {
        this.x = x;
        this.y = y;
        this.esverde = false;
    }

    public void dibujar(Graphics2D g) {
        // Dibujar luz roja
        g.setColor(esverde ? Color.DARK_GRAY : Color.RED);
        g.fillOval(x, y, tamano_luz, tamano_luz);

        // Dibujar luz amarilla
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x, y + espacio, tamano_luz, tamano_luz);

        // Dibujar luz verde
        g.setColor(esverde ? Color.GREEN : Color.DARK_GRAY);
        g.fillOval(x, y + espacio * 2, tamano_luz, tamano_luz);
    }

    public void setGreen(boolean esverde) {
        this.esverde = esverde;
    }

    public boolean esverde() {
        return esverde;
    }
}
