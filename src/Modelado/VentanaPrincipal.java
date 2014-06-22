package Modelado;

import Busqueda.Busqueda;
import Busqueda.Busqueda3;
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements Constantes {

    public Lienzo lienzo;
    public AnimadorAutomatico animador;
    public Busqueda3 buscador;
    public static boolean bool1 = true, bool2 = true;


    public VentanaPrincipal() {

        lienzo = new Lienzo();
        lienzo.setFocusable(true);
        lienzo.requestFocus();
        ImageIcon icon = new ImageIcon("src/Images/laberinto_icon.jpg");
        Image Image = icon.getImage();
        this.setIconImage(Image);
        this.setTitle("Laberinto IA");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(lienzo, BorderLayout.CENTER);
        this.setSize(lienzo.getWidth() + 30, lienzo.getHeight() + 75);
        this.setLocationRelativeTo(null);
        /*
         Timer lanzadorTareas = new Timer();
         buscador = new Busqueda3(lienzo);
         buscador.setTipoBusqueda(true); //true anchura, false profundidad
         buscador.setRole(true);
         buscador.buscar();
         buscador.calcularRuta();
         animador = new AnimadorAutomatico(lienzo, buscador.pasos, true);
         lanzadorTareas.scheduleAtFixedRate(animador, 0, 1000);
         */

    }

    public synchronized void startThread() {

        thread1.start();
        thread2.start();
    }

    Thread thread1 = new Thread() {
        @Override
        public void run() {
                      
            do {
                
                Timer lanzadorTareas = new Timer();
                buscador = new Busqueda3(lienzo);
                buscador.setTipoBusqueda(true); //true anchura, false profundidad
                buscador.setRole(true);
                buscador.buscar();
                buscador.calcularRuta();
                animador = new AnimadorAutomatico(lienzo, buscador.pasos, true);
                lanzadorTareas.scheduleAtFixedRate(animador, 0, 10);
                parar(1000);
            } while (bool1);
            JOptionPane.showMessageDialog(null, "Has sido capturado!", "Fin del Juego", 1);
        }
    };

    Thread thread2 = new Thread() {
        @Override
        public void run() {
          
            do {
                Timer lanzadorTareas = new Timer();
                buscador = new Busqueda3(lienzo);
                buscador.setTipoBusqueda(true); //true anchura, false profundidad
                buscador.setRole(false);
                buscador.buscar();
                buscador.calcularRuta();
                System.out.println(buscador.pasos);
                animador = new AnimadorAutomatico(lienzo, buscador.pasos, false);
                lanzadorTareas.scheduleAtFixedRate(animador, 0, 10);
                parar(1000);
            } while (bool2);
        }
    };

    public void parar(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

}
