package Modelado;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {

    private int x, y;                             //Coordenada x e y
    public char tipo;
    public BufferedImage jugador_uno, jugador_dos, enemigo, pared, piso, premio, fin;
    boolean seleccionado;                        //Estado de la Celda

    //Constructor Celda
    public Celda(int x, int y, char tipo) {

        this.x = x;
        this.y = y;
        this.tipo = tipo;

        try {
            jugador_uno = ImageIO.read(new File("src/Images/jugador1_abajo.png"));
            jugador_dos = ImageIO.read(new File("src/Images/jugador2_abajo.png"));
            enemigo = ImageIO.read(new File("src/Images/enemigo.png"));
            piso = ImageIO.read(new File("src/Images/piso.jpg"));
            pared = ImageIO.read(new File("src/Images/pared.jpg"));
            fin = ImageIO.read(new File("src/Images/fin.jpg"));
            premio = ImageIO.read(new File("src/Images/premio.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        /*
         if (seleccionado) {
         g.drawRect(x, y, Longitud_Casilla, Longitud_Casilla);
         g.fillRect(x, y, Longitud_Casilla, Longitud_Casilla);
         } else {
         g.drawRect(x, y, Longitud_Casilla, Longitud_Casilla);
         }
         */

        switch (tipo) {
            case 'J':
                g.drawImage(jugador_uno, x, y, null);
                break;
            case 'H':
                g.drawImage(jugador_dos, x, y, null);
                break;
            case 'E':
                g.drawImage(enemigo, x, y, null);
                break;
            case 'P':
                g.drawImage(pared, x, y, null);
                break;
            case 'V':
                g.drawImage(piso, x, y, null);
                break;
            case 'F':
                g.drawImage(premio, x, y, null);
                break;
            case 'M':
                g.drawImage(fin, x, y, null);
                break;
        }

    }

    public boolean dentro_area(int xp, int yp) {
        Rectangle r = new Rectangle(x, y, Constantes.Longitud_Casilla, Constantes.Longitud_Casilla);
        return r.contains(new Point(xp, yp));
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public void j1abajo() {
        try {
            jugador_uno = ImageIO.read(new File("src/Images/jugador1_abajo.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j1derecha() {
        try {
            jugador_uno = ImageIO.read(new File("src/Images/jugador1_derecha.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j1arriba() {
        try {
            jugador_uno = ImageIO.read(new File("src/Images/jugador1_arriba.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j1izquierda() {
        try {
            jugador_uno = ImageIO.read(new File("src/Images/jugador1_izquierda.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j2abajo() {
        try {
            jugador_dos = ImageIO.read(new File("src/Images/jugador2_abajo.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j2derecha() {
        try {
            jugador_dos = ImageIO.read(new File("src/Images/jugador2_derecha.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j2arriba() {
        try {
            jugador_dos = ImageIO.read(new File("src/Images/jugador2_arriba.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void j2izquierda() {
        try {
            jugador_dos = ImageIO.read(new File("src/Images/jugador2_izquierda.png"));
        } catch (IOException ex) {
            Logger.getLogger(Celda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
