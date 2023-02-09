package random;

public class InitMotion {
    private final double from;
    private final double to;
    private double current;
    private double step;

    public InitMotion(double from, double to, double step) {
        if (step >= Math.abs(to - from) || to == from)
            throw new IllegalArgumentException("Invalid arguments");
        this.from = from;
        this.to = to;
        this.step = step;
        this.current = from - step;
    }

    public synchronized double next() {
        if (from < to) {
            // if next step is over or under the range
            if (current + step > to || current + step < from)
                step = -step;
        }
        else if (from > to) {
            // if next step under or over the range (negative)
            if (current + step < to || current + step > from)
                step = -step;
        }    
        return current += step;
    }
}