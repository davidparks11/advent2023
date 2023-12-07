package advent2023.problems;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Puzzle {

    private int day;
    protected List<String> input;

    public Puzzle(int day) {
        this.day = day;
        setInput();
    }

    public Object part1() {
        throw new RuntimeException("Not implemented");
    }

    public Object part2() {
        throw new RuntimeException("Not implemented");
    }

    public int getDay() { return day; }

    protected void setInput() {
        ClassLoader classLoader = Puzzle.class.getClassLoader();
        URL resource = classLoader.getResource(String.format("day%d.txt", day));
        File file = new File(resource.getFile());
        try {
            input = Files.lines(Path.of(file.getPath())).toList();
        } catch (Exception e) {
            throw new RuntimeException("Could not read file " + file, e);
        }
    }
}
