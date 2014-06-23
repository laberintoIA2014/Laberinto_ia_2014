package Busqueda;

import java.util.Objects;

public class Estado2 implements Comparable {

    private int x, y;
    private double f, g, h;
    private Estado2 antecesor;
    private char movimiento;

    public Estado2(int x, int y, char movimiento, Estado2 antecesor) {
        this.x = x;
        this.y = y;
        this.antecesor = antecesor;
        this.movimiento = movimiento;
    }

    public Estado2 getAntecesor() {
        return antecesor;
    }

    public void setAntecesor(Estado2 antecesor) {
        this.antecesor = antecesor;
    }

    public char getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(char movimiento) {
        this.movimiento = movimiento;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setG(int g) {
        this.g = g;
    }

    public double getG() {
        return this.g;
    }

    public double getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void calcularH(Estado2 meta) {
        this.h = ((Math.pow(Math.abs(meta.x - this.x), 2))
                + (Math.pow(Math.abs(meta.y - this.y), 2)));
    }

    public void calcularG() {
        this.g = 0;
    }

    public void calcularF() {
        this.f = this.h + this.g;
    }

    public double getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     *
     * @param x
     * @return
     */
    @Override
    public boolean equals(Object x) {
        Estado2 e = (Estado2) x;
        return this.x == e.getX() && this.y == e.getY();
    }

    @Override
    public int compareTo(Object x) {
        Estado2 o = (Estado2) x;
        if (o.getH() > this.getH()) {
            return -1;
        } else if (o.getH() < this.getH()) {
            return 1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        hash = 71 * hash + Objects.hashCode(this.antecesor);
        hash = 71 * hash + this.movimiento;
        return hash;
    }

}
