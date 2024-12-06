/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofx;

/**
 *
 * @author alexi
 */
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sonidos {
    public String AVANCE_SONIDO= "C:\\Users\\52229\\Downloads\\S21002431CruceVial\\ProyectoFX\\resources\\Avance.wav";    // Ajusta estas rutas según
    public String DETENGASE_SONIDO = "C:\\Users\\52229\\Downloads\\S21002431CruceVial\\ProyectoFX\\resources\\Detengase.wav"; // tu estructura de carpetas
    
      public void reproducirSonido(String archivoSonido) {
        try {
            File audioFile = new File(archivoSonido);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("NO ES COMPATIBLE SONIDO");
                return;
            }

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
            
            // Liberar recursos cuando termine de reproducir
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void reproducirVerde() {
        reproducirSonido(AVANCE_SONIDO);
    }

    public void reproducirRojo() {
        reproducirSonido(DETENGASE_SONIDO);
    }
    
    // Nuevo método para reproducir ambos sonidos con un pequeño delay
    public void reproducirAmbos(boolean verdeVertical) {
        if (verdeVertical) {
            // Si el vertical se pone verde, el horizontal se pone rojo
            reproducirVerde();
            // Pequeña pausa antes del segundo sonido
            try {
                Thread.sleep(1000); // 1 segundo de delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reproducirRojo();
        } else {
            // Si el vertical se pone rojo, el horizontal se pone verde
            reproducirRojo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reproducirVerde();
        }
    }
}
