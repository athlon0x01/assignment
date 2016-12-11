package my.ciklum.assignment.second;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParticleTest {

    @Test
    public void testMove() throws Exception {
        Particle particle = new Particle(Particle.Direction.RIGHT, 5);
        particle.move(4);
        assertEquals(9, particle.getPosition());
    }

    @Test
    public void testMoveLeft() throws Exception {
        Particle particle = new Particle(Particle.Direction.LEFT, 5);
        particle.move(4);
        assertEquals(1, particle.getPosition());
    }

    @Test
    public void testIsInChamber() throws Exception {
        Particle particle = new Particle(Particle.Direction.LEFT, 3);
        assertTrue(particle.inChamber(5));
    }

    @Test
    public void testOutOfChamber() throws Exception {
        Particle particle = new Particle(Particle.Direction.LEFT, -3);
        assertFalse(particle.inChamber(5));
    }

    @Test
    public void testOutOfChamber2() throws Exception {
        Particle particle = new Particle(Particle.Direction.LEFT, 5);
        assertFalse(particle.inChamber(3));
    }
}