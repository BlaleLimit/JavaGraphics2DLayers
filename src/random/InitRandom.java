package random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InitRandom {
    private final double min;
    private final double max;
    double rand;
    double velocity;
    int velocityInt;

    public InitRandom(double min, double max) {
        if (min > max)
            throw new IllegalArgumentException("Invalid arguments");
        this.min = min;
        this.max = max;
    }

    public synchronized double randomVelocity() {
        rand = new Random().nextDouble();
        velocity = ((max - min) * rand) + min; // Math.random() * (max - min + 1) + min
        // retry if velocity is too small
        while ((velocity > -0.7 && velocity < 0) || (velocity > 0 && velocity < 0.7)) {
            rand = new Random().nextDouble();
            velocity = ((max - min) * rand) + min;
        }
        return velocity;
    }

    public synchronized int randomTheta() {
        velocityInt = ThreadLocalRandom.current().nextInt((int) min, (int) (max + 1));
        // retry if velocity is == 0
        while (velocityInt == 0)
            velocityInt = ThreadLocalRandom.current().nextInt((int) min, (int) (max + 1));
        return velocityInt;
    }

    public synchronized int randomPosition() {
        return ThreadLocalRandom.current().nextInt((int) min, (int) (max + 1));
    }
}
