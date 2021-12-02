package day_1_sonar_sweep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SonarSweep {

    public static void main(String[] args) throws IOException {
        part_1(getInput());
        part_2();
    }

    private static void part_1(List<Integer> input) throws IOException{
        Integer times_depth_increased = 0;
        for(int i = 0; i < input.size() - 1; i ++) {
            Integer current = input.get(i);
            Integer next = input.get(i+1);
            if (next > current) {
                times_depth_increased += 1;
            }
        }
        System.out.printf("the number of times a depth measurement increased is %d", times_depth_increased);
    }

    private static void part_2() throws IOException{
        part_1(get3MeasurementWindowSums());
    }


    private static List<Integer> getInput() throws IOException{
        List<Integer> lines = Files.lines(Path.of("/home/suhas/advent-of-code-2021/day_1_sonar_sweep/input.txt")).map(Integer::parseInt).collect(Collectors.toList());
        return lines;
    }

    private static List<Integer> get3MeasurementWindowSums() throws IOException{
        List<Integer> originalInput = getInput();
        List<Integer> result = new ArrayList<>();
        Integer window_start = 0;
        Integer window_end = 2;
        while (window_end <= originalInput.size()-1) {
            result.add(originalInput.get(window_start) + originalInput.get(window_start+1) + originalInput.get(window_start+2));
            window_start += 1;
            window_end += 1;
        }
        return result;
    }

}