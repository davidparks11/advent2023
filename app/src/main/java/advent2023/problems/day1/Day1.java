package advent2023.problems.day1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import advent2023.problems.Puzzle;

import static java.util.Map.entry;

public class Day1 extends Puzzle {

    private final static Map<String, Integer> VALUE_BY_CALIBRATION = Map.ofEntries(
            entry("zero", 0),
            entry("one", 1),
            entry("two", 2),
            entry("three", 3),
            entry("four", 4),
            entry("five", 5),
            entry("six", 6),
            entry("seven", 7),
            entry("eight", 8),
            entry("nine", 9),
            entry("0", 0),
            entry("1", 1),
            entry("2", 2),
            entry("3", 3),
            entry("4", 4),
            entry("5", 5),
            entry("6", 6),
            entry("7", 7),
            entry("8", 8),
            entry("9", 9));

    public Day1() {
        super(1);
    }

    @Override
    public Object part1() {
        return input.stream()
                .map(String::chars)
                .map((characters) -> {
                    List<Integer> numerics = characters.filter((character) -> character >= '0' && character <= '9')
                            .boxed()
                            .collect(Collectors.toList());
                    if (numerics.size() == 0)
                        return 0;
                    return (numerics.get(0) - '0') * 10 + numerics.get(numerics.size() - 1) - '0';
                })
                .collect(Collectors.summingInt(n -> n));
    }

    @Override
    public Object part2() {
        return input.stream()
                .map((line) -> {
                    // store first and last index of found calibration values
                    Map<Integer, Integer> valueByPosition = new HashMap<>();
                    VALUE_BY_CALIBRATION.forEach((k, v) -> {
                        int firstIndex = line.indexOf(k);
                        if (firstIndex != -1) {
                            valueByPosition.put(firstIndex, v);
                        }
                        int lastIndex = line.lastIndexOf(k);
                        if (lastIndex != -1) {
                            valueByPosition.put(lastIndex, v);
                        }
                    });

                    int minIndex = Integer.MAX_VALUE;
                    int maxIndex = Integer.MIN_VALUE;
                    for (Integer index : valueByPosition.keySet()) {
                        if (index < minIndex) {
                            minIndex = index;
                        }

                        if (index > maxIndex) {
                            maxIndex = index;
                        }
                    }

                    if (valueByPosition.size() == 0)
                        return 0;

                    return valueByPosition.get(minIndex) * 10 + valueByPosition.get(maxIndex);
                })
                .collect(Collectors.summingInt(n -> n));
    }
}

/**
 * --- Day 1: Trebuchet?! ---
 * Something is wrong with global snow production, and you've been selected to
 * take a look. The Elves have even given you a map; on it, they've used stars
 * to mark the top fifty locations that are likely to be having problems.
 * 
 * You've been doing this long enough to know that to restore snow operations,
 * you need to check all fifty stars by December 25th.
 * 
 * Collect stars by solving puzzles. Two puzzles will be made available on each
 * day in the Advent calendar; the second puzzle is unlocked when you complete
 * the first. Each puzzle grants one star. Good luck!
 * 
 * You try to ask why they can't just use a weather machine ("not powerful
 * enough") and where they're even sending you ("the sky") and why your map
 * looks mostly blank ("you sure ask a lot of questions") and hang on did you
 * just say the sky ("of course, where do you think snow comes from") when you
 * realize that the Elves are already loading you into a trebuchet ("please hold
 * still, we need to strap you in").
 * 
 * As they're making the final adjustments, they discover that their calibration
 * document (your puzzle input) has been amended by a very young Elf who was
 * apparently just excited to show off her art skills. Consequently, the Elves
 * are having trouble reading the values on the document.
 * 
 * The newly-improved calibration document consists of lines of text; each line
 * originally contained a specific calibration value that the Elves now need to
 * recover. On each line, the calibration value can be found by combining the
 * first digit and the last digit (in that order) to form a single two-digit
 * number.
 * 
 * For example:
 * 
 * 1abc2
 * pqr3stu8vwx
 * a1b2c3d4e5f
 * treb7uchet
 * In this example, the calibration values of these four lines are 12, 38, 15,
 * and 77. Adding these together produces 142.
 * 
 * Consider your entire calibration document. What is the sum of all of the
 * calibration values?
 * 
 * Your puzzle answer was 54601.
 * 
 * --- Part Two ---
 * Your calculation isn't quite right. It looks like some of the digits are
 * actually spelled out with letters: one, two, three, four, five, six, seven,
 * eight, and nine also count as valid "digits".
 * 
 * Equipped with this new information, you now need to find the real first and
 * last digit on each line. For example:
 * 
 * two1nine
 * eightwothree
 * abcone2threexyz
 * xtwone3four
 * 4nineeightseven2
 * zoneight234
 * 7pqrstsixteen
 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76.
 * Adding these together produces 281.
 * 
 * What is the sum of all of the calibration values?
 * 
 * Your puzzle answer was 54078.
 */
