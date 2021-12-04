package day_2_dive;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import utils.InputReader;

public class Dive {
    public static void main (String args[]) {
        // part1();
        part2();
    }

    private static void part2() {
        List<Instruction> instructions = readAndFormatInput();

        Integer depth = 0;
        Integer horizontalPosition = 0;
        Integer aim = 0;

        for (Instruction instruction: instructions) {
            Integer distanceMoved = instruction.distance;
            switch(instruction.direction) {
                case FORWARD: 
                    horizontalPosition += distanceMoved;
                    depth += aim * distanceMoved;
                    break;
                case DOWN: 
                    aim += distanceMoved;
                    break;
                case UP:
                    aim -= distanceMoved;
                    break;
            }
        }

        System.out.printf("depth * horizontalPosition = %d", depth * horizontalPosition);
    }

    private static void part1() {
        List<Instruction> instructions = readAndFormatInput();

        Integer depth = 0;
        Integer horizontalPosition = 0;

        for (Instruction instruction: instructions) {
            Integer distanceMoved = instruction.distance;
            switch(instruction.direction) {
                case FORWARD: horizontalPosition += distanceMoved; break;
                case DOWN: depth += distanceMoved; break;
                case UP: depth -= distanceMoved; break;
            }
        }

        Integer result = depth * horizontalPosition;
        System.out.printf("depth * horizontalPosition = %d", result);
    }

    public static List<Instruction> readAndFormatInput() {
        Stream<String> rawInput = InputReader.readLinesFromFile(Path.of("/home/suhas/advent-of-code-2021/day_2_dive/input.txt"));
        List<Instruction> instructions = rawInput
                            .map(s -> s.split(" "))
                            .map(x -> new Instruction(Direction.fromValue(x[0]), Integer.parseInt(x[1])))
                            .collect(Collectors.toList());
        return instructions;
    }
}
