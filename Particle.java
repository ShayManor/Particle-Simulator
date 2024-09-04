import java.awt.*;

public class Particle {

    private double x;
    private double y;
    private double radius;
    private double mass;
    private double vx;
    private double vy;

    public Particle(double x, double y, double radius, double mass, double vx, double vy) {
        this.x = x - radius/2;
        this.y = y - radius/2;
        this.radius = radius;
        this.mass = mass;
        this.vx = vx;
        this.vy = vy;
        System.out.println("Particle created");
    }

    public void update() {
        x += vx;
        y += vy;
        this.checkWallCollision();
    }

    public void checkCollision(Particle p) {
        double dx = p.x - x;
        double dy = p.y - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= (radius + p.radius)) {
            this.calculateFinalVelocity(p);
        }
    }

    private void checkWallCollision() {
        if (x < 0 || x > simulatorFrame.WINDOW_WIDTH - radius) {
            vx = -vx;
        }
        if (y < 0 || y > simulatorFrame.WINDOW_HEIGHT - radius) {
            vy = -vy;
        }
    }

    private void calculateFinalVelocity(Particle p) {
        // Calculate the centers of the circles
        double thisCenterX = this.x - radius / 2;
        double thisCenterY = this.y - radius / 2;
        double otherCenterX = p.x - p.radius / 2;
        double otherCenterY = p.y - p.radius / 2;

        // Calculate the normal vector based on the centers
        double dx = otherCenterX - thisCenterX;
        double dy = otherCenterY - thisCenterY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Normalize the distance vector (normal vector)
        double nx = dx / distance;
        double ny = dy / distance;

        // Calculate the relative velocity
        double dvx = this.vx - p.vx;
        double dvy = this.vy - p.vy;

        // Calculate velocity along the normal
        double velocityAlongNormal = dvx * nx + dvy * ny;

        // Do not resolve if velocities are separating
        if (velocityAlongNormal > 0) {
            return;
        }

        // Calculate the restitution (elastic collision coefficient)
        double restitution = 1.0;  // Perfectly elastic collision

        // Calculate the impulse scalar
        double impulse = (-(1 + restitution) * velocityAlongNormal) / (1 / this.mass + 1 / p.mass);

        // Apply impulse to each particle's velocity
        double impulseX = impulse * nx;
        double impulseY = impulse * ny;

        this.vx -= impulseX / this.mass;
        this.vy -= impulseY / this.mass;

        p.vx += impulseX / p.mass;
        p.vy += impulseY / p.mass;

        // Correct any potential overlapping after collision
        double overlap = 0.5 * (this.radius + p.radius - distance + 0.01); // Adjust overlap slightly to prevent repeated collision
        this.x -= overlap * nx;
        this.y -= overlap * ny;
        p.x += overlap * nx;
        p.y += overlap * ny;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }


}
