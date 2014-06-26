package Modelado;

import Busqueda.Busqueda;
import Sounds.Reproductor;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    public Busqueda buscador, buscador2;
    public static boolean StatusJugador1 = false, StatusJugador2 = false;
    public static int countPremio, sizePremio, InteligenciaJugador1=1, InteligenciaJugador2=1;
    public JLabel label, label2;
    public static Timer timer1, timer2, timer3;
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem menuItem;
    public static boolean Thread1IsRunnig = false, Thread2IsRunnig = false, Thread3IsRunnig = false, pause1 = false, pause2 = false, pause3 = false, muerto = false;
    public Reproductor sonidoFondo = new Reproductor();

    public VentanaPrincipal() {

        lienzo = new Lienzo();
        lienzo.setFocusable(true);
        lienzo.requestFocus();
        label = new JLabel("Laberinto 2014", (int) CENTER_ALIGNMENT);
        label.setFont(new java.awt.Font("Impact", 0, 30));
        label2 = new JLabel("IECI", (int) CENTER_ALIGNMENT);
        label2.setFont(new java.awt.Font("Impact", 0, 30));
        lienzo.getLaberinto().generarNivelNuevo(0);
        sonidofondoGo();
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
        menu = new JMenu("Laberinto Nivel");
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
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
                    lienzo.repaint();
                }
            }
        });
        menu.add(menuItem);

        menu = new JMenu("Jugador Nivel");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem = new JMenuItem("Nivel 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 1;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 2;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 3");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 3;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 4");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 4;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 5");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 5;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 6");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 6;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 7");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 7;
            }

        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 8");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 8;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 9");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 9;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 10");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador1 = 10;
            }
        });
        menu.add(menuItem);

        menu = new JMenu("Enemigo Nivel");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem = new JMenuItem("Nivel 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 1;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 2");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 2;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 3");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 3;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 4");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 4;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 5");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 5;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 6");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 6;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 7");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 7;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 8");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 8;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 9");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 9;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Nivel 10");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteligenciaJugador2 = 10;
            }
        });
        menu.add(menuItem);

        menu = new JMenu("Duelo");
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem = new JMenuItem("Jug. Vs Ene.");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lienzo.getLaberinto().getNivelLaberinto() != 0) {
                    StatusJugador1 = true;
                    StatusJugador2 = true;
                } else {
                    StatusJugador1 = false;
                    StatusJugador2 = false;
                }

                if (Thread1IsRunnig == true) {
                    timer1.cancel();
                    Thread1IsRunnig = false;
                }

                if (Thread2IsRunnig == true) {
                    timer2.cancel();
                    Thread2IsRunnig = false;
                }
                Thread1IsRunnig = false;
                Thread2IsRunnig = false;
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Jug. VS Ene. IA");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lienzo.getLaberinto().getNivelLaberinto() != 0) {
                    StatusJugador1 = true;
                    StatusJugador2 = false;
                    if (Thread1IsRunnig == true) {
                        timer1.cancel();
                        Thread1IsRunnig = false;
                    }
                    if (Thread2IsRunnig == false) {
                        startThread2();
                    }
                }

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Jug. IA VS Ene.");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lienzo.getLaberinto().getNivelLaberinto() != 0) {
                    StatusJugador1 = false;
                    StatusJugador2 = true;

                    if (Thread2IsRunnig == true) {
                        timer2.cancel();
                        Thread2IsRunnig = false;
                    }
                    if (Thread1IsRunnig == false) {
                        startThread1();
                    }
                }

            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Jug. IA VS Ene. IA");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lienzo.getLaberinto().getNivelLaberinto() != 0) {
                    StatusJugador2 = false;
                    StatusJugador1 = false;
                    if (Thread1IsRunnig == false) {
                        startThread1();
                    }
                    if (Thread2IsRunnig == false) {
                        startThread2();
                    }
                }
            }
        });
        menu.add(menuItem);
    }

    public void startThread1() {
        Thread1IsRunnig = true;
        timer1 = new Timer();
        timer1.scheduleAtFixedRate(new thread1(), 0, 250);
    }

    public void startThread2() {
        Thread2IsRunnig = true;
        timer2 = new Timer();

        timer2.scheduleAtFixedRate(new thread2(), 0, 255);

    }

    public void startStatus() {
        Thread3IsRunnig = true;
        timer3 = new Timer();
        timer3.scheduleAtFixedRate(new status(), 0, 300);
    }

    class thread1 extends TimerTask {

        @Override
        public void run() {
            Timer timer_animador = new Timer();
            buscador = new Busqueda(lienzo);
            buscador.setTipoBusqueda(true);
            buscador.setRole(true);
            buscador.setInteligenciaJugador(InteligenciaJugador1);
            buscador.buscarJugador();
            buscador.calcularRuta();
            animador = new AnimadorAutomatico(lienzo, buscador.pasos, true);
            timer_animador.scheduleAtFixedRate(animador, 0, 1);
        }
    };

    class thread2 extends TimerTask {

        @Override
        public void run() {
            Timer timer_animador2 = new Timer();
            buscador2 = new Busqueda(lienzo);
            buscador2.setTipoBusqueda(true);
            buscador2.setRole(false);
            buscador2.setInteligenciaEnemigo(InteligenciaJugador2);
            buscador2.buscarEnemigo();
            buscador2.calcularRuta();
            animador2 = new AnimadorAutomatico(lienzo, buscador2.pasos, false);
            timer_animador2.scheduleAtFixedRate(animador2, 0, 1);
        }
    };

    class status extends TimerTask {

        @Override
        public void run() {

            if (lienzo.getLaberinto().getNivelLaberinto() == 0) {

            } else {
                label2.setText("Monedas Obtenidas: " + countPremio);
                lienzo.repaint();
            }
            if (muerto == true) {
                try {
                    sonidoPrimeraSangre();
                } catch (Exception ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                     if (Thread1IsRunnig) {
                    timer1.cancel();
                    Thread1IsRunnig = false;
                    StatusJugador2 = false;
                }
                if (Thread2IsRunnig) {
                    timer2.cancel();
                    Thread2IsRunnig = false;
                    StatusJugador2 = false;
                }
                if (Thread3IsRunnig) {
                    Thread3IsRunnig = false;
                    timer3.cancel();
                }
                JOptionPane.showMessageDialog(null, "Has Sido Capturado!\nMonedas Obtenidas: " + countPremio + " / " + sizePremio, "Fin del Juego", 1);
           
                lienzo.getLaberinto().vaciarLaberinto();
                lienzo.getLaberinto().generarNivelNuevo(0);
                lienzo.repaint();
                label.setText("FIN DEL JUEGO!");
                label2.setText("IECI");
                muerto = false;
            } else if (countPremio == sizePremio && lienzo.getLaberinto().EsunWinner()) {
                if (Thread2IsRunnig) {
                    timer2.cancel();
                }
                JOptionPane.showMessageDialog(null, "Has Superado Nivel " + lienzo.getLaberinto().getNivelLaberinto() + "\nMonedas Obtenidas: " + countPremio, "Nivel Completado", 1);
                if (countPremio == 10 && lienzo.getLaberinto().getNivelLaberinto() == 10) {
                    JOptionPane.showMessageDialog(null, "FELICITACIONES\nHAS COMPLETADO EL JUEGO", "Juego Completado", 1);
                    lienzo.getLaberinto().vaciarLaberinto();
                    lienzo.getLaberinto().generarNivelNuevo(0);
                    lienzo.repaint();
                    label.setText("FIN DEL JUEGO!");
                    label2.setText("IECI");
                    InteligenciaJugador1=1;
                    InteligenciaJugador2=1;
                    StatusJugador1 = false;
                    StatusJugador2 = false;
                    if (Thread1IsRunnig) {
                        timer1.cancel();
                    }
                    if (Thread2IsRunnig) {
                        timer2.cancel();
                    }
                    if (Thread3IsRunnig) {
                        timer3.cancel();
                    }
                    Thread1IsRunnig = false;
                    Thread2IsRunnig = false;
                    Thread3IsRunnig = false;

                    countPremio = 0;

                } else {
                    resetJuego();
                    lienzo.getLaberinto().generarNivelNuevo(lienzo.getLaberinto().getNivelLaberinto() + 1);
                    label.setText("NIVEL " + (lienzo.getLaberinto().getNivelLaberinto()));
                    lienzo.repaint();
                    if (Thread2IsRunnig) {
                        startThread2();
                    }

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
        sonidofondoGo();
        lienzo.getLaberinto().vaciarLaberinto();
        lienzo.getLaberinto().defaultPosicionLaberinto();
        countPremio = 0;
        lienzo.getLaberinto().monedas = new ArrayList<>();
    }

    public void sonidoPrimeraSangre() throws Exception {
        Reproductor primerasangre = new Reproductor();
        primerasangre.AbrirFichero("src/Sounds/primerasangre.wav");
        primerasangre.Play();
    }

    public void sonidofondoGo() {
        try {
            sonidoFondo.AbrirFichero("src/Sounds/fondo.ogg");
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sonidoFondo.Stop();
            sonidoFondo.Play();
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
