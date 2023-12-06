package advent2023.problems.day3;

import java.util.List;
import java.util.Set;

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
        int numLength = 0;

        for (int row = 0; row < engineSchematic.size(); row++) {
            for (int col = 0; col < engineSchematic.get(row).length(); col++) {
                char character = engineSchematic.get(row).charAt(col);
                if (character >= '0' && character <= '9') {
                    numLength++;
                    currentNum *= 10;
                    currentNum += character - '0';
                    currentNumIsValid |= isValidPartNumber(row, col, engineSchematic);
                } else {
                    if (currentNumIsValid) {
                        // if (col - numLength - 1 >= 0 && engineSchematic.get(row).charAt(col - numLength - 1) == '-')
                        //     currentNum *= -1;
                
                        System.out.println(currentNum);
                            total += currentNum;
                    }
                    numLength = 0;
                    currentNum = 0;
                    currentNumIsValid = false;
                }
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

}
