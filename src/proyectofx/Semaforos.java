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
    private int posX;
    private int posY;
    private boolean esverde;
    private static final int TAMANO_LUZ = 20;
    private static final int ESPACIO_LUCES = 25;

    public Semaforos(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.esverde = false;
    }

    public void dibujar(Graphics2D g) {
        // Dibujar luz roja
        g.setColor(esverde ? Color.DARK_GRAY : Color.RED);
        g.fillOval(posX, posY, TAMANO_LUZ, TAMANO_LUZ);

        // Dibujar luz amarilla
        g.setColor(Color.DARK_GRAY);
        g.fillOval(posX, posY + ESPACIO_LUCES, TAMANO_LUZ, TAMANO_LUZ);

        // Dibujar luz verde
        g.setColor(esverde ? Color.GREEN : Color.DARK_GRAY);
        g.fillOval(posX, posY + ESPACIO_LUCES * 2, TAMANO_LUZ, TAMANO_LUZ);
    }

    public void setGreen(boolean esverde) {
        this.esverde = esverde;
    }

    public boolean esverde() {
        return esverde;
    }
}
