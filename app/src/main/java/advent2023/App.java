package advent2023;

import java.util.stream.Stream;

import advent2023.problems.Puzzle;
import advent2023.problems.day1.Day1;

public class App {

    public static void main(String[] args) {
       getPuzzles()
            .forEach((puzzle) -> {
                System.out.println(puzzle.part1());
                System.out.println(puzzle.part2());
            });
    }

    private static Stream<Puzzle> getPuzzles() {
        return Stream.of(
            new Day1()
        );
    }

}

