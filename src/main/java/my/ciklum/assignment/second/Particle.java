package my.ciklum.assignment.second;

/**
 * Particle model for the {@link Chamber}
 *
 * @author Anton Borovyk
 */
public class Particle {

    public enum Direction {
        LEFT(-1),
        RIGHT(1);

        private int sign;

        private Direction(int sign) {
            this.sign = sign;
        }

        public int getSign() {
            return sign;
        }
    }

    private final Direction direction;
    private int position;

    public Particle(Direction direction, int position) {
        this.direction = direction;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    /**
     * Moves particle in the chamber
     * @param speed of moving
     */
    public void move(int speed) {
        position += direction.getSign() * speed;
    }

    /**
     * Checks if the particle is in the chamber
     * @param size of the chamber
     * @return result of the check
     */
    public boolean inChamber(int size) {
        return position >= 0 && position < size;
    }
}
