package day_2_dive;

public class Instruction {

    public final Direction direction;
    public final Integer distance;

    public Instruction(Direction direction, Integer distance) {
        this.direction = direction;
        this.distance = distance;
    }
}