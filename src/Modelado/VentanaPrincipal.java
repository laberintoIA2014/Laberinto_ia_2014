package Modelado;

import Busqueda.Busqueda3;
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements Constantes {

    public Lienzo lienzo;
    public AnimadorAutomatico animador;
    public Busqueda3 buscador1, buscador2;
    public static boolean bool1 = true, bool2 = true;
    public static boolean StatusJugador1 = true, StatusJugador2 = true; // TECLADO MOVIMIENTOS JUGADOR
    public static int countPremio, sizePremio;

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

    }

    public void startThread() {
        parar(2000);
        if (!StatusJugador1) {
            thread1.start();
        }
        if (!StatusJugador2) {
            thread2.start();
        }
        status.start();
    }

    Thread thread1 = new Thread() {
        @Override
        public void run() {
            while (bool1) {
                Timer lanzadorTareas = new Timer();
                buscador1 = new Busqueda3(lienzo);
                buscador1.setTipoBusqueda(true); //true anchura, false profundidad
                buscador1.setRole(true);
                buscador1.buscar();
                buscador1.calcularRuta();
                //System.out.println(buscador1.pasos);
                animador = new AnimadorAutomatico(lienzo, buscador1.pasos, true);
                lanzadorTareas.scheduleAtFixedRate(animador, 0, 10);
                parar(1000);
            }
        }
    };

    Thread thread2 = new Thread() {
        @Override
        public void run() {
            while (bool2) {
                Timer lanzadorTareas = new Timer();
                buscador2 = new Busqueda3(lienzo);
                buscador2.setTipoBusqueda(true); //true anchura, false profundidad
                buscador2.setRole(false);
                buscador2.buscar();
                buscador2.calcularRuta();
                //System.out.println(buscador2.pasos);
                animador = new AnimadorAutomatico(lienzo, buscador2.pasos, false);
                lanzadorTareas.scheduleAtFixedRate(animador, 0, 10);
                parar(1000);
            }
        }
    };

    Thread status = new Thread() {
        @Override
        public void run() {
            while (true) {
                 if(countPremio == sizePremio && lienzo.getLaberinto().EsunWinner()){
                    bool1 = false;
                    bool2 = false;
                    JOptionPane.showMessageDialog(null, "HAS GANADO!\nHas Obtenido " + countPremio + " Monedas", "Fin del Juego", 1);
                    break;
                }
                
                if (!bool1 && !bool2) {
                    JOptionPane.showMessageDialog(null, "Has sido capturado!\nHas Obtenido " + countPremio + " Monedas", "Fin del Juego", 1);
                    break;
                }
             
               
                
                
                
            }

        }
    };

    public void parar(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

}
