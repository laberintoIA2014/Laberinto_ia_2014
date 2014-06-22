package Modelado;

import java.util.ArrayList;
import java.util.TimerTask;

public class AnimadorAutomatico extends TimerTask implements Constantes {

    public Lienzo lienzo;
    public ArrayList<Character> pasos;
    public int paso_actual;
    public boolean role;

    public AnimadorAutomatico(Lienzo lienzo, ArrayList<Character> pasos, boolean role) {
        this.role = role;
        this.lienzo = lienzo;
        this.pasos = pasos;
        paso_actual = pasos.size() - 1;

    }

    @Override
    public void run() {

        if (role) {

            if (paso_actual >= 0) {
                switch (pasos.get(paso_actual)) {
                    case 'D':
                        lienzo.getLaberinto().mover_abajo();
                        this.cancel();
                        break;
                    case 'U':
                        lienzo.getLaberinto().mover_arriba();
                        this.cancel();
                        break;
                    case 'R':
                        lienzo.getLaberinto().mover_derecha();
                        this.cancel();
                        break;
                    case 'L':
                        lienzo.getLaberinto().mover_izquierda();
                        this.cancel();
                        break;
                }

                lienzo.repaint();
                paso_actual--;
            } else {

                this.cancel();
            }

        } else {
            if (paso_actual >= 0) {
                switch (pasos.get(paso_actual)) {
                    case 'D':
                        lienzo.getLaberinto().mover_abajo2();
                        this.cancel();
                        break;
                    case 'U':
                        lienzo.getLaberinto().mover_arriba2();
                        this.cancel();
                        break;
                    case 'R':
                        lienzo.getLaberinto().mover_derecha2();
                        this.cancel();
                        break;
                    case 'L':
                        lienzo.getLaberinto().mover_izquierda2();
                        this.cancel();
                        break;
                }

                lienzo.repaint();
                paso_actual--;
            } else {

                this.cancel();
            }
        }

    }
}
