package day_3_binary_diagnostic;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.InputReader;

public class BinaryDiagnostic {
    public static void main(String[] args) {
        part1();
    }

    public static void part1() {
        List<String> input = getInput();
        List<Integer> countOf1AtEachPosition = getCountof1AtEachPosition(input);

        Integer rowLength = input.size();

        System.out.print("obj");
    }

    private static Integer getGammaRate(List<Integer> countOf1AtEachPosition, Integer totalRows) {
        List<Integer> binaryGamma = countOf1AtEachPosition.stream()
                                                          .map(i -> {
                                                              if(i < totalRows/2) {
                                                                  return Integer.valueOf(0);
                                                              } else {
                                                                  return Integer.valueOf(1);
                                                              }
                                                            })
                                                          .collect(Collectors.toList());
        return 0;
    }

    private static void getEpsilonRate(List<Integer> countOf1AtEachPosition, Integer totalRows) {

    }

    private static List<String> getInput() {
        return InputReader.readLinesFromFile(Path.of("/home/suhas/advent-of-code-2021/day_3_binary_diagnostic/input.txt"))
                          .collect(Collectors.toList());
    }

    private static List<Integer> getCountof1AtEachPosition(List<String> input) {
        Integer columnLength = input.stream().findFirst().get().length();

        List<Integer> identity = Collections.nCopies(columnLength, 0);
        List<Integer> countOf1AtEachPosition = input.stream().map(i -> splitIntoIntegerList(i))
                                                    .reduce(identity, (subtotal, element) -> sum(subtotal, element));
        return countOf1AtEachPosition;
    }

    private static List<Integer> splitIntoIntegerList(String input) {
        return input.chars()
                    .mapToObj(i -> (char)i)
                    .map(Character::getNumericValue)
                    .collect(Collectors.toList());
    }

    private static List<Integer> sum(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            throw new RuntimeException("List size are not equal. Check input or initial list");
        }

        List<Integer> result = new ArrayList<>();
        for(int i=0; i < list1.size(); i++) {
            result.add(list1.get(i) + list2.get(i));
        }

        return result;
    }
}
