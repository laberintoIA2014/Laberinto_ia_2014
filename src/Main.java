
import Modelado.VentanaPrincipal;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setVisible(true);
        vp.setResizable(false);
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
