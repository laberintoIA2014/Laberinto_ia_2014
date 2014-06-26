package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Busqueda implements Constantes {

    public Lienzo lienzo;
    public PriorityQueue<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    public ArrayList<Point> premios;
    public int index_pasos;
    public int nivel_busqueda_Jugador ;
    public int nivel_busqueda_Enemigo;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito, tipoBusqueda, role;

    public Busqueda(Lienzo lienzo) {
        this.lienzo = lienzo;
        colaEstados = new PriorityQueue<Estado>();
        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos = 0;
        exito = false;
        premios = new ArrayList<>();
    }

    public void buscarJugador() {
        System.out.println(" Inteligencia ACTUAL JUGADOR " + nivel_busqueda_Jugador);
        if (role == true) {
            inicial = new Estado(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 0, 'N', null);
            objetivo = new Estado(lienzo.getLaberinto().distanciaJugador().x, lienzo.getLaberinto().distanciaJugador().y, 0, 'N', null);
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
        System.out.println(" Inteligencia ACTUAL ENEMIGO " + nivel_busqueda_Enemigo);
        if (role == false) {
            inicial = new Estado(lienzo.getLaberinto().getJugador2X(), lienzo.getLaberinto().getJugador2Y(), 0, 'N', null);
            objetivo = new Estado(lienzo.getLaberinto().getJugadorX(), lienzo.getLaberinto().getJugadorY(), 0, 'N', null);
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

    public void expandirJugador(Estado temp) {
        moverAbajoJugador(temp);
        moverArribaJugador(temp);
        moverIzquierdaJugador(temp);
        moverDerechaJugador(temp);
    }
    
    public double distanciaJugadorEnemigo(Estado actual){
        
        return Math.sqrt(((Math.pow(Math.abs(objetivo.x - actual.x), 2)))
                    + (Math.pow(Math.abs(objetivo.y - actual.y), 2)));
        
    }

    public boolean verEspacioActual_Jugador(int x, int y) {
        if (x >= 0 && y >= 0 && x < 16 && y < 16) {
            return lienzo.getLaberinto().getCasillas()[x][y].tipo != 'P'
                    && lienzo.getLaberinto().getCasillas()[x][y].tipo != 'H';
        }
        return false;
    }

    public boolean verEspacioFuturo_Jugador(int x, int y) {
        if (x >= 0 && y >= 0 && x < 16 && y < 16) {
            return lienzo.getLaberinto().getCasillas()[x][y].tipo != 'H';
        }
        return false;
    }

    public boolean verEspacioActual_Enemigo(int x, int y) {
        if (x >= 0 && y >= 0 && x < 16 && y < 16) {
            return lienzo.getLaberinto().getCasillas()[x][y].tipo != 'P'
                    && lienzo.getLaberinto().getCasillas()[x][y].tipo != 'F'
                    && lienzo.getLaberinto().getCasillas()[x][y].tipo != 'M';
        }
        return false;
    }

    public void expandirEnemigo(Estado temp) {
        moverAbajoEnemigo(temp);
        moverArribaEnemigo(temp);
        moverIzquierdaEnemigo(temp);
        moverDerechaEnemigo(temp);
    }

       public synchronized void moverArribaJugador(Estado e) {
        if (nivel_busqueda_Jugador == 1) {
            if (verEspacioActual_Jugador(e.x, e.y - 1)) {
                Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo) * (Math.random() * 6) * -1, 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
            }
        }
        if (nivel_busqueda_Jugador == 2) {
            if (verEspacioActual_Jugador(e.x, e.y - 1)
                && verEspacioFuturo_Jugador(e.x, e.y - 2)) {
                Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
            }
        }
        if (nivel_busqueda_Jugador == 3) {
            if (verEspacioActual_Jugador(e.x, e.y - 1)
                    && verEspacioFuturo_Jugador(e.x, e.y - 2)
                    && verEspacioFuturo_Jugador(e.x - 1, e.y - 1)
                    && verEspacioFuturo_Jugador(e.x + 1, e.y - 1)) {
                Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
            }
        }
    }

    public synchronized void moverAbajoJugador(Estado e) {
        if (nivel_busqueda_Jugador == 1) {
            if (verEspacioActual_Jugador(e.x, e.y + 1)) {
                Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo) * (Math.random() * 6) * -1, 'D', e);
                colaEstados.add(abajo); // busqueda en anchura;
            }
        }
        if (nivel_busqueda_Jugador == 2) {
            if (verEspacioActual_Jugador(e.x, e.y + 1)
                    && verEspacioFuturo_Jugador(e.x, e.y + 2)) {
                Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                colaEstados.add(abajo); // busqueda en anchura;
            }
        }
        if (nivel_busqueda_Jugador == 3) {
            if (verEspacioActual_Jugador(e.x, e.y + 1)
                    && verEspacioFuturo_Jugador(e.x, e.y + 2)
                    && verEspacioFuturo_Jugador(e.x + 1, e.y + 1)
                    && verEspacioFuturo_Jugador(e.x + 1, e.y + 1)) {
                Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                colaEstados.add(abajo); // busqueda en anchura;
            }
        }
    }

    public synchronized void moverIzquierdaJugador(Estado e) {
        if (nivel_busqueda_Jugador == 1) {
            if (verEspacioActual_Jugador(e.x - 1, e.y)) {
                Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo) * (Math.random() * 10)*-1 , 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;
            }
        }
        if (nivel_busqueda_Jugador == 2) {
            if (verEspacioActual_Jugador(e.x - 1, e.y)
                    && verEspacioFuturo_Jugador(e.x - 2, e.y)) {
                Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;
            }
        }

        if (nivel_busqueda_Jugador == 3) {
            if (verEspacioActual_Jugador(e.x - 1, e.y)
                    && verEspacioFuturo_Jugador(e.x - 2, e.y)
                    && verEspacioFuturo_Jugador(e.x - 1, e.y - 1)
                    && verEspacioFuturo_Jugador(e.x - 1, e.y + 1)) {
                Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;
            }
        }
    }

    public synchronized void moverDerechaJugador(Estado e) {
        if (nivel_busqueda_Jugador == 1) {
            if (verEspacioActual_Jugador(e.x + 1, e.y)) {
                Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo) * (Math.random() * 10) , 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
            }
        }
        if (nivel_busqueda_Jugador == 2) {
            if (verEspacioActual_Jugador(e.x + 1, e.y)
                    && verEspacioFuturo_Jugador(e.x + 2, e.y)) {
                Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
            }
        }

        if (nivel_busqueda_Jugador == 3) {
            if (verEspacioActual_Jugador(e.x + 1, e.y)
                    && verEspacioFuturo_Jugador(e.x + 2, e.y)
                    && verEspacioFuturo_Jugador(e.x + 1, e.y - 1)
                    && verEspacioFuturo_Jugador(e.x + 1, e.y + 1)) {
                Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
            }
        }
    }
    //ENEMIGO INICIO
    public synchronized void moverArribaEnemigo(Estado e) {
        if (nivel_busqueda_Enemigo == 1) {
            if (verEspacioActual_Enemigo(e.x, e.y - 1)) {

                if(distanciaJugadorEnemigo(e) >6){
                Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo)* (Math.random() * 5), 'U', e);

                colaEstados.add(arriba); // busqueda en anchura;
                }else{
                    Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo)*Math.random() * -2, 'U', e);

                    colaEstados.add(arriba); // busqueda en anchura;
                }
            }
        }
        if (nivel_busqueda_Enemigo == 2) {
            if (verEspacioActual_Enemigo(e.x, e.y - 1)) {
                if(distanciaJugadorEnemigo(e) >4){
                Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo)* (Math.random() * 2), 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
                }else{
                    Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo)*Math.random() * -2, 'U', e);
                    colaEstados.add(arriba); // busqueda en anchura;
                }
           }
        }
        if (nivel_busqueda_Enemigo == 3) {
            if (verEspacioActual_Enemigo(e.x, e.y - 1)) {
                Estado arriba = new Estado(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
            }
        }
    }

    public synchronized void moverAbajoEnemigo(Estado e) {
        if (nivel_busqueda_Enemigo == 1) {
            if (verEspacioActual_Enemigo(e.x, e.y + 1)) {

                 if(distanciaJugadorEnemigo(e) >6){
                Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo)* (Math.random() * 5), 'D', e);

                colaEstados.add(abajo);
                 }else{
                   Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y +1, objetivo)*Math.random() * -2, 'D', e);
                colaEstados.add(abajo);
                 
                 }

            }
        }
        if (nivel_busqueda_Enemigo == 2) {
            if (verEspacioActual_Enemigo(e.x, e.y + 1)) {
                 if(distanciaJugadorEnemigo(e) >4){
                Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo)* (Math.random() * 2), 'D', e);
                colaEstados.add(abajo);
                 }else{
                   Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y +1, objetivo)*Math.random() * -2, 'D', e);
                colaEstados.add(abajo);
                 
                 }
            }
        }
        if (nivel_busqueda_Enemigo == 3) {
            if (verEspacioActual_Enemigo(e.x, e.y + 1)) {
                Estado abajo = new Estado(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                colaEstados.add(abajo);
            }
        }
    }

    public synchronized void moverIzquierdaEnemigo(Estado e) {
        if (nivel_busqueda_Enemigo == 1) {
            if (verEspacioActual_Enemigo(e.x - 1, e.y)) {

                 if(distanciaJugadorEnemigo(e)>6){
                Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo)* (Math.random() * 5), 'L', e);

                colaEstados.add(izquierda);
                 }else{
                 Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo)*Math.random() * -2, 'L', e);
                colaEstados.add(izquierda);
                 
                 }

            }
        }
        if (nivel_busqueda_Enemigo == 2 ) {
            if (verEspacioActual_Enemigo(e.x - 1, e.y)) {
                 if(distanciaJugadorEnemigo(e)>4){
                Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo)* (Math.random() * 2), 'L', e);
                colaEstados.add(izquierda);
                 }else{
                 Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo)*Math.random() * -2, 'L', e);
                colaEstados.add(izquierda);
                 
                 }
            }
        }
        if (nivel_busqueda_Enemigo == 3) {
            if (verEspacioActual_Enemigo(e.x - 1, e.y)) {
                Estado izquierda = new Estado(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;

            }
        }
    }

    public synchronized void moverDerechaEnemigo(Estado e) {
        if (nivel_busqueda_Enemigo == 1) {
            if (verEspacioActual_Enemigo(e.x + 1, e.y)) {

                 if(distanciaJugadorEnemigo(e)>6){
                  Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo)* (Math.random() * 5), 'R', e);

                  colaEstados.add(derecha);
                 }else{
                    Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x +1 , e.y , objetivo)*Math.random() * -2, 'R', e);

                    colaEstados.add(derecha);
              
                }
            }
        }
        if (nivel_busqueda_Enemigo == 2) {
            if (verEspacioActual_Enemigo(e.x + 1, e.y)) {
                 if(distanciaJugadorEnemigo(e)>4){
                  Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo)* (Math.random() * 2), 'R', e);
                  colaEstados.add(derecha);
                 }else{
                    Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x +1 , e.y , objetivo)*Math.random() * -2, 'R', e);
                    colaEstados.add(derecha);
                 
                 }
            }
        }
        if (nivel_busqueda_Enemigo == 3) {
            if (verEspacioActual_Enemigo(e.x + 1, e.y)) {
                Estado derecha = new Estado(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
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

    public void setInteligenciaEnemigo(int i) {
        this.nivel_busqueda_Enemigo = i;
    }

    public int setInteligenciaEnemigo() {
        return this.nivel_busqueda_Enemigo;
    }

    public void setInteligenciaJugador(int i) {
        this.nivel_busqueda_Jugador = i;
    }

    public int setInteligenciaJugador() {
        return this.nivel_busqueda_Jugador;
    }

    public int distanciaEneAJug() {
        return ((lienzo.getLaberinto().getJugador2X() - lienzo.getLaberinto().getJugadorX()) + (lienzo.getLaberinto().getJugador2Y() - lienzo.getLaberinto().getJugadorY()));
    }
}
