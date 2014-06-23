package Modelado;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Lienzo extends Canvas {

    public static Laberinto laberinto;

    public Lienzo() {

        laberinto = new Laberinto();
        this.setBackground(Color.DARK_GRAY);
        this.setSize(laberinto.Ancho, laberinto.Largo);

      //laberinto.generarNivelRandom();
        //laberinto.generarNivelRandom();
      //laberinto.generarNivel1();
        //laberinto.generarNivel2();
        laberinto.generarNivel3();


        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                identificarCelda(evt);
                repaint();
            }

        });

        addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                laberinto.chequearTecla(e);
                repaint();
            }

        });

    }

    @Override
    public void update(Graphics g) {
        laberinto.update(g);
    }

    @Override
    public void paint(Graphics g) {
        laberinto.paintComponent(g);
    }

    public void identificarCelda(java.awt.event.MouseEvent evt) {

        for (int i = 0; i < laberinto.Casillas.length; i++) {
            for (int j = 0; j < laberinto.Casillas.length; j++) {
                if (laberinto.Casillas[i][j].dentro_area(evt.getX(), evt.getY())) {
                    if (laberinto.Casillas[i][j].tipo == 'P') {
                        laberinto.Casillas[i][j].tipo = 'V';
                    } else if (laberinto.Casillas[i][j].tipo == 'V') {
                        laberinto.Casillas[i][j].tipo = 'P';
                    }
                }
            }
        }

    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

}
