package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Busqueda3 implements Constantes {

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

    public Busqueda3(Lienzo lienzo) {
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

        moverIzquierdaJugador(temp,0);
        moverDerechaJugador(temp,0);
        moverAbajoJugador(temp,0);
        moverArribaJugador(temp,0);
        

    }

    public void expandirEnemigo(Estado3 temp) {

        moverAbajoEnemigo(temp);
        moverIzquierdaEnemigo(temp);
        moverArribaEnemigo(temp);
        
        moverDerechaEnemigo(temp);

    }

    public synchronized void moverArribaJugador(Estado3 e, double valor) {
        if (e.y > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'P'&& lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'H') {
                if(e.y - 2>=0){
                    if(lienzo.getLaberinto().getCasillas()[e.x][e.y - 2].tipo != 'H'){
                        if(e.x-1>=0)
                        {
                            if(lienzo.getLaberinto().getCasillas()[e.x-1][e.y - 1].tipo != 'H'){
                                if(e.x+1<16){
                                    if(lienzo.getLaberinto().getCasillas()[e.x+1][e.y - 1].tipo != 'H')
                                    {
                                        if(valor==0){
                                            valor=e.setF(e.x, e.y - 1, objetivo);
                                        }
                                        Estado3 arriba = new Estado3(e.x, e.y - 1,valor , 'U', e);
                                        if (getTipoBusqueda()) {
                                            colaEstados.add(arriba); // busqueda en anchura;
                                        } else {
                                            //  colaEstados.add(0, arriba); // busqueda en profundidad;
                                        }
                                    }
                                    else{
                                        moverIzquierdaJugador(e,valor-1);
                                    }
                                }
                            }
                            else{
                                moverDerechaJugador(e,valor-1);
                                
                            }
                        }
                    }

                }
            }
            else if(lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo == 'H'){
                 moverAbajoJugador(e,valor-1);
            }
        }
    }

    public synchronized void moverAbajoJugador(Estado3 e, double valor) {
        if (e.y + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'P'&&lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'H') {
                 if(e.y +2<16){
                    if(lienzo.getLaberinto().getCasillas()[e.x][e.y + 2].tipo != 'H'){
                        if(e.x-1>=0)
                        {
                            if(lienzo.getLaberinto().getCasillas()[e.x-1][e.y + 1].tipo != 'H'){
                                if(e.x+1<16){
                                    if(lienzo.getLaberinto().getCasillas()[e.x+1][e.y + 1].tipo != 'H')
                                    {
                                        if(valor==0){
                                            valor=e.setF(e.x, e.y + 1, objetivo);
                                        }
                                        Estado3 abajo = new Estado3(e.x, e.y + 1,valor , 'D', e);
                                        if (getTipoBusqueda()) {
                                            colaEstados.add(abajo);
                                        } else {
                                            //  colaEstados.add(0, abajo); // busqueda en profundidad;
                                        }
                                    }else{
                                        
                                        moverIzquierdaJugador(e,valor-1);  
                                    }
                                }
                            }
                            else{
                                 moverDerechaJugador(e,valor-1);  
                            }
                        }
                    }
                }
                

            }
            else if(lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo == 'H'){
                 moverArribaJugador(e,valor-1);
            }
        }
    }

    public synchronized void moverIzquierdaJugador(Estado3 e, double valor) {
        if (e.x > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'H') {
                if(e.x - 2>=0){
                    if(lienzo.getLaberinto().getCasillas()[e.x - 2][e.y].tipo != 'H'){
                        if(e.y-1>=0)
                        {
                            if(lienzo.getLaberinto().getCasillas()[e.x-1][e.y-1].tipo != 'H'){
                                if(e.y+1<16){
                                    if(lienzo.getLaberinto().getCasillas()[e.x-1][e.y+1].tipo != 'H')
                                    {
                                        if(valor==0){
                                            valor= e.setF(e.x - 1, e.y, objetivo);
                                        }
                                        Estado3 izquierda = new Estado3(e.x - 1, e.y, valor, 'L', e);
                                        if (getTipoBusqueda()) {
                                            colaEstados.add(izquierda); // busqueda en anchura;
                                        } else {
                                            // colaEstados.add(0, izquierda); // busqueda en profundidad;
                                        }
                                    }else{
                                        moverArribaJugador(e,valor-1);
                                    }
                                }
                            }
                            else{
                                moverAbajoJugador(e,valor-1);
                            }
                        }
                    }
                } 
            }
            else if(lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo == 'H'){
                moverDerechaJugador(e,valor-1);
            }
        }
    }

    public synchronized void moverDerechaJugador(Estado3 e, double valor) {
        if (e.x + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'P' && lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'H') {
               if(e.x + 2<16){
                    if(lienzo.getLaberinto().getCasillas()[e.x + 2][e.y].tipo != 'H'){
                        if(e.y-1>=0)
                        {
                            if(lienzo.getLaberinto().getCasillas()[e.x+1][e.y-1].tipo != 'H'){
                                if(e.y+1<16){
                                    if(lienzo.getLaberinto().getCasillas()[e.x+1][e.y+1].tipo != 'H')
                                    {
                                        if(valor==0){
                                            valor= e.setF(e.x + 1, e.y, objetivo);
                                        }
                                        Estado3 derecha = new Estado3(e.x + 1, e.y, valor, 'R', e);
                                        if (getTipoBusqueda()) {
                                            colaEstados.add(derecha); // busqueda en anchura;
                                        } else {
                                            //colaEstados.add(0, derecha); // busqueda en profundidad;
                                        }
                                    }else{
                                        moverArribaJugador(e,valor-1);
                                    }
                                }
                            }else{
                                moverAbajoJugador(e,valor-1);
                            }
                        }
                    }
                }
            }
            else if(lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo == 'H'){
                moverIzquierdaJugador(e,valor-1);
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
