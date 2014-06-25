package Busqueda;

import Modelado.Constantes;
import Modelado.Lienzo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Busqueda_panxo implements Constantes {

    public Lienzo lienzo;
    public PriorityQueue<Estado3> colaEstados;
    public ArrayList<Estado3> historial;
    public ArrayList<Character> pasos;
    public ArrayList<Point> premios;
    public int index_pasos;
    public int nivel_busqueda_Jugador=1;
    public int nivel_busqueda_Enemigo=2;
    public Estado3 inicial;
    public Estado3 objetivo;
    public Estado3 temp;
    public boolean exito, tipoBusqueda, role;

    public Busqueda_panxo(Lienzo lienzo) {
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
    
    public boolean verEspacioActual_Jugador(int x, int y){
       if(x>=0 && y >=0 && x<16 && y<16){
        return  lienzo.getLaberinto().getCasillas()[x][y].tipo != 'P' &&
                lienzo.getLaberinto().getCasillas()[x][y].tipo != 'H';
        }
        return false;
    }
    
    public boolean verEspacioFuturo_Jugador(int x, int y){
        if(x>=0 && y >=0 && x<16 && y<16){
        return  lienzo.getLaberinto().getCasillas()[x][y].tipo != 'H';
        }
        return false;
    }
    
    public boolean verEspacioActual_Enemigo(int x, int y){
        if(x>=0 && y >=0 && x<16 && y<16){
            return lienzo.getLaberinto().getCasillas()[x][y].tipo != 'P' && 
                   lienzo.getLaberinto().getCasillas()[x][y].tipo != 'F' && 
                   lienzo.getLaberinto().getCasillas()[x][y].tipo != 'M';
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
        if(nivel_busqueda_Jugador==1){      
            if (verEspacioActual_Jugador(e.x,e.y-1)) {
                Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo)*(Math.random()*2)*-1, 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
                                        
            }
      }
        
        if(nivel_busqueda_Jugador==2){      
            if (verEspacioActual_Jugador(e.x,e.y-1)&&  
                verEspacioFuturo_Jugador(e.x,e.y-2)) {
                Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
                                        
            }
      }
        
        if(nivel_busqueda_Jugador==3){      
        if (verEspacioActual_Jugador(e.x,e.y-1)   &&  
                verEspacioFuturo_Jugador(e.x,e.y-2)   && 
                verEspacioFuturo_Jugador(e.x-1,e.y-1) && 
                verEspacioFuturo_Jugador(e.x+1,e.y-1)){
                Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
                colaEstados.add(arriba); // busqueda en anchura;
                                        
            }
      }                     
                 
     }

    public synchronized void moverAbajoJugador(Estado3 e) {
        if(nivel_busqueda_Jugador==1){ 
            if (verEspacioActual_Jugador(e.x,e.y+1)){
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo)*(Math.random()*2)*-1, 'D', e);
                colaEstados.add(abajo); // busqueda en anchura;
                                        
              }
          }
        
        if(nivel_busqueda_Jugador==2){ 
            if (verEspacioActual_Jugador(e.x,e.y+1) && 
                verEspacioFuturo_Jugador(e.x,e.y+2)){
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                colaEstados.add(abajo); // busqueda en anchura;
                                        
              }
          }
        
        if(nivel_busqueda_Jugador==3){ 
            if (verEspacioActual_Jugador(e.x,e.y+1) && 
                verEspacioFuturo_Jugador(e.x,e.y+2) &&
                verEspacioFuturo_Jugador(e.x+1,e.y+1)&&  
                verEspacioFuturo_Jugador(e.x+1,e.y+1)){
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                colaEstados.add(abajo); // busqueda en anchura;
                                        
              }
          } 
      }
           

    public synchronized void moverIzquierdaJugador(Estado3 e) {
        if(nivel_busqueda_Jugador==1){ 
            if (verEspacioActual_Jugador(e.x-1,e.y)){
                Estado3 izquierda = new Estado3(e.x-1, e.y , e.setF(e.x-1, e.y, objetivo)*(Math.random()*2)*-1, 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;

             }  
        }
        if(nivel_busqueda_Jugador==2){ 
            if (verEspacioActual_Jugador(e.x-1,e.y) &&
                verEspacioFuturo_Jugador(e.x-2,e.y)) {
                Estado3 izquierda = new Estado3(e.x-1, e.y , e.setF(e.x-1, e.y, objetivo), 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;

             }  
        }
        
        if(nivel_busqueda_Jugador==3){ 
            if (verEspacioActual_Jugador(e.x-1,e.y) &&
                verEspacioFuturo_Jugador(e.x-2,e.y) &&
                verEspacioFuturo_Jugador(e.x-1,e.y-1) &&
                verEspacioFuturo_Jugador(e.x-1,e.y+1)) {
                Estado3 izquierda = new Estado3(e.x-1, e.y , e.setF(e.x-1, e.y, objetivo), 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;

             }  
        }     
    }

    public synchronized void moverDerechaJugador(Estado3 e) {
        if(nivel_busqueda_Jugador==1){  
            if (verEspacioActual_Jugador(e.x+1,e.y)){
                Estado3 derecha = new Estado3(e.x+1, e.y , e.setF(e.x+1, e.y, objetivo)*(Math.random()*2)*-1, 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
            }                                 
        } 
        if(nivel_busqueda_Jugador==2){  
            if (verEspacioActual_Jugador(e.x+1,e.y) && 
                verEspacioFuturo_Jugador(e.x+2,e.y)) {
                Estado3 derecha = new Estado3(e.x+1, e.y , e.setF(e.x+1, e.y, objetivo), 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
            }                                 
        } 
        
       if(nivel_busqueda_Jugador==3){  
        if (verEspacioActual_Jugador(e.x+1,e.y) && 
            verEspacioFuturo_Jugador(e.x+2,e.y) && 
            verEspacioFuturo_Jugador(e.x+1,e.y-1)&& 
            verEspacioFuturo_Jugador(e.x+1,e.y+1)) {
            Estado3 derecha = new Estado3(e.x+1, e.y , e.setF(e.x+1, e.y, objetivo), 'R', e);
            colaEstados.add(derecha); // busqueda en anchura;
                                        
        }                              }  
     
    }

    public synchronized void moverArribaEnemigo(Estado3 e) {
         if(nivel_busqueda_Enemigo==2){  
           if (verEspacioActual_Enemigo(e.x,e.y-1)){
               Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo)*Math.random()*-1, 'U', e);
               colaEstados.add(arriba); // busqueda en anchura;
           }
         }
         if(nivel_busqueda_Enemigo==3){  
           if (verEspacioActual_Enemigo(e.x,e.y-1)){
               Estado3 arriba = new Estado3(e.x, e.y - 1, e.setF(e.x, e.y - 1, objetivo), 'U', e);
               colaEstados.add(arriba); // busqueda en anchura;
           }
         }
     }

    public synchronized void moverAbajoEnemigo(Estado3 e) {
        if(nivel_busqueda_Enemigo==2){  
            if (verEspacioActual_Enemigo(e.x,e.y+1)){
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo)*Math.random()*2, 'D', e);
                colaEstados.add(abajo);
            }
        }
        if(nivel_busqueda_Enemigo==3){  
            if (verEspacioActual_Enemigo(e.x,e.y+1)){
                Estado3 abajo = new Estado3(e.x, e.y + 1, e.setF(e.x, e.y + 1, objetivo), 'D', e);
                colaEstados.add(abajo);
            }
        }     
    }

    public synchronized void moverIzquierdaEnemigo(Estado3 e) {
        if(nivel_busqueda_Enemigo==2){  
            if (verEspacioActual_Enemigo(e.x-1,e.y)){
                Estado3 izquierda = new Estado3(e.x-1, e.y , e.setF(e.x-1, e.y , objetivo)*Math.random()*-1, 'L', e);
                colaEstados.add(izquierda);
            }
        }
      if(nivel_busqueda_Enemigo==3){    
        if (verEspacioActual_Enemigo(e.x-1,e.y)){
                Estado3 izquierda = new Estado3(e.x - 1, e.y, e.setF(e.x - 1, e.y, objetivo), 'L', e);
                colaEstados.add(izquierda); // busqueda en anchura;

         }
      }  
    }

    public synchronized void moverDerechaEnemigo(Estado3 e) {
        if(nivel_busqueda_Enemigo==2){    
        if (verEspacioActual_Enemigo(e.x+1,e.y)){
                Estado3 derecha = new Estado3(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo)*Math.random()*2, 'R', e);
                colaEstados.add(derecha); // busqueda en anchura;
         }
     } 
        if(nivel_busqueda_Enemigo==3){    
           if (verEspacioActual_Enemigo(e.x+1,e.y)){
                   Estado3 derecha = new Estado3(e.x + 1, e.y, e.setF(e.x + 1, e.y, objetivo), 'R', e);
                   colaEstados.add(derecha); // busqueda en anchura;
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