import javax.swing.*;

public class simulatorFrame extends JFrame {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 800;

    public simulatorFrame() {
        super("Particle Simulator");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Panel panel = new Panel();
        panel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(panel);

        setVisible(true);

    }

}