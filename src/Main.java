
import Modelado.VentanaPrincipal;
import Sounds.Reproductor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {

    public static void main(String[] args) throws Exception {
        VentanaPrincipal vp = new VentanaPrincipal();

        //Menu Variables
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        //Menu 1
        menuBar = new JMenuBar();
        menu = new JMenu("Opciones");

        menuBar.add(menu);

        //Grupo
        menuItem = new JMenuItem("Opcion 1");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        menu.add(menuItem);
        menuItem = new JMenuItem("Opcion 2");
        menu.add(menuItem);

        //Separador grupo
        menu.addSeparator();

        menuItem = new JMenuItem("Salir");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        menu.add(menuItem);

        //Menu 2
        menu = new JMenu("Info");
        menuBar.add(menu);
        
        Reproductor musica_de_vivo = new Reproductor();
        musica_de_vivo.AbrirFichero("src/Sounds/music.ogg");
        musica_de_vivo.Play();
        
        vp.setJMenuBar(menuBar);
        vp.setVisible(true);
        vp.setResizable(false);
        vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vp.startThread();
      
    

}

}
