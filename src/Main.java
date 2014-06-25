
import Modelado.VentanaPrincipal;
import static Modelado.VentanaPrincipal.lienzo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setVisible(true);
        vp.setResizable(false);
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(true){
        lienzo.repaint();
        }
    }

}
