/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Busqueda2 implements Constantes {

    public Lienzo lienzo;
    public PriorityQueue<Estado2> colaEstados;
    public ArrayList<Estado2> historial;
    public ArrayList<Character> pasos;
    public int index_pasos;
    public Estado2 inicial;
    public Estado2 objetivo;
    public Estado2 temp;
    public boolean exito, tipoBusqueda, role;

    public Busqueda2(Lienzo lienzo) {
        this.lienzo = lienzo;
        colaEstados = new PriorityQueue<Estado2>();

        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos = 0;
        //digo cual es el estado inicial y el final
        //inicial = new Estado(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 'N', null);
        //objetivo = new Estado(13, 13, 'N', null);
        //colaEstados.add(inicial);
        exito = false;

    }

    public void buscar() {

        if (role == true) {
            inicial = new Estado2(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 'N', null);
            objetivo = new Estado2(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 'N', null);
            colaEstados.add(inicial);
        }
        if(role == false){
            inicial = new Estado2(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 'N', null);
            objetivo = new Estado2(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 'N', null);
            colaEstados.add(inicial);
        
        }

        if (inicial.equals(objetivo)) {
            exito = true;
        }

        while (!colaEstados.isEmpty() && !exito) {
        
            temp = colaEstados.poll();
            System.out.println("WHILE ACTUAL->    "+temp.getX()+", "+temp.getY()+" "+temp.getF());
            if (temp.equals(objetivo)) {
                objetivo = temp;
                exito = true;

            } else {

                if (!historial.contains(temp)) {
                    System.out.println("COLAESTADO ACTUAL ->    "+colaEstados);
                 
                    System.out.println("HISTORIAL ACTUAL ->   "+historial);
                    historial.add(temp);
                 
                    expandir(temp);

                }
                
                colaEstados.remove(0);  //Anchura
       
            }

        }

        if (exito) {
            System.out.println("Ruta Calculada");
        } else {
            System.out.println("La Ruta no pudo Calcularse");
        }
    }

    public void expandir(Estado2 temp) {
        //System.out.println("ACTUAL ->    "+temp.x+", "+temp.y+" "+temp.getF());
        moverAbajo(temp);
        moverArriba(temp);
        moverIzquierda(temp);
        moverDerecha(temp);

    }

    public void moverArriba(Estado2 e) {

        if (e.getY() > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.getX()][e.getY() - 1].tipo != 'P') {
                Estado2 arriba = new Estado2(e.getX(), e.getY() - 1, 'U', e);
                arriba.calcularH(objetivo);

                if (getTipoBusqueda()) {
                    colaEstados.add(arriba); // busqueda en anchura;
                } else {
                    //  colaEstados.add(0, arriba); // busqueda en profundidad;
                }
            }
        }

    }

    public void moverAbajo(Estado2 e) {

        if (e.getY() + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.getX()][e.getY() + 1].tipo != 'P') {
                Estado2 abajo = new Estado2(e.getX(), e.getY() + 1, 'D', e);
                abajo.calcularH(objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(abajo);
                } else {
                    //  colaEstados.add(0, abajo); // busqueda en profundidad;
                }

            }

        }

    }

    public void moverIzquierda(Estado2 e) {

        if (e.getX() > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.getX() - 1][e.getY()].tipo != 'P') {
                Estado2 izquierda = new Estado2(e.getX() - 1, e.getY(), 'L', e);
                     izquierda.calcularH(objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(izquierda); // busqueda en anchura;
                } else {
                    // colaEstados.add(0, izquierda); // busqueda en profundidad;
                }

            }

        }
    }

    public void moverDerecha(Estado2 e) {

        if (e.getX() + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.getX()+ 1][e.getY()].tipo != 'P') {
                Estado2 derecha = new Estado2(e.getX() + 1, e.getY(), 'R', e);
                derecha.calcularH(objetivo);
                if (getTipoBusqueda()) {
                    colaEstados.add(derecha); // busqueda en anchura;
                } else {
                    // colaEstados.add(0, derecha); // busqueda en profundidad;
                }

            }

        }
    }

    public void calcularRuta() {
        Estado2 antecesor = objetivo;
        do {

            pasos.add(antecesor.getMovimiento());
            antecesor = antecesor.getAntecesor();

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

