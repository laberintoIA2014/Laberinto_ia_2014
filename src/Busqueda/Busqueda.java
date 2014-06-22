package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.util.ArrayList;

public class Busqueda implements Constantes {

    public Lienzo lienzo;
    public ArrayList<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    public int index_pasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito, tipoBusqueda, role;

    public Busqueda(Lienzo lienzo) {
        this.lienzo = lienzo;
        colaEstados = new ArrayList<>();
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

        if (role == false) {
            inicial = new Estado(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 'N', null);
            objetivo = new Estado(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 'N', null);
            colaEstados.add(inicial);
        }
        if (role == true) {
            inicial = new Estado(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 'N', null);
            objetivo = new Estado(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 'N', null);
            colaEstados.add(inicial);

        }

        if (inicial.equals(objetivo)) {
            exito = true;
        }

        while (!colaEstados.isEmpty() && !exito) {

            temp = colaEstados.get(0);
            if (!getTipoBusqueda()) {
                colaEstados.remove(0); //Profundidad
            }
            if (temp.equals(objetivo)) {
                objetivo = temp;
                exito = true;

            } else {

                if (!historial.contains(temp)) {

                    historial.add(temp);

                    expandir(temp);

                }

                if (getTipoBusqueda()) {
                    colaEstados.remove(0);  //Anchura
                }
            }

        }

        if (exito) {
            System.out.println("Ruta Calculada");
        } else {
            System.out.println("La Ruta no pudo Calcularse");
        }
    }

    public void expandir(Estado temp) {

        moverAbajo(temp);
        moverArriba(temp);
        moverIzquierda(temp);
        moverDerecha(temp);

    }

    public void moverArriba(Estado e) {

        if (e.y > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y - 1].tipo != 'P') {
                Estado arriba = new Estado(e.x, e.y - 1, 'U', e);

                if (getTipoBusqueda()) {
                    colaEstados.add(arriba); // busqueda en anchura;
                } else {
                    colaEstados.add(0, arriba); // busqueda en profundidad;
                }
            }
        }

    }

    public void moverAbajo(Estado e) {

        if (e.y + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x][e.y + 1].tipo != 'P') {
                Estado abajo = new Estado(e.x, e.y + 1, 'D', e);

                if (getTipoBusqueda()) {
                    colaEstados.add(abajo);
                } else {
                    colaEstados.add(0, abajo); // busqueda en profundidad;
                }

            }

        }

    }

    public void moverIzquierda(Estado e) {

        if (e.x > 0) {
            if (lienzo.getLaberinto().getCasillas()[e.x - 1][e.y].tipo != 'P') {
                Estado izquierda = new Estado(e.x - 1, e.y, 'L', e);

                if (getTipoBusqueda()) {
                    colaEstados.add(izquierda); // busqueda en anchura;
                } else {
                    colaEstados.add(0, izquierda); // busqueda en profundidad;
                }

            }

        }
    }

    public void moverDerecha(Estado e) {

        if (e.x + 1 < 16) {
            if (lienzo.getLaberinto().getCasillas()[e.x + 1][e.y].tipo != 'P') {
                Estado derecha = new Estado(e.x + 1, e.y, 'R', e);

                if (getTipoBusqueda()) {
                    colaEstados.add(derecha); // busqueda en anchura;
                } else {
                    colaEstados.add(0, derecha); // busqueda en profundidad;
                }

            }

        }
    }

    public void calcularRuta() {
        Estado antecesor = objetivo;
        do {

            pasos.add(antecesor.Movimiento);
            antecesor = antecesor.Antecesor;

        } while (antecesor != null);
    }

    public void setTipoBusqueda(boolean bool) {
        this.tipoBusqueda = bool;
    }

    public boolean getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setRole(boolean bool) {
        this.role = bool;
    }

    public boolean getRole() {
        return role;
    }

}
