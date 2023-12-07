package advent2023.problems.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import advent2023.problems.Puzzle;
import advent2023.util.Pair;

public class Day3 extends Puzzle {

    private Set<Pair<Integer>> offsets = Set.of(
            new Pair<Integer>(-1, -1), new Pair<Integer>(-1, 0), new Pair<Integer>(-1, 1),
            new Pair<Integer>(0, -1), new Pair<Integer>(0, 1),
            new Pair<Integer>(1, -1), new Pair<Integer>(1, 0), new Pair<Integer>(1, 1));

    public Day3() {
        super(3);
    }

    @Override
    public Object part1() {
        return sumPartNumbers(input);
    }

    @Override
    public Object part2() {
        return null;
    }

    private int sumPartNumbers(List<String> engineSchematic) {
        int currentNum, total;
        currentNum = total = 0;
        boolean currentNumIsValid = false;

        for (int row = 0; row < engineSchematic.size(); row++) {
            for (int col = 0; col < engineSchematic.get(row).length(); col++) {
                char character = engineSchematic.get(row).charAt(col);
                if (character >= '0' && character <= '9') {
                    currentNum *= 10;
                    currentNum += character - '0';
                    currentNumIsValid |= isValidPartNumber(row, col, engineSchematic);

                    // handle end of line to avoid parsing two part numbers as one
                    if (col != engineSchematic.get(row).length() - 1) {
                        continue;
                    }
                }

                if (currentNumIsValid) {
                    total += currentNum;
                }
                currentNum = 0;
                currentNumIsValid = false;
            }
        }
        return total;
    }

    private boolean isValidPartNumber(int row, int col, List<String> engineSchematic) {
        return offsets.stream()
                .anyMatch((offset) -> {
                    int neighborRow = row + offset.left();
                    int neighborCol = col + offset.right();

                    // verify neighbor is in bounds
                    if (neighborRow < 0 || neighborRow >= engineSchematic.size() || neighborCol < 0
                            || neighborCol >= engineSchematic.get(neighborRow).length()) {
                        return false;
                    }

                    char neighbor = engineSchematic.get(neighborRow).charAt(neighborCol);

                    return neighbor != '.' && (neighbor < '0' || neighbor > '9');
                });
    }

    private int sumGearPairs(List<String> engineSchematic) {
        Map<Pair<Integer>, List<Integer>> gearsByPartNumbers = new HashMap<>();

        int currentNum = 0;
        boolean isNearGear = false;
        Set<Pair<Integer>> surroundingGears = new HashSet<>();

        for (int row = 0; row < engineSchematic.size(); row++) {
            for (int col = 0; col < engineSchematic.get(row).length(); col++) {
                char character = engineSchematic.get(row).charAt(col);
                if (character >= '0' && character <= '9') {
                    currentNum *= 10;
                    currentNum += character - '0';
                    surroundingGears.addAll(getSurroundingGears(row, col, engineSchematic));
                    isNearGear |= surroundingGears.size() > 0;

                    // handle end of line to avoid parsing two part numbers as one
                    if (col != engineSchematic.get(row).length() - 1) {
                        continue;
                    }
                }

                if (isNearGear) {
                    surroundingGears.stream()
                            .forEach((gearLocation) -> {
                                List<Integer> partNumbers = Optional.ofNullable(gearsByPartNumbers.get(gearLocation))
                                    .map((partsList) -> partsList.add(currentNum) )
                                        .orElse(new ArrayList<Integer>());
                                partNumbers.add(currentNum);
                                gearsByPartNumbers.put(gearLocation, partNumbers);
                            });
                }
                currentNum = 0;
                isNearGear = false;
                
            }
        }

        return gearsByPartNumbers.values().stream()
                .filter((partsList) -> partsList.size() == 2)
                .map((partsList) -> partsList.get(0) * partsList.get(1))
                .collect(Collectors.summingInt(Integer::valueOf));
    }

    private Set<Pair<Integer>> getSurroundingGears(int row, int col, List<String> engineSchematic) {
        return offsets.stream()
                .filter((offset) -> {
                    int neighborRow = row + offset.left();
                    int neighborCol = col + offset.right();

                    // verify neighbor is in bounds
                    return neighborRow < 0 || neighborRow >= engineSchematic.size() || neighborCol < 0
                            || neighborCol >= engineSchematic.get(neighborRow).length();
                })
                .filter((offset) -> {
                    char neighbor = engineSchematic.get(row + offset.left()).charAt(col + offset.right());
                    return neighbor == '*';
                })
                .map((offset) -> new Pair<Integer>(row + offset.left(), col + offset.right()))
                .collect(Collectors.toSet());
    }
}
