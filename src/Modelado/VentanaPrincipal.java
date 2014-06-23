package Modelado;

import Busqueda.Busqueda3;
import Sounds.Reproductor;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements Constantes {

    public static Lienzo lienzo;
    public AnimadorAutomatico animador;
    public Busqueda3 buscador1, buscador2;
    public static boolean bool1 = true, bool2 = true;
    public static boolean StatusJugador1 = false, StatusJugador2 = false; // TECLADO MOVIMIENTOS JUGADOR
    public static int countPremio, sizePremio;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    public JLabel label, label2;

    public VentanaPrincipal() {

        lienzo = new Lienzo();
        lienzo.setFocusable(true);
        lienzo.requestFocus();
        label = new JLabel("Laberinto 2014", (int) CENTER_ALIGNMENT);
        label.getAlignmentX();
       
        label.setFont(new java.awt.Font("Impact", 0, 30));

        label2 = new JLabel(" ", (int) CENTER_ALIGNMENT);
        label2.setFont(new java.awt.Font("Impact", 0, 30));
        
        
        
        sonidofondo();
        ImageIcon icon = new ImageIcon("src/Images/laberinto_icon.jpg");
        Image Image = icon.getImage();
        this.setIconImage(Image);
        this.setTitle("Laberinto IA");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(label,BorderLayout.NORTH);
        this.getContentPane().add(lienzo, BorderLayout.CENTER);
        this.getContentPane().add(label2,BorderLayout.SOUTH);
        this.setSize(lienzo.getWidth() + 21, lienzo.getHeight() + 144);
        this.setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menu = new JMenu("Nivel Laberinto");
        menuBar.add(menu);
        menuItem = new JMenuItem("Nivel 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel1();
                label.setText("NIVEL 1");
                
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel2();
                label.setText("NIVEL 2");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 3");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel3();
                label.setText("NIVEL 3");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 4");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel4();
                label.setText("NIVEL 4");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 5");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel5();
                label.setText("NIVEL 5");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 6");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel6(); 
                label.setText("NIVEL 6");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 7");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel7();
                 label.setText("NIVEL 7");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 8");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel8();
                label.setText("NIVEL 8");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 9");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel9();
                label.setText("NIVEL 9");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 10");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel10();
                label.setText("NIVEL 10");
            }
        });
        menu.add(menuItem);

        menu = new JMenu("P1 NIVEL");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem = new JMenuItem("Nivel 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 3");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 4");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 5");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 6");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 7");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 8");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 9");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 10");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menu = new JMenu("P2 NIVEL");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem = new JMenuItem("Nivel 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 3");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 4");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 5");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 6");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 7");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 8");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 9");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 10");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menu = new JMenu("J VS J");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem = new JMenuItem("J1 VS J2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusJugador1 = true;
                StatusJugador2 = true;
                startThread();
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("J1 VS J2IA");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusJugador1 = true;
                StatusJugador2 = false;
                startThread();

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("J1IA VS J2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusJugador1 = false;
                StatusJugador2 = true;
                startThread();

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("J1IA VS J2IA");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusJugador1 = false;
                StatusJugador2 = false;
                startThread();
            }
        });
        menu.add(menuItem);

    }

    public void startThread() {
        if (!StatusJugador1 && !thread1.isAlive()) {
            thread1.start();
        } else if (thread1.isAlive() && !StatusJugador1) {
            thread1.resume();
        }
        if (!StatusJugador2 && !thread2.isAlive()) {
            thread2.start();
        } else if (thread2.isAlive() && !StatusJugador2) {
            thread2.resume();
        }

        if (thread1.isAlive() && StatusJugador1) {
            thread1.suspend();
        }

        if (thread2.isAlive() && StatusJugador2) {
            thread2.suspend();
        }

        if (status.isAlive()) {
            status.resume();
        } else {
            status.start();
        }

    }

    Thread thread1 = new Thread() {
        @Override
        public void run() {
            while (bool1) {
                Timer lanzadorTareas = new Timer();
                buscador1 = new Busqueda3(lienzo);
                buscador1.setTipoBusqueda(true); //true anchura, false profundidad
                buscador1.setRole(true);
                buscador1.buscarJugador();
                buscador1.calcularRuta();
                //System.out.println(buscador1.pasos);
                animador = new AnimadorAutomatico(lienzo, buscador1.pasos, true);
                lanzadorTareas.scheduleAtFixedRate(animador, 0, 10);
                parar(800);
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
                buscador2.buscarEnemigo();
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
                label2.setText("Monedas Count: "+ countPremio);
                if (!bool1 && !bool2) {
                    sonidoPrimeraSangre();
                    thread2.suspend();
                    thread1.suspend();
                    JOptionPane.showMessageDialog(null, "HAS SIDO CAPTURADO!\nMONEDAS RECOLECTADAS: " + countPremio + " / " + sizePremio, "Fin del Juego!", 1);
                    status.suspend();
                } else if (countPremio == sizePremio && lienzo.getLaberinto().EsunWinner()) {
                    thread2.suspend();
                    thread1.suspend();
                    JOptionPane.showMessageDialog(null, "HAS SUPERADO EL NIVEL " + lienzo.getLaberinto().getNivelLaberinto() + "\nMONEDAS RECOLECTADAS: " + countPremio, "Nivel Completado!", 1);
                    if (countPremio == 10) {
                        StatusJugador1 = false;
                        StatusJugador2 = false;
                        JOptionPane.showMessageDialog(null, "FELICITACIONES\nHAS COMPLETADO EL JUEGO!!!", "Juego Completado!", 1);
                        status.suspend();
                    }
                    lienzo.getLaberinto().vaciarLaberinto();
                    lienzo.getLaberinto().defaultPosicionLaberinto();
                    lienzo.getLaberinto().generarNivelNuevo(lienzo.getLaberinto().getNivelLaberinto() + 1);            
                    lienzo.repaint();
                    thread1.resume();
                    thread2.resume();
                    countPremio = 0;
                    label.setText("NIVEL " + (lienzo.getLaberinto().getNivelLaberinto()));
                    label2.setText("Monedas Count: "+ countPremio);
                    parar(1000);
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

    public void resetJuego() {
        bool1 = true;
        bool2 = true;
        lienzo.getLaberinto().vaciarLaberinto();
        lienzo.getLaberinto().defaultPosicionLaberinto();
        lienzo.repaint();
        countPremio = 0;
    }

    public void sonidoPrimeraSangre() {
        Reproductor moneda = new Reproductor();
        try {
            moneda.AbrirFichero("src/Sounds/primerasangre.wav");
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            moneda.Play();
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sonidofondo() {
        Reproductor moneda = new Reproductor();
        try {
            moneda.AbrirFichero("src/Sounds/fondo.ogg");
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            moneda.Play();
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
