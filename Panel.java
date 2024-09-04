import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Panel extends JPanel implements MouseListener {
    private simulatorFrame frame;
    private ArrayList<Particle> particles = new ArrayList<>();
    private static int fps = 60;
    private boolean isRunning = true;

    public Panel() {
        this.setBackground(Color.BLACK);
        this.addMouseListener(this);
        this.GameLoop();
    }

    public void update() {
        for (Particle p : particles) {
            p.update();
            for (Particle p2 : particles) {
                if (!p.equals(p2)) {
                    p.checkCollision(p2);
                }
            }
        }
    }

    private void GameLoop() {
        Thread gameLoop = new Thread(() -> {
            while (isRunning) {

                this.update();

                // TODO later on one might add a method like this.scene.checkCollisions in which you check if 2 sprites are interesecting and do something about it
                try {
                    int sleepTimer =1000/fps;
                    Thread.sleep(sleepTimer);
                } catch (InterruptedException ex) {
                }
            }
        });
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Particle particle : particles) {
            g.setColor(Color.WHITE);
//            particle.update();
            g.fillRect((int) particle.getX(), (int) particle.getY(), (int) particle.getRadius(), (int) particle.getRadius());
            g.setColor(Color.RED);
            g.fillOval((int) (particle.getX()), (int) (particle.getY()), (int) particle.getRadius(), (int) particle.getRadius());
            g.setColor(Color.GREEN);
            g.fillRect((int) (particle.getX()+ particle.getRadius()/2), (int) (particle.getY()+particle.getRadius()/2), (int) 5, (int) 5);
        }
        repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Particle particle = new Particle(e.getX(), e.getY(), 100, 1, Math.random()*8-4, Math.random()*8-4);
        particles.add(particle);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
