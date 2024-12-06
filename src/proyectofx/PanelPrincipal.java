/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofx;

/**
 *
 * @author alexi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;

public class PanelPrincipal extends JPanel {
    public List<Coche> carro;
    public List<Semaforos> semaforos;
    private Image fondoImagen;
    private Image fondoImagen2;
    private int contadorNorteSur;
    private int contadorOeste;
    private int contadorEste;
    private BufferedImage fondoBuffer;
    private Sonidos sonidoSistema = new Sonidos();
    
    // Constantes para las posiciones de las calles
    private static final int VERTICAL_STREET_X = 440;
    private static final int VERTICAL_STREET_WIDTH = 80;
    private static final int HORIZONTAL_STREET_Y = 250;
    private static final int HORIZONTAL_STREET_HEIGHT = 120;
    private static final Rectangle INTERSECTION = new Rectangle(325, 225, 100, 100);

    public void setContadorNorteSur(int contadorNorteSur) {
        this.contadorNorteSur = contadorNorteSur;
    }

    public void setContadorOeste(int contadorOeste) {
        this.contadorOeste = contadorOeste;
    }

    public void setContadorEste(int contadorEste) {
        this.contadorEste = contadorEste;
    }


    public PanelPrincipal() {
        carro = new ArrayList<>();
        semaforos = new ArrayList<>();
        setDoubleBuffered(true);
        inicializarSemaforos();
        setPreferredSize(new Dimension(800, 600)); 
        cargarImagenes();
        setBackground(new Color(34, 139, 34)); // Verde oscuro para simular césped
    }
    
    private void verificarBuffer() {
        int width = getWidth();
        int height = getHeight();
        
        if (width <= 0 || height <= 0) return;
        
        if (fondoBuffer == null || 
            fondoBuffer.getWidth() != width || 
            fondoBuffer.getHeight() != height) {
            
            fondoBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = fondoBuffer.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Dibujar elementos estáticos
            crearEdificioIndividual(g2d, 20, 0, 100, 170);
            crearEdificioIndividual(g2d, 120, 0, 100, 170);
            crearEdificioIndividual(g2d, 220, 0, 100, 170);
            crearEdificioIndividual(g2d, 320, 0, 100, 170);
            
            crearEdificioIndividual(g2d, 690, 0, 100, 200);
            crearEdificioIndividual(g2d, 850, 0, 100, 200);
            crearEdificioIndividual(g2d, 530, 0, 100, 200);
            
            crearEdificioIndividual(g2d, 20, 500, 400, 170);
            
            crearCalles(g2d);
            crearLineas(g2d);
            
            crearFacultad(g2d);
            crearDecoraciones(g2d);
            g2d.dispose();
        }
    }
     private void cargarImagenes() {
        try {
           
            String imagePath = "C:\\Users\\52229\\Downloads\\S21002431CruceVial\\ProyectoFX\\resources\\maxresdefault.jpg";
            fondoImagen = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            
            fondoImagen = null;
            fondoImagen2 = null;
        }
    }
     
    private void inicializarSemaforos() {
    semaforos.add(new Semaforos(VERTICAL_STREET_X - 40, 200)); // Adjusted for vertical lanes
    semaforos.add(new Semaforos(VERTICAL_STREET_X + VERTICAL_STREET_WIDTH + 10, 200)); // Eastbound
    semaforos.add(new Semaforos(VERTICAL_STREET_X - 40, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT + 10)); // Westbound
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        verificarBuffer();
        
        if (fondoBuffer != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(fondoBuffer, 0, 0, this);
            
            // Dibujar elementos dinámicos
            for (Semaforos light : semaforos) {
                light.dibujar(g2d);
            }
            
            for (Coche vehicle : carro) {
                vehicle.dibujar(g2d);
            }
            
            mostrarEstadisticas(g2d);
        }
    }

    
   
   private void crearFacultad(Graphics2D g2d){
    //CREACION FACULTAD
    g2d.setColor(Color.lightGray);
    g2d.fillRect(VERTICAL_STREET_X+100,570, getWidth(), getHeight());
    if (fondoImagen != null) {
            // La imagen se dibuja debajo de la carretera horizontal
            g2d.drawImage(fondoImagen, 
                         550, // x position
                         600, // y position (debajo de la carretera)
                         400, // width
                         170, // height
                         this);
        }
    
   
   }
   
private void crearEdificioIndividual(Graphics2D g2d, int x, int y, int width, int height) {
    Random rand = new Random();
    
    // Color base del edificio (tonos grises)
    Color colorBase = new Color(
        rand.nextInt(30) + 100,
        rand.nextInt(30) + 100,
        rand.nextInt(30) + 100
    );
    
    // Calcular la posición Y real del edificio
    int edificioY = y + HORIZONTAL_STREET_Y - height;
    
    // Dibujar el cuerpo principal del edificio
    g2d.setColor(colorBase);
    g2d.fillRect(x, edificioY, width, height);
    
    // Añadir efecto de sombra
    g2d.setColor(colorBase.darker());
    g2d.fillRect(x + width - 10, edificioY, 10, height);
    
    // Dibujar ventanas
    int ventanaSize = 10;
    int espaciado = 20;
    
    // Calcular el número de pisos basado en la altura del edificio
    int numPisos = (height - 35) / espaciado; // Restamos 35 para dejar espacio en la parte superior e inferior
    
    for (int piso = 0; piso < numPisos; piso++) {
        int pisoY = edificioY + 15 + (piso * espaciado);
        
        // Calcular el número de ventanas por piso basado en el ancho
        int numVentanasPorPiso = (width - 25) / espaciado; // 25 es el espacio total en los bordes
        
        for (int ventana = 0; ventana < numVentanasPorPiso; ventana++) {
            int ventanaX = x + 10 + (ventana * espaciado);
            
            // Algunas ventanas estarán apagadas (grises)
            if (rand.nextDouble() < 0.3) {
                g2d.setColor(new Color(100, 100, 100));
            } else {
                g2d.setColor(new Color(255, 255, 224));
            }
            
            g2d.fillRect(ventanaX, pisoY, ventanaSize, ventanaSize);
        }
    }
    
    // Añadir techo decorativo
    int[] xPoints = {x, x + width/2, x + width};
    int[] yPoints = {edificioY, 
                    edificioY - 20,
                    edificioY};
    g2d.setColor(colorBase.darker().darker());
    g2d.fillPolygon(xPoints, yPoints, 3);
    
    // Añadir línea base del edificio
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(x, y + HORIZONTAL_STREET_Y, width, 5);
}


   private void crearDecoraciones(Graphics2D g2d) {
    // Árboles entre las calles horizontales
    crearArboles(g2d, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT + 20, 8); // Primera fila de árboles
    crearArboles(g2d, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT - 130, 5); // Segunda fila de árboles
   
    // Crear bancos en el área verde
    crearBancos(g2d);
    
    // Crear faroles
    crearFaroles(g2d);
}

private void crearArboles(Graphics2D g2d, int y, int cantidad) {
    int espaciado = getWidth() / (cantidad + 1);
    for (int i = 1; i <= cantidad; i++) {
        int x = i * espaciado;
        // Evitar dibujar árboles en la calle vertical
        if (Math.abs(x - VERTICAL_STREET_X) < VERTICAL_STREET_WIDTH + 20) {
            continue;
        }
        
        // Tronco del árbol
        g2d.setColor(new Color(139, 69, 19)); // Marrón
        g2d.fillRect(x - 5, y - 20, 10, 25);
        
        // Copa del árbol
        g2d.setColor(new Color(0, 100, 0)); // Verde oscuro
        g2d.fillOval(x - 20, y - 50, 40, 40);
        g2d.fillOval(x - 15, y - 60, 30, 30);
        g2d.fillOval(x - 10, y - 65, 20, 20);
    }
}

private void crearBancos(Graphics2D g2d) {
    // Crear algunos bancos en el área verde
    int[] posicionesBancos = {200, 400, 600, 800};
    for (int x : posicionesBancos) {
        if (Math.abs(x - VERTICAL_STREET_X) < VERTICAL_STREET_WIDTH + 20) {
            continue;
        }
        
        int y = HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT + 35;
        
        // Base del banco
        g2d.setColor(new Color(139, 69, 19)); // Marrón
        g2d.fillRect(x - 20, y, 40, 5);
        
        // Patas del banco
        g2d.fillRect(x - 18, y + 5, 5, 15);
        g2d.fillRect(x + 13, y + 5, 5, 15);
        
        // Respaldo
        g2d.fillRect(x - 20, y - 15, 40, 3);
        g2d.fillRect(x - 18, y - 15, 3, 15);
        g2d.fillRect(x + 15, y - 15, 3, 15);
    }
}

private void crearFaroles(Graphics2D g2d) {
    int[] posicionesFaroles = {100, 300, 500, 700, 900};
    for (int x : posicionesFaroles) {
        if (Math.abs(x - VERTICAL_STREET_X) < VERTICAL_STREET_WIDTH + 20) {
            continue;
        }
        
        int y = HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT + 45;
        
        // Poste del farol
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(x - 3, y - 40, 6, 45);
        
        // Lámpara
        g2d.setColor(Color.GRAY);
        g2d.fillOval(x - 8, y - 55, 16, 16);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 6, y - 53, 12, 12);
    }
}
   
   private void crearCalles(Graphics2D g2d) {
    g2d.setColor(Color.DARK_GRAY);
    
    g2d.fillRect(VERTICAL_STREET_X, 0, VERTICAL_STREET_WIDTH, getHeight());
    g2d.fillRect(0, HORIZONTAL_STREET_Y, getWidth(), HORIZONTAL_STREET_HEIGHT);
    g2d.fillRect(0, HORIZONTAL_STREET_Y+180, getWidth(), HORIZONTAL_STREET_HEIGHT);  
}


    private void crearLineas(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    float[] dash = {10.0f};
    //LINEAS VERTICALES
    g2d.setStroke(new BasicStroke(2));
    g2d.drawLine(VERTICAL_STREET_X, 0, VERTICAL_STREET_X, getHeight()/2-130);
    g2d.drawLine(VERTICAL_STREET_X + VERTICAL_STREET_WIDTH, 0, VERTICAL_STREET_X + VERTICAL_STREET_WIDTH, getHeight()/2-130);
    g2d.drawLine(VERTICAL_STREET_X, getHeight()/2-10, VERTICAL_STREET_X, getHeight()/2+50);
    g2d.drawLine(VERTICAL_STREET_X+ VERTICAL_STREET_WIDTH, getHeight()/2-10, VERTICAL_STREET_X+ VERTICAL_STREET_WIDTH, getHeight()/2+50);
    g2d.drawLine(VERTICAL_STREET_X, getHeight()/2+170, VERTICAL_STREET_X, getHeight());
    g2d.drawLine(VERTICAL_STREET_X + VERTICAL_STREET_WIDTH, getHeight()/2+170, VERTICAL_STREET_X + VERTICAL_STREET_WIDTH, getHeight());
    
    //HORIZONTAL ARRIBA
    g2d.drawLine(0, HORIZONTAL_STREET_Y, getWidth()/2 -52, HORIZONTAL_STREET_Y);
    g2d.drawLine(0, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT, getWidth()/2 -52, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT);
    
    g2d.drawLine(getWidth()/2+27, HORIZONTAL_STREET_Y, getWidth(), HORIZONTAL_STREET_Y);
    g2d.drawLine(getWidth()/2+27, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT, getWidth() , HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT);
    
    //HORIZONTAL ABAJO
    g2d.drawLine(0, HORIZONTAL_STREET_Y+180, getWidth()/2 -52, HORIZONTAL_STREET_Y+180);
    g2d.drawLine(0, HORIZONTAL_STREET_Y+180 + HORIZONTAL_STREET_HEIGHT, getWidth()/2 -52, HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT+180);
    g2d.drawLine(getWidth()/2+27, HORIZONTAL_STREET_Y+180, getWidth(), HORIZONTAL_STREET_Y+180);
    g2d.drawLine(getWidth()/2+27, HORIZONTAL_STREET_Y+180 + HORIZONTAL_STREET_HEIGHT, getWidth() , HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT+180);
    
    
    
    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
    // Horizontal street divider lines AARIBA
    for (int i = 1; i <= 2; i++) {
        int yPosition = HORIZONTAL_STREET_Y + (i * HORIZONTAL_STREET_HEIGHT / 3);
        g2d.drawLine(0, yPosition, getWidth()/2 -60, yPosition);
        g2d.drawLine(getWidth()/2 +40, yPosition, getWidth(), yPosition);
    }
    for (int i = 1; i <= 2; i++) {
        int yPosition = HORIZONTAL_STREET_Y +180+ (i * HORIZONTAL_STREET_HEIGHT / 3);
        g2d.drawLine(0, yPosition, getWidth()/2 -60, yPosition);
        g2d.drawLine(getWidth()/2 +40, yPosition, getWidth(), yPosition);
    }

    // Vertical street divider line
    for (int i = 1; i < 2; i++) {
        int xPosition = VERTICAL_STREET_X + (i * VERTICAL_STREET_WIDTH / 2);
        g2d.drawLine(xPosition, 0, xPosition, 250);
        g2d.drawLine(xPosition, 385, xPosition, 430);
        g2d.drawLine(xPosition,560, xPosition, getWidth());
    }
    
    //DIVISIONES INTERSECCIONES
    g2d.setColor(Color.YELLOW);//HORIZOTAL
    for (int i = 1; i <= 13; i++) {
        int yPosition = HORIZONTAL_STREET_Y + (i * HORIZONTAL_STREET_HEIGHT / 14);
        g2d.drawLine(getWidth()/2-60, yPosition, getWidth()/2 -50 ,yPosition);
        g2d.drawLine(getWidth()/2+30, yPosition, getWidth()/2 +40 ,yPosition);    
    }
    for (int i = 1; i <= 13; i++) {
        int yPosition = HORIZONTAL_STREET_Y+180 + (i * HORIZONTAL_STREET_HEIGHT / 14);
        g2d.drawLine(getWidth()/2-60, yPosition, getWidth()/2 -50 ,yPosition);
        g2d.drawLine(getWidth()/2+30, yPosition, getWidth()/2 +40 ,yPosition);    
    }
    //VERTICAL
    for (int i = 1; i < 12; i++) {
        int xPosition = VERTICAL_STREET_X + (i * VERTICAL_STREET_WIDTH / 12);
        g2d.drawLine(xPosition, 240, xPosition, 250);
        g2d.drawLine(xPosition,430, xPosition, 420);
        g2d.drawLine(xPosition,560, xPosition, 570);
         g2d.drawLine(xPosition, 370, xPosition, 380);
    }
}


    private void mostrarEstadisticas(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("Vehículos Sur: " + contadorNorteSur, 10, 20);
        g2d.drawString("Vehículos Oeste: " + contadorOeste, 10, 40);
        g2d.drawString("Vehículos Este: " + contadorEste, 10, 60);
    }

    public void actualizarCoches() {
    Iterator<Coche> iterator = carro.iterator();
    while (iterator.hasNext()) {
        Coche vehicle = iterator.next();
        
        // Verificar si debe detenerse por el semáforo o por otro vehículo
        if (verificarAlto(vehicle)) {
            continue; // Skip movement for this vehicle
        }
        
        vehicle.mover();
        
        // Verificar si el vehículo está fuera de los límites
        if (yaCruzo(vehicle)) {
            iterator.remove();
            actualizarContador(vehicle);
        }
    }
}
    
    public boolean verificarAlto(Coche vehicle) {
    // Primero verificamos el semáforo
    if (verificarParada(vehicle)) {
        return true;
    }
    
    // Luego verificamos si hay vehículos adelante
    return verificarCocheEnfrente(vehicle);
}

    
private boolean verificarCocheEnfrente(Coche vehicle) {
    final int SAFE_DISTANCE = 50; // Distancia de seguridad entre vehículos
    
    for (Coche otherVehicle : carro) {
        if (vehicle == otherVehicle) continue;
        
        // Solo verificamos vehículos que van en la misma dirección
        if (!esMismaDireccion(vehicle, otherVehicle)) continue;
        
        if (vehicle.getSpeedY() > 0) { // Dirección sur
            if (otherVehicle.getPosX() == vehicle.getPosX() && 
                otherVehicle.getPosY() > vehicle.getPosY() && 
                otherVehicle.getPosY() - vehicle.getPosY() <= SAFE_DISTANCE) {
                return true;
            }
        } else if (vehicle.getSpeedX() > 0) { // Dirección este
            if (otherVehicle.getPosY() == vehicle.getPosY() && 
                otherVehicle.getPosX() > vehicle.getPosX() && 
                otherVehicle.getPosX() - vehicle.getPosX() <= SAFE_DISTANCE) {
                return true;
            }
        } else if (vehicle.getSpeedX() < 0) { // Dirección oeste
            if (otherVehicle.getPosY() == vehicle.getPosY() && 
                otherVehicle.getPosX() < vehicle.getPosX() && 
                vehicle.getPosX() - otherVehicle.getPosX() <= SAFE_DISTANCE) {
                return true;
            }
        }
    }
    return false;
}

private boolean esMismaDireccion(Coche v1, Coche v2) {
    return (v1.getSpeedX() == v2.getSpeedX() && v1.getSpeedY() == v2.getSpeedY());
}


 private boolean verificarParada(Coche vehicle) {
    Semaforos relevantLight = obtenerSemaforo(vehicle);
    if (relevantLight != null && !relevantLight.esverde()) {
        Point stopPosition = calcularParada(vehicle);
        if (stopPosition != null) {
            if (vehicle.getSpeedY() > 0) { // Dirección sur
                return vehicle.getPosY() >= stopPosition.y - 50 && vehicle.getPosY() <= stopPosition.y;
            } else if (vehicle.getSpeedX() > 0) { // Dirección este
                return vehicle.getPosX() >= stopPosition.x - 50 && vehicle.getPosX() <= stopPosition.x;
            } else if (vehicle.getSpeedX() < 0) { // Dirección oeste
                return vehicle.getPosX() <= stopPosition.x + 50 && vehicle.getPosX() >= stopPosition.x;
            }
        }
    }
    return false;
}

    private Point calcularParada(Coche vehicle) {
        if (vehicle.getSpeedY() > 0) { // Sur
            return new Point(VERTICAL_STREET_X, HORIZONTAL_STREET_Y - 30);
        } else if (vehicle.getSpeedX() > 0) { // Este
            return new Point(VERTICAL_STREET_X - 30, HORIZONTAL_STREET_Y);
        } else if (vehicle.getSpeedX() < 0) { // Oeste
            return new Point(VERTICAL_STREET_X + VERTICAL_STREET_WIDTH + 30, 
                           HORIZONTAL_STREET_Y + HORIZONTAL_STREET_HEIGHT);
        }
        return null;
    }

    public void agregarCoche() {
        Coche vehicle = CocheDatos.crearCocheAleatorio(getWidth(), getHeight());
        if (vehicle != null) {
            carro.add(vehicle);
        }
    }

    private Semaforos obtenerSemaforo(Coche vehicle) {
        if (vehicle.getSpeedY() > 0) return semaforos.get(0); // Sur
        if (vehicle.getSpeedX() > 0) return semaforos.get(1); // Este
        if (vehicle.getSpeedX() < 0) return semaforos.get(2); // Oeste
        return null;
    }

public void cambiarLuzSemaforos() {
    boolean esLuzVerde = semaforos.get(0).esverde();
    
    // Cambiar los estados de los semáforos
    semaforos.get(0).setGreen(!esLuzVerde); // Vertical
    semaforos.get(1).setGreen(esLuzVerde);  // Este
    semaforos.get(2).setGreen(esLuzVerde);  // Oeste
    
    // Reproducir los sonidos para ambas direcciones
    sonidoSistema.reproducirAmbos(!esLuzVerde);
}

    // Getters para las estadísticas
    public int getContadorNorteSur() { return contadorNorteSur; }
    public int getoesteCount() { return contadorOeste; }
    public int getesteCount() { return contadorEste; }

    public void actualizarContador(Coche vehicle) {
        if (vehicle.getSpeedY() != 0) {
            contadorNorteSur++;
        }else{
            if (vehicle.getSpeedX() > 0) {
                contadorEste++;
        }else{
                contadorOeste++;
            }        
        } 
    }

    public boolean yaCruzo(Coche vehicle) {
        return vehicle.getPosX() < -50 || vehicle.getPosX() > getWidth() + 50 ||
               vehicle.getPosY() < -50 || vehicle.getPosY() > getHeight() + 50;
    }
}