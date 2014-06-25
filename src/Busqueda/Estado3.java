package Busqueda;

import java.util.Objects;

public class Estado3 implements Comparable<Estado3> {

    public int x, y;
    public Estado3 Antecesor;
    public char Movimiento;
    public double f, g, h;

    public Estado3(int x, int y, double f, char Movimiento, Estado3 Antecesor) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.Antecesor = Antecesor;
        this.Movimiento = Movimiento;
    }

    public double getG() {
        return g;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void calcularF(Estado3 actual, Estado3 objetivo) {

        this.g = actual.getG()
                + Math.sqrt(((Math.pow(Math.abs(actual.getX() - this.getX()), 2))
                        + (Math.pow(Math.abs(actual.getY() - this.getY()), 2))));

        this.h = Math.sqrt(((Math.pow(Math.abs(objetivo.getX() - this.getX()), 2))
                + (Math.pow(Math.abs(objetivo.getY() - this.getY()), 2))));

        this.f = this.g + this.h;
    }

    public Estado3 getAntecesor() {
        return Antecesor;
    }

    public char getMovimiento() {
        return Movimiento;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double setF(int x, int y, Estado3 meta) {
        f = ((Math.pow(Math.abs(x - meta.x), 2))
                + (Math.pow(Math.abs(y - meta.y), 2)));
        return f;
    }

    public double getF() {
        return f;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object x) {
        Estado3 e = (Estado3) x;
        return this.x == e.x && this.y == e.y;
    }

    @Override
    public int compareTo(Estado3 o) {
        if (o.getF() == this.getF()) {
            return 0;
        } else if (this.getF() > o.getF()) {
            return 1;
        }

        return -1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        hash = 89 * hash + Objects.hashCode(this.Antecesor);
        hash = 89 * hash + this.Movimiento;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.f) ^ (Double.doubleToLongBits(this.f) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.g) ^ (Double.doubleToLongBits(this.g) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.h) ^ (Double.doubleToLongBits(this.h) >>> 32));
        return hash;
    }

}
