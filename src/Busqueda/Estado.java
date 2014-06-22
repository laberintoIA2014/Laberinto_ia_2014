package Busqueda;

import java.util.Objects;

public class Estado{
    
    public int x, y;
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
    Estado e = (Estado)x;
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
