
package proyectofx;


public class CocheDatos {
    public static final double velocidad = 6;
    
    public static Coche crearCocheAleatorio(int anchura, int altura) {
        double x, y;
        double speedX = 0, speedY = 0;
        
        int direccion = (int)(Math.random() * 8); // Solo 3 direcciones posibles
        
        switch(direccion) {
            case 0: // Sur (calle vertical)
                x = 440; // Centro de la calle vertical
                y = -20;
                speedY = velocidad;
                break;
                
            case 1: // Este (carril inferior de la calle horizontal)
                x = 480;
                y = -20; // Carril inferior
                speedY = velocidad;
                break;
                
            case 2: // Oeste (carril superior de la calle horizontal)
                x = anchura+20;
                y = 270; // Carril superior
                speedX = -velocidad;
                break;
            case 3: // Oeste (carril superior de la calle horizontal)
                x = anchura + 20;
                y = 300; // Carril superior
                speedX = -velocidad;
                break;
            case 4: // Oeste (carril superior de la calle horizontal)
                x = anchura + 20;
                y = 340; // Carril superior
                speedX = -velocidad;
                break;
            case 5: // Oeste (carril superior de la calle horizontal)
                x = - 20;
                y = 440; // Carril superior
                speedX = velocidad;
                break;
            case 6: // Oeste (carril superior de la calle horizontal)
                x = - 20;
                y = 470; // Carril superior
                speedX = velocidad;
                break;
            case 7: // Oeste (carril superior de la calle horizontal)
                x = - 20;
                y = 510; // Carril superior
                speedX = velocidad;
                break;
                
            default:
                return null;
        }
        
        return new Coche(x, y, speedX, speedY);
    }
}