package advent2023.problems.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import advent2023.problems.Puzzle;
import advent2023.util.Pair;

public class Day3 extends Puzzle {

    private static final Set<Pair<Integer>> offsets = Set.of(
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
        return sumGearPairs(input);
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
        Set<Pair<Integer>> surroundingGears = new HashSet<>();
        int currentNum = 0;

        for (int row = 0; row < engineSchematic.size(); row++) {
            for (int col = 0; col < engineSchematic.get(row).length(); col++) {
                char character = engineSchematic.get(row).charAt(col);
                if (character >= '0' && character <= '9') {
                    currentNum *= 10;
                    currentNum += character - '0';
                    surroundingGears.addAll(getSurroundingGears(row, col, engineSchematic));

                    // handle end of line to avoid parsing two part numbers as one
                    if (col != engineSchematic.get(row).length() - 1) {
                        continue;
                    }
                }

                if (surroundingGears.size() > 0) {
                    for (Pair<Integer> gearLocation : surroundingGears) {
                        List<Integer> partsList = new ArrayList<Integer>();
                        partsList.add(currentNum);
                        gearsByPartNumbers.merge(gearLocation, partsList, (oldList, newList) -> {
                            oldList.addAll(newList);
                            return oldList;
                        });

                    }
                    surroundingGears = new HashSet<Pair<Integer>>();
                }
                currentNum = 0;
            }
        }

        return gearsByPartNumbers.values().stream()
                .filter((partsList) -> partsList.size() == 2)
                .map((partsList) -> partsList.get(0) * partsList.get(1))
                .collect(Collectors.summingInt(Integer::valueOf));
    }

    private Set<Pair<Integer>> getSurroundingGears(int row, int col, List<String> engineSchematic) {
        return offsets.stream()
                .map((offset) -> new Pair<Integer>(row + offset.left(), col + offset.right()))
                .filter((neighborLocation) -> neighborLocation.left() >= 0 && neighborLocation.right() >= 0
                        && neighborLocation.left() < engineSchematic.size()
                        && neighborLocation.right() < engineSchematic.get(neighborLocation.left()).length())
                .filter((neighborLocation) -> engineSchematic.get(neighborLocation.left())
                        .charAt(neighborLocation.right()) == '*')
                .map((offset) -> {
                    return new Pair<Integer>(offset.left(), offset.right());
                })
                .collect(Collectors.toSet());
    }
}

/**
 * --- Day 3: Gear Ratios ---
 * 
 * You and the Elf eventually reach a gondola lift station; he says the gondola
 * lift will take you up to the water source, but this is as far as he can bring
 * you. You go inside.
 * 
 * It doesn't take long to find the gondolas, but there seems to be a problem:
 * they're not moving.
 * 
 * "Aaah!"
 * 
 * You turn around to see a slightly-greasy Elf with a wrench and a look of
 * surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working
 * right now; it'll still be a while before I can fix it." You offer to help.
 * 
 * The engineer explains that an engine part seems to be missing from the
 * engine, but nobody can figure out which one. If you can add up all the part
 * numbers in the engine schematic, it should be easy to work out which part is
 * missing.
 * 
 * The engine schematic (your puzzle input) consists of a visual representation
 * of the engine. There are lots of numbers and symbols you don't really
 * understand, but apparently any number adjacent to a symbol, even diagonally,
 * is a "part number" and should be included in your sum. (Periods (.) do not
 * count as a symbol.)
 * 
 * Here is an example engine schematic:
 * 
 * 467..114..
 * ...*......
 * ..35..633.
 * ......#...
 * 617*......
 * .....+.58.
 * ..592.....
 * ......755.
 * ...$.*....
 * .664.598..
 * 
 * In this schematic, two numbers are not part numbers because they are not
 * adjacent to a symbol: 114 (top right) and 58 (middle right). Every other
 * number is adjacent to a symbol and so is a part number; their sum is 4361.
 * 
 * Of course, the actual engine schematic is much larger. What is the sum of all
 * of the part numbers in the engine schematic?
 * 
 * Your puzzle answer was 535351.
 * --- Part Two ---
 * 
 * The engineer finds the missing part and installs it in the engine! As the
 * engine springs to life, you jump in the closest gondola, finally ready to
 * ascend to the water source.
 * 
 * You don't seem to be going very fast, though. Maybe something is still wrong?
 * Fortunately, the gondola has a phone labeled "help", so you pick it up and
 * the engineer answers.
 * 
 * Before you can explain the situation, she suggests that you look out the
 * window. There stands the engineer, holding a phone in one hand and waving
 * with the other. You're going so slowly that you haven't even left the
 * station. You exit the gondola.
 * 
 * The missing part wasn't the only issue - one of the gears in the engine is
 * wrong. A gear is any * symbol that is adjacent to exactly two part numbers.
 * Its gear ratio is the result of multiplying those two numbers together.
 * 
 * This time, you need to find the gear ratio of every gear and add them all up
 * so that the engineer can figure out which gear needs to be replaced.
 * 
 * Consider the same engine schematic again:
 * 
 * 467..114..
 * ...*......
 * ..35..633.
 * ......#...
 * 617*......
 * .....+.58.
 * ..592.....
 * ......755.
 * ...$.*....
 * .664.598..
 * 
 * In this schematic, there are two gears. The first is in the top left; it has
 * part numbers 467 and 35, so its gear ratio is 16345. The second gear is in
 * the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a
 * gear because it is only adjacent to one part number.) Adding up all of the
 * gear ratios produces 467835.
 * 
 * What is the sum of all of the gear ratios in your engine schematic?
 * 
 * Your puzzle answer was 87287096.
 */
