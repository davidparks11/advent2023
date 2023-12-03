package advent2023.problems.day1;

import java.util.List;
import java.util.stream.Collectors;

import advent2023.problems.Puzzle;

public class Day1 extends Puzzle {

    public Day1() {
        super(1);
    }

    @Override
    public Object part1() {
        return input
                .map(String::chars)
                .map((characters) -> {
                    List<Integer> numerics = characters.filter((character) -> character >= '0' && character <= '9')
                            .boxed()
                            .collect(Collectors.toList());
                    if (numerics.size() == 0)
                        return 0;
                    return (numerics.get(0) - '0') * 10 + numerics.get(numerics.size() - 1) - '0';
                })
                .collect(Collectors.summingInt(Integer::valueOf));
    }

    @Override
    public Object part2() {
        return null;
    }
}
