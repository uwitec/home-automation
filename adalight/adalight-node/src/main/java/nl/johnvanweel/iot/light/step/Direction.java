package nl.johnvanweel.iot.light.step;

import java.util.function.Function;

/**
 * Defines direction. Direction is used to increment or decrement depending on the current direction.
 */
public enum Direction {
    LEFT(Direction::decrement), RIGHT(Direction::increment);

    private Function<Integer, Integer> next;

    Direction(Function<Integer, Integer> next) {
        this.next = next;
    }


    private static Integer increment(Integer i) {
        return i + 1;
    }

    private static Integer decrement(Integer i) {
        return i - 1;
    }

    public int getNext(Integer current) {
        return next.apply(current);
    }

    public static Direction change(Direction direction) {
        switch (direction) {
            case LEFT:
                return RIGHT;
            default:
                return LEFT;
        }
    }
}
