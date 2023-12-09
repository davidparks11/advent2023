package advent2023;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import advent2023.problems.Puzzle;
import advent2023.problems.day1.Day1;
import advent2023.problems.day2.Day2;
import advent2023.problems.day3.Day3;
import advent2023.problems.day4.Day4;

public class App {

    public static void main(String[] args) {
       getPuzzles()
            .forEach((puzzle) -> {
                Instant t1 = Instant.now();
                Object part1Result = puzzle.part1();
                Instant t2 = Instant.now();
                Object part2Result = puzzle.part2();
                Instant t3 = Instant.now();
                System.out.printf("Day %d, part %d [%dms]: %s\n", puzzle.getDay(), 1, Duration.between(t1, t2).toMillis(), part1Result);
                System.out.printf("Day %d, part %d [%dms]: %s\n", puzzle.getDay(), 2, Duration.between(t2, t3).toMillis(), part2Result);
            });
    }

    private static Stream<Puzzle> getPuzzles() {
        return Stream.of(
            new Day1(),
            new Day2(),
            new Day3(),
            new Day4()
        );
    }

}
