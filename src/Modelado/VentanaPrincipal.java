package Modelado;

import Busqueda.Busqueda4;
import Sounds.Reproductor;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
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
    public AnimadorAutomatico animador, animador2;
    public Busqueda4 buscador, buscador2;
    public static boolean StatusJugador1 = false, StatusJugador2 = false;
    public static int countPremio, sizePremio;
    public JLabel label, label2;
    public static Timer timer1, timer2, timer3;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    public static boolean Thread1IsRunnig = false, Thread2IsRunnig = false, Thread3IsRunnig = false, pause1 = false, pause2 = false, pause3 = false, muerto = false;

    public VentanaPrincipal() {

        lienzo = new Lienzo();
        lienzo.setFocusable(true);
        lienzo.requestFocus();
        label = new JLabel("Laberinto 2014", (int) CENTER_ALIGNMENT);
        label.setFont(new java.awt.Font("Impact", 0, 30));
        label2 = new JLabel("IECI", (int) CENTER_ALIGNMENT);
        label2.setFont(new java.awt.Font("Impact", 0, 30));
        lienzo.getLaberinto().generarNivelNuevo(0);

        ImageIcon icon = new ImageIcon("src/Images/laberinto_icon.jpg");
        Image Image = icon.getImage();
        this.setIconImage(Image);
        this.setTitle("Laberinto IA");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(label, BorderLayout.NORTH);
        this.getContentPane().add(lienzo, BorderLayout.CENTER);
        this.getContentPane().add(label2, BorderLayout.SOUTH);
        this.setSize(lienzo.getWidth() + 21, lienzo.getHeight() + 144);
        this.setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menu = new JMenu("LAB NIVEL");
        menuBar.add(menu);
        menuItem = new JMenuItem("Nivel 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetJuego();
                lienzo.getLaberinto().generarNivel1();
                label.setText("NIVEL 1");
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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
                if (Thread3IsRunnig == false) {
                    startStatus();
                }
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

                if (lienzo.getLaberinto().getNivelLaberinto() != 0) {
                    StatusJugador1 = true;
                    StatusJugador2 = true;

                }

                if (Thread1IsRunnig == true) {
                    pause1 = true;
                    timer1.cancel();
                    Thread1IsRunnig = false;

                }

                if (Thread2IsRunnig == true) {
                    pause2 = true;
                    timer2.cancel();
                    Thread2IsRunnig = false;

                }

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("J1 VS J2IA");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusJugador1 = true;
                StatusJugador2 = false;

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("J1IA VS J2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("J1IA VS J2IA");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Thread1IsRunnig == false) {
                    startThread1();
         
                     StatusJugador1 = false;
                }



                if (Thread2IsRunnig == false )  {
                    startThread2();
                     
                }

         

            }
        });
        menu.add(menuItem);

        try {
            sonidofondo();
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void startThread1() {
        Thread1IsRunnig = true;
        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new thread1(), 0, 450);
    }

    public void startThread2() {
        Thread2IsRunnig = true;
        timer2 = new Timer();
        timer2.scheduleAtFixedRate(new thread2(), 0, 450);
    }

    public void startStatus() {
        Thread3IsRunnig = true;
        timer3 = new Timer();

        timer3.scheduleAtFixedRate(status, 0, 1);
    }

    class thread1 extends TimerTask {

        @Override
        public void run() {
            Timer timer_animador = new Timer();
            buscador = new Busqueda4(lienzo);
            buscador.setTipoBusqueda(true);
            buscador.setRole(true);
            buscador.buscarJugador();
            buscador.calcularRuta();
            animador = new AnimadorAutomatico(lienzo, buscador.pasos, true);
            timer_animador.scheduleAtFixedRate(animador, 0, 1);
        }
    };

    class thread2 extends TimerTask  {
        @Override
        public void run() {
            Timer timer_animador2 = new Timer();
            buscador2 = new Busqueda4(lienzo);
            buscador2.setTipoBusqueda(true); //true anchura, false profundidad
            buscador2.setRole(false);
            buscador2.buscarEnemigo();
            buscador2.calcularRuta();
            //System.out.println(buscador1.pasos);
            animador2 = new AnimadorAutomatico(lienzo, buscador2.pasos, false);
            timer_animador2.scheduleAtFixedRate(animador2, 0, 1);

        }
    };

    TimerTask status = new TimerTask() {
        @Override
        public void run() {
            label2.setText("Monedas Count: " + countPremio);
            if (lienzo.getLaberinto().getNivelLaberinto() == 0) {
                label2.setText("IECI");
            }
            if (muerto == true) {
                try {
                    sonidoPrimeraSangre();
                } catch (Exception ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "HAS SIDO CAPTURADO!\nMONEDAS RECOLECTADAS: " + countPremio + " / " + sizePremio, "Fin del Juego!", 1);
                muerto = false;
                StatusJugador1 = false;
                StatusJugador2 = false;
                parar(1000);
            } else if (countPremio == sizePremio && lienzo.getLaberinto().EsunWinner()) {

                JOptionPane.showMessageDialog(null, "HAS SUPERADO EL NIVEL " + lienzo.getLaberinto().getNivelLaberinto() + "\nMONEDAS RECOLECTADAS: " + countPremio, "Nivel Completado!", 1);
                if (countPremio == 10) {
                    JOptionPane.showMessageDialog(null, "FELICITACIONES\nHAS COMPLETADO EL JUEGO!!!", "Juego Completado!", 1);
                    lienzo.getLaberinto().vaciarLaberinto();
                    lienzo.getLaberinto().generarNivelNuevo(0);
                    lienzo.repaint();
                    label.setText("FIN DEL JUEGO!");
                    countPremio = 0;
                } else {
                    lienzo.getLaberinto().vaciarLaberinto();
                    lienzo.getLaberinto().defaultPosicionLaberinto();
                    lienzo.getLaberinto().generarNivelNuevo(lienzo.getLaberinto().getNivelLaberinto() + 1);
                    lienzo.repaint();
                    countPremio = 0;
                    label.setText("NIVEL " + (lienzo.getLaberinto().getNivelLaberinto()));
                    label2.setText("Monedas Count: " + countPremio);
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
        lienzo.getLaberinto().vaciarLaberinto();
        lienzo.getLaberinto().defaultPosicionLaberinto();
        countPremio = 0;
    }

    public void sonidoPrimeraSangre() throws Exception {
        Reproductor primerasangre = new Reproductor();
        primerasangre.AbrirFichero("src/Sounds/primerasangre.wav");
        primerasangre.Play();
    }

    public void sonidofondo() throws Exception {
        Reproductor fondo = new Reproductor();
        fondo.AbrirFichero("src/Sounds/fondo.ogg");
        fondo.Play();
    }

}
