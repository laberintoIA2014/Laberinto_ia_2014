package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Busqueda3 implements Constantes {
    
    public Lienzo lienzo;
    public PriorityQueue<Estado3> colaEstados;
    public ArrayList<Estado3> historial;
    public ArrayList<Character> pasos;
    public int index_pasos;
    public Estado3 inicial;
    public Estado3 objetivo;
    public Estado3 temp;
    public boolean exito, tipoBusqueda, role;
    
    public Busqueda3(Lienzo lienzo) {
        this.lienzo = lienzo;
        colaEstados = new PriorityQueue<Estado3>();
        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos = 0;
        exito = false;
    }
    
    public void buscar() {
        if (role == true) {
            inicial = new Estado3(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 0, 'N', null);
            objetivo = new Estado3(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 0, 'N', null);
            colaEstados.add(inicial);
        }
        if (role == false) {
            inicial = new Estado3(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 0, 'N', null);
            objetivo = new Estado3(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 0, 'N', null);
            colaEstados.add(inicial);
        }
        
        if (inicial.equals(objetivo)) {
            exito = true;
        }
        
        while (!colaEstados.isEmpty() && !exito) {
            
            temp = colaEstados.poll();
          //System.out.println("WHILE ACTUAL->    " + temp.x + ", " + temp.y + " " + temp.getF());
            if (temp.equals(objetivo)) {
                objetivo = temp;
                exito = true;
                
            } else {
                
                if (!historial.contains(temp)) {
                  //System.out.println("COLAESTADO ACTUAL ->    " + colaEstados);
                  //System.out.println("HISTORIAL ACTUAL ->   " + historial);
                    historial.add(temp);
                    expandir(temp);
                }
                colaEstados.remove(0); //Anchura
            }
        }
        
        if (exito) {
            //System.out.println("Ruta Calculada");
        } else {
            //System.out.println("La Ruta no pudo Calcularse");
        }
    }
    
    public void expandir(Estado3 temp) {
        
        moverAbajo(temp);
        moverArriba(temp);
        moverIzquierda(temp);
        moverDerecha(temp);
        
    }
    
    public synchronized void moverArriba(Estado3 e) {
        
        if (e.y > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'P') {
                Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                arriba.calcularF(e, objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(arriba); // busqueda en anchura;
                } else {
                    //  colaEstados.add(0, arriba); // busqueda en profundidad;
                }
            }
        }
        
    }
    
    public synchronized void moverAbajo(Estado3 e) {
        
        if (e.y + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'P') {
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                abajo.calcularF(e, objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(abajo);
                } else {
                    //  colaEstados.add(0, abajo); // busqueda en profundidad;
                }
                
            }
            
        }
        
    }
    
    public synchronized void moverIzquierda(Estado3 e) {
        
        if (e.x > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'P') {
                Estado3 izquierda = new Estado3(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                izquierda.calcularF(e, objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(izquierda); // busqueda en anchura;
                } else {
                    // colaEstados.add(0, izquierda); // busqueda en profundidad;
                }
                
            }
            
        }
    }
    
    public synchronized void moverDerecha(Estado3 e) {
        
        if (e.x + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'P') {
                Estado3 derecha = new Estado3(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
                derecha.calcularF(e, objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(derecha); // busqueda en anchura;
                } else {
                   //colaEstados.add(0, derecha); // busqueda en profundidad;
                }
                
            }
            
        }
    }
    
    public void calcularRuta() {
        Estado3 antecesor = objetivo;
        do {
            pasos.add(antecesor.Movimiento);
            antecesor = antecesor.Antecesor;
            
        } while (antecesor != null);
    }
    
    public void setTipoBusqueda(boolean bool) {
        this.tipoBusqueda = bool;
    }
    
    public boolean getTipoBusqueda() {
        return true;
    }
    
    public void setRole(boolean bool) {
        this.role = bool;
    }
    
    public boolean getRole() {
        return role;
    }
    
    public void parar(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
