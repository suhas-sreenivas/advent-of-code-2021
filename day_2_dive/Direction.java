package day_2_dive;

enum Direction {
    FORWARD("forward"),
    UP("up"),
    DOWN("down");

    private final String value;

    private Direction(String value) {
        this.value = value;
    }

    public static Direction fromValue(String value) {
        return Direction.valueOf(value.toUpperCase());
    }
}