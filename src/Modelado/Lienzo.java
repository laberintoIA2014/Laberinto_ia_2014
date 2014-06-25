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

    public Laberinto getLaberinto() {
        return laberinto;
    }

}
