package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Busqueda4 implements Constantes {

    public Lienzo lienzo;
    public PriorityQueue<Estado3> colaEstados;
    public ArrayList<Estado3> historial;
    public ArrayList<Character> pasos;
    public ArrayList<Point> premios;
    public int index_pasos;
    public Estado3 inicial;
    public Estado3 objetivo;
    public Estado3 temp;
    public boolean exito, tipoBusqueda, role;

    public Busqueda4(Lienzo lienzo) {
        this.lienzo = lienzo;
        colaEstados = new PriorityQueue<Estado3>();
        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos = 0;
        exito = false;
        premios = new ArrayList<>();

    }

    public void buscarJugador() {
        if (role == true) {
            inicial = new Estado3(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 0, 'N', null);
            objetivo = new Estado3(lienzo.getLaberinto().distanciaJugador().x, lienzo.getLaberinto().distanciaJugador().y, 0, 'N', null);
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
                    expandirJugador(temp);
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

    public void buscarEnemigo() {

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
                    expandirEnemigo(temp);
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

    public void expandirJugador(Estado3 temp) {

        moverAbajoJugador(temp);
        moverArribaJugador(temp);
        moverIzquierdaJugador(temp);
        moverDerechaJugador(temp);

    }
    
    public boolean verEspacioActual(int x, int y){
       if(x>0 && y > 0 && x<16 && y<16){
        return  lienzo.getLaberinto().getCasillas()[x][y].tipo != 'P' &&
                lienzo.getLaberinto().getCasillas()[x][y].tipo != 'H';
        }
        return false;
    }
    
    public boolean verEspacioFuturo(int x, int y){
        if(x>0 && y >0 && x<16 && y<16){
        return  lienzo.getLaberinto().getCasillas()[x][y].tipo != 'H';
        }
        return false;
    }

    public void expandirEnemigo(Estado3 temp) {

        moverAbajoEnemigo(temp);
        moverArribaEnemigo(temp);
        moverIzquierdaEnemigo(temp);
        moverDerechaEnemigo(temp);

    }

    public synchronized void moverArribaJugador(Estado3 e) {
            if (verEspacioActual(e.x,e.y-1)) {
                    if (verEspacioFuturo(e.x,e.y-2)) {
                            if (verEspacioFuturo(e.x-1,e.y-1)) {
                                    if (verEspacioFuturo(e.x+1,e.y-1)) {
                                        Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                                        colaEstados.add(arriba); // busqueda en anchura;
                                        
                                     }
                            }
                     }
             }
     }

    public synchronized void moverAbajoJugador(Estado3 e) {
            if (verEspacioActual(e.x,e.y+1)) {
                    if (verEspacioFuturo(e.x-1,e.y+1)) {
                            if (verEspacioFuturo(e.x+1,e.y+1)) {
                                    if (verEspacioFuturo(e.x,e.y+2)) {
                                        Estado3 arriba = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'U', e);
                                        colaEstados.add(arriba); // busqueda en anchura;
                                        
                                     }  
                             }
                     }
              }
     }

    public synchronized void moverIzquierdaJugador(Estado3 e) {
        if (e.x > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'H') {
                if(e.x - 2>=0){
                    if(lienzo.getLaberinto().getCasillas()[e.x - 2][e.y].tipo != 'H'){
                        if(e.y-1>=0 && e.x-1>=0)
                        {
                            if(lienzo.getLaberinto().getCasillas()[e.x-1][e.y-1].tipo != 'H'){
                                if(e.y+1<=16){
                                    if(lienzo.getLaberinto().getCasillas()[e.x-1][e.y+1].tipo != 'H')
                                    {
                                        Estado3 izquierda = new Estado3(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                                        if (getTipoBusqueda()) {
                                            colaEstados.add(izquierda); // busqueda en anchura;
                                        } else {
                                            // colaEstados.add(0, izquierda); // busqueda en profundidad;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } 
            }
        }
    }

    public synchronized void moverDerechaJugador(Estado3 e) {
        if (e.x + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'H') {
               if(e.x + 2<=16){
                    if(lienzo.getLaberinto().getCasillas()[e.x + 2][e.y].tipo != 'H'){
                        if(e.y-1>=0)
                        {
                            if(lienzo.getLaberinto().getCasillas()[e.x+1][e.y-1].tipo != 'H'){
                                if(e.y+1<=16 && e.x+1<=16){
                                    if(lienzo.getLaberinto().getCasillas()[e.x+1][e.y+1].tipo != 'H')
                                    {
                                        Estado3 derecha = new Estado3(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
                                        if (getTipoBusqueda()) {
                                            colaEstados.add(derecha); // busqueda en anchura;
                                        } else {
                                            //colaEstados.add(0, derecha); // busqueda en profundidad;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public synchronized void moverArribaEnemigo(Estado3 e) {
        if (e.y > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'F' && lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'M') {
                Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                if (getTipoBusqueda()) {
                    colaEstados.add(arriba); // busqueda en anchura;
                } else {
                    //  colaEstados.add(0, arriba); // busqueda en profundidad;
                }
            }

        }

    }

    public synchronized void moverAbajoEnemigo(Estado3 e) {
        if (e.y + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'F' && lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'M') {
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                if (getTipoBusqueda()) {
                    colaEstados.add(abajo);
                } else {
                    //  colaEstados.add(0, abajo); // busqueda en profundidad;
                }

            }

        }
    }

    public synchronized void moverIzquierdaEnemigo(Estado3 e) {
        if (e.x > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'F' && lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'M') {
                Estado3 izquierda = new Estado3(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                if (getTipoBusqueda()) {
                    colaEstados.add(izquierda); // busqueda en anchura;
                } else {
                    // colaEstados.add(0, izquierda); // busqueda en profundidad;
                }
            }
        }
    }

    public synchronized void moverDerechaEnemigo(Estado3 e) {
        if (e.x + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'F' && lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'M') {
                Estado3 derecha = new Estado3(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
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
