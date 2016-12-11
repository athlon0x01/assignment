package my.ciklum.assignment.second;

import java.util.ArrayList;
import java.util.List;

/**
 * Chamber model for {@link Animation}
 *
 * @author Anton Borovyk
 */
public class Chamber {

    private final int size;
    private final int speed;
    private final List<Particle> particles;
    private final String emptyChamber;

    public Chamber(int speed, String init) {
        if (speed < 1 || speed > 10) {
            throw new IllegalArgumentException("Wrong speed value - " + speed + "  has to be between 1 and 10 inclusive");
        }
        if (init == null || init.isEmpty() || init.length() > 50) {
            throw new IllegalArgumentException("Wrong init length - " + speed + " has to be between 1 and 50 characters inclusive");
        }
        this.speed = speed;
        this.size = init.length();
        this.particles = new ArrayList<>();
        final StringBuilder builder = new StringBuilder(size);
        //parse init string
        for (int i = 0; i < init.length(); i++) {
            switch (init.charAt(i)) {
                case 'L': particles.add(new Particle(Particle.Direction.LEFT, i));
                    break;
                case 'R': particles.add(new Particle(Particle.Direction.RIGHT, i));
                    break;
                case '.': break;
                default: throw new IllegalArgumentException("Wrong character found - " + init.charAt(i));
            }
            builder.append('.');
        }
        this.emptyChamber = builder.toString();
    }

    /**
     * @return string that shows particles in the chamber
     */
    public String print() {
        final StringBuilder chamber = new StringBuilder(emptyChamber);
        particles.stream()
                .filter(p -> p.inChamber(size))
                .forEach(p -> chamber.setCharAt(p.getPosition(), 'X'));
        return chamber.toString();
    }

    /**
     * Check if there is any particle in chamber
     * @return result of checking
     */
    public boolean isEmpty() {
        return !particles.stream()
                .filter(p -> p.inChamber(size))
                .findAny().isPresent();
    }

    /**
     * Move particles in the chamber
     */
    public void moveParticles() {
        particles.forEach(p -> p.move(speed));
    }
}
