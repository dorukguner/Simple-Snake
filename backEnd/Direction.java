package backEnd;

public enum Direction {

    /**
     * Enum for Direction, used to make things clearer instead of passing ints around for direction
     */

    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);

    private int direction;

    Direction(int direction) {
        this.direction = direction;
    }
}
