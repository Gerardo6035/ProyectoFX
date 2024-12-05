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
import java.awt.geom.*;

public class Coche {
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    public Color color;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 20;

    public Coche(double x, double y, double speedX, double speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        // Color aleatorio para los vehículos (evitando colores muy claros)
        this.color = new Color(
            (int)(Math.random() * 200),
            (int)(Math.random() * 200),
            (int)(Math.random() * 200)
        );
    }

    public void mover() {
        x += speedX;
        y += speedY;
    }

    public void dibujar(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.translate(x, y);

        // Rotar según la dirección
        if (speedX != 0) {
            g.rotate(speedX > 0 ? 0 : Math.PI, WIDTH/2, HEIGHT/2);
        } else if (speedY > 0) {
            g.rotate(Math.PI/2, WIDTH/2, HEIGHT/2);
        }

        // Cuerpo principal del auto
        g.setColor(color);
        g.fillRoundRect(0, 0, WIDTH, HEIGHT, 8, 8);
        
        // Techo
        g.fillRoundRect(WIDTH/4, -5, WIDTH/2, HEIGHT/2, 5, 5);
        
        // Ventanas
        g.setColor(Color.CYAN);
        g.fillRect(WIDTH/3, -2, WIDTH/4, HEIGHT/3);
        
        // Luces delanteras
        g.setColor(Color.YELLOW);
        g.fillRect(WIDTH-5, 2, 5, 5);
        g.fillRect(WIDTH-5, HEIGHT-7, 5, 5);
        
        // Luces traseras
        g.setColor(Color.RED);
        g.fillRect(0, 2, 3, 5);
        g.fillRect(0, HEIGHT-7, 3, 5);
        
        //LLANTAS
        g.setColor(Color.BLACK);
        g.fillOval(5, HEIGHT, 7, 9);
        
        g.setColor(Color.BLACK);
        g.fillOval(26, HEIGHT, 7, 9);
        
        g.setTransform(old);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
    }

    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSpeedX() { return speedX; }
    public double getSpeedY() { return speedY; }

 
}