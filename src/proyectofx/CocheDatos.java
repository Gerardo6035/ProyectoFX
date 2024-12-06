
package proyectofx;


public class CocheDatos {
    public static final double VELOCIDAD_COCHE = 6;
    
    public static Coche crearCocheAleatorio(int anchura, int altura) {
        double posX, posY;
        double speedX = 0, speedY = 0;
        
        int direccion = (int)(Math.random() * 8); // Solo 3 direcciones posibles
        
        switch(direccion) {
            case 0: // Sur (calle vertical)
                posX = 440; // Centro de la calle vertical
                posY = -20;
                speedY = VELOCIDAD_COCHE;
                break;
                
            case 1: // Este (carril inferior de la calle horizontal)
                posX = 480;
                posY = -20; // Carril inferior
                speedY = VELOCIDAD_COCHE;
                break;
                
            case 2: // Oeste (carril superior de la calle horizontal)
                posX = anchura+20;
                posY = 270; // Carril superior
                speedX = -VELOCIDAD_COCHE;
                break;
            case 3: // Oeste (carril superior de la calle horizontal)
                posX = anchura + 20;
                posY = 300; // Carril superior
                speedX = -VELOCIDAD_COCHE;
                break;
            case 4: // Oeste (carril superior de la calle horizontal)
                posX = anchura + 20;
                posY = 340; // Carril superior
                speedX = -VELOCIDAD_COCHE;
                break;
            case 5: // Oeste (carril superior de la calle horizontal)
                posX = - 20;
                posY = 440; // Carril superior
                speedX = VELOCIDAD_COCHE;
                break;
            case 6: // Oeste (carril superior de la calle horizontal)
                posX = - 20;
                posY = 470; // Carril superior
                speedX = VELOCIDAD_COCHE;
                break;
            case 7: // Oeste (carril superior de la calle horizontal)
                posX = - 20;
                posY = 510; // Carril superior
                speedX = VELOCIDAD_COCHE;
                break;
                
            default:
                return null;
        }
        
        return new Coche(posX, posY, speedX, speedY);
    }
}