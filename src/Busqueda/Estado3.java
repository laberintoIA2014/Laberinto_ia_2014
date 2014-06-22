package Busqueda;

import java.util.Objects;

public class Estado3{
    
    public int x, y;
    public Estado3 Antecesor;
    public char Movimiento;
    public double f,g,h;
    
    public Estado3(int x, int y, double f, double g, double h, char Movimiento, Estado3 Antecesor) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.g = g;
        this.h = h;
        this.Antecesor = Antecesor;
        this.Movimiento = Movimiento;
    }
        
    public Estado3 getAntecesor() {
        return Antecesor;
    }

    public char getMovimiento() {
        return Movimiento;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return x;
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";   
    }
    
    @Override
    public boolean equals(Object x) {
    Estado3 e = (Estado3)x;
        return this.x==e.x && this.y==e.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        hash = 79 * hash + Objects.hashCode(this.Antecesor);
        hash = 79 * hash + this.Movimiento;
        return hash;
    }
    
}
