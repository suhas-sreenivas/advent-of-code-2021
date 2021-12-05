package day_3_binary_diagnostic;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import utils.InputReader;

public class BinaryDiagnostic {
    public static void main(String[] args) {
        part1();
        part2();
    }

    public static void part2() {
        List<String> input = getInput();

        Integer oxygenGeneratorRating = getOxygenGeneratorRating(input);
        Integer CO2ScrubberRating = getCO2ScrubberRating(input);

        System.out.printf("oxygen generator rating * CO2 scrubber rating = %d", oxygenGeneratorRating * CO2ScrubberRating);
    }


    public static void part1() {
        List<String> input = getInput();
        List<Integer> countOf1AtEachPosition = getCountof1AtEachPosition(input);
        Integer totalRows = input.size();

        Integer gammaRate = convertFromBinary(getGammaRate(countOf1AtEachPosition, totalRows));
        Integer epsilonRate = convertFromBinary(getEpsilonRate(countOf1AtEachPosition, totalRows));

        System.out.printf("Gamma rate * Epsilon rate = %d", gammaRate * epsilonRate);
    }

    private static Integer getOxygenGeneratorRating(List<String> input) {
        List<String> result = new ArrayList<>(input);

        Integer columnLength = input.get(0).length();

        for(int i = 0; i < columnLength; i++) {
            List<String> intermediateResult = new ArrayList<>();

            char mostCommonBitInColumnI = getGammaRate(getCountof1AtEachPosition(result), result.size()).charAt(i);
            // System.out.println(getGammaRate(getCountof1AtEachPosition(result), input.size()));
            for (String x: result) {
                if (x.charAt(i) == mostCommonBitInColumnI) {
                    intermediateResult.add(x);
                }
            }
            
            result = intermediateResult;

            if (result.size() == 1) {
                return convertFromBinary(result.get(0));
            }

        }

        throw new RuntimeException("No match found");
    }

    private static Integer getCO2ScrubberRating(List<String> input) {
        List<String> result = new ArrayList<>(input);

        Integer columnLength = input.get(0).length();

        for(int i = 0; i < columnLength; i++) {
            List<String> intermediateResult = new ArrayList<>();

            Character leastCommonBitInColumnI = getEpsilonRate(getCountof1AtEachPosition(result), result.size()).charAt(i);
            
            for (String x: result) {
                if (x.charAt(i) == leastCommonBitInColumnI) {
                    intermediateResult.add(x);
                }
            }
            
            result = intermediateResult;

            if (result.size() == 1) {
                return convertFromBinary(result.get(0));
            }

        }

        throw new RuntimeException("No match found");
    }

    private static Integer convertFromBinary(String binaryString) {
        return Integer.parseInt(binaryString, 2);
    }

    private static String getGammaRate(List<Integer> countOf1AtEachPosition, Integer totalRows) {
        String binaryGamma = countOf1AtEachPosition.stream()
                                                          .map(i -> {
                                                              if(i >= (totalRows-i)) {
                                                                  return Integer.valueOf(1);
                                                              } else {
                                                                  return Integer.valueOf(0);
                                                              }
                                                            })
                                                            .map(i -> String.valueOf(i))
                                                          .collect(Collectors.joining());
        return binaryGamma;
    }

    private static String getEpsilonRate(List<Integer> countOf1AtEachPosition, Integer totalRows) {
        String binaryEpsilon = countOf1AtEachPosition.stream()
                                                          .map(i -> {
                                                              if(i >= (totalRows-i)) {
                                                                  return Integer.valueOf(0);
                                                              } else {
                                                                  return Integer.valueOf(1);
                                                              }
                                                            })
                                                            .map(i -> String.valueOf(i))
                                                          .collect(Collectors.joining());
        return binaryEpsilon;
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
