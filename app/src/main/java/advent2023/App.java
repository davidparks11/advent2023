package advent2023;

import java.util.stream.Stream;

import advent2023.problems.Puzzle;
import advent2023.problems.day1.Day1;
import advent2023.problems.day2.Day2;
import advent2023.problems.day3.Day3;

public class App {

    public static void main(String[] args) {
       getPuzzles()
            .forEach((puzzle) -> {
                System.out.printf("Day %d, part %d: %s\n", puzzle.getDay(), 1, puzzle.part1());
                System.out.printf("Day %d, part %d: %s\n", puzzle.getDay(), 2, puzzle.part2());
            });
    }

    private static Stream<Puzzle> getPuzzles() {
        return Stream.of(
            new Day1(),
            new Day2(),
            new Day3()
        );
    }

}
