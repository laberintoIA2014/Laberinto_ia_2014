package Modelado;

import Sounds.Reproductor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class Laberinto extends JComponent implements Constantes {

    public int Ancho, Largo;    //Dimensiones del Laberinto
    public Celda[][] Casillas;  //Las Casillas n x m
    public ArrayList<Point> monedas;
    public static boolean decision = false;
    public static boolean win;

    int i_jugador, j_jugador,
            i_jugador2, j_jugador2,
            i_premio, j_premio,
            i_premio2, j_premio2,
            i_premio3, j_premio3,
            i_premio4, j_premio4,
            i_fin, j_fin,
            NivelNum;

    public Laberinto() {
        monedas = new ArrayList<>();
        this.Casillas = new Celda[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Casillas[i][j] = new Celda(i + (i * Constantes.Longitud_Casilla),
                        j + (j * Constantes.Longitud_Casilla), 'V');
            }
        }

        defaultPosicionLaberinto();
        this.Ancho = n * Longitud_Casilla;
        this.Largo = m * Longitud_Casilla;
        this.setSize(Ancho, Largo);
    }

    public Point distanciaJugador() {
        Point punto = new Point();
        double opcion = (Math.sqrt(((Math.pow(Math.abs(i_jugador2 - i_jugador), 2))
                + (Math.pow(Math.abs(j_jugador2 - j_jugador), 2)))));

        return menorDistancia();

        //return menorDistancia();
    }

    public Point menorDistancia() {
        Point punto = new Point();
        double menor = 9999999;

        for (int i = 0; i < monedas.size(); i++) {
            double opcion = (Math.sqrt(((Math.pow(Math.abs(monedas.get(i).x - i_jugador), 2))
                    + (Math.pow(Math.abs(monedas.get(i).y - j_jugador), 2)))));
            if (menor > opcion) {
                menor = opcion;
                punto = new Point(monedas.get(i).x, monedas.get(i).y);
            }
        }

        if (monedas.isEmpty()) {
            punto = new Point(14, 14);
        }
        return punto;
    }

    public void eliminarMoneda(int x, int y) {
        Point punto = new Point(x, y);
        monedas.remove(punto);
    }

    public void insertarObjetos() {
        Casillas[i_jugador][j_jugador].tipo = 'J';
        Casillas[i_jugador2][j_jugador2].tipo = 'H';
        Casillas[i_fin][j_fin].tipo = 'M';
    }

    public void insertarMarco() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1) {
                    Casillas[i][j].tipo = 'P';
                } else if (j == 0 || j == m - 1) {
                    Casillas[i][j].tipo = 'P';
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Casillas[i][j].paintComponent(g);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Casillas[i][j].update(g);
            }
        }
    }

    void chequearTecla(KeyEvent evento) {

        if (!VentanaPrincipal.muerto) {

            if (VentanaPrincipal.StatusJugador1) {
                //jugador 1
                if (evento.getKeyCode() == 38) {
                    //System.out.println("J1->Mover Arriba");
                    mover_arriba();
                }
                if (evento.getKeyCode() == 40) {
                    //System.out.println("J1->Mover Abajo");
                    mover_abajo();
                }
                if (evento.getKeyCode() == 37) {
                    //System.out.println("J1->Mover Izquierda");
                    mover_izquierda();
                }
                if (evento.getKeyCode() == 39) {
                    //System.out.println("J1->Mover Derecha");
                    mover_derecha();
                }
            }

            if (VentanaPrincipal.StatusJugador2) {
                //jugador 2
                if (evento.getKeyCode() == 87) {
                    //System.out.println("J2->Mover Arriba");
                    mover_arriba2();
                }
                if (evento.getKeyCode() == 83) {
                    //System.out.println("J2->Mover Abajo");
                    mover_abajo2();
                }
                if (evento.getKeyCode() == 65) {
                    //System.out.println("J2->Mover Izquierda");
                    mover_izquierda2();
                }
                if (evento.getKeyCode() == 68) {
                    //System.out.println("J2->Mover Derecha");
                    mover_derecha2();
                }
            }
        }
    }

    public void mover_arriba() {
        //System.out.println("Jugador esta en: " + i_jugador + ", " + j_jugador);
        if (j_jugador > 0) {
            if (Casillas[i_jugador][j_jugador - 1].tipo == 'F') {
                //System.out.println("Jugador paso a: " + i_jugador + ", " + (j_jugador - 1));
                VentanaPrincipal.countPremio++;
                sonidoMoneda();
                eliminarMoneda(i_jugador, j_jugador - 1);
                System.out.println("Monedas: " + VentanaPrincipal.countPremio);
                Casillas[i_jugador][j_jugador].tipo = 'V';
                j_jugador -= 1;
                Casillas[i_jugador][j_jugador].tipo = 'J';
                // Casillas[i_jugador][j_jugador].tipo = 'J';
            } else if (Casillas[i_jugador][j_jugador - 1].tipo != 'P') {
                if (Casillas[i_jugador][j_jugador - 1].tipo == 'M') {
                    if (VentanaPrincipal.countPremio == VentanaPrincipal.sizePremio) {
                        Casillas[i_jugador][j_jugador].tipo = 'V';
                        j_jugador -= 1;
                        Casillas[i_jugador][j_jugador].tipo = 'J';
                    }
                }

                if (Casillas[i_jugador][j_jugador - 1].tipo == 'H') {
                    VentanaPrincipal.muerto = true;
                    VentanaPrincipal.StatusJugador1 = false;
                    VentanaPrincipal.StatusJugador2 = false;
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                } else {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    j_jugador -= 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                    //System.out.println("Jugador paso a: " + i_jugador + ", " + j_jugador);
                }
            } else {
                System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Subir");
        }
        Casillas[i_jugador][j_jugador].j1arriba();
    }

    public void mover_abajo() {
        //System.out.println("Jugador esta en: " + i_jugador + ", " + j_jugador);
        if (15 > j_jugador) {
            if (Casillas[i_jugador][j_jugador + 1].tipo == 'F') {
                //System.out.println("Jugador paso a: " + i_jugador + ", " + (j_jugador + 1));
                VentanaPrincipal.countPremio++;
                sonidoMoneda();

                eliminarMoneda(i_jugador, j_jugador + 1);
                System.out.println("Monedas: " + VentanaPrincipal.countPremio);
                Casillas[i_jugador][j_jugador].tipo = 'V';
                j_jugador += 1;
                Casillas[i_jugador][j_jugador].tipo = 'J';
            } else if (Casillas[i_jugador][j_jugador + 1].tipo == 'M') {
                if (VentanaPrincipal.countPremio == VentanaPrincipal.sizePremio) {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    j_jugador += 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                }
            } else if (Casillas[i_jugador][j_jugador + 1].tipo != 'P') {
                if (Casillas[i_jugador][j_jugador + 1].tipo == 'H') {
                    VentanaPrincipal.muerto = true;
                    VentanaPrincipal.StatusJugador1 = false;
                    VentanaPrincipal.StatusJugador2 = false;
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                } else {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    j_jugador += 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                    // System.out.println("Jugador paso a: " + i_jugador + ", " + j_jugador);
                }
            } else {
                System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Bajar");
        }
        Casillas[i_jugador][j_jugador].j1abajo();
    }

    public void mover_izquierda() {
        //System.out.println("Jugador esta en: " + i_jugador + ", " + j_jugador);
        if (i_jugador > 0) {
            if (Casillas[i_jugador - 1][j_jugador].tipo == 'F') {
                // System.out.println("Jugador paso a: " + (i_jugador - 1) + ", " + j_jugador);
                VentanaPrincipal.countPremio++;
                sonidoMoneda();

                eliminarMoneda(i_jugador - 1, j_jugador);
                System.out.println("Monedas: " + VentanaPrincipal.countPremio);
                Casillas[i_jugador][j_jugador].tipo = 'V';
                i_jugador -= 1;
                Casillas[i_jugador][j_jugador].tipo = 'J';
            } else if (Casillas[i_jugador - 1][j_jugador].tipo == 'M') {
                if (VentanaPrincipal.countPremio == VentanaPrincipal.sizePremio) {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    i_jugador -= 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                }
            } else if (Casillas[i_jugador - 1][j_jugador].tipo != 'P') {
                if (Casillas[i_jugador - 1][j_jugador].tipo == 'H') {
                    VentanaPrincipal.muerto = true;
                    VentanaPrincipal.StatusJugador1 = false;
                    VentanaPrincipal.StatusJugador2 = false;
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                } else {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    i_jugador -= 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                    // System.out.println("Jugador paso a: " + i_jugador + ", " + j_jugador);
                }
            } else {
                System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Ir hacia la Izquierda");
        }
        Casillas[i_jugador][j_jugador].j1izquierda();
    }

    public void mover_derecha() {
        // System.out.println("Jugador esta en: " + i_jugador + ", " + j_jugador);
        if (15 > i_jugador) {
            if (Casillas[i_jugador + 1][j_jugador].tipo == 'F') {
                //System.out.println("Jugador paso a: " + (i_jugador + 1) + ", " + j_jugador);
                VentanaPrincipal.countPremio++;
                sonidoMoneda();
                eliminarMoneda(i_jugador + 1, j_jugador);
                System.out.println("Monedas: " + VentanaPrincipal.countPremio);
                Casillas[i_jugador][j_jugador].tipo = 'V';
                i_jugador += 1;
                Casillas[i_jugador][j_jugador].tipo = 'J';
            } else if (Casillas[i_jugador + 1][j_jugador].tipo == 'M') {
                if (VentanaPrincipal.countPremio == VentanaPrincipal.sizePremio) {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    i_jugador += 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                }
            } else if (Casillas[i_jugador + 1][j_jugador].tipo != 'P') {
                if (Casillas[i_jugador + 1][j_jugador].tipo == 'H') {
                    VentanaPrincipal.muerto = true;
                    VentanaPrincipal.StatusJugador1 = false;
                    VentanaPrincipal.StatusJugador2 = false;
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                } else {
                    Casillas[i_jugador][j_jugador].tipo = 'V';
                    i_jugador += 1;
                    Casillas[i_jugador][j_jugador].tipo = 'J';
                    //System.out.println("Jugador paso a: " + i_jugador + ", " + j_jugador);
                }
            } else {
                System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Ir hacia la Derecha");
        }
        Casillas[i_jugador][j_jugador].j1derecha();
    }

    public void mover_arriba2() {
        //System.out.println("Jugador esta en: " + i_jugador2 + ", " + j_jugador2);
        if (j_jugador2 > 0) {
            if (Casillas[i_jugador2][j_jugador2 - 1].tipo == 'J') {
                VentanaPrincipal.muerto = true;
                VentanaPrincipal.StatusJugador1 = false;
                VentanaPrincipal.StatusJugador2 = false;

                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                j_jugador2 -= 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
            } else if (Casillas[i_jugador2][j_jugador2 - 1].tipo == 'V') {
                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                j_jugador2 -= 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
                //System.out.println("Jugador paso a: " + i_jugador2 + ", " + j_jugador2);
            } else {
                //System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Subir");
        }
        Casillas[i_jugador2][j_jugador2].j2arriba();
    }

    public void mover_abajo2() {
        //System.out.println("Jugador esta en: " + i_jugador2 + ", " + j_jugador2);
        if (15 > j_jugador2) {
            if (Casillas[i_jugador2][j_jugador2 + 1].tipo == 'J') {
                VentanaPrincipal.muerto = true;
                VentanaPrincipal.StatusJugador1 = false;
                VentanaPrincipal.StatusJugador2 = false;

                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                j_jugador2 += 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
            } else if (Casillas[i_jugador2][j_jugador2 + 1].tipo == 'V') {
                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                j_jugador2 += 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
                //System.out.println("Jugador paso a: " + i_jugador2 + ", " + j_jugador2);
            } else {
                //System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Bajar");
        }
        Casillas[i_jugador2][j_jugador2].j2abajo();
    }

    public void mover_izquierda2() {
        // System.out.println("Jugador esta en: " + i_jugador2 + ", " + j_jugador2);
        if (i_jugador2 > 0) {
            if (Casillas[i_jugador2 - 1][j_jugador2].tipo == 'J') {
                VentanaPrincipal.muerto = true;
                VentanaPrincipal.StatusJugador1 = false;
                VentanaPrincipal.StatusJugador2 = false;

                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                i_jugador2 -= 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
            } else if (Casillas[i_jugador2 - 1][j_jugador2].tipo == 'V') {
                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                i_jugador2 -= 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
                //System.out.println("Jugador paso a: " + i_jugador2 + ", " + j_jugador2);
            } else {
                //System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Ir hacia la Izquierda");
        }
        Casillas[i_jugador2][j_jugador2].j2izquierda();
    }

    public void mover_derecha2() {
        // System.out.println("Jugador esta en: " + i_jugador2 + ", " + j_jugador2);
        if (15 > i_jugador2) {
            if (Casillas[i_jugador2 + 1][j_jugador2].tipo == 'J') {
                VentanaPrincipal.muerto = true;
                VentanaPrincipal.StatusJugador1 = false;
                VentanaPrincipal.StatusJugador2 = false;

                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                i_jugador2 += 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
            } else if (Casillas[i_jugador2 + 1][j_jugador2].tipo == 'V') {
                Casillas[i_jugador2][j_jugador2].tipo = 'V';
                i_jugador2 += 1;
                Casillas[i_jugador2][j_jugador2].tipo = 'H';
                //System.out.println("Jugador paso a: " + i_jugador2 + ", " + j_jugador2);
            } else {
                //System.out.println("Contra una Pared");
            }
        } else {
            //System.out.println("Imposible Ir hacia la Derecha");
        }
        Casillas[i_jugador2][j_jugador2].j2derecha();
    }

    public String getJugadorString() {
        return "(" + i_jugador + " ," + j_jugador + ")";
    }

    public int getJugadorX() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ('J' == Casillas[i][j].getTipo()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getJugadorY() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ('J' == Casillas[i][j].getTipo()) {
                    return j;
                }
            }
        }
        return 0;
    }

    public int getJugador2X() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ('H' == Casillas[i][j].getTipo()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getJugador2Y() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ('H' == Casillas[i][j].getTipo()) {
                    return j;
                }
            }
        }
        return 0;
    }

    public String getMetaString() {
        return "(" + i_fin + " ," + j_fin + ")";
    }

    public int getFinX() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ('M' == Casillas[i][j].getTipo()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int getFinY() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ('M' == Casillas[i][j].getTipo()) {
                    return j;
                }
            }
        }
        return 0;
    }

    public Celda[][] getCasillas() {
        return Casillas;
    }

    public void setCasillas(Celda[][] casillas) {
        this.Casillas = casillas;
    }

    public void generarNivelInicial() {
        NivelNum = 0;
        Casillas[4][2].tipo = 'P';
        Casillas[8][2].tipo = 'P';
        Casillas[9][2].tipo = 'P';
        Casillas[10][2].tipo = 'P';
        Casillas[11][2].tipo = 'P';
        Casillas[8][3].tipo = 'P';
        Casillas[11][3].tipo = 'P';
        Casillas[4][4].tipo = 'P';
        Casillas[8][4].tipo = 'P';
        Casillas[11][4].tipo = 'P';
        Casillas[4][5].tipo = 'P';
        Casillas[8][5].tipo = 'P';
        Casillas[9][5].tipo = 'P';
        Casillas[10][5].tipo = 'P';
        Casillas[11][5].tipo = 'P';
        Casillas[4][6].tipo = 'P';
        Casillas[8][6].tipo = 'P';
        Casillas[11][6].tipo = 'P';
        Casillas[4][7].tipo = 'P';
        Casillas[6][7].tipo = 'P';
        Casillas[8][7].tipo = 'P';
        Casillas[11][7].tipo = 'P';
        Casillas[13][7].tipo = 'P';
        Casillas[2][10].tipo = 'P';
        Casillas[3][10].tipo = 'P';
        Casillas[5][10].tipo = 'P';
        Casillas[6][10].tipo = 'P';
        Casillas[7][10].tipo = 'P';
        Casillas[9][10].tipo = 'P';
        Casillas[11][10].tipo = 'P';
        Casillas[13][10].tipo = 'P';
        Casillas[3][11].tipo = 'P';
        Casillas[5][11].tipo = 'P';
        Casillas[7][11].tipo = 'P';
        Casillas[9][11].tipo = 'P';
        Casillas[11][11].tipo = 'P';
        Casillas[13][11].tipo = 'P';
        Casillas[2][12].tipo = 'P';
        Casillas[3][12].tipo = 'P';
        Casillas[5][12].tipo = 'P';
        Casillas[7][12].tipo = 'P';
        Casillas[9][12].tipo = 'P';
        Casillas[11][12].tipo = 'P';
        Casillas[12][12].tipo = 'P';
        Casillas[13][12].tipo = 'P';
        Casillas[2][13].tipo = 'P';
        Casillas[5][13].tipo = 'P';
        Casillas[7][13].tipo = 'P';
        Casillas[9][13].tipo = 'P';
        Casillas[13][13].tipo = 'P';
        Casillas[2][14].tipo = 'P';
        Casillas[3][14].tipo = 'P';
        Casillas[5][14].tipo = 'P';
        Casillas[6][14].tipo = 'P';
        Casillas[7][14].tipo = 'P';
        Casillas[9][14].tipo = 'P';
        Casillas[13][14].tipo = 'P';

    }

    public void generarNivelRandom() {
        NivelNum = 0;
        for (int i = 0; i < n; i++) {
            Casillas[0][i].tipo = Casillas[n - 1][i].tipo = 'P';
        }
        for (int i = 0; i < m; i++) {
            Casillas[i][0].tipo = Casillas[i][m - 1].tipo = 'P';
        }
        int obstaculosMaximo = (n * m) / 3;
        for (int i = 0; i < obstaculosMaximo; i++) {
            int x = (int) (Math.random() * n);
            int y = (int) (Math.random() * m);
            if (x != n - 3 && y != m - 3) {
                if (Casillas[x][y].tipo != 'J' && Casillas[x][y].tipo != 'H' && Casillas[x][y].tipo != 'E' && Casillas[x][y].tipo != 'F' && Casillas[x][y].tipo != 'M') {
                    Casillas[x][y].tipo = 'P';
                }
            }
        }
        eliminarContornoInterno();
        insertarMarco();
        insertarObjetos();
        generarPremio(8);
    }

   public void generarNivel1() {
        NivelNum = 1;
        Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','V','V','V','P','P','P','P','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','P','V','V','P','V','V','V','P'},
                                 {'P','V','V','V','P','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','F','P','V','V','V','P','P','P','P','V','V','V','P'},
                                 {'P','V','V','V','P','V','V','V','P','V','V','P','V','F','V','P'},
                                 {'P','V','V','V','P','V','P','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','F','V','V','V','V','V','P'},
                                 {'P','V','P','P','V','P','P','P','V','P','V','P','V','P','V','P'},
                                 {'P','V','V','P','V','P','V','P','V','P','V','P','V','P','V','P'},
                                 {'P','V','P','P','V','P','V','P','V','P','V','P','P','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        

        insertPremio(mapa,3);
        insertarObjetos();
    }

    public void generarNivel2() {
        
        NivelNum = 2;
        Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','F','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','P','V','V','V','V','P','P','V','V','V','P'},
                                 {'P','V','V','P','P','P','P','V','V','P','P','P','P','V','V','P'},
                                 {'P','V','V','V','P','P','V','V','V','V','P','P','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','F','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','F','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','P','V','V','V','V','P','P','V','V','V','P'},
                                 {'P','V','V','P','P','P','P','V','V','P','P','P','P','V','V','P'},
                                 {'P','V','V','V','P','P','V','V','V','V','P','P','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','F','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};

        insertPremio(mapa,4);
        insertarObjetos();
    }

     public void generarNivel3() {
         
        NivelNum = 3;
        Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','P','V','V','V','V','V','V','P','V','V','V','V','P'},
                                 {'P','V','F','V','P','V','V','V','V','P','V','V','V','V','V','P'},
                                 {'P','V','P','V','V','P','V','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','F','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','V','P','V','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','F','V','V','V','P'},
                                 {'P','V','P','V','V','P','V','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','F','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','V','P','V','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        
        insertPremio(mapa,4);
        insertarObjetos();
        
    }

    public void generarNivel4() {
        NivelNum = 4;
        Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','F','V','P'},
                                 {'P','V','V','V','P','P','V','V','V','V','V','V','V','P','V','P'},
                                 {'P','V','V','V','V','V','P','V','V','V','V','V','P','V','V','P'},
                                 {'P','V','P','V','V','V','V','P','V','V','V','P','V','V','V','P'},
                                 {'P','V','P','V','V','V','V','P','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','P','F','V','V','V','V','P','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','P','V','V','P','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','P','V','V','P','P','V','V','V','P'},
                                 {'P','V','V','V','V','V','P','V','V','V','V','V','P','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','P','V','V','V','V','P','V','P'},
                                 {'P','V','V','V','F','V','V','V','P','V','V','V','V','P','V','P'},
                                 {'P','V','V','P','V','V','V','V','V','P','V','F','V','V','V','P'},
                                 {'P','V','P','V','V','V','V','V','V','V','P','P','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        
        
        insertPremio(mapa,4);
        insertarObjetos();
    }

    public void generarNivel5() {
        NivelNum = 5;
         Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','P','V','V','V','V','V','V','V','V','P','V','V','P'},
                                 {'P','V','V','V','P','V','V','V','V','V','V','P','V','V','V','P'},
                                 {'P','V','V','V','V','P','V','V','V','V','P','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','F','V','V','V','V','F','V','V','P'},
                                 {'P','V','V','F','V','V','V','P','P','V','V','V','P','V','V','P'},
                                 {'P','V','V','P','V','V','V','P','P','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','P','V','V','V','V','P','F','V','V','V','P'},
                                 {'P','V','V','V','P','V','V','V','V','V','V','P','V','V','V','P'},
                                 {'P','V','V','P','V','V','V','V','V','V','V','V','P','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','F','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        insertPremio(mapa,5);
        insertarObjetos();
    }
    
    public void generarNivel6() {
        NivelNum = 6;      
         Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','F','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','P','P','P','P','V','V','P','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','P','V','V','P','V','P'},
                                 {'P','V','V','F','P','V','V','P','V','V','V','P','V','V','V','P'},
                                 {'P','V','V','P','V','V','P','V','P','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','F','V','V','P','V','V','V','V','V','V','F','V','V','P'},
                                 {'P','V','V','P','V','V','V','V','P','V','V','V','P','V','V','P'},
                                 {'P','V','V','V','P','V','V','P','V','V','V','P','V','V','V','P'},
                                 {'P','V','F','V','V','V','P','V','V','V','P','V','V','V','V','P'},
                                 {'P','V','P','V','V','V','P','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','P','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','F','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        
        insertPremio(mapa,6);
        insertarObjetos();
    }

    public void generarNivel7() {
         NivelNum = 7;
         Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','V','V','V','P','P','P','P','V','P','V','P'},
                                 {'P','V','P','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','F','V','V','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','F','V','P','V','V','V','V','V','V','V','V','P','V','P'},
                                 {'P','V','V','V','P','V','V','V','P','V','V','P','V','F','V','P'},
                                 {'P','V','P','V','V','V','F','V','V','V','V','V','V','P','V','P'},
                                 {'P','V','P','V','P','V','V','V','P','V','V','P','V','P','V','P'},
                                 {'P','V','V','V','P','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','F','V','V','V','V','V','P','V','V','P','V','V','V','P'},
                                 {'P','V','P','V','F','V','V','V','V','V','V','V','V','P','V','P'},
                                 {'P','V','P','V','P','V','V','V','V','V','V','F','V','P','V','P'},
                                 {'P','V','P','V','P','V','V','V','P','P','P','P','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        insertPremio(mapa,7);
        insertarObjetos();
        
    }

    public void generarNivel8() {
         NivelNum = 8;
         Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','P','V','V','P','V','V','V','V','V','P'},
                                 {'P','V','F','V','P','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','P','V','P','V','V','P','V','V','P','V','V','P'},
                                 {'P','V','P','V','V','V','V','V','V','V','V','V','P','V','V','P'},
                                 {'P','V','V','V','V','V','P','V','V','P','V','V','P','V','V','P'},
                                 {'P','V','P','V','P','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','P','P','V','P','V','V','P','V','V','V','V','V','P'},
                                 {'P','V','P','V','P','V','V','V','V','V','V','F','P','F','V','P'},
                                 {'P','V','V','V','V','V','V','F','V','V','V','P','P','P','V','P'},
                                 {'P','V','F','V','V','V','P','P','P','P','V','F','P','F','V','P'},
                                 {'P','V','P','V','P','V','V','P','P','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','P','V','V','P','P','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','P','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','F','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
        
     insertPremio(mapa,8);
     insertarObjetos();
    }

    public void generarNivel9() {
        NivelNum = 9;
        Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','P','P','V','P','P','V','V','P','P','P','V','P'},
                                 {'P','V','P','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','F','V','P','V','F','V','V','V','V','F','V','V','P'},
                                 {'P','V','P','P','V','P','P','P','P','V','V','P','P','P','V','P'},
                                 {'P','V','P','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','P','V','V','P','V','V','V','V','F','V','V','F','V','P'},
                                 {'P','V','P','V','V','P','V','V','V','V','P','V','V','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','P','V','V','P','V','P'},
                                 {'P','V','F','V','V','F','V','V','P','P','P','P','V','P','V','P'},
                                 {'P','V','P','V','V','P','V','V','V','V','V','V','V','F','V','P'},
                                 {'P','V','P','V','V','P','V','V','V','F','V','V','V','V','V','P'},
                                 {'P','V','P','V','V','P','V','V','P','P','P','V','P','P','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};
     insertPremio(mapa,9);
        insertarObjetos();
    }

    public void generarNivel10() {
        NivelNum = 10;
        Character [][] mapa =   {{'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'},
                                 {'P','V','F','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','V','V','F','V','V','P','V','V','V','V','P'},
                                 {'P','V','V','V','P','P','P','V','P','P','P','V','V','V','V','P'},
                                 {'P','V','V','V','P','F','V','V','V','F','P','V','V','F','V','P'},
                                 {'P','F','V','V','V','V','V','P','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','P','P','P','P','P','P','P','V','V','V','V','P'},
                                 {'P','V','V','V','P','V','P','V','P','V','P','V','V','V','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','V','V','V','V','P','V','P','V','P','V','V','V','V','V','P'},
                                 {'P','V','F','V','V','P','P','P','P','P','V','V','V','F','V','P'},
                                 {'P','V','P','P','V','V','V','V','V','V','V','V','P','P','V','P'},
                                 {'P','V','V','P','P','V','V','V','V','V','V','P','P','V','V','P'},
                                 {'P','V','F','V','P','P','V','V','V','V','P','P','V','F','V','P'},
                                 {'P','V','V','V','V','V','V','V','V','V','V','V','V','V','V','P'},
                                 {'P','P','P','P','P','P','P','P','P','P','P','P','P','P','P','P'}};

        insertPremio(mapa,10);
        insertarObjetos();
    }
    
    public void insertPremio(Character [][] mapa,int cantidad){
        VentanaPrincipal.sizePremio = cantidad;
        monedas.clear();
        Point punto;
        for(int i=0 ; i < mapa.length ; i++){
            for(int j=0 ; j < mapa.length ; j++){
                if(mapa[i][j] == 'F'){
                 punto = new Point(j, i);
                 monedas.add(punto);
                }
                Casillas[j][i].tipo = mapa[i][j];
            }
        }
    }

    public void generarPremio(int cantidad) {
        VentanaPrincipal.sizePremio = cantidad;
        Point punto;

        do {
            int x = (int) (Math.random() * 13) + 1;
            int y = (int) (Math.random() * 13) + 1;

            if (Casillas[x][y].tipo == 'V') {
                punto = new Point(x, y);
                monedas.add(punto);
                Casillas[x][y].tipo = 'F';
                cantidad = cantidad - 1;
            }

        } while (cantidad > 0);
    }

    public boolean EsunWinner() {
        if (i_jugador == i_fin && j_jugador == j_fin) {
            return true;
        } else {
            return false;
        }

    }

    public int getNivelLaberinto() {
        return NivelNum;
    }

    public void generarNivelNuevo(int x) {
        if (x == 0) {
            generarNivelInicial();
        }
        if (x == 1) {
            generarNivel1();
        }
        if (x == 2) {
            generarNivel2();
        }
        if (x == 3) {
            generarNivel3();
        }
        if (x == 4) {
            generarNivel4();
        }
        if (x == 5) {
            generarNivel5();
        }
        if (x == 6) {
            generarNivel6();
        }
        if (x == 7) {
            generarNivel7();
        }
        if (x == 8) {
            generarNivel8();
        }
        if (x == 9) {
            generarNivel9();
        }
        if (x == 10) {
            generarNivel10();
        }
    }

    public void vaciarLaberinto() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Casillas[i][j].tipo = 'V';
            }
        }

    }

    public void defaultPosicionLaberinto() {
        i_jugador = 1;
        j_jugador = 1;
        i_jugador2 = 14;
        j_jugador2 = 1;
        i_fin = 14;
        j_fin = 14;
        Casillas[i_jugador][j_jugador].j1abajo();
        Casillas[i_jugador2][j_jugador2].j1abajo();
    }

    public void sonidoMoneda() {
        Reproductor musica_de_vivo = new Reproductor();
        try {
            musica_de_vivo.AbrirFichero("src/Sounds/moneda.wav");
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            musica_de_vivo.Play();
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarContornoInterno() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 1 && i > 0 && i < 16 || i == 1 && j > 0 && j < 16 || j == 14 && i > 0 && i < 16 || i == 14 && j < 16 && j > 0) {
                    Casillas[i][j].tipo = 'V';
                }
            }
        }
    }

    public int protegerMeta() {
        int i;
        if (VentanaPrincipal.countPremio <= VentanaPrincipal.sizePremio) {
            i = VentanaPrincipal.countPremio;
            return i;

        } else {
            return 0;
        }
    }
}
