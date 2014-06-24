package Busqueda;

import java.util.Objects;

public class Estado implements Comparable {

    public int x, y;
    public double f, g, h;
    public Estado Antecesor;
    public char Movimiento;

    public Estado(int x, int y, char Movimiento, Estado Antecesor) {
        this.x = x;
        this.y = y;
        this.Antecesor = Antecesor;
        this.Movimiento = Movimiento;
    }

    public Estado getAntecesor() {
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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object x) {
        Estado e = (Estado) x;
        return this.x == e.x && this.y == e.y;
    }

    @Override
    public int compareTo(Object x) {
        Estado e = (Estado) x;
        if (this.f == e.f) {
            return 0;
        } else {
            if (this.f > e.f) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.f) ^ (Double.doubleToLongBits(this.f) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.g) ^ (Double.doubleToLongBits(this.g) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.h) ^ (Double.doubleToLongBits(this.h) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.Antecesor);
        hash = 53 * hash + this.Movimiento;
        return hash;
    }
}
